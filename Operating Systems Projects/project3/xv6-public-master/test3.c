#include "types.h"
#include "stat.h"
#include "user.h"

int
main(void)
{
  for (int j = 0; j < 100; j++) {
    sleep(1);
  }

  getpinfo(getpid());
  exit();
}