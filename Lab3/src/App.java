import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    String version = "0.02";

    public static void main(String[] args) {

        Renderer mainRenderer = new Renderer(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
        mainRenderer.clear();

        int x1 = 40; int y1 = 30;
        int x2 = 250; int y2 = 110;
        int x3 = 140; int y3 = 270;
        var vec1 = new Vec2f(x1, y1);
        var vec2 = new Vec2f(x2, y2);
        var vec3 = new Vec2f(x3, y3);

        mainRenderer.drawTriangle(vec1, vec2, vec3, new Vec3f(100, 200, 150));

        try {
            mainRenderer.save();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getVersion() {
	return this.version;
    }

}
