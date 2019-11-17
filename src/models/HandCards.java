package models;

public class HandCards {

	private int numOfSelectedCards;
	private int numOfRemainCards;
	private Card[] arrCards;
	private Card[] arrSelCards;
	private int status;
	private boolean isActivated;
	private int rank;
	
	public static final int NUM_OF_CARDS = 13;
	public static final int STATUS_ON = 1;
	public static final int STATUS_OFF = 0;
	
	public HandCards() {
		
		numOfRemainCards = NUM_OF_CARDS;
		rank = -1;
		isActivated = true;
		status = STATUS_ON;
	}

	public int getNumOfSelectedCards() {
		return numOfSelectedCards;
	}

	public void setNumOfSelectedCards(int numOfSelectedCards) {
		this.numOfSelectedCards = numOfSelectedCards;
	}

	public int getNumOfRemainCards() {
		return numOfRemainCards;
	}

	public void setNumOfRemainCards(int numOfRemainCards) {
		this.numOfRemainCards = numOfRemainCards;
	}

	public Card[] getArrCards() {
		return arrCards;
	}

	public void setArrCards(Card[] arrCards) {
		this.arrCards = arrCards;
	}
	
	public Card[] getArrSelCards() {
		return arrSelCards;
	}

	public void setArrSelCards(Card[] arrSelCards) {
		this.arrSelCards = arrSelCards;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
}
