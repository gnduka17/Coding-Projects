# Gloria Nduka
# ITP 115
# Assignment 4
# 2/6/2019
# Description:
# Part 1 is a word jumbler game
# Part 2 is encrypter/decrypter
import random

#Part 1 Word Jumble Game

words =["apple", "church", "Monday", "dog", "Seattle", "Nigeria", "basketball", "grapefruit", "gym", "food","boyfriend"]

#pick random word from list
chosenword = random.choice(words)

#put word in list
wordinlist = list(chosenword)

#get length of word
wordsize = len(chosenword)

#create a new list for the jumbled word
jumbled = list()

# manipulates the word list and inserts random letter in jumbled list
while wordsize != 0:
    rand = random.randrange(wordsize)
    jumbled.append(wordinlist[rand])
    wordinlist.remove(wordinlist[rand])
    wordsize=wordsize-1

#outputting the jumbled word
print("")
print("The jumbled word is", ''.join(jumbled))
print("")

attempts = 1
guessed=input("Please enter your guess: ")

#user figure out word and output how many times they've guessed
while guessed!=chosenword and guessed.upper()!=chosenword.upper() and guessed.upper()!=chosenword.upper():
    attempts = attempts+1
    print("Try Again.")
    guessed = input("Please enter your guess: ")

print("You got it!")
if attempts==1:
    print("It took you", attempts, "try.")
else:
    print("It took you", attempts, "tries.")






#Part2 Encrypt/Decrypt

#declare helper variables
num = 0
newnum = num
encryptlist = []
decryptlist = []

#list of alphabet
alphabet = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]

#get user input
message = input("Enter a message: ")
shiftnum = int(input("Enter a number to shift by (0-25): "))

#convert message to list
listmessge = list(message)

#figure out count as the number of iterations in list
count = len(listmessge)
newcount=count

#create cipher alphabet with slicing
sliced1 = alphabet[shiftnum:]
sliced2 = alphabet[0:shiftnum]
cipheralphabet= sliced1+sliced2

#create cypher message
while count!=0:
    #if what is in tbe list isn't a letter then just append whats in listmessage
    if listmessge[num].isalpha()==False:
        encryptlist.append(listmessge[num])
        #else, find letter corresponding to cipher alphabet
    else:
        encryptlist.append(cipheralphabet[alphabet.index(listmessge[num])])
    num = num+1
    count=count-1


#output message
print("Encrypting message....")
print("\tEncrypted message:", ''.join(encryptlist))
print("")

print("Decrypting message....")

#decrypting message
while newcount!=0:
    # if what is in tbe list isn't a letter then just append whats in encryptlist
    if encryptlist[newnum].isalpha()==False:
        decryptlist.append(encryptlist[newnum])
    # else, find letter corresponding to regular alphabet
    else:
        decryptlist.append(alphabet[cipheralphabet.index(encryptlist[newnum])])
    newnum = newnum+1
    newcount=newcount-1

#print message4
print("\tDecrypted message:", ''.join(decryptlist))
print("\tOriginal message:", ''.join(listmessge))




