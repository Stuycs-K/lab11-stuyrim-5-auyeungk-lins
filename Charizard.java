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
	
	public String attack(Adventurer other) {
		// ember
		 if (other.getType().equals("Grass")){
		      other.applyDamage(4);
		      if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
		      return this.toString() + " used Ember! It's super effective! It did 4 dmg!";
		    }
		    if (other.getType().equals("Water")){
		      other.applyDamage(1);
		      if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
		      return this.toString() + " used Ember! It's not very effective... It did 1 dmg...";
		    }
				other.applyDamage(2);
				if(getSpecial() != getSpecialMax()) {
					setSpecial(getSpecial() + 1);
				}
				return this.toString() + " used Ember! It did 2 dmg!";
	}

	public String support(Adventurer other) {
		// flamethrower
		if (other.getType().equals("Grass")){
		      other.applyDamage(8);
		      
		      return this.toString() + " used Flamethrower! It's super effective! It did 8 dmg!";
		    }
		    if (other.getType().equals("Water")){
		      other.applyDamage(2);
		      
		      return this.toString() + " used Flamethrower! It's not very effective... It did 2 dmg...";
		    }
				other.applyDamage(4);
				
				return this.toString() + " used Flamethrower! It did 4 dmg!";
	}


	public String support() {
		//swords dance
		return null;
	}

	public String specialAttack(Adventurer other) {
		// flare blitz
		if (other.getType().equals("Grass")){
		      other.applyDamage(12);
		      
		      return this.toString() + " used Flare Blitz! It's super effective! It did 12 dmg!";
		    }
		    if (other.getType().equals("Water")){
		      other.applyDamage(3);
		     
		      return this.toString() + " used Flare Blitz! It's not very effective... It did 3 dmg...";
		    }
				other.applyDamage(6);
				
				return this.toString() + " used Flare Blitz! It did 6 dmg!";
	}
	
}