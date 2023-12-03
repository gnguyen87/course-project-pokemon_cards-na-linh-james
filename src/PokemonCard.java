import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class PokemonCard {
    private final double CARD_WIDTH = 120;
    private final double CARD_HEIGHT = 180;

    private GraphicsGroup group;
    private double centerX;
    private double centerY;
    private Rectangle cardShape;
    private Image pokemon;
    private boolean flipped = false;
    
    public PokemonCard (double centerX, double centerY, boolean flipped) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.flipped = flipped;

        group = new GraphicsGroup();
        
        this.cardShape = new Rectangle(this.centerX, this.centerY, CARD_WIDTH, CARD_HEIGHT);
        group.add(cardShape);
    }

    public void makePokemonCard(String name){
        pokemon = new Image(this.centerX + CARD_WIDTH/2, this.centerY + CARD_HEIGHT/2);
        pokemon.setMaxWidth(CARD_WIDTH*0.85);
        pokemon.setMaxHeight(CARD_HEIGHT*0.85);
        pokemon.setImagePath("pokemon_images/" + name + ".png");
        updatePokemonPosition();
    }

    public boolean isFlipped(Point clickPoint){
        GraphicsObject object = group.getCanvas().getElementAt(clickPoint);
        if (object == pokemon) {
            return true;
        }
        return false;
    }

    public void cardClicked(Point userPoint) {
        boolean isClicked = group.testHitInLocalCoordinates(userPoint.getX(), userPoint.getY());
        if (isClicked == true) {
            if (isFlipped(userPoint) == true) {
                group.remove(pokemon);
            } else {
                group.add(pokemon);
            }
        }
    }

    private void updatePokemonPosition() {
        pokemon.setCenter(centerX + CARD_WIDTH/2, centerY + CARD_HEIGHT/2);
    }

    public void addToCanvas(CanvasWindow canvas) {
        cardShape.setFillColor(Color.PINK);
        canvas.add(group);
    }

    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(group);
    }
 
}
