//
//  Birthday.swift
//  birthday
//
//  Created by Gloria Nduka on 12/4/19.
//  Copyright © 2019 Gloria Nduka. All rights reserved.
//

import Foundation
struct Birthday:Equatable{
    //Birthday class
    public var name:String
    public var date:String
    public var userKey:String
    func getName()->String{
        return name
    }
    //returns long form of date MMMM dd, yyyy
    func getDate()->String{
        return date
    }
    func getUserKey()->String{
        return userKey
    }
    //returns the short form of date MMMM dd
    func getShortDate()->String{
        var hold = date
        let range = hold.index(hold.endIndex, offsetBy: -6)..<hold.endIndex
        hold.removeSubrange(range)
        return hold
    }
    mutating func setUserKey(_userkey userID: String){
        self.userKey = userID
    }
    init(name: String, date: String, userKey: String){
        self.name = name
        self.date = date
        self.userKey = userKey
    }
}
