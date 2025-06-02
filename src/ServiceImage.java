import raytracer.Image;
import raytracer.Scene;

public class ServiceImage implements ServiceCalculatoire {

    @Override
    public Image calculer(Scene scene, int x0, int y0, int l, int h) {
        Image i = scene.compute(x0, y0, l, h);
        System.out.println(x0 + " " + y0 + " " + l + " " + h );
        return i;
    }
}
