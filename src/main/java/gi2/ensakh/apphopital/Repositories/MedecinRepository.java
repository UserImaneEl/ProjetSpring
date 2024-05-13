package gi2.ensakh.apphopital.Repositories;

import gi2.ensakh.apphopital.Entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin,Integer> {
    @Query("select R.patient.cin from RendezVous R where R.id=:id")
    public String findDrCinByIdRDV(@Param("id")Long id);
   @Query("select m from Medecin m where m.cin=:cin")
    Medecin findByCin(@Param("cin")String cin);

    @Query("SELECT p FROM Personne p WHERE TYPE(p)= Medecin and p.nom=:nomMedecin")
    Medecin getMedByName(@Param("nomMedecin") String nomMedecin);
    @Query("select p from Personne p where TYPE(p)= Medecin and p.dep.secretaire.compte.username= :username")
    List<Medecin> getAllMedecins(@Param("username") String username);
    @Query("SELECT p FROM Personne p WHERE TYPE(p)= Medecin and CONCAT(p.nom, ' ', p.prenom) =:nomPrenom")
    Medecin getMedByNomPrenom(@Param("nomPrenom") String nomPrenom);
    @Query("SELECT m FROM Medecin m WHERE LOWER(m.nom) LIKE %:keyword% OR LOWER(m.prenom) LIKE %:keyword%")
    List<Medecin> findMedecinByKeyword(@Param("keyword") String keyword);
    @Query("SELECT m FROM Personne m  WHERE TYPE(m)= Medecin and m.compte.username= :username")
    Medecin findByUsername(@Param("username") String username);

}
