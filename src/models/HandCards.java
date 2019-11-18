package models;

import controll.Rules;

public class HandCards {

	private int numOfSelectedCards;
	private int numOfRemainCards;
	private Card[] arrCards;
	private Card[] arrSelCards;
	private int status;
	private boolean isActivated;
	private int rank;
	private String name;
	
	public static final int NUM_OF_CARDS = 13;
	public static final int STATUS_ON = 1;
	public static final int STATUS_OFF = 0;
	
	public HandCards() {
		numOfRemainCards = NUM_OF_CARDS;
		rank = -1;
		isActivated = true;
		status = STATUS_ON;
	}
	
	public void ShowCards() {
		
		Rules.SortCards(arrCards);
		System.out.println("Cards of " + name);
		
		for(int i=0; i<arrCards.length; i++) {
			if(arrCards[i].isPlay() == false) {
				String line = "";
				if(i < 10) {
					line += " " + i + "    ->    " + arrCards[i].toString();
				}
				else {
					line +=  i + "    ->    " + arrCards[i].toString();
				}
				System.out.println(line);
			}
		}
	}
	
	public boolean isWin() {
		// Neu co bat ki la bai nao chua danh tuc la chua ve nhat duoc
		for(int i=0; i<arrCards.length; i++) {
			if(arrCards[i].isPlay() == false)
				return false;
		}
		return true;
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

	public Card getCard(int position) {
		return arrCards[position];
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
