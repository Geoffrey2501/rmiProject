import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.util.NoSuchElementException;

public class ServiceRaytracer implements ServiceAffichage {
    private Scene scene;
    private Disp disp;
    public ServiceRaytracer(Scene c, int largeur, int hauteur){
        this.scene = c;
        this.disp = new Disp("Raytracer", largeur, hauteur);
    }
    public void addImage(Image i, int x, int y){
        disp.setImage(i, x, y);
    }

    public Scene getScene(){
        return this.scene;
    }

}
