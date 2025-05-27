import raytracer.Image;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public interface ServiceCentrale extends Remote {

    public void Enregistrer(ServiceCalculatoire service);
    ArrayList<ServiceCalculatoire> getServices();
}
