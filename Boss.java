public class Boss extends Adventurer{
	 private int ppCount, ppMax;

	  public Boss(){
			super("Mewtwo", 100, "Psychic");
			this.ppCount = 80;
			this.ppMax = 80;
		}

		public Boss(String name){
			super(name, 100, "Psychic");
			this.ppCount = 80;
			this.ppMax = 80;
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

		return null;
	}


	public String support(Adventurer other) {

		return null;
	}


	public String support() {
		if(getSpecial() - 5 > 0) {
			if(getHP() < getmaxHP()/2) {
				setHP(getHP() + getmaxHP());
				return this.toString() + " used Recover! It restored " + getmaxHP()/2 + " HP!";
			}else {
				int healAmt = getmaxHP() - getHP();
				setHP(getmaxHP());
				return this.toString() + " used Recover! It restored " + healAmt + " HP!";
			}
		}else {
			return this.toString() + " has not enough PP!";
		}
		
	}

	public String specialAttack(Adventurer other) {

		return null;
	}
	
}