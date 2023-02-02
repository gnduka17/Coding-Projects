#include "types.h"
#include "user.h"
#include "date.h"
int
main(int argc, char *argv[])
{
 struct rtcdate r;
 if (date(&r)) {
	 printf(2, "date failed\n");
	 exit();
 }
 // your code to print the date-time
 // all printing functionalities in sys_date function in sysproc.c
 exit();
}
