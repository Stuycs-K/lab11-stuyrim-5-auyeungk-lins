public class Charizard extends Adventurer{
	private int ppCount, ppMax, swordsDance; 
	public Charizard(){
		super("Charizard", 25, "Fire");
		this.ppCount = 40;
		this.ppMax = 40;
		this.swordsDance = 0;
	}

	public Charizard(String s){
		super(s, 25, "Fire");
		this.ppCount = 40;
		this.ppMax = 40;
		this.swordsDance = 0;
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
	
	public void setSD(int n) {
		this.swordsDance = n;
	}
	
	public int getSD() {
		return swordsDance;
	}
	
	public String attack(Adventurer other) {
		// ember
		int baseDmg = 2;
		if(other.getProtect() == true){
			baseDmg = 0;
			other.setProtectStatus(false);
		}
		if(this.getHH() == true){
			baseDmg*=2;
			this.setHHStatus(false);
		}
		if(this.getSD() > 0) {
			baseDmg*=2;
			setSD(getSD() - 1);
		}
		 if(baseDmg != 0) {
			 if(Math.random() > 0.75) {
				 other.setBurnStatus(true);
			 }
		 }
		if (other.getType().equals("Grass")){
		      other.applyDamage(baseDmg * 2);
		      if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
		      return this.toString() + " used Ember! It's super effective! It did " + baseDmg*2 + " dmg!";
		    }
		    if (other.getType().equals("Water")){
		      other.applyDamage(baseDmg/2);
		      if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
		      return this.toString() + " used Ember! It's not very effective... It did " + baseDmg/2 +" dmg...";
		    }
				other.applyDamage(baseDmg);
				if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
				return this.toString() + " used Ember! It did "+ baseDmg+" dmg!";
	}

	public String support(Adventurer other) {
		// flamethrower
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
			if(this.getSD() > 0) {
				baseDmg*=2;
				setSD(getSD() - 1);
			}
			setSpecial(getSpecial() - 5);
			if(baseDmg != 0) {
				 if(Math.random() > 0.75) {
					 other.setBurnStatus(true);
				 }
			 }
			if (other.getType().equals("Grass")){
			      other.applyDamage(baseDmg * 2);
			      
			      return this.toString() + " used Flamethrower! It's super effective! It did "+ baseDmg*2+ " dmg!";
			    }
			    if (other.getType().equals("Water")){
			      other.applyDamage(baseDmg/2);
			      
			      return this.toString() + " used Flamethrower! It's not very effective... It did " + baseDmg/2 + " dmg...";
			    }
					other.applyDamage(baseDmg);
					
					return this.toString() + " used Flamethrower! It did " + baseDmg + " dmg!";
		}else {
			return "Not enough PP!";
		}
	}


	public String support() {
		this.setSD(2);
		return this.toString() + " used Swords Dance! It powered up its moves for the next 2 turns!";
	}

	public String specialAttack(Adventurer other) {
		// flare blitz
		if (getSpecial()-10 > 0){
			int baseDmg = 8;
			if(other.getProtect() == true){
				baseDmg = 0;
				other.setProtectStatus(false);
			}
			if(this.getHH() == true){
				baseDmg*=2;
				this.setHHStatus(false);
			}
			if(this.getSD() > 0) {
				baseDmg*=2;
				setSD(getSD() - 1);
			}
			setSpecial(getSpecial() - 10);
			if(baseDmg != 0) {
				 if(Math.random() > 0.75) {
					 other.setBurnStatus(true);
				 }
			 }
			if (other.getType().equals("Grass")){
			      other.applyDamage(baseDmg * 2);
			      this.applyDamage(3);
			      return this.toString() + " used Flare Blitz! It's super effective! It did "+ baseDmg*2 +" dmg! " + this.toString() + " was hit by recoil!";
			    }
			    if (other.getType().equals("Water")){
			      other.applyDamage(baseDmg/2);
			      this.applyDamage(3);
			      return this.toString() + " used Flare Blitz! It's not very effective... It did "+ baseDmg/2 +" dmg... " + this.toString() + " was hit by recoil!";
			    }
					other.applyDamage(baseDmg);
					this.applyDamage(3);
					return this.toString() + " used Flare Blitz! It did " + baseDmg + " dmg! " + this.toString() + " was hit by recoil!";
		}else {
			return "Not enough PP!";
		}
	}
	
}