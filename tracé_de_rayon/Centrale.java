import raytracer.Image;

import java.util.ArrayList;
import java.util.Iterator;

public class Centrale implements ServiceCentrale{
    private ArrayList<ServiceCalculatoire> services;
    private ArrayList<Image> images;
    private Iterator<ServiceCalculatoire> it;
    public Centrale(){
        this.services = new ArrayList<>();
        images = new ArrayList<>();
    }


    public synchronized void Enregistrer(ServiceCalculatoire service) {
        services.add(service);
    }

    @Override
    public ServiceCalculatoire getService() {
        if(it == null || !it.hasNext()) {
            it = services.iterator();
        }
        return it.next();
    }
}

