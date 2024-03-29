#include "types.h"
#include "defs.h"
#include "param.h"
#include "memlayout.h"
#include "mmu.h"
#include "x86.h"
#include "proc.h"
#include "spinlock.h"

extern char getSharedCounter(int index);

void clearThread(struct thread * t);

struct {
  struct spinlock lock;
  struct proc proc[NPROC];
} ptable;

//for mutexes functions
struct{
  struct spinlock lock;
  struct kthread_mutex mutex[MAX_MUTEXES];
}mtable;

static struct proc *initproc;

int nextpid = 1;
int nexttid = 1;
int nextmid = 1;
extern void forkret(void);
extern void trapret(void);

static void wakeup1(void *chan);

void
pinit(void)
{
  initlock(&ptable.lock, "ptable");
}

struct thread*
allocthread(struct proc * p)
{
  struct thread *t;
  char *sp;
  int found = 0;

  for(t = p->threads; found != 1 && t < &p->threads[NTHREAD]; t++)
  {
    if(t->state == TUNUSED)
    {
      found = 1;
      t--;
    }
    else if(t->state == TZOMBIE)
    {
      clearThread(t);
      t->state = TUNUSED;
      found = 1;
      t--;
    }
  }

  if(!found)
    return 0;

  t->tid = nexttid++;
  t->state = TEMBRYO;
  t->parent = p;
  t->killed = 0;

  // Allocate kernel stack.
  if((t->kstack = kalloc()) == 0){
    t->state = TUNUSED;
    return 0;
  }
  sp = t->kstack + KSTACKSIZE;

  // Leave room for trap frame.
  sp -= sizeof *t->tf;
  t->tf = (struct trapframe*)sp;

  // Set up new context to start executing at forkret,
  // which returns to trapret.
  sp -= 4;
  *(uint*)sp = (uint)trapret;

  sp -= sizeof *t->context;
  t->context = (struct context*)sp;
  memset(t->context, 0, sizeof *t->context);
  t->context->eip = (uint)forkret;

  return t;
}



//PAGEBREAK: 32
// Look in the process table for an UNUSED proc.
// If found, change state to EMBRYO and initialize
// state required to run in the kernel.
// Otherwise return 0.
// Must hold ptable.lock.
static struct proc*
allocproc(void)
{
  struct proc *p;
  struct thread *t;

  for(p = ptable.proc; p < &ptable.proc[NPROC]; p++)
    if(p->state == UNUSED)
      goto found;
  return 0;

found:
  p->state = USED;
  p->pid = nextpid++;

  t = allocthread(p);

  if(t == 0)
  {
    p->state = UNUSED;
    return 0;
  }
  p->threads[0] = *t;

  for(t = p->threads; t < &p->threads[NTHREAD]; t++)
    t->state = TUNUSED;

  return p;
}

//PAGEBREAK: 32
// Set up first user process.
void
userinit(void)
{
  struct proc *p;
  struct thread *t;
  extern char _binary_initcode_start[], _binary_initcode_size[];

  acquire(&ptable.lock);

  p = allocproc();
  t = p->threads;
  initproc = p;
  if((p->pgdir = setupkvm()) == 0)
    panic("userinit: out of memory?");
  inituvm(p->pgdir, _binary_initcode_start, (int)_binary_initcode_size);
  p->sz = PGSIZE;
  memset(t->tf, 0, sizeof(*t->tf));
  t->tf->cs = (SEG_UCODE << 3) | DPL_USER;
  t->tf->ds = (SEG_UDATA << 3) | DPL_USER;
  t->tf->es = t->tf->ds;
  t->tf->ss = t->tf->ds;
  t->tf->eflags = FL_IF;
  t->tf->esp = PGSIZE;
  t->tf->eip = 0;  // beginning of initcode.S

  safestrcpy(p->name, "initcode", sizeof(p->name));
  p->cwd = namei("/");

  t->state = TRUNNABLE;

  release(&ptable.lock);
}

// Grow current process's memory by n bytes.
// Return 0 on success, -1 on failure.
int
growproc(int n)
{
  uint sz;
  //acquire lock 
  acquire(&ptable.lock);
  sz = proc->sz;
  if(n > 0){
    if((sz = allocuvm(proc->pgdir, sz, sz + n)) == 0){
      //releasse lock
      release(&ptable.lock);
      return -1;
    }
  } else if(n < 0){
    if((sz = deallocuvm(proc->pgdir, sz, sz + n)) == 0){
      //release lock
      release(&ptable.lock);
      return -1;
    }
  }
  proc->sz = sz;
  switchuvm(proc);
  //release lock
  release(&ptable.lock);
  return 0;
}

// Create a new process copying p as the parent.
// Sets up stack to return as if from system call.
// Caller must set state of returned proc to RUNNABLE.
int
fork(void)
{
  int i, pid;
  struct proc *np;
  struct thread *nt;

  acquire(&ptable.lock);

  // Allocate process.
  if((np = allocproc()) == 0){
    release(&ptable.lock);
    return -1;
  }
  nt = np->threads;

  // Copy process state from p.
  if((np->pgdir = copyuvm(proc->pgdir, proc->sz)) == 0){
    kfree(nt->kstack);
    nt->kstack = 0;
    np->state = UNUSED;
    release(&ptable.lock);
    return -1;
  }

  np->sz = proc->sz;
  np->parent = proc;
  *nt->tf = *thread->tf;

  // Clear %eax so that fork returns 0 in the child.
  nt->tf->eax = 0;

  for(i = 0; i < NOFILE; i++)
    if(proc->ofile[i])
      np->ofile[i] = filedup(proc->ofile[i]);
  np->cwd = idup(proc->cwd);

  safestrcpy(np->name, proc->name, sizeof(proc->name));

  pid = np->pid;

  nt->state = TRUNNABLE;

  release(&ptable.lock);

  return pid;
}

//Kills all alive threads and process
void kill_all(){
  struct thread *t;
  
  //loop through each threads
  for(t = proc->threads; t < &proc->threads[NTHREAD]; t++){
    //If threadis not current thread and not running and not unused make it Zombie
    if(t->tid !=thread->tid &&(t->state!=TINVALID && t->state!=TUNUSED)){
      t->state = ZOMBIE;
    }
  }
  
  // Make current thread zombie 
  thread->state = ZOMBIE;

  // Kill process 
  proc->killed=1;

}


// Exit the current process.  Does not return.
// An exited process remains in the zombie state
// until its parent calls wait() to find out it exited.
void
exit(void)
{
  struct proc *p;
  int fd;

  if(proc == initproc)
    panic("init exiting");

  // Close all open files.
  for(fd = 0; fd < NOFILE; fd++){
    if(proc->ofile[fd]){
      fileclose(proc->ofile[fd]);
      proc->ofile[fd] = 0;
    }
  }

  begin_op();
  iput(proc->cwd);
  end_op();
  proc->cwd = 0;

  acquire(&ptable.lock);

  // Parent might be sleeping in wait().
  wakeup1(proc->parent);

  // Pass abandoned children to init.
  for(p = ptable.proc; p < &ptable.proc[NPROC]; p++){
    if(p->parent == proc){
      p->parent = initproc;
      if(p->state == ZOMBIE)
        wakeup1(initproc);
    }
  }

  kill_all();

  // Jump into the scheduler, never to return.
  thread->state = TINVALID;
  proc->state = ZOMBIE;

  sched();
  panic("zombie exit");
}

void
clearThread(struct thread * t)
{
  if(t->state == TINVALID || t->state == TZOMBIE)
    kfree(t->kstack);

  t->kstack = 0;
  t->tid = 0;
  t->state = TUNUSED;
  t->parent = 0;
  t->killed = 0;
}

// Wait for a child process to exit and return its pid.
// Return -1 if this process has no children.
int
wait(void)
{
  struct proc *p;
  int havekids, pid;
  struct thread * t;

  acquire(&ptable.lock);
  for(;;){
    // Scan through table looking for zombie children.
    havekids = 0;
    for(p = ptable.proc; p < &ptable.proc[NPROC]; p++){
      if(p->parent != proc)
        continue;
      havekids = 1;
      if(p->state == ZOMBIE){
        // Found one.
        pid = p->pid;

        for(t = p->threads; t < &p->threads[NTHREAD]; t++)
          clearThread(t);

        freevm(p->pgdir);
        p->pid = 0;
        p->parent = 0;
        p->name[0] = 0;
        p->killed = 0;
        p->state = UNUSED;
        release(&ptable.lock);
        return pid;
      }
    }

    // No point waiting if we don't have any children.
    if(!havekids || proc->killed){
      release(&ptable.lock);
      return -1;
    }

    // Wait for children to exit.  (See wakeup1 call in proc_exit.)
    sleep(proc, &ptable.lock);  //DOC: wait-sleep
  }
}

//PAGEBREAK: 42
// Per-CPU process scheduler.
// Each CPU calls scheduler() after setting itself up.
// Scheduler never returns.  It loops, doing:
//  - choose a process to run
//  - swtch to start running that process
//  - eventually that process transfers control
//      via swtch back to the scheduler.
void
scheduler(void)
{
  struct proc *p;
  struct thread *t;

  for(;;){
    // Enable interrupts on this processor.
    sti();
    // Loop over process table looking for process to run.
    acquire(&ptable.lock);
    for(p = ptable.proc; p < &ptable.proc[NPROC]; p++){
      if(p->state != USED)
          continue;

      for(t = p->threads; t < &p->threads[NTHREAD]; t++){
        if(t->state != TRUNNABLE)
          continue;

        // Switch to chosen process.  It is the process's job
        // to release ptable.lock and then reacquire it
        // before jumping back to us.


        proc = p;
        thread = t;
        switchuvm(p);
		
		 //cprintf("scheduler p loop 2 state=%d\n",p->state);
		
        t->state = TRUNNING;
        swtch(&cpu->scheduler, t->context);
		
				 //cprintf("scheduler p loop 3\n");
		
		
        switchkvm();


        // Process is done running for now.
        // It should have changed its p->state before coming back.
        proc = 0;
        if(p->state != USED)
          t = &p->threads[NTHREAD];
        
        thread = 0;
      }

    }
    release(&ptable.lock);

  }
}

// Enter scheduler.  Must hold only ptable.lock
// and have changed proc->state. Saves and restores
// intena because intena is a property of this
// kernel thread, not this CPU. It should
// be proc->intena and proc->ncli, but that would
// break in the few places where a lock is held but
// there's no process.
void
sched(void)
{
  int intena;
  if(!holding(&ptable.lock))
    panic("sched ptable.lock");
  if(cpu->ncli != 1)
    panic("sched locks");
  if(thread->state == TRUNNING)
    panic("sched running");
  if(readeflags()&FL_IF)
    panic("sched interruptible");

  intena = cpu->intena;
  swtch(&thread->context, cpu->scheduler);
  cpu->intena = intena;
}

// Give up the CPU for one scheduling round.
void
yield(void)
{
  acquire(&ptable.lock);  //DOC: yieldlock
  thread->state = TRUNNABLE;
  sched();
  release(&ptable.lock);
}

// A fork child's very first scheduling by scheduler()
// will swtch here.  "Return" to user space.
void
forkret(void)
{
  static int first = 1;
  // Still holding ptable.lock from scheduler.
  release(&ptable.lock);

  if (first) {
    // Some initialization functions must be run in the context
    // of a regular process (e.g., they call sleep), and thus cannot
    // be run from main().
    first = 0;
    iinit(ROOTDEV);
    initlog(ROOTDEV);
  }

  // Return to "caller", actually trapret (see allocproc).
}

// Atomically release lock and sleep on chan.
// Reacquires lock when awakened.
void
sleep(void *chan, struct spinlock *lk)
{
	
  if(proc == 0 || thread == 0)
    panic("sleep");

  if(lk == 0)
    panic("sleep without lk");

  // Must acquire ptable.lock in order to
  // change p->state and then call sched.
  // Once we hold ptable.lock, we can be
  // guaranteed that we won't miss any wakeup
  // (wakeup runs with ptable.lock locked),
  // so it's okay to release lk.
  if(lk != &ptable.lock){  //DOC: sleeplock0
    acquire(&ptable.lock);  //DOC: 4lock1
    release(lk);
  }

  
  // Go to sleep.
  thread->chan = chan;
  thread->state = TSLEEPING;
  sched();

  // Tidy up.
  thread->chan = 0;

  // Reacquire original lock.
  if(lk != &ptable.lock){  //DOC: sleeplock2
    release(&ptable.lock);
    acquire(lk);
  }
}

//PAGEBREAK!
// Wake up all processes sleeping on chan.
// The ptable lock must be held.
static void
wakeup1(void *chan)
{
  struct proc *p;
  struct thread *t;

  for(p = ptable.proc; p < &ptable.proc[NPROC]; p++)
    if(p->state == USED)
    {
      for(t = p->threads; t < &p->threads[NTHREAD]; t++)
        if(t->state == TSLEEPING && t->chan == chan)
          t->state = TRUNNABLE;
    }
}

// Wake up all processes sleeping on chan.
void
wakeup(void *chan)
{
  acquire(&ptable.lock);
  wakeup1(chan);
  release(&ptable.lock);
}

// Kill the process with the given pid.
// Process won't exit until it returns
// to user space (see trap in trap.c).
int
kill(int pid)
{
  struct proc *p;
  struct thread *t;

  acquire(&ptable.lock);
  for(p = ptable.proc; p < &ptable.proc[NPROC]; p++){
    if(p->pid == pid){
      p->killed = 1;
      // Wake process from sleep if necessary.
      for(t = p->threads; t < &p->threads[NTHREAD]; t++)
        if(t->state == TSLEEPING)
          t->state = TRUNNABLE;

      release(&ptable.lock);
      return 0;
    }
  }
  release(&ptable.lock);
  return -1;
}

// Kill the threads with of given process with pid.
// Thread won't exit until it returns
// to user space (see trap in trap.c).
void
killSelf()
{
  acquire(&ptable.lock);
  wakeup1(thread);
  thread->state = TINVALID; // thread must INVALID itself! - else two cpu's can run on the same thread
  sched();
}

//PAGEBREAK: 36
// Print a process listing to console.  For debugging.
// Runs when user types ^P on console.
// No lock to avoid wedging a stuck machine further.
void
procdump(void)
{
  static char *states[] = {
  [UNUSED]    "unused",
  [USED]    "used",
  [ZOMBIE]    "zombie"
  };
 
  int i;
  struct proc *p;
  struct thread *t;
  char *state;//, *threadState;
  uint pc[10];

  for(p = ptable.proc; p < &ptable.proc[NPROC]; p++){
    if(p->state == UNUSED)
      continue;
    if(p->state >= 0 && p->state < NELEM(states) && states[p->state])
      state = states[p->state];
    else
      state = "???";

    cprintf("%d %s %s\n", p->pid, state, p->name);
    for(t = p->threads; t < &p->threads[NTHREAD]; t++){
 

      if(t->state == TSLEEPING){
        getcallerpcs((uint*)t->context->ebp+2, pc);
        for(i=0; i<10 && pc[i] != 0; i++)
          cprintf("%p ", pc[i]);
        cprintf("\n");
      }
    }


  }
}

//create a new thread within the context of the calling process
//The newly created thread state will be TRUNNABLE. 
//The caller of kthread_create must allocate a 
//user stack for the new thread to use
int
kthread_create(void*(*start_func)(), void* stack, int stack_size){
  struct thread *t;

  t = allocthread(proc);                    //allocate thread
  if(t==0){
    return -1;
  }
  *t->tf = *thread->tf;                     // Copy current thread’s trap frame

  t->tf->esp = (int)stack + stack_size;     // Make stack pointer inside trap frame stack address + stack size
 
  t->tf->ebp = t->tf->esp;                  // Update base pointer inside trap frame as stack pointer
 
  t->tf->eip = (int)start_func;             // Make instruction pointer inside trap frame start address
  
  t->state = TRUNNABLE;                     //make thread runnablE

 return t->tid;

}

//returns the caller thread's id
int
kthread_id(){

  if(thread!=0&&proc!=0){   //check if process anf thread exists
    return thread->tid;
  }
  return -1; //if failed
}

//terminates the execution of the calling thread
void
kthread_exit(){
//aquire lock
acquire(&ptable.lock);
struct thread *t;
int found = 0; //found flag

// Loop through all threads to find another thread running
for(t=proc->threads; found!=1 && t<&proc->threads[NTHREAD]; t++){
  //  If t is not current thread (because calling thread is current)
  if(t->tid !=thread->tid){
    //  If t is not Unused, not Zombied and not Invalid
    if(t->state != TUNUSED && t->state != TZOMBIE && t->state != TINVALID){
      found=1; //Make flag true

      break; //only one running t is enough so break
    }
  }
}

    if(found){
      wakeup1(thread); //Wakeup all waiting
    }
    else{
      release(&ptable.lock);
      exit();
      wakeup(t);
    }

    thread->state = TZOMBIE; //Make this thread zombie

    sched();    //Call shed to schedule another thread
}

//suspends the execution of the calling 
//thread until the target thread terminates
int
kthread_join(int thread_id){
  //check if thread_id is valid
  if(thread->tid==thread_id){
    return -1;
  }
  if(thread_id<0){
    return -1;
  }
  int found=0;
  acquire(&ptable.lock);
  // create a thread pointer t
  struct thread *t;
  // Loop through all threads to find target thread id(parameter)
  for(t=proc->threads; t<&proc->threads[NTHREAD]; t++){
    //   make t point target thread with thread_id
    if(t->tid==thread_id){
      found=1;
      break;
    }
  }
  if(found==1){
    // while the t->t_id = thread_id and is valid
    while(t->tid ==thread_id &&(t->state != TUNUSED && t->state != TZOMBIE && t->state != TINVALID)){ 
      sleep(t, &ptable.lock); //make t sleep
    }
    // if state of t is zombie clearthread
    if(t->state==TZOMBIE){
      clearThread(t);
    }
    release(&ptable.lock);
    return 0;
  }
  // if not Found return -1
  release(&ptable.lock);
  return -1;
}


//Allocates a mutex object and initializes it
//the initial state should be unlocked 
int 
kthread_mutex_alloc(){
  // Create a mutex pointer m
  struct kthread_mutex *m;
  acquire(&mtable.lock); //acquire mtable lock

  // Loop through all mutex table
  for(m=mtable.mutex; m<&mtable.mutex[NPROC]; m++){
  //  If m is unused break
    if(m->state==MUNUSED){
      break;
    }
  }
  // If unused mutex not found return -1
  if(m==&mtable.mutex[NPROC]){
    release(&mtable.lock);
    return -1;
  }
     
  m->mutex_id = nextmid++;    // set mutex id

  m->state = MUNLOCKED;       // set mutex state to unlock
  release(&mtable.lock);
  return m->mutex_id;         //  Return mutex id
}

//De-allocates a mutex object which is no longer needed
int
kthread_mutex_dealloc(int mutex_id){
  // Create a mutex pointer m 
  struct kthread_mutex *m;
  acquire(&mtable.lock);
  // Loop through all mutex table to find given mutex_id
  for(m=mtable.mutex; m<&mtable.mutex[NPROC]; m++){
    //  If found, break
    if(m->mutex_id==mutex_id){
      break;
    }
  }

  // unused mutex not found, return -1
  if(m==&mtable.mutex[NPROC]){
    release(&mtable.lock);
    return -1;
  }
 //If m is locked -> we can’t dealloc
  if(m->state==MLOCKED){
    release(&mtable.lock);
    return -1;
  }
  // mutex id is 0 
  m->mutex_id=0;
  // mutext state is UNUSED
  m->state = MUNUSED;
  release(&mtable.lock);
  return 0; //success
}

//used by a thread to lock the mutex specified by mutex ID
//If the mutex is already locked by another thread, this call 
//will block the calling thread (change the thread state to TBLOCKED) 
//until the mutex is unlocked.
int
kthread_mutex_lock(int mutex_id){
  // Create a mutex pointer m )
  struct kthread_mutex *m;
  acquire(&mtable.lock);

  // Loop through all mutex table to find given mutex_id
  for(m=mtable.mutex; m<&mtable.mutex[NPROC]; m++){
  //  if mutex ID found, break
    if(m->mutex_id==mutex_id){
      break;
    }
  }

  // If given mutex_id not found
  if(m==&mtable.mutex[NPROC] || m->state==MUNUSED){
    release(&mtable.lock);
    return -1;
  }

  //spinlock
  while(m->state==MLOCKED){
    sleep(m,&mtable.lock);
    thread->state = TBLOCKED; //TBLOCKED implementation
  }

  // If (m->state != M_UNLOCKED) -> failed, return -1
  if(m->state != MUNLOCKED){
    release(&mtable.lock);
    return -1;
  }

  //mutex state is locked
  m->state=MLOCKED;
  release(&mtable.lock);
  return 0;

}

//unlocks the mutex specified by the argument mutex_id if called
//by the owning thread, and if there are any blocked threads, one of the
//threads will acquire the mutex. 
int
kthread_mutex_unlock(int mutex_id){
  // Create a mutex pointer m
  struct kthread_mutex *m;
  acquire(&mtable.lock);
  // Loop through all mutex table to find given mutex_id
  for(m=mtable.mutex; m<&mtable.mutex[NPROC]; m++){
  //break, if found 
    if(m->mutex_id==mutex_id){
      break;
    }
  }

  // If given mutex_id not found
  if(m==&mtable.mutex[NPROC]){
    release(&mtable.lock);
    return -1;
  }
  // If (m->state is not LOCKED)
  if(m->state!=MLOCKED){
    release(&mtable.lock);
    return -1;
  }
  //set mutex state to unlocked
  m->state=MUNLOCKED;
  //set thread state to runnable
  thread->state = TRUNNABLE;
  // wakeup thread
  wakeup1(m);
  release(&mtable.lock);
  return 0;
}
