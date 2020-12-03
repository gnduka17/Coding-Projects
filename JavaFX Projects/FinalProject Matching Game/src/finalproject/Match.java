package finalproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Match extends Application {
	//declaring variables
	Pane root;
	StackPane cards;
	Rectangle table;
	Text directions;
	Text warning;
	Random rand;
	Rectangle border;
	Integer timeSeconds;
	boolean color = false;
	Timeline tl;
	Label winlose = new Label("");
	int timeitem;
	boolean playon = false;
	VBox tHolder = new VBox();
	Text tInstr = new Text("Cards will flip back in...");
	int count = 0;
	int clickCount = 0;
	int numCards = 18;
	int randCount = 18;
	Integer timernum = 50;
	int matches = 0;
	ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Food",
		        "Animals",
		        "Colors"
		    );
	ComboBox imageItem = new ComboBox(options);
	
	ObservableList<String> timeroptions = 
		    FXCollections.observableArrayList(
		        "30",
		        "40",
		        "50",
		        "60",
		        "70"
		    );
	ComboBox timerItem = new ComboBox(timeroptions);
	VBox pointBox = new VBox();
	String optionitem;
	
	Label pointLabel = new Label("Points: ");
	Integer pointsNum = 0;
	Label points = new Label("    "+pointsNum.toString());
	Label timer = new Label();
	Integer timerNum = 6;
	ArrayList <Card> testing = new ArrayList<Card>();
	ArrayList <Image> imageNum = new ArrayList<Image>();
	ArrayList<String> imageurl = new ArrayList<String>();
	ArrayList<String> imageAniurl = new ArrayList<String>();
	ArrayList<String> imageColorurl = new ArrayList<String>();
	ArrayList<String> imageColorlist = new ArrayList<String>();
	ArrayList<String> matchURL = new ArrayList<String>();
	ArrayList<Card>matchURLCard = new ArrayList<Card>();
	Label title = new Label("Match it Up");
	Button play = new Button("Let's Do It");
	public static void main( String[] args )
	{ 
		
		launch(args);}
    @Override
    public void start(Stage stage) 
    { initUI(stage); }

    private void initUI(Stage stage)
    {
    	
    	stage.setTitle("Match it Up");
    	//initializing table, root, warning, and directions
		root = new Pane();
		cards = new StackPane();
		table = new Rectangle(7,241,1385,600);
		warning = new Text("***Warning: For the play button to light up, ALL cards must\nbe on the \"table.\" Select your picture of choice and your time\nframe from the dropdown menus.");
		directions = new Text("\t\t\t\t\t    Welcome to Match It Up!!!!\n~Drag all the cards from the right onto the \"table.\"\n~Press the play "
				+ "button once all the cards are properly placed onto the table.\n~You have 5 seconds to "
				+ "see where the cards are located until they are flipped \n    over.\n~Once the timer begins, "
				+ "find as many pairs as you can.\n\t\t\t\t\t\t          Goodluck!!");
		border = new Rectangle(442.0,70,511,120);
		Scene scene = new Scene(root, 1400, 1000,true,SceneAntialiasing.BALANCED);
		scene.setCamera(new PerspectiveCamera());
		//using CSS Stylesheet
		scene.getStylesheets().add("styleSheet.css");
		border.getStyleClass().add("border");
		table.getStyleClass().add("tablecolor");
		root.getStyleClass().add("wholePane");
		stage.setScene(scene);
		stage.show();
	
		addToAniList();
		addToFoodList();
		
		pointBox.getChildren().add(pointLabel);
		pointBox.getChildren().add(points);
		pointBox.setLayoutX(1117.0);
		pointBox.setLayoutY(40);
		
		//iniitalizing drop down menu
		 EventHandler<ActionEvent> event = 
                 new EventHandler<ActionEvent>() { 
           public void handle(ActionEvent e) 
           { 
        	   optionitem = (String) imageItem.getValue();
        	   randCount = 18;
        	   rand = new Random();
        	   if(optionitem.equals("Food")) {
        		   imageurl.clear();
        		   addToFoodList();
        		   for(int i = 0; i<testing.size();i++) {
        			   int randNum = rand.nextInt(randCount);
        			   testing.get(i).imageURL=imageurl.get(randNum);
        			   imageurl.remove(randNum);
        			   randCount--;
        		   }
        		   
        	   }
        	   else if(optionitem.equals("Animals")) {
        		   imageAniurl.clear();
        		   addToAniList();
        		   System.out.println("anisize "+ imageAniurl.size());
        		   System.out.println("randcount out for: "+ randCount);
        		   for(int i = 0; i<testing.size();i++) {
        			   System.out.println("randcount in for: "+ randCount);
        			   int randNum = rand.nextInt(randCount);
        			   System.out.println("randnum in for: "+ randNum);
        			   testing.get(i).imageURL=imageAniurl.get(randNum);
        			   imageAniurl.remove(randNum);
        			   randCount--;
        		   }   
        	   }
        	   else {
        		   color = true;
        		   imageColorlist.clear();
        		   addToColorList();
        		   String red = "red;";
        		   for(int i = 0; i<testing.size();i++) {
        			   testing.get(i).imageURL="transp.png";
        		   }
        	   }
           } 
       }; 
       EventHandler<ActionEvent> eventtime = 
               new EventHandler<ActionEvent>() { 
         public void handle(ActionEvent e) 
         { 
      	   timeitem = Integer.parseInt((String) timerItem.getValue());
      	   timernum = timeitem;
         } 
     }; 
		
		imageItem.setOnAction(event);
		timerItem.setOnAction(eventtime);
		
		//layout of nodes
		imageItem.setStyle("-fx-background-color:#6fb98f;");
		timerItem.setStyle("-fx-background-color:#6fb98f;");
		imageItem.setLayoutX(10);
		imageItem.setLayoutY(209.0);
		imageItem.getSelectionModel().selectFirst();
		timerItem.setLayoutX(110);
		timerItem.setLayoutY(209.0);
		timerItem.getSelectionModel().select(2);
		timeitem = Integer.parseInt((String) timerItem.getValue());
		optionitem = (String) imageItem.getValue();
		tHolder.getChildren().add(tInstr);
		tHolder.getChildren().add(timer);
		tInstr.setStyle("-fx-font-size:30px;");
		timer.setText(timerNum.toString());
		tHolder.setLayoutX(37.0);
		tHolder.setLayoutY(44.0);
		timer.setTextFill(Color.RED);
	    timer.setStyle("-fx-font-size: 4em;");
	    timer.setPadding(new Insets(0,0,0,120));
		title.setLayoutX(564.0);
		title.setLayoutY(-5);
		title.getStyleClass().add("fonttext");
    	root.getChildren().add(title);
		warning.setLayoutX(207.0);
		warning.setLayoutY(206.0);
		directions.setLayoutX(452.0);
		directions.setLayoutY(85.0);
		play.setLayoutX(641.0);
		play.setLayoutY(205.0);
		play.setPrefWidth(118);
		play.setDisable(true);
		//add to root
		root.getChildren().add(table);
		root.getChildren().add(border);
		root.getChildren().add(directions);
		root.getChildren().add(warning);
		root.getChildren().add(play);
		root.getChildren().add(pointBox);
		root.getChildren().add(imageItem);
		root.getChildren().add(timerItem);
		optionitem = (String) imageItem.getValue();
		timeitem = Integer.parseInt((String) timerItem.getValue());
		
		//if play button selected, start timer, show cards, then flip cards, begin game timer
		play.setOnAction((ActionEvent e)->{
			randCount = 18;
     	   rand = new Random();
			playon = true;
			if(color) {
				imageColorlist.clear();
     		   addToColorList();
     		   for(int i = 0; i<testing.size();i++) {
     			   int randNum = rand.nextInt(randCount);
     			   testing.get(i).col=imageColorlist.get(randNum);
     			   testing.get(i).r.setStyle("-fx-fill:"+imageColorlist.get(randNum) );
     			   imageColorlist.remove(randNum);
     			   randCount--;
     		   }
				
			}
			play.setDisable(true);
			imageItem.setDisable(true);
			timerItem.setDisable(true);
			optionitem = (String) imageItem.getValue();
			
			timeitem = Integer.parseInt((String) timerItem.getValue());
			showCards();
			
			root.getChildren().add(tHolder);
			displayTimer(timerNum,timer);
			
		});
		
		//initializing playing cards
		for(int i = 0; i<numCards;i++) {
			rand = new Random();
	    	int randNum = rand.nextInt(randCount);
	    	Card r = new Card(i, imageurl.get(randNum), "");
			imageurl.remove(randNum);
			testing.add(r);
			r.addToRoot();
			randCount--;
		}
    	
    }
    //display timer for 5 seconds then flip card 
    public void displayTimer(int time, Label l) {
    	play.setDisable(true);
    	timeSeconds = time;
    	if (tl != null) {
            tl.stop();
        }
        // update timerLabel
        l.setText(timeSeconds.toString());
        tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(1),(ActionEvent e)->{
    		timeSeconds--;
            // update timerLabel
            l.setText(
                  timeSeconds.toString());
            if (timeSeconds <= 0) {
                tl.stop();
                play.setDisable(true);
                dontShowCards();
                tInstr.setText("\t  Time Left:");
                
                timer.setText(timernum.toString());
                displayGameTimer(timernum,timer);
                
            }
    		
    	}));
        tl.playFromStart();
                
    	
    }
    //displays timer for given seconds - gamer timer
    public void displayGameTimer(int time, Label l) {
    	timeSeconds = time;
    	if (tl != null) {
            tl.stop();
        }
        // update timerLabel
        l.setText(timeSeconds.toString());
        tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(1),(ActionEvent e)->{
    		timeSeconds--;
            // update timerLabel
            l.setText(
                  timeSeconds.toString());
            //declares if winner or loser
            if (timeSeconds <= 0) {
                tl.stop();
                tInstr.setText("\t Time's Up!!");
                tInstr.setStyle("-fx-font-size:40px;"+ "-fx-color:red;");
                if(pointsNum==numCards/2) {
                	System.out.println("YOU WON!");
                	winlose.setText("YOU WON");
                	winlose.setLayoutX(156);
					winlose.setLayoutY(384);
                	winlose.setTextFill(Color.DARKGREEN);
                	winlose.setStyle("-fx-fill:green;"+ "-fx-font-size: 200px;");
                	root.getChildren().add(winlose);
                }
                else {
                	System.out.println("YOU LOST!!!");
                	winlose.setText("YOU LOST:(");
                	winlose.setLayoutX(141);
					winlose.setLayoutY(384);
                	winlose.setTextFill(Color.DARKRED);
                	winlose.setStyle("-fx-fill:red;"+ "-fx-font-size: 200px;");
                	root.getChildren().add(winlose);
                	
                }  
            }
    		
    	}));
        tl.playFromStart();
                
    	
    }
    //function to show cards images
    public void showCards() {
    	try {
	    	for(int i=0;i<testing.size();i++) {
	    		testing.get(i).logoword.setText("");
	    		Image logo = new Image(new FileInputStream(testing.get(i).imageURL));
	    		ImageView imageView = new ImageView(logo);
	    		imageView.setStyle("-fx-background-color:transparent;");
	    		imageView.setFitWidth(140);
	    		imageView.setFitHeight(180);
    			testing.get(i).holder.getChildren().remove(2);
    			testing.get(i).holder.getChildren().add(imageView);
	    		if(color) {
	    			testing.get(i).r.setStyle("-fx-fill:"+testing.get(i).col);
//	    			testing.get(i).r.setStyle("-fx-fill:#6fb98f");
	    		}
	    		
    			
	    		
	    	}
    	}catch(FileNotFoundException e) {
    		System.out.println("File not found!!!!");
    	}
    	
    	
    }
    //function to flip card with image not shown
    public void dontShowCards() {
    	try {
    		for(int i=0;i<testing.size();i++) {
	    		testing.get(i).logoword.setText("\n\nMatch it Up");
	    		Image logo = new Image(new FileInputStream("playingCards.png"));
	    		ImageView imageView = new ImageView(logo);
	    		imageView.setX(13);
    			imageView.setY(6);
    			imageView.setFitWidth(60);
    			//Setting the preserve ratio of the image view
    			imageView.setPreserveRatio(true); 
    			StackPane.setAlignment(testing.get(i).logoword, Pos.TOP_CENTER);
//    			testing.get(i).r.setStyle("-fx-fill:#6fb98f");
				testing.get(i).holder.getChildren().remove(2);
    			testing.get(i).holder.getChildren().add(imageView);
    			if(color) {
    				testing.get(i).r.setStyle("-fx-fill:#6fb98f");
    				
    			}
    			
    		}
    		
    	}catch(FileNotFoundException e) {
    		System.out.println("FILE NOT FOUND!!");
    		
    	}
    }
    //not used tried to implement flipping animation
    private RotateTransition createRotator(StackPane card) {
        RotateTransition rotator = new RotateTransition(Duration.millis(10000), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(360);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);

        return rotator;
    }
    //adding to list for animals 
    public void addToAniList() {
    	imageAniurl.add("bird.png");
		imageAniurl.add("cat.png");
		imageAniurl.add("doggy.png");
		imageAniurl.add("elephant.png");
		imageAniurl.add("giraffe.png");
		imageAniurl.add("kangaroo.png");
		imageAniurl.add("monkey.png");
		imageAniurl.add("zebra.png");
		imageAniurl.add("shark.png");
		imageAniurl.add("bird.png");
		imageAniurl.add("cat.png");
		imageAniurl.add("doggy.png");
		imageAniurl.add("elephant.png");
		imageAniurl.add("giraffe.png");
		imageAniurl.add("kangaroo.png");
		imageAniurl.add("monkey.png");
		imageAniurl.add("zebra.png");
		imageAniurl.add("shark.png");
    	
    }
    //adding to list for food
    public void addToFoodList() {
    	imageurl.add("hotdog.png");
		imageurl.add("nachos.png");
		imageurl.add("pancake.png");
		imageurl.add("pizza.png");
		imageurl.add("salad.png");
		imageurl.add("sandwich.png");
		imageurl.add("spaghetti.png");
		imageurl.add("sushi.png");
		imageurl.add("tacos.png");
		imageurl.add("hotdog.png");
		imageurl.add("nachos.png");
		imageurl.add("pancake.png");
		imageurl.add("pizza.png");
		imageurl.add("salad.png");
		imageurl.add("sandwich.png");
		imageurl.add("spaghetti.png");
		imageurl.add("sushi.png");
		imageurl.add("tacos.png");
    	
    }
    //adding to list for colors
    public void addToColorList() {
       	imageColorlist.add("red;");
       	imageColorlist.add("orange;");
       	imageColorlist.add("yellow;");
       	imageColorlist.add("green;");
       	imageColorlist.add("blue;");
       	imageColorlist.add("purple;");
       	imageColorlist.add("black;");
       	imageColorlist.add("white;");
       	imageColorlist.add("grey;");
    	imageColorlist.add("red;");
       	imageColorlist.add("orange;");
       	imageColorlist.add("yellow;");
       	imageColorlist.add("green;");
       	imageColorlist.add("blue;");
       	imageColorlist.add("purple;");
       	imageColorlist.add("black;");
       	imageColorlist.add("white;");
       	imageColorlist.add("grey;");
   
	
    }
    //card class
    public class Card extends Pane{
    	//variables
    	Rectangle r;
    	Card hold = this;
    	StackPane holder;
    	boolean it = false;
    	int cardId;
    	String imageURL;
    	boolean chosen = false;
    	boolean onTable = false;
    	boolean entered = false;
    	String col;
		ImageView imageView;
    	Text logoword = new Text("\n\nMatch it Up");
    	public Card(int id, String url, String colorc) {
    		try {
    			col = colorc;
    			Image logo = new Image(new FileInputStream("playingCards.png"));
    			imageView = new ImageView(logo);
    			imageView.setX(13);
    			imageView.setY(6);
    			imageView.setFitWidth(60);
    			//Setting the preserve ratio of the image view
    			imageView.setPreserveRatio(true); 
    			holder = new StackPane();
    			r=new Rectangle(140,180);
    			r.setArcHeight(25);
    		    r.setArcWidth(25);
    		    
    		    holder.setStyle("-fx-border-radius: 25 25 0 0;"+ "-fx-background-radius: 25 25 25 25;");
    			holder.setPrefWidth(140);
    			holder.setPrefHeight(180);
        		r.getStyleClass().add("card");
        		holder.setLayoutX(13);
        		holder.setLayoutY(6);
        		StackPane.setAlignment(logoword, Pos.TOP_CENTER);
        		holder.getChildren().addAll(r,logoword,imageView);
        		r.getStyleClass().add("cardColor");
        		cardId = id;
        		imageURL = url;
        		//highlight when hovering card
    			holder.setOnMouseEntered(new EventHandler<MouseEvent>() {
        			public void handle(MouseEvent t) {
        				if(!entered) {
        					r.getStyleClass().add("strokeColorInside");
        					entered = true;
        				}
        			}
        		});
    			holder.setOnMouseExited(new EventHandler<MouseEvent>() {
        			public void handle(MouseEvent t) {
        				if(entered) {
        					r.getStyleClass().add("strokeColorOutside");
        					entered = false;
        				}
        			}
        		});
    			//if play time, then once card is clicked, flip card
    			holder.setOnMouseClicked(new EventHandler<MouseEvent>() {
        			public void handle(MouseEvent t) {
//        				play.setDisable(true);
        				if(chosen) {
        					
        				}
        				else if(playon) {
        					play.setDisable(true);
        					if(clickCount==1) {
        						try {
        							if(matchURLCard.get(0).cardId==hold.cardId) {
        								
        								
        							}
        							else {
        								clickCount++;
    									flipCard();
    									matchURL.add(imageURL);
    									matchURLCard.add(hold);
    									//check if same card
    	        						if(!color) {
    	        							if(matchURL.get(0).equals(matchURL.get(1))){
    	            							pointsNum++;
    	            							matches++;
    	            							matchURLCard.get(0).chosen = true;
    	            							matchURLCard.get(1).chosen = true;
    	            							if(matches==numCards/2) {
    	            								tl.stop();
    	            								System.out.println("YOU WON!!!!!!");
    	            								winlose.setLayoutX(156);
    	            								winlose.setLayoutY(384);
    	            								
    	            								winlose.setText("YOU WON!");
    	            								winlose.setAlignment(Pos.CENTER);
    	            								winlose.setTextFill(Color.DARKGREEN);
    	            			                	winlose.setStyle("-fx-color:green;"+ "-fx-font-size: 200px;");
    	            			                	root.getChildren().add(winlose);
    	            							}
    	            							points.setText("    "+pointsNum.toString());
    	            							clickCount = 0;
    	                						matchURL.clear();
    	                						matchURLCard.clear();	
    	            						}	
    	        						}
    	        						else {
    	        							if(matchURLCard.get(0).col.equals(matchURLCard.get(1).col)){
    	            							pointsNum++;
    	            							matches++;
    	            							matchURLCard.get(0).chosen = true;
    	            							matchURLCard.get(1).chosen = true;
    	            							if(matches==numCards/2) {
    	            								tl.stop();
    	            								System.out.println("YOU WON!!!!!!");
    	            								winlose.setLayoutX(156);
    	            								winlose.setLayoutY(384);
    	            								
    	            								winlose.setText("YOU WON!");
    	            								winlose.setAlignment(Pos.CENTER);
    	            								winlose.setTextFill(Color.DARKGREEN);
    	            			                	winlose.setStyle("-fx-color:green;"+ "-fx-font-size: 200px;");
    	            			                	root.getChildren().add(winlose);
    	            							}
    	            							points.setText("    "+pointsNum.toString());
    	            							clickCount = 0;
    	                						matchURL.clear();
    	                						matchURLCard.clear();	
    	            						}	
    	        						}
        								
        							}
        							
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
        						
        				
        						
        						
        					}
        					else {
        						if(clickCount==2) {
        							if(!color) {
        								if(!matchURL.get(0).equals(matchURL.get(1))) {
            								flipBack(matchURLCard.get(0), matchURLCard.get(1));
            								clickCount = 0;
                    						matchURL.clear();
                    						matchURLCard.clear();
            							}
        							}
        							else {
        								if(!matchURLCard.get(0).col.equals(matchURLCard.get(1).col)) {
            								flipBack(matchURLCard.get(0), matchURLCard.get(1));
            								clickCount = 0;
                    						matchURL.clear();
                    						matchURLCard.clear();
            							}
        								
        							}
        							
        						}
        						clickCount++;
        						try {
									flipCard();
									it=true;
									matchURL.add(imageURL);
									matchURLCard.add(hold);
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
        					}
        				}	
        			}
        		});
    			//drag card to table; must be properly placed on table
    			holder.setOnMouseDragged(new EventHandler<MouseEvent>() {
        			public void handle(MouseEvent t) {
//        				play.setDisable(true);
        				if(!playon) {
        					ArrayList<Double> listx = new ArrayList<Double>();
            				ArrayList<Double> listy = new ArrayList<Double>();
            				listx.add(t.getSceneX());
            				listy.add(t.getSceneY());
            				holder.setLayoutX(t.getSceneX());
            				holder.setLayoutY(t.getSceneY());
            				holder.setOnMouseReleased(event->{
            					if(onTable(listx.get(listx.size()-1),listy.get(listy.size()-1),r.getHeight(),r.getWidth())) {
                					onTable = true;
                					testing.get(cardId).onTable = true;
                					count++;
                				}
                				else {
                					onTable = false;
                					testing.get(cardId).onTable = false;
                					count--;
                				
                				}
            					if(allOnTable(testing)) {
            						//display dropdown for animals and color
            						//display dropdown for timer
            						optionitem = (String) imageItem.getValue();
            						timeitem = Integer.parseInt((String) timerItem.getValue());
            						
            						play.setDisable(false);
            						warning.setText("");
            						
            					}
            					else {
            						play.setDisable(true);
            						warning.setText("***Warning: For the play button to light up, ALL cards must\nbe "
            								+ "on the \"table.\" Select your picture of choice and your time\nframe from the dropdown menus.");
            					}
            				});		
        				}
        			}
        		});
    		}
    		catch(FileNotFoundException e) {
    			System.out.println("FILE IS NOT FOUND!!!!");
    		}
    	}
    	//adds stackpane to root
    	public void addToRoot() {
    		root.getChildren().add(holder);
    	}
    	//returns true if all cards properly placed on table
    	public boolean allOnTable(ArrayList<Card> hold) {
    		int count = 0;
    		for(int i =0; i<hold.size();i++) {
    			if(hold.get(i).onTable) {
    				count++;
    			}
    		}
    		if(count==numCards) {
    			return true;
    		}
    		return false;
    	}
    	//returns true if card on table
    	public boolean onTable(double x, double y, double height, double width ) {
    		if(x<7 ||x>1392) {
    			return false;
    		}
    		if(y<200 ||y>841) {
    			return false;
    		}
    		if(x+width<1392 && x+width>7) {
    			if(y+height < 841 && y+height >200) {
    				return true;
    			}
    		}
			return false;
    	}
    	//flips this card
    	public void flipCard() throws FileNotFoundException {
    		logoword.setText("");
    		Image logo = new Image(new FileInputStream(imageURL));
    		ImageView imageView = new ImageView(logo);
    		imageView.setStyle("-fx-background-color:transparent;");
    		imageView.setFitWidth(140);
    		imageView.setFitHeight(180);
			holder.getChildren().remove(2);
			holder.getChildren().add(imageView);
			if(color) {
				r.setStyle("-fx-fill:"+col);
			}
    		
    		
    	}
    	//flips back this card
    	public void flipBack(Card a, Card b) {
    		try {
    			a.logoword.setText("\n\nMatch it Up");
        		Image logo = new Image(new FileInputStream("playingCards.png"));
        		ImageView imageView = new ImageView(logo);
    			imageView.setFitWidth(60);
    			//Setting the preserve ratio of the image view
    			imageView.setPreserveRatio(true); 
    			StackPane.setAlignment(a.logoword, Pos.TOP_CENTER);
    			a.holder.getChildren().remove(2);
    			a.holder.getChildren().add(imageView);
    			b.logoword.setText("\n\nMatch it Up");
        		logo = new Image(new FileInputStream("playingCards.png"));
        		imageView = new ImageView(logo);
    			imageView.setFitWidth(60);
    			//Setting the preserve ratio of the image view
    			imageView.setPreserveRatio(true); 
    			StackPane.setAlignment(b.logoword, Pos.TOP_CENTER);
    			b.holder.getChildren().remove(2);
    			b.holder.getChildren().add(imageView);	
    			if(color) {
    				a.r.setStyle("-fx-fill:#6fb98f");
    				b.r.setStyle("-fx-fill:#6fb98f");
    				
    				
    			}
    			
    		}
    		catch(FileNotFoundException e) {
    			e.printStackTrace();
    		}
    		
    	}
    }
}
