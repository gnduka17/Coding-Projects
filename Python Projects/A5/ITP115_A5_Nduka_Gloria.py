# Gloria Nduka
# ITP 115
# Assignment 5
# 2/22/2019
# Description:
    #Airline ticket


seat = ["","","","","","","","","",""]

#this function prints out the options
def printoptions():
    print("1:  Assign Seat.")
    print("2:  Print Seat Map.")
    print("3:  Print Boarding Pass.")
    print("-1:  Quit.")

#This function put the name in a seat
def putnameonmap(name):
    count =0
    for word in seat:
        if word=="":
            seat[seat.index(word)] = name
            break
        else:
            count=count+1
    if count==10:
        print("Next flight leaves in 3 hours.")

#function prints the seat map
def printseatmap():
    print("*****************************************")
    check = 1
    for word in seat:
        if word =="":
            #holder = seat.index(word)
            print("Seat #" + str(check)+":       Empty")
            check+=1
        else:
            #holder = seat.index(word)
            print("Seat #" + str(check)+ ":       " + word)
            check+=1
    print("*****************************************")

#function prints boarding pass given name
def printboardingpassname(word):
    track = 0
    for name in seat:
        if word==name:
            track+=1
    if track!=0:
        print("======= BOARDING PASS =======")
        print("\tSeat #: " + str(seat.index(word)+1))
        print("\tPassenger Name: " + word)
        print("=============================")

    else:
        print("No passenger with name " + str(word) + " was found")

#function prints boarding pass given seat number
def printboardingpassseat(number):
    if number > 10 or number < 1:
        print("Invalid number--no boarding pass found")
    else:
        print("======= BOARDING PASS =======")
        print("\tSeat #: " + str(number))
        print("\tPassenger Name: "+ seat[number-1])
        print("=============================")

#main function
def main():
    printoptions()
    num = int(input("> "))
    while num != -1:
        if num==1:
            theirname = str(input("Please enter your first name: "))
            putnameonmap(theirname)
        elif num ==2:
            printseatmap()
        elif num ==3:
            newnum = int(input("Type 1 to get Boarding Pass by seat number\nType 2 to get Boarding Pass by name\n> "))
            if newnum==1:
                seatnum = int(input("What is the seat number: "))
                printboardingpassseat(seatnum)
            elif newnum==2:
                theirname=str(input("Enter passenger name: "))
                printboardingpassname(theirname)
            else:
                print("Invalid input")
                printoptions()
                num = int(input("> "))
                continue
        else:
            num = int(input("Please enter choice: "))
            continue
        printoptions()
        num = int(input("> "))
    print("Have a nice day!")





main()




