public class PokemonCardGame {
    private GameManager gm  = new GameManager();

    public static void main(String[] args) {
        PokemonCardGame game = new PokemonCardGame();
        String filepath = "res/music/guide.wav";
        PokeMusic.PlayMusic(filepath);
        // JOptionPane.showMessageDialog(null, "Press okay to stop playing");
        game.run();
    }

    public void run() {
        StartMenu.displayStartMenu(gm);
    }


}