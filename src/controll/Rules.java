package controll;

import models.Card;

//check the rule of the game
public class Rules {

	public static final int THROWING_SINGLE = 1;
	public static final int THROWING_DOUBLE = 2;
	public static final int THROWING_TRIPLE = 3;
	public static final int THROWING_QUADRA = 4;
	public static final int THROWING_ORDER = 5;
	public static final int THROWING_UNKNOWN = 6;

	// check the value of 2 cards, who is will bigger
	public static boolean IsSingleCardWin(Card c1, Card c2) {

		if (c1.getValue() > c2.getValue()) {
			return true;
		} else if (c1.getValue() < c2.getValue()) {
			return false;
		} else {
			return (c1.getType() > c2.getType());
		}
	}

	// check the value of double cards, who is will bigger
	public static boolean IsDoubleCardWin(Card[] arr1, Card[] arr2) {

		if (arr1[0].getValue() > arr2[0].getValue()) {
			return true;
		} else if (arr1[0].getValue() < arr2[0].getValue()) {
			return false;
		} else {
			int max1 = Math.max(arr1[0].getType(), arr1[1].getType());
			int max2 = Math.max(arr2[0].getType(), arr2[1].getType());
			return (max1 > max2);
		}
	}

	// check the value of triple cards, who is will bigger
	public static boolean IsTripleCardWin(Card[] arr1, Card[] arr2) {

		return (arr1[0].getValue() > arr2[0].getValue());
	}

	// check the value of quadrad cards, who is will bigger
	public static boolean IsQuadraCardWin(Card[] arr1, Card[] arr2) {

		return (arr1[0].getValue() > arr2[0].getValue());
	}

	// check the straight cards, who is will bigger ex: 3,4,5,6 < 6,7,8,9
	public static boolean IsOrderCardWin(Card[] arr1, Card[] arr2) {

		SortCards(arr1);
		SortCards(arr2);
		// Kiem tra ve cuoi cung de phan biet
		return IsSingleCardWin(arr1[arr1.length - 1], arr2[arr2.length - 1]);
	}

	// just sort the value of cards from small to big ( No sort suits)
	public static void SortCards(Card[] arr) {
		// selection sort
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i].getValue() > arr[j].getValue()) {
					Card temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	// check two cards which is double or not. ex: 3H and 3P
	public static boolean IsDouble(Card[] arr) {
		if (arr[0].getValue() == arr[1].getValue()) {
			return true;
		}
		return false;
	}

	// check two cards which is triple or not. ex: 3H and 3P and 3H
	public static boolean IsTriple(Card[] arr) {
		if (arr[0].getValue() == arr[1].getValue() && arr[0].getValue() == arr[2].getValue()) {
			return true;
		}
		return false;
	}

	// check two cards which is four or not. ex: 3H and 3P and 3H and 3D
	public static boolean IsQuadra(Card[] arr) {
		if (arr[0].getValue() == arr[1].getValue() && arr[0].getValue() == arr[2].getValue()
				&& arr[0].getValue() == arr[3].getValue()) {
			return true;
		}
		return false;
	}

	public static boolean IsOrder(Card[] arr) {

		// throw at least 3 cards
		if (arr.length < 3)
			return false;

		// sort in order from small to big
		SortCards(arr);

		// check the order of Cards. ex: 3 = 4 - 1. if no, return false
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i].getValue() != arr[i + 1].getValue() - 1) {
				return false;
			}
		}
		return true;
	}

	// function sum up all the case of players is playing
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

	// check selected cards of one player ex: 3P and 3H > return int / 3P and 4P >
	// return int
	public static int CheckType(Card[] arr) {

		int length = arr.length;
		switch (length) {
		case 1:
			return THROWING_SINGLE;

		case 2:
			if (IsDouble(arr))
				return THROWING_DOUBLE;
			else
				return THROWING_UNKNOWN;

		case 3:
			if (IsTriple(arr))
				return THROWING_TRIPLE;
			else if (IsOrder(arr))
				return THROWING_ORDER;
			else
				return THROWING_UNKNOWN;

		case 4:
			if (IsQuadra(arr))
				return THROWING_TRIPLE;
			else if (IsOrder(arr))
				return THROWING_ORDER;
			else
				return THROWING_UNKNOWN;

		default:
			if (IsOrder(arr))
				return THROWING_ORDER;
			else
				return THROWING_UNKNOWN;
		}

	}

	// check the length of cards and check the values of cards depend on single,
	// double,...
	public static boolean IsValid(int type, Card[] arr) {

		switch (type) {

		case Rules.THROWING_SINGLE:
			if (arr.length == 1)
				return true;
			break;

		case Rules.THROWING_DOUBLE:
			if (arr.length == 2 && IsDouble(arr))
				return true;
			break;

		case Rules.THROWING_TRIPLE:
			if (arr.length == 3 && IsTriple(arr))
				return true;
			break;

		case Rules.THROWING_QUADRA:
			if (arr.length == 4 && IsQuadra(arr))
				return true;
			break;

		// fix again
		case Rules.THROWING_ORDER:
			if (arr.length >= 3 && IsOrder(arr))
				return true;
			break;

		}

		return false;
	}
}
