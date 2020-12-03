package hw02;

import java.util.Stack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class CalcNduka extends Application {
	//Variable declaration
	VBox root; 
	HBox biggerButs = new HBox();
	Label screen = new Label("");
	TilePane holder = new TilePane();
	Stack <Double> equations = new Stack <Double>();
	Boolean enterPressed = false;
	Boolean positiveSym = true;
	Boolean decimalPres = false;
	Boolean done = true;
	String R3 = "";
	String R4 = "";
	String neg = "-";
	String holder1 = "";
	double R3doub = 0.0;
	double answer = 0.0;
	double R1 = 0.0;
	double R2 = 0.0;
	int sizex = 4; 
	int sizey = 4;  
    public static void main( String[] args )
	{ launch(args);}
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {    
    	//SET STAGE/SCENE AND ROOT
        stage.setTitle("Calculator");
    	root = new VBox();
        Scene scene = new Scene(root, 250, 360);
	    stage.setScene(scene);
	    stage.show();
	    
	    
	    //SET LABEL SCREEN AND SET COLOR
	    Color color = new Color(0.9,0.1,0.6,1.0);
	    screen.setText("0");
	    screen.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
	    screen.setTextFill(Color.BLACK);
	    screen.setStyle("-fx-font-size:25");
	    screen.setMinWidth(250);
	    screen.setMinHeight(60);
	    
	    //SET ENTER AND SWAP BUTTONS 
	    Button enter = new Button();
	    enter.setText("Enter");
	    enter.setStyle("-fx-font-size:20");
	    enter.setOnAction(
        		(ActionEvent event) ->
        		{
        			//enter pressed will push onto stack
        			R3 = R4;
        			decimalPres = false;
        			R3doub = Double.parseDouble(R3);
        			System.out.println(R3doub);
        			equations.push(R3doub);
        			screen.setText(R3);
        			R4 = "";
        		}
        		);
	    enter.setMinWidth(125);
	    enter.setMinHeight(50);
	    	//swap button
	    Button swap = new Button();
	    swap.setText("Swap");
	    swap.setStyle("-fx-font-size:20");
	    swap.setOnAction(
        		(ActionEvent event) ->
        		{
        			//DECLARE TEMP VAR TO HOLD
        			double temp = 0.0;
        			temp = Double.parseDouble(R4);
        			R4 = Double.toString(equations.pop());
        			equations.push(temp);
        		}
        		);
	    swap.setMinWidth(125);
	    swap.setMinHeight(50);
	    //BIGGERBUTS IS HORIZONTAL BOX FOR ENTER AND SWAP BUTTONS 
	    biggerButs.getChildren().add(enter);
	    biggerButs.getChildren().add(swap);
	    
	    //ADDING LABEL SCREEN AND BIGGERBUTS TO ROOT 
	    root.getChildren().add(screen);
	    root.getChildren().add(biggerButs);
	    
	    //SETTING UP TILEPANE AND DECLARE SIZES
	    holder.setPrefColumns(sizex);
	    holder.setPrefRows(sizey);
	    holder.setMaxWidth(Region.USE_PREF_SIZE);
	    
	    
	    for(int y =0; y<16;y++) {
	    	Button but = new Button();
	    	but.setMinWidth(62.5);
	    	but.setMinHeight(62.5);
	    	but.setTextFill(Color.BLACK);
	    	switch(y) {
	    	case 0:
	    		//IF 7 PRESSED ADD 7 TO SCREEN
	    		but.setText("7");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            				R4+="7";
		            			screen.setText(R4);
		            			enterPressed=false;
	            		}
	            		);
	    		break;
	    	case 1:
	    		//IF 8 PRESSED ADD 8 TO SCREEN
	    		but.setText("8");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            				R4+="8";
		            			screen.setText(R4);
		            			enterPressed=false;		
	            		}
	            		);
	    		break;
	    	case 2:
	    		//IF 9 PRESSED ADD 9 TO SCREEN
	    		but.setText("9");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			R4+="9";
	            			screen.setText(R4);
	            			enterPressed=false;
	            		}
	            		);
	    		break;
	    	case 3:
	    		//DIVISION IS PRESSED...IF DIVIDE BY 0 DISPLAY ERRROR
	    		but.setText("/");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			if(!equations.empty()) {
	            			R3 = R4;
	            			decimalPres = false;
	            			R3doub = Double.parseDouble(R3);
            				R1 = R3doub;
            				R2 = equations.pop();
            				if(R1==0.0) {
            					screen.setText("Error");
            					done = true;
            					R4 = "";
            					R3doub = 0.0;
            					
            				}
            				else {
            					R3doub = R2/R1;
                				System.out.println("sum is" + R3doub);
                				R3 = Double.toString(R3doub);
                				screen.setText(R3);
                				done = true;
                				R4 = "";

            				}
	            			}

	            		}
	            		);
	    		break;
	    	case 4:
	    		//IF 4 PRESSED ADD 4 TO SCREEN
	    		but.setText("4");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			R4+="4";
	            			screen.setText(R4);
	            			enterPressed=false;

	            		}
	            		);
	    		break;
	    	case 5:
	    		//IF 5 PRESSED ADD 5 TO SCREEN
	    		but.setText("5");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			R4+="5";
	            			screen.setText(R4);
	            			enterPressed=false;

	            		}
	            		);
	    		break;
	    	case 6:
	    		//IF 6 PRESSED ADD 6 TO SCREEN
	    		but.setText("6");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			R4+="6";
	            			screen.setText(R4);
	            			enterPressed=false;

	            		}
	            		);
	    		break;
	    	case 7:
	    		//MULIPLICATION IS PRESSED
	    		but.setText("*");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			if(!equations.empty()) {
	            			R3 = R4;
	            			decimalPres = false;
	            			R3doub = Double.parseDouble(R3);
            				R1 = R3doub;
            				R2 = equations.pop();
            				R3doub = R2*R1;
            				System.out.println("sum is" + R3doub);
            				equations.push(R3doub);
            				R3 = Double.toString(R3doub);
            				screen.setText(R3);
            				R4 = "";
	            			}

	            		}
	            		);
	    		break;
	    	case 8:
	    		//IF 1 PRESSED ADD 1 TO SCREEN
	    		but.setText("1");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			R4+="1";
	            			screen.setText(R4);
	            			enterPressed=false;

	            		}
	            		);
	    		break;
	    	case 9:
	    		//IF 2 PRESSED ADD 2 TO SCREEN
	    		but.setText("2");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			R4+="2";
	            			screen.setText(R4);
	            			enterPressed=false;

	            		}
	            		);
	    		break;
	    	case 10:
	    		//IF 3 PRESSED ADD 3 TO SCREEN
	    		but.setText("3");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			R4+="3";
	            			screen.setText(R4);
	            			enterPressed=false;

	            		}
	            		);
	    		break;
	    	case 11:
	    		//SUBTRACTION IS PRESSED
	    		but.setText("-");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			if(!equations.empty()) {
	            				R3=R4;
	            				decimalPres = false;
	            				R3doub = Double.parseDouble(R3);
	            				R1 = R3doub;
	            				R2 = equations.pop();
	            				R3doub = R2-R1;
	            				System.out.println("sum is" + R3doub);
	            				R3 = Double.toString(R3doub);
	            				screen.setText(R3);
	            				done = true;
	            				R4 = "";
	            			}

	            		}
	            		);
	    		break;
	    	case 12:
	    		//IF 0 PRESSED ADD 0 TO SCREEN
	    		but.setText("0");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			R4+="0";
	            			screen.setText(R4);
	            			enterPressed=false;

	            		}
	            		);
	    		break;
	    	case 13:
	    		//DECIMAL IS PRESSED. CANT BE PRESSED AGAIN UNTIL NEW NUMBER IS BEING FORMED 
	    		but.setText(".");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			if(!decimalPres) {
	            				R4+=".";
		            			screen.setText(R4);
		            			decimalPres = true;
	            			}
	            			

	            		}
	            		);
	    		break;
	    	case 14:
	    		//ADDITION IS PRESSED
	    		but.setText("+");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			if(!equations.empty()) {
	            				R3 = R4;
	            				decimalPres = false;
	            				R3doub = Double.parseDouble(R3);
	            				equations.push(R3doub);
	            				R1 = equations.pop();
	            				R2 = equations.pop();
	            				R3doub = R2+R1;
	            				R3 = Double.toString(R3doub);
	            				screen.setText(R3);
	            				done = true;
	            				R4 = "";
	            			}
	            			

	            		}
	            		);
	    		break;
	    	case 15:
	    		//NEGATIVE AND POSITIVE
	    		but.setText("\u00B1");
	    		but.setStyle("-fx-font-size:30");
	    		but.setOnAction(
	            		(ActionEvent event) ->
	            		{
	            			//TURN TO NEGATIVE 
	            			if(positiveSym) {
	            				positiveSym = false;
	            				holder1 = R4;
	            				//NEG IS A NEGATIVE SIGN 
	            				R4 = neg;
	            				//ADDING REST OF NUMBER TO NEG SIGN
	            				R4+=holder1;
	            			}
	            			//TURN TO POSITIVE...LEAVE OUT NEG SIGN
	            			else {
	            				R4 = R4.substring(1);
	            				positiveSym = true;
	            			}
	            			screen.setText(R4);

	            		}
	            		);
	    		break;
	    	}
	    	holder.getChildren().add(but);
	    }
	    root.getChildren().add(holder);
	    
    	
    }
    
	
	
	
	

}
