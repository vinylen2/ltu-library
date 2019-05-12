package Common;

public class Item {
	private int ID;
	private String name;
	private int SNorAge;
	
	public Item(int id, String name, int SNorAge) {
		this.ID = id;
		this.name = name;
		this.SNorAge = SNorAge;
	}
	
	public int getId() {
		return this.ID;
	}

	public String getName() {
		return this.name;
	}

	public int getSNorAge() {
		return this.SNorAge;
	}
	
	
}
