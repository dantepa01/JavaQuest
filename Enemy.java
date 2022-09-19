public class Enemy{
  
  private String name;
  private String weakness1;
  private String weakness2;
  private String weakness3;
  private String resistance1;
  private String resistance2;
  private String resistance3;
  private int hp;
  private int level;
  private boolean life;
  
  public Enemy(String n, String w1, String w2, String w3, String r1, String r2, String r3, int h, int l){
    name = n;
    weakness1 = w1;
    weakness2 = w2;
    weakness3 = w3;
    resistance1 = r1;
    resistance2 = r2;
    resistance3 = r3;
    hp = h;
    level = l;
    life = true;
  }
  
  public int attack(){
    int damage = 0;
    damage += (int)(Math.random()*40) + 20;
    damage += (int)((double)damage * (double)level/10);
    return damage;
  }
  
  public void takeDamage(int d, String a){
    if (weakness1.equals(a) || weakness2.equals(a) || weakness3.equals(a)){
      d *= 2;
      hp -= d;
      System.out.println(name + " is weak to " + a + " and took " + d + " damage!");
    }
    else if (resistance1.equals(a) || resistance2.equals(a) || resistance3.equals(a)){
      d /= 2;
      hp -= d;
      System.out.println(name + " is resistant to " + a + " and took " + d + " damage!");
    }
    else{
      hp -= d;
      System.out.println(name + " took " + d + " damage!");
    }
    if(hp < 1){
      life = false;
    }
  }
  
  public boolean checkLife(){
    return life;
  }
  
  public String getName(){
    return name;
  }

  public int getLevel(){
    return level;
  }
  
}