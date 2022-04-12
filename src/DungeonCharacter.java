/*
* DungeonCharacter.java
*
* TCSS 143 – Spring 2021
* Instructor - Tom Capaul
* Ian McLean
* Assignment 5
*/

import java.util.Random;

/**
* This class is the basis for
* all dungeon characters. It 
* is the base template for the 
* average dungeon hero or monster.
* @author Ian McLean
* @version 2.0 
*/
public class DungeonCharacter {
   
    /**
    * The characters name.
    */
    private String myName;
   
    /**
    * The characters health.
    */
    private int myHealthPoints;
    
    /**
    * The characters max health.
    */
    private int myMaxHealth;
   
    /**
    * The attack speed.
    */
    private int myAttackSpeed;
    
    /**
    * The characters minimum damage.
    */
    private int myMinDamage;
   
    /**
    * The characters maximum damage.
    */
    private int myMaxDamage;
    
    /**
    * The characters chance to land a successful hit.
    */
    private double myHitChance;
    
    /**
        A constructor which creates a Dungeon Character.
        
        @param theName - the characters name.
        @param theHealthPoints - the characters health.
        @param theMinDamage - the minimum damage the character can do.
        @param theMaxDamage - the maximum damage the character can do.
        @param theHitChance - the chance for the character to land a hit.
        @param theAttackSpeed - the attack speed of the character.
    */
    protected DungeonCharacter(final String theName, final int theHealthPoints, 
        final int theMinDamage, 
        final int theMaxDamage, final double theHitChance, 
        final int theAttackSpeed) {
    
        setName(theName);
        setHealth(theHealthPoints);
        setMaxHealth(theHealthPoints);
        setMinDamage(theMinDamage);
        setMaxDamage(theMaxDamage);
        setHitChance(theHitChance);
        setAttackSpeed(theAttackSpeed);
    }
   
    /**
        This method sets the name of the character.
        
        @param theName - the characters name.
    */
    protected final void setName(final String theName) {
        
        myName = theName;
    }
    
    /**
        This method sets the health of the character.
        
        @param theHealthPoints - the characters health.

    */ 
    protected final void setHealth(final int theHealthPoints) {
        myHealthPoints = theHealthPoints;
    }
    
    protected final void setMaxHealth(final int theHealthPoints) {
        myMaxHealth = theHealthPoints;
    }
    
    /**
        This method sets the minimum damage the character can do.
        
        @param theMinDamage - the minimum damage the character can do.
    */
    protected final void setMinDamage(final int theMinDamage) {
        myMinDamage = theMinDamage;
    } 
    
    /**
        This method sets the maximum damage the character can do.
        
        @param theMaxDamage - the maxiumum damage the character can do.
    */
    protected final void setMaxDamage(final int theMaxDamage) {
        myMaxDamage = theMaxDamage;
    }
    
    /**
        This method sets the hit chance a character can do.
        
        @param theHitChance - the chance a character has to land a hit.
    */
    protected final void setHitChance(final double theHitChance) {
        myHitChance = theHitChance;
    }
    
    /**
        This method sets the attack speed for a character.
        
        @param theAttackSpeed - the characters attack speed.
    */
    protected final void setAttackSpeed(final int theAttackSpeed) {
        myAttackSpeed = theAttackSpeed;
    }
    
    /**
        This method returns the characters name.
        
        @return the name.
    */
    protected final String getName() {
        return myName;
    }
    
    /**
        This method returns the characters health.
        
        @return the health.
    */
    protected final int getHealth() {
        return myHealthPoints;
    }
    
    /**
        This method returns the characters max health.
        
        @return the max health.
    */
    protected final int getMaxHealth() {
        return myMaxHealth;
    }
    
    /**
        This method returns the characters attack speed.
        
        @return the attack speed.
    */
    protected final int getAttackSpeed() {
        return myAttackSpeed;
    }
    
    /**
        This method returns the characters minimum damage.
        
        @return the minimum damage.
    */
    protected final int getMinDamage() {
        return myMinDamage;
    }
    
    /**
        This method returns the characters maximum damage.
        
        @return the maximum damage.
    */
    protected final int getMaxDamage() {
        return myMaxDamage;
    }
    
    /**
        This method returns the characters hit chance.
        
        @return the hit chance.
    */
    protected final double getHitChance() {
        return myHitChance;
    }
    
    /**
        This method returns if the character is alive or dead.
        
        @return if the character is alive.
    */
    protected boolean alive() {
        return myHealthPoints <= 0;
    }
    
    /**
        This method subtracts the health from a character
        being attacked.

     @param theDamage - the damage done to the character.
    */
    protected void subtractHealth(final int theDamage) {
           
        int health = getHealth();  
        
        health -= theDamage;

        setHealth(Math.max(health, 0));
    }
    
    /**
        This method allows a character to attack another character.
        
        @param theOpponent - the character being attacked.
    */
    protected void attack(final DungeonCharacter theOpponent) {
    
        Random chance = new Random();
        
        Random damage = new Random();
        
        int damageDealt;
        
        if (chance.nextFloat() <= myHitChance) {
            damageDealt = damage.nextInt(getMaxDamage()) + getMinDamage();
            
            reportHealth(damageDealt);
                        
            theOpponent.subtractHealth(damageDealt);
            
            if (theOpponent.getHealth() > 0) {
                System.out.println(theOpponent.getName() + "'s health is now " 
                    + theOpponent.getHealth() + "!\n");
            } else {
                System.out.println(theOpponent.getName() + " has fainted!\n");
            }
        }
        else {
            System.out.println(getName() + " missed their attack!\n");   
        }  
    }
    
    /**
    *   This method reports the damage done by the character.
    *   @param theDamage - the damage dealt.
     */
    protected void reportHealth(final int theDamage) {
    
        System.out.println(getName() + " dealt " + theDamage + " damage!\n");
        
    }
}