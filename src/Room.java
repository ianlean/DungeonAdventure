/*
* Room.java
*
* TCSS 143 – Spring 2021
* Instructor - Tom Capaul
* Ian McLean
* Assignment 5
*/

import java.util.*;

/**
* This class represents
* a room.
* @author Ian McLean
* @version 1.0 
*/
public class Room {
	
    /**
       Whether there is a monster in the room or not.
    */    
    private boolean myMonsterBoolean;
   
    /**
       Whether there is a healing potion.
    */
    private boolean myHealingPotion;
   
    /**
       Whether there is a crown.
    */
    private boolean myCrown;
   
    /**
       If this is an entrance or not.
    */
    private boolean myEntrance;
   
    /**
       If this is an exit or not.
    */
    private boolean myExit;
   
    /**
       If the door is open or not.
    */
    private boolean myDoor;
   
    /**
       What kind of monster is in the room.
    */
    private final Monster myMonster;
	
    /**
       A constructor which creates a room.
    */
    public Room() { //normal room constructor
       
	    // generates if we have a monster
        myMonsterBoolean = setMonster(); 
       
        if (myMonsterBoolean) {
            myMonster = generateMonster();
        } else {
            myMonster = null;
        } 
       
        myHealingPotion = setHealingPotion(); 	
    }

    /**
       Generates a monster.
       
       @return the monster.
    */
    protected Monster generateMonster() {
       
        Random randomMonster = new Random();
          
        int monsterSelection = randomMonster.nextInt(3) + 1;
         
        Monster theEnemy;
         
        if (monsterSelection == 1) {
            theEnemy = new Ogre();
        } else if (monsterSelection == 2) {
            theEnemy = new Gremlin();
        } else {
            theEnemy = new Skeleton();
        }
        return theEnemy;
    }
    
     /**
        Decides if this room will have a monster or not.
        
        @return if there is a monster in this room.
    */
    protected boolean setMonster() {
		
        Random rand = new Random();

        return rand.nextFloat() <= 0.30;
    }
   
   /**
      Decides if this room has a healing potion.
      
      @return if there is a healing potion.
   */
    protected boolean setHealingPotion() {
        Random rand = new Random();

        return rand.nextFloat() <= 0.20;
    }
   
   /**
      Return if there is a potion.
      
      @return if there is a potion or not.
   */
    protected boolean hasPotion() {
        return myHealingPotion;
    } 
   
   /**
      Return if there is a monster.
      
      @return if there is a monster or not.
   */
    protected boolean getMonsterBoolean() {
        return myMonsterBoolean;
    }
   
   /**
      Return the kind of monster.
      
      @return the kind of monster (null if none).
   */
    protected Monster getMonster() {
        return myMonster;
    }
   
   /**
      Returns whether this room is an exit.
      
      @return if this room is an exit.
   */
    protected boolean isExit() {
        return myExit;
    }
   
   /**
      Returns whether this room is an entrance.
      
      @return if this room is an entrance.
   */
    protected boolean isEntrance() {
        return myEntrance;
    }
   
   /**
      Returns whether this room has a crown.
      
      @return if this room has a crown.
   */
    protected boolean hasCrown() {
        return myCrown;
    }
   
   /**
      Turns this room into an exit.
   */
    protected void makeExit() {
        myExit = true;
    }
   
   /**
      Turns this room into an entrance..
   */
    protected void makeEntrance() {
        myEntrance = true;
    }
   
   /**
      Gives this room a crown.
   */
    protected void giveCrown() {
        myCrown = true;
    }
   
   /**
      Takes this rooms monster away.
   */
    protected void takeMonster() {
        myMonsterBoolean = false;
    }
  
   /**
      Takes the crown out of this room.
   */
    protected void takeCrown() {
        myCrown = false;
    }
   
   /**
      Take the potion out of this room.
   */
    protected void takePotion() {
        myHealingPotion = false;
    }
   
   /**
      Opens the door making the contents visible.
   */
    protected void openDoor() {
        myDoor = true;
    }
   
   /**
      Tells you whether this rooms door is open.
      
      @return if the door is open or not.
   */
    protected boolean isOpen() {
        return myDoor;
    }
   
   /**
      The toString method for this class.
      
      @return the string representation of the room.
   */
    public String toString() {
        String s;
        if (isOpen()) {
            if (!hasMultiple()) {
           
                if (isEntrance()) {
                    s = "I";
                } else if (isExit()) {
                    s = "O";
                } else if (hasCrown()){
                    s = "!";
                } else if (getMonsterBoolean()) {
                    s = "M";
                } else if (hasPotion()) {
                    s = "H";
                } else {
                    s = "E";
                }
            } else {
                s = "X";
            }
        } else {
            s = "?";
        }
        return s;   
    }
   
   /**
      Figures out if this room has multiple elements.
      
      @return if this room has more than on thing in it.
   */
    public boolean hasMultiple() {
       
        if (hasCrown() && getMonsterBoolean() && hasPotion()) {
            return true;
        } else if (hasCrown() && getMonsterBoolean()) {
            return true;
        } else if (hasCrown() && hasPotion()) {
            return true;
        } else return hasPotion() && getMonsterBoolean();
    }

}