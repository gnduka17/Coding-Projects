def mysum1(listofnum):
    if len(listofnum)== 0:
        return 0
    sum = 0
    for num in listofnum:
        sum = sum+num

    return sum


def myfib1(num):
    if num ==0:
        return 0;
    elif num ==1 or num==2:
        return 1
    else:
        return myfib1(num-1)+myfib1(num-2)


def main():
    numlist = [1,5,7]
    print(mysum1(numlist))

    numlist2 = []
    print(mysum1(numlist2))

    numlist3 = [-5,3]
    print(mysum1(numlist3))

    print(myfib1(3))
    print(myfib1(5))
    print(myfib1(7))


main()
