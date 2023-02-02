#include "types.h"
#include "x86.h"
#include "defs.h"
#include "date.h"
#include "param.h"
#include "memlayout.h"
#include "mmu.h"
#include "proc.h"

int
sys_fork(void)
{
  return fork();
}

int
sys_exit(void)
{
  exit();
  return 0;  // not reached
}

int
sys_wait(void)
{
  return wait();
}

int
sys_kill(void)
{
  int pid;

  if(argint(0, &pid) < 0)
    return -1;
  return kill(pid);
}

int
sys_getpid(void)
{
  return myproc()->pid;
}

int
sys_sbrk(void)
{
  int addr;
  int n;

  if(argint(0, &n) < 0)
    return -1;
  addr = myproc()->sz;
  if(growproc(n) < 0)
    return -1;
  return addr;
}

int
sys_sleep(void)
{
  int n;
  uint ticks0;

  if(argint(0, &n) < 0)
    return -1;
  acquire(&tickslock);
  ticks0 = ticks;
  while(ticks - ticks0 < n){
    if(myproc()->killed){
      release(&tickslock);
      return -1;
    }
    sleep(&ticks, &tickslock);
  }
  release(&tickslock);
  return 0;
}

// return how many clock tick interrupts have occurred
// since start.
int
sys_uptime(void)
{
  uint xticks;

  acquire(&tickslock);
  xticks = ticks;
  release(&tickslock);
  return xticks;
}

// retrieves integer input and checks if 0 or 1
//updates the process on/off tag accordingly
//returns the total number of syscalls.
int
sys_trace(void)
{
  struct proc *p = myproc();
  int n;

  //retrieves int passed in
  if(argint(0,&n) < 0){
    return -1;
  }
  if(n==0){
    p->onoff = 0;    //sets the on/off tag to respective number
  }
  else if(n>0){
    p->onoff = 1;
  }
  else{
    return -1;   //invalid input if negative number
  }
  return p->numsys;   //returns the number of system calls
}

//prints out the current time in UTC time 
//uses helper function cmostime() defined in lapic.c which
//reads real time clock
int
sys_date(void)
{
char* p;

//months array to print out month in string form; index is month number
char months[14][15]={"","January","February", "March", "April", "May","June","July", "August", "September","October", "November", "December"};

if(argptr(0,&p,sizeof(struct rtcdate))<0){    //retrieving pointer rtcdate struct through argptr
  return -1;
}
struct rtcdate *np = (struct rtcdate*)&p;      //cast the pointer received through argptr to rtcdate struct pointer
cmostime(np);        //pass the struct rtcdate as a pointer into cmostime function

//use variables for cleanliness
int mon = (int)np->month; 
int h=(int)np->hour;
int m=(int)np->minute;

//parsing; printing format: Month Day, Year Hour:Minute AM/PM
if(h>=12&&h<24){ //if hour between 12 and 24 then it is PM
  if(h==12){ //if hour is 12 no need to subtract 12 from it
    if(m<10){ //if minute less than 10, add 0 in front
      cprintf("%s %d, %d %d:0%d PM\n", months[mon], np->day, np->year, np->hour, np->minute);
    }
    else{
      cprintf("%s %d, %d %d:%d PM\n", months[mon], np->day, np->year, np->hour, np->minute);
    }
  }
  else{
    if(m<10){
      cprintf("%s %d, %d %d:0%d PM\n", months[mon], np->day, np->year, h-12, np->minute);
    }
    else{
      cprintf("%s %d, %d %d:%d PM\n", months[mon], np->day, np->year, h-12, np->minute);
    }
  }
}
else{
  if(h==0){ //if hour is 0, print as 12
    if(m<10){
      cprintf("%s %d, %d 12:0%d AM\n", months[mon], np->day, np->year, np->minute);
    }
    else{
      cprintf("%s %d, %d 12:%d AM\n", months[mon], np->day, np->year, np->minute);
    }
  }
  else{
    if(m<10){
      cprintf("%s %d, %d %d:0%d AM\n", months[mon], np->day, np->year, np->hour, np->minute);
    }
    else{
      cprintf("%s %d, %d %d:%d AM\n", months[mon], np->day, np->year, np->hour, np->minute);
    }
    
  }
  
}
return 0;  
}
