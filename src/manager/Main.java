package manager;

import controll.GamePlay;
import controll.Rules;
import models.Card;

public class Main {
	
	private static GamePlay gamePlay;
	
	public static void main(String[] args) {
		
		gamePlay = new GamePlay(4);
		gamePlay.Deal();
		System.out.println(gamePlay.NextPlayer());
		gamePlay.setJustPlayed(gamePlay.NextPlayer());
		System.out.println(gamePlay.NextPlayer());
	}
}
