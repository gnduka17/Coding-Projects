package hw6;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.*;

public class Ball2 extends Rectangle
{
	
	double vx; 
	double vy;
	double vxHolder;
	double vyHolder;
	int speed = 100;
	int side;
	int randcolor;
	protected final static int hw = 10; // size of ball
	public static int getHW() { return hw; }
	Random r;
	protected static int spaceW; // dimensions of the space
	protected static int spaceH; // to bounce in ( 0,0 to W,H )
	public static void setSpaceW( int w ) { spaceW = w; }
	public static void setSpaceH( int h ) { spaceH = h; }
	
    public Ball2( Random r1)
    {
    	super( hw, hw );
    	r=r1;
    	//generate random numbers f=to dtermine color and side 
    	side = r.nextInt(2);
    	randcolor = r.nextInt(2);
    	if(side==0) {
    		if(randcolor==0) {
    			setFill(Color.RED);
    			vx = 150;
    			vy=130;
    		}
    		else {
    			setFill(Color.BLUE);
    			vx=80;
    			vy=60;
    		}
    		setX(r.nextInt(471)+10);
        	setY(r.nextInt(581)+10); 
    	}
    	else {
    		if(randcolor==0) {
    			setFill(Color.RED);
    			vx=150;
    			vy = 130;
    		}
    		else {
    			setFill(Color.BLUE);
    			vx=80;
    			vy=60;
    		}
    		setX(r.nextInt(476)+515);
        	setY(r.nextInt(581)+10); 
    	} 
    	
    }
    public int getColor() {
    	return randcolor;
    	
    }
    public int getSide() {
    	return side;
    	
    }
    public void setSide(int x) {
    	if(x==0) {
    		side=1;
    	}
    	else {
    		side=0;
    	}
    }
    
    public void move( double deltat )
    {
    	double x = getX();
    	x += vx * deltat;
    	setX(x);
    	double y = getY();
    	y += vy * deltat;
    	setY(y);
    	
    	//if ( y>spaceH -hw ) { vy = -r.nextInt(speed) - hw; }
    	if ( x>spaceW -hw ) { vx = -r.nextInt(speed) - hw; }
    	if ( x<0 ) { vx = r.nextInt(speed) + hw; }
    	if ( y<0 ) { vy = r.nextInt(speed) + hw; }
    }
    
    public void hitLineVert()
    {
    		vx = vx*(-1);
    }
    public void hitLineHori()
    {
    	if ( vy > 0 )
    	{
    		vy = -vy;
    	}
    }
}
