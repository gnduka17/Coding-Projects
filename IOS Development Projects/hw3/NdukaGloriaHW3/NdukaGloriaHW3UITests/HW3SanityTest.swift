//
//  HW3SanityTest.swift
//  HW3
//
//  Created by Harrison Weinerman on 8/24/18.
//  Copyright © 2018 Harrison Weinerman. All rights reserved.
//

import XCTest

class HW3SanityTest: XCTestCase {
    
    private let app = XCUIApplication()

    override func setUp() {
        super.setUp()
        continueAfterFailure = true
        XCUIApplication().launch()
    }
    
    /// This test should pass regardless of how you configured your app
    func testBasicUIElements() {
        let questionLabel = app.staticTexts[HW3AccessibilityIdentifiers.questionLabel]
        let messageLabel = app.staticTexts[HW3AccessibilityIdentifiers.messageLabel]
        let answerTextField = app.textFields[HW3AccessibilityIdentifiers.answerTextField]
        let optionButton1 = app.buttons[HW3AccessibilityIdentifiers.optionButton1]
        let optionButton2 = app.buttons[HW3AccessibilityIdentifiers.optionButton2]
        let resetButton = app.buttons[HW3AccessibilityIdentifiers.resetButton]
        
        [questionLabel,
         answerTextField,
         optionButton1,
         optionButton2,
         messageLabel,
         resetButton].forEach({ XCTAssert($0.exists) })
    }
    
    /// This test should only pass if you implemented UIButtons and UIImageViews for option selection
    func testImageViews() {
        let answerImageView1 = app.images[HW3AccessibilityIdentifiers.answerImageView1]
        let answerImageView2 = app.images[HW3AccessibilityIdentifiers.answerImageView2]
        
        [answerImageView1,
         answerImageView2].forEach({
            XCTAssert($0.exists)
         })
    }
    
    // This test will validate user input is cleared when reset pressed
    func testResetButton() {
        //makes the connection to your IBOutlets
        let questionLabel = app.staticTexts[HW3AccessibilityIdentifiers.questionLabel]
        let messageLabel = app.staticTexts[HW3AccessibilityIdentifiers.messageLabel]
        let answerTextField = app.textFields[HW3AccessibilityIdentifiers.answerTextField]
        let resetButton = app.buttons[HW3AccessibilityIdentifiers.resetButton]
        let optionButton1 = app.buttons[HW3AccessibilityIdentifiers.optionButton1]
        
        //added for students who are making keyboard come up on runtime
        if app.keyboards.count != 0
        {
            answerTextField.typeText("\n")
        }

        // type in the textfield and click done
        let name = "Tommy Trojan"
        let initialMessage = messageLabel.label
        answerTextField.tap()
        XCTAssertEqual(app.keyboards.count, 1)
        answerTextField.typeText(name)
        
        // you may experience a failure if your software keybaord doesnt popup
        //Simulator->Hardware->Keyboard->Toggle Software Keyboard
        app.keyboards.buttons["Done"].tap()
        XCTAssertEqual(app.keyboards.count, 0)
        //message didnt change when clicking 'Done'
        XCTAssertEqual(messageLabel.label, initialMessage)
        
        //tap option button to change message
        optionButton1.tap()
        XCTAssertNotEqual(messageLabel.label, initialMessage)
        
        //reset components - to its initial phase
        resetButton.tap()
        XCTAssertEqual(answerTextField.label, "")
        
        XCTAssert([initialMessage, " ", ""].contains(messageLabel.label))
        XCTAssertNotEqual(questionLabel.label, "")
    }
}
