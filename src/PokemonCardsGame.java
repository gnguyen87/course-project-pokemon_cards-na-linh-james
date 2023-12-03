import java.util.Random;

import edu.macalester.graphics.CanvasWindow;

public class PokemonCardsGame {
    private CanvasWindow canvas;

    public static void main(String[] args) {
        PokemonCardsGame game = new PokemonCardsGame();
        game.addPokemonCard(); 
        
    }

    public PokemonCardsGame() {
        canvas = new CanvasWindow("Pokemon Card Puzzle", 1200, 900);
       
    }

    public void addPokemonCard() {
        // You can adjust the coordinates based on where you want the card to appear
        double cardX = 100;
        double cardY = 100;

        PokemonCard pokemonCard = new PokemonCard(cardX, cardY, false);
        pokemonCard.makePokemonCard("starly"); 
        
        

        pokemonCard.addToCanvas(canvas);
        canvas.onClick(event -> pokemonCard.cardClicked(event.getPosition()));
        
    }
}
