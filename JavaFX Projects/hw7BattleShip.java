package battleShip;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class hw7BattleShip extends Application {

		Text aText;
		Text[][] mark;
	    Text[][] mark2;
	    boolean client;
	    int countH = 0;
	    int countC = 0;
	    boolean hit;
	    Label label = new Label("You Win!!!!!");
	    HBox bottomPart = new HBox();
	    ArrayList <String> coord = new ArrayList<String>();
	    ArrayList <String> coord1 = new ArrayList<String>();
	    ArrayList <String> holder = new ArrayList<String>();
		boolean myTurn;
		VBox root; // the whole window
		Scene scene;
		Ear oe; // listens for what the other end says
		ServerSocket serverSock; // allows client to connect
		Socket clientSock; // this is the connection that BOTH server
		                   // and client use one client connects
		String ip; // the IP number of the server
		int socketNumber = 12457; // random number for this app
		BufferedReader myIn=null; // how we read from other end
		PrintWriter myOut=null; // how we write to the other end
		Pane gamePane;
		Pane gamePane2;// upper part of window, shows conversation
		HBox gamePaneHolder;
		TextField talker; // where user types a new thing to say
		boolean beginning  = true;
		int clickedi;
		int clickedj;
		HBox controlPane; // has buttons to get started
		
	   public static void main( String[] args )
	   { launch(args); }
	   
	   public void start( Stage stage )
	   {
		   	  root = new VBox();
		      scene = new Scene(root, 1300, 800);
		      stage.setTitle("BattleShip");
		      stage.setScene(scene);
		      stage.show();
		      
		      gamePane = new Pane();
		      gamePane2 = new Pane();
		      gamePaneHolder = new HBox();
		      gamePane.setPrefSize( 600,600 );
		      gamePane2.setPrefSize( 600,600 );
		      gamePaneHolder.getChildren().add(gamePane);
		      gamePaneHolder.getChildren().add(gamePane2);
		      
		      root.getChildren().add(gamePaneHolder);
		      controlPane = new HBox();
		      
		      bottomPart.getChildren().add(controlPane);
		      
		      label.setPadding(new Insets(50,0,0, 600));
		      label.setStyle("-fx-font-size:50px;"+ "-fx-font-weight:bold;");
		      label.setTextFill(Color.GREEN);
		      
		      root.getChildren().add( bottomPart );
		      
		      mark = new Text[5][5];
		      mark2 = new Text[5][5];
		      
		      //setting up the 1st of the 2 boards
		      Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
		      Rectangle bg = new Rectangle(0,20,520,520);
		      Rectangle bg1 = new Rectangle(0,20,520,520);
		      bg.setFill( Color.BLACK);
		      gamePane.getChildren().add(bg);
		      gamePane2.getChildren().add(bg1);
		      
		      for (int i=0; i<5; i++ )
		      {
		      	for ( int j=0; j<5; j++ )
		      	{
		      		double x = i*100+15;
		      		double y = j*100+35;
		      		Rectangle r = new Rectangle(x, y, 90, 90 );
		      		r.setFill(Color.LIGHTBLUE);
		      		r.setOnMouseClicked(new EventHandler<MouseEvent>()
			        {
			            @Override
			            public void handle(MouseEvent t) {
			            	//before host/client buttons selected, player chooses ship
			            	if(beginning) {
			            		int l = (int)((t.getX()-15)/100);
		            			int m = (int)((t.getY()-35)/100);
		            			//putting coords in string format then placing in arraylist
		            			String wordh = Integer.toString(l) + Integer.toString(m);
		            			//if player chooses to de-select option then player can do so
			            		if(r.getFill()==Color.LIGHTBLUE) {
			            			holder.add(wordh);
				            		r.setFill(Color.BLACK);
				            	}
				            	else {
				            		holder.remove(findIndex(holder,wordh));
				            		r.setFill(Color.LIGHTBLUE);
				            	}	
			            	}
			            }
			        });
		      		gamePane.getChildren().add(r);
		      		Text t = new Text(); t.setFont(font);
		      		mark[i][j] = t; t.setX(x+12); t.setY(y+50);
		      		gamePane.getChildren().add(t);
		      	}
		      }
		      //instructions
		      aText = new Text("Pick your ship by selecting 4 sqaures in the same ROW\nThen Click Button\n\n\n\n");
		      aText.setX(10); aText.setY(580);
		      gamePane.getChildren().add(aText);
		      
		     //setting up the second board!
		      for (int i=0; i<5; i++ )
		      {
		      	for ( int j=0; j<5; j++ )
		      	{
		      		double x = i*100+15;
		      		double y = j*100+35;
		      		Rectangle r = new Rectangle(x, y, 90, 90 );
		      		r.setFill(Color.LIGHTBLUE);
		      		r.setOnMouseClicked(new EventHandler<MouseEvent>()
			        {
			            @Override
			            public void handle(MouseEvent t) {
			            	
			            	clickedi = (int)((t.getX()-15)/100);
			 	            clickedj = (int)((t.getY()-35)/100);
			 	            String wordh = Integer.toString(clickedi) + Integer.toString(clickedj);
			 	            if( myTurn )
			 	            {
			 	            	//if clients turn
			 	            	if(client) {
			 	            		//check if coord is in the array of coords for host
			 	            		if(findIndex(coord, wordh)!=-1) {
				 	            		r.setFill(Color.GREEN);
				 	            		hit = true;			 	            		
				 	            		countC++;
				 	            	}
				 	            	else {
				 	            		r.setFill(Color.RED);
				 	            		hit=false;
				 	            	}
			 	            		
			 	            	}
			 	            	else {
			 	            		if(findIndex(coord1, wordh)!=-1) {
				 	            		r.setFill(Color.GREEN);
				 	            		hit=true;
				 	            		countH++;
				 	            		
				 	            	}
				 	            	else {
				 	            		r.setFill(Color.RED);
				 	            		hit=false;
				 	            	}
			 	            	}
			 	               marker( clickedi, clickedj, mark2, hit);
			 	               
			 	               //determines who wins
			 	               if(countC==4) {
			 	            	  bottomPart.getChildren().add(label);
			 	               }
			 	               else if(countH==4) {
			 	            	  bottomPart.getChildren().add(label);			 	            	 
			 	               }
			 	               //determine if hit was miss or not then sends info to other side
			 	               if(hit) {
			 	            	  send("play "+clickedi+" "+clickedj+ " true" );  
			 	               }
			 	               else {
			 	            	  send("play "+clickedi+" "+clickedj+ " false" );
			 	               }
			 	               myTurn = false;
			 	            }
			            }
			        });
		      		gamePane2.getChildren().add(r);
		      		Text t = new Text(); t.setFont(font);
		      		mark2[i][j] = t; t.setX(x+12); t.setY(y+50);
		      		gamePane2.getChildren().add(t);
		      	}
		      }
		      
		      aText = new Text("Hit Opponents ship by selecting a square");
		      aText.setX(10); aText.setY(580);
		      gamePane2.getChildren().add(aText);

		      
		      // host button
		      Button hostButton = new Button("host");
		      controlPane.getChildren().add(hostButton);
		      hostButton.setOnAction
		      (e->
		      { 
		    	  startTHISend(); new SetupHost().start(); 
		    	  beginning = false;
		      });
		      
		      // client Button
		      // When you press it, it puts up a field to enter the IP
		      // number.  And when you enter that, then it calls
		      // setupClient();
		      Button clientButton = new Button("client");
		      controlPane.getChildren().add(clientButton);
		      clientButton.setOnAction
		      ( e->
		         {	        	 
		        	beginning = false;
		        	controlPane.getChildren().clear();
		         	Label ipLabel = new Label("IP#?");
		         	controlPane.getChildren().add(ipLabel);
		         	TextField iptf = new TextField("localhost");
		         	controlPane.getChildren().add(iptf);
		         	iptf.setOnAction
		         	( f-> { ip = iptf.getText(); setupClient(); } );
		         }
		      );
		      
		      // set it so that when you close the application window,
		      // it exits BOTH this end and the other end
		 	  stage.setOnCloseRequest
		 	  ( (WindowEvent w) -> 
		 	    { try{ send("byebyebye"); System.exit(0); } 
		 	      catch (Exception e) { System.out.println("can't stop"); } 
		 	    } 
		 	  );

	   }
	   //this function returns index of the item that is being searched or -1 if DNE
	   public int findIndex(ArrayList<String> hold, String word) {
		   for(int i = 0; i < hold.size(); i++) {
			   if(word.equalsIgnoreCase(hold.get(i))) {
				   return i;
			   } 
		   }
		   return -1;
		   
	   }
	   //does a deepcopy of the array 
	   public ArrayList<String> deepCopy(ArrayList<String> array){
		   ArrayList<String> holder = new ArrayList<String>();
		   for(String w:array) {
			   holder.add(w);
		   }
		   return holder;
	   }
	   // fucntion put s in square i,j and also switches the client boolean and also updates hit function 
	   public void marker( int i, int j, Text[][] hold, boolean hitmiss )
	   {
		   if(client) {
			   client=false;
		   }
		   else {
			   client = true;
		   }
		   hold[i][j].setFill(Color.ORANGE);
		   if(hitmiss) {
			   hold[i][j].setText("hit");
			   hit=true;
			   send("false"); 
		   }
		   else {
			   hold[i][j].setText("miss");
			   hit=false;
			   send("true");
		   }

	   }
	   
	   public class SetupHost extends Thread
	   {
		   
		   // sets up this to be the first player / host ...
		   // First player opens a socket and announces the
		   // IP and number, then waits (hangs) until 2nd connects.  
	   	@Override
		   public void run()
		   {
	   		myTurn = true;
	   		//puts contents in holder in coord array for host
	   		for(String i:holder) {
	  		  coord.add(i);
	     	}
	   		holder.clear();
	   		
		   	try
		   	{
		   	   serverSock = new ServerSocket(socketNumber);
		   	   clientSock = serverSock.accept(); 
		   	   say("server says client connected ...");
		   	   InputStream in = clientSock.getInputStream();
		   	   myIn = new BufferedReader( new InputStreamReader(in));
		   	   String msg = myIn.readLine();
		   	   //these next lines read the contents of clients array then appends it to coord1 array
		   	   say("just read="+msg);
		   	   msg = myIn.readLine();
		   	   coord1.add(msg);
		   	   msg = myIn.readLine();
		   	   coord1.add(msg);
		   	   msg = myIn.readLine();
		   	   coord1.add(msg);
		   	   msg = myIn.readLine();
		   	   coord1.add(msg);
		   	  
		   	   myOut = new PrintWriter( clientSock.getOutputStream(),true);
		   	   String msg2 = "you rang?";
		   	   myOut.println(msg2);
		   	   myOut.flush();
		         
		         //send contents of host array to client
		        for(String co:coord) {
		        	 myOut.println(co);
			         myOut.flush();
		        }
		         oe = new Ear();
		         oe.start();     
		   	}
		   	catch(Exception e) 
		   	{ System.out.println("socket open error e="+e); }
		   }
	   }
	   
	   // sets up this to be the client, which logs into
	   // the host. ...
	   public void setupClient()
	   {
	   	say("client setup: starting ...");
	   	myTurn = false;
   		for(String i:holder) {
		  coord1.add(i);
   		}
   		holder.clear();
	   	try
	   	{
	   		// connect to server.  Use ip="localhost" for server
	   		// on the same machine (for testing)
	   		clientSock = new Socket(ip,socketNumber);
	   		
	   		say("if you see this, client is connected!");
	         InputStream in = clientSock.getInputStream();
	         myIn = new BufferedReader( new InputStreamReader(in) );
	   	   	myOut = new PrintWriter( clientSock.getOutputStream(),true);
	   	   
	         myOut.println("greetings");    
	         myOut.flush();
	         for(String word:coord1) {
	        	 myOut.println(word);    
		         myOut.flush();
	         }
	         
	         String line;
	         line = myIn.readLine();
	         say("read from server = "+line);
	         line = myIn.readLine();
	         coord.add(line);
	         line = myIn.readLine();
	         coord.add(line);
	         line = myIn.readLine();
	         coord.add(line);
	         line = myIn.readLine();
	         coord.add(line);  
	         startTHISend();
	         
	         // start the Ear thread, which listens for messages
	         // from the other end.
	         oe = new Ear();
	         oe.start();     

	   	}
	   	catch( Exception e )
	   	{ say("client setup error e="+e); }
	   }
	   
	   // put a place for
	   // the user to type in the controlPane and connect
	   // it to the conversation window
	   public void startTHISend()
	   {
	      controlPane.getChildren().clear();
		   talker = new TextField();
		   controlPane.getChildren().add(talker);
		   talker.setOnAction
		   ( g-> { String s = talker.getText();
		   	     say( "me: " +s ); 
		   	     send(s);
		   	     talker.setText(""); 
		         } 
		   );
	   }
	   
	   // Ear is the thread that listens for information coming
	   // from the other user.  Go into a loop reading
	   // whatever they send and add it to the conversation.
	   // If the other end sends "byebyebye", exit this app.
	   public class Ear extends Thread
	   {
		   @Override
		   public void run()
		   {
		   	while (true)
		   	{
		   		try
		   		{
		   			String s = myIn.readLine(); // hangs for input
			         if ( s.equals("byebyebye") ) { System.exit(0); }
			         else
			         {
			         	try
			         	{
				         	StringTokenizer st = new StringTokenizer(s);
				         	String cmd = st.nextToken();
				         	
				         	//if opponents turn then click on right then show on left of other page
				         	if ( cmd.equals("play") )
				         	{
				         		int i = Integer.parseInt( st.nextToken());
				         		int j = Integer.parseInt( st.nextToken());
				         		//getting info whether piece have been hit or not then updates on other end
				         		String misshit = st.nextToken();
				         		if(misshit.equals("true")) {
				         			hit = true;
				         		}
				         		else {
				         			hit=false;
				         			
				         		}
				         		marker( i,j, mark,hit );
				         		myTurn = true;
				         	}
			         	}
			         	catch(Exception k)
			         	{ System.out.println(" read failure"); }
			         }
		   		}
		   		catch(Exception h)
		   		{ say("couldn't read from the other end"); }
		   	}
		   }
	   }
	   
	   // add this string to the conversation.
	   public void say(String s) 
	   {
		   System.out.println(" says "+s); 

	   }
	   
	   // if the output is established, send s to it.
	   public void send( String s )
	   {
	   	if ( myOut!=null )
	   	{
	   		myOut.println(s);
	   	}
	   }
	}