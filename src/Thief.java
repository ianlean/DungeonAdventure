/*
* Thief.java
*
* TCSS 143 � Spring 2021
* Instructor - Tom Capaul
* Ian McLean
* Assignment 5
*/

import java.util.Random;

/**
* This class represents a
* thief dungeon character.
* @author Ian McLean
* @version 2.0 
*/
public class Thief extends Hero {

    /**
        A constructor which creates a Thief with generic stats.
        
        @param theName - the characters name.
    */
    public Thief(final String theName) {
    
        super(theName, 75, 20, 40, 0.8, 6, 0.4, 40, 20, 0.4);
    
    }

    /**
        This method allows a thief to attack another character.
        
        @param theOpponent - the character being attacked.
    */
    @Override 
    protected void attack(final DungeonCharacter theOpponent) {
        
        Random chance = new Random();
        
        Random damage = new Random();
        
        int count = 1;
        
        int damageDealt;
        
        
        while (count == 1) {
            double specificAttack = chance.nextFloat();
            double getCaught = 0.20;
            if (specificAttack <= getCaught) {
                System.out.println(getName() 
                    + " was caught sneaking and missed their attack! \n");
                count++;
        
            } else if (specificAttack <= getSpecialChance()) {
                
                System.out.println(getName() 
                    + " landed a sneak attack and will get a second attack! \n");
                damageDealt = damage.nextInt((getMaxDamage() 
                    - getMinDamage()) + 1) + getMinDamage();
               
                reportHealth(damageDealt);
                theOpponent.subtractHealth(damageDealt);
                if (theOpponent.getHealth() > 0) {
                    System.out.println(theOpponent.getName() 
                        + "'s health is now " + theOpponent.getHealth() + "!\n");
                } else {
                    System.out.println(theOpponent.getName() + " has fainted!\n");
                    break;
                }
            
            } else if (specificAttack <= getHitChance()) {
                damageDealt = damage.nextInt((getMaxDamage() 
                    - getMinDamage()) + 1) + getMinDamage();
               
                reportHealth(damageDealt);
                theOpponent.subtractHealth(damageDealt);
                
                if (theOpponent.getHealth() > 0) {
                    System.out.println(theOpponent.getName() 
                        + "'s health is now " + theOpponent.getHealth() + "!\n");
                } else {
                    System.out.println(theOpponent.getName() + " has fainted!\n");
                }
                
                count++;
            } else {
                System.out.println(getName() + " missed their attack!\n");
                count++;
            }
        }
    }
}
