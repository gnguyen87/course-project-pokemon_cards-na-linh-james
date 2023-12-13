import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;

public class PokemonCardGame {
    private GameManager gameManager;

    public static void main(String[] args) {
        PokemonCardGame game = new PokemonCardGame();
        game.run();
    }

    public void run() {
        gameManager = new GameManager();
        gameManager.displayStartMenu();
    }

    // This method will be called when a difficulty is chosen
    public void startGame(int numPairs) {
        gameManager.startGame(numPairs);
    }
}