import java.lang.Math;
import java.util.*;

class Main {

  public static boolean dungeon(int num, ArrayList<Player> party, ArrayList<Enemy> orc){
    Scanner mando = new Scanner(System.in);
    boolean combat = true;
    boolean partyLife = true;
    int turnOrder = 0;
    int damage = 0;
    int action = 0;
    int spellUse = 0;
    if(orc.get(num).getName().equals("Tarrasque")){
      System.out.println("You are not prepared for this challenge...\n...the ultimate creature...\n...preapre to die.");
    }
    System.out.println(orc.get(num).getName() + " appeared!\n");
    while(combat){
      if(party.get(turnOrder % party.size()).getLife()){
        System.out.println("What would you like " + party.get(turnOrder % party.size()).getName() + " to do?\n1. Attack\t2. Use Ability\t3.Wait (Regen MP)\n(Select the number of a corresponding action)");
        System.out.println(party.get(turnOrder % party.size()));
        action = mando.nextInt();
        if(action == 1){
          if(party.get(turnOrder % party.size()).checkClass().equals("Paladin") || party.get(turnOrder % party.size()).checkClass().equals("Cleric")){
            orc.get(num).takeDamage(party.get(turnOrder % party.size()).attack(), "Defense");
          }
          else{
            orc.get(num).takeDamage(party.get(turnOrder % party.size()).attack(), "Offense");
          }
        }
        else if(action == 2){
          System.out.println("Which ability would you like to use?");
          for(int i = 0; i < party.get(turnOrder % party.size()).spells.size(); i++){
            System.out.print((i+1) + ". ");
            party.get(turnOrder % party.size()).printSpell(i);
            System.out.println();
          }
          System.out.println("(Select a number corresponding to an ability)");
          spellUse = mando.nextInt();
          if(spellUse > 0 && spellUse <= (party.get(turnOrder % party.size()).spells.size())){
            orc.get(num).takeDamage(party.get(turnOrder % party.size()).getSpell(spellUse-1).useAbility(party.get(turnOrder % party.size())), party.get(turnOrder % party.size()).getSpell(spellUse-1).getSpellAttribute());
          }
          else{
            System.out.println("You will wait for this turn.");
            party.get(turnOrder % party.size()).playerWait();
          }
        }
        else{
          party.get(turnOrder % party.size()).playerWait();
        }
      }
      else{
        System.out.println(party.get(turnOrder % party.size()).getName() + " is unconcious!");
      }
      System.out.println();
      if(!orc.get(num).checkLife()){
        System.out.println(orc.get(num).getName() + " has been slain!\n");
        combat = false;
      }
      if(turnOrder % party.size() == 3 && combat){
        boolean attackCheck = true;
        while(attackCheck){
          int attackPlayer = (int)(Math.random()*party.size());
          if(party.get(attackPlayer).getLife()){
            party.get(attackPlayer).takeDamage(orc.get(num).attack());
            attackCheck = false;
          }
        }
      }
      turnOrder++;
      action = 0;
      spellUse = 0;
      if(!(party.get(0).getLife() || party.get(1).getLife() || party.get(2).getLife() || party.get(3).getLife())){
        partyLife = false;
        combat = false;
      }
    }
    if(!partyLife){
      System.out.println("Your party has been defeated... better luck next time!");
      return false;
    }
    System.out.println("Congrats! Your party can continue into the depths...");
    for(int i = 0; i < party.size(); i++){
      party.get(i).gainExp(orc.get(num).getLevel());
      if(party.get(i).levelCheck()){
        System.out.println(party.get(i).getName() + " has leveled up!");
      }
      party.get(i).readyCharacter();
    }
    return true;
  }
  
  public static void main(String[] args) {
    Scanner mando = new Scanner(System.in);
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n~ JJJJJJJJJJJ      AAAAA      VVV       VVV      AAAAA     ~\n~      JJJ        AAA AAA      VVV     VVV      AAA AAA    ~\n~      JJJ       AAA   AAA      VVV   VVV      AAA   AAA   ~\n~ JJJ  JJJ      AAAAAAAAAAA      VVV VVV      AAAAAAAAAAA  ~\n~ JJJJJJJJ     AAA       AAA      VVVVV      AAA       AAA ~\n~                                                          ~");
    System.out.println("~ QQQQQQQQ    UU  UU EEEEE SSSSS TTTTTTTT   /\\      ||     ~\n~ QQQ  QQQ    UU  UU EE    SS       TT   ---  --- ------   ~\n~ QQQ  QQQ    UU  UU EEEEE SSSSS    TT    \\    /    ||     ~\n~ QQQ  QQQQQ  UU  UU EE       SS    TT     \\  /     ||     ~\n~ QQQQQQQQQQQ UUUUUU EEEEE SSSSS    TT      \\/      \\/     ~\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.print("Enter \"Play\" to continue:");
    while(!mando.nextLine().toLowerCase().equals("play")){
      System.out.print("Enter \"Play\" to continue:");
    }
    ArrayList<Player> party = new ArrayList<Player>();
    String memberNumber[] = {"first", "second", "third", "last"}; 
    Encounters encounter = new Encounters();
    for(int i = 0; i < 4; i++){
      System.out.println("What would you like to name your " + memberNumber[i] + " party member?");
      String tempName = mando.nextLine();
      System.out.println("And what class shall this member be?");
      System.out.println("(Mercenary, Mage, Paladin, Cleric, or Random)\n(If none of the above are selected, Random will be chosen)");
      String tempClass = mando.nextLine();
      party.add(new Player(tempName, tempClass));
      party.get(i).fillSpellSlots();
      System.out.println(party.get(i).getName() + " the " + party.get(i).returnClass() + " has been added to the party!\n");
    }
    System.out.println("Party Complete!\n");
    ArrayList<Enemy> dungeonCreatures = new ArrayList<Enemy>();
    dungeonCreatures.add(new Enemy("Mimic", "Offense", "Fire", "Holy", "Defense", "Air", "Unholy", 60, 1));
    dungeonCreatures.add(new Enemy("Goblin", "Defense", "Darkness", "Air", "Offense", "Water", "Holy", 80, 1));
    dungeonCreatures.add(new Enemy("Orc", "Offense", "Fire", "Holy", "Unholy", "Defense", "Earth", 120, 3));
    dungeonCreatures.add(new Enemy("Werewolf", "Offense", "Earth", "Darkness", "Defense", "Air", "Unholy", 180, 5));
    dungeonCreatures.add(new Enemy("Candle Golem", "Water", "Unholy", "", "Fire", "Defense", "Holy", 230, 7));
    dungeonCreatures.add(new Enemy("Minotaur", "Offense", "Holy", "", "Defense", "Water", "Darkness", 270, 10));
    dungeonCreatures.add(new Enemy("Lesser Demon", "Defense", "Unholy", "", "Offense", "Fire", "Air", 315, 12));
    dungeonCreatures.add(new Enemy("Lich", "Holy", "", "", "Air", "Unholy", "Offense", 340, 14));
    dungeonCreatures.add(new Enemy("Vampire Lord", "Holy", "Fire", "", "Defense", "Earth", "Water", 375, 16));
    dungeonCreatures.add(new Enemy("Medusa", "Water", "Offense", "", "Fire", "Earth", "Holy", 400, 18));
    dungeonCreatures.add(new Enemy("Dragon", "Water", "Void", "Darkness", "Earth", "Offense", "Defense", 450, 20));
    if((int)(Math.random()*25) == 1){
      dungeonCreatures.add(new Enemy("Tarrasque", "", "", "", "Offense", "Fire", "Holy", 800, 30));
    }
    
    boolean notLoser = true;
    boolean dungeonCheck;
    String encounterCheck;
    for(int i = 0; i < dungeonCreatures.size(); i++){
      dungeonCheck = dungeon(i, party, dungeonCreatures);
      if((dungeonCreatures.size()-1 != i) && encounter.checkEncounter(dungeonCreatures.get(i).getLevel())){
        encounterCheck = encounter.getEncounter(party);
        if (encounterCheck.equals("Revive Enemy")){
          i--;
          System.out.println("The " + dungeonCreatures.get(i+1).getName() + " has been revived!\n");
        }
        else if(encounterCheck.equals("Remove Player")){
          party.remove(party.size()-1);
        }
        else if(encounterCheck.equals("Move Forward")){
          i += (int)(Math.random()*3) + 1;
          if (i > dungeonCreatures.size() - 2){
            i = dungeonCreatures.size() - 2;
          }
        }
        System.out.println();
      }
      if(!dungeonCheck){
        notLoser = false;
        break;
      }
    }
    if(notLoser){
      System.out.println("\nCongrats! You completed the dungeon!");
    }
    else{
      System.out.println("You lose!");
    }
  }
}