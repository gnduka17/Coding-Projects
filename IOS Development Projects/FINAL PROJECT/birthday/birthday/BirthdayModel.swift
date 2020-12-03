//
//  BirthdayModel.swift
//  birthday
//
//  Created by Gloria Nduka on 12/4/19.
//  Copyright © 2019 Gloria Nduka. All rights reserved.
//

import Foundation
import GoogleSignIn

class BirthdayModel: NSObject{
    public static let shared = BirthdayModel()
    public var birthdays:[Birthday]
    let kFileName = "data.plist"
    let kNameKey = "personName"
    let kDateKey = "birthdate"
    let kUserIdKey = "UserID"
    var userId:String = ""
    var filepath = ""
    
    override init(){
        //initializing list of birthdays from plist storage
        birthdays = [Birthday]()
        let filemanager = FileManager.default
        let url = filemanager.urls(for: .documentDirectory, in: .userDomainMask).first
        guard let filep = url?.appendingPathComponent(kFileName).path else {
            super.init()
            return
        }
        filepath = filep
        print(filepath)
        if filemanager.fileExists(atPath: filepath){
            let cardsholder = NSArray(contentsOfFile: filepath)
            if let birthdays1 = cardsholder{
                for dict in birthdays1{
                    let cardinit = dict as! [String:String]
                    let card = Birthday(name: cardinit[kNameKey]!, date: cardinit[kDateKey]!, userKey: cardinit[kUserIdKey]!)
                    birthdays.append(card)
                }
            }
        }
        super.init()
    }
    func numberOfBirthdays() ->Int{
        return birthdays.count
    }
    //func returns number of birthdays for specific user
    func numberOfBirthdaysUser(_ userID:String) -> Int{
        var count = 0
        for item in birthdays{
            if item.getUserKey() == userID{
                count = count + 1
            }
        }
        return count
    }
    //function to insert birthday into model
    func insertBirthday(name: String, date: String, userID: String, at index: Int){
        let card = Birthday(name:name, date:date, userKey: userID)
        if index>=0 && index<=birthdays.count{
            if index == birthdays.count{
                birthdays.append(card)
            }else{
                birthdays.insert(card, at: index)
            }
        }
        //save it to plist
       save()
    }
    //removes bday from model of user
    func removeBdayName(_ userID: String, _ personName: String){
        let count = birthdays.count
        for i in 0..<count{
            if birthdays[i].getName() == personName && birthdays[i].getUserKey() == userID{
                birthdays.remove(at: i)
                break 
            }
        }
        save()
    }
    //function to check if birthday exist for that name
    func checkPersonExist(personName: String, userID:String )->Bool{
        for card in birthdays{
            if card.getName().lowercased() == personName.lowercased() && card.getUserKey().lowercased()==userID.lowercased(){
                return true
            }
        }
        return false
    }
    //save to file function
    func save(){
        var dictArr = [[String:String]]()
        for card in birthdays{
            let holder = [kNameKey: card.getName(), kDateKey: card.getDate(), kUserIdKey: card.getUserKey()]
            dictArr.append(holder)
        }
        (dictArr as NSArray).write(toFile:filepath, atomically: true)
        
    }
}



