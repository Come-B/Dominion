package cards;

public class Gold extends Silver {

	public Gold() {
		super();
		this.goldCost=6;
		this.plusGold=3;
	}
	
	public int getStartingNumber(int players) {return 30;}

}
