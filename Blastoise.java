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
      other.applyDamage(1);
      return this.toString() + " used Aqua Tail! It's super effective! (2 dmg)";
    }
		other.applyDamage(1);
		return this.toString() + " used Aqua Tail! It did 1 dmg!";
	}

}
