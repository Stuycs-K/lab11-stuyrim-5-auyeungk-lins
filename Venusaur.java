public class Venusaur extends Adventurer{
	private int ppCount, ppMax;


	public Venusaur(){
		super("Venusaur", 20);
		this.ppCount = 40;
		this.ppMax = 40;
	}

	public Venusaur(String s){
		super(s, 20);
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
		other.applyDamage(1);
		return this.toString() + " used Vine Whip on " + other.toString() + "!";
	}

	public String support(Adventurer other) {
		other.setSeededStatus(true);
		return this.toString() + " used Leech Seed! Seeds were planted on " + other.toString() + "!";
	}

	public String support() {
		foe.setSleepStatus(true);
		return this.toString() + " used Sleep Powder!";
	}

	public String specialAttack(Adventurer other) {
		other.applyDamage(10);
		if(Math.random() > 0.25)
		other.setPoisonStatus(true);
		return this.toString() + " used Sludge Bomb on " + other.toString() + "!";
	}

}
