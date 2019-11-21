package manager;

import java.util.Scanner;

import controll.GamePlay;
import controll.Rules;
import models.Card;
import models.HandCards;

public class GameMain {

	private static GamePlay gamePlay;
	private static Scanner scanner;
	private static int numOfPlayer;
	
	private HandCards currentPlayer = null;
	private int currentType;
	private Card[] currentCard;
	
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		System.out.print("Enter the number of players: ");
		numOfPlayer = Integer.parseInt(scanner.nextLine()); 
		gamePlay = new GamePlay(numOfPlayer);
		new GameMain();
	}
	
	public GameMain() {
		StartGame();
	}
	
	
	public void StartGame() {

		// Chia bai
		gamePlay.Deal();
	
		// Cho nguoi co quan 3 bich danh dau tien neu khong co thi mac dinh nguoi dau tien danh
		for(int i=0; i<numOfPlayer; i++) {
			
			HandCards player = gamePlay.getArrPlayers()[i];
			
			for(int j=0; j<HandCards.NUM_OF_CARDS; j++) {
				Card card = player.getArrCards()[j];
				if(card.getValue() == 3 && card.getType() == Card.TYPE_SPADES) {
					
					// nguoi co quyen danh bai
					gamePlay.setActivatingPlayer(i);
					break;
				}
			}
		}
		
		StartNewRound();
	}
	
	public void StartNewRound() {
		
		for(int i=0; i<numOfPlayer; i++) {
			HandCards player = gamePlay.getArrPlayers()[i];
			if(player.getStatus() != HandCards.STATUS_OFF)
				player.setActivated(true);
		}
		
		int position = gamePlay.getActivatingPlayer();
		currentPlayer = gamePlay.getArrPlayers()[position];
		
		System.out.println("Cards of player " + (position + 1));
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
			arrSelCards[i] = currentPlayer.getArrCards()[index];
		}
		currentPlayer.setArrSelCards(arrSelCards);
		currentType = Rules.CheckType(arrSelCards);
		
		// Kiem tra nguoi choi danh bai co hop le khong
		if(currentType == Rules.THROWING_UNKNOWN || Rules.IsValid(currentType, arrSelCards) == false) {
			System.out.println("Your gambling is invalid!");
			StartNewRound();
		}
		else {
			gamePlay.Play(position);
			currentCard = arrSelCards;
			// Kiem tra nguoi choi da thang chua
			// Neu thang thi ket thuc game
			// Neu khong thi cho cac nguoi choi khac danh
			if(currentPlayer.IsPlayerWin()) {
				EndGame();
			}else {
				PlayGame();
			}
		}
		
	}
	
	
	public void PlayGame() {
		
		int nextPlayer = gamePlay.NextPlayer();
		while( nextPlayer != -1 && gamePlay.getStatus() == GamePlay.STATUS_ON) {
			
			gamePlay.setActivatingPlayer(nextPlayer);
			NextRound();
			nextPlayer = gamePlay.NextPlayer();
		}
		
		if(gamePlay.getStatus() == GamePlay.STATUS_ON) {
		
			int justplayer = gamePlay.getJustPlayer();
			gamePlay.setActivatingPlayer(justplayer);
			StartNewRound();
		}
		else {
			// ket thuc game
			EndGame();
		}
	}
	
	public void NextRound() {
		
		int indexPlayer = gamePlay.getActivatingPlayer();
		currentPlayer = gamePlay.getArrPlayers()[indexPlayer];
		
		do {
			
			int select = 0;
			System.out.println("");
			System.out.println("Cards of player " + (indexPlayer + 1));
			currentPlayer.ShowCards();
			System.out.println("Player " + (indexPlayer + 1) + " you want to gamble or skip ?");
			System.out.println("1. Play");
			System.out.println("2. Skip");
			
			do {
				try {
					System.out.print("Enter the selection: ");
					select = Integer.parseInt(scanner.nextLine());
				}catch(Exception ex) {
					//System.out.println("");
				}
			}while(select < 1 || select > 2);
			
			if(select == 2) {
				gamePlay.Ignore(indexPlayer);
				return;
			}
			
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
				arrSelCards[i] = currentPlayer.getArrCards()[index];
			}
			currentPlayer.setArrSelCards(arrSelCards);
			
			// Kiem tra neu bai danh phai lon hon moi hop le
			boolean isWin = Rules.IsWin(currentType, arrSelCards, currentCard);
			boolean isValid = Rules.IsValid(currentType, arrSelCards);
			if(isValid && isWin) {
				gamePlay.Play(indexPlayer);
				// Kiem tra neu da danh het bai thi ket thuc game
				if(currentPlayer.IsPlayerWin()) {
					currentPlayer.setStatus(HandCards.STATUS_OFF);
					gamePlay.setStatus(GamePlay.STATUS_OFF);
				}
				return;
			}
			else {
				System.out.println("Invalid gambling");
			}
		}while(gamePlay.getStatus() == GamePlay.STATUS_ON);
		
	}
	
	
	public void EndGame() {
		for(int i=0; i<numOfPlayer; i++) {
			if(gamePlay.getArrPlayers()[i].getStatus() == HandCards.STATUS_OFF) {
				System.out.println("Player " + (i + 1) + " winner!");
				break;
			}
		}
		System.out.println("End game!");
	}
	
}
