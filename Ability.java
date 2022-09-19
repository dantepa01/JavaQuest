public class Ability{

  public String spellName;
  public String spellClass;
  public String spellAttribute;
  public int minDamage;
  public int maxDamage;
  public int mpCost;
  public String desc;
  boolean setGuard;
  boolean setRevive;
  boolean healing;

  public Ability(String n, String c, String a, int minDam, int maxDam, int mpc, String des, boolean gurd, boolean reviv, boolean heel){
    spellName = n;
    spellClass = c;
    spellAttribute = a;
    minDamage = minDam;
    maxDamage = maxDam;
    mpCost = mpc;
    desc = des;
    setGuard = gurd;
    setRevive = reviv;
    healing = heel;
  }

  public String toString(){
    String temp = "";
    if(spellAttribute.equals("Darkness") || spellAttribute.equals("Void")){
      temp += spellName + " \t" + spellClass + " Ability\t" + spellAttribute + " type\n";
      temp += minDamage + " - " + maxDamage + " Damage\tCost: " + mpCost + " HP\n";
      temp += desc;
      return temp;
    }
    temp += spellName + " \t" + spellClass + " Ability\t" + spellAttribute + " type\n";
    temp += minDamage + " - " + maxDamage + " Damage\tCost: " + mpCost + " MP\n";
    temp += desc;
    return temp;
  }

  public int useAbility(Player p){
    int damage = 0;
    if (p.getCurrentMana() >= mpCost){
      damage = (int)(Math.random()*(maxDamage - minDamage))+  minDamage;
      if(spellAttribute.equals("Darkness") || spellAttribute.equals("Void") || spellName.equals("Gamble")){
        p.takeDamage(mpCost);
      }
      else{
        p.useMana(mpCost);
      }
      if (setGuard){
        p.guarded = true;
      }
      if (setRevive){
        p.revival = true;
      }
      if (healing){
        if(spellName.equals("Draining Strike")){
         p.healPlayer(damage/2);
        }
        else if(spellName.equals("Bandage")){
          p.healPlayer(5);
        }
        else if(spellName.equals("Gamble")){
          int gambleTemp = (int)(Math.random()*14);
          if(gambleTemp > 0 && gambleTemp < 7){
            p.healPlayer((int)(Math.random()*20));
          }
          else if(gambleTemp >= 7 && gambleTemp < 14){
            p.healPlayer((int)(Math.random()*20)+20);
          }
          else if(gambleTemp == 14){
            p.healPlayer(p.getMaxHealth());
          }
        }
        else if(spellName.equals("God's Grace")){
          p.healPlayer(5);
          System.out.println(p.getName() + " was healed 5 HP!");
        }
        else if(spellName.equals("Healing Word")){
          p.healPlayer(15);
          System.out.println(p.getName() + " was healed 15 HP!");
       }
        else if(spellName.equals("Healing Grace")){
         p.healPlayer(p.getMaxHealth());
         System.out.println(p.getName() + " was healed " + p.getMaxHealth() + " HP!");
        }
        else if(spellName.equals("Healing Wave")){
          p.healPlayer(20);
          System.out.println(p.getName() + " was healed 20 HP!");
        }
        else if(spellName.equals("Life's Wind")){
          p.healPlayer(15);
          System.out.println(p.getName() + " was healed 15 HP!");
        }
        else if(spellName.equals("Nature's Regeneration")){
          p.healPlayer(p.getMaxHealth() / 2);
          System.out.println(p.getName() + " was healed " + (p.getMaxHealth() / 2) + " HP!");
        }
      }
     if (spellName.equals("Satanic Ritual")){
        if (((int)(Math.random()*9)) == 0)
          damage = 999;
       else
          damage = 0;
      }
     if (spellName.equals("Ultimate Tornado")){
       if(((int)(Math.random()*29)) == 0){
         damage = 999;
       }
       else{
         damage = 0;
       }
     }
    }
    else{
      System.out.println("Not enough MP!");
    }
    return damage;
  }
  
  public String getSpellAttribute(){
    return spellAttribute;
  }

  public String getSpellName(){
    return spellName;
  }
  
}