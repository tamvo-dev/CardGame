package models;

import controll.Rules;

public class HandCards {

	// store all the cards of only 1 players
	private Card[] arrCards;
	
	// store the number of cards the player want to throwing before checking
	private Card[] arrSelCards;
	
	// check there are any cards in player's hand or not
	private int status;
	
	// tracking the player's turn	
	private boolean isActivated;
	
	
	// the default cards of each player
	public static final int NUM_OF_CARDS = 13;
	
	// you still have cards in your hand
	public static final int STATUS_ON = 1;
	
	// out of cards in your hand.
	public static final int STATUS_OFF = 0;
	
	
	public HandCards() {
			
		
		// true is mean that is still playing
		isActivated = true;
		
		// still playing
		status = STATUS_ON;
	}
	
	// Display the cards in player's hand 
	// isPlay = true > the cards were played so not to display that cards
	public void ShowCards() {
		Rules.SortCards(arrCards);
		for(int i=0; i<arrCards.length; i++) {
			if(arrCards[i].isPlay() == false) {
				// Format String
				System.out.println(i + "    ->    " + arrCards[i].toString() + " ");
			}
		}
		System.out.print("\n");
	}

	public boolean IsPlayerWin() {
		for(int i=0; i<arrCards.length; i++) {
			if(arrCards[i].isPlay() == false)
				return false;
		}
		return true;
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
	
}
