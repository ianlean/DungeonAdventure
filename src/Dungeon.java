/*
* Dungeon.java
*
* TCSS 143 – Spring 2021
* Instructor - Tom Capaul
* Ian McLean
* Assignment 5
*/

import java.util.*;

/**
* This class represents
* a dungeon maze.
* @author Ian McLean
* @version 1.0 
*/
public class Dungeon {
    
    /**
        The room the hero is in.
    */
    private Room heroSpot;
    
    /**
        The x coordinate of the hero spot.
    */
    private int myX;
    
    /**
        The y coordinate of the hero spot.
    */
    private int myY;
    
    /**
        The 2d array that represents the dungeon.
    */
    private final Room[][] myMaze = new Room[5][5];
    
    /**
        A constructor which creates a Dungeon.
    */
    public Dungeon() {
        Random rand = new Random();
        for (int row = 0; row < myMaze.length; row++) {
              
            for (int col = 0; col < myMaze[row].length; col++) {
                Room aRoom = new Room();
                myMaze[row][col] = aRoom;
            }    
        }
        // creates the entrance door
        int entranceRow = rand.nextInt(myMaze.length);
        myMaze[entranceRow][0].makeEntrance();
        // open it so the toString knows what to print
        myMaze[entranceRow][0].openDoor();
        // get rid of its monster incase it has one
        myMaze[entranceRow][0].takeMonster();
        // get rid of its potion incase it has one
        myMaze[entranceRow][0].takePotion();
        setX(0); // set coordinates to the entrance door
        setY(entranceRow);
        heroSpot = myMaze[entranceRow][0]; // sets the entrance to where the hero is
        //do everything we just did but for the exit door
        int exitRow = rand.nextInt(myMaze.length);
        myMaze[exitRow][4].makeExit();
        myMaze[exitRow][4].takeMonster();
        myMaze[exitRow][4].takePotion();
        int firstCrownRow = rand.nextInt(4); // create crown rooms
        int firstCrownCol = rand.nextInt(4);
        int secondCrownRow = rand.nextInt(4);
        int secondCrownCol = rand.nextInt(4);
        int shorten = entranceRow;
        int shorten2 = exitRow + 4;
        int shorten3 = firstCrownRow + firstCrownCol;
        int shorten4 = secondCrownRow + firstCrownCol;
        // continue to create crown rooms until they are unique
        while (helper(shorten, shorten2, shorten3, shorten4)) {
            firstCrownRow = rand.nextInt(4);
            firstCrownCol = rand.nextInt(4);
            secondCrownRow = rand.nextInt(4);
            secondCrownCol = rand.nextInt(4);
            shorten = entranceRow + 4;
            shorten2 = exitRow + 4;
            shorten3 = firstCrownRow + firstCrownCol;
            shorten4 = secondCrownRow + firstCrownCol;      
        }
        
        this.myMaze[firstCrownRow][firstCrownCol].giveCrown();
        this.myMaze[secondCrownRow][secondCrownCol].giveCrown();
    }
    
    /**
        A helper method for the constructor, insures
        that the rooms aren't the same.
        
        @param theShorten - sum of entrance.
        @param theShorten2 - sum of exit.
        @param theShorten3 - sum of first crown.
        @param theShorten4 - sum of second crown.
        @return if each door is unique.
    */
    public boolean helper(final int theShorten, final int theShorten2, 
        final int theShorten3, final int theShorten4) {
        return theShorten == theShorten3 || theShorten == theShorten4
                || theShorten2 == theShorten3 || theShorten2 == theShorten4
                || theShorten3 == theShorten4;
    }
    
    /**
        Moves the heroSpot and coordinates throughout the dungeon.
        
        @param theRow - the row being moved to.
        @param theCol - the column being moved to.
    */ 
    public void move(final int theRow, final int theCol) {

        if (validMove(theRow, theCol)) {
            try {
                heroSpot = myMaze[theRow][theCol];
                setX(theCol);
                setY(theRow);
                myMaze[theRow][theCol].openDoor();
            } catch (IndexOutOfBoundsException e) {
                System.out.print("You can't move your hero there, try again.");
            }
        }   
    }

    /**
        returns the hero spot.
        
        @return the hero spot.
    */ 
    protected Room getHeroSpot() {
        return heroSpot;
    }
    
    /**
        sets the x coordinate.
        
        @param theX - the x coordinate.
    */ 
    protected void setX(final int theX) {
        myX = theX;
    }
    
     /**
        sets the y coordinate.
        
        @param theY - the y coordinate.
    */ 
    protected void setY(final int theY) {
        myY = theY;
    }
    
    /**
        returns the x coordinate.
        
        @return the x coordinate.
    */ 
    protected int getX() {
        return myX;
    } 
    
     /**
        returns the y coordinate.
        
        @return the y coordinate.
    */ 
    protected int getY() {
        return myY;
    }
    
     /**
        makes sure the move is possible.
        
        @param theRow - the row being moved to.
        @param theCol - the column being moved to.
        @return if the move is possible.
    */ 
    private boolean validMove(final int theRow, final int theCol) {
        return theRow >= 0 && theRow < myMaze.length
		      && theCol >= 0 && theCol < myMaze[theRow].length;
    }
    
     /**
        The toString method for the dungeon.
        
        @return the string representation of the dungeon.
    */ 
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        for (int row = 0; row < myMaze.length; row++) {
            s.append("\n");
            for (int col = 0; col < myMaze[row].length; col++) {
                if (row == myY && col == myX) {
                    s.append("C ");
                } else {
                    s.append(myMaze[row][col].toString()).append(" ");
                }
            }
        }
        return s.toString();
    }
}