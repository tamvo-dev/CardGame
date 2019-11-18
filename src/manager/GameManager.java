package manager;

import java.util.Scanner;

import controll.GamePlay;
import controll.Rules;
import models.Card;
import models.HandCards;

public class GameManager {

	private GamePlay gamePlay;
	private GameEvent gamEvent;
	private Scanner scanner;
	private HandCards currentPlayer = null;
	private int currentType;
	private Card[] currentCard;
	private int numOfPlayer;
	
	public GameManager(GamePlay gamePlay, Scanner scanner ,GameEvent gameEvent) {
		this.gamePlay = gamePlay;
		this.scanner = scanner;
		this.gamEvent = gameEvent;
		this.numOfPlayer = gamePlay.getNumOfPlayer();
		this.currentType = Rules.THROWING_UNKNOWN;
	}
	
	
	public void StartGame() {

		// Chia bai
		gamePlay.Deal();
	
		// Cho nguoi co quan 3 bich danh dau tien
		for(int i=0; i<numOfPlayer; i++) {
			
			HandCards player = gamePlay.getPlayer(i);
			
			for(int j=0; j<HandCards.NUM_OF_CARDS; j++) {
				Card card = player.getCard(j);
				if(card.getValue() == 3 && card.getType() == Card.TYPE_SPADES) {
					// nguoi co quyen danh bai
					gamePlay.setActivatingPlayer(i);
					FirstPlay();
				}
			}
		}
	}
	
	public void FirstPlay() {
		
		for(int i=0; i<numOfPlayer; i++) {
			HandCards player = gamePlay.getPlayer(i);
			if(player.getStatus() != HandCards.STATUS_OFF)
				player.setActivated(true);
		}
		
		int position = gamePlay.getActivatingPlayer();
		currentPlayer = gamePlay.getPlayer(position);
		currentPlayer.ShowCards();
		System.out.print("Please play cards: ");
		String line = scanner.nextLine();
		String[] items = line.split(" ");
		int arrIndex[] = new int[items.length];
		for(int i=0; i<items.length; i++) {
			arrIndex[i] = Integer.parseInt(items[i]);
		}
		Card[] arrSelCards = new Card[items.length];
		for(int i=0; i<items.length; i++) {
			
			int index = arrIndex[i];
			arrSelCards[i] = currentPlayer.getCard(index);
		}
		currentPlayer.setArrSelCards(arrSelCards);
		currentType = Rules.CheckType(arrSelCards);
		// Kiem tra nguoi choi danh bai co hop le khong
		if(currentType == Rules.THROWING_UNKNOWN || Rules.IsValid(currentType, arrSelCards) == false) {
			System.out.println("Your gambling is invalid!");
			FirstPlay();
		}
		else {
			gamePlay.Play(position);
			currentCard = arrSelCards;
			PlayGame();
		}
		
	}
	
	
	public void PlayGame() {
		
		int nextPlayer = gamePlay.NextPlayer();
		while( nextPlayer != -1) {
			
			gamePlay.setActivatingPlayer(nextPlayer);
			ThrowingCards();
			nextPlayer = gamePlay.NextPlayer();
			System.out.println("Nextplayer: " + (nextPlayer + 1));
			System.out.println("Just: " + (gamePlay.getJustPlayer() + 1));
		}
		
		if(gamePlay.getStatus() != GamePlay.STATUS_OFF) {
			System.out.println("Fisrt play");
			int justplayer = gamePlay.getJustPlayer();
			gamePlay.setActivatingPlayer(justplayer);
			FirstPlay();
		}
		else {
			gamEvent.OnDestroyGame();
		}
	}
	
	public void ThrowingCards() {
		
		int indexPlayer = gamePlay.getActivatingPlayer();
		currentPlayer = gamePlay.getPlayer(indexPlayer);
		
		do {
			int select = 0;
			do {
				
				System.out.println("Player " + (indexPlayer + 1) + " you want to gamble or skip ?");
				System.out.println("1. Play");
				System.out.println("2. Skip");
				System.out.print("Enter the selection: ");
				select = Integer.parseInt(scanner.nextLine());
			}while(select < 1 || select > 2);
			
			if(select == 2) {
				gamePlay.Ignore(indexPlayer);
				return;
			}
			
			currentPlayer.ShowCards();
			System.out.print("Please play cards: ");
			String line = scanner.nextLine();
			String[] items = line.split(" ");
			int arrIndex[] = new int[items.length];
			for(int i=0; i<items.length; i++) {
				arrIndex[i] = Integer.parseInt(items[i]);
			}
			Card[] arrSelCards = new Card[items.length];
			for(int i=0; i<items.length; i++) {
				
				int index = arrIndex[i];
				arrSelCards[i] = currentPlayer.getCard(index);
			}
			currentPlayer.setArrSelCards(arrSelCards);
			
			// Kiem tra neu bai danh phai lon hon moi hop le
			boolean isWin = Rules.IsWin(currentType, arrSelCards, currentCard);
			boolean isValid = Rules.IsValid(currentType, arrSelCards);
			if(isValid && isWin) {
				gamePlay.Play(indexPlayer);
				// Kiem tra neu da danh het bai thi set rank cho nguoi choi
				if(currentPlayer.isWin()) {
					int rank = gamePlay.getRank();
					currentPlayer.setStatus(HandCards.STATUS_OFF);
					currentPlayer.setRank(rank);
					gamePlay.setRank(rank + 1);
				}
				return;
			}
			else {
				System.out.println("Invalid gambling");
			}
		}while(true);
		
	}
	
}
