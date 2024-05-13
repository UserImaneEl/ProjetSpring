package gi2.ensakh.apphopital.Services;



import gi2.ensakh.apphopital.Entities.Personne;
import gi2.ensakh.apphopital.Repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PersonneServiceImp implements PersonneService{


    @Autowired
    PersonneRepository pr;

    @Override
    public Personne getInf(String id) {
        // TODO Auto-generated method stub
        return pr.getInf(id);
    }
    @Override
    public boolean existsByCin(String cin ) {
        // Utilisez le UserRepository pour vérifier si le nom d'utilisateur existe déjà dans la base de données
        return pr.existsById(cin);
    }

    @Override
    public void ajouterImage(String personneCin, byte[] imageBlob) {
        // Vérifier si la personne existe
        Personne personne = pr.findByCin(personneCin);
        if (personne != null) {
            personne.setImageBlob(imageBlob); // Définir les données BLOB de l'image
            pr.save(personne); // Enregistrer l'entité mise à jour avec l'image
        } else {
            // Gérer le cas où la personne n'existe pas
        }
    }

    public byte[] getBlobImage(String personneCin) {
        Personne personne = pr.findById(personneCin).orElse(null);
        if (personne != null) {
            return personne.getImageBlob();
        } else {
            return null;
        }
    }

}