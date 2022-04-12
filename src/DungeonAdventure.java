/*
* DungeonAdventure.java
*
* TCSS 143 – Spring 2021
* Instructor - Tom Capaul
* Ian McLean
* Assignment 5
*/

import java.util.*;

/**
* This is the driver class
* for a Dungeon game
* it is the interactions between
* all dungeon character, dungeon
* and room classes.
* @author Ian McLean
* @version 1.0 
*/
public class DungeonAdventure {
     
    /**
        The driver for this program.
        
        @param theArgs - ignored in this program.
    */
    public static void main(final String [] theArgs) {
     
        displayIntro();
    
        String name = getName();
        
        Hero player;
        
        int characterChoice = selectHero();
          
        if (characterChoice == 1) {
            player = new Warrior(name);
        } else if (characterChoice == 2) {
            player = new Sorceress(name);
        } else {
            player = new Thief(name);
        }
        
        Dungeon myDungeon = new Dungeon();

        while (!player.alive()) {
            if (myDungeon.getHeroSpot().isExit()) {
                System.out.println("You have found the exit\n");
                if (player.getCrownCount() == 2) {
                    System.out.println("Would you like to leave you have the crown?\n");
                    winScreen();
                    break;
                } else {
                    System.out.println("You cannot exit, you have"
                        + " not found all of the crown.\n");
                }
            }
            
            if (player.getHealingPotions() >= 1 && canDrink(player)) {
                System.out.println("Before going to another room,"
                    + " would you like to take a healing potion?\n"
                    + "Type y or n: ");
                if (drink()) {
                    player.takePotion();
                }
            }
            
            promptDirection(player, myDungeon);
            
            askDirection(myDungeon, player);
        }
        if (player.alive()) {
            System.out.println("Yikes! Looks like you couldn't handle the dungeon");
            System.out.println("Better luck next time...");
            deathScreen();
        }   
    }
     
     /**
        Figures out the kind of hero the 
        user wants to be.
        
        @return the choice of character.
    */
    public static int selectHero() {
        System.out.println("1) Warrior\n2) Sorceress\n3) Thief");
        System.out.println("Choose your character: ");

        return getIntInRange();
    }
    
    /**
        Asks the user which directiong they would like to go.
        
        @param thePlayer - the player moving direction.
        @param theDungeon - the dungeon being moved through
    */
    public static void promptDirection(final Hero thePlayer, final Dungeon theDungeon) {
        System.out.println("---------------------------");
        System.out.println("CHOOSE YOUR DIRECTION:\n");
        System.out.println("N, E, S, W\n");
        System.out.println(theDungeon.toString() + "\n" + thePlayer.toString());
    }
    
    /**
        Asks the user if they would like to use a 
        health potion.
        
        @return if the user wants to use a health potion.
    */
    public static boolean drink() {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        while (!s.equalsIgnoreCase("y") && !s.equalsIgnoreCase("n")) {
            System.out.println("Try again: Type y or n");
            s = scan.next();
        }
        return s.equalsIgnoreCase("y");
    }
    
    /**
        Checks if the hero wants to drink or not.
        
        @param thePlayer - the character drinking the potion.
        @return if the player wants to drink or not.
    */
    public static boolean canDrink(final Hero thePlayer) {
        boolean canDrink;
        canDrink = thePlayer.getHealth() <= thePlayer.getMaxHealth() - 15;
        return canDrink;
    }
    
    /**
        Chooses the direction the user wants to go.
        
        @param theDungeon - the dungeon object we are moving through.
        @param thePlayer - the hero which is moving through the dungeon.
    */
    public static void askDirection(final Dungeon theDungeon, final Hero thePlayer) {
        boolean moved = false;
        Scanner scan = new Scanner(System.in);
        String direction = scan.next();
        while (!moved) {
            if (direction.equalsIgnoreCase("w")) {
           
                theDungeon.move(theDungeon.getY(), theDungeon.getX() - 1);
                moved = true;
            } else if (direction.equalsIgnoreCase("e")) {
           
                theDungeon.move(theDungeon.getY(), theDungeon.getX() + 1);
                moved = true;
           
            } else if (direction.equalsIgnoreCase("s")) {
           
                theDungeon.move(theDungeon.getY() + 1, theDungeon.getX());
                moved = true;
           
            } else if (direction.equalsIgnoreCase("n")) {
              
                theDungeon.move(theDungeon.getY() - 1, theDungeon.getX());
                moved = true;
           
            } else {
                System.out.println("not a valid direction\n");
                promptDirection(thePlayer, theDungeon);
                direction = scan.next();
            }
        } 
        
        System.out.println(theDungeon);
        if (theDungeon.getHeroSpot().hasCrown()) {
            System.out.println("A CROWN PIECE IS IN THIS ROOM!\n");
            theDungeon.getHeroSpot().takeCrown();
            thePlayer.addCrownPiece();
        }
        
        if (theDungeon.getHeroSpot().hasPotion()) {
            System.out.println("The hero found a potion in this room!\n");
            thePlayer.addHealingPotions(1);
            theDungeon.getHeroSpot().takePotion();
        }
        
        if (theDungeon.getHeroSpot().getMonsterBoolean()) {
            System.out.println("There is a monster in this room! Time to fight!\n");
            startPlaying(theDungeon.getHeroSpot().getMonster(), thePlayer);
            if (theDungeon.getHeroSpot().getMonster().alive()) {
                theDungeon.getHeroSpot().takeMonster();       
            }
        }
    }
    
     /**
        This method begins the game  and
        each round by initiating the attacks.
       
        @param theEnemy - the opponent of the user.
        @param thePlayer - the users character.
    */
    public static void startPlaying(final Monster theEnemy, final Hero thePlayer) {

        System.out.println("Our hero will get the first attack!\n");
            
        while ((theEnemy.getHealth()) > 0 && (thePlayer.getHealth()) > 0) {
            Scanner scan = new Scanner(System.in);
            int count = 1;
            if (attackOrRun() == 1) {
                if (thePlayer.getAttackSpeed() > theEnemy.getAttackSpeed()) {
                    System.out.println("Our hero has the faster attack " 
                        + "speed and will get multiple attacks!\n");
                    while (count <= thePlayer.getAttackSpeed() 
                        && !theEnemy.alive()) {
                        System.out.println("Press any key to continue: ");
                        scan.next();
                        System.out.print("\nAttack #" + count + ": ");
                        thePlayer.attack(theEnemy);
                        count++;
                    }
                } else {
                    thePlayer.attack(theEnemy);
                }
                if (theEnemy.alive()) {
                    break;
                }
                System.out.println(theEnemy.getName() + " is now attacking!\n");
                theEnemy.attack(thePlayer);
                if (thePlayer.alive()) {
                    break;
                }

            } else {
                thePlayer.runAwayDamage();
                System.out.println("You lost 10 health for running away\n");
                break;
            }
        }  
    }

    /**
        Returns whether the player
        chooses to run away or continue 
        the fight.
        
        @return the number the player chose.
    */
    public static int attackOrRun() {
        System.out.println("1) Attack\n2) Run\n");
        System.out.println("Do you want to attack or run away?: ");

        return getRightChoice();
    }
    
     /**
        Gets a number in a specific range.
        
        @return the number.
    */
    public static int getIntInRange() {
        int choice = getInt(); 
        while (choice < 1 || choice > 3) {
            System.out.println("That option doesn't exist, please try again. ");
            System.out.println("Choose your character: ");
            choice = getInt();
        }
        return choice;
    }
     
    /**
        This method makes sure the users
        choice is valid.
        
        @return the chosen character.
    */
    public static int getRightChoice() {
        
        int choice = getIntForFight(); 
         
        while (choice < 1 || choice > 2) {
            System.out.println("That option doesn't exist, please try again. ");
            System.out.println("Do you want to attack or run away?: ");
            choice = getIntForFight();
        }
         
        return choice;    
    }
    
    /**
        This method makes sure the users
        typed in a number for their choice.
        
        @return the choice to run or fight.
    */
    public static int getIntForFight() {
    
        Scanner scanConsole = new Scanner(System.in);
         
           
        while (!scanConsole.hasNextInt()) {
            scanConsole.next();
            System.out.println("That is not a number, try again.");
            System.out.println("Do you want to attack or run away?: ");
        } 
        return scanConsole.nextInt();
    }

    
     /**
        Gets a number from the user.
        
        @return the number.
    */
    public static int getInt() {
        Scanner scanConsole = new Scanner(System.in);
        while (!scanConsole.hasNextInt()) {
            scanConsole.next();
            System.out.println("That is not a number, try again.");
            System.out.println("Choose your character: ");
        } 
        return scanConsole.nextInt(); 
    }
    
    /**
        This method asks the user
        for their characters name.
        
        @return the name the user chose.
    */
    public static String getName() {
         
        System.out.print("Enter your character name: ");

        return getString();
    }
    
    /**
        Returns a string from the user.
        
        @return the string entered by the user.
    */ 
    public static String getString() {
         
        Scanner scanConsole = new Scanner(System.in);
         
        return scanConsole.next();
    }

    /**
        Asks the user if they want to see the rules.
        
        @return if they want to see the rules or not.
    */
    public static boolean toDisplayRules() {
        boolean display = false;
        System.out.println("If you want to see the rules enter 1");
        System.out.println("If you're a pro, click any other button");
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        if (s.equals("1")) {
            display = true;
        }
        return display;
    }
    
    /**
        Displays the rules of the game.
    */
    public static void displayIntro() {
        entranceScreen();
        System.out.println("Welcome to Dungeon Adventure!\n");
        if (toDisplayRules()) {
            System.out.println("This is a game where a hero of your choice will");
            System.out.println("travel through a dungeon and search for " 
                + "the pieces of the crown of coding.\n");
            System.out.println("""
                    Every turn you have the option to go North, East, South or West into another room.
                    If you run into a wall, it is considered a valid move, and your character will stay in place.
                    if you have low enough health, you will be asked if you would like  to drink a potion.\s
                    YOU WILL BE ASKED EVERYTIME BETWEEN ROOMS, BUT THATS IT.
                    """);
            System.out.println("""
                    The Room your character is in will display: C
                    If a room you have been in is empty it will display: E
                    A room you ran away from a monster in will display: M
                    The Entrance will display: I
                    The Exit will display: O (once you find it)
                    **NOTE the exit and entrance will always be randomly generated
                    on the left and right sides of the dungeon.**""");
            System.out.println("Every room besides the " 
                + "exit and entrance has the chance " 
                + "of having a potion, monster or a piece to the crown in it!\n");
            System.out.println("Rules for Monsters: \n");
            System.out.println("If a monster is encountered in a " 
                + "room you have the option to " 
                + "run or battle the monster. "
                + "\nThe monster may be a skeleton, ogre or gremlin.");
            System.out.println("If you choose to run away YOU WILL LOSE 30 HEALTH "
                + "POINTS IN THE PROCESS");
            System.out.print("If you choose to attack your character "
                + "has a chance to hit a special or normal attack.\n");
            System.out.println("Your hero can also miss their attack. "
                + "Each hero has its own special attribute:\n");
            System.out.println("""


                    Warrior: 125 health, 35-60 damage, can land a crushing blow attack
                    """);
            System.out.println("Sorceress: 75 health, 25-45 damage, " 
                + "if injured sorceress can heal during her attacks\n");
            System.out.println("Thief: 75 health, 25-40 damage, " 
                 + "has the chance to do a sneak attack for every attack\n");
            System.out.println("""
                    You cannot exit the dungeon until you have found all of the pieces of the crown!

                    GOOD LUCK AND HAVE FUN!
                    """);
        }
    }
    
    /**
        Displays ASCII art if you die.
    */
    public static void deathScreen() {
        
        System.out.println("     _..--\"\"---.");
        System.out.println("     /           \".");
        System.out.println("     `            l");
        System.out.println("     |'._  ,._ l/\"");
        System.out.println("     |  _J<__/.v._/");
        System.out.println("      \\( ,~._,,,,-)");
        System.out.println("       `-' \\`,,j|");
        System.out.println("          \\_,____J");
        System.out.println("     .--.__)--(__.--.");
        System.out.println("    /  `-----..--'. j");
        System.out.println("    '.- '`--` `--' \\");
        System.out.println("   //  '`---'`  `-' \\");
        System.out.println("  //   '`----'`.-.-' \\");
        System.out.println("_//     `--- -'   ' | \\________");
        System.out.println("|  |         ) (      `.__.---- -'\\");
        System.out.println(" \\7          \\`-(               74\\\\\\");
        System.out.println(" ||       _  /`-(               l|//7__");
        System.out.println(" |l    ('  `-)-/_.--.          f''` -.-\"|");
        System.out.println(" |\\     l\\_  `-'    .'         |     |  |");
        System.out.println(" llJ   _ _)J--._.-('           |     |  l");
        System.out.println(" |||( ( '_)_  .l   \". _    ..__I     |  L");
        System.out.println(" ^\\\\\\||`'   \"   '\"-. \" )''`'---'     L.-'`-.._");
        System.out.println("      \\ |           ) /.              ``'`-.._``-.");
        System.out.println("      l l          / / |                      |''|");
        System.out.println("       \" \\        / /   \"-..__                |  |");
        System.out.println("       | |       / /          1       ,- t-...J_.'");
        System.out.println("       | |      / /           |       |  |");
        System.out.println("       J  \\  /\\\"  (            l       |  |");
        System.out.println("       | ().'`-()/            |       |  |");
        System.out.println("      _.-\"_.____/             l       l.-l");
        System.out.println("      _.-\"_.+\"|              /        \\.  \\ ");
        System.out.println("/\"\\.-\"_.-\"  | |             /          \\   \\ ");
        System.out.println("\\_   \"      | |            1            | `'|");
        System.out.println("  |ll       | |            |            i   |");
        System.out.println("  \\\\\\       |-\\           \\j ..          L,,'. `/");
        System.out.println(" __\\\\\\     ( .-\\           .--'    ``--../..'      '-..");
        System.out.println("|   `'''`----`\\\\ .....--'''");
        System.out.println("              \\\\                           -");
    }
    
    /**
        Displays ASCII art in the intro.
    */
    public static void entranceScreen() {
    
        
        System.out.println("______ _   _ _   _ _____  _____ _____ _   _    ___ ______ _   _ _____ _   _ _____ _   _______ _____"); 
        System.out.println("|  _  \\ | | | \\ | |  __ \\|  ___|  _  | \\ | |  / _ \\|  _  \\ | | |  ___| \\ | |_   _| | | | ___ \\  ___|");
        System.out.println("| | | | | | |  \\| | |  \\/| |__ | | | |  \\| | / /_\\ \\ | | | | | | |__ |  \\| | | | | | | | |_/ / |__");  
        System.out.println("| | | | | | | . ` | | __ |  __|| | | | . ` | |  _  | | | | | | |  __|| . ` | | | | | | |    /|  __|"); 
        System.out.println("| |/ /| |_| | |\\  | |_\\ \\| |___\\ \\_/ / |\\  | | | | | |/ /\\ \\_/ / |___| |\\  | | | | |_| | |\\ \\| |___"); 
        System.out.println("|___/  \\___/\\_| \\_/\\____/\\____/ \\___/\\_| \\_/ \\_| |_/___/  \\___/\\____/\\_| \\_/ \\_/  \\___/\\_| \\_\\____/"); 
                                                                                                    
                                                                                                    
 
    }
    
    /**
        Displays ASCII art if you win.
    */
    public static void winScreen() {
    
            
        System.out.println("__   _______ _   _   _    _  _____ _   _   _"); 
        System.out.println("\\ \\ / /  _  | | | | | |  | ||  _  | \\ | | | |");                  
        System.out.println(" \\ V /| | | | | | | | |  | || | | |  \\| | | |");                                                                                                     
        System.out.println("  \\ / | | | | | | | | |/\\| || | | | . ` | | |");
        System.out.println("  | | \\ \\_/ / |_| | \\  /\\  /\\ \\_/ / |\\  | |_|");
        System.out.println("  \\_/  \\___/ \\___/   \\/  \\/  \\___/\\_| \\_/ (_)"); 
  
  
        System.out.println("                           ,--.");
        System.out.println("                          {    }");
        System.out.println("                          K,   }");
        System.out.println("                         /  `Y`");
        System.out.println("                    _   /   /");
        System.out.println("                   {_'-K.__/v");
        System.out.println("                     `/-.__L.\"_");
        System.out.println("                     /  ' /`\\_}");
        System.out.println("                    /  ' /     ");
        System.out.println("            ____   /  ' /");
        System.out.println("     ,-'~~~~    ~~/  ' /_v");
        System.out.println("   ,'             ``~~~%%',");
        System.out.println("  (                     %  Y");
        System.out.println(" {                      %% I");
        System.out.println("{      -                 %  `.");
        System.out.println("|       ',                %  )");
        System.out.println("|        |   ,..__      __. Y");
        System.out.println("|    .,_./  Y ' / ^Y   J   )|");
        System.out.println("\\           |' /   |   |   ||");
        System.out.println(" \\          L_/    . _ (_,.'(");
        System.out.println("  \\,   ,      ^^\"\"' / |      )");
        System.out.println("    \\_  \\          /,L]     /");
        System.out.println("      '-_`-,       ` `   ./`");
        System.out.println("         `-(_            )");
        System.out.println("             ^^\\..___,.--`");
    }
}