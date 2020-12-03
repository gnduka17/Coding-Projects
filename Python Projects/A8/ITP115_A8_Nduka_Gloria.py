# Gloria Nduka
# ITP 115
# Assignment 8
# 3/22/2019
# nduka@usc.edu
# Description:
    #File Processing

def outputToFile(file, num, list,mfrlist, carlinelist):
    for item in list:
        if item==num:
            fileMFR = mfrlist[list.index(item)]
            filecarline = carlinelist[list.index(item)]
            print("\t", fileMFR, filecarline,"\n", file=file)
            list[list.index(item)]=-1

def main():
    ClassListpart1 = []
    MPGList = []
    MFRpart1=[]
    CarLinepart1=[]

    #output menu and ask for user input
    print("Welcome to EPA Mileage Calculator")
    year = int(input("What year would you like to view data for? (2008 or 2009): "))

    while year!=2008 and year!=2009:
        print("*Invalid input, please try again!")
        year=int(input("What year would you like to view data for? (2008 or 2009): "))

    if year==2009:
        inputfile="epaVehicleData2009 (2).csv"
        word=str(2009)
    elif year==2008:
        inputfile = "epaVehicleData2008.csv"
        word = str(2008)

    filename = input("Enter the filename to save results to: ")
    filein= open(inputfile, "r")

    filein.readline()

    for line in filein:
        line =line.strip()
        datalist=line.split(",")

        if datalist[0]!="STANDARD PICKUP TRUCKS 4WD" and datalist[0]!="STANDARD PICKUP TRUCKS 2WD" and datalist[0]!="SMALL PICKUP TRUCKS 2WD" and datalist[0]!="SMALL PICKUP TRUCKS 4WD" and datalist[0]!="VANS - CARGO TYPE" and datalist[0]!="VANS - PASSENGER TYPE" and datalist[0]!="MINIVAN - 2WD" and datalist[0]!="MINIVAN - 4WD":
            ClassListpart1.append(datalist[0])
            MPGList.append(datalist[8])
            CarLinepart1.append(datalist[2])
            MFRpart1.append(datalist[1])

    #converting elements in MPGList all to integers
    MPGList = [int(i) for i in MPGList]

    maxnum = max(MPGList)
    minnum = min(MPGList)

    fileOut = open(filename, "w")

    print("EPA City MPG Calculator (" + word + ")", file=fileOut)
    print("-----------------------------------", file=fileOut)

    print("Maximum Mileage (city):", maxnum, file=fileOut)
    print("")
    outputToFile(fileOut, maxnum, MPGList, MFRpart1, CarLinepart1)

    print("Minimum Mileage (city):", minnum, file=fileOut)
    print("")

    outputToFile(fileOut, minnum, MPGList, MFRpart1, CarLinepart1)

    fileOut.close()
    print("Operation Success! Mileage data has been saved to", filename)
    print("Thanks, and have a great day!")

    filein.close()


main()
