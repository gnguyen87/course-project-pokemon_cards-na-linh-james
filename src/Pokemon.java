import edu.macalester.graphics.Image;

public class Pokemon extends Image implements Comparable<Pokemon> {
    private String name;

    public Pokemon(String name) {
        super(name);
        this.name = name;
    }

    /**
     * @return name of the Pokemon image
     */
    public String getName() {
        return name;
    }

    @Override
    /**
     * Compare if the name of this pokemon and the name of the input pokemon are the same.
     */
    public int compareTo(Pokemon p2) {
        return name.compareTo(p2.getName());
    }
}
