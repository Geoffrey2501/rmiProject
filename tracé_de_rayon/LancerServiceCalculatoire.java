import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LancerServiceCalculatoire {
    public static void main(String[] args) {
        String ip = "localhost";
        int port = 1099;// Adresse IP par défaut
        // Vérification des arguments
        if (args.length >= 1) {
           ip = args[0];
        }
        if(args.length >= 2) {
           port = Integer.parseInt(args[1]);
        }

        try {
            ServiceCalculatoire serviceCalculatoire = new ServiceImage(); // Implémentation de l'interface ServiceCalculatoire
            // Création du service de calcul
            ServiceCalculatoire service = (ServiceCalculatoire) UnicastRemoteObject.exportObject(serviceCalculatoire, 0);
            Registry reg = LocateRegistry.getRegistry(ip, port);
            Centrale centrale = (Centrale) reg.lookup("Centrale");
            // Enregistrement du service auprès de la centrale
            centrale.Enregistrer(service);

            // Affichage d'un message de confirmation
            System.out.println("Service de calcul lancé sur le port " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
