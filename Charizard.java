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
		return null;
	}

	public String support(Adventurer other) {
		// flamethrower
		return null;
	}


	public String support() {
		//swords dance
		return null;
	}

	public String specialAttack(Adventurer other) {
		// flare blitz
		return null;
	}
	
}