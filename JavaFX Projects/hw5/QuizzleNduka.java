package hw5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class QuizzleNduka extends Application {
	//declaring variables 
	VBox root = new VBox();
	Button loadFile = new Button("Load File");
	FileChooser fc = new FileChooser();
	File file;
	Scanner sc;
	ArrayList<String> list1 = new ArrayList<String>();
	ArrayList<String> list2 = new ArrayList<String>();
	String word1 = "";
	String word2 = "";
	String wordList1 = "";
	String wordList2 = "";
	int answer1;
	int answer2;
	int answer3;
	int answer4;
	int count =0;
	int quesCount = 0;
	int num;
	Random rand = new Random();
	int randnum = 0;
	int randOp;
	int randChoice;
	HBox bottomPart = new HBox(400);
	Label title = new Label("QUIZZLE");
	HBox yesno = new HBox(70);
	HBox secLevel = new HBox(70);
	Button yes = new Button("Hell Yea!");
	Button no = new Button("Sadly, No");
	Button ans1 = new Button();
	Button ans2 = new Button();
	Button ans3 = new Button();
	Button ans4 = new Button();
	Button nextButt = new Button("Next Question");
	int score = 0;
	Label scoreLabel = new Label("SCORE:\n0");
	Button startQuiz = new Button("Start Quiz");
	Label descrip = new Label("Welcome to Quizzle!\nYou are about to take a mini quiz.\nAre You Ready?");
	
	public static void main(String[]args) {
		launch(args);
		
	}
	public void start(Stage stage) {
		Scene scene = new Scene(root, 700,700);
		stage.setScene(scene);
		stage.show();
		root.setAlignment(Pos.TOP_CENTER);
		root.setStyle("-fx-background-color:white;");
		
		//creating title of page 
		title.setPadding(new Insets(10, 0, 50, 0));
		title.setTextFill(Color.web("#0076a3"));
		title.setStyle( "-fx-font-size:60px;"+"-fx-font-weight: bold;"+"-fx-font-family: \"Comic Sans MS\", cursive, sans-serif;");
		//description 
		descrip.setStyle("-fx-font-size:25px;"+ "-fx-padding-right:30px;");
		descrip.setTextAlignment(TextAlignment.CENTER);
		descrip.setPadding(new Insets(0,0,40,0));
		//yes/no buttons 
		yesno.setPadding(new Insets(0,0,0,140));
		yes.setMinWidth(100);
		no.setMinWidth(100);
		yes.setMinHeight(45);
		no.setMinHeight(45);
		yes.setStyle("-fx-font-size:30;"+"-fx-background-color:green;");
		no.setStyle("-fx-font-size:30;"+"-fx-background-color:red");
		yesno.getChildren().add(yes);
		yesno.getChildren().add(no);
		
		//if click yes then choose file 
		yes.setOnAction((ActionEvent e)->{
			descrip.setText("You're in for a treat!\n Please choose a file");
			yesno.getChildren().remove(no);
			yesno.getChildren().remove(yes);
			loadFile.setMinWidth(100);
			loadFile.setMinHeight(45);
			loadFile.setStyle("-fx-font-size:30;"+"-fx-background-color:#0076a3;");
			yesno.setPadding(new Insets(0,0,0,265));
			yesno.getChildren().add(loadFile);
		});
		
		//if click no then no quiz
		no.setOnAction((ActionEvent e)->{
			descrip.setText("AW MAN! It's truly sad to see you leave.\nGoodbye!");
			yes.setStyle("-fx-background-color:white;");
			no.setStyle("-fx-background-color:white;");
			yes.setText("");
			no.setText("");
			yes.setVisible(true);
			no.setVisible(true);
		});
		root.getChildren().add(title);
		root.getChildren().add(descrip);
		root.getChildren().add(yesno);
		
		//USER clicks load file button then filechooser opens up
		loadFile.setOnAction((ActionEvent e)->{
			file = fc.showOpenDialog(stage);
			fc.setTitle("Choose file to load");
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("FILE IS NOT FOUND");
			}
			//parse the 2 first words on top of file
			word1 = sc.nextLine();
			word2 = (word1.substring(word1.indexOf(",")+1, word1.length())).trim();
			word1 = (word1.substring( 0,word1.indexOf(","))).trim();
			//puts words in 2 arraylist for easy retrieval
			while(sc.hasNextLine()) {
				count++;
				wordList1 = sc.nextLine();
				wordList2 = (wordList1.substring(wordList1.indexOf(",")+1, wordList1.length())).trim();
				wordList1 = (wordList1.substring( 0,wordList1.indexOf(","))).trim();
				list1.add(wordList1);
				list2.add(wordList2);
			}
			yesno.getChildren().remove(loadFile);
			yesno.getChildren().add(startQuiz);
			descrip.setText("Let's Start This Quiz!");
			startQuiz.setMinWidth(100);
			startQuiz.setMinHeight(45);
			startQuiz.setStyle("-fx-font-size:30;"+"-fx-background-color:green;");
		});
		//starts the quiz when start button is clicked
		startQuiz.setOnAction((ActionEvent e)->{
			randOp = rand.nextInt(2);
			randnum = rand.nextInt(count);
			num = rand.nextInt(4);
			yesno.getChildren().remove(startQuiz);
			//setting up the 4 buttons in 2 HBOXES
			ans1.setMinWidth(100);
			ans2.setMinWidth(100);
			ans1.setMinHeight(45);
			ans2.setMinHeight(45);
			ans1.setStyle("-fx-font-size:15;");
			ans2.setStyle("-fx-font-size:15;");
			yesno.getChildren().add(ans1);
			yesno.getChildren().add(ans2);
			root.getChildren().add(secLevel);
			ans3.setMinWidth(100);
			ans4.setMinWidth(100);
			ans3.setMinHeight(45);
			ans4.setMinHeight(45);
			ans3.setStyle("-fx-font-size:15;");
			ans4.setStyle("-fx-font-size:15;");
			yesno.setPadding(new Insets(0,0,0,0));
			yesno.setAlignment(Pos.BOTTOM_CENTER);
			secLevel.setAlignment(Pos.BOTTOM_CENTER);
			//secLevel.setPadding(new Insets(0,0,0,255));
			secLevel.getChildren().add(ans3);
			secLevel.getChildren().add(ans4);
			descrip.setMinWidth(400);
			
			bottomPart.setPadding(new Insets(130,0,0,70));
			scoreLabel.setStyle("-fx-font-size:30px;");
			nextButt.setStyle("-fx-background-color:#CE7CF1;");
			nextButt.setMinHeight(50);
			nextButt.setVisible(false);
			//THIS IS THE NEXT QUESTION BUTTON 
			nextButt.setOnAction((ActionEvent ep)->{
				nextButt.setVisible(false);
				//COUNTER TO KNOW IF 5 questions have been asked then return to final page 
				quesCount++;
				ans1.setDisable(false);
				ans2.setDisable(false);
				ans3.setDisable(false);
				ans4.setDisable(false);
				ans1.setStyle("-fx-font-size:15;");
				ans2.setStyle("-fx-font-size:15;");
				ans3.setStyle("-fx-font-size:15;");
				ans4.setStyle("-fx-font-size:15;");
				
				if(quesCount<5) {
					randOp = rand.nextInt(2);
					randnum = rand.nextInt(count);
					num = rand.nextInt(4);
					System.out.println("NUM IS: "+ num);
					if(quesCount==4) {
						nextButt.setText("Quiz Over");
					}
					//randOP is a random number 0,1 to figire out which order to ask question (state then cap or captial then state )
					if(randOp==0) {
						descrip.setText("What is the "+ word2+" of the "+ word1 + ": "+list1.get(randnum));
						//num is random number to see which button to place the correct answer then everything else follows 
						if(num==0) {
							answer1 = randnum;
							ans1.setText(list2.get(answer1));
							randChoice = rand.nextInt(count);
							//while loops to ensure that nothing is repeated
							while(randChoice==answer1 ) {
								randChoice = rand.nextInt(count);
							}
							answer2 = randChoice;
							ans2.setText(list2.get(answer2));
							//ans2.setText(list2.get(randChoice));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 || randChoice==answer2) {
								randChoice = rand.nextInt(count);
							}
							answer3 = randChoice;
							ans3.setText(list2.get(answer3));
							while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
								randChoice = rand.nextInt(count);
							}
							answer4 = randChoice;
							ans4.setText(list2.get(answer4));
							
							//if the correct button is clicked it turns green if not it turns red then next question button appears
							ans1.setOnAction((ActionEvent epp)->{
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								score++;
								scoreLabel.setText("SCORE:\n"+Integer.toString(score));
								nextButt.setVisible(true);
							});
							ans2.setOnAction((ActionEvent epp)->{
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
								
								
							});
							ans3.setOnAction((ActionEvent epp)->{
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans4.setOnAction((ActionEvent epp)->{
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
						}else if(num==1) {
							answer1 = randnum;
							ans2.setText(list2.get(answer1));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 ) {
								randChoice = rand.nextInt(count);
							}
							answer2 = randChoice;
							ans3.setText(list2.get(answer2));
							//ans2.setText(list2.get(randChoice));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 || randChoice==answer2) {
								randChoice = rand.nextInt(count);
							}
							answer3 = randChoice;
							ans4.setText(list2.get(answer3));
							while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
								randChoice = rand.nextInt(count);
							}
							answer4 = randChoice;
							ans1.setText(list2.get(answer4));
							
							ans1.setOnAction((ActionEvent epp)->{
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans2.setOnAction((ActionEvent epp)->{
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
								ans2.setDisable(true);
								ans1.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
								score++;
								scoreLabel.setText("SCORE:\n"+Integer.toString(score));
								
							});
							ans3.setOnAction((ActionEvent epp)->{
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans4.setOnAction((ActionEvent epp)->{
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
						}else if(num==2) {
							answer1 = randnum;
							ans3.setText(list2.get(answer1));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 ) {
								randChoice = rand.nextInt(count);
							}
							answer2 = randChoice;
							ans4.setText(list2.get(answer2));
							//ans2.setText(list2.get(randChoice));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 || randChoice==answer2) {
								randChoice = rand.nextInt(count);
							}
							answer3 = randChoice;
							ans1.setText(list2.get(answer3));
							while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
								randChoice = rand.nextInt(count);
							}
							answer4 = randChoice;
							ans2.setText(list2.get(answer4));
							
							ans1.setOnAction((ActionEvent epp)->{
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans2.setOnAction((ActionEvent epp)->{
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
								
							});
							ans3.setOnAction((ActionEvent epp)->{
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans4.setDisable(true);
								ans3.setDisable(true);
								nextButt.setVisible(true);
								score++;
								scoreLabel.setText("SCORE:\n"+Integer.toString(score));
							});
							ans4.setOnAction((ActionEvent epp)->{
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
						}else if(num==3) {
							answer1 = randnum;
							ans4.setText(list2.get(answer1));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 ) {
								randChoice = rand.nextInt(count);
							}
							answer2 = randChoice;
							ans1.setText(list2.get(answer2));
							//ans2.setText(list2.get(randChoice));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 || randChoice==answer2) {
								randChoice = rand.nextInt(count);
							}
							answer3 = randChoice;
							ans2.setText(list2.get(answer3));
							while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
								randChoice = rand.nextInt(count);
							}
							answer4 = randChoice;
							ans3.setText(list2.get(answer4));
							
							ans1.setOnAction((ActionEvent epp)->{
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans2.setOnAction((ActionEvent epp)->{
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
								
							});
							ans3.setOnAction((ActionEvent epp)->{
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans4.setOnAction((ActionEvent epp)->{
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
								score++;
								scoreLabel.setText("SCORE:\n"+Integer.toString(score));
							});
							
							
						}
						
					}
					//same as above 
					else if(randOp==1) {
						descrip.setText("What is the "+ word1+" of the "+ word2 + ": "+list2.get(randnum));
						if(num==0) {
							answer1 = randnum;
							ans1.setText(list1.get(answer1));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 ) {
								randChoice = rand.nextInt(count);
							}
							answer2 = randChoice;
							ans2.setText(list1.get(answer2));
							//ans2.setText(list2.get(randChoice));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 || randChoice==answer2) {
								randChoice = rand.nextInt(count);
							}
							answer3 = randChoice;
							ans3.setText(list1.get(answer3));
							while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
								randChoice = rand.nextInt(count);
							}
							answer4 = randChoice;
							ans4.setText(list1.get(answer4));
							
							ans1.setOnAction((ActionEvent epp)->{
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								score++;
								scoreLabel.setText("SCORE:\n"+Integer.toString(score));
								nextButt.setVisible(true);
							});
							ans2.setOnAction((ActionEvent epp)->{
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
								
								
							});
							ans3.setOnAction((ActionEvent epp)->{
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans4.setOnAction((ActionEvent epp)->{
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							
						}else if(num==1) {
							answer1 = randnum;
							ans2.setText(list1.get(answer1));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 ) {
								randChoice = rand.nextInt(count);
							}
							answer2 = randChoice;
							ans3.setText(list1.get(answer2));
							//ans2.setText(list2.get(randChoice));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 || randChoice==answer2) {
								randChoice = rand.nextInt(count);
							}
							answer3 = randChoice;
							ans4.setText(list1.get(answer3));
							while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
								randChoice = rand.nextInt(count);
							}
							answer4 = randChoice;
							ans1.setText(list1.get(answer4));
							
							ans1.setOnAction((ActionEvent epp)->{
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans2.setOnAction((ActionEvent epp)->{
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
								ans2.setDisable(true);
								ans1.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
								score++;
								scoreLabel.setText("SCORE:\n"+Integer.toString(score));
								
							});
							ans3.setOnAction((ActionEvent epp)->{
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans4.setOnAction((ActionEvent epp)->{
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							
						}else if(num==2) {
							answer1 = randnum;
							ans3.setText(list1.get(answer1));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 ) {
								randChoice = rand.nextInt(count);
							}
							answer2 = randChoice;
							ans4.setText(list1.get(answer2));
							//ans2.setText(list2.get(randChoice));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 || randChoice==answer2) {
								randChoice = rand.nextInt(count);
							}
							answer3 = randChoice;
							ans1.setText(list1.get(answer3));
							while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
								randChoice = rand.nextInt(count);
							}
							answer4 = randChoice;
							ans2.setText(list1.get(answer4));
							
							ans1.setOnAction((ActionEvent epp)->{
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans2.setOnAction((ActionEvent epp)->{
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
								
							});
							ans3.setOnAction((ActionEvent epp)->{
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans4.setDisable(true);
								ans3.setDisable(true);
								nextButt.setVisible(true);
								score++;
								scoreLabel.setText("SCORE:\n"+Integer.toString(score));
							});
							ans4.setOnAction((ActionEvent epp)->{
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							
						}else if(num==3) {
							answer1 = randnum;
							ans4.setText(list1.get(answer1));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 ) {
								randChoice = rand.nextInt(count);
							}
							answer2 = randChoice;
							ans1.setText(list1.get(answer2));
							//ans2.setText(list2.get(randChoice));
							randChoice = rand.nextInt(count);
							while(randChoice==answer1 || randChoice==answer2) {
								randChoice = rand.nextInt(count);
							}
							answer3 = randChoice;
							ans2.setText(list1.get(answer3));
							while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
								randChoice = rand.nextInt(count);
							}
							answer4 = randChoice;
							ans3.setText(list1.get(answer4));
							
							ans1.setOnAction((ActionEvent epp)->{
								ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans2.setOnAction((ActionEvent epp)->{
								ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
								
							});
							ans3.setOnAction((ActionEvent epp)->{
								ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
							});
							ans4.setOnAction((ActionEvent epp)->{
								ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
								ans1.setDisable(true);
								ans2.setDisable(true);
								ans3.setDisable(true);
								ans4.setDisable(true);
								nextButt.setVisible(true);
								score++;
								scoreLabel.setText("SCORE:\n"+Integer.toString(score));
							});
							
							
						}
					}
					
				}
				//after 5 questions have been asked then present score and thank you message to user
				else {
					root.getChildren().remove(bottomPart);
					root.getChildren().remove(secLevel);
					root.getChildren().remove(yesno);
					descrip.setText("Your Score is\n"+ score+"\nThank you so much for partaking in Quizzle!\nGoodBye!");
				}
				
			});
			
			//same as above 
			bottomPart.getChildren().add(scoreLabel);
			bottomPart.getChildren().add(nextButt);
			root.getChildren().add(bottomPart);
			nextButt.setVisible(false);
			if(randOp==0) {
				descrip.setText("What is the "+ word2+" of the "+ word1 + ": "+list1.get(randnum));
				if(num==0) {
					answer1 = randnum;
					ans1.setText(list2.get(answer1));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 ) {
						randChoice = rand.nextInt(count);
					}
					answer2 = randChoice;
					ans2.setText(list2.get(answer2));
					//ans2.setText(list2.get(randChoice));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 || randChoice==answer2) {
						randChoice = rand.nextInt(count);
					}
					answer3 = randChoice;
					ans3.setText(list2.get(answer3));
					while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
						randChoice = rand.nextInt(count);
					}
					answer4 = randChoice;
					ans4.setText(list2.get(answer4));
					
					ans1.setOnAction((ActionEvent epp)->{
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						score++;
						scoreLabel.setText("SCORE:\n"+Integer.toString(score));
						nextButt.setVisible(true);
					});
					ans2.setOnAction((ActionEvent epp)->{
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
						
						
					});
					ans3.setOnAction((ActionEvent epp)->{
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans4.setOnAction((ActionEvent epp)->{
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
				}else if(num==1) {
					answer1 = randnum;
					ans2.setText(list2.get(answer1));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 ) {
						randChoice = rand.nextInt(count);
					}
					answer2 = randChoice;
					ans3.setText(list2.get(answer2));
					//ans2.setText(list2.get(randChoice));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 || randChoice==answer2) {
						randChoice = rand.nextInt(count);
					}
					answer3 = randChoice;
					ans4.setText(list2.get(answer3));
					while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
						randChoice = rand.nextInt(count);
					}
					answer4 = randChoice;
					ans1.setText(list2.get(answer4));
					
					ans1.setOnAction((ActionEvent epp)->{
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans2.setOnAction((ActionEvent epp)->{
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
						ans2.setDisable(true);
						ans1.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
						score++;
						scoreLabel.setText("SCORE:\n"+Integer.toString(score));
						
					});
					ans3.setOnAction((ActionEvent epp)->{
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans4.setOnAction((ActionEvent epp)->{
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
				}else if(num==2) {
					answer1 = randnum;
					ans3.setText(list2.get(answer1));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 ) {
						randChoice = rand.nextInt(count);
					}
					answer2 = randChoice;
					ans4.setText(list2.get(answer2));
					//ans2.setText(list2.get(randChoice));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 || randChoice==answer2) {
						randChoice = rand.nextInt(count);
					}
					answer3 = randChoice;
					ans1.setText(list2.get(answer3));
					while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
						randChoice = rand.nextInt(count);
					}
					answer4 = randChoice;
					ans2.setText(list2.get(answer4));
					
					ans1.setOnAction((ActionEvent epp)->{
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans2.setOnAction((ActionEvent epp)->{
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
						
					});
					ans3.setOnAction((ActionEvent epp)->{
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans4.setDisable(true);
						ans3.setDisable(true);
						nextButt.setVisible(true);
						score++;
						scoreLabel.setText("SCORE:\n"+Integer.toString(score));
					});
					ans4.setOnAction((ActionEvent epp)->{
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
				}else if(num==3) {
					answer1 = randnum;
					ans4.setText(list2.get(answer1));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 ) {
						randChoice = rand.nextInt(count);
					}
					answer2 = randChoice;
					ans1.setText(list2.get(answer2));
					//ans2.setText(list2.get(randChoice));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 || randChoice==answer2) {
						randChoice = rand.nextInt(count);
					}
					answer3 = randChoice;
					ans2.setText(list2.get(answer3));
					while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
						randChoice = rand.nextInt(count);
					}
					answer4 = randChoice;
					ans3.setText(list2.get(answer4));
					
					ans1.setOnAction((ActionEvent epp)->{
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans2.setOnAction((ActionEvent epp)->{
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
						
					});
					ans3.setOnAction((ActionEvent epp)->{
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans4.setOnAction((ActionEvent epp)->{
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
						score++;
						scoreLabel.setText("SCORE:\n"+Integer.toString(score));
					});
					
					
				}
				//nextButt.setVisible(true);
				//quesCount++;
				
			}
			else if(randOp==1) {
				descrip.setText("What is the "+ word1+" of the "+ word2 + ": "+list2.get(randnum));
				if(num==0) {
					answer1 = randnum;
					ans1.setText(list1.get(answer1));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 ) {
						randChoice = rand.nextInt(count);
					}
					answer2 = randChoice;
					ans2.setText(list1.get(answer2));
					//ans2.setText(list2.get(randChoice));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 || randChoice==answer2) {
						randChoice = rand.nextInt(count);
					}
					answer3 = randChoice;
					ans3.setText(list1.get(answer3));
					while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
						randChoice = rand.nextInt(count);
					}
					answer4 = randChoice;
					ans4.setText(list1.get(answer4));
					
					ans1.setOnAction((ActionEvent epp)->{
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						score++;
						scoreLabel.setText("SCORE:\n"+Integer.toString(score));
						nextButt.setVisible(true);
					});
					ans2.setOnAction((ActionEvent epp)->{
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
						
						
					});
					ans3.setOnAction((ActionEvent epp)->{
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans4.setOnAction((ActionEvent epp)->{
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					
				}else if(num==1) {
					answer1 = randnum;
					ans2.setText(list1.get(answer1));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 ) {
						randChoice = rand.nextInt(count);
					}
					answer2 = randChoice;
					ans3.setText(list1.get(answer2));
					//ans2.setText(list2.get(randChoice));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 || randChoice==answer2) {
						randChoice = rand.nextInt(count);
					}
					answer3 = randChoice;
					ans4.setText(list1.get(answer3));
					while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
						randChoice = rand.nextInt(count);
					}
					answer4 = randChoice;
					ans1.setText(list1.get(answer4));
					
					ans1.setOnAction((ActionEvent epp)->{
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans2.setOnAction((ActionEvent epp)->{
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
						ans2.setDisable(true);
						ans1.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
						score++;
						scoreLabel.setText("SCORE:\n"+Integer.toString(score));
						
					});
					ans3.setOnAction((ActionEvent epp)->{
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans4.setOnAction((ActionEvent epp)->{
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					
				}else if(num==2) {
					answer1 = randnum;
					ans3.setText(list1.get(answer1));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 ) {
						randChoice = rand.nextInt(count);
					}
					answer2 = randChoice;
					ans4.setText(list1.get(answer2));
					//ans2.setText(list2.get(randChoice));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 || randChoice==answer2) {
						randChoice = rand.nextInt(count);
					}
					answer3 = randChoice;
					ans1.setText(list1.get(answer3));
					while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
						randChoice = rand.nextInt(count);
					}
					answer4 = randChoice;
					ans2.setText(list1.get(answer4));
					
					ans1.setOnAction((ActionEvent epp)->{
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans2.setOnAction((ActionEvent epp)->{
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
						
					});
					ans3.setOnAction((ActionEvent epp)->{
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans4.setDisable(true);
						ans3.setDisable(true);
						nextButt.setVisible(true);
						score++;
						scoreLabel.setText("SCORE:\n"+Integer.toString(score));
					});
					ans4.setOnAction((ActionEvent epp)->{
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					
				}else if(num==3) {
					answer1 = randnum;
					ans4.setText(list1.get(answer1));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 ) {
						randChoice = rand.nextInt(count);
					}
					answer2 = randChoice;
					ans1.setText(list1.get(answer2));
					//ans2.setText(list2.get(randChoice));
					randChoice = rand.nextInt(count);
					while(randChoice==answer1 || randChoice==answer2) {
						randChoice = rand.nextInt(count);
					}
					answer3 = randChoice;
					ans2.setText(list1.get(answer3));
					while(randChoice==answer1 || randChoice==answer2 || randChoice==answer3){
						randChoice = rand.nextInt(count);
					}
					answer4 = randChoice;
					ans3.setText(list1.get(answer4));
					
					ans1.setOnAction((ActionEvent epp)->{
						ans1.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans2.setOnAction((ActionEvent epp)->{
						ans2.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
						
					});
					ans3.setOnAction((ActionEvent epp)->{
						ans3.setStyle("-fx-font-size:15;"+"-fx-background-color:red;");
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:#0076a3;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
					});
					ans4.setOnAction((ActionEvent epp)->{
						ans4.setStyle("-fx-font-size:15;"+"-fx-background-color:green;");
						ans1.setDisable(true);
						ans2.setDisable(true);
						ans3.setDisable(true);
						ans4.setDisable(true);
						nextButt.setVisible(true);
						score++;
						scoreLabel.setText("SCORE:\n"+Integer.toString(score));
					});
					
					
				}
			}
		});
		
	}

}
