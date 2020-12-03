//
//  AddViewController.swift
//  NdukaGloriaHW6
//
//  Created by Gloria Nduka on 11/14/19.
//  Copyright © 2019 Gloria Nduka. All rights reserved.
//

import UIKit

class AddViewController: UIViewController, UITextFieldDelegate, UITextViewDelegate {
    // IBOutlets
    @IBOutlet weak var inputAnswer: UITextField!
    @IBOutlet weak var inputQuestion: UITextView!
    @IBOutlet weak var saveButton: UIBarButtonItem!
    @IBOutlet weak var cancelButton: UIBarButtonItem!
    @IBOutlet weak var navBar: UINavigationItem!
    @IBOutlet weak var isFav: UISwitch!
    
    public var purpose = "Add Flashcard"
    let flashModel = FlashcardsModel.shared

    @IBAction func cancelPressed(_ sender: Any) {
        // clearing out the textfields
        inputQuestion.text = ""
        inputAnswer.text = ""
        
        dismiss(animated: true, completion: nil)
    }
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        saveButton.isEnabled = false
        
        // Setting the delegate in code
        inputAnswer.delegate = self
        inputQuestion.delegate = self
        
        // Change the label on the navigation bar
        navBar.title = purpose
    }
    @IBAction func keyboardLeaveBackgroundTap(_ sender: UITapGestureRecognizer) {
        inputQuestion.resignFirstResponder()
    }
   
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        if textField == inputQuestion {
            inputQuestion.resignFirstResponder()
            inputAnswer.becomeFirstResponder()
        } else {
            inputAnswer.resignFirstResponder()
        }
        return true
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        
        if let text = textField.text, let textRange = Range(range, in: text) {
            
            let updatedText = text.replacingCharacters(in: textRange, with: string)
            
            if textField == inputAnswer {
                enableSaveButton(quesText: inputQuestion.text, ansText: updatedText)
            }
        }
        return true
    }
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        if text == "\n" {
            inputQuestion.resignFirstResponder()
            inputAnswer.becomeFirstResponder()
            return false
        }
        if let value = textView.text, let textRange = Range(range, in: value) {
            
            let updatedText = value.replacingCharacters(in: textRange, with: text)
            
            if textView == inputQuestion {
                enableSaveButton(quesText: updatedText, ansText: inputAnswer.text ?? "")
            }
        }
        return true
    }
    
    // MARK: - Helper Methods
    
    func enableSaveButton(quesText: String, ansText: String) {
        saveButton.isEnabled = quesText.count > 0 && ansText.count > 0
    }
    
    // MARK: - IBAction Methods
    
    @IBAction func saveButtonPressed(_ sender: UIBarButtonItem) {
        var fav:Bool = false
        if isFav.isOn{
            fav = true
        }
        else{
            fav = false
        }
        
        let ques = inputQuestion.text ?? ""
        let answer = inputAnswer.text ?? ""
        //if question is already asked (true) then alert
        if flashModel.checkAskedQuestion(potentialQuestion: ques){
            let alert = UIAlertController(title: "Warning!", message: "The question you have entered already exists, try a new question", preferredStyle: .alert)
            
            
            let okAction = UIAlertAction(title: "OK", style: .default, handler: okButtonTapped)
            alert.addAction(okAction)
//            controller.dismissViewControllerAnimated(true){ () -> Void in
//                self.present(alert, animated: true, completion: nil)
            self.present(alert, animated: true, completion: nil)
        }
        else{
            flashModel.insert(question: ques ,answer: answer, favorite: fav, at: flashModel.numberOfFlashcards())
            // close the view controller
            saveButton.isEnabled = true;
            dismiss(animated: true, completion: nil)
        }
        
       
    }
    
    func okButtonTapped(action: UIAlertAction) {
        inputQuestion.text = ""
        inputAnswer.text = ""
        saveButton.isEnabled = false
        isFav.isOn = false
        
        
    }
   
}
