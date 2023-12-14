
public class PokemonCardGame {

    public static void main(String[] args) {
        PokemonCardGame game = new PokemonCardGame();
        game.run();
    }

    // This method will be called when a difficulty is chosen
    public void run() {
        GameManager gameManager = new GameManager();
        StartMenu.displayStartMenu(gameManager);
    }
}