public class Boss extends Adventurer{
	 private int ppCount, ppMax, buff;

	  public Boss(){
			super("Mewtwo", 100, "Psychic");
			this.ppCount = 80;
			this.ppMax = 80;
			this.buff = 0;
		}

		public Boss(String name){
			super(name, 100, "Psychic");
			this.ppCount = 80;
			this.ppMax = 80;
			this.buff = 0;
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
	  
	  public void setBuff(int n) {
			this.buff = n;
		}
		
		public int getBuff() {
			return buff;
		}

	public String attack(Adventurer other) {
		int baseDmg = 4;
		if(other.getProtect() == true){
			baseDmg = 0;
			other.setProtectStatus(false);
		}
		if(getBuff() > 0) {
			baseDmg*=2;
			setBuff(0);
		}
		if(getSpecial() != getSpecialMax()) {
			setSpecial(getSpecial() + 1);
		}
		other.applyDamage(baseDmg);
		return this.toString() + " used Psychic! It did " + baseDmg + " dmg!";
	}


	public String support(Adventurer other) {
		int baseDmg = 6;
		if(other.getProtect() == true){
			baseDmg = 0;
			other.setProtectStatus(false);
		}
		if(getBuff() > 0) {
			baseDmg*=2;
			setBuff(0);
		}
		if(getSpecial() - 10 > 0) {
			other.applyDamage(baseDmg);
			setBuff(1);
			return this.toString() + " used Power-Up Punch! It did " + baseDmg + " dmg!";
		}else {
			return this.toString() + " has not enough PP!";
		}
	}


	public String support() {
		if(getSpecial() - 10 > 0) {
			if(getHP() < getmaxHP()/2) {
				setHP(getHP() + getmaxHP());
				return this.toString() + " used Recover! It restored " + getmaxHP()/2 + " HP!";
			}else {
				int healAmt = getmaxHP() - getHP();
				setHP(getmaxHP());
				if(healAmt > 0) {
					return this.toString() + " used Recover! It restored " + healAmt + " HP!";
				}else {
					return this.toString() + " used Recover! Its HP was already full!";
				}
			}
		}else {
			return this.toString() + " has not enough PP!";
		}
		
	}

	public String specialAttack(Adventurer other) {
		int baseDmg = 10;
		if(other.getProtect() == true){
			baseDmg = 0;
			other.setProtectStatus(false);
		}
		if(getBuff() > 0) {
			baseDmg*=2;
			setBuff(0);
		}
		if(getSpecial() - 20 > 0) {
			other.applyDamage(baseDmg);
			return this.toString() + " used Psyshock! It did " + baseDmg + " dmg!";
		}else {
			return this.toString() + " has not enough PP!";
		}
	}
	
}