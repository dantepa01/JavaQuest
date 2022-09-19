import java.lang.Math;
import java.util.*;

public class Encounters{

  private int chance;

  public Encounters(){
    Scanner mando = new Scanner(System.in);
    System.out.println("This dungeon features random encounters. \nHow often  would you like these to occur?\n1. Not Often 2. Often 3. Very Often 4. Always");
    chance = mando.nextInt();
  }

  public boolean checkEncounter(int i){
    if (chance == 1){
      if (i <= 5){
        if((int)(Math.random()*30) == 1){
          return true;
        }
        else{
          return false;
        }
      }
      else if(i <= 10){
        if((int)(Math.random()*20) == 1){
          return true;
        }
        else{
          return false;
        }
      }
      else if(i <= 15){
        if((int)(Math.random()*10) == 1){
          return true;
        }
        else{
          return false;
        }
      }
    }
    else if (chance == 2){
      if (i <= 5){
        if((int)(Math.random()*20) == 1){
          return true;
        }
        else{
          return false;
        }
      }
      else if(i <= 10){
        if((int)(Math.random()*10) == 1){
          return true;
        }
        else{
          return false;
        }
      }
      else if(i <= 15){
        if((int)(Math.random()*5) == 1){
          return true;
        }
        else{
          return false;
        }
      }
    }
    else if(chance == 3){
      if (i <= 5){
        if((int)(Math.random()*10) == 1){
          return true;
        }
        else{
          return false;
        }
      }
      else if(i <= 10){
        if((int)(Math.random()*5) == 1){
          return true;
        }
        else{
          return false;
        }
      }
      else if(i <= 15){
        if((int)(Math.random()*2) == 1){
          return true;
        }
        else{
          return false;
        }
      }
    }
    return true;
  }

  public String getEncounter(ArrayList<Player> p){
    int crazyWackyStuff = (int)(Math.random()*7)+1;
    int pickPlayer = (int)(Math.random()*4);
    if(crazyWackyStuff == 1){
      loseLevel(p.get(pickPlayer));
    }
    else if(crazyWackyStuff == 2){
      shrine(p.get(pickPlayer));
    }
    else if(crazyWackyStuff == 3){
      return "Revive Enemy";
    }
    else if(crazyWackyStuff == 4){
      coinFlip(p);
    }
    else if(crazyWackyStuff == 5){
      treasure(p.get(pickPlayer));
    }
    else if(crazyWackyStuff == 6){
      int portalInt = portal(p.get(p.size()-1));
      if(portalInt == 1){
        return "Remove Player";
      }
      else if (portalInt == 2){
        return "Move forward";
      }
    }
    else if(crazyWackyStuff == 7){
      if(meetPlayer(p.get(pickPlayer))){
        String names[] = {"Aleena", "Marcus", "Dimitri", "Dismus", "Bismuth", "Angeline", "Ralph"};
        p.add(new Player(names[(int)(Math.random()*7)], "Random"));
      }
    }
    return "";
  }

  public void loseLevel(Player p){
    System.out.println("A lich minion springs from a crack in the wall and starts to suffocate " + p.getName() + "...\nYour party members defeat it, but...");
    if(p.loseLevel()){
      System.out.println("..." + p.getName() + " suffers by losing a level!\n");
    }
    else{
      System.out.println("nothing happed! " + p.getName() + " is too weak to have any life drained!\n");
    }
  }

  public void shrine(Player p){
    Scanner mando = new Scanner(System.in);
    System.out.println("As you continue into the depths, " + p.getName() + " finds a shrine...");
    int statue = (int)(Math.random()*4);
    if((int)(Math.random()*2) == 1){
      System.out.println("The statue is of a demon with an outstretched hand." + p.getName() + " feels it calling to them. Will they reach out?\n1. Yes\t\t2. No");
      if(mando.nextInt() == 2){
        System.out.println(p.getName() + " pulls back and recognizes the dangers that might be hidden. They rejoin their party in safety and move forward.\n");
      }
      else{
        System.out.println(p.getName() + " slowly is lifted into the air by a magical force...");
        if(statue == 0){
          System.out.println("The eyes of the statue glow green, and your party watches as every cut, scape, and any other minor to major injury is completely reversed on " + p.getName() + "\'s body.");
          p.healPlayer(999);
        }
        else if (statue == 1){
          System.out.println("...and their equipment is destroyed! Shrapnel from what once was armor is blasted all over the room, and " + p.getName() + " is left completely defenseless. They stand bare with simply their weapon of choice.\n");
          p.setDefense(0);
        }
        else if(statue == 2){
          System.out.println("The statue's eyes start to glow blue, and your party feels its thirst for knowledge...\nYour party watches as " + p.getName() + " has some of their energy drained from them before\nthey drop back to the floor, and the statue reverts back to normal.");
          p.removeSpell();
        }
        else {
          System.out.println("The statue's eyes flash red before " + p.getName() + " is dropped to the floor right before they drop the floor without a heatbeat. Your party is forced to carry their body onward...");
          p.takeDamage(999);
        }
      }
    }
    else{
      System.out.println("The statue is of a caring mother looking up at " + p.getName() + " whilst a baby lay in her lap. " + p.getName() + " feels the statue calling to them. Will they reach out?\n1. Yes\t\t2. No");
      if(mando.nextInt() == 2){
        System.out.println(p.getName() + " pulls back and recognizes the dangers that might be hidden. They rejoin their party in safety and move forward.\n");
      }
      else{
        System.out.println(p.getName() + " drifts off to sleep under the magical influence of the statue...");
        if (statue == 0){
          System.out.println("The eyes of the statue glow green, and your party watches as every cut, scape, and any other minor to major injury is completely reversed on " + p.getName() + "\'s body.");
          p.healPlayer(999);
        }
        else if (statue == 1){
          System.out.println("Your party patiently awaits the reanimation of " + p.getName() + ", but that moment never comes. After nearly two hours of waiting, your party lifts " + p.getName() + "\'s body off of the floor to continue onward.");
          p.takeDamage(999);
        }
        else {
          System.out.println(p.getName() + "\'s weapon begins to glow, and it radiates magical energy.\n");
          p.increaseAttack(2);
        }
      }
    }
  }

  public void coinFlip(ArrayList<Player> p){
    Scanner mando = new Scanner(System.in);
    System.out.println("Your party continues on into a room with table on it and a single gold coin. As they move closer to examine it, the entrance and exit slam shut. " + p.get(0).getName() + " picks up the coin and notices an angel on one side and a skull on the other.\n");
    for(int i = 0; i < p.size(); i++){
      System.out.println(p.get(i).getName() + " is currently holding the coin. Would you like to flip it?/n1. Yes    2. No");
      if(mando.nextInt() == 1){
        if(((int)(Math.random()*2)) == 1){
          System.out.println("Tails");
          p.get(i).takeDamage(999);
        }
        else {
          System.out.println("Heads");
          p.get(i).healPlayer(999);
        }
      }
      else{
        System.out.println(p.get(i).getName() + " decided not to flip the coin.\n");
      }
    }
  }

  public void treasure(Player p){
    Scanner mando = new Scanner (System.in);
    System.out.println("As " + p.getName() + " enters the room, they recognize a chest in the corner. Using their keen adventuring instincts, \nthey deduce that it could be a mimic. What would you like " + p.getName() + " to do?\n1. Attack First   2. Open Chest");
    int mimicCheck = (int)(Math.random()*2);
    if (mando.nextInt() == 1){
      if(mimicCheck == 1){
        System.out.println("\nGood call! The weak mimic cries out as you defeat it in one blow, and it leaves behind it's treasure for you.\n" + p.getName() + " has double gained attack.\n");
        p.increaseAttack(2);
      }
      else{
        System.out.println("Oh no! This was simply a chest. Your strong blow obliterated the old box, and its contents with it.\n");
      }
    }
    else{
      if(mimicCheck == 1){
       System.out.println("Oh no! " + p.getName() + "\'s excitement got the better of them, and they were attacked by the mimic.");
       p.takeDamage(35);
      }
      else{
        System.out.println(p.getName() + " opens the chest slowly to reveal...\nA full of unused armor! What luck!\n" + p.getName() + "\'s defense has been increased to 30");
        p.setDefense(30);
      }
    }
  }

  public int portal(Player p){
    Scanner mando = new Scanner(System.in);
    System.out.println(p.getName() + " encounters a strange portal on the surface of the wall. What would you like them to do?\n1. Enter 2.Ignore");
    int portalType = (int)(Math.random()*2);
    if(mando.nextInt() == 1){
      System.out.println(p.getName() + " decides to take the risk and enter the portal...");
      if(portalType == 1){
        System.out.println("The risk was not worth it. As " + p.getName() + " entered the portal, it closed behind them before the rest of their party could enter.\n" + p.getName() + " has been removed from the party.");
        return 1;
      }
      else{
        System.out.println("The portal remains stable after " + p.getName() + " enters. The rest of your party enters, and they find themselves farther into the dungeon than they previously were.");
        return 2;
      }
    } 
    System.out.println(p.getName() + " decides that knowing their location and being safe are more important than risking their life. Your party moves on.\n");
    return 3;  
  }

  public boolean meetPlayer(Player p){
    Scanner mando = new Scanner(System.in);
    System.out.println(p.getName() + " is approaching a room with a closed door when they hear rustling and other noises coming from behind it.\nWhat would you like " + p.getName() + " to do?\n1. Open the door    2. Ignore the room");
    if(mando.nextInt() == 2){
      System.out.println("\"Curiosity killed the cat\" " + p.getName() + " says as they safely move on through the dungeon with the rest of their party.");
      return false;
    }
    System.out.println(p.getName() + " slowly opens the door, and they search the room to find another adventurer.\n\"Please! Help me! I'm scared and lost in this dungeon! You have to help me get out!\"\nYou can't tell if they're being sincere. What do you do?\n1. Betray Them    2. Invite them to your party");
    if(mando.nextInt() == 1){
      System.out.println(p.getName() + " cannot discern the adventurer's true intentions, and so they strike first to be safe. " + p.getName() + " quickly defeats their target, and they continue on in the dungeon.");
      return false;
    }
    if(((int)(Math.random()*2)) == 1){
      System.out.println("\"You fool!\" are the last words shouted by the adventurer before they force a knife into " + p.getName() + "\'s side. " + p.getName() + " quickly defeats the scoundrel, but they leave quite a mark on " + p.getName() + ".");
      p.takeDamage(30);
      return false;
    }
    System.out.println("\"Oh, splendid!\" the adventurer shouts before showing you their arms and joining your party.\n");
    return true;
  }

  /*public int poet(ArrayList<Player> p, int num){
    System.out.println(p.get(num).getName() + " enters a small room with a lonely chair in its center. " + p.get(num).getName() + " lights a candle on the wall. As the room begins to illuminate, a figure of a man appears in the chair.\n\"Hello there, " + p.get(num).getName() + ". I see you might be in need of some guidance.\nI'll give you a choice of options.\"\n1. A game of chance\n2. A strange passageway\n3. A box of treasures\n4. A special encounter\n5. A unique challenge.\n(Enter an integer)");
    int choice = mando.nextInt();
    if (!(choice > 0 && choice < 6){
      choice = (int)(Math.random() * 6) + 1;
    }
    System.out.println("\"Your wish is my command.\"\nThe candle flickers before it is extinguished by a sudden gust of wind. Before " + p.get(num).getName() + "'s eyes can adjust to the darkness, the man in the chair is gone.");
    return choice;
  }*/

}