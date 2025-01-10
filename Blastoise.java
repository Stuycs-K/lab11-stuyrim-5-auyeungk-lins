public class Blastoise extends Adventurer{
  private int ppCount, ppMax;

  public Blastoise(){
		super("Blastoise", 20);
		this.ppCount = 40;
		this.ppMax = 40;
	}

	public Blastoise(String s){
		super(s, 20);
		this.ppCount = 40;
		this.ppMax = 40;
	}

	public String getSpecialName() {
		return "PP";
	}

	public int getSpecial() {
		return ppCount;
	}


	public int getSpecialMax() {
		return ppMax;
	}

  public void setSpecial(int n) {
		ppCount = n;
	}

  public String attack(Adventurer other) {
    if (other.getType().equals("fire")){
      other.applyDamage(4);
      return this.toString() + " used Aqua Tail! It's super effective! (4 dmg)";
    }
    if (other.getType().equals("grass")){
      other.applyDamage(1);
      return this.toString() + " used Aqua Tail! It's not very effective... (1 dmg)";
    }
		other.applyDamage(2);
		return this.toString() + " used Aqua Tail! It did 2 dmg!";
	}

  public String support(Adventurer other) {
    // haze
		other.setSeededStatus(true);
		return this.toString() + " used Haze! Seeds were planted on " + other.toString() + "!";
	}

	public String support(other) {
    // protect
		other.setProtectStatus(true);
		return this.toString() + " used Protect! " + this.toString() + " protected " ;
	}

	public String specialAttack(Adventurer other) {
		other.applyDamage(10);
		if(Math.random() > 0.25)
		other.setPoisonStatus(true);
		return this.toString() + " used Sludge Bomb on " + other.toString() + "!";
	}

}
