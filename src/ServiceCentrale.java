import raytracer.Image;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ServiceCentrale extends Remote {

    public void Enregistrer(ServiceCalculatoire service) throws RemoteException;
    ServiceCalculatoire getService() throws RemoteException;

}
