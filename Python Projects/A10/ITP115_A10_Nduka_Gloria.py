# Gloria Nduka
# ITP 115
# Assignment 10
# 4/6/2019
# nduka@usc.edu
# Description:
    #Animal Daycare

#this is the animal class
class Animal():
    def __init__(self, hungerparam, happinessparam, healthparam, energyparam, ageparam, nameparam, speciesparam):
        self.hunger=hungerparam
        self.happiness=happinessparam
        self.health=healthparam
        self.energy=energyparam
        self.age=ageparam
        self.name=nameparam
        self.species=speciesparam
    #play function adds to happiness and hunger but if over 100 then set to 100
    def play(self):
        self.happiness+=10
        self.hunger+=5
        if self.hunger>100:
            self.hunger=100
        if self.happiness>100:
            self.happiness=100
    #feed function decreases hunger;if less than 0 then set to 0
    def feed(self):
        self.hunger-=10
        if self.hunger<0:
            self.hunger=0
    #give medicine decreases happiness and increase health then set it to either 0 if below 0 or 100 if over 100
    def giveMedicine(self):
        self.happiness-=20
        self.health+=20
        if self.happiness<0:
            self.happiness=0
        if self.health>100:
            self.health=100
    #sleep adds 20 to energy and 1 to age
    def sleep(self):
        self.energy+=20
        self.age+=1
        if self.energy>100:
            self.energy=100
    #outputs pet info
    def __str__(self):
        info = "Name: "+ self.name + " the "+ self.species
        info+="\nHealth: "+str(self.health)
        info+="\nHappiness: "+str(self.happiness)
        info+="\nHunger: " +str(self.hunger)
        info+="\nEnergy: "+str(self.energy)
        info+="\nAge: "+str(self.age)
        return info

#function takes animal info from file and loads it into a list
def loadAnimals(filename):
    filein = open(filename, "r")
    AnimalList = []
    #for each line, make an Animal using info parsed from file and append it to list
    for line in filein:
        line=line.strip()
        AnimalInfoList = line.split(",")
        InstAnimal = Animal(int(AnimalInfoList[0]),int(AnimalInfoList[1]),int(AnimalInfoList[2]),int(AnimalInfoList[3]),int(AnimalInfoList[4]),AnimalInfoList[5],AnimalInfoList[6])
        AnimalList.append(InstAnimal)
    filein.close()
    return AnimalList

#displays menu
def displayMenu():
    print("1.) Play")
    print("2.) Feed")
    print("3.) Give Medicine")
    print("4.) Sleep")
    print("5.) Print an Animal's stats")
    print("6.) View All Animals")
    print("7.) Exit")

#given a list of animals it outputs the animal information and asks the user to choose which animal then returns that animal
def selectAnimal(listofanimals):
    for item in listofanimals:
        #to prevent hardcoding use length of list to output the animals
        print(str(listofanimals.index(item)+1)+")", item.name, item.species)
    print("")
    option = int(input("Please select an animal from the list (enter the 1-"+str(len(listofanimals)) + "): "))
    #error checking
    while option <=0 or option>len(listofanimals):
        print("Invalid input, please try again!")
        print("")
        option = int(input("Please select an animal from the list (enter the 1-" + str(len(listofanimals)) + "): "))
    #return selected animal
    return listofanimals[option-1]

def OutputToFile(filename, listofanimals):
    fileout=open(filename, "w")
    for item in listofanimals:
        print(str(item.hunger)+","+str(item.happiness)+","+str(item.health)+","+str(item.energy)+","+str(item.age)+","+item.name+","+item.species, file=fileout)
    fileout.close()


def main():
    options=[1,2,3,4,5,6,7]
    file="animals.csv"
    #loads animal into list
    AniList=loadAnimals(file)
    print("Welcome to the Animal Daycare! Here is what we can do:")
    print("")
    displayMenu()
    print("")
    selection = int(input("Please make a selection: "))
    #error checking
    while selection not in options:
        print("*Invalid selection, please try again.")
        print("")
        displayMenu()
        selection = int(input("Please make a selection: "))

    while selection in options:
        if selection==1:
            animal=selectAnimal(AniList)
            #play with animal
            animal.play()
            print("You played with",animal.name,"the",animal.species)
            print("")
            displayMenu()
            print("")
            selection = int(input("Please make a selection: "))
            #error checking
            while selection not in options:
                print("*Invalid selection, please try again.")
                selection = int(input("Please make a selection: "))
        elif selection==2:
            animal = selectAnimal(AniList)
            #feed animal
            animal.feed()
            print("You fed", animal.name, "the", animal.species)
            print("")
            displayMenu()
            print("")
            selection = int(input("Please make a selection: "))
            #error checking
            while selection not in options:
                print("*Invalid selection, please try again.")
                selection = int(input("Please make a selection: "))
        elif selection==3:
            animal = selectAnimal(AniList)
            #give animal meds
            animal.giveMedicine()
            print("You gave", animal.name, "the", animal.species,"some medicine!")
            print("")
            displayMenu()
            print("")
            selection = int(input("Please make a selection: "))
            #error checking
            while selection not in options:
                print("*Invalid selection, please try again.")
                selection = int(input("Please make a selection: "))
        elif selection==4:
            animal = selectAnimal(AniList)
            #animal sleeps
            animal.sleep()
            print(animal.name, "the", animal.species,"took a nap!")
            print("")
            displayMenu()
            print("")
            selection = int(input("Please make a selection: "))
            #error checking
            while selection not in options:
                print("*Invalid selection, please try again.")
                selection = int(input("Please make a selection: "))
        elif selection==5:
            animal = selectAnimal(AniList)
            #prints the animal information
            print(animal)
            print("********************************")
            print("")
            displayMenu()
            print("")
            selection = int(input("Please make a selection: "))
            #error checking
            while selection not in options:
                print("*Invalid selection, please try again.")
                selection = int(input("Please make a selection: "))
        elif selection==6:
            #prints all the animal information
            for item in AniList:
                print(item)
                print("********************************")
            print("")
            displayMenu()
            print("")
            selection = int(input("Please make a selection: "))
            #error checking
            while selection not in options:
                print("*Invalid selection, please try again.")
                selection = int(input("Please make a selection: "))
        elif selection==7:
            #puts info into file
            OutputToFile(file, AniList)
            #leaves;exit
            break
    print("Goodbye!")

main()