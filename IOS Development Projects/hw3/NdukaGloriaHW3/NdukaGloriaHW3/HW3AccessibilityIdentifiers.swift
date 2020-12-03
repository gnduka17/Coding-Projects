//
//  HW3AccessibilityIdentifiers.swift
//  HW3
//
//  Created by Harrison Weinerman
//  Copyright © 2018 Harrison Weinerman. All rights reserved.
//

/// Contains accessibility identifiers used in ITP-342 HW3 for grading.
/// Don't change the contents of this file.
struct HW3AccessibilityIdentifiers {
    /// Identifier for your UILabel asking the user the question
    static let questionLabel = "questionLabel"
    /// Identifier for the UITextField that the user can input their answer into
    static let answerTextField = "answerTextField"
    /// Identifier for the first UIButton the user can press to select the first option
    static let optionButton1 = "optionButton1"
    /// Identifier for the second UIButton the user can press to select the second option
    static let optionButton2 = "optionButton2"
    /// Identifier for the UILabel that displays the message to the user based on their input
    static let messageLabel = "messageLabel"
    /// Identifer for the UIButton that resets the state of the app
    static let resetButton = "resetButton"
    
    /////////////OPTIONAL DEPENDING ON IMPLEMENTATION////////////////
    
    /// Identifier for the UIImageView used to display content near the first option button
    /// if the button has text. Not required if the button has no text and its own image.
    static let answerImageView1 = "answerImageView1"
    /// Identifier for the UIImageView used to display content near the second option button
    /// if the button has text. Not required if the button has no text and its own image.
    static let answerImageView2 = "answerImageView2"
}
