import java.lang.Math;
import java.util.*;

public class Player {

private int hp;
private int hpMax;
private String name;
private int mp;
private int mpMax;
private String vocation;
private int classPos;
private int level;
private int exp;
private int defense;
private int attack;
private boolean life;
private static int baseDefense[] = {15, 10, 6, 4};
private static int baseAttack[] = {10, 15, 8, 4};
public ArrayList<Ability> spells = new ArrayList<Ability>();
private String classes[] = {"Paladin", "Mercenary", "Cleric", "Mage"};
private static int baseHpIndex[] = {120, 100, 80, 70};
private static int hpIncreaseIndex[] = {15, 10, 7, 4};
private static int baseMpIndex[] = {20, 30, 50, 75};
private static int mpIncreaseIndex[] = {2, 6, 8, 10};
private static int spellSlotsIndex[] = {2, 3, 5, 8};
public boolean guarded;
public boolean revival;

public Player(String tempName, String tempClass){
  if(!(tempClass.toLowerCase().equals("paladin") || tempClass.toLowerCase().equals("mercenary") || tempClass.toLowerCase().equals("cleric") || tempClass.toLowerCase().equals("mage"))){
    tempClass = classes[(int)(Math.random()*4)];
  }
  for(int i = 0; i < classes.length; i++){
    if(classes[i].toLowerCase().equals(tempClass.toLowerCase())){
      classPos = i;
    }
  }
  vocation = classes[classPos];
  name = tempName;
  hp = baseHpIndex[classPos];
  hpMax = hp;
  mp = baseMpIndex[classPos];
  mpMax = mp;
  defense = baseDefense[classPos];
  attack = baseAttack[classPos];
  level = 1;
  exp = 0;
  life = true;
}

public String toString(){
  String temp = "";
  temp += name + "\t" + vocation + "\tLevel: " + level + "\n";
  temp += "HP:" + hp + " / " + hpMax + "\t\tMP: " + mp + " / " + mpMax + "\n";
  temp += "EXP until next level: " + (100 - exp);
  return temp;
}

public void fillSpellSlots(){
  int tempIndex;
  if (classPos == 0){ //Paladin
    ArrayList<Ability> offenseP = new ArrayList<Ability>();
    offenseP.add(new Ability("Smite", "Paladin", "Offense", 15, 20, 6, "Strike your opponent with heavenly fury!", false, false, false));
    offenseP.add(new Ability("Slash", "Paladin", "Offense", 12, 15, 3, "Attack with extra might.", false, false, false));

    ArrayList<Ability> defenseP = new ArrayList<Ability>();
    defenseP.add(new Ability("Shield Slam", "Paladin", "Defense", 5, 10, 4, "A weaker attack that increases defense.", true, false, false));
    defenseP.add(new Ability("Guard", "Paladin", "Defense", 0, 0, 2, "A status ability that increases defense.", true, false, false));
    defenseP.add(new Ability("Healing Grace", "Paladin", "Defense", 0, 0, 20, "Fully heal your character.", false, false, true));
    defenseP.add(new Ability("Undying Might", "Paladin", "Defense", 0, 0, 15, "Your unwavering courage and strength will prevent you from feinting the next time you receive fatal damage.", false, true, false)); 

    int offOrDef;
    for (int i = 0; i < spellSlotsIndex[0]; i++){
      offOrDef = (int)(Math.random()*2);
      if (offOrDef == 1){
        tempIndex = (int)(Math.random()*4);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (defenseP.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(defenseP.get(tempIndex));
        }
        else{
          i--;
        }
      }
      else{
        tempIndex = (int)(Math.random()*2);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (offenseP.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(offenseP.get(tempIndex));
        }
        else{
          i--;
        }
      }
    }
  }
  else if (classPos == 1){ //Mercenary
    ArrayList<Ability> offenseM = new ArrayList<Ability>();
    offenseM.add(new Ability("Bombard", "Mercenary", "Offense", 20, 30, 9, "An all out attack! Throw out a flurry of strong blows.", false, false, false));
    offenseM.add(new Ability("Slash", "Mercenary", "Offense", 12, 15, 3, "Attack with extra might.", false, false, false));
    offenseM.add(new Ability("Draining Strike", "Mercenary", "Offense", 10, 14, 4, "Heal for half of the damage dealt.", false, false, true));

    ArrayList<Ability> defenseM = new ArrayList<Ability>();
    defenseM.add(new Ability("Guard", "Mercenary", "Defense", 0, 0, 2, "A status ability that increases defense.", true, false, false));
    defenseM.add(new Ability("Bandage", "Mercenary", "Defense", 0, 0, 5, "Heal yourself 10 hp.", false, false, true));
    defenseM.add(new Ability("Gamble", "Mercenary", "Defense", 0, 0, 10, "Spend your HP to heal yourself. This can heal anywhere from nothing to all of your health.", false, false, true));

    int offOrDef;
    for (int i = 0; i < spellSlotsIndex[0]; i++){
      offOrDef = (int)(Math.random()*2);
      if (offOrDef == 1){
        tempIndex = (int)(Math.random()*2);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (defenseM.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(defenseM.get(tempIndex));
        }
        else{
          i--;
        }
      }
      else{
        tempIndex = (int)(Math.random()*3);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (offenseM.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(offenseM.get(tempIndex));
        }
        else{
          i--;
        }
      }
    }
  }
  else if (classPos == 2){ //Cleric
    ArrayList<Ability> holy = new ArrayList<Ability>();
    holy.add(new Ability("Holy Light", "Cleric", "Holy", 10, 14, 4, "Heavenly light burns your target foe.", false, false, false));
    holy.add(new Ability("God's Grace", "Cleric", "Holy", 0, 0, 12, "Your heavenly figure grants you defense and a small heal.", true, false, true));
    holy.add(new Ability("Magical Smite", "Cleric", "Holy", 15, 22, 10, "A powerful attack fueled by heavenly energy.", false, false, false));

    ArrayList<Ability> healing = new ArrayList<Ability>();
    healing.add(new Ability("Healing Word", "Cleric", "Healing", 0, 0, 6, "Heal yourself a moderate amount.", false, false, true));
    healing.add(new Ability("Promise of Resurrection", "Cleric", "Healing", 0, 0, 15, "Your heavenly figure grants you a second life after this one; you shall be revived upon death.", false, true, false));
    
    ArrayList<Ability> unholy = new ArrayList<Ability>();
    unholy.add(new Ability("Satan's Patron", "Cleric", "Unholy", 8, 12, 2, "A weak attack sourced by calling up a devil's power.", false, false, false));
    unholy.add(new Ability("Demonic Claw", "Cleric", "Unholy", 14, 20, 8, "Infuse your hand with a devil's power and forge a claw out of its energy to slice your foe.", false, false, false));
    unholy.add(new Ability("Satanic Ritual", "Cleric", "Unholy", 0, 999, 20, "Attempt to perform a ritual to destroy your foe. If you succeed, they will immediately be vanquished.", false, false, false));

    ArrayList<Ability> darkness = new ArrayList<Ability>();
    darkness.add(new Ability("Corrupt", "Cleric", "Darkness", 15, 25, 10, "A powerful magic attack that drains the user's health instead of mana.", false, false, false));
    darkness.add(new Ability("Betray", "Cleric", "Darkness", 25, 30, 20, "A powerful magic attack that drains the user's health instead of mana.", false, false, false));
    
    int attributeTemp;
    int cHoly = 0;
    int cHealing = 0;
    int cUnholy = 0;
    int cDarkness = 0;
    for (int i = 0; i < spellSlotsIndex[classPos]; i++){
      attributeTemp = (int)(Math.random()*4);
      if (attributeTemp == 0 && cHoly < 4){
        tempIndex = (int)(Math.random()*3);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (holy.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(holy.get(tempIndex));
          cHoly++;
        }
        else{
          i--;
        }
      }
      else if (attributeTemp == 1 && cHealing < 3){
        tempIndex = (int)(Math.random()*2);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (healing.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(healing.get(tempIndex));
          cHealing++;
        }
        else{
          i--;
        }
      }
      else if (attributeTemp == 2 && cUnholy < 4){
        tempIndex = (int)(Math.random()*3);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (unholy.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(unholy.get(tempIndex));
          cUnholy++;
        }
        else{
          i--;
        }
      }
      else if (attributeTemp == 3 && cDarkness < 3){
        tempIndex = (int)(Math.random()*2);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (darkness.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(darkness.get(tempIndex));
          cDarkness++;
        }
        else{
          i--;
        }
      }
      else{
        i--;
      }
    }
  }
  else if (classPos == 3){ //Mage
    ArrayList<Ability> water = new ArrayList<Ability>();
    water.add(new Ability("Water Slash", "Mage", "Water", 12, 16, 5, "Materialize a sword of rain to slice your foe.", false, false, false));
    water.add(new Ability("Water Whip", "Mage", "Water", 16, 22, 10, "A stronger water based attack. Use a whip made out of water to strike your opponent.", false, false, false));
    water.add(new Ability("Icicle Armor", "Mage", "Water", 0, 0, 5, "Wrap water around your body and freeze it to protect ourself from an attack.", true, false, false));
    water.add(new Ability("Healing Wave", "Mage", "Water", 0, 0, 15, "Surround yourself with cleansing water to heal your wounds.", false, false, true));

    ArrayList<Ability> fire = new ArrayList<Ability>();
    fire.add(new Ability("Fire Slash", "Mage", "Fire", 12, 16, 5, "Create a spear of flame to pierce your opponent.", false, false, false));
    fire.add(new Ability("Fireball", "Mage", "Fire", 30, 40, 30, "\"The Fireball is meant to be cool.\"...so it might be a little broken.", false, false, false));
    fire.add(new Ability("Phoenix's Might", "Mage", "Fire", 0, 0, 15, "A phoenix grants you the gift of ressurection.", false, true, false));

    ArrayList<Ability> air = new ArrayList<Ability>();
    air.add(new Ability("Air Slash", "Mage", "Air", 12, 16, 5, "Slice your target with a gust of sharp wind.", false, false, false));
    air.add(new Ability("Ultimate Tornado", "Mage", "Air", 0, 999, 15, "If succussfully cast, your taget will be swept away and immediately excuted in a tornado. Very low succession rate.", false, false, false));
    air.add(new Ability("Life's Wind", "Mage", "Air", 0, 0, 5, "Refreshing air fills your lungs to heal your wounds.", false, false, true));

    ArrayList<Ability> earth = new ArrayList<Ability>();
    earth.add(new Ability("Mud Armor", "Mage", "Earth", 0, 0, 6, "Raise earth and cover yourself in it to protect against attacks.", true, false, false));
    earth.add(new Ability("Earthquake", "Mage", "Earth", 20, 30, 15, "Rip the ground beneath your opponent apart in a devastating attack.", false, false, false));
    earth.add(new Ability("Nature's Regeneration", "Mage", "Earth", 0, 0, 30, "The most powerful heal in a mage's arsenal. Regenerate half of your maximum health using photosynthesis and nature's beautiful gifts.", false, false, false));
    
    ArrayList<Ability> voidSpells = new ArrayList<Ability>();
    voidSpells.add(new Ability("Black Hole", "Mage", "Void", 15, 20, 10, "Create a rip in space to rip apart your foe. Uses HP instead of MP.", false, false, false));
    voidSpells.add(new Ability("Collapse", "Mage", "Void", 30, 40, 25, "Create a hole of empty space within your opponent's chest, causing their body to fall in on itself. Costs HP instead of MP.", false, false, false));

    int attributeTemp;
    int mWater = 0;
    int mFire = 0;
    int mAir = 0;
    int mEarth = 0;
    int mVoid = 0;
    for (int i = 0; i < spellSlotsIndex[classPos]; i++){
      attributeTemp = (int)(Math.random()*5);
      if(attributeTemp == 0 && mWater < 5){
        tempIndex = (int)(Math.random()*4);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (water.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(water.get(tempIndex));
          mWater++;
        }
        else{
          i--;
        }
      }
      else if(attributeTemp == 1 && mFire < 4){
        tempIndex = (int)(Math.random()*3);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (fire.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(fire.get(tempIndex));
          mFire++;
        }
        else{
          i--;
        }
      }
      else if(attributeTemp == 2 && mAir < 4){
        tempIndex = (int)(Math.random()*3);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (air.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(air.get(tempIndex));
          mAir++;
        }
        else{
          i--;
        }
      }
      else if(attributeTemp == 3 && mEarth < 4){
        tempIndex = (int)(Math.random()*3);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (earth.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(earth.get(tempIndex));
          mEarth++;
        }
        else{
          i--;
        }
      }
      else if(attributeTemp == 4 && mVoid < 3){
        tempIndex = (int)(Math.random()*2);
        boolean check = true;
        for(int j = 0; j < spells.size(); j++){
          if (voidSpells.get(tempIndex) == spells.get(j)){
            check = false;
          }
        }
        if(check){
          spells.add(voidSpells.get(tempIndex));
          mVoid++;
        }
        else{
          i--;
        }
      }
    }
  }
}

public void gainExp(int e){
  if(life){
    exp += e * 10;
    exp += (int)(Math.random()*(e*20));
  }
}

public boolean levelCheck(){
  boolean lu = false;
  if(life){
    if(exp >= 100){
      while (exp > 100){
        level ++;
        hpMax += hpIncreaseIndex[classPos];
        mpMax += mpIncreaseIndex[classPos];
        exp -= 100;
        lu = true;
      }
    }
  }
  return lu;
}

public int attack(){
  int damage = 0;
  damage += attack + (int)((double)attack * ((double)level/10));
  if((int)(Math.random()*9) == 0){
    System.out.println("Critical Hit!");
    damage *= 2;
  }
  return damage;
}

public void takeDamage(int dam){
  dam -= (int)((double)defense * ((double)level/10)) + defense;
  if (guarded){
    dam -= defense;
    guarded = false;
  }
  if (dam < 0){
    dam = 0;
  }
  hp -= dam;
  System.out.println(name + " took " + dam + " damage!");
  if (hp <= 0){
    hp = 0;
    life = false;
    System.out.println(name + " has fainted!");
    if(revival){
      hp = hpMax / 2;
      System.out.println("...but they have been revived!");
      life = true;
    }
  }
  System.out.println();
}

public void healPlayer(int h){
  if (h > 0){
    hp += h;
  }
  if (hp > hpMax){
    hp = hpMax;
  }
}

public String getName(){
  return name;
}

public int getMaxHealth(){
  return hpMax;
}

public int getCurrentMana(){
  return mp;
}

public String returnClass(){
  return vocation;
}

public void useMana(int m){
  mp -= m;
}

public void playerWait(){
  mp += mpIncreaseIndex[classPos];
  System.out.print(name + " regained " + mpIncreaseIndex[classPos] + " MP!\n");
}

public void readyCharacter(){
  mp = mpMax;
  if(!life){
    hp = hpMax / 2;
  }
  life = true;
  guarded = false;
  revival = false;
}

public Ability getSpell(int num){
  return spells.get(num);
}

public boolean getLife(){
  return life;
}

public void printSpell(int num){
  System.out.println(spells.get(num));
}

public String checkClass(){
  return vocation;
}

public boolean loseLevel(){
  if (level > 1){
    level -= 1;
    hpMax -= hpIncreaseIndex[classPos];
    if (hp > hpMax){
      hp = hpMax;
    }
    return true;
  }
  return false;
}

public void setDefense(int num){
  defense = num;
}

public void removeSpell(){
  int tempInt = (int)(Math.random()*spells.size());
  String temp = spells.get(tempInt).getSpellName();
  spells.remove(tempInt);
  System.out.println(name + " forgot how to use " + temp + "!\n");
}

public void increaseAttack(int num){
  attack *= num;
}

}