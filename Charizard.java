public class Charizard extends Adventurer{
	private int ppCount, ppMax, swordsDance; 
	public Charizard(){
		super("Charizard", 15, "Fire");
		this.ppCount = 40;
		this.ppMax = 40;
		this.swordsDance = 0;
	}

	public Charizard(String s){
		super(s, 15, "Fire");
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
			this.setHelpingHandStatus(false);
		}
		if(this.getSD() > 0) {
			baseDmg*=2;
			setSD(getSD() - 1);
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
		int baseDmg = 4;
		if(other.getProtect() == true){
			baseDmg = 0;
			other.setProtectStatus(false);
		}
		if(this.getHH() == true){
			baseDmg*=2;
			this.setHelpingHandStatus(false);
		}
		if(this.getSD() > 0) {
			baseDmg*=2;
			setSD(getSD() - 1);
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
	}


	public String support() {
		//swords dance
		return null;
	}

	public String specialAttack(Adventurer other) {
		// flare blitz
		int baseDmg = 6;
		if(other.getProtect() == true){
			baseDmg = 0;
			other.setProtectStatus(false);
		}
		if(this.getHH() == true){
			baseDmg*=2;
			this.setHelpingHandStatus(false);
		}
		if(this.getSD() > 0) {
			baseDmg*=2;
			setSD(getSD() - 1);
		}
		
		if (other.getType().equals("Grass")){
		      other.applyDamage(baseDmg * 2);
		      
		      return this.toString() + " used Flare Blitz! It's super effective! It did "+ baseDmg*2 +" dmg!";
		    }
		    if (other.getType().equals("Water")){
		      other.applyDamage(baseDmg/2);
		     
		      return this.toString() + " used Flare Blitz! It's not very effective... It did "+ baseDmg/2 +" dmg...";
		    }
				other.applyDamage(baseDmg);
				
				return this.toString() + " used Flare Blitz! It did " + baseDmg + " dmg!";
	}
	
}