#include "types.h"
#include "user.h"
#include "stat.h"

int
main(void)
{
	int pid = fork();
	if (pid > 0) {
	    for (int i = 0; i < 1000; i++) {
	      printf(1, "I/O Intensive\n");
	    }
	    wait();
	    getpinfo(getpid());
	    exit();
	 } else {
	 	volatile unsigned long long i;
  		for (i = 0; i < 1000000000ULL; ++i);
    getpinfo(getpid());
    exit();
  }
}