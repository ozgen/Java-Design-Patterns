package facade;

import java.util.ArrayList;
import java.util.List;

class Buffer {
    private char[] chars;
    private int lineWidth;

    public Buffer(int lineHeight, int lineWidth) {
        this.lineWidth = lineWidth;
        chars = new char[lineHeight * lineWidth];
    }

    public char charAt(int x, int y) {
        return chars[y * lineWidth + x];
    }
}

class ViewPort {
    private Buffer buffer;
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;

    public ViewPort(Buffer buffer, int width, int height, int offsetX, int offsetY) {
        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public char charAt(int x, int y) {
        return buffer.charAt(x + offsetX, y + offsetY);
    }
}

class Console {
    private List<ViewPort> viewPorts = new ArrayList<>();
    private int width, height;

    public Console(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addView(ViewPort viewPort) {
        this.viewPorts.add(viewPort);
    }

    /**
     * façade pattern method
     * @param width
     * @param height
     * @return
     */
    public static Console newConsole(int width, int height){
        Buffer buffer = new Buffer(height,width);
        ViewPort viewPort = new ViewPort(buffer, width, height, 0, 0);
        Console console = new Console(width,height);
        console.addView(viewPort);
        return console;
    }

    public void render() {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                for (ViewPort vp : viewPorts) {
                    System.out.print(vp.charAt(x,y));
                }
                System.out.println();
            }
        }
    }
}

public class FacadeDemo {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(30, 20);
        ViewPort viewPort = new ViewPort(buffer, 30, 20, 0, 0);
        Console console = new Console(30, 20);
        console.addView(viewPort);
        console.render();

        // rather than above...
        Console console2 = Console.newConsole(30, 20);
        console2.render();
    }
}
