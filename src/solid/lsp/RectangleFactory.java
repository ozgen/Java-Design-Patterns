package solid.lsp;

//implement factory pattern to use LSP

public class RectangleFactory {

    public static Rectangle newRectangle(int width, int height) {
        return new Rectangle(width, height);
    }

    public static Rectangle newSquare(int side) {
        return new Rectangle(side, side);
    }


}
