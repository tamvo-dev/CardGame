package manager;

import java.util.Scanner;

import controll.GamePlay;
import controll.Rules;
import models.Card;
import models.HandCards;

public class GameMain {

	private static GamePlay game;
	private static Scanner scanner;
	private static int numOfPlayer;

	private static HandCards currentPlayer = null;
	private static int currentType;
	private static Card[] currentCards;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		do {
			System.out.print("Enter the number of players: ");
			numOfPlayer = Integer.parseInt(scanner.nextLine());
		} while (numOfPlayer < 2 || numOfPlayer > 4);
		
		game = new GamePlay(numOfPlayer);
		StartGame();
	}

	public static void StartGame() {

		// Chia bai
		game.Deal();

		// Cho nguoi co quan 3 bich danh dau tien neu khong co thi mac dinh nguoi dau
		// tien danh
		for (int i = 0; i < numOfPlayer; i++) {

			HandCards player = game.getArrPlayers()[i];

			for (int j = 0; j < HandCards.NUM_OF_CARDS; j++) {
				Card card = player.getArrCards()[j];
				if (card.getValue() == 3 && card.getType() == Card.TYPE_SPADES) {

					// nguoi co quyen danh bai
					game.setActivatingPlayer(i);
					break;
				}
			}
		}

		StartNewRound();
	}

	public static void StartNewRound() {

		for (int i = 0; i < numOfPlayer; i++) {
			HandCards player = game.getArrPlayers()[i];
			if (player.getStatus() != HandCards.STATUS_OFF)
				player.setActivated(true);
		}

		int position = game.getActivatingPlayer();
		currentPlayer = game.getArrPlayers()[position];

		System.out.print("\nCards of player " + (position + 1) + " : ");
		currentPlayer.ShowCards();

		boolean isValid = false;
		do {

			boolean isThowing = ThrowingCards(currentPlayer);
			
			if(isThowing == true) {
				Card[] arrSelCards = currentPlayer.getArrSelCards();
				currentType = Rules.CheckType(arrSelCards);

				// Kiem tra nguoi choi danh bai co hop le khong
				if (currentType == Rules.THROWING_UNKNOWN || Rules.IsValid(currentType, arrSelCards) == false) {
					System.out.println("Your gambling is invalid!");
				} else {
					isValid = true;
					game.Play(position);
					currentCards = arrSelCards;
				}
			}
			
		} while (isValid == false);

		if (currentPlayer.IsPlayerWin()) {
			currentPlayer.setStatus(HandCards.STATUS_OFF);
			EndGame();
		} else {
			PlayGame();
		}
	}

	public static void PlayGame() {

		int nextPlayer = game.NextPlayer();
		while (nextPlayer != -1 && game.getStatus() == GamePlay.STATUS_ON) {

			game.setActivatingPlayer(nextPlayer);
			NextRound();
			nextPlayer = game.NextPlayer();
		}

		if (game.getStatus() == GamePlay.STATUS_ON) {

			int justplayer = game.getJustPlayer();
			game.setActivatingPlayer(justplayer);
			StartNewRound();
		} else {
			// ket thuc game
			EndGame();
		}
	}

	public static void NextRound() {

		int indexPlayer = game.getActivatingPlayer();
		currentPlayer = game.getArrPlayers()[indexPlayer];

		ShowCurrentCard();
		System.out.print("Cards of player " + (indexPlayer + 1) + " : ");
		currentPlayer.ShowCards();

		boolean isWin = false;
		boolean isValid = false;

		do {
			System.out.println("Player " + (indexPlayer + 1) + " you want to gamble or skip ?");
			System.out.println("1. Play");
			System.out.println("2. Skip");

			int select = 0;
			do {
				try {
					System.out.print("Enter the selection: ");
					select = Integer.parseInt(scanner.nextLine());
				} catch (Exception ex) {
				}

			} while (select < 1 || select > 2);

			if (select == 2) {
				game.Ignore(indexPlayer);
				return;
			}

			// Cho nguoi choi danh bai
			boolean isThowing =  ThrowingCards(currentPlayer);
			
			if(isThowing == true) {
				Card[] arrSelCards = currentPlayer.getArrSelCards();
				// Kiem tra neu bai danh phai lon hon moi hop le
				isWin = Rules.IsWin(currentType, arrSelCards, currentCards);
				isValid = Rules.IsValid(currentType, arrSelCards);
				if (isWin == false) {
					System.out.println("Ban phai danh bai lon hon nguoi choi hien tai");
				} else if(isValid == false) {
					System.out.println("Type khong hop le");
				}
			}
			
		} while(isWin == false || isValid == false);
			
		// Cho nguoi choi danh bai
		game.Play(indexPlayer);
		// Cap nhat lai currentCards
		currentCards = currentPlayer.getArrSelCards();
		// Kiem tra neu da danh het bai thi ket thuc game
		if (currentPlayer.IsPlayerWin()) {
			currentPlayer.setStatus(HandCards.STATUS_OFF);
			game.setStatus(GamePlay.STATUS_OFF);
		}

	}
	
	public static boolean ThrowingCards(HandCards player) {

		System.out.print("Please play cards: ");
		String line = scanner.nextLine();
		
		if(line.isBlank()) {
			System.out.println("Khong dc de trong");
			return false;
		}
		
		String[] items = line.split(" ");
		int arrIndex[] = new int[items.length];
		for (int i = 0; i < items.length; i++) {
			arrIndex[i] = Integer.parseInt(items[i]);
			// Kiem tra index co hop le khong
			// Index hop le nam trong 0 - 12
			// Index hop le se la nhung quan bai chua danh
			int index = arrIndex[i];
			
			if( index < 0 || index > 12) {
				System.out.println("index khong hop le");
				return false;
				
			} else if(player.getArrCards()[index].isPlay() == true) {
				// Quan bai da duoc danh roi thi set isThowingValid = false
				System.out.println("lua chon khong hop le");
				return false;
			}
		}
		
		Card[] arrSelCards = new Card[items.length];
		for (int i = 0; i < arrIndex.length; i++) {
			int index = arrIndex[i];
			arrSelCards[i] = player.getArrCards()[index];
		}
		player.setArrSelCards(arrSelCards);
		return true;	
	}

	public static void ShowCurrentCard() {

		String result = "Current cards : [ ";
		for (int i = 0; i < currentCards.length; i++) {
			result += currentCards[i].toString() + ", ";
		}
		result += "]";
		result = result.replace(", ]", " ]");
		System.out.println();
		System.out.println(result);
	}

	public static void EndGame() {

		for (int i = 0; i < numOfPlayer; i++) {
			if (game.getArrPlayers()[i].getStatus() == HandCards.STATUS_OFF) {
				System.out.println("Player " + (i + 1) + " winner!");
				break;
			}
		}
		System.out.println("End game!");
	}

}
