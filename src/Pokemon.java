import edu.macalester.graphics.Image;

public class Pokemon extends Image implements Comparable<Pokemon> {
    private String name;

    public Pokemon(String name) {
        super(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Pokemon p2) {
        return name.compareTo(p2.getName());
    }
}
