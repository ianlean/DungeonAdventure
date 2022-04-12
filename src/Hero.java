/*
* Hero.java
*
* TCSS 143 � Spring 2021
* Instructor - Tom Capaul
* Ian McLean
* Assignment 5
*/

import java.util.Random;

/**
* This class is the basis for
* for all hero characters, it
* inherits from dungeon character.
* @author Ian McLean
* @version 2.0 
*/
public class Hero extends DungeonCharacter {
    /**
    * The Heros chance to dodge/block.
    */
    private double myDodgeChance;
    
    /**
    * The Heros chance to get their special ability.
    */
    private double mySpecialChance;
    
    /**
    * The Heros max special ability damage or health regen.
    */
    private int mySpecialMaxDamage;
    
    /**
    * The Heros min special ability damage or health regen.
    */
    private int mySpecialMinDamage;
    
    /**
    * The amount of healing potions contained by the Hero.
    */
    private int myHealingPotions;
    
    /**
    * Whether the hero has the crown.
    */
    private int myCrownCount = 0;
    
    /**
        A constructor which creates a Dungeon Character.
        
        @param theName - the characters name.
        @param theHealthPoints - the characters health.
        @param theMinAttack - the minimum damage the character can do.
        @param theMaxAttack - the maximum damage the character can do.
        @param theHitChance - the chance for the character to land a hit.
        @param theAttackSpeed - the attack speed of the character.
        @param theDodgeChance - The Heros chance to dodge/block.
        @param theSpecialMaxDamage - The Heros max special ability damage or health regen.
        @param theSpecialMinDamage - The Heros min special ability damage or health regen.
        @param theSpecialChance - The Heros chance to get their special ability.
    */
    public Hero(final String theName, final int theHealthPoints, final int theMinAttack, 
        final int theMaxAttack, final double theHitChance, final int theAttackSpeed, 
        final double theDodgeChance, final int theSpecialMaxDamage, 
        final int theSpecialMinDamage, final double theSpecialChance) {
        
        super(theName, theHealthPoints, theMinAttack, theMaxAttack, 
            theHitChance, theAttackSpeed);
        
        setDodgeChance(theDodgeChance);
        setSpecialMaxDamage(theSpecialMaxDamage);
        setSpecialMinDamage(theSpecialMinDamage);
        setSpecialChance(theSpecialChance);
    }

    /**
    *   This method returns the heros chance for a special ability.
    *
    *   @return the heros chance for a special ability.
    */
    protected double getSpecialChance() {
        return mySpecialChance;
    }
    
    /**
    *   This method returns the count of healing potions.
    *
    *   @return the heros healing potion count.
    */
    protected int getHealingPotions() {
        return myHealingPotions;
    }
    
    /**
    *   This method adds healing potions.
    *  
    *   @param theAmount - the amount of healing potions to add.
    */
    protected void addHealingPotions(int theAmount) {
        myHealingPotions += theAmount;
    }
    
    /**
    *   This method returns the characters special max damage.
    *
    *   @return the special max damage.
    */
    protected int getSpecialMaxDamage() {
        return mySpecialMaxDamage;
    }
    
    /**
    *   This method returns the characters special min damage.
    *
    *   @return the special min damage.
    */
    protected int getSpecialMinDamage() {
        return mySpecialMinDamage;
    }
    
    /**
    *   This method set the characters block/dodge chance.
    *
    * @param theDodgeChance - the chance to block/dodge.
    */
    protected void setDodgeChance(final double theDodgeChance) {
        myDodgeChance = theDodgeChance;
    }
    
    /**
    *   This method sets the characters special chance.
    *
    *   @param theSpecialChance - the chance to do a special ability.
    */
    protected void setSpecialChance(final double theSpecialChance) {
        mySpecialChance = theSpecialChance;
    }
    
    /**
    *   This method sets the characters special max damage.
    *
    *   @param theSpecialMaxDamage the max damage or health regen from a special ability.
    */
    protected void setSpecialMaxDamage(final int theSpecialMaxDamage) {
        mySpecialMaxDamage = theSpecialMaxDamage;
    }
    
    /**
    *   This method sets the characters special min damage.
    *
    *   @param theSpecialMinDamage - the min damage or health 
    *   regen from a special ability.
    */
    protected void setSpecialMinDamage(final int theSpecialMinDamage) {
        mySpecialMinDamage = theSpecialMinDamage;
    }
    
    /**
    *   This method adds a crown piece to the heros inventory.
    */
    protected void addCrownPiece() {
        myCrownCount += 1;
    }
    
    /**
    *   This method returns how many crown pieces
    *   the hero has.
    *
    *   @return the crown pieces.
    */
    protected int getCrownCount() {
        return myCrownCount;
    }
    
    /**
    *   This method sees if the characters blocking
    *   abiliy will be used.
    *
    *   @return whether the hero will block or not.
    */
    protected boolean block() {
        
        Random block = new Random();

        return block.nextFloat() <= myDodgeChance;
    }
    
    /**
    *   This method subtracts the health from a character
    *   being attacked.
    *    
    *   @param theDamage - the damage done to the character.
     */
    @Override
    protected void subtractHealth(final int theDamage) {
        if (!block()) {
            int health = getHealth();  
        
            health -= theDamage;
            
            setHealth(health);

        } else {
            System.out.println(getName() + " blocked the attack! ");
        }
    }
    
    /**
        This method takes health away for running away.

     */
    protected void runAwayDamage() {
        int health = getHealth() - 30;
        setHealth(health);    
    }

    /**
        This method allows a character to attack another character.
        
        @param theOpponent - the character being attacked.
    */
    @Override
    protected void attack(final DungeonCharacter theOpponent) {
        
        Random chance = new Random();
        
        Random damage = new Random();
        
        int damageDealt;
        
        if (chance.nextFloat() <= this.getHitChance()) {
        
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
    *   Allows the hero to take a potion.
    */
    protected void takePotion() {
        Random rand = new Random();
        int heals = rand.nextInt(11) + 5;
        
        if (getHealth() <= getMaxHealth() - 15) {
            System.out.println("You healed " + heals + " health");
            this.setHealth(getHealth() + heals);
            this.addHealingPotions(-1);
        } else {
            System.out.println("You have too much health to take this potion");
        }
    }
    
    /**
        This method allows a character to attack another character.
        
        @return the heros string representation
    */
    public String toString() {
        String s = "";
        s += getName() + "'s stats:\n";
        s += "Health: " + getHealth() + "\n";
        s += "Healing potions: " + getHealingPotions() + "\n";
        s += "Crown Pieces: " + getCrownCount() + "/2";
        
        return s;
    }
} 
           
