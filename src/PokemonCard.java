import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class PokemonCard {
    public final double CARD_WIDTH = 120;
    public final double CARD_HEIGHT = 150;
    private double centerX;
    private double centerY;
    private Rectangle cardShape;
    private Pokemon pokemon;
    private GraphicsGroup group;
    
    public PokemonCard (double centerX, double centerY, boolean flipped, Pokemon pokemon) {
        this.centerX = centerX;
        this.centerY = centerY;

        group = new GraphicsGroup();
        this.cardShape = new Rectangle(this.centerX, this.centerY, CARD_WIDTH, CARD_HEIGHT);
        group.add(cardShape);
        
        this.pokemon = pokemon;
        pokemon.setCenter(centerX + CARD_WIDTH/2, centerY + CARD_HEIGHT/2);
        pokemon.setMaxWidth(CARD_WIDTH);
        pokemon.setMaxHeight(CARD_HEIGHT);
        updatePokemonPosition();
    }

    /**
     * @param clickPoint user's input by clicking on the canvas
     * @return true if the user has clicked on the position where a Pokemon image is 
     * supposed to be
     */
    public boolean isFlipped(Point clickPoint){
        GraphicsObject object = group.getCanvas().getElementAt(clickPoint);
        if (object == pokemon) {
            return true;
        }
        return false;
    }

    /**
     * Makes the pokemon image appears after the user clicks on a card.
     * If the users click on a pair of same pokemon cards, remove the pair from canvas. If the pair is 
     * not matched, update the number of attempts user has left and flip back cards.
     * @param userPoint user's input by clicking on the pokemon card
     * @param gm a Game Manager object
     */
    public void flipCard(Point userPoint, GameManager gm) {
        boolean isClicked = group.testHitInLocalCoordinates(userPoint.getX(), userPoint.getY());
        if (isClicked) {
            PokemonCard firstFlipped = gm.getFirstFlipped();
            group.add(pokemon);
            gm.updateCanvas(); 
    
            if (firstFlipped == null) {
                gm.setFirstFlipped(this);
            } else {
                if (firstFlipped.equals(this)) {
                    removePokemon();
                } else {
                    if (pokemon.compareTo(firstFlipped.getPokemon()) == 0) {
                        gm.ringPokeBall();
                        gm.pauseCanvas();
                        gm.removeCard(firstFlipped);
                        gm.removeCard(this);
                        gm.updateCanvas(); 
                    } else {
                        gm.pauseCanvas();
                        firstFlipped.removePokemon();
                        removePokemon();
                        gm.updateCanvas(); 
                        gm.AttemptsCount(); // Increment attempts count only for mismatches
                    }
                }
                gm.setFirstFlipped(null);
            }
        }
    }
    
    /**
     * Positions the pokemon image to the center of the pink card frame.
     */
    private void updatePokemonPosition() {
        pokemon.setCenter(centerX + CARD_WIDTH/2, centerY + CARD_HEIGHT/2);
    }

    /**
     * Getter method
     * @return an individual pokemon card that has been generated.
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * Getter method
     * @return Graphics Group of all pokemon cards
     */
    public GraphicsGroup getGraphicsGroup() {
        return group;
    }

    /**
     * Add the frame of the pokemon card (pink filled rectangle) to canvas and to the
     * pokemon group
     * @param canvas
     */
    public void addToCanvas(CanvasWindow canvas) {
        cardShape.setFillColor(Color.PINK);
        cardShape.setStrokeColor(Color.PINK);
        canvas.add(group);
    }

    /**
     * Removes a pokemon card from the group of pokemon cards.
     */
    public void removePokemon() {
        group.remove(pokemon);
    }
}
