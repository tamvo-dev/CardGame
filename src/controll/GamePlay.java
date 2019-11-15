package controll;

import models.HandCards;

public class GamePlay {

	private int status;
	private int numOfPlayed;
	private HandCards[] arrPlayeds;
	private int justPlayed;
	private int activatingPlayed;
	private int rank;
	private Rules rules;
	static GamePlay instance = null;
	
	// Khong cho phep tao moi GamePlay
	private GamePlay() {
		
	}
	
	// Tao mot the hien cua GamePlay
	public static GamePlay getInstance() {
		if(instance == null) {
			instance = new GamePlay();
		}
		
		return instance;
	}
	
	public void Deal() {
		
	}
	
	public int NextPlayer() {
		return 0;
	}
	
	public void RemovePlayer() {
		
	}
	
	public boolean TestValid() {
		
		return false;
	}
	
	public void ActivatePlayed() {
		
	}
	
	public void Ignore() {
		
	}
	
	public void Play() {
		
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getNumOfPlayed() {
		return numOfPlayed;
	}

	public void setNumOfPlayed(int numOfPlayed) {
		this.numOfPlayed = numOfPlayed;
	}

	public HandCards[] getArrPlayeds() {
		return arrPlayeds;
	}

	public void setArrPlayeds(HandCards[] arrPlayeds) {
		this.arrPlayeds = arrPlayeds;
	}

	public int getJustPlayed() {
		return justPlayed;
	}

	public void setJustPlayed(int justPlayed) {
		this.justPlayed = justPlayed;
	}

	public int getActivatingPlayed() {
		return activatingPlayed;
	}

	public void setActivatingPlayed(int activatingPlayed) {
		this.activatingPlayed = activatingPlayed;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Rules getRules() {
		return rules;
	}

	public void setRules(Rules rules) {
		this.rules = rules;
	}
	
	
}
