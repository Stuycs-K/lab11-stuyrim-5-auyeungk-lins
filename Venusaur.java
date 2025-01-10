
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
		return this.toString() + " used Vine Whip! It did 1 dmg!";
	}

	public String support(Adventurer other) {
		other.setPoisonStatus(true);
		return null;
	}
	
	public String support() {
		return null;
	}

	public String support2(Adventurer other) {
		other.setSleepStatus(true);
		return null;
	}

	public String specialAttack(Adventurer other) {

		return null;
	}

}
