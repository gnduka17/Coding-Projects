/* REMEMBER:
 * - This is the only file that you need to edit!
 * - Declare variables only at the beginning of a function (as in C89).
 * - Do not include <stdio.h> header (it confuses dlc)
 * - Check correctness with ./btest
 * - Check operator constraints with ./dlc bits.c
 * - Run ./grade before you commit/push your solution
 */

/*
 * tmin - return minimum two's complement integer
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 1
 *   Rating: 1
 */
int tmin(void) {
  return 1<<31;
}

/* 
 * bitOr - x|y using only ~ and & 
 *   Example: bitOr(6, 5) = 7
 *   Legal ops: ~ &
 *   Max ops: 4
 *   Rating: 1
 */
int bitOr(int x, int y) {
  return ~(~x&~y);
}

/*
 * negate - return -x
 * (tip: remember the definition of two's complement!)
 *   Example: negate(1) = -1.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 2
 *   Rating: 1
 */
int negate(int x) {
  return (~x+1);
}

/*
 * isNotEqual - return 0 if x == y, and 1 otherwise
 *   Examples: isNotEqual(5,5) = 0, isNotEqual(4,5) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 3
 *   Rating: 2
 */
int isNotEqual(int x, int y) {
  return !!(x^y);
}

/*
 * isGreater - if x > y  then return 1, else return 0
 *   Example: isGreater(4,5) = 0, isGreater(5,4) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 3
 */
int isGreater(int x, int y) {
  int op = y + (~x+1);
  int k = ~x&y;
  return ((((op | k) & (~x | y))) >> 31) & 1;
 
}

/*
 * subtractionOK - Determine if can compute x-y without overflow
 *   Example: subtractionOK(0x80000000,0x80000000) = 1,
 *            subtractionOK(0x80000000,0x70000000) = 0,
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 16
 *   Rating: 3
 */
int subtractionOK(int x, int y)
{
  int ynum = (y>>31)&1;
  int xnum = (x>>31)&1;
  int sum = (x+(~y+1))>>31;
  int sumsign = (sum&1)^xnum;
  return !(sumsign&(ynum^xnum));
}

/*
 * conditional - same as x ? y : z
 *   Example: conditional(2,4,5) = 4
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 8
 *   Rating: 3
 */
int conditional(int x, int y, int z) {
  int t = (!x<<31)>>31;
  int hold = (~t&y);
  int sechold = (t&z);
  return hold | sechold;
}

/*
 * satMul2 - multiplies by 2, saturating to Tmin or Tmax if overflow
 *   Examples: satMul2(0x30000000) = 0x60000000
 *             satMul2(0x40000000) = 0x7FFFFFFF (saturate to TMax)
 *             satMul2(0x80000001) = 0x80000000 (saturate to TMin)
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 14
 *   Rating: 3
 */
int satMul2(int x) {
  int min = 1<<31;
  int mul2 = x>>31;
  int xdoub = x<<1;
  int ov = (xdoub^x)>>31;
  int clea = ov &(mul2 ^ ~min);
  return clea | (~ov &xdoub);
}

/*
 * byteSwap - swaps the nth byte and the mth byte
 *  Examples: byteSwap(0x12345678, 1, 3) = 0x56341278
 *            byteSwap(0xDEADBEEF, 0, 2) = 0xDEEFBEAD
 *  You may assume that 0 <= n <= 3, 0 <= m <= 3
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 17
 *  Rating: 2
 */
int byteSwap(int x, int n, int m) {
  int mtemp = m<<3;
  int ntemp = n<<3;
  int nrest = (x >> ntemp) & 255;
  int mrest = (x >> mtemp) & 255;
  //m and n not equal clear m and n
  int xhold = x ^ (mrest << mtemp);
  xhold = xhold ^ (nrest <<ntemp);
  //reset back swap
  return xhold | (mrest<<ntemp) | (nrest <<mtemp);
}

/*
 * floatNegate - Return bit-level equivalent of expression -f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   When argument is NaN, return argument.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 7
 *   Rating: 2
 */
unsigned floatNegate(unsigned uf) {
  unsigned ex = uf >>23 & 255;
  unsigned part = uf <<9;
  //if ex all 1's and frac !=0
  if((ex==255 && part != 0) ){
    return uf;
  }
  //flip sign
  return uf ^ (1<<31);
}

/*
 * floatIsEqual - Compute f == g for floating point arguments f and g.
 *   Both the arguments are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   If either argument is NaN, return 0.
 *   +0 and -0 are considered equal.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 23
 *   Rating: 2
 */
int floatIsEqual(unsigned uf, unsigned ug) {
  unsigned a;
  unsigned b;
  unsigned holder = (~(1 << 31));
  unsigned ftemp = holder & uf;
  unsigned gtemp = holder & ug;
  unsigned spil = ~0 + (1 << 23); 
  if(ftemp==0 && gtemp==0){
    return 1;
  }
  a = (((ftemp >> 23) == 255) && ((spil & uf) != 0));
  b= (((gtemp >> 23) == 255) && ((spil & ug) != 0));
  if(a || b ){
        return 0;
  }
  return uf==ug;
}

/*
 * floatFloat2Int - Return bit-level equivalent of expression (int) f
 *   for floating point argument f.
 *   Argument is passed as unsigned int, but
 *   it is to be interpreted as the bit-level representation of a
 *   single-precision floating point value.
 *   Anything out of range (including NaN and infinity) should return
 *   0x80000000u.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 18
 *   Rating: 4
 */
int floatFloat2Int(unsigned uf) { 

    int ex = (uf >>23) &255;
    int part = uf & 0x7fffff;
    part = part | 0x800000;
    if(ex == 0x7f800000 || (ex - 127) >30){
      return 0x80000000u;
    }
    if(!ex || (ex - 127) <0){
      return 0;
    }
    if((ex - 127)>=23){
      part=part <<((ex - 127)-23);
    }
    else{
      part = part >> (23-(ex - 127));
    }
    if((uf>>31) &1){
      return ~part+1;
    }
    return part;
}
