import raytracer.Image;
import raytracer.Scene;

public interface ServiceCalculatoire {
    public Image calculer(Scene scene, int x0, int y0, int l, int h);
}
