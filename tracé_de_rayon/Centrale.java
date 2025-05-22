import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Centrale implements ServiceCentrale{
    private ArrayList<ServiceCalculatoire> services;
    private ArrayList<Image> images;

    public Centrale(){
        this.services = new ArrayList<>();
        images = new ArrayList<>();
    }

    public synchronized void distribuerMessage(Scene scene,int l,int h) throws RemoteException{
        images.clear();
        Iterator<ServiceCalculatoire> it = services.iterator();

        int Iy = h/services.size();
        int Ix = l/services.size();

        while(it.hasNext()){
            for (int y = 0; y <= h;y+=Iy){
                for (int x = 0; x <= l; x+=Ix){
                    Thread t = new Thread(
                            ()->{
                                try{
                                    images.add(it.next().calculer(scene,Ix,Iy,l,h));
                                }
                                catch(RemoteException e){
                                    it.remove();
                                }catch(NoSuchElementException e){

                                }
                            }
                    );
                    t.start();
                }
            }
        }
    }


    public synchronized void Enregistrer(ServiceCalculatoire service) throws RemoteException{
        services.add(service);
        for (Image img : images) {
            service.calculer(img);
        }
    }
}

