import raytracer.Scene;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LancerRaytracerService {
    public static void main(String[] args) {
        int l = 512; // Largeur par défaut
        int h = 512; // Hauteur par défaut
        String ip = "localhost";
        int port = 1099; // Adresse IP par défaut
        // Vérification des arguments
        if (args.length >= 1) {
            ip = args[0];
        }
        if (args.length >= 2) {
            port = Integer.parseInt(args[1]);
        }

        try {
            // Création du service de raytracing
            ServiceRaytracer serviceRaytracer = new ServiceRaytracer();
            // Exportation du service pour RMI
            ServiceRaytracer service = (ServiceRaytracer) java.rmi.server.UnicastRemoteObject.exportObject(serviceRaytracer, 0);
            Registry reg = LocateRegistry.getRegistry(ip, port);
            Centrale centrale = (Centrale) reg.lookup("Centrale");
            Scene scene = new Scene("simple.txt", l, h); // Charger une scène par défaut
            // Enregistrement du service auprès de la centrale
            service.afficherResultat(scene, l, h, centrale); // Passer une scène valide ici

            // Affichage d'un message de confirmation
            System.out.println("Service de raytracing lancé sur le port " + port + " et enregistré auprès de la centrale à l'adresse " + ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
