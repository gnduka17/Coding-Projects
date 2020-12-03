//
//  AllBDayTableViewController.swift
//  birthday
//
//  Created by Gloria Nduka on 12/4/19.
//  Copyright © 2019 Gloria Nduka. All rights reserved.
//

import UIKit
import GoogleSignIn
// delegation design pattern - Table View
class AllBDayTableViewController: UITableViewController {
    //singleton used
    let model = BirthdayModel.shared
    //obtain signed in users email
    var kUserID = GIDSignIn.sharedInstance().currentUser.profile.email ?? ""
    //array to hold birthdays of user
    var holder:[Birthday] = []
    var purpose = ""
    
    @IBOutlet weak var signOutPressed: UIBarButtonItem!
    
    override func viewDidLoad() {
        holder = [Birthday]()
        //fill holder with users bdays
        for bdays in model.birthdays{
            if bdays.getUserKey() == kUserID{
                holder.append(bdays)
            }
        }
        //sorts alphabetically by name
       holder = holder.sorted { $0.name.lowercased() < $1.name.lowercased() }
        super.viewDidLoad()
    }
    override func viewWillAppear(_ animated:Bool) {
        super.viewWillAppear(animated)
        //empty holder array to prevent double entries
        holder = []
         //fill holder with users bdays 
        for bdays in model.birthdays{
            if bdays.getUserKey() == kUserID{
                holder.append(bdays)
            }
        }
        //sorts alphabetically by name
        holder = holder.sorted { $0.name.lowercased() < $1.name.lowercased() }
        tableView.reloadData()
    }
    //sign out user
    @IBAction func signOutPressed(_ sender: AnyObject) {
         GIDSignIn.sharedInstance().signOut()
    }
    
    
    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return model.numberOfBirthdaysUser(kUserID)
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "tableCell", for: indexPath)
        //populate cell
        if let label = cell.textLabel {
                label.text =  holder[indexPath.row].getName()
                if let subtitle = cell.detailTextLabel {
                    subtitle.text =  holder[indexPath.row].getShortDate()
                }
        }
        return cell
    }
    //editing styles for table view
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            //delete from model given userID and name of bday person
            model.removeBdayName(holder[indexPath.row].getUserKey(), holder[indexPath.row].getName())
            holder.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            
        }
    }
    //directs to AddtoCal/details view controller of tapped cell
    //Tells the delegate that the specified row is now selected.
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let Storyboard = UIStoryboard(name: "Main", bundle: nil)
        let dvc = Storyboard.instantiateViewController(withIdentifier: "AddtoCalViewController") as! AddtoCalViewController
        dvc.getPersonName = holder[indexPath.row].getName()
        dvc.getPersonBDay = holder[indexPath.row].getShortDate()
        dvc.getLongBday = holder[indexPath.row].getDate()
        self.navigationController?.pushViewController(dvc, animated: true)
    }

}
