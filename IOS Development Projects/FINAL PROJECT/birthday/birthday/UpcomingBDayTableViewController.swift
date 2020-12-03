//
//  UpcomingBDayTableViewController.swift
//  birthday
//
//  Created by Gloria Nduka on 12/4/19.
//  Copyright © 2019 Gloria Nduka. All rights reserved.
//

import UIKit
import GoogleSignIn

class UpcomingBDayTableViewController: UITableViewController {
    let model = BirthdayModel.shared
    var kUserID = GIDSignIn.sharedInstance().currentUser.profile.email ?? ""
    var holderUpcoming:[Birthday] = []

    override func viewDidLoad() {
        var todaysDateStr = ""
        var next10Str = ""
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "MMMM dd"
        dateFormatter.timeZone = TimeZone(abbreviation: "PST")
         let date = Date()
        let calendar = Calendar.current
        let next10Bdays = calendar.date(byAdding: .day, value: 10, to: date)
        //get current date and convert to MMMM, dd, yyyy
      
        todaysDateStr = dateFormatter.string(from: date)
       
        // get next 10 date and convert it to string MMMM, dd, yyyy
        next10Str = dateFormatter.string(from: next10Bdays!)
       
        
        //create 2 date vraiables - today adn next 10 days
        let firstDate = dateFormatter.date(from: todaysDateStr)
     
        let secDate = dateFormatter.date(from: next10Str)
       
        //iterate through array
        for bdays in model.birthdays{
            if bdays.getUserKey() == kUserID{
                let midDate = dateFormatter.date(from: bdays.getShortDate())
                if midDate?.compare(firstDate!) == .orderedDescending {
                    
                    if midDate?.compare(secDate!) == .orderedAscending {
                       
                        holderUpcoming.append(bdays)
                       
                        
                    }}
            }
        }
        holderUpcoming = holderUpcoming.sorted { $0.date.lowercased() < $1.date.lowercased() }
                super.viewDidLoad()
        }

override func viewWillAppear(_ animated:Bool) {
    super.viewWillAppear(animated)
    holderUpcoming = []
    var todaysDateStr = ""
    var next10Str = ""
    let dateFormatter = DateFormatter()
    dateFormatter.dateFormat = "MMMM dd"
    dateFormatter.timeZone = TimeZone(abbreviation: "PST")
    let date = Date()
    let calendar = Calendar.current
    let next10Bdays = calendar.date(byAdding: .day, value: 10, to: date)
    //get current date and convert to MMMM, dd, yyyy
    
    todaysDateStr = dateFormatter.string(from: date)
   
    // get next 10 date and convert it to string MMMM, dd, yyyy
    next10Str = dateFormatter.string(from: next10Bdays!)
   
    
    //create 2 date vraiables - today adn next 10 days
    let firstDate = dateFormatter.date(from: todaysDateStr)
  
    let secDate = dateFormatter.date(from: next10Str)

    //iterate through array
    for bdays in model.birthdays{
        if bdays.getUserKey() == kUserID{
            let midDate = dateFormatter.date(from: bdays.getShortDate())
            //first: if middate is greater than todays date, second: if middate is equal to first and second date, third, if middate is less than sec date
            if midDate?.compare(firstDate!) == .orderedDescending {
               
                if midDate?.compare(secDate!) == .orderedAscending {
                holderUpcoming.append(bdays)
                   
            
        }}
        }
    }
     holderUpcoming = holderUpcoming.sorted { $0.date.lowercased() < $1.date.lowercased() }
    tableView.reloadData()
}


    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        
        return holderUpcoming.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "tableCell2", for: indexPath)
        
        if let label = cell.textLabel {
            // if let birthday = holder[indexPath.row]{
            //if let birthday = model.birthday(at: indexPath.row){
            label.text =  holderUpcoming[indexPath.row].getName()
            if let subtitle = cell.detailTextLabel {
                subtitle.text =  holderUpcoming[indexPath.row].getShortDate()
            }
            // }
        }
        return cell
    }
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let Storyboard = UIStoryboard(name: "Main", bundle: nil)
        let dvc = Storyboard.instantiateViewController(withIdentifier: "AddtoCalViewController") as! AddtoCalViewController
        dvc.getPersonName = holderUpcoming[indexPath.row].getName()
        dvc.getPersonBDay = holderUpcoming[indexPath.row].getShortDate()
        dvc.getLongBday = holderUpcoming[indexPath.row].getDate()
        self.navigationController?.pushViewController(dvc, animated: true)
    }
}

