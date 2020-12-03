# Gloria Nduka
# ITP 115, Spring 2019
# Assignment 1
# nduka@usc.edu


# Asking for user input
adverb = input("Enter an adverb: ")
verb = input("Enter a past tense verb: ")
store = input("Enter a grocery store: ")
item = input("Enter an item: ")
name = input("Enter a name: ")
adj1 = input("Enter an adjective: ")
adj2 = input("Enter another adjective: ")
num1 = int(input("Enter a number: "))
num2 = int(input("Enter a number: "))
num3 = int(input("Enter a number: "))
float1 = float(input("Enter a decimal number: "))
bigfloat = float(input("Enter a bigger decimal number: "))
location = input("Enter a location: ")
event = input("Enter a type of event: ")
currency = input("Enter a currency type: ")
emotion = input("Enter an emotion: ")
interjection = input("Enter an interjection: ")
bignum = num1 + num2




#forming message with given information
message = "I \"" + adverb + "\" \"" + verb + "\" to \"" + store + "\" to buy \"" + item + "\" with \"" + name +"\". \n"
message += "My \"" + adj1 + "\" cart was filled with \"" + str(num1) + "\" items.\nWe were thinking of having a \"" + adj2 + "\" \""
message += event + "\" at \"" + location + "\".\nThe total price of the items came to be \"" + str(float1) + "\" \"" + currency + "\".\n\""
message += name + "\" forgot to put \"" + str(num2) + "\" items in our cart, so I felt \"" + emotion + "\" and screamed, \""
message += interjection + "!\", \nand had to go and get those missing items and put them in the cart.\n"
message += "Now we have \"" + str(bignum) + "\" items for the \"" + event + "\". \nAfter we purchased everything it came out to be \""
message += str(bigfloat) + "\" \"" + currency + "\". \nWe ended up taking the \"" + str(num3) + "\" bags we used to our cars and got ready for the \"" + event + "\"."

#print message
print(message)





