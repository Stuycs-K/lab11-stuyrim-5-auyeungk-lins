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

		return null;
	}

	public String specialAttack(Adventurer other) {

		return null;
	}
	
}