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
        pokemon.setMaxWidth(CARD_WIDTH*0.9);
        pokemon.setMaxHeight(CARD_HEIGHT*0.9);
        updatePokemonPosition();
    }

    public boolean isFlipped(Point clickPoint){
        GraphicsObject object = group.getCanvas().getElementAt(clickPoint);
        if (object == pokemon) {
            return true;
        }
        return false;
    }

    public void flipCard(Point userPoint, GameManager gm) {
        boolean isClicked = group.testHitInLocalCoordinates(userPoint.getX(), userPoint.getY());
        if (isClicked) {
            PokemonCard firstFlipped = gm.getFirstFlipped();
            group.add(pokemon);
            gm.updateCanvas(); 
    
            if (firstFlipped == null) {
                gm.setFirstFlipped(this);
            } else {
                gm.pauseCanvas();
                if (firstFlipped.equals(this)) {
                    removePokemon();
                } else {
                    if (pokemon.compareTo(firstFlipped.getPokemon()) == 0) {
                        gm.removeCard(firstFlipped);
                        gm.removeCard(this);
                    } else {
                        firstFlipped.removePokemon();
                        removePokemon();
                        gm.AttemptsCount(); // Increment attempts count only for mismatches
                    }
                }
                gm.setFirstFlipped(null);
            }
        }
    }
    
    private void updatePokemonPosition() {
        pokemon.setCenter(centerX + CARD_WIDTH/2, centerY + CARD_HEIGHT/2);
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public GraphicsGroup getGraphicsGroup() {
        return group;
    }

    public void addToCanvas(CanvasWindow canvas) {
        cardShape.setFillColor(Color.PINK);
        cardShape.setStrokeColor(Color.PINK);
        canvas.add(group);
    }

    public void removePokemon() {
        group.remove(pokemon);
    }
}
