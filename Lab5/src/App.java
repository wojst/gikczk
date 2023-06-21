import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    String version = "0.02";

    public static void main(String[] args) throws IOException {


        Model deerModel = new Model();
        deerModel.readOBJ("src/deer-mod.obj");

        Model deerModel2 = new Model();
        deerModel2.readOBJ("src/deer-mod.obj");
//
//        RandomColorRenderer mainRenderer = new RandomColorRenderer(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
//        mainRenderer.render(deerModel);
//
//        FlatShadingRenderer mainRenderer = new FlatShadingRenderer(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));

        var mainRenderer = new GouraudShadingRenderer(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));

        var TRANSLATE_VECTOR = new Vec3f(300, 200, 500);

        TRANSLATE_VECTOR.x /= mainRenderer.w;
        TRANSLATE_VECTOR.y /= mainRenderer.h;
        TRANSLATE_VECTOR.z /= mainRenderer.maxDepth;

        deerModel.translate(TRANSLATE_VECTOR);

        mainRenderer.render(deerModel);
        mainRenderer.render(deerModel2);

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
