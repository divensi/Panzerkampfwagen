package divensi;
import robocode.*;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Panzerkampfwagen - a robot by (Felipe Divensi)
 */
public class Panzerkampfwagen extends Robot
{
	/**
	 * run: Panzerkampfwagen's default behavior
	 */
	public int found = 0 ;

	public void run() {
		setBodyColor(new Color(0,0,0));
		setGunColor(new Color(200,0,0));
		setRadarColor(new Color(200,0,0));
		setScanColor(new Color(0,0,0));
		setBulletColor(new Color(200,0,0));
		
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while( true ) {
			if ( inSentryArea() == true ) {
				moveOutOfSentryArea();
			} else {
				turnGunLeft( found * 2 );
				found+=5;
			}
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot( ScannedRobotEvent e ) {
		// Replace the next line with any behavior you would like
		if ( inSentryArea() == true ) {
			moveOutOfSentryArea();
		} else {
			found = 0;
	        precisionFire( e );
	    	seekAndDestroy( e );
		}
	}
	
	public void precisionFire(ScannedRobotEvent e) {
		fire(600/e.getDistance()); 
	}
	
	public boolean inSentryArea() {
		if ( getX() > getBattleFieldWidth() - getSentryBorderSize() ||  getX() < getSentryBorderSize() ) {
			return true;
		} else {
			if ( getY() > getBattleFieldHeight() - getSentryBorderSize() ||  getY() < getSentryBorderSize() ) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public void moveOutOfSentryArea() {
		if ( getX() > getBattleFieldWidth() - getSentryBorderSize() ) {
			//System.out.println("turning " + (getHeading() - 180) + " heading " + getHeading() );
			turnLeft(getHeading() - 270);
			ahead(50);
		}
		if ( getX() < getSentryBorderSize() ) {
			//System.out.println("turning " + (getHeading() - 0) + " heading " + getHeading() );
			turnLeft(getHeading() - 90);
			ahead(50);
		}
		if ( getY() > getBattleFieldHeight() - getSentryBorderSize() ) {
			//System.out.println("turning " + (getHeading() - 90) + " heading " + getHeading() );
			turnLeft(getHeading() - 180);
			ahead(50);
		}
		if ( getY() < getSentryBorderSize() ) {
			//System.out.println("turning " + (getHeading() - 270) + " heading " + getHeading() );
			turnLeft(getHeading() - 0);
			ahead(50);
		}
	}
	
	public void seekAndDestroy (ScannedRobotEvent e) {
		if (e.getBearing() > 45) {
			turnRight(20);
		}
		else {
			if (e.getBearing() < -45) {
				turnLeft(20);
			}
		}
		if (e.getDistance() > 150) {
        	ahead(100);
		} 

	}
	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		//back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		turnLeft(45);
		ahead(20);
		
	}	
}
