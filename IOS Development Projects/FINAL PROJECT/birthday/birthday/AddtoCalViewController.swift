//
//  AddtoCalViewController.swift
//  birthday
//
//  Created by Gloria Nduka on 12/4/19.
//  Copyright © 2019 Gloria Nduka. All rights reserved.
//

import UIKit
import EventKit

class AddtoCalViewController: UIViewController {
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var birthdateLabel: UILabel!
    let datePick = AddBdayViewController()
    //usong unicode string values to obtain info from both table views
    var getPersonName = String()
    var getPersonBDay = String()
    var getLongBday = String()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        nameLabel.text = getPersonName
        birthdateLabel.text = getPersonBDay
    }
    
    
    
    @IBAction func addToCalenderPressed(_ sender: Any) {
        let dateformat = DateFormatter()
        dateformat.dateFormat = "MMMM dd, yyyy"
        dateformat.timeZone = TimeZone(abbreviation: "PST")
        guard let dateFrString = dateformat.date(from: getLongBday) else{
            return
        }
        let eventStore:EKEventStore = EKEventStore()
        eventStore.requestAccess(to: .event, completion: {(granted, error) in
            if(granted) && (error==nil){
                let event:EKEvent = EKEvent(eventStore: eventStore)
                DispatchQueue.main.async {
                    event.title = "\(self.nameLabel.text ?? "")'s Birthday Today!!"
                    event.startDate = dateFrString
                    event.endDate = dateFrString
                    event.notes = "Wish \(self.nameLabel.text ?? "") a happy birthday before you forget!"
                    event.isAllDay = true
                    event.calendar = eventStore.defaultCalendarForNewEvents
                    
                    do{
                        try eventStore.save(event, span: .futureEvents)
                    }catch let error as NSError{
                        print("There is an Error: \(error)")
                        
                    }
                    print("Event is saved")
                }
                
            }
            else{
                print("error")
            }
            
        })
        
    }
    
  
    
}
