package manager;

import controll.GamePlay;
import controll.Rules;
import models.Card;

public class Main {
	
	private static GamePlay gamePlay;
	
	public static void main(String[] args) {
		
		Card[] arr1 = new Card[3];
		arr1[0] = new Card();
		arr1[0].setValue(7);
		arr1[0].setType(Card.TYPE_CO);
		
		arr1[1] = new Card();
		arr1[1].setValue(6);
		arr1[1].setType(Card.TYPE_BICH);
		
		arr1[2] = new Card();
		arr1[2].setValue(5);
		arr1[2].setType(Card.TYPE_BICH);
		
		Card[] arr2 = new Card[3];
		arr2[0] = new Card();
		arr2[0].setValue(6);
		arr2[0].setType(Card.TYPE_RO);
		
		arr2[1] = new Card();
		arr2[1].setValue(5);
		arr2[1].setType(Card.TYPE_CHUON);
		
		arr2[2] = new Card();
		arr2[2].setValue(7);
		arr2[2].setType(Card.TYPE_CHUON);
		
		System.out.println(Rules.IsWin(Rules.THROWING_ORDER, arr1, arr2));
		
	}
}
