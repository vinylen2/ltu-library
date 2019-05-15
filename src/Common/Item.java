package Common;

public class Item {
	private int ID;
	private String name;
	private int SNorAge;
	private String type;
	
	public Item(int id, String name, int SNorAge, String type) {
		this.ID = id;
		this.name = name;
		this.SNorAge = SNorAge;
		this.type = type;
	}
	
	public int getId() {
		return this.ID;
	}

	public String getIdInString() {
		return Integer.toString(this.ID);
	}

	public String getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public int getSNorAge() {
		return this.SNorAge;
	}
	
	
}
