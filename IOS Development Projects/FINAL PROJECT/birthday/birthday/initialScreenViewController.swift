//
//  initialScreenViewController.swift
//  birthday
//
//  Created by Gloria Nduka on 12/4/19.
//  Copyright © 2019 Gloria Nduka. All rights reserved.
//

import UIKit
import GoogleSignIn

class initialScreenViewController: UIViewController, GIDSignInDelegate{
    
    @IBOutlet weak var signInButton: GIDSignInButton!

    override func viewDidLoad() {
        super.viewDidLoad()
        GIDSignIn.sharedInstance()?.presentingViewController = self
        // Automatically sign in the user.
        GIDSignIn.sharedInstance()?.restorePreviousSignIn()
        let signIn = GIDSignIn.sharedInstance()
        signIn?.delegate = self
        //editing the google sign in button
        signInButton.colorScheme = GIDSignInButtonColorScheme.dark
        signInButton.center = view.center
        signInButton.style = GIDSignInButtonStyle.wide
    }
    
    func sign(_ signIn: GIDSignIn!, didSignInFor user: GIDGoogleUser!, withError error: Error!) {
        if error == nil {
            if let vc = self.storyboard?.instantiateViewController(withIdentifier: "UITabBarController") as? UITabBarController {
                self.present(vc, animated: true, completion: nil)
            }
        }
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "signIn" {
            if let signin = segue.destination as? AllBDayTableViewController {
                //signin.purpose = ""
            }
        }
        
    }

}
