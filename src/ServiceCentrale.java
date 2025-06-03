import raytracer.Image;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ServiceCentrale extends Remote {

    void Enregistrer(ServiceCalculatoire service) throws RemoteException;
    void utiliserService(ServiceAffichage a, int l, int h) throws RemoteException;

}
