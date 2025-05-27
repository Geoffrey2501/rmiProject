import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ServiceRaytracer implements ServiceAffichage {

    public void afficherResultat(Scene scene, int l, int h, Centrale centrale) {
        Disp disp = new Disp("Raytracer", l, h);
        try {
            ArrayList<ServiceCalculatoire> listServices = centrale.getServices();
            Iterator<ServiceCalculatoire> it = listServices.iterator();
            int Iy = h / 50;
            int Ix = l / 50;
            for (int y = 0; y <= h; y += Iy) {
                for (int x = 0; x <= l; x += Ix) {
                    if(!it.hasNext()) {
                        listServices = centrale.getServices();
                        it = listServices.iterator(); // Reset iterator if we reach the end
                    }
                    int finalX = x;
                    int finalY = y;
                    Iterator<ServiceCalculatoire> finalIt = it;
                    Thread t = new Thread(
                            () -> {
                                try {
                                    disp.setImage(finalIt.next().calculer(scene, Ix, Iy, l, h), finalX, finalY);
                                } catch (RemoteException e) {
                                    finalIt.remove();
                                } catch (NoSuchElementException e) {

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
