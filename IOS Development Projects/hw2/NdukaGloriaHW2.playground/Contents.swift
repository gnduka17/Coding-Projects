import Foundation 
/// ITP-342 Fall 2019 Homework 2
/// Strings, Functions, and Optionals

// ************
// Follow the instructions in the comments to complete the 13 coding problems. You can simply write your code in the Swift Playground we've provided. Rename the file in the format LastFirstHW2.playground, then zip the file in the format LastFirstHW2.zip and submit on Blackboard.
// ************

/// 1.
/// Format the below into a Swift String, taking care to keep the exact formatting, inclduing quotation marks and tabs! You can ignore the slashes for the comment, though.
/// Hint: use the escape character for tab, new-line, and quotation marks.
//    "Hello, this is a
// multiline-quote."

// Uncomment below to finish the line of code and print!
let multiLineQuote = "\t\"Hello, this is a\nmultiline-quote.\""
print(multiLineQuote)

/// 2.
/// Mutate the below string to read "Hello!" instead of "Hello."
/// Hint: don't re-assign the value of the String directly, but use the insert and remove methods to mutate in place.
var message = "Hello." 
// Your String mutation code here
message.remove(at: message.index(before: message.endIndex))
message.insert("!", at: message.endIndex)
print(message)

/// 3.
/// Use the priceWithSalesTax variable to format the price tag string like this: "Total price: $XXX.XX" (with just 2 decimal places)
let priceWithSalesTax = 124.99 * 1.095
 //Uncomment me!
let priceTag = String(format: "$%6.2f",priceWithSalesTax)
  print(priceTag)


/// 4.
/// The below code combines names and dates of birth and prints them. There's a lot of repeated logic! Using your best judgement, refactor these operations into a function.
/// Hint: consider designing a function that takes in a name and date of birth as String parameters
var p1 = "Wanda Austin"
var d1 = "1/1/1954"
var formatted1 = p1 + " " + d1
print(formatted1)

var p2 = "Tommy Trojan"
var d2 = "2/24/1912"
var formatted2 = p2 + " " + d2
print(formatted2)

var p3 = "Traveler IX"
var d3 = "12/1/2017"
var formatted3 = p3 + " " + d3
print(formatted3)
// Your function and function calls to produce the same output as above here
func nameDate(name:String, date: String){
    print(name + " " + date)
}
nameDate(name:"Wanda Austin", date: "1/1/1954")
nameDate(name:"Tommy Trojan", date: "2/24/1912")
nameDate(name:"Traveler", date: "12/1/2017")



/// 5.
/// Write a function that returns the sum of two Ints and that can be called like this: let x = add(1, 2)
// Your function here
func add(_ num1: Int,_ num2: Int) ->Int{
    return num1+num2
}


/// 6.
/// Write a function that prints the sum of two Ints, does not return the sum, and can be called like this: printSum(a: 1, b: 2)
// Your function here
func printSum(a: Int, b: Int){
    print(a+b)
}



/// 7.
/// Write a function that returns a formatted String including age someone is turning this year given the year they were born.
/// The function should be able to be called as follows. It should also use the parameter name "year" and arguement label provided ("bornIn").
/// Example function call:
/// hello("John", bornIn: 1995) // Returns: "John is turning 24 in 2019"
// Your function here
func hello(_ name: String, bornIn year: Int) -> String{
    let x = 2019 - year
    return name + " is turning " + String(x) + " in 2019"
}


/// 8.
/// Write a function that takes in a Float score representing a grade percentage and returns a String describing the grade. Handle bad input appropriately.
/// Example:
/// grade(95) returns "A"
/// grade(70.2) returns "C-"
/// grade(-50) returns "Invalid entry"
// Your function here
func grade(_ grad: Float) ->String{
    if grad < 0 || grad > 100{
        return "Invalid entry"
    }
    else if grad >= 93{
        return "A"
    }
    else if grad >= 90 && grad < 93{
        return "A-"
    }
    else if grad >= 83{
        return "B"
    }
    else if grad >= 80 && grad < 83{
        return "B-"
    }
    else if grad >= 73{
        return "C"
    }
    else if grad >= 70 && grad < 73{
        return "C-"
    }
    else if grad >= 63{
        return "D"
    }
    else if grad >= 60 && grad < 63{
        return "D-"
    }
    return "F"
}


/// 9.
/// Write a function below that allow both of the function calls below compile without any changes. The function doesn't have to actually do anything.
/// Hint: Take a look at the function slides 12-17 for default argument values.
// Write your function here
func foo(_ parameter1: Int,_ parameter2: Int = 3){
}

// Uncomment me to make sure your function works!
  foo(1, 2)
  foo(1)


/// 10.
/// Write a function that takes in a String called word and a Character called letter, and returns an integer reprsenting the number of times letter appears in word. Then, call the function with some sample input and print it. Add a comment next to your sample input noting what the expected output is.
/// Hint: Use a for loop to iterate over word.indices
// Your code here
func numOfChar(_ word: String,_ letter: Character) -> Int{
    var counter = 0
    for char in word{
        if char==letter{
            counter+=1
        }
    }
    return counter
}
var x = numOfChar("apple", "p") //2
print(x)

var y = numOfChar("Mississippi", "s") //4
print(y)

var z = numOfChar("dragonfly", "h") // 0
print(z)




/// 11.
/// Write a function using the inout keyword that takes in an Int and increments it by 1. Call your function to show it works.
// Your code here
func increment1(_ a: inout Int){
    a=a+1
}
var r = 4
increment1(&r)
print("r is now " + String(r))

/// 12.
/// Safely unwrap the optional variable below, taking care to avoid crashing. If the optional has a value, print it. If it doesn't, print "No value". You can use any method you like to unwrap the optional.
var maybeHasAValue: String?
// Your code here
if let value = maybeHasAValue{
    print(value)
}
else{
    print("No Value")
}


/// 13.
/// Suppose you are working at a highly dysfunctional snack bar. You sell water, soda, cookies, and chips. Customers can order any of these items by calling the corresopnding "buy" function (e.g. buyWater() or buyCookies()).
/// When a customer orders something, you can try to get the item from the back by calling the item's corresponding private function (water(), soda(), cookies(), and chips()). Those functions return the item, but since the snack bar is so busy, sometimes you run out and it returns nil instead, meaning you can't fulfill the order!
/// The snack bar also sells 2 combos. You know when customers order the drink and chips combo (by calling buyDrinkAndChipsCombo()) that they prefer soda, but if you're out of soda, they'll be happy with water. But if you don't have both soda and water, or you don't have chips, you can't fulfill the order. Lastly, customers can order one of everything on the menu, but this is all or nothing--if you can't fulfill at least one of the items, you can't fulfill the order at all.
/// Because the snack bar is dysfunctional, you run out of things constantly and frequently, but instead of providing the customer with nothing, as the "back of house" functions might by returning an optional, we have to tell the customer in words.

/// ***YOUR JOB***:
/// Implement the 6 function stubs below without changing the function declaration. Again, note that the functions you are writing return non-optional Strings whereas your "inventory" functions already provided return optionals, since you might not have an item in stock.
/// For orders of just one item, try to get the item. If you can safely unwrap it, return it for the customer. If not, return a message saying that you're out of that item.
/// For the drink and chips combo, try to get a soda. If you can't get a soda, try to get a water. If you can't get either, tell the customer that you're out of an ingredient. Similarly, if you can get either a soda or a water, but can't get chips, tell the customer you're out of an ingredient. Finally, if you can provide a water or soda AND chips, return the order to the customer in the form "Soda + Chips" or "Water + Chips". You should only give the customer a water if you're out of soda.
/// For the one of everything combo, try to get one of each item. If any of the items are out of stock, tell the customer accordingly. Otherwise, return to the customer their order in the format above, with each item name separated by " + ".
/// For multi-item orders, you can (but are not required to) say the specific item that is out of stock. It is also acceptable to have one error message for ANY of the items being out of stock.
/// When you are unwrapping optionals, be sure to use at least 1 guard let and if let statement as well as the nil coalescing operator (??)

/// Example input/output:
/// SnackBar.buyWater() ➡️ returns either "Water" or "Sorry, we're out of water."
/// SnackBar.buyDrinkAndChipsCombo() ➡️ returns either "Soda + Chips", "Water + Chips", or "Sorry, we're out of something"

class SnackBar {
    // Implement the next 6 functions. Note that the included return statements are just so that the playground will compile before you get to this problem--you'll want to replace them.
    static func buyWater() -> String {
        var waterholder: String? = water()
        if let val = waterholder{
            return "Water"
        }
        return "Sorry, we're out of water."
    }
    
    static func buyCookies() -> String {
        var cookiesHolder: String? = cookies()
        if let val = cookiesHolder{
            return "Cookies"
        }
        return "Sorry, we're out of cookies."
    }
    
    static func buySoda() -> String {
        var sodaHolder: String? = soda()
        guard let val = sodaHolder else{
            return "Sorry, we're out of soda."
        }
        return "Soda"
    }
    
    static func buyChips() -> String {
        let check = chips() ?? "Sorry, we're out of chips."
        return "Chips"
    }
    
    static func buyDrinkAndChipsCombo() -> String {
        if let val = chips(){
            if let val2 = soda(){
                return "Soda + Chips"
            }
            else if let val3 = water(){
                return "Water + Chips"
            }
            return "Sorry, we're out of something"
        }
        return "Sorry, we're out of chips"
    }
    
    static func buyOneOfEverything() -> String {
        guard let val = chips(), let val2 = soda(), let val3 = water(), let val4 = cookies() else{
            return "Sorry we're out of something"
        }
       return "Soda + Chips + Water + Cookies"
    }
    
    // Call these functions when trying to fulfill orders. Don't change this code!
    private static func water() -> String? {
        return Bool.random() ? nil : "Water"
    }
    private static func soda() -> String? {
        return Bool.random() ? nil : "Soda"
    }
    private static func cookies() -> String? {
        return Bool.random() ? nil : "Cookies"
    }
    private static func chips() -> String? {
        return Bool.random() ? nil : "Chips"
    }
}

