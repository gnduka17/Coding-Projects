# Gloria Nduka
# ITP 115
# Assignment 9
# 3/30/2019
# nduka@usc.edu
# Description:
    #Music Library

import MusicLibraryHelper
import random

#this function converts everything in dictionary to all lowercase
def lowerDic(musicLibDictionary):

    lowerdictionary=dict((k.lower(), v) for k, v in musicLibDictionary.items())
    for key in lowerdictionary:
        alblist=[]
        word = key
        for name in lowerdictionary[key]:
            name = name.lower()
            alblist.append(name)
        del lowerdictionary[key]
        lowerdictionary[word]=alblist
    return lowerdictionary

#function displays menu
def displayMenu():
    print("Welcome to Your Music Library")
    print("Options:")
    print("\t1) Display library\n\t2) Display all artists\n\t3) Add an album\n\t4) Delete an album\n\t5) Delete an artist\n\t6) Search library\n\t7) Generate a random playlist\n\t8) Make your own playlist\n\t9) Exit")

#function displays the library in dictionary
def displayLibrary(musicLibDictionary):
    for key in musicLibDictionary:
        print("Artist: ", key)
        print("Albums:")
        for name in musicLibDictionary.get(key):
            print("\t-", name)

#display all the artists
def displayArtists(musicLibDictionary):
    print("Displaying all artists:")
    for artist in musicLibDictionary:
        print("-", artist)

#adds album to list of specified artist
def addAlbum(musicLibDictionary):
    artist = str(input("Enter artist: "))
    album = str(input("Enter album: "))
    artist=artist.lower()
    album=album.lower()

    if artist not in musicLibDictionary:
        listalb=[]
        listalb.append(album)
        musicLibDictionary[artist]=listalb
    else:
        if album not in musicLibDictionary[artist]:
            musicLibDictionary[artist].append(album)

#deletes an album
def deleteAlbum(musicLibDictionary):
    artist = input("Enter artist:")
    alb = str(input("Enter album:"))
    artist=artist.lower()
    alb=alb.lower()
    if artist in musicLibDictionary:
        if alb in musicLibDictionary[artist]:
            if len(musicLibDictionary[artist])==1:
                del musicLibDictionary[artist]
            else:
                musicLibDictionary[artist].remove(alb)
            return True
        else:
            return False
    else:
        return False
#function deletes an artist
def deleteArtist(musicLibDictionary):
    art=input("Enter artist to delete: ")
    art=art.lower()
    if art in musicLibDictionary:
        del musicLibDictionary[art]
        return True
    else:
        return False
#function searches for a word in the library
def searchLibrary(musicLibDictionary):
    count=0
    countalb = 0
    term = input("Please enter a search term: ")
    term=term.lower()
    print("Artists containing '"+ term+"'")
    #search term in artist list
    for name in musicLibDictionary:
        if term in name:
            print("-",name)
            count+=1
    if count==0:
        print("\tNo results")
    print("Albums containing '" + term+"'")
    #search term in album list
    for artist in musicLibDictionary:
        for songs in musicLibDictionary[artist]:
            if term in songs:
                print("-",songs )
                countalb+=1
    if countalb==0:
        print("\tNo results")

#forms random playlist
def generateRandomPlaylist(musicLibDictionary):
    print("Here is your random playlist:")
    for artist in musicLibDictionary:
        listalb = musicLibDictionary[artist]
        alb = random.choice(listalb)
        print("-", alb,"by",artist)

#user customizes playlist
def generateCustomPlaylist(musicLibDictionary):
    playlist = []
    options=["y","Y","n","N"]
    print("Your playlist so far:\n----")
    artistlist = list(musicLibDictionary.keys())
    total=len(artistlist)
    #output artists
    for i in range(total):
        word = artistlist[i]
        print(str(i)+")", word)
    option = int(input("Select an artist from the list by entering its number: "))
    #error checking
    while option not in range(total):
        print("*Error, number is not an option. Please try again")
        option = int(input("Select an artist from the list by entering its number: "))

    albumlist = musicLibDictionary[artistlist[option]]
    totalalb = len(albumlist)
    #outputs album
    for j in range(totalalb):
        word = albumlist[j]
        print(str(j)+")", word)
    choice = int(input("Select an album from the list by entering its number: "))
    #error checking
    while choice not in range(totalalb):
        print("*Error, number is not an option. Please try again")
        choice = int(input("Select an album from the list by entering its number: "))
        #adding to playlist list
    playlist.append(str(albumlist[choice])+" by " + str(artistlist[option]))
    cont = input("Would you like to continue building your playlist? (y/n) ")
    #error checking
    while cont not in options:
        print("Invalid option. Choose correct option (y/n)")
        cont = input("Would you like to continue building your playlist? (y/n) ")
        #until user enters 'n' then quit out of the loop
    while cont in options:
        if cont=="y" or cont=="Y":
            print("Your playlist so far:")
            for item in playlist:
                print("-", item)
            print("----")
            artistlist = list(musicLibDictionary.keys())
            total = len(artistlist)
            for i in range(total):
                print(str(i) + ")", artistlist[i])
            option = int(input("Select an artist from the list by entering its number: "))
            while option not in range(total):
                print("*Error, number is not an option. Please try again")
                option = int(input("Select an artist from the list by entering its number: "))
            albumlist = musicLibDictionary[artistlist[option]]
            print(albumlist)
            totalalb = len(albumlist)
            for j in range(totalalb):
                print(str(j) + ")", albumlist[j])
            choice = int(input("Select an album from the list by entering its number: "))
            while choice not in range(totalalb):
                print("*Error, number is not an option. Please try again")
                choice = int(input("Select an album from the list by entering its number: "))
            playlist.append(str(albumlist[choice]) + " by " + str(artistlist[option]))
            cont = input("Would you like to continue building your playlist? (y/n) ")
            while cont not in options:
                print("Invalid option. Choose correct option (y/n)")
                cont = input("Would you like to continue building your playlist? (y/n) ")
        else:
            print("Your completed playlist:")
            for item in playlist:
                print("-", item)
            break

def main():
    dictionary=MusicLibraryHelper.loadLibrary("musicLibrary.dat")
    #converts item in dictionary lowercase
    dictionary=lowerDic(dictionary)
    nums=[1,2,3,4,5,6,7,8,9]
    displayMenu()
    number = int(input("> "))
    #error checking
    while number not in nums:
        print("Invalid option. Please choose a correct option")
        number = int(input("> "))
        #for each number call the designated function and also error check
    while number in nums:
        if number==1:
            displayLibrary(dictionary)
            print("")
            displayMenu()
            number = int(input("> "))
            while number not in nums:
                print("Invalid option. Please choose a correct option")
                number = int(input("> "))
        elif number==2:
            displayArtists(dictionary)
            print("")
            displayMenu()
            number = int(input("> "))
            while number not in nums:
                print("Invalid option. Please choose a correct option")
                number = int(input("> "))
        elif number==3:
            addAlbum(dictionary)
            print("")
            displayMenu()
            number = int(input("> "))
            while number not in nums:
                print("Invalid option. Please choose a correct option")
                number = int(input("> "))
        elif number==4:
            result = deleteAlbum(dictionary)
            print("")
            if result == False:
                print("Delete album failed.")
            else:
                print("Delete album success!")
                print("")
            displayMenu()
            number = int(input("> "))
            while number not in nums:
                print("Invalid option. Please choose a correct option")
                number = int(input("> "))
        elif number==5:
            resultart = deleteArtist(dictionary)
            print("")
            if resultart == False:
                print("Delete artist failed.")
            else:
                print("Delete artist success!")
                print("")
            displayMenu()
            number = int(input("> "))
            while number not in nums:
                print("Invalid option. Please choose a correct option")
                number = int(input("> "))
        elif number==6:
            searchLibrary(dictionary)
            print("")
            displayMenu()
            number = int(input("> "))
            while number not in nums:
                print("Invalid option. Please choose a correct option")
                number = int(input("> "))
        elif number==7:
            generateRandomPlaylist(dictionary)
            print("")
            displayMenu()
            number = int(input("> "))
            while number not in nums:
                print("Invalid option. Please choose a correct option")
                number = int(input("> "))
        elif number==8:
            generateCustomPlaylist(dictionary)
            print("")
            displayMenu()
            number = int(input("> "))
            while number not in nums:
                print("Invalid option. Please choose a correct option")
                number = int(input("> "))
        elif number==9:
            MusicLibraryHelper.saveLibrary("musicLibrary.dat",dictionary)
            print("Saving music library...\nGoodbye!")
            break



main()