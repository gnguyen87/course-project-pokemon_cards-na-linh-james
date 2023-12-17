public class PokemonCardGame {
    private GameManager gm  = new GameManager();

    public static void main(String[] args) {
        PokemonCardGame game = new PokemonCardGame();
        String filepath = "res/music/poke_compilation.wav";
        // PokeMusic.PlayMusic(filepath);
        game.run();
       
    }

    public boolean run() {
        StartMenu.displayStartMenu(gm);
        return true;
    }


}