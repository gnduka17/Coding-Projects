# Gloria Nduka
# ITP 115
# Assignment 6
# 3/2/2019
# nduka@usc.edu
# Description:
    #Rock, Paper, Scissors
import random

#function to display menu
def displayMenu():
    print("Welcome! Let's play rock, paper, scissors.")
    print("The rules of the game are:")
    print("\tRock smashes scissors")
    print("\tScissors cut paper")
    print("\tPaper covers rock")
    print("\tIf both the choices are the same, it's a tie")

#function to get computer choice
def getComputerChoice():
    num = [0, 1, 2]
    comp = random.choice(num)
    return comp

#function to get player choice
def getPlayerChoice():
    user = int(input(("Please choose (0) for rock, (1) for paper or (2) for scissors\n")))
    return user


#function to simulate rock paper scissors
def playRound(computerChoice, playerChoice):
    if playerChoice==0:
        print("You chose Rock.")
        if computerChoice == 0:
            print("The computer chose Rock.")
            print("Both have chosen Rock. It's a tie.")
            return 0
        elif computerChoice == 1:
            print("The computer chose Paper.")
            print("Paper covers Rock. Computer wins!")
            return -1
        elif computerChoice == 2:
            print("The computer chose Scissors.")
            print("Rock smashes Scissors. You win!")
            return 1
    elif playerChoice==1:
        print("You chose Paper.")
        if computerChoice == 0:
            print("The computer chose Rock.")
            print("Paper covers Rock. You win!")
            return 1
        elif computerChoice == 1:
            print("The computer chose Paper.")
            print("Both have chosen Paper. It's a tie.")
            return 0
        elif computerChoice == 2:
            print("The computer chose Scissors.")
            print("Scissors cut paper. Computer wins!")
            return -1
    elif playerChoice==2:
        print("You chose Scissors.")
        if computerChoice == 0:
            print("The computer chose Rock.")
            print("Rock smashes Scissors. Computer wins!")
            return -1
        elif computerChoice == 1:
            print("The computer chose Paper.")
            print("Scissors cut paper. You win!")
            return 1
        elif computerChoice == 2:
            print("The computer chose Scissors.")
            print("Both have chosen Scissors. It's a tie.")
            return 0
    else:
        print("Invalid input")

#function to ask user if they want to continue playing
def continueGame():
    continueplaying = input("Do you want to continue playing? Enter (y) for yes or (n) for no.\n")
    if continueplaying=="y" or continueplaying=="Y":
        return True

    elif continueplaying=="n" or continueplaying=="N":
        return False
    else:
        print("Invalid input")



def main():
    #variables to keep track of wins
    compwins=0
    playerwins=0
    ties=0
    #use functions to play the game
    displayMenu()
    PC=getPlayerChoice()
    CC=getComputerChoice()
    num = playRound(CC,PC)

    #update wins
    if num==1:
        playerwins+=1
    elif num==-1:
        compwins+=1
    elif num==0:
        ties+=1
    while(continueGame()):
        displayMenu()
        num = playRound(getComputerChoice(), getPlayerChoice())
        if num == 1:
            playerwins += 1
        elif num == -1:
            compwins += 1
        elif num == 0:
            ties += 1
    #ouput the results
    print("\nYou won", playerwins, "game(s).")
    print("The computer won", compwins, "game(s).")
    print("You tied with the computer", ties, "time(s).\nThanks for playing!")


main()








