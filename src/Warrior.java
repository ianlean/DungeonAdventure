/*
* Warrior.java
*
* TCSS 143 � Spring 2021
* Instructor - Tom Capaul
* Ian McLean
* Assignment 5
*/

import java.util.Random;

/**
* This class represents a
* warrior dungeon character.
* @author Ian McLean
* @version 2.0 
*/
public class Warrior extends Hero {
    
    /**
        A constructor which creates a Thief with generic stats.
        
        @param theName - the characters name.
    */
    protected Warrior(final String theName) {
        
        super(theName, 125, 35, 60, 0.8, 4, 0.2, 175, 75, 0.4);
    }

    /**
        This method allows a warrior to attack another character.
        
        @param theOpponent - the character being attacked.
    */
    @Override
    protected void attack(final DungeonCharacter theOpponent) {
        
        Random chance = new Random();
        
        Random damage = new Random();
        
        int damageDealt;
        
        if (chance.nextFloat() <= this.getHitChance()) {
            
            if (chance.nextFloat() <= getSpecialChance()) {
                damageDealt = damage.nextInt((getSpecialMaxDamage() 
                    - getSpecialMinDamage()) + 1) + getSpecialMinDamage();
                System.out.println(getName() 
                     + " landed a crushing blow attack! \n");
            } else {
                damageDealt = damage.nextInt((getMaxDamage()
                     - getMinDamage()) + 1) + getMinDamage();
            }
            
            reportHealth(damageDealt);
            
            theOpponent.subtractHealth(damageDealt);
            
            if (theOpponent.getHealth() > 0) {
                System.out.println(theOpponent.getName() 
                    + "'s health is now " + theOpponent.getHealth() + "!\n");
            } else {
                System.out.println(theOpponent.getName() 
                    + " has fainted!\n");
            }
        }
        else {
            System.out.println(getName() 
                + " missed their attack!\n");   
        }  
    }

}