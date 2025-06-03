import raytracer.Image;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Centrale implements ServiceCentrale{
    private  ArrayList<ServiceCalculatoire> services;

    private Iterator<ServiceCalculatoire> it;
    public Centrale(){
        this.services = new ArrayList<>();
    }


    public synchronized void Enregistrer(ServiceCalculatoire service) {
        services.add(service);
    }

    @Override
    public void utiliserService(ServiceAffichage a, int l, int h) {
        // découpage de l'image en
        int lPartager = l / 3;
        int hPartager = l / 3;

        for (int y = 0; y < h; y += hPartager) {
            for (int x = 0; x < l; x += lPartager) {
                int finalX = x;
                int finalY = y;
                it = services.iterator();
                Thread t = new Thread(() -> {
                    try {
                        ServiceCalculatoire service;
                        synchronized (services) {
                            if (!it.hasNext()) {
                                it = services.iterator();
                            }
                            service = it.next();
                        }
                        System.out.println("Calcul de l'image pour " + finalX + ", " + finalY);
                        a.addImage(service.calculer(a.getScene(), finalX, finalY, lPartager, hPartager), finalX, finalY);
                        System.out.println("Image calculée pour " + finalX + ", " + finalY);
                    } catch (RemoteException e) {
                       it.remove();
                    }catch (NoSuchElementException e) {
                        System.out.println("Aucun service disponible pour le calcul de l'image à " + finalX + ", " + finalY);
                    }
                });
                t.start();
            }
        }
    }
}

