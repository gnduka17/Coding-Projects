package hw6;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import week8.Ball2;
import week8.BrickOut.Driver;

public class MaxwellNduka extends Application {
	Pane root;
	Scene scene;
	Line line;
	Rectangle rec;
	StackPane stack;
	Random r;
	int sideHold;
	Button addMore = new Button("ADD MORE BALLS");
	Map <Ball2, Integer> blueMap = new HashMap<Ball2,Integer>();
	Map <Ball2, Integer> redMap = new HashMap<Ball2,Integer>();
	long lasttime;
	boolean thru = false;
	boolean firsttime = true;
	LinkedList <Ball2> balls = new LinkedList<Ball2>();
	
	
	public static void main (String [] args) {
		launch(args);
	}
	public void start(Stage stage) {
		root = new Pane();
		scene = new Scene(root, 1000, 600);
		scene.setFill(Color.BLACK);
		root.setStyle("-fx-background-color:black;");
        stage.setTitle("HW6 - Maxwell's Demon");
	    stage.setScene(scene);
	    stage.show();
	    
	    line = new Line(500,0,500,600);
	    line.setStroke(Color.WHITE);
	    line.setStrokeWidth(2);
	    
	    rec = new Rectangle(495,0,10,100);
	    rec.setFill(Color.YELLOW);
	    
	    root.getChildren().add(line);
	    root.getChildren().add(rec);
	    
	    //add more balls button
	    addMore.setStyle("-fx-background-color:purple;");
	    //once button clicked, balls will randomly be placed on screen
	    addMore.setOnAction((ActionEvent e)->{
	    	r = new Random();
	    	 Ball2 b = new Ball2(r);
	    	 //places ball in right map
	    	 if(b.getColor()==0) {
	    		 redMap.put(b,b.getSide());
	    	 }
	    	 else {
	    		 blueMap.put(b,b.getSide()); 
	    	 }
	    	 root.getChildren().add(b);
	    	 balls.add(b);
	    });
	    root.getChildren().add(addMore);
	    //space ball can roam around
	    Ball2.setSpaceW(1000);
	    Ball2.setSpaceH(600);
	    //start the balls moving
	    Driver d = new Driver();
	    d.start();
	    //create first 6 balls
	    for(int i =0; i<6;i++) {
	    	 r = new Random();
	    	 Ball2 b = new Ball2(r);
	    	 if(b.getColor()==0) {
	    		 redMap.put(b,b.getSide());
	    	 }
	    	 else {
	    		 blueMap.put(b,b.getSide()); 
	    	 }
	    	 root.getChildren().add(b);
	    	 balls.add(b);
	    }
	    //moves door
	    root.setOnMouseMoved(me->{	
	    	if(me.getY()<510) {
	    		rec.setY(me.getY());
	    	}
	    });
	    //turns door green when clicked
	    root.setOnMousePressed(me->{
	    	rec.setFill(Color.GREEN);
	    	thru = true;
	    	
	    });
	    root.setOnMouseReleased(me->{
	    	rec.setFill(Color.YELLOW);
	    	thru = false;
	    });
	}
	 public class Driver extends AnimationTimer
	    {
	    	@Override
	    	public void handle( long now )
	    	{
	    		if ( firsttime ) { lasttime = now; firsttime = false;  }
	    		else
	    		{
	    		    double deltat = (now-lasttime ) * 1.0e-9;
	    			lasttime = now;
	    			
	    			for (Ball2 b1 : balls )
	                { 
	    				b1.move(deltat); 
		                Bounds ballBounds = b1.getBoundsInLocal();
		    			Bounds paddleBounds = line.getBoundsInLocal();
		    			Bounds greendoor = rec.getBoundsInLocal();
		    			
		    			//if ball passes through update sides on map and update map
		    			if(thru&&ballBounds.intersects(greendoor)) {
		    				b1.setSide(b1.getSide());
		    				sideHold = b1.getSide();
		    				//red
		    				if(b1.getColor()==0) {
		    					redMap.remove(b1);
		    					redMap.put(b1,sideHold);
		    				}
		    				else {
		    					blueMap.remove(b1);
		    					blueMap.put(b1,sideHold);	
		    				}
		    				System.out.println("print red map-"+ redMap);
		    				System.out.println("print blue map-"+ blueMap);
	//	    				if((redMap.containsValue(0)&&redMap.containsValue(1))) {
	//	    			    	System.out.println("STILL LOSING 12");
	//	    			    }
	//	    			    else {
	//	    			    	if(redMap.containsValue(0)) {
	//	    			    		if(blueMap.containsValue(0)) {
	//	    			    			System.out.println("STILL LOSING34");
	//	    			    		}
	//	    			    		else {
	//	    			    			System.out.println("I  WONNNN------------------------------");
	//	    			    		}
	//	    			    		
	//	    			    	}
	//	    			    	else {
	//	    			    		if(blueMap.containsValue(1)) {
	//	    			    			System.out.println("STILL LOSING67");
	//	    			    		}
	//	    			    		else {
	//	    			    			System.out.println("I  WONNNN------------------------------");
	//	    			    		}
	//	    			    		
	//	    			    	}
	////	    			    	if(blueMap.containsValue(0)&&blueMap.containsValue(1)) {
	////	    			    		System.out.println("STILL LOSING");
	////	    			    		
	////	    			    	}
	////	    			    	else {
	////	    			    		System.out.println("I  WONNNN------------------------------");
	////	    			    	}
	//	    			    	
	//	    			    }
		    				
		    				
		    			}
		    			//if hit line then bounce back
		    			else if (ballBounds.intersects( paddleBounds ) )
		    			{
		    				b1.hitLineVert();
		    			}
		    			//if hit the perimeters then bounce back
		    			if(ballBounds.intersects(0,0,5,600)){
		    				b1.hitLineVert();
		    			}
		    			if(ballBounds.intersects(10,599,995,10)) {
		    				b1.hitLineHori();
		    			}
	                }
	    	    }
	    	}
	    }
	
}
