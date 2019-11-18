package controll;

import models.Card;
import models.HandCards;

public class Rules {
	
	public static final int THROWING_SINGLE = 1;
	public static final int THROWING_DOUBLE = 2;
	public static final int THROWING_TRIPLE = 3;
	public static final int THROWING_QUADRA = 4;
	public static final int THROWING_ORDER = 5;
	public static final int THROWING_UNKNOWN = 6;

	public static boolean IsSingleCardWin(Card c1, Card c2) {
		
		if(c1.getValue() > c2.getValue() ) {
			return true;
		}
		else if (c1.getValue() < c2.getValue() ) {
			return false;
		} 
		else {
			return (c1.getType() > c2.getType());
		}
	}
	
	public static boolean IsDoubleCardWin(Card[] arr1, Card[] arr2) {
		
		if(arr1[0].getValue() > arr2[0].getValue()) {
			return true;
		}
		else if(arr1[0].getValue() < arr2[0].getValue()) {
			return false;
		}
		else {
			int max1 = Math.max(arr1[0].getType(), arr1[1].getType());
			int max2 = Math.max(arr2[0].getType(), arr2[1].getType());
			return (max1 > max2);
		}
	}
	
	public static boolean IsTripleCardWin(Card[] arr1, Card[] arr2) {
		
		return (arr1[0].getValue() > arr2[0].getValue());
	}
	
	public static boolean IsQuadraCardWin(Card[] arr1, Card[] arr2) {
		
		return (arr1[0].getValue() > arr2[0].getValue());
	}
	
	public static boolean IsOrderCardWin(Card[] arr1, Card[] arr2) {
		
		SortCards(arr1);
		SortCards(arr2);
		// Kiem tra ve cuoi cung de phan biet
		return IsSingleCardWin(arr1[arr1.length - 1], arr2[arr2.length - 1]);
	}
	
	public static void SortCards(Card[] arr) {
		
		for(int i=0; i<arr.length; i++) {
			for(int j=i+1; j<arr.length; j++) {
				if(arr[i].getValue() > arr[j].getValue()) {
					Card temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	
	
	public static boolean IsDouble(Card[] arr) {
		if(arr[0].getValue() == arr[1].getValue()) {
			return true;
		}
		return false;
	}
	
	public static boolean IsTriple(Card[] arr) {
		if(arr[0].getValue() == arr[1].getValue() && arr[0].getValue() == arr[2].getValue()) {
			return true;
		}
		return false;
	}
	
	public static boolean IsQuadra(Card[] arr) {
		if(arr[0].getValue() == arr[1].getValue() && arr[0].getValue() == arr[2].getValue() && arr[0].getValue() == arr[3].getValue()) {
			return true;
		}
		return false;
	}
	
	public static boolean IsOrder(Card[] arr) {
		
		// sanh phai co it nhat 3 ve
		if(arr.length < 3)
			return false;
		
		// Sap xep card tang dan
		SortCards(arr);
		
		// Quan bai cuoi cung khong duoc quan 2
		if(arr[arr.length - 1].getValue() == Card.VALUE_2) {
			return false;
		}
		
		for(int i=0; i<arr.length - 1; i++) {
			if(arr[i].getValue() != arr[i+1].getValue() - 1) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean IsWin(int type, Card[] arr1, Card[] arr2) {
		
		// return true if player_1 win player_2
		
		switch (type) {
		case THROWING_SINGLE:
			return IsSingleCardWin(arr1[0], arr2[0]);
			
		case THROWING_DOUBLE:
			return IsDoubleCardWin(arr1, arr2);
			
		case THROWING_TRIPLE:
			return IsTripleCardWin(arr1, arr2);
			
		case THROWING_QUADRA:
			return IsQuadraCardWin(arr1, arr2);
			
		case THROWING_ORDER:
			return IsOrderCardWin(arr1, arr2);

		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		
	}
	
	public static int CheckType(Card[] arr) {
		
		int length = arr.length;
		switch (length) {
		case 1:
			return THROWING_SINGLE;
			
		case 2:
			if(IsDouble(arr))
				return THROWING_DOUBLE; 
			else
				return THROWING_UNKNOWN;
			
		case 3:
			if(IsTriple(arr)) 
				return THROWING_TRIPLE;
			else if(IsOrder(arr))
				return THROWING_ORDER;
			else
				return THROWING_UNKNOWN;
			
		case 4:
			if(IsQuadra(arr)) 
				return THROWING_TRIPLE;
			else if(IsOrder(arr))
				return THROWING_ORDER;
			else
				return THROWING_UNKNOWN;
			
		default:
			if(IsOrder(arr)) 
				return THROWING_ORDER;
			else 
				return THROWING_UNKNOWN;
		}
		
	}
	
	public static boolean IsValid(int type, Card[] arr) {
		
		switch(type){
		
		case Rules.THROWING_SINGLE:
			if(arr.length == 1)
				return true;
			break;
		
		case Rules.THROWING_DOUBLE:
			if(arr.length == 2 && IsDouble(arr))
				return true;
			break;
			
		case Rules.THROWING_TRIPLE:
			if(arr.length == 3 && IsTriple(arr))
				return true;
			break;
			
		case Rules.THROWING_QUADRA:
			if(arr.length ==  4 && IsQuadra(arr))
				return true;
			break;
			
		case Rules.THROWING_ORDER:
			if(arr.length >= 3 && IsOrder(arr))
				return true;
			break;
	
		}
		
		return false;
	}
}
