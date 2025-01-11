public class Venusaur extends Adventurer{
	private int ppCount, ppMax;


	public Venusaur(){
		super("Venusaur", 20, "Grass");
		this.ppCount = 40;
		this.ppMax = 40;
	}

	public Venusaur(String s){
		super(s, 20, "Grass");
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
		// vine whip
		
		 if (other.getType().equals("Water")){
		      other.applyDamage(4);
		      if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
		      return this.toString() + " used Vine Whip! It's super effective! It did 4 dmg!";
		    }
		    if (other.getType().equals("Fire")){
		      other.applyDamage(1);
		      if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
		      return this.toString() + " used Vine Whip! It's not very effective... It did 1 dmg...";
		    }
				other.applyDamage(2);
				if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
				return this.toString() + " used Vine Whip! It did 2 dmg!";
	}

	public String support(Adventurer other) {
		// leech seed
		if (getSpecial()-2 > 0){
			other.setSeededStatus(true);
			setSpecial(getSpecial() - 2);
			return this.toString() + " used Leech Seed! Seeds were planted on " + other.toString() + "!";
		}
		else{
			return "Not enough PP!";
		}
		
	}

	public String support() {
		// sleep powder
		if (getSpecial()-2 > 0){
			for(int i = 0; i < foeCount(); i++) {
				if(Math.random() > 0.25) {
					getFoe(i).setSleepStatus(true);
				}
			}
			setSpecial(getSpecial() - 2);
			return this.toString() + " used Sleep Powder!";
		}
		else{
			return "Not enough PP!";
		}
		
	}

	public String specialAttack(Adventurer other) {
		//sludge bomb
		if (getSpecial()-5 > 0){
			other.applyDamage(10);
			if(Math.random() > 0.25) {
				other.setPoisonStatus(true);
			}	
			setSpecial(getSpecial() - 5);
			return this.toString() + " used Sludge Bomb on " + other.toString() + "!";
		}
		else{
			return "Not enough PP!";
		}
		
	}

}
