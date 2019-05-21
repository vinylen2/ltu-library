package Common;

public class Item {
	private int objectId;
	private String name;
	private int SNorAge;
	private String type;
	private String returnDate;
	
	public Item(int objectId, String name, int SNorAge, String type) {
		this.name = name;
		this.SNorAge = SNorAge;
		this.type = type;
		this.objectId = objectId;
	}

	public Item(int objectId, String name, int SNorAge, String type, String returnDate) {
		this.name = name;
		this.SNorAge = SNorAge;
		this.type = type;
		this.objectId = objectId;
		this.returnDate = returnDate;
	}
	
	public String getObjectIdInString() {
		return Integer.toString(this.objectId);
	}
	
	public int getObjectId() {
		return this.objectId;
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
	
	public String getReturnDate() {
		return this.returnDate;
	}
	
	
}
