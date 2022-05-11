package sandbox.elements;

import java.awt.*;

public class Air extends Element {
    public static final int elementNumber = 0;
    public static final String name = "Air";
    public static final Color color = Color.BLACK;

    @Override
    void step(int row, int col) {

    }

    @Override
    public String getName() {
        return Air.name;
    }


}
