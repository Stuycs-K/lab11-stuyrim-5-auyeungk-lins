import java.util.Random;
import java.util.ArrayList;

public abstract class Adventurer{
  private String name;
  private int HP,maxHP;
  private ArrayList<Adventurer> friends;
  private ArrayList<Adventurer> foes;
  private boolean poisonStatus;
  private boolean sleepStatus;
  private boolean seededStatus;
	private boolean protectStatus;
	private boolean burnStatus;
	private boolean HHStatus;
  private String type;

  //Abstract methods are meant to be implemented in child classes.
  /*
  all adventurers must have a custom special
  consumable resource (mana/rage/money/witts etc)
  */

  //give it a short name (fewer than 13 characters)
  public abstract String getSpecialName();
  //accessor methods
  public abstract int getSpecial();
  public abstract int getSpecialMax();
  public abstract void setSpecial(int n);

  //concrete method written using abstract methods.
  //refill special resource by amount, but only up to at most getSpecialMax()
  public int restoreSpecial(int n){
    if( n > getSpecialMax() - getSpecial()){
      n = getSpecialMax() - getSpecial();
    }
    setSpecial(getSpecial()+n);
    return n;
  }

  /*
  all adventurers must have a way to attack enemies and
  support their allys
  */
  //hurt or hinder the target adventurer
  public abstract String attack(Adventurer other);

  /*This is an example of an improvement that you can make to allow
   * for more flexible targetting.
   */
  //heal or buff the party
  //public abstract String support(ArrayList<Adventurer> others);

  //heal or buff the target adventurer
  public abstract String support(Adventurer other);

  //heal or buff self
  public abstract String support();

  //hurt or hinder the target adventurer, consume some special resource
  public abstract String specialAttack(Adventurer other);

  /*
  standard methods
  */

  public void applyDamage(int amount){
    if(this.HP - amount < 0){
      this.HP = 0;
    }else {
      this.HP -= amount;
    }
  }

  //You did it wrong if this happens.
  public Adventurer(){
    this("Lester-the-noArg-constructor-string");
  }

  public Adventurer(String name){
    this(name, 10, "Fire");
  }

  public Adventurer(String name, int hp, String type){
    this.name = name;
    this.HP = hp;
    this.maxHP = hp;
    this.poisonStatus = false;
    this.sleepStatus = false;
    this.seededStatus = false;
		this.protectStatus = false;
		this.burnStatus = false;
		this.HHStatus = false;
    this.type = type;
  }
  //friends and foes methods
  public void addFriend(Adventurer other) {
	  friends.add(other);
  }

  public void addFoe(Adventurer other) {
	  foes.add(other);
  }

  public Adventurer getFriend(int i) {
	  return friends.get(i);
  }

  public Adventurer getFoe(int i) {
	  return foes.get(i);
  }

  public int foeCount() {
	  return foes.size();
  }

  //toString method
  public String toString(){
    return this.getName();
  }

  //Get Methods
  public String getName(){
    return name;
  }

  public int getHP(){
    return HP;
  }

  public int getmaxHP(){
    return maxHP;
  }

	public String getType(){
		return type;
	}

	//Set Methods
  public void setmaxHP(int newMax){
    maxHP = newMax;
  }

  public void setHP(int health){
    this.HP = health;
  }

  public void setName(String s){
    this.name = s;
  }

  public void setPoisonStatus(boolean b){
	    this.poisonStatus = b;
  }

  public boolean getPoisonStatus(){
	    return poisonStatus;
}

  public void setSleepStatus(boolean b){
	    this.sleepStatus = b;
  }

  public boolean getSleepStatus(){
	    return sleepStatus;
}

  public void setSeededStatus(boolean b){
	    this.seededStatus = b;
  }

  public void setProtectStatus(boolean b){
    this.protectStatus = b;
  }

  public void setBurnStatus(boolean b){
    this.burnStatus = b;
  }

  public boolean getBurnStatus(){
	    return burnStatus;
}

  public void setHHStatus(boolean b){
	    this.HHStatus = b;
  }

  // status conditions

  public boolean getProtect() {
	  return protectStatus;
  }

  public boolean getHH() {
	  return HHStatus;
  }

  public String applyPoison() {
	  if(this.poisonStatus == true) {
		  this.applyDamage(1);
		  return this.toString() + " is poisoned! It did 1 dmg!";
	  }else {
		  return null;
	  }
  }

  public String applyBurn() {
	  if(this.burnStatus == true) {
		  this.applyDamage(1);
		  return this.toString() + " is burned! It did 1 dmg!";
	  }else {
		  return null;
	  }
  }

  public String applySeed() {
	  if(this.seededStatus == true) {
		  this.applyDamage(1);
		  for(int i = 0; i < 3; i++) {
			  getFoe(i).applyDamage(-1);
		  }
		  return this.toString() + " has seeds planted on them! It did 1 dmg! All its opponents are healed for 1 hp!";
	  }else {
		  return null;
	  }
  }

}
