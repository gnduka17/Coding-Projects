# Gloria Nduka
# ITP 115
# Assignment 2
# 1/25/2019
# Description:
# Part 1 stimulates a vending machine and outputting the change
# Part 2 is a story based on user input


#PART 1 HARRY POTTER VENDING MACHINE

#display options
print("Please select an item from the vending machine: ")
print("\t a) Butterbeer: 58 knuts")
print("\t b) Quill: 10 knuts")
print("\t c) The Daily Prophet: 7 knuts")
print("\t d) Book of Spells: 400 knuts")

#retrieve user input
option = input("> ")

#if enter invalid, send message
if option != 'a' and option != 'b' and option != 'c' and option != 'd' and option != 'A' \
        and option != 'B' and option != 'C' and option != 'D':
    print("You have entered an invalid option. You will be given a Quill for 10 knuts.\n")

#Instagram discounted options
instagram = input("Will you share this on Instagram? (y/n): ")
if instagram=='y' or instagram =='Y':
    print("Thanks! You get 5 knuts off your purchase\n")
    coup = "(with coupon of 5 knuts)"
    numcoup = 5
elif instagram=='n' or instagram =='N':
    print("  ")
    coup = "(with coupon of 0 knuts)"
    numcoup=0
else:
    print("You have entered an invalid option. No coupon will be used\n")
    coup = "(with coupon of 0 knuts)"
    numcoup=0

#output what user has chosen including discount
if option =='a' or option=='A':
    print("You bought a Butterbeer for 58 knuts",coup, "and paid with one galleon.")
    price = 58
elif option =='b' or option=='B':
    print("You bought a Quill for 10 knuts", coup, "and paid with one galleon.")
    price = 10
elif option =='c' or option=='C':
    print("You bought The Daily Prophet for 7 knuts", coup, "and paid with one galleon.")
    price = 7
elif option =='d' or option=='D':
    print("You bought the Book of Spells for 400 knuts", coup, "and paid with one galleon.")
    price = 400
else:
    print("You bought a Quill for 10 knuts", coup, "and paid with one galleon.")
    price = 10

#calculations of change
change = 493-(price-numcoup)
sick = change//29
knut = change % 29

#output results
print("Here is your change (" + str(change)+ " knuts):" )
print("Sickles:", sick)
print("Knuts:", knut)




#PART 2 CHOOSE YOUR OWN ADVENTURE
print("\nWelcome to a choose your own adventure game.\n")

print("The month of August has finally arrived and school has started. The sun's out; your mother calls you down for\n"
      "breakfast; music is blasting in your room. You say \"Today's gonna be a great day.\" You finish getting\n"
      "dressed and go downstairs. Your mom hands you your breakfast and asks you, \"How are you getting to school?\"")

#output options
print("Do you choose:")
print("\t a) Mom Drives")
print("\t b) School Bus")
print("\t c) Airplane")

transport = input(">")

#share story given user input
if transport=='a' or transport=='A':
    print("You tell her that she is the one taking you to school. She happily accepts it then grabs her purse and\n"
          "keys. You grab your backpack and tie your shoes. You guys get in the car and are off to school.\n"
          "The drive is quite long so you take a 30 minute nap in the back of the car. You have finally arrived at\n"
          "school and you see your best friends, your teachers, and your crush.\n")

elif transport=='b' or transport=='B':
    print("You tell her that you want to take the school bus. She kisses you on the forehead and\n"
          "tells you to get outside before you miss the bus. You quickly tie your shoes and grab your backpack\n"
          "then head out the door. When the bus arrives, dirty water is splashed all over your nice first\n"
          "day of school outfit. You scream and shout because there is no way for you to go back to ur house\n"
          "and change or you'll be very late to school. You quickly get on the bus and put your jacket on. You\n"
          "finally arrived at school and you see your best friends, your teachers, and your crush.")
elif transport=='c' or transport=='C':
    print("You tell her that you want to take our family plane then she laughs. She calls me crazy because of\n"
          "the stupid idea I just shared. She then tells me that I am carpooling with my neighbor. I grab my bag\n"
          "and tie my shoes then kiss my mom. I head out and walk to my neighbors home. I say hey then we\n"
          "are off to school. We finally arrive and I see my best friends, my teachers, and my crush.")

else:
    print("You did not choose a valid option; an option will be picked for you: A \n")
    print("You tell her that she is the one taking you to school. She happily accepts it then grabs her purse and\n"
      "keys. You grab your backpack and tie your shoes. You guys get in the car and are off to school.\n"
      "The drive is quite long so you take a 30 minute nap in the back of the car. You have finally arrived at\n"
      "school and you see your best friends, your teachers, and your crush.\n")

#output options
person = input("Do you walk towards:\n a) Your Best Friends \n b) Your Teachers \n c) Your Crush\n> ")

#share story given user input
if person=='a' or person=='A':
    print("You run towards your friends and give them the biggest hugs. You are extremely happy to see them given\n"
          "the fact that you haven't seen them since last school year. They all give you gifts because your birthday\n"
          "was during the summer and they couldn't see you. The school bell rings and you all\n"
          "go inside to have your first day of school.\n")

elif person=='b' or person=='B':
    print("You walk towards your teachers and give them each back to school presents. They ask you how your break\n"
          "was and you tell them all about your birthday trip in Hawaii. Your favorite teacher got married\n"
          "and showed you pictures of her wedding and her dress. The school bell rings and you all go inside\n"
          "to start the school year.")
elif person=='c' or person=='C':
    print("You awkwardly walk towards your crush and say hey. As you guys are talking, his girlfriend swoops in\n"
          "between y'all and kiss him right in front of you. You don't know what to do, so you just stare at them\n"
          "while your friends feel so bad for you. His girlfriend turns around and asks who you are then you\n"
          "get so red and embarrassed. The school bell rings then you rush into the school and start\n"
          "crying because of how awkward of a situation that was.")

else:
    print("You did not choose a valid option; an option will be picked for you: A \n")
    print("You run towards your friends and give them the biggest hugs. You are extremely happy to see them given\n"
      "the fact that you haven't seen them since last school year. They all give you gifts because your birthday\n"
      "was during the summer and they couldn't see you. The school bell rings and you all\n"
      "go inside to have your first day of school.\n")

print("\nTHE END")

