package manager;

import java.util.Scanner;

import controll.GamePlay;

public class Main implements GameEvent {
	
	public static void main(String[] args) {
		
		new Main();
	}
	
	private GamePlay gamePlay;
	private GameManager gameManager;
	private Scanner scanner;
	
	public Main() {
		scanner = new Scanner(System.in);
		this.OnStartGame();
	}

	@Override
	public void OnStartGame() {
		
		System.out.print("Enter the number of players: ");
		int num = Integer.parseInt(scanner.nextLine());
		gamePlay = new GamePlay(num);
		gameManager = new GameManager(gamePlay, scanner,this);
		gameManager.StartGame();
	}

	@Override
	public void OnEndGame() {
		
	}
	
	@Override
	public void OnRestartGame() {
		
	}

	@Override
	public void OnDestroyGame() {
		
	}
}
