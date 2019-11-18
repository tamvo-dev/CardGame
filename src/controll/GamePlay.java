package controll;

import java.util.Random;

import models.Card;
import models.HandCards;

public class GamePlay {

	private int status;
	private int numOfPlayed;
	private HandCards[] arrPlayeds;
	private int justPlayed;
	private int activatingPlayed;
	private int rank;
	private Card[] currentCard;
	private int currentType;
	
	public static final int NUM_CARDS = 52;
	public static final int STATUS_ON = 1;
	public static final int STATUS_OFF = 0;
	
	public GamePlay(int numOfPlayed) {
		this.status = STATUS_ON;
		this.numOfPlayed = numOfPlayed;
		this.arrPlayeds = new HandCards[numOfPlayed];
	}

	public void Deal() {
		
		// Tao mang danh dau
		int arr[] = new int[NUM_CARDS];
		for(int i = 0; i< NUM_CARDS; i++) {
			arr[i] = 1;
		}
		
		// Tao mang cac quan bai
		Card[] arrCards = new Card[NUM_CARDS];
		for(int i=0; i<NUM_CARDS; i++) {	
			int value = i%13 + 3;
			int type =  i%4;
			arrCards[i] = new Card();
			arrCards[i].setPlay(false);
			arrCards[i].setValue(value);
			arrCards[i].setType(type);
		}
		
		// Chia bai cho moi nguoi choi
		Random ran = new Random();
		for(int i=0; i<numOfPlayed; i++) {
			arrPlayeds[i] = new HandCards();
			
			Card[] arrCardsPlayer = new Card[HandCards.NUM_OF_CARDS];
			int num = 0;
			while(num < HandCards.NUM_OF_CARDS) {
				
				int index = ran.nextInt(NUM_CARDS);
				while(arr[index] == 0) {
					index = ran.nextInt(NUM_CARDS);
				}
				arrCardsPlayer[num] = arrCards[index];
				arr[index] = 0;
				num++;
			}
			
			arrPlayeds[i].setArrCards(arrCardsPlayer);
		}
		
	}
	
	public int NextPlayer() {
		
		int nextPlayer = justPlayed;
		for(int i=1; i<numOfPlayed; i++) {
			nextPlayer = (nextPlayer + 1) % numOfPlayed;
			if(arrPlayeds[nextPlayer].isActivated())
				return nextPlayer;
		}
		
		return -1;
	}
	
	public void RemovePlayer(int currentPlayer) {
		arrPlayeds[currentPlayer].setStatus(HandCards.STATUS_OFF);
		arrPlayeds[currentPlayer].setActivated(false);
	}
	
	public boolean TestValid(Card[] arr) {
		
		if(!Rules.IsValid(currentType, arr)) {
			return false;
		} 
		else if(Rules.IsWin(currentType, arr, this.currentCard)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void ActivatePlayed(int currentPlayer) {
		arrPlayeds[currentPlayer].setActivated(true);
	}
	
	public void Ignore(int currentPlayer) {
		arrPlayeds[currentPlayer].setActivated(false);
	}
	
	public void Play(int currentPlayer) {
		
		currentCard = arrPlayeds[currentPlayer].getArrSelCards();
		justPlayed = currentPlayer;
		
		HandCards player = arrPlayeds[currentPlayer];
		Card[] arrCards = player.getArrCards();
		
		int numOfRemainCards = player.getNumOfRemainCards() - currentCard.length;
		player.setNumOfRemainCards(numOfRemainCards);
		
		for(int i=0; i<currentCard.length; i++) {
			for(int j=0; j<arrCards.length; j++) {
				if(currentCard[i].equals(arrCards[j])) {
					
					// Danh dau nhung quan bai da danh
					arrCards[j].setPlay(true);
					break;
				}
			}
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getNumOfPlayed() {
		return numOfPlayed;
	}

	public HandCards[] getArrPlayeds() {
		return arrPlayeds;
	}

	public void setArrPlayeds(HandCards[] arrPlayeds) {
		this.arrPlayeds = arrPlayeds;
	}

	public int getJustPlayed() {
		return justPlayed;
	}

	public void setJustPlayed(int justPlayed) {
		this.justPlayed = justPlayed;
	}

	public int getActivatingPlayed() {
		return activatingPlayed;
	}

	public void setActivatingPlayed(int activatingPlayed) {
		this.activatingPlayed = activatingPlayed;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getCurrentType() {
		return currentType;
	}

	public void setCurrentType(int currentType) {
		this.currentType = currentType;
	}
	
}
