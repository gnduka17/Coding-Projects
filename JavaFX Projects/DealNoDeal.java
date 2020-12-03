package DealNduka;

import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DealNoDeal extends Application {
	
	//declaring variables needed to implement game 
	BorderPane root = new BorderPane();
	TilePane suitcases = new TilePane();
	VBox moneyList = new VBox(10);
	VBox middle = new VBox(); 
	Label title = new Label("DEAL OR NO DEAL GAME");
	VBox bottomPart = new VBox();
	HBox bottomButs = new HBox();
	ArrayList<Integer> moneyArray = new ArrayList<Integer>();
	ArrayList<Integer> orderArray = new ArrayList<Integer>();
	Boolean userChoice = false;
	double userChoice2 = 0;
	double sum = 0;
	double numclicks = 0;
	double offer;
	int totalsumMoney = 1616116;
	Label offerMoney = new Label("");
	Label offerTitle = new Label("Offer:");
	Label winningMoney = new Label("");
	int count =0;
	//declaring deal and no deal buttons
    Button deal = new Button();
    Button nodeal = new Button();
    //Creating 10 suitcases without FOR LOOP so it would be easier to disable them  
	Button but1 = new Button();
	Button but2 = new Button();
	Button but3 = new Button();
	Button but4 = new Button();
	Button but5 = new Button();
	Button but6 = new Button();
	Button but7 = new Button();
	Button but8 = new Button();
	Button but9 = new Button();
	Button but10 = new Button();
	
	//deal with keyboard input
	private void setGlobalEventHandler(Node root) {
	    root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getText().equals("d")) {
	        		deal.fire();
	  	           	ev.consume(); 
	        }
	        else if(ev.getText().equals("n")) {
	        	nodeal.fire();
	        	ev.consume();
	        	
	        }
	        else if(ev.getText().equals("1")) {
	        		but1.fire();
		        	ev.consume();
	        	
	        }
	        else if(ev.getText().equals("2")) {
	        	but2.fire();
	        	ev.consume();
	        }
	        else if(ev.getText().equals("3")) {
	        	but3.fire();
	        	ev.consume();
	        }
	        else if(ev.getText().equals("4")) {
	        	but4.fire();
	        	ev.consume();
	        }
	        else if(ev.getText().equals("5")) {
	        	but5.fire();
	        	ev.consume();
	        }
	        else if(ev.getText().equals("6")) {
	        	but6.fire();
	        	ev.consume();
	        }
	        else if(ev.getText().equals("7")) {
	        	but7.fire();
	        	ev.consume();
	        }
	        else if(ev.getText().equals("8")) {
	        	but8.fire();
	        	ev.consume();
	        }
	        else if(ev.getText().equals("9")) {
	        	but9.fire();
	        	ev.consume();
	        }
	        else if(ev.getText().equals("10")) {
	        	but10.fire();
	        	ev.consume();
	        }
	        
	    });
	}

	 public static void main( String[] args )
		{ launch(args);}
	    @Override
	    public void start(Stage stage)
	    { initUI(stage); }

	    private void initUI(Stage stage)
	    { 
	    	//setting up the stage and root
	    	stage.setTitle("Deal or No Deal Game");
	    	Scene scene = new Scene(root, 800, 800);
	    	stage.setScene(scene);
	    	stage.show();
	    	setGlobalEventHandler(root);
	    	root.setPadding(new Insets(70, 0, 0, 0));
	    	
	    	//setting game title and centering it 
	    	title.setStyle("-fx-font-size:50;" + "-fx-font-family:Verdana;"+"-fx-font-weight: bold;");
	    	root.setTop(title);
	    	root.setAlignment(title, Pos.CENTER);
	    	
	    	//create a list of the money values
	    	//will shuffle this list and place the money under suitcase with same index number
	    	moneyArray.add(1);
	    	moneyArray.add(5);
	    	moneyArray.add(10);
	    	moneyArray.add(100);
	    	moneyArray.add(1000);
	    	moneyArray.add(5000);
	    	moneyArray.add(10000);
	    	moneyArray.add(100000);
	    	moneyArray.add(500000);
	    	moneyArray.add(1000000);
	    	Collections.shuffle(moneyArray);
	    	
	    	//this list is NOT shuffled and is used to find the index of the right money in correct spot to know where to cancel/highlight of the list 
	    	orderArray.add(1);
	    	orderArray.add(5);
	    	orderArray.add(10);
	    	orderArray.add(100);
	    	orderArray.add(1000);
	    	orderArray.add(5000);
	    	orderArray.add(10000);
	    	orderArray.add(100000);
	    	orderArray.add(500000);
	    	orderArray.add(1000000);
	    	
	    	//This is the table of values to the right of screen that is highlighted when money is selected
	    	moneyList.getChildren().add(new Label("$1"));
	    	moneyList.getChildren().add(new Label("$5"));
	    	moneyList.getChildren().add(new Label("$10"));
	    	moneyList.getChildren().add(new Label("$100"));
	    	moneyList.getChildren().add(new Label("$1,000"));
	    	moneyList.getChildren().add(new Label("$5,000"));
	    	moneyList.getChildren().add(new Label("$10,000"));
	    	moneyList.getChildren().add(new Label("$100,000"));
	    	moneyList.getChildren().add(new Label("$500,000"));
	    	moneyList.getChildren().add(new Label("$1,000,000"));
	    	moneyList.setPadding(new Insets(50,90,0,0));
	    	root.setRight(moneyList);
	    	
		    
		    deal.setText("DEAL");
		    deal.setMinWidth(205);
		    deal.setMinHeight(50);
		    deal.setStyle("-fx-font-size:30;"+ "-fx-background-color:green;"+"-fx-font-weight: bold;");
		    deal.setOnAction(
	        		(ActionEvent event) ->
	        		{
	        			//disable NO DEAL and DEAL buttons when deal is clicked
	        			nodeal.setDisable(true);
	        			deal.setDisable(true);
	        			//present user their winning money
	        			winningMoney.setText("GAME OVER!! \nYou Won: $"+ String.format("%.2f",offer));
	        			winningMoney.setPadding(new Insets(40,0,0,0));
	        			winningMoney.setStyle("-fx-font-size:40;");
	        			//show user money won
	        			middle.getChildren().add(winningMoney);
	        			
	        		}
	        		);
		   
		   
		    nodeal.setText("NO DEAL");
		    nodeal.setMinWidth(205);
		    nodeal.setMinHeight(50);
		    nodeal.setStyle("-fx-font-size:30;"+ "-fx-background-color:red;"+"-fx-font-weight: bold;");
		    nodeal.setOnAction(
	        		(ActionEvent event) ->
	        		{
	        			count++;
	        			if(count==9) {
	        				but1.setDisable(true);
	        				but2.setDisable(true);
	        				but3.setDisable(true);
	        				but4.setDisable(true);
	        				but5.setDisable(true);
	        				but6.setDisable(true);
	        				but7.setDisable(true);
	        				but8.setDisable(true);
	        				but9.setDisable(true);
	        				but10.setDisable(true);
	        				nodeal.setDisable(true);
	        				deal.setDisable(true);
	        				
	        				//present user their winning money
		        			winningMoney.setText("GAME OVER!! \nYou Won: $"+ String.format("%.2f",userChoice2));
		        			winningMoney.setPadding(new Insets(40,0,0,0));
		        			winningMoney.setStyle("-fx-font-size:40;");
		        			//show user money won
		        			middle.getChildren().add(winningMoney);
	        				
	        				
	        				
	        			}else {
	        				//enable other suitcases when NO DEAL button is clicked 
		        			but1.setDisable(false);
	        				but2.setDisable(false);
	        				but3.setDisable(false);
	        				but4.setDisable(false);
	        				but5.setDisable(false);
	        				but6.setDisable(false);
	        				but7.setDisable(false);
	        				but8.setDisable(false);
	        				but9.setDisable(false);
	        				but10.setDisable(false);
	        				
	        				//disable these buttons 
	        				nodeal.setDisable(true);
	        				deal.setDisable(true);
	        				offerMoney.setText("");
	        				
	        			}
	        		}
	        		);
		  
		    offerMoney.setMinWidth(205);
		    offerMoney.setStyle("-fx-text-align:center;"+ "-fx-font-size:40;"+"-fx-font-weight: bold;");
		    offerMoney.setPadding(new Insets(0,45,0,45));
		    offerTitle.setStyle( "-fx-font-size:25;" + "-fx-font-weight: bold;");

		    bottomButs.setAlignment(Pos.CENTER);
	    	bottomPart.setAlignment(Pos.CENTER);
	    	bottomPart.setPadding(new Insets(0,0,80,0));
		    bottomButs.getChildren().add(deal);
		    bottomButs.getChildren().add(offerMoney);
		    bottomButs.getChildren().add(nodeal);
		    
		    //add title and buttons and offer to VBox bottomPart
		    bottomPart.getChildren().add(offerTitle);
		    bottomPart.getChildren().add(bottomButs);
	    	
	    	//setting the style of button and height/width
	    	but1.setStyle("-fx-background-color: #4D8CC3; "+"-fx-font-size:30;");
	    	but1.setMinWidth(82.5);
	    	but1.setMinHeight(82.5);
    		but1.setText("1");
    		but1.setOnAction(
            		(ActionEvent event) ->
            		{
            			//userchoice is the first suitcase user has chosen
            			//if userchoice is false, the button user clicks, is the first suitcase
            			if(!userChoice) {
            				userChoice = true;
            				//puts user's choice in variable..chosen from shuffled array
            				userChoice2 = moneyArray.get(0);
            				but1.setDisable(true);
            				but1.setStyle("-fx-background-color: #000000;" + "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            			}
            			else {
            				but1.setStyle("-fx-background-color: #000000;"+ "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            				nodeal.setDisable(false);
    	        			deal.setDisable(false);
            				//from the moneyList, find the correct money by using indexof 
            				((Label) moneyList.getChildren().get(orderArray.indexOf(moneyArray.get(0)))).setStyle("-fx-background-color:#ADA2AD");
            				
            				//numclicks used to figure out how many suitcases have been opened
            				numclicks++;
            				
            				//sum the money that had been opened to later subtract from total sum
            				sum+=moneyArray.get(0);
    
            				//offer is sum of all the rest unopened suitcases (totalSumMoney-sum)/rest of suitcases(10-numclicks) * 0.9
            				offer = ((totalsumMoney-sum)/(10-numclicks))*0.9;
            				
            				//setting offerMoney to variable 
            				offerMoney.setText(String.format("%.2f",offer));
            				
            				//Display offer and DEAL and NO DEAL buttons on bottom
            				root.setBottom(bottomPart);
            				but1.setDisable(true);
            				but2.setDisable(true);
            				but3.setDisable(true);
            				but4.setDisable(true);
            				but5.setDisable(true);
            				but6.setDisable(true);
            				but7.setDisable(true);
            				but8.setDisable(true);
            				but9.setDisable(true);
            				but10.setDisable(true);
            			}
            				
            		}
            		);
    		suitcases.getChildren().add(but1);
    		
    		
	    	//setting the style of button and height/width
	    	but2.setStyle("-fx-background-color: #4D8CC3; "+"-fx-font-size:30;");
	    	but2.setMinWidth(82.5);
	    	but2.setMinHeight(82.5);
    		but2.setText("2");
    		but2.setOnAction(
            		(ActionEvent event) ->
            		{
            			//userchoice is the first suitcase user has chosen
            			//if userchoice is false, the button user clicks, is the first suitcase
            			if(!userChoice) {
            				userChoice = true;
            				//puts user's choice in variable..chosen from shuffled array
            				userChoice2 = moneyArray.get(1);
            				but2.setDisable(true);
            				but2.setStyle("-fx-background-color: #000000;" + "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            			}
            			else {
            				nodeal.setDisable(false);
    	        			deal.setDisable(false);
            				but2.setStyle("-fx-background-color: #000000;"+ "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            				//from the moneyList, find the correct money by using indexof 
            				((Label) moneyList.getChildren().get(orderArray.indexOf(moneyArray.get(1)))).setStyle("-fx-background-color:#ADA2AD");
            				
            				//numclicks used to figure out how many suitcases have been opened
            				numclicks++;
            				
            				//sum the money that had been opened to later subtract from total sum
            				sum+=moneyArray.get(1);
    
            				//offer is sum of all the rest unopened suitcases (totalSumMoney-sum)/rest of suitcases(10-numclicks) * 0.9
            				offer = ((totalsumMoney-sum)/(10-numclicks))*0.9;
            				
            				//setting offerMoney to variable 
            				offerMoney.setText(String.format("%.2f",offer));
            				
            				//Display offer and DEAL and NO DEAL buttons on bottom
            				root.setBottom(bottomPart);
            				but1.setDisable(true);
            				but2.setDisable(true);
            				but3.setDisable(true);
            				but4.setDisable(true);
            				but5.setDisable(true);
            				but6.setDisable(true);
            				but7.setDisable(true);
            				but8.setDisable(true);
            				but9.setDisable(true);
            				but10.setDisable(true);
            			}
            				
            		}
            		);
    		suitcases.getChildren().add(but2);
    		
	    	//setting the style of button and height/width
	    	but3.setStyle("-fx-background-color: #4D8CC3; "+"-fx-font-size:30;");
	    	but3.setMinWidth(82.5);
	    	but3.setMinHeight(82.5);
    		but3.setText("3");
    		but3.setOnAction(
            		(ActionEvent event) ->
            		{
            			//userchoice is the first suitcase user has chosen
            			//if userchoice is false, the button user clicks, is the first suitcase
            			if(!userChoice) {
            				userChoice = true;
            				//puts user's choice in variable..chosen from shuffled array
            				userChoice2 = moneyArray.get(2);
            				but3.setDisable(true);
            				but3.setStyle("-fx-background-color: #000000;" + "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            			}
            			else {
            				but3.setStyle("-fx-background-color: #000000;"+ "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            				nodeal.setDisable(false);
    	        			deal.setDisable(false);
            				//from the moneyList, find the correct money by using indexof 
            				((Label) moneyList.getChildren().get(orderArray.indexOf(moneyArray.get(2)))).setStyle("-fx-background-color:#ADA2AD");
            				
            				//numclicks used to figure out how many suitcases have been opened
            				numclicks++;
            				
            				//sum the money that had been opened to later subtract from total sum
            				sum+=moneyArray.get(2);
    
            				//offer is sum of all the rest unopened suitcases (totalSumMoney-sum)/rest of suitcases(10-numclicks) * 0.9
            				offer = ((totalsumMoney-sum)/(10-numclicks))*0.9;
            				
            				//setting offerMoney to variable 
            				offerMoney.setText(String.format("%.2f",offer));
            				
            				//Display offer and DEAL and NO DEAL buttons on bottom
            				root.setBottom(bottomPart);
            				but1.setDisable(true);
            				but2.setDisable(true);
            				but3.setDisable(true);
            				but4.setDisable(true);
            				but5.setDisable(true);
            				but6.setDisable(true);
            				but7.setDisable(true);
            				but8.setDisable(true);
            				but9.setDisable(true);
            				but10.setDisable(true);
            			}
            				
            		}
            		);
    		suitcases.getChildren().add(but3);
    		
	    	//setting the style of button and height/width
	    	but4.setStyle("-fx-background-color: #4D8CC3; "+"-fx-font-size:30;");
	    	but4.setMinWidth(82.5);
	    	but4.setMinHeight(82.5);
    		but4.setText("4");
    		but4.setOnAction(
            		(ActionEvent event) ->
            		{
            			//userchoice is the first suitcase user has chosen
            			//if userchoice is false, the button user clicks, is the first suitcase
            			if(!userChoice) {
            				userChoice = true;
            				//puts user's choice in variable..chosen from shuffled array
            				userChoice2 = moneyArray.get(3);
            				but4.setDisable(true);
            				but4.setStyle("-fx-background-color: #000000;" + "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            			}
            			else {
            				but4.setStyle("-fx-background-color: #000000;"+ "-fx-text-fill: yellow;"+"-fx-font-size:30;");	
            				nodeal.setDisable(false);
    	        			deal.setDisable(false);
            				//from the moneyList, find the correct money by using indexof 
            				((Label) moneyList.getChildren().get(orderArray.indexOf(moneyArray.get(3)))).setStyle("-fx-background-color:#ADA2AD");
            				
            				//numclicks used to figure out how many suitcases have been opened
            				numclicks++;
            				
            				//sum the money that had been opened to later subtract from total sum
            				sum+=moneyArray.get(3);
    
            				//offer is sum of all the rest unopened suitcases (totalSumMoney-sum)/rest of suitcases(10-numclicks) * 0.9
            				offer = ((totalsumMoney-sum)/(10-numclicks))*0.9;
            				
            				//setting offerMoney to variable 
            				offerMoney.setText(String.format("%.2f",offer));
            				
            				//Display offer and DEAL and NO DEAL buttons on bottom
            				root.setBottom(bottomPart);
            				but1.setDisable(true);
            				but2.setDisable(true);
            				but3.setDisable(true);
            				but4.setDisable(true);
            				but5.setDisable(true);
            				but6.setDisable(true);
            				but7.setDisable(true);
            				but8.setDisable(true);
            				but9.setDisable(true);
            				but10.setDisable(true);
            			}
            				
            		}
            		);
    		suitcases.getChildren().add(but4);
    		
	    	//setting the style of button and height/width
    		but5.setStyle("-fx-background-color: #4D8CC3; "+"-fx-font-size:30;");
    		but5.setMinWidth(82.5);
    		but5.setMinHeight(82.5);
    		but5.setText("5");
    		but5.setOnAction(
            		(ActionEvent event) ->
            		{
            			//userchoice is the first suitcase user has chosen
            			//if userchoice is false, the button user clicks, is the first suitcase
            			if(!userChoice) {
            				userChoice = true;
            				//puts user's choice in variable..chosen from shuffled array
            				userChoice2 = moneyArray.get(4);
            				but5.setDisable(true);
            				but5.setStyle("-fx-background-color: #000000;" + "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            			}
            			else {
            				but5.setStyle("-fx-background-color: #000000;"+ "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            				nodeal.setDisable(false);
    	        			deal.setDisable(false);
            				//from the moneyList, find the correct money by using indexof 
            				((Label) moneyList.getChildren().get(orderArray.indexOf(moneyArray.get(4)))).setStyle("-fx-background-color:#ADA2AD");
            				
            				//numclicks used to figure out how many suitcases have been opened
            				numclicks++;
            				
            				//sum the money that had been opened to later subtract from total sum
            				sum+=moneyArray.get(4);
    
            				//offer is sum of all the rest unopened suitcases (totalSumMoney-sum)/rest of suitcases(10-numclicks) * 0.9
            				offer = ((totalsumMoney-sum)/(10-numclicks))*0.9;
            				
            				//setting offerMoney to variable 
            				offerMoney.setText(String.format("%.2f",offer));
            				
            				//Display offer and DEAL and NO DEAL buttons on bottom
            				root.setBottom(bottomPart);
            				but1.setDisable(true);
            				but2.setDisable(true);
            				but3.setDisable(true);
            				but4.setDisable(true);
            				but5.setDisable(true);
            				but6.setDisable(true);
            				but7.setDisable(true);
            				but8.setDisable(true);
            				but9.setDisable(true);
            				but10.setDisable(true);
            			}
            				
            		}
            		);
    		suitcases.getChildren().add(but5);
    		
	    	//setting the style of button and height/width
	    	but6.setStyle("-fx-background-color: #4D8CC3; "+"-fx-font-size:30;");
	    	but6.setMinWidth(82.5);
	    	but6.setMinHeight(82.5);
    		but6.setText("6");
    		but6.setOnAction(
            		(ActionEvent event) ->
            		{
            			//userchoice is the first suitcase user has chosen
            			//if userchoice is false, the button user clicks, is the first suitcase
            			if(!userChoice) {
            				userChoice = true;
            				//puts user's choice in variable..chosen from shuffled array
            				userChoice2 = moneyArray.get(5);
            				but6.setDisable(true);
            				but6.setStyle("-fx-background-color: #000000;" + "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            			}
            			else {
            				but6.setStyle("-fx-background-color: #000000;"+ "-fx-text-fill: yellow;"+"-fx-font-size:30;");	
            				nodeal.setDisable(false);
    	        			deal.setDisable(false);
            				//from the moneyList, find the correct money by using indexof 
            				((Label) moneyList.getChildren().get(orderArray.indexOf(moneyArray.get(5)))).setStyle("-fx-background-color:#ADA2AD");
            				
            				//numclicks used to figure out how many suitcases have been opened
            				numclicks++;
            				
            				//sum the money that had been opened to later subtract from total sum
            				sum+=moneyArray.get(5);
    
            				//offer is sum of all the rest unopened suitcases (totalSumMoney-sum)/rest of suitcases(10-numclicks) * 0.9
            				offer = ((totalsumMoney-sum)/(10-numclicks))*0.9;
            				
            				//setting offerMoney to variable 
            				offerMoney.setText(String.format("%.2f",offer));
            				
            				//Display offer and DEAL and NO DEAL buttons on bottom
            				root.setBottom(bottomPart);
            				but1.setDisable(true);
            				but2.setDisable(true);
            				but3.setDisable(true);
            				but4.setDisable(true);
            				but5.setDisable(true);
            				but6.setDisable(true);
            				but7.setDisable(true);
            				but8.setDisable(true);
            				but9.setDisable(true);
            				but10.setDisable(true);
            			}
            				
            		}
            		);
    		suitcases.getChildren().add(but6);
    		
	    	//setting the style of button and height/width
	    	but7.setStyle("-fx-background-color: #4D8CC3; "+"-fx-font-size:30;");
	    	but7.setMinWidth(82.5);
	    	but7.setMinHeight(82.5);
    		but7.setText("7");
    		but7.setOnAction(
            		(ActionEvent event) ->
            		{
            			//userchoice is the first suitcase user has chosen
            			//if userchoice is false, the button user clicks, is the first suitcase
            			if(!userChoice) {
            				userChoice = true;
            				//puts user's choice in variable..chosen from shuffled array
            				userChoice2 = moneyArray.get(6);
            				but7.setDisable(true);
            				but7.setStyle("-fx-background-color: #000000;" + "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            			}
            			else {
            				but7.setStyle("-fx-background-color: #000000;"+ "-fx-text-fill: yellow;"+"-fx-font-size:30;");	
            				nodeal.setDisable(false);
    	        			deal.setDisable(false);
            				//from the moneyList, find the correct money by using indexof 
            				((Label) moneyList.getChildren().get(orderArray.indexOf(moneyArray.get(6)))).setStyle("-fx-background-color:#ADA2AD");
            				
            				//numclicks used to figure out how many suitcases have been opened
            				numclicks++;
            				
            				//sum the money that had been opened to later subtract from total sum
            				sum+=moneyArray.get(6);
    
            				//offer is sum of all the rest unopened suitcases (totalSumMoney-sum)/rest of suitcases(10-numclicks) * 0.9
            				offer = ((totalsumMoney-sum)/(10-numclicks))*0.9;
            				
            				//setting offerMoney to variable 
            				offerMoney.setText(String.format("%.2f",offer));
            				
            				//Display offer and DEAL and NO DEAL buttons on bottom
            				root.setBottom(bottomPart);
            				but1.setDisable(true);
            				but2.setDisable(true);
            				but3.setDisable(true);
            				but4.setDisable(true);
            				but5.setDisable(true);
            				but6.setDisable(true);
            				but7.setDisable(true);
            				but8.setDisable(true);
            				but9.setDisable(true);
            				but10.setDisable(true);
            			}
            				
            		}
            		);
    		suitcases.getChildren().add(but7);
    		
	    	//setting the style of button and height/width
	    	but8.setStyle("-fx-background-color: #4D8CC3; "+"-fx-font-size:30;");
	    	but8.setMinWidth(82.5);
	    	but8.setMinHeight(82.5);
    		but8.setText("8");
    		but8.setOnAction(
            		(ActionEvent event) ->
            		{
            			//userchoice is the first suitcase user has chosen
            			//if userchoice is false, the button user clicks, is the first suitcase
            			if(!userChoice) {
            				userChoice = true;
            				//puts user's choice in variable..chosen from shuffled array
            				userChoice2 = moneyArray.get(7);
            				but8.setDisable(true);
            				but8.setStyle("-fx-background-color: #000000;" + "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            			}
            			else {
            				but8.setStyle("-fx-background-color: #000000;"+ "-fx-text-fill: yellow;"+"-fx-font-size:30;");	
            				nodeal.setDisable(false);
    	        			deal.setDisable(false);
            				//from the moneyList, find the correct money by using indexof 
            				((Label) moneyList.getChildren().get(orderArray.indexOf(moneyArray.get(7)))).setStyle("-fx-background-color:#ADA2AD");
            				
            				//numclicks used to figure out how many suitcases have been opened
            				numclicks++;
            				
            				//sum the money that had been opened to later subtract from total sum
            				sum+=moneyArray.get(7);
    
            				//offer is sum of all the rest unopened suitcases (totalSumMoney-sum)/rest of suitcases(10-numclicks) * 0.9
            				offer = ((totalsumMoney-sum)/(10-numclicks))*0.9;
            				
            				//setting offerMoney to variable 
            				offerMoney.setText(String.format("%.2f",offer));
            				
            				//Display offer and DEAL and NO DEAL buttons on bottom
            				root.setBottom(bottomPart);
            				but1.setDisable(true);
            				but2.setDisable(true);
            				but3.setDisable(true);
            				but4.setDisable(true);
            				but5.setDisable(true);
            				but6.setDisable(true);
            				but7.setDisable(true);
            				but8.setDisable(true);
            				but9.setDisable(true);
            				but10.setDisable(true);
            			}
            				
            		}
            		);
    		suitcases.getChildren().add(but8);
    		
	    	//setting the style of button and height/width
	    	but9.setStyle("-fx-background-color: #4D8CC3; "+"-fx-font-size:30;");
	    	but9.setMinWidth(82.5);
	    	but9.setMinHeight(82.5);
    		but9.setText("9");
    		but9.setOnAction(
            		(ActionEvent event) ->
            		{
            			//userchoice is the first suitcase user has chosen
            			//if userchoice is false, the button user clicks, is the first suitcase
            			if(!userChoice) {
            				userChoice = true;
            				//puts user's choice in variable..chosen from shuffled array
            				userChoice2 = moneyArray.get(8);
            				but9.setDisable(true);
            				but9.setStyle("-fx-background-color: #000000;" + "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            			}
            			else {
            				but9.setStyle("-fx-background-color: #000000;"+ "-fx-text-fill: yellow;"+"-fx-font-size:30;");	
            				nodeal.setDisable(false);
    	        			deal.setDisable(false);
            				//from the moneyList, find the correct money by using indexof 
            				((Label) moneyList.getChildren().get(orderArray.indexOf(moneyArray.get(8)))).setStyle("-fx-background-color:#ADA2AD");
            				
            				//numclicks used to figure out how many suitcases have been opened
            				numclicks++;
            				
            				//sum the money that had been opened to later subtract from total sum
            				sum+=moneyArray.get(8);
    
            				//offer is sum of all the rest unopened suitcases (totalSumMoney-sum)/rest of suitcases(10-numclicks) * 0.9
            				offer = ((totalsumMoney-sum)/(10-numclicks))*0.9;
            				
            				//setting offerMoney to variable 
            				offerMoney.setText(String.format("%.2f",offer));
            				
            				//Display offer and DEAL and NO DEAL buttons on bottom
            				root.setBottom(bottomPart);
            				but1.setDisable(true);
            				but2.setDisable(true);
            				but3.setDisable(true);
            				but4.setDisable(true);
            				but5.setDisable(true);
            				but6.setDisable(true);
            				but7.setDisable(true);
            				but8.setDisable(true);
            				but9.setDisable(true);
            				but10.setDisable(true);
            			}
            				
            		}
            		);
    		suitcases.getChildren().add(but9);
    		
	    	//setting the style of button and height/width
	    	but10.setStyle("-fx-background-color: #4D8CC3; "+"-fx-font-size:30;");
	    	but10.setMinWidth(82.5);
	    	but10.setMinHeight(82.5);
    		but10.setText("10");
    		but10.setOnAction(
            		(ActionEvent event) ->
            		{
            			//userchoice is the first suitcase user has chosen
            			//if userchoice is false, the button user clicks, is the first suitcase
            			if(!userChoice) {
            				userChoice = true;
            				//puts user's choice in variable..chosen from shuffled array
            				userChoice2 = moneyArray.get(9);
            				but10.setDisable(true);
            				but10.setStyle("-fx-background-color: #000000;" + "-fx-text-fill: yellow;"+"-fx-font-size:30;");
            			}
            			else {
            				but10.setStyle("-fx-background-color: #000000;"+ "-fx-text-fill: yellow;"+"-fx-font-size:30;");	
            				nodeal.setDisable(false);
    	        			deal.setDisable(false);
            				//from the moneyList, find the correct money by using indexof 
            				((Label) moneyList.getChildren().get(orderArray.indexOf(moneyArray.get(9)))).setStyle("-fx-background-color:#ADA2AD");
            				
            				//numclicks used to figure out how many suitcases have been opened
            				numclicks++;
            				
            				//sum the money that had been opened to later subtract from total sum
            				sum+=moneyArray.get(9);
    
            				//offer is sum of all the rest unopened suitcases (totalSumMoney-sum)/rest of suitcases(10-numclicks) * 0.9
            				offer = ((totalsumMoney-sum)/(10-numclicks))*0.9;
            				
            				//setting offerMoney to variable 
            				offerMoney.setText(String.format("%.2f",offer));
            				
            				//Display offer and DEAL and NO DEAL buttons on bottom
            				root.setBottom(bottomPart);
            				but1.setDisable(true);
            				but2.setDisable(true);
            				but3.setDisable(true);
            				but4.setDisable(true);
            				but5.setDisable(true);
            				but6.setDisable(true);
            				but7.setDisable(true);
            				but8.setDisable(true);
            				but9.setDisable(true);
            				but10.setDisable(true);
            			}
            				
            		}
            		);
    		suitcases.getChildren().add(but10);
    		
    		//Adding space between suitcases and having 5 in each row
	    	suitcases.setHgap(10);
	    	suitcases.setVgap(10);
	    	suitcases.setPrefColumns(5);
	    	suitcases.setMaxWidth(Region.USE_PREF_SIZE);
	    	
	    	//adding suitcases to middle part of root BorderPane
	    	middle.getChildren().add(suitcases);
	    	middle.setPadding(new Insets(80,0,0,70));
	    	root.setCenter(middle);
	    	
	    	
	    	
		    
	    	
	    	
	    	
	    	
	    	

	    	
	    	
	    	
	    
	    	
	    	
	    	
	    	
	    	
	    	
	    }

}
