package controll;

import java.util.Random;

import models.Card;
import models.HandCards;

public class GamePlay {

	private int status;
	private int numOfPlayer;
	private HandCards[] arrPlayers;
	private int justPlayer;
	private int activatingPlayer;
	private int rank;
	private Card[] currentCard;
	private int currentType;
	
	public static final int NUM_CARDS = 52;
	public static final int STATUS_ON = 1;
	public static final int STATUS_OFF = 0;
	
	public GamePlay(int numOfPlayer) {
		this.status = STATUS_ON;
		this.numOfPlayer = numOfPlayer;
		this.arrPlayers = new HandCards[numOfPlayer];
		this.rank = 1;
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
		for(int i=0; i<numOfPlayer; i++) {
			
			
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
			
			arrPlayers[i] = new HandCards();
			arrPlayers[i].setArrCards(arrCardsPlayer);
			String name = "Player " + (i + 1);
			arrPlayers[i].setName(name);
		}
		
	}
	
	public int NextPlayer() {
		
		int nextPlayer = justPlayer;
		for(int i=1; i<numOfPlayer; i++) {
			nextPlayer = (nextPlayer + 1) % numOfPlayer;
			if(arrPlayers[nextPlayer].isActivated() 
					&& nextPlayer != justPlayer 
					&& arrPlayers[nextPlayer].getStatus() != HandCards.STATUS_OFF)
				return nextPlayer;
		}
		
		return -1;
	}
	
	public void RemovePlayer(int currentPlayer) {
		arrPlayers[currentPlayer].setStatus(HandCards.STATUS_OFF);
		arrPlayers[currentPlayer].setActivated(false);
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
	
	public void ActivatePlayer(int currentPlayer) {
		arrPlayers[currentPlayer].setActivated(true);
	}
	
	public void Ignore(int currentPlayer) {
		arrPlayers[currentPlayer].setActivated(false);
	}
	
	public void Play(int currentPlayer) {
		
		currentCard = arrPlayers[currentPlayer].getArrSelCards();
		justPlayer = currentPlayer;
		
		HandCards player = arrPlayers[currentPlayer];
		
		int numOfRemainCards = player.getNumOfRemainCards() - currentCard.length;
		player.setNumOfRemainCards(numOfRemainCards);
		
		for(int i=0; i<currentCard.length; i++) {
			for(int j=0; j<HandCards.NUM_OF_CARDS; j++) {
				if(currentCard[i].equals(player.getCard(j))) {
					
					// Danh dau nhung quan bai da danh
					player.getCard(j).setPlay(true);
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

	public int getNumOfPlayer() {
		return numOfPlayer;
	}

	public HandCards getPlayer(int position) {
		return arrPlayers[position];
	}

	public void setArrPlayers(HandCards[] arrPlayeds) {
		this.arrPlayers = arrPlayeds;
	}

	public int getJustPlayer() {
		return justPlayer;
	}

	public void setJustPlayer(int justPlayed) {
		this.justPlayer = justPlayed;
	}

	public int getActivatingPlayer() {
		return activatingPlayer;
	}

	public void setActivatingPlayer(int activatingPlayed) {
		this.activatingPlayer = activatingPlayed;
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
