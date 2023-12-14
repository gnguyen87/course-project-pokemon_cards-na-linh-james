public class PokemonCardGame {

    public static void main(String[] args) {
        PokemonCardGame game = new PokemonCardGame();
        game.run();
    }
    public void run() {
        GameManager gameManager = new GameManager();
        StartMenu.displayStartMenu(gameManager);
    }
}