package controll;

import java.util.Random;

import models.Card;
import models.HandCards;

//tracking the game and store the players
public class GamePlay {

	// tracking the game end or not
	private int status;

	// the number of players
	private int numOfPlayer;

	// Store the players
	private HandCards[] arrPlayers;

	// tracking turn of each players, ex: player 1: 3h > justplayer= 0,
	// player2 pass > justplayer= 0, player3: 6h > justplayer= 2: tracking the key
	// play.
	private int justPlayer;

	// tracking the next player will play. ex: player 1: 3h > activatingPlayer= 1,
	// player2 pass > activatingPlayer= 2, player3: 6h > activatingPlayer= 0:
	private int activatingPlayer;

	// store the cards of The [justPlayer]. delete and store constantly
	private Card[] currentCard;

	// tracking the suits that you throw, ex: 3d > single, 3d3p > double

	//private int currentType;

	// because number of cards.
	public static final int NUM_CARDS = 52;

	// tracking the game on
	public static final int STATUS_ON = 1;

	// tracking the game off
	public static final int STATUS_OFF = 0;

	public GamePlay(int numOfPlayed) {
		// game starts
		this.status = STATUS_ON;

		// number of players
		this.numOfPlayer = numOfPlayed;

		// create the array of players
		this.arrPlayers = new HandCards[numOfPlayed];
	}

	// divide cards for players
	public void Deal() {

		// create the array(52) for cards and set all element in that array equal 1. it
		// means have not taken
		int arr[] = new int[NUM_CARDS];
		for (int i = 0; i < NUM_CARDS; i++) {
			arr[i] = 1;
		}

		// create 1 array (52 cards) for cards + suits.
		Card[] arrCards = new Card[NUM_CARDS];
		for (int i = 0; i < NUM_CARDS; i++) {
			// values of cards
			int value = i % 13 + 3;

			// suits of cards
			int type = i % 4;

			arrCards[i] = new Card(value, type, false);

		}

		// divide for each player
		Random ran = new Random();

		// create the array store for players
		for (int i = 0; i < numOfPlayer; i++) {
			// create each player in array [new HandCards[numOfPlayed];]
			arrPlayers[i] = new HandCards();

			// create an array(13) for each player
			Card[] arrCardsPlayer = new Card[HandCards.NUM_OF_CARDS];
			int num = 0;

			while (num < HandCards.NUM_OF_CARDS) {

				// index random at area 51
				int index = ran.nextInt(NUM_CARDS);
				// check the array's index of [int arr[] = new int[NUM_CARDS]]
				// it equals 0 , it means that was used.
				while (arr[index] == 0) {
					// pick the other index
					index = ran.nextInt(NUM_CARDS);
				}
				// add the number index of [Card[] arrCards = new Card[NUM_CARDS]] = array of
				// players
				arrCardsPlayer[num] = arrCards[index];
				// set array's index of [Card[] arrCards = new Card[NUM_CARDS]] = 0, it means
				// that was used.
				arr[index] = 0;
				num++;
			}

			arrPlayers[i].setArrCards(arrCardsPlayer);
		}

	}

	// change the player
	public int NextPlayer() {
		// get the index of the current player
		int nextPlayer = justPlayer;

		for (int i = 0; i < numOfPlayer - 1; i++) {
			// 0 1 2 3
			nextPlayer = (nextPlayer + 1) % numOfPlayer;

			if (arrPlayers[nextPlayer].isActivated() == true)
				return nextPlayer;
		}
		//
		return -1;
	}

	// check the length of cards and check the values of cards depend on single,
	// double,...
	/*
	public boolean TestValid(Card[] arr) {

		// throwing is not valid
		if (Rules.IsValid(currentType, arr) == false) {
			return false;
		}
		// throwing is valid
		else if (Rules.IsWin(currentType, arr, this.currentCard) == true) {
			return true;
		}
		// throwing is not valid
		else {
			return false;
		}
	}
	*/

	// true, it means keep playing for the new round
	public void ActivatePlayer(int currentPlayer) {
		arrPlayers[currentPlayer].setActivated(true);
	}

	// false, it means skipping the turn.
	public void Ignore(int currentPlayer) {
		arrPlayers[currentPlayer].setActivated(false);
	}

	// next player
	// if [TestValid = true], accept the player goes, if [TestValid = false], no
	// play
	//
	public void Play(int currentPlayer) {
		// get the cards of the final player throwing or update the current card
		currentCard = arrPlayers[currentPlayer].getArrSelCards();

		// update current player
		justPlayer = currentPlayer;

		// get values of player's cards is holding
		// Bo luon cai nay
		//Card[] arrCards = arrPlayers[currentPlayer].getArrCards();

		for (int i = 0; i < currentCard.length; i++) {
			currentCard[i].setPlay(true);
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

	public HandCards[] getArrPlayers() {
		return arrPlayers;
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

	/*
	public int getCurrentType() {
		return currentType;
	}

	public void setCurrentType(int currentType) {
		this.currentType = currentType;
	}
	*/

}
