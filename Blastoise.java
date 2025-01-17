public class Blastoise extends Adventurer{
  private int ppCount, ppMax;

  public Blastoise(){
		super("Blastoise", 40, "Water");
		this.ppCount = 40;
		this.ppMax = 40;
	}

	public Blastoise(String name){
		super(name, 40, "Water");
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
	  int baseDmg = 2;
	  int sleep = (int)(Math.random() * 101);
		if(this.getSleepStatus() == true && sleep < 75){
			return this.toString() + " is asleep!";
		}
		this.setSleepStatus(false);
		if(other.getProtect() == true){
			baseDmg = 0;
			other.setProtectStatus(false);
		}
		if(this.getHH() == true){
			baseDmg*=2;
			this.setHHStatus(false);
		}
		if(getSpecial() != getSpecialMax()) {
			setSpecial(getSpecial() + 1);
		}

    if (other.getType().equals("Fire")){
      other.applyDamage(baseDmg * 2);
      return this.toString() + " used Aqua Tail! It's super effective! It did "+ baseDmg*2 +" dmg!";
    }
    if (other.getType().equals("Grass")){
      other.applyDamage(baseDmg);
      return this.toString() + " used Aqua Tail! It's not very effective... It did "+baseDmg/2+" dmg...";
    }
		other.applyDamage(baseDmg);
		return this.toString() + " used Aqua Tail! It did "+baseDmg+" dmg!";
	}

  public String support(Adventurer other) {
    // protect
	  int sleep = (int)(Math.random() * 101);
		if(this.getSleepStatus() == true && sleep < 75){
			return this.toString() + " is asleep!";
		}
		this.setSleepStatus(false);
		if (getSpecial()-2 > 0){
			other.setProtectStatus(true);
			setSpecial(getSpecial() - 2);
			if (other.toString().equals(this.toString())){
				return this.toString() + " used Protect on itself!";
			}
			else{
				return this.toString() + " used Protect!" + other.toString() + " was protected!";
			}
		}
		else{
			return "Not enough PP!";
		}
	}

	public String support() {
    // haze
		int sleep = (int)(Math.random() * 101);
		if(this.getSleepStatus() == true && sleep < 75){
			return this.toString() + " is asleep!";
		}
		this.setSleepStatus(false);
		if (getSpecial()-2 > 0){
			for(int i = 0; i < 3; i++) {
				if(Math.random() > 0.25) {
					getFoe(i).setSleepStatus(false);
					getFriend(i).setSleepStatus(false);
					getFoe(i).setPoisonStatus(false);
					getFriend(i).setPoisonStatus(false);
					getFoe(i).setSeededStatus(false);
					getFriend(i).setSeededStatus(false);
					getFoe(i).setBurnStatus(false);
					getFriend(i).setBurnStatus(false);
				}
			}
			setSpecial(getSpecial() - 2);
			return this.toString() + " used Haze! All status effects are cleared!";
		}
		else{
			return "Not enough PP!";
		}
	}

	public String specialAttack(Adventurer other) {
		// helping hand
		int sleep = (int)(Math.random() * 101);
		if(this.getSleepStatus() == true && sleep < 75){
			return this.toString() + " is asleep!";
		}
		this.setSleepStatus(false);
		if (getSpecial()-5 > 0){
			other.setHHStatus(true);
			setSpecial(getSpecial() - 5);
			return this.toString() + " used Helping Hand! " + this.toString() + " is ready to help " + other.toString() + "!";
		}
		else{
			return "Not enough PP!";
		}
	}

}
