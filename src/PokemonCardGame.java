public class PokemonCardGame {
    private GameManager gameManager;

    public static void main(String[] args) {
        PokemonCardGame game = new PokemonCardGame();
        game.run();
    }

    /**
     * Create a Game Manager object to run the game and call method to display the game menu first.
     */
    public void run() {
        gameManager = new GameManager();
        gameManager.displayStartMenu();
    }

    // This method will be called when a difficulty is chosen
    public void startGame(int numPairs) {
        gameManager.startGame(numPairs);
    }
}