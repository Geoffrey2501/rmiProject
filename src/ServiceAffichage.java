import raytracer.Scene;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceAffichage extends Remote {
    public void afficherResultat(Scene scene, int l, int h, ServiceCentrale centrale) throws RemoteException;
}
