# Gloria Nduka
# ITP 115
# Assignment 7
# 3/13/2019
# nduka@usc.edu
# Description:
    #Tic Tac Toe

import TicTacToeHelper

#function takes in list and integer position and return boolean to see if the spot is available
def isValidMove(boardList, spot):
    if spot > 8:
        return False
    elif spot < 0:
        return False
    elif boardList[spot].isdigit():
        return True
    else:w
        return False

#function to update the board; takes in a list, int position, and the letter
def updateBoard(boardList, spot, playerLetter):
    boardList[spot]=playerLetter

#function to print the board in a pretty way
def printPrettyBoard(board_list):
    print()
    counter = 0
    for i in range(5):
        if i%2==0:
            for j in range(2):
                print(board_list[counter], end=" | ")
                counter += 1

            #counter+=1
            print(board_list[counter])
            counter+=1
        else:
            print("---------")


#function that stimulates the tic tac toe game
def playGame():

    #initializes the board and variables
    board = ["0","1","2","3","4","5","6","7","8"]
    nummoves=0
    letter = 1

    #asks user what letter they would like to be
    player1= input("Would you like to be be X or O?: ")
    while(player1!="x" and player1!="X" and player1!="o" and player1!="O"):
        print("Invalid Option")
        player1 = input("Would you like to be be X or O?: ")

    if player1=="x" or player1=="X":
        print("You are Player X. You will go first")
    elif player1=="o" or player1=="O":
        print("You are Player O. You will go second")

    #prints board
    printPrettyBoard(board)

    if letter%2!=0:
        numspot = int(input("Player X, pick a spot: "))
    else:
        numspot = int(input("Player O, pick a spot: "))



    #if its not a valid move continue until a valid move is chosen
    while (not isValidMove(board, numspot)):
        print("Invalid move, please try again.")
        if letter % 2 != 0:
            numspot = int(input("Player X, pick a spot: "))
        else:
            numspot = int(input("Player O, pick a spot: "))

    #update the board and the number of moves
    if letter%2!=0:
        updateBoard(board, numspot,"X" )
    else:
        updateBoard(board, numspot,"O")

    nummoves += 1

    #if there isnt a winner continue to play
    while TicTacToeHelper.checkForWinner(board, nummoves)=="n":

        printPrettyBoard(board)
        letter+=1

        if letter % 2 != 0:
            numspot = int(input("Player X, pick a spot: "))
        else:
            numspot = int(input("Player O, pick a spot: "))

        while (not isValidMove(board, numspot)):
            print("Invalid move, please try again.")
            if letter % 2 != 0:
                numspot = int(input("Player X, pick a spot: "))
            else:
                numspot = int(input("Player O, pick a spot: "))

        if letter % 2 != 0:
            updateBoard(board, numspot, "X")
        else:
            updateBoard(board, numspot, "O")

        nummoves += 1

    print("")
    print("Game Over!")

    if TicTacToeHelper.checkForWinner(board, nummoves) == "s":
        print("Stalemate reached!")
    else:
        print("Player",TicTacToeHelper.checkForWinner(board, nummoves), "is the winner!" )

    print()


#main function continually ask user if they want to continue playing
def main():
    print("Welcome to Tic Tac Toe!\n")
    playGame()
    answer =input("Would you like to play another round? (y/n): ")
    while(answer!= "n" and answer!= "N"):
        playGame()
        answer = input("Would you like to play another round? (y/n): ")
    print("Goodbye!")


main()