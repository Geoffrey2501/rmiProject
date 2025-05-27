import raytracer.Image;
import raytracer.Scene;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceCalculatoire extends Remote {
    public Image calculer(Scene scene, int x0, int y0, int l, int h) throws RemoteException;
}
