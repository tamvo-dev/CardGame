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
	private int numOfPlayer;
	
	public GameManager(GamePlay gamePlay, Scanner scanner ,GameEvent gameEvent) {
		this.gamePlay = gamePlay;
		this.scanner = scanner;
		this.gamEvent = gameEvent;
		this.numOfPlayer = gamePlay.getArrPlayeds().length;
		this.currentType = Rules.THROWING_UNKNOWN;
	}
	
	
	public void StartGame() {

		// Chia bai
		gamePlay.Deal();
	
		// Cho nguoi co quan 3 chuon danh dau tien
		for(int i=0; i<numOfPlayer; i++) {
			HandCards[] arrPlayer = gamePlay.getArrPlayeds();
			HandCards player = arrPlayer[i];
			Card[] arrCard = player.getArrCards();
			
			for(int j=0; j<arrCard.length; j++) {
				if(arrCard[j].getValue() == 3 && arrCard[j].getType() == Card.TYPE_CLUBS) {
					// nguoi co quyen danh bai
					gamePlay.setActivatingPlayed(i);
					FirstPlay();
				}
			}
		}
	}
	
	public void FirstPlay() {
		
		int indexPlayer = gamePlay.getActivatingPlayed();
		currentPlayer = gamePlay.getArrPlayeds()[indexPlayer];
		currentPlayer.showCards();
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
			Card[] arrCards = currentPlayer.getArrCards();
			arrSelCards[i] = arrCards[index];
		}
		currentPlayer.setArrSelCards(arrSelCards);
		currentType = Rules.CheckType(arrSelCards);
		// Kiem tra nguoi choi danh bai co hop le khong
		if(currentType == Rules.THROWING_UNKNOWN || Rules.IsValid(currentType, arrSelCards) == false) {
			System.out.println("Ban danh bai khong hop le");
		}
		else {
			System.out.println("Hop le");
		}
		
	}
	
	
	public void PlayGame() {
		
		while(gamePlay.getStatus() == GamePlay.STATUS_ON) {
			
			int index = gamePlay.getActivatingPlayed();
			currentPlayer = gamePlay.getArrPlayeds()[index];
			currentPlayer.showCards();
			System.out.println("Please play cards: ");
			String line = scanner.nextLine();
			String[] items = line.split(" ");
			int arrIndex[] = new int[items.length];
			for(int i=0; i<items.length; i++) {
				arrIndex[i] = Integer.parseInt(items[i]);
			}
			
			// Kiem tra nguoi choi danh bai co hop le khong
			
			
			gamePlay.setStatus(GamePlay.STATUS_OFF);
			/*
			while(isPlay) {
			
				System.out.print("");
			}
			// Hoi nguoi choi co muon danh khong
			if(false) {
				// Khong danh
				gamePlay.Ignore();
			}else {
				// Danh bai
				// Kiem tra neu danh hop le thi cho danh
				
				//if(gamePlay.TestValid()) {
					/gamePlay.Play();
				}else {
					// Danh lai hoac bo luot
				}
				
			}
			
			// Kiem tra nguoi choi da ve dich chua
			// Neu ve dich thi xet rank co nguoi do va loai nguoi do khoi cuoc choi
			if(false) {
				gamePlay.RemovePlayer();
			}
			
			// Kiem tra xem cac nguoi choi da ve dich het chua ?
			// Neu het roi thi ket thuc game
			
			// Tim nguoi choi tiep theo
			currentPlayed = gamePlay.NextPlayer();
			
			// Neu het nguoi danh thi cho nguoi choi danh thi cho nguoi choi cuoi cung danh kieu bai khac 
			// va dong thoi kich hoat lai tat ca nguoi choi
			
			if(currentPlayed == -1) {
				for(int i=0; i<numOfPlayed; i++) {
					gamePlay.ActivatePlayed();
				}
			}
			*/
		}
	}
	
}
