# Gloria Nduka
# ITP 115
# Assignment 3
# 2/2/2019
# Description:
# Part 1 is translating a given word to Elvish
# Part 2 finds the largest number

import random


# Part 1 – Igpén Lvísheá (Pig Elvish)

print("Elcómewó óten heten Igpén Lvísheá ránslátórtë!")
print("(Welcome to the Pig Elvish translator!)")
print("")

#first user prompt
word = input("Please enter a word you would like to translate: ")

#replace the k and c
word = word.replace("k", "c")
word = word.replace("K","C")

size = len(word)
# creates a list of vowels to use
letters=["a", "ë", "i", "o", "u"]

#puts word in list
wordlist=list(word)

#appends first letter
if wordlist[0].isupper():
    wordlist.append(wordlist[0].lower())
    wordlist.remove(wordlist[0])
    wordlist[0]=wordlist[0].upper()
else:
    wordlist.append(wordlist[0])
    wordlist.remove(wordlist[0])

#puts random vowel at the end
if size>=4:
    randnum = random.randrange(5)
    wordlist.append(letters[randnum])
else:
    wordlist.append("e")
    wordlist.append("n")


print("'" + word + "' in elvish is: " + ''.join(wordlist))
print("")


#do first conversion here and output

anotherone = input("Would you like to translate another word? (y/n): ")

if anotherone != "y" and anotherone!="Y" and anotherone!="n" and anotherone!="N":
    print("You did not give a valid input.")
    print("")
    print("Oodbyega! Aveha aen icenë ayden!")
    print("(Goodbye! Have a nice day!)")
elif anotherone == "n" or anotherone=="N":
    print("")
    print("Oodbyega! Aveha aen icenë ayden!")
    print("(Goodbye! Have a nice day!)")
else:
    while anotherone=="y" or anotherone=="Y":
        word = input("Please enter a word you would like to translate: ")

        #replaces k and c
        word = word.replace("k", "c")
        word = word.replace("K", "C")

        #do the conversions and manipulations here
        size = len(word)
        letters = ["a", "ë", "i", "o", "u"]
        wordlist = list(word)
        if wordlist[0].isupper():
            wordlist.append(wordlist[0].lower())
            wordlist.remove(wordlist[0])
            wordlist[0] = wordlist[0].upper()
        else:
            wordlist.append(wordlist[0])
            wordlist.remove(wordlist[0])
        if size >= 4:
            randnum = random.randrange(5)
            wordlist.append(letters[randnum])
        else:
            wordlist.append("e")
            wordlist.append("n")


        print("'" + word + "' in elvish is: " + ''.join(wordlist))

        print("")

        anotherone =input("Would you like to translate another word? (y/n): ")
        if anotherone != "y" and anotherone != "Y" and anotherone != "n" and anotherone != "N":
            print("You did not give a valid input.")
            print("")
            print("Oodbyega! Aveha aen icenë ayden!")
            print("(Goodbye! Have a nice day!)")
        elif anotherone == "n" and anotherone == "N":
            print("Oodbyega! Aveha aen icenë ayden!")
            print("(Goodbye! Have a nice day!)")


#Part 2	– Largest Number

print("")
#once user doesnt enter yes then while loop will break

while 1:
    #ask user for input
    print("Input an integer greater than or equal to 0 or -1 to quit: ")
    num = int(input("> "))
    #set first number to max
    max = num

    #compares number for max number
    while num >= 0 and num != -1:
        if num > max:
            max = num
        else:
            num = int(input("> "))

    print("The largest number is " + str(max))

    print("")
    #ask if user wants to find number again
    yesno = input("Would you like to find the largest number again? (y/n): ")
    if yesno == "n" or yesno=="N":
        print("")
        print("Goodbye!")
        break
    elif yesno == "Y" or yesno=="y":
        continue
    else:
        print("")
        print("Invalid input; Goodbye!")
        break


