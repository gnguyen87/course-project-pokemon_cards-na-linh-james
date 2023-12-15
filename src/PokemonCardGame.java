public class PokemonCardGame {
    private GameManager gm  = new GameManager();

    public static void main(String[] args) {
        PokemonCardGame game = new PokemonCardGame();
        game.run();
    }

    public void run() {
        StartMenu.displayStartMenu(gm);
    }


}