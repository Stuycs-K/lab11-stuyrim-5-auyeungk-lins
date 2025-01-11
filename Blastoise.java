public class Blastoise extends Adventurer{
  private int ppCount, ppMax;

  public Blastoise(){
		super("Blastoise", 20, "Water");
		this.ppCount = 40;
		this.ppMax = 40;
	}

	public Blastoise(String name){
		super(name, 20, "Water");
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
      if(getSpecial() != getSpecialMax()) {
			setSpecial(getSpecial() + 1);
		}
      return this.toString() + " used Aqua Tail! It's super effective! (4 dmg)";
    }
    if (other.getType().equals("grass")){
      other.applyDamage(1);
      if(getSpecial() != getSpecialMax()) {
			setSpecial(getSpecial() + 1);
		}
      return this.toString() + " used Aqua Tail! It's not very effective... (1 dmg)";
    }
		other.applyDamage(2);
		if(getSpecial() != getSpecialMax()) {
			setSpecial(getSpecial() + 1);
		}
		return this.toString() + " used Aqua Tail! It did 2 dmg!";
	}

  public String support(Adventurer other) {
    // haze
		if (getSpecial()-2 > 0){
			other.setSeededStatus(true);
			setSpecial(getSpecial() - 2);
			return this.toString() + " used Haze! " + other.toString() + "'s status effects are cleared!";
		}
		else{
			return "Not enough PP!";
		}
	}

	public String support() {
    // protect
		if (getSpecial()-2 > 0){
			this.setProtectStatus(true);
			setSpecial(getSpecial() - 2);
			return this.toString() + " used Protect!";
		}
		else{
			return "Not enough PP!";
		}
	}

	public String specialAttack(Adventurer other) {
		// helping hand
		if (getSpecial()-5 > 0){
			other.setHelpingHandStatus(true);
			setSpecial(getSpecial() - 5);
			return this.toString() + " used Helping Hand! " + this.toString() + " is ready to help " + other.toString() + "!";
		}
		else{
			return "Not enough PP!";
		}
	}

}
