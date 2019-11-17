package manager;

import java.util.Scanner;

import controll.GamePlay;
import models.HandCards;

public class GameManager {

	private GamePlay gamePlay;
	private GameEvent gamEvent;
	private Scanner scanner;
	
	private int numOfPlayer;
	
	public GameManager(GamePlay gamePlay, Scanner scanner ,GameEvent gameEvent) {
		this.gamePlay = gamePlay;
		this.scanner = scanner;
		this.gamEvent = gameEvent;
	}
	
	
	public void StartGame() {

		// Chia bai
		gamePlay.Deal();
	
		// Cho nguoi co quan 3 bich danh
		
		
		//currentPlayed = 0;
		//gamePlay.Play();
		//this.PlayGame();
	}
	
	/*
	public void PlayGame() {
		
		// Tim nguoi danh tiep theo
		//currentPlayed = gamePlay.NextPlayer();
		
		while(true) {
			
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
		}
	}
	*/
}
