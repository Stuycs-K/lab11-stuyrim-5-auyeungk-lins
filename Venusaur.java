public class Venusaur extends Adventurer{
	private int ppCount, ppMax;
	private boolean isFriend;


	public Venusaur(){
		super("Venusaur", 50, "Grass");
		this.ppCount = 40;
		this.ppMax = 40;
	}

	public Venusaur(String s){
		super(s, 50, "Grass");
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
		 if (other.getType().equals("Water")){
		      other.applyDamage(baseDmg * 2);
		      if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
		      return this.toString() + " used Vine Whip! It's super effective! It did "+ baseDmg *2 +" dmg!";
		    }
		    if (other.getType().equals("Fire")){
		      other.applyDamage(baseDmg/2);
		      if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
		      return this.toString() + " used Vine Whip! It's not very effective... It did "+ baseDmg/2 +" dmg...";
		    }
				other.applyDamage(baseDmg);
				if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
				return this.toString() + " used Vine Whip! It did "+ baseDmg +" dmg!";
	}

	public String support(Adventurer other) {
		// leech seed
		int sleep = (int)(Math.random() * 101);
		if(this.getSleepStatus() == true && sleep < 75){
			return this.toString() + " is asleep!";
		}
		this.setSleepStatus(false);
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
		int sleep = (int)(Math.random() * 101);
		if(this.getSleepStatus() == true && sleep < 75){
			return this.toString() + " is asleep!";
		}
		this.setSleepStatus(false);
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
		int sleep = (int)(Math.random() * 101);
		if(this.getSleepStatus() == true && sleep < 75){
			return this.toString() + " is asleep!";
		}
		this.setSleepStatus(false);
		if (getSpecial()-5 > 0){
			int baseDmg = 4;
			if(other.getProtect() == true){
				baseDmg = 0;
				other.setProtectStatus(false);
			}
			if(this.getHH() == true){
				baseDmg*=2;
				this.setHHStatus(false);
			}
			setSpecial(getSpecial() - 5);
			if(baseDmg != 0) {
				 if(Math.random() > 0.25) {
					 other.setPoisonStatus(true);
				 }
			 }
			if (other.getType().equals("Water")){
			      other.applyDamage(baseDmg * 2);

			      return this.toString() + " used Sludge Bomb! It's super effective! It did "+ baseDmg*2+ " dmg!";
			    }
			    if (other.getType().equals("Fire")){
			      other.applyDamage(baseDmg/2);

			      return this.toString() + " used Sludge Bomb! It's not very effective... It did " + baseDmg/2 + " dmg...";
			    }
					other.applyDamage(baseDmg);

					return this.toString() + " used Sludge Bomb! It did " + baseDmg + " dmg!";
		}
		else{
			return "Not enough PP!";
		}

	}

}
