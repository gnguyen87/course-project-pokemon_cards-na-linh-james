import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;

public class PokemonCardsGame {
    private CanvasWindow canvas;
    private PokemonCardManager cardManager;


    public static void main(String[] args) {
        PokemonCardsGame game = new PokemonCardsGame();
        game.initializeGame();
        
    }

    public PokemonCardsGame() {
        canvas = new CanvasWindow("Pokemon Card Puzzle", 1200, 1000);
        cardManager = new PokemonCardManager();
        
    }

    public void initializeGame() {
        cardManager.cardGenerator(canvas);
        setCardClickHandler(canvas);
       
    }

    public void setCardClickHandler(CanvasWindow canvas) {
        List<PokemonCard> cards = cardManager.getCards();
        for (PokemonCard card: cards){
            canvas.onClick(event -> card.flipCard(event.getPosition()));
        }
    }
 
  




}
