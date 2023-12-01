import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;

public class GameManager {
    private CanvasWindow canvas;
    private double tries;
    private boolean gameStarted;
    private GraphicsText numberTriesLeft;

    public GameManager(CanvasWindow canvas) {
        this.canvas = canvas;

    }

    public double getTries() {
        return tries;
    }

    public void setTries(double newTries) {
        this.tries = newTries;
    }
}
