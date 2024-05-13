package gi2.ensakh.apphopital.Services;


import gi2.ensakh.apphopital.Entities.Personne;

public interface PersonneService {
    public Personne getInf(String id);
    public boolean existsByCin(String cin);

    void ajouterImage(String personneCin, byte[] imageBlob);
}