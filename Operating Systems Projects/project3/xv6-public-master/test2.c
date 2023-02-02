#include "types.h"
#include "stat.h"
#include "user.h"

void 
cpuIntensive(void);

int
main(void)
{
  int pid = fork();
  if (pid > 0) {
    int pid2 = fork();
    if (pid2 > 0) {
      int pid3 = fork();
      if (pid3 > 0) {
        int pid4 = fork();
        if (pid4 > 0) {
          int pid5 = fork();
          if (pid5 > 0) {
            int pid6 = fork();
            if (pid6 > 0) {
              int pid7 = fork();
              if (pid7 > 0) {
                cpuIntensive();
                wait();
              } else {
                cpuIntensive();
              }
              cpuIntensive();
              wait();
             } else {
              cpuIntensive();              
            }
           cpuIntensive();
            wait();
          } else {
            cpuIntensive();            
          }
          cpuIntensive();
          wait();
          } else {
          cpuIntensive();
        }
        cpuIntensive();
        wait();
      } else {
        cpuIntensive();        
      }
      cpuIntensive();
      wait();
    } else {
      cpuIntensive();
    }
    cpuIntensive();
    wait();
  } else {
     cpuIntensive();
  }

  getpinfo(getpid());
  exit();
}
 
void cpuIntensive(void) {
  int randnum = 0;
  for (int i = 0; i < 10000; i++) {
      for (int j = 1; j < i; j++) {
          if (i % j == 0) {
              randnum++;
          }
      }
  }
  printf(1, "Rand num and mod in range %d and %d: %d \n", 1, 10000, randnum);
}