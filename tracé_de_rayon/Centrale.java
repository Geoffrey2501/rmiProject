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


    public synchronized void Enregistrer(ServiceCalculatoire service) {
        services.add(service);
    }

    @Override
    public ArrayList<ServiceCalculatoire> getServices() {
        return services;
    }
}

