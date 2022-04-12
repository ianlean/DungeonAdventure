/*
* Sorceress.java
*
* TCSS 143 � Spring 2021
* Instructor - Tom Capaul
* Ian McLean
* Assignment 5
*/

import java.util.Random;

/**
* This class represents a
* sorceress dungeon character.
* @author Ian McLean
* @version 2.0 
*/
public class Sorceress extends Hero {
    
    /**
        A constructor which creates a Sorceress with generic stats.
        
        @param theName - the characters name.
    */
    public Sorceress(final String theName) {
        
        super(theName, 75, 25, 45, 0.7, 5, 0.3, 50, 25, 0.3);    
    }

    /**
        This method allows a sorceress to attack another character.
        
        @param theOpponent - the character being attacked.
    */
    @Override
    protected void attack(final DungeonCharacter theOpponent) {
        
        Random chance = new Random();
        
        Random damage = new Random();
        
        int damageDealt;
        
        if (chance.nextFloat() <= this.getHitChance()) {
            
            if (chance.nextFloat() <= getSpecialChance() && getHealth() != 75) {
                damageDealt = damage.nextInt((getMaxDamage()
                     - getMinDamage()) + 1) + getMinDamage();
                int healthRegen = heal();
                int healthSetter = getHealth() + healthRegen;
                if ((getHealth() + healthRegen) > 75) {
                    healthSetter = 75;
                }
                setHealth(healthSetter);
                System.out.print(getName() 
                    + " used magic to heal " + healthRegen + " health! \n");
                System.out.println(" " + getName() 
                    + " now has " + getHealth() + " health. \n");
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
        
        } else {
            System.out.println(getName()
                 + " missed their attack!\n");   
        }  
    }
    
    /**
        This method allows a sorceress to heal their health.
        
        @return the health that was regenerated.
    */   
    protected int heal() {                                         
        
        Random heals = new Random();

        return heals.nextInt((getSpecialMaxDamage()
            - getSpecialMinDamage()) + 1) + getSpecialMinDamage();

    }
}