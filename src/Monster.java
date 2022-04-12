/*
* Monster.java
*
* TCSS 143 � Spring 2021
* Instructor - Tom Capaul
* Ian McLean
* Assignment 5
*/

import java.util.Random;


/**
* This class is the basis for
* for all monster characters, it
* inherits from dungeon character.
* @author Ian McLean
* @version 2.0 
*/
public class Monster extends DungeonCharacter {
    
    /**
    * The Monsters chance to heal.
    */
    private double myChanceToHeal;
    
    /**
    * The Monsters max heal.
    */
    private int myMaxHeal;
    
    /**
    * The Monsters min heal.
    */
    private int myMinHeal;
    
     
    /**
        A constructor which creates a Dungeon Character.
        
        @param theName - the characters name.
        @param theHealthPoints - the characters health.
        @param theMinAttack - the minimum damage the character can do.
        @param theMaxAttack - the maximum damage the character can do.
        @param theHitChance - the chance for the character to land a hit.
        @param theAttackSpeed - the attack speed of the character.
        @param theHealChance - the monsters chance to heal after attack.
        @param theMaxHeal - the maximum health points a monster can heal.
        @param theMinHeal - the minimum health points a monster can heal.
    */
    public Monster(final String theName, final int theHealthPoints, 
        final int theMinAttack, final int theMaxAttack, 
        final double theHitChance, final int theAttackSpeed, 
        final double theHealChance, final int theMaxHeal, 
        final int theMinHeal) {
    
        super(theName, theHealthPoints, theMinAttack, 
            theMaxAttack, theHitChance, theAttackSpeed);
        
        setHealChance(theHealChance);
        setMaxHeal(theMaxHeal);
        setMinHeal(theMinHeal);
    }

    /**
        This method gets the max heal points 
        of the monster.
        
        @return the chance to heal.
    */
    protected int getMaxHeal() {
        return myMaxHeal;
    }
    
    /**
        This method gets the min heal points
        of the monster.
        
        @return the minimum heal points.
    */
    protected int getMinHeal() {
        return myMinHeal;
    }
    
    /**
        This method sets the heal chance 
        of the monster.
        
        @param theHealChance - the chance to heal.
    */    
    protected void setHealChance(final double theHealChance) {
        myChanceToHeal = theHealChance;
    }
    
    /**
        This method sets the max meal points
        of the monster.
        
        @param theMaxHeal - the maximum heal points.
    */
    protected void setMaxHeal(final int theMaxHeal) {
        myMaxHeal = theMaxHeal;
    }
    
    /**
        This method sets the min meal points
        of the monster.
        
        @param theMinHeal - the minimum heal points.
    */
    protected void setMinHeal(final int theMinHeal) {
        myMinHeal = theMinHeal;
    }
    
     /**
        This method subtracts the health from a monster
        being attacked.

      @param theDamage - the damage done to the character.
    */
    @Override
    protected void subtractHealth(final int theDamage) {
        Random rand = new Random();
         
        Random heal = new Random();
         
        int health = getHealth();  
        
        health -= theDamage;
            
        setHealth(health);
         
        if ((rand.nextFloat() <= myChanceToHeal) 
            && !alive()) {
            int theHeals = heal.nextInt((getMaxHeal() - getMinHeal()) + 1) + getMinHeal();
            setHealth(getHealth() + theHeals);
            System.out.print(getName() 
                + " absorbed the attack energy and used it to gain "
                + theHeals + " health! \n\n");
        }
    }
}