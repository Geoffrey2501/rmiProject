import raytracer.Disp;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.util.NoSuchElementException;

public class ServiceRaytracer implements ServiceAffichage {

    public void afficherResultat(Scene scene, int l, int h, ServiceCentrale centrale) {
        Disp disp = new Disp("Raytracer", l, h);
        try {

            int Iy = h / 2;
            int Ix = l / 2;
            for (int y = 0; y < h; y += Iy) {
                for (int x = 0; x < l; x += Ix) {
                    int finalX = x;
                    int finalY = y;
                    final ServiceCalculatoire service = centrale.getService();
                    Thread t = new Thread(
                            () -> {
                                try {
                                    System.out.println("Calcul de l'image pour " + finalX + ", " + finalY);
                                    disp.setImage(service.calculer(scene, finalX, finalY, Ix, Iy), finalX, finalY);
                                    System.out.println("Image calculée pour " + finalX + ", " + finalY);
                                } catch (RemoteException | NoSuchElementException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                    t.start();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
