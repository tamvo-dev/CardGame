package models;

public class Card {
	
	private int value;
	private int type;
	private boolean isPlay;
	
	public static final int VALUE_J = 11;
	public static final int VALUE_Q = 12;
	public static final int VALUE_K = 13;
	public static final int VALUE_A = 14;
	public static final int VALUE_2 = 15;
	
	public static final int TYPE_BICH = 1;
	public static final int TYPE_CHUON = 2;
	public static final int TYPE_RO = 3;
	public static final int TYPE_CO = 4;

	public Card() {
		
	}

	public Card(int value, int type, boolean isPlay) {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isPlay() {
		return isPlay;
	}

	public void setPlay(boolean isPlay) {
		this.isPlay = isPlay;
	}

	@Override
	public String toString() {
		return "Card [value=" + value + ", type=" + type + ", isPlay=" + isPlay + "]";
	}
	
}
