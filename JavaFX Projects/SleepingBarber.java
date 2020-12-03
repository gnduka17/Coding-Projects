package sleepingBarb;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SleepingBarber extends Application
{
	Pane root; // has everything
	Scene scene; // the town scene
	static int maxid = 0; // ids for customers
	Rectangle room; // pink box which is barber shop
	LinkedList<Customer> customers; // whole town
	Barber theBarber; // red dot in pink box
	protected int townSize = 5; // number of potential Customers
	Semaphore custFlag;

   public static void main( String[] args )
   { launch(args); }
   
   public void start( Stage stage )
   {
   	// the usual 5 lines to set up window
      root = new Pane();
      scene = new Scene(root, 500, 400);
      stage.setTitle("Sleeping Barber demo");
      stage.setScene(scene);
      stage.show();

  	   // draw the barber shop
      room = new Rectangle(30, 200, 200, 100 );
      room.setFill( Color.PINK);
      root.getChildren().add(room);
    	
      // set up the barber, start
   	theBarber = new Barber();
   	theBarber.start();
   
   	// make a town of customers, start them
      customers = new LinkedList<Customer>();
      for ( int i=0; i<townSize; i++ )
      {
    	   Customer c = new Customer(i);
    	   c.start();
    	   customers.add(c);
      }  
      //setting up semaphore for customer
      custFlag =  new Semaphore(1);

      // program stops when you close the window
	   stage.setOnCloseRequest
	   ( (WindowEvent w) -> 
	     { try{ System.exit(0); } 
	       catch (Exception e) { System.out.println("can't stop"); } 
	     } 
	   );
   } // end start() function
   
   public class Customer extends Thread
   {
      //protected String me; // name
      protected Circle marker;
      protected boolean stuck = false; // stuck at the Barber
      public void setStuck( boolean tf ) { stuck = tf; }
      protected int id;
      
      // 3 places customer can be, hoeme, at the door, in the shop
      double homex;
      double homey = 50;
      double doorx;
      double doory = 250;
      double shopx;
      double shopy = 250;
      
      public Customer( int i )
      {
   	   id = maxid++;
   	   marker = new Circle(10 );
   	   homex = 250 + 20*id;
   	   doorx = 250 + 20*id;
   	   shopx = 100 + 20*id;
   	   setX(homex);
   	   setY(homey);
   	   root.getChildren().add(marker);
      }
      
      public void run()
      {
      	while( true )
      	{
      		waitwait(1,2);
      		if(!stuck) {
	      		waitwait(3,6);
	      		goToShop(); // to the shop door
	      		waitwait(1,2);
				goIn(); //go inside
				stuck=true;
			}
      	}
      }
      
      protected void goToShop()
      {
      	setX(doorx);
      	setY(doory);
      }
      
      protected void goIn()
      {
    	  //acquire the 1 semaphore
		   try { custFlag.acquire(); } catch(Exception e) {}
		   setY(shopy);
	       setX(shopx);
	       theBarber.comesIn(this); //adds customer in the barber list
      }
      
      public void goHome()
      {  
    	  custFlag.release(); //release semaphore;
		  setX(homex);
	      setY(homey);
      }

      public void setX(double x ) { marker.setCenterX(x); }
      public void setY(double y ) { marker.setCenterY(y); }
      public double getX(){ return marker.getCenterX(); }
      public double getY(){ return marker.getCenterY(); }
   }

   public class Barber extends Thread
   {
   	protected Circle marker; // red dot which is the barber
   	protected double homex = 50; // sleeping location
   	protected double homey = 230;
   	LinkedList<Customer> waiting; // list of customers waiting
   	
      public Barber( )
      {
         //me = m;
   	   marker = new Circle(10 );
   	   marker.setFill( Color.RED );
   	   marker.setCenterX(homex);
   	   marker.setCenterY(homey);
   	   root.getChildren().add(marker);
   	   //writeFlag = new Semaphore(1);
   	   waiting = new LinkedList<Customer>();
      }
      
      @Override
      public void run()
      {
      	while (true )
      	{
      		waitwait(2,3); // sleep a little
      		cut(); // go cut someone's hair
      	}
      }
      
      // go back to sleeping location
      public void goBackToSleep()
      {
      	setX(homex);
      	setY(homey);
      }
      
      // customer tells barber that they are waiting for
      // a cut by adding to the waiting list
      public void comesIn( Customer c )
      { waiting.add(c); }
      
      // the is the barber cutting hair.  
      public void cut( )
      {
      	if ( waiting.size()>0 )
      	{
      		// get a customer and 'cut hair' (kill time)
      		Customer c = waiting.getFirst();
//	      	waitwait(1,2);
	      	setX( c.getX() ); // move next to customer
	      	waitwait(2,3); // cut time
	      	
	      	// bye bye to customer
	      	waiting.remove(c); 
	      	c.goHome();
	      	c.setStuck(false);
	      	
	      	goBackToSleep(); // barber back to sleeping 
      	}
      }

      public void setX(double x ) { marker.setCenterX(x); }
      public void setY(double y ) { marker.setCenterY(y); }
   }
   
   // waits between min and max seconds
   public void waitwait( double min, double max )
   {
   	int sleeptime = (int)( min + Math.random() * (max-min) ) * 1000;
      try { Thread.sleep(sleeptime); } catch (Exception e) {}
   }
   public void waitwait( int much )
   {
       int sleeptime = 500 + (int)(Math.random()*much);
       try { Thread.sleep(sleeptime); } catch (Exception e) {}
   }
   
}
