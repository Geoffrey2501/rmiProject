import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LancerServiceCentrale {
    public static void main(String[] args) {
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
            // Création de la centrale
            ServiceCentrale centrale = new Centrale();
            // Exportation de la centrale pour RMI
            ServiceCentrale centraleRMI = (ServiceCentrale) java.rmi.server.UnicastRemoteObject.exportObject(centrale, 0);
            // Enregistrement de la centrale dans le registre RMI
            LocateRegistry.createRegistry(port);
            Registry reg = LocateRegistry.getRegistry(ip, port);
            reg.rebind("Centrale", centraleRMI);

            // Affichage d'un message de confirmation
            System.out.println("Centrale lancée sur le port " + port + " et enregistrée à l'adresse " + ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
