//
//  AddBdayViewController.swift
//  birthday
//
//  Created by Gloria Nduka on 12/4/19.
//  Copyright © 2019 Gloria Nduka. All rights reserved.
//

import UIKit
import ContactsUI
import GoogleSignIn

//Add A Birthday View Controller

class AddBdayViewController: UIViewController, CNContactPickerDelegate, UITextFieldDelegate{
    
    @IBOutlet weak var inputName: UITextField!
    @IBOutlet weak var chooseContacts: UIButton!
    @IBOutlet weak var addBirthday: UIButton!
    @IBOutlet weak var birthdateTextField: UITextField!
    //UI DATE Picker
    let datePick = UIDatePicker()
    //user signin username
    var kUserID = GIDSignIn.sharedInstance().currentUser.profile.email ?? ""
    //using singleton to access birthday model and modify it
    let birthdayModel = BirthdayModel.shared
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //establish date picker
        birthdateTextField.inputView = datePick
        datePick.datePickerMode = .date
        let toolbar = UIToolbar()
        toolbar.sizeToFit()
        let doneButton = UIBarButtonItem(barButtonSystemItem: .done, target: self, action: #selector(doneAction))
        let flexSpace = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
        toolbar.setItems([flexSpace,doneButton], animated: true)
        birthdateTextField.inputAccessoryView = toolbar
        //delegate to respond to the text entered by the user and to some special commands, such as when Return is tapped.
        inputName.delegate = self
    }
    //presents date in format when done is clicked
    @objc func doneAction(){
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "MMMM dd, YYYY"
        birthdateTextField.text = dateFormatter.string(from: datePick.date)
        view.endEditing(true)
    }
    //keyboard dismisses when tapped and return key is pressed
    @IBAction func keyboardLeave(_ sender: UITapGestureRecognizer) {
        inputName.resignFirstResponder()
    }
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        if textField == inputName {
            inputName.resignFirstResponder()
        }
        return true
    }
    
    @IBAction func addBirthdayPressed(_ sender: UIButton) {
        let name = inputName.text ?? ""
        let birthdate = birthdateTextField.text ?? ""
        //if name already enetered for specific user return alert
        if  birthdayModel.checkPersonExist(personName: name, userID: kUserID){
            let alert = UIAlertController(title: "Warning!", message: "The name you have entered already exists. Please enter a new person. ", preferredStyle: .alert)
            let okAction = UIAlertAction(title: "OK", style: .default, handler: okButtonTapped)
            alert.addAction(okAction)
            self.present(alert, animated: true, completion: nil)
        }
        else{
            birthdayModel.insertBirthday(name: name, date: birthdate, userID: kUserID,at: birthdayModel.numberOfBirthdays())
            // close the view controller
            dismiss(animated: true, completion: nil)
        }
    }
    
    func okButtonTapped(action: UIAlertAction) {
        inputName.text = ""
        birthdateTextField.text = ""
    }
    
    @IBAction func cancelPressed(_ sender: UIBarButtonItem) {
        inputName.text=""
        birthdateTextField.text = ""
        self.dismiss(animated: true)
    }
    //if choose from contacts button is pressed access contacts to choose
    @IBAction func chooseContactsPressed(_ sender: UIButton) {
        let picker = CNContactPickerViewController()
        picker.delegate = self
        present(picker, animated: true, completion: nil)
        
    }
    func contactPicker(_ picker: CNContactPickerViewController, didSelect contact: CNContact) {
        inputName.text = contact.givenName + " " + contact.familyName
    }
    

}
