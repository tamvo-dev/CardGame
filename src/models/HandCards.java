package models;

public class HandCards {

	private int numOfCards;
	private int numOfSelectedCards;
	private int numOfRemainCards;
	private Card[] arrCards;
	private Card[] arrSelCards;
	private int status;
	private boolean isActivated;
	private int rank;
	
	public HandCards() {
		
	}
	
	public HandCards(int numOfCards, int numOfSelectedCards, int numOfRemainCards, Card[] arrCards, Card[] arrSelCards,
			int status, boolean isActivated, int rank) {

		this.numOfCards = numOfCards;
		this.numOfSelectedCards = numOfSelectedCards;
		this.numOfRemainCards = numOfRemainCards;
		this.arrCards = arrCards;
		this.arrSelCards = arrSelCards;
		this.status = status;
		this.isActivated = isActivated;
		this.rank = rank;
	}

	public int getNumOfCards() {
		return numOfCards;
	}

	public void setNumOfCards(int numOfCards) {
		this.numOfCards = numOfCards;
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
