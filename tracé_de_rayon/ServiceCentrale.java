import raytracer.Image;
import java.util.List;

public interface ServiceCentrale {

    public void Enregistrer(ServiceCalculatoire service);

    public List<Image> calculer();
}
