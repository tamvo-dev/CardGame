package models;

public class Card {
	
	private int value;
	private String type;
	private boolean isPlay;
	
	public Card() {
		
	}

	public Card(int value, String type, boolean isPlay) {
		this.value = value;
		this.type = type;
		this.isPlay = isPlay;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isPlay() {
		return isPlay;
	}

	public void setPlay(boolean isPlay) {
		this.isPlay = isPlay;
	}
	
}
