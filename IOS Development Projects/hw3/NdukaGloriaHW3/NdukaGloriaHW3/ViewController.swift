//
//  ViewController.swift
//  NdukaGloriaHW3
//
//  Created by Gloria Nduka on 9/25/19.
//  Copyright © 2019 Gloria Nduka. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var outputlabel: UILabel!
    @IBOutlet weak var inputName: UITextField!
    @IBOutlet weak var icecreambutton: UIButton!
    @IBOutlet weak var bobabutton: UIButton!
    @IBOutlet weak var questionLabel: UILabel!
    @IBOutlet weak var resetButton: UIButton!
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        inputName.placeholder="Full Name"
        outputlabel.text=""
        
        questionLabel.accessibilityIdentifier=HW3AccessibilityIdentifiers.questionLabel
        inputName.accessibilityIdentifier = HW3AccessibilityIdentifiers.answerTextField
        icecreambutton.accessibilityIdentifier=HW3AccessibilityIdentifiers.optionButton1
        bobabutton.accessibilityIdentifier = HW3AccessibilityIdentifiers.optionButton2
        outputlabel.accessibilityIdentifier=HW3AccessibilityIdentifiers.messageLabel
        resetButton.accessibilityIdentifier = HW3AccessibilityIdentifiers.resetButton
    }

    @IBAction func button1(_ sender: UIButton) {
        let name = inputName.text ?? ""
        if name.count > 0 {
            outputlabel.text = "Hey \(name)! You like that Icy Treat."
        }else{
            outputlabel.text = "Hey! You like that Icy Treat."
        }
        inputName.resignFirstResponder()
    }
    
    
    @IBAction func button2(_ sender: UIButton) {
        let name = inputName.text ?? ""
        if name.count > 0 {
            outputlabel.text = "Hey \(name)! You like Boba tea! That's literally the best choice."
        }else{
            outputlabel.text = "Hey! You like Boba tea! That's literally the best choice."
        }
        inputName.resignFirstResponder()
    }
    
    @IBAction func donePressed(_ sender: Any) {
         inputName.resignFirstResponder()
    }
    
    @IBAction func keyboardLeaveBackgroundTap(_ sender: UITapGestureRecognizer) {
        inputName.resignFirstResponder()
    }
    
    @IBAction func resetButton(_ sender: UIButton) {
        outputlabel.text = ""
        inputName.text=""
    }
}

