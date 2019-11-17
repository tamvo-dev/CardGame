package manager;

public interface GameEvent {

	void OnStartGame();
	void OnRestartGame();
	void OnEndGame();
	void OnDestroyGame();
}
