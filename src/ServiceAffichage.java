import raytracer.Image;
import raytracer.Scene;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceAffichage extends Remote {
    Scene getScene()throws RemoteException;

    void addImage(Image i, int finalX, int finalY)throws RemoteException;
}
