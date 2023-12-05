import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
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

    private boolean flipped = false;
    private CanvasWindow canvas;
    
    public PokemonCard (double centerX, double centerY, boolean flipped, Pokemon pokemon) {
        this.centerX = centerX;
        this.centerY = centerY;

        group = new GraphicsGroup();
        this.cardShape = new Rectangle(this.centerX, this.centerY, CARD_WIDTH, CARD_HEIGHT);
        group.add(cardShape);
        
        this.pokemon = pokemon;
        
        
        pokemon.setCenter(centerX + CARD_WIDTH/2, centerY + CARD_HEIGHT/2);
        pokemon.setMaxWidth(CARD_WIDTH*0.85);
        pokemon.setMaxHeight(CARD_HEIGHT*0.85);
        updatePokemonPosition();

        group.add(pokemon);

    
    }

    public boolean isFlipped(Point clickPoint){
        GraphicsObject object = group.getCanvas().getElementAt(clickPoint);
        if (object == pokemon) {
            return true;
        }
        return false;
    }

    public void flipCard(Point userPoint) {
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

    public void setCardStatus(boolean newStatus) {
        this.flipped = newStatus;
    }

    public boolean isFlipped() {
        return this.flipped;
    }


    public Image getPokemon() {
        return pokemon;
    }

    public GraphicsGroup getGraphicsGroup() {
        return group;
    }

    public void addToCanvas(CanvasWindow canvas) {
        cardShape.setFillColor(Color.PINK);
        canvas.add(group);
    }

    




    
 
}
