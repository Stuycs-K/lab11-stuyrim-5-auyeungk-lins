
public class Venusaur extends Adventurer{
	private String pp;
	private int ppCount, ppMax;
	
	
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
