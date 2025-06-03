import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LancerRaytracerService {
    public static void main(String[] args) {
        int l = 512; // Largeur par défaut
        int h = 512; // Hauteur par défaut
        String ip = "localhost";
        int port = 0; // Adresse IP par défaut
        // Vérification des arguments
        if (args.length >= 1) {
            ip = args[0];
        }
        if (args.length >= 2) {
            port = Integer.parseInt(args[1]);
        }

        try {
            Scene scene = new Scene("./src/simple.txt", l, h);

            ServiceAffichage serviceRaytracer = new ServiceRaytracer(scene, l, h);
            // Exportation du service pour RMI
            ServiceAffichage service = (ServiceAffichage) UnicastRemoteObject.exportObject(serviceRaytracer, 0);

            Registry reg = LocateRegistry.getRegistry(ip, port);
            ServiceCentrale centrale = (ServiceCentrale) reg.lookup("Centrale");
            // Charger une scène par défaut
            // Enregistrement du service auprès de la centrale
            centrale.utiliserService(service, l, h); // Passer une scène valide ici

            // Affichage d'un message de confirmation
            System.out.println("Service de raytracing lancé sur le port " + port + " et enregistré auprès de la centrale à l'adresse " + ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
