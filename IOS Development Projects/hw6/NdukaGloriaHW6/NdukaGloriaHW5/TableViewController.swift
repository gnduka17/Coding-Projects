//
//  TableViewController.swift
//  NdukaGloriaHW5
//
//  Created by Gloria Nduka on 11/14/19.
//  Copyright © 2019 Gloria Nduka. All rights reserved.
//

import UIKit

class TableViewController: UITableViewController {
     let model = FlashcardsModel.shared

    override func viewDidLoad() {
        super.viewDidLoad()

        self.navigationItem.leftBarButtonItem = self.editButtonItem
    }
    override func viewWillAppear(_ animated:Bool) {
        super.viewWillAppear(animated)
        tableView.reloadData()
    }
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return model.numberOfFlashcards()
    }
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "tableCell", for: indexPath)
        
        if let label = cell.textLabel {
            if let flashcard = model.flashcard(at: indexPath.row){
                label.text = flashcard.getQuestion()
                if let subtitle = cell.detailTextLabel {
                    subtitle.text = "- " + flashcard.getAnswer()
                }
            }
        }
        return cell
    }
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            model.removeFlashcard(at: indexPath.row)
            
            // Delete from tableview
            tableView.deleteRows(at: [indexPath], with: .fade)

        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }
    }
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
        
        model.rearrageFlashcards(from: fromIndexPath.row, to: to.row)
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "AddFlashcardSegue" {
            if let addQuesVC = segue.destination as? AddViewController {
                addQuesVC.purpose = "Add Question"
            }
        }
        
    }

    

}
