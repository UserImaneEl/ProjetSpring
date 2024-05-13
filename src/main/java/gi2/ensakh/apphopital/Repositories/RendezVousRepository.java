package gi2.ensakh.apphopital.Repositories;
import gi2.ensakh.apphopital.Entities.Medecin;
import gi2.ensakh.apphopital.Entities.RendezVous;
import gi2.ensakh.apphopital.Entities.StatusRDV;
import gi2.ensakh.apphopital.Entities.Secretaire;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous,Integer> {
    @Query("select R.patient.cin from RendezVous R where R.id=:id")
    String findCinPatientByIdRDV(@Param("id")Long id);
    @Query("select R.medecin.cin from RendezVous R where R.id=:id")
    String findCinDrByIdRDV(@Param("id")Long id);
    @Query("SELECT r.medecin.dep.secretaire FROM RendezVous r WHERE r.id = :idRdv")
    Secretaire findSecretaireByRdvId(@Param("idRdv") Long idRdv);
    @Query("select r from RendezVous r where r.medecin.dep.secretaire.compte.username = :username and r.statusRDV IN ('EnAttente', 'reporte')")
    List<RendezVous> listRdvBySec(@Param("username") String username);

    @Query("select count (r) from RendezVous r where r.medecin.cin=:cinMed and r.compte.username=:username and r.date_demande=:dateDemande")
    int numRdvByDay(@Param("cinMed")String cinMed,@Param("username") String username,@Param("dateDemande") Date dateDemande);
    @Query("SELECT TIME(a.date) FROM RendezVous a WHERE DATE(a.date) = DATE(:date) ORDER BY a.date DESC LIMIT 1")
    String findFirstAppointmentTimeForDate(@Param("date") LocalDate date);

    @Query("SELECT count(a) FROM RendezVous a WHERE DATE(a.date) = DATE(:date) ")
    int countNumberRDVByDay(@Param("date") LocalDate date);
    @Query("SELECT count (a) FROM RendezVous a WHERE a.statusRDV IN (:statuses) AND a.compte.username = :username AND a.patient.cin = :cinpatient AND a.medecin.cin = :cinMedecin")
    int RendezVousTaken(@Param("cinMedecin") String cinMedecin,@Param("cinpatient") String cinpatient, @Param("username") String username, @Param("statuses") List<StatusRDV> statuses);


    @Modifying
    @Transactional
    @Query("update RendezVous r set r.date = :date, r.date_demande = :dateDem where r.id = :idRdv")
    void ValiderRDV(@Param("idRdv") Long idRdv, @Param("date") LocalDateTime date, @Param("dateDem") Date dateDem);

    @Query("SELECT count(r) FROM RendezVous r WHERE DATE(r.date) = DATE(:date) AND r.medecin.cin=:cin ")
    int countNumberRDVByDay(@Param("date") LocalDate date,@Param("cin") String cin);
    @Modifying

        @Query("UPDATE RendezVous r SET r.statusRDV = :status WHERE r.id = :idRdv")
        void ChangerStatus(@Param("idRdv") Long idRdv,@Param("status") StatusRDV status);

        @Query("SELECT p.email FROM Personne p WHERE TYPE(p) = Patient AND p.cin = :cin")
        String getEmailByCin(@Param("cin") String cin);

        @Query("select CONCAT(p.nom, ' ', p.prenom) from Personne p, RendezVous r where p.cin = r.medecin.cin and TYPE(p) = Medecin and r.patient.cin = :cin")
        String findMedecinByPatientCin(@Param("cin") String cin);


        @Query("select date from RendezVous r where r.patient.cin= :cin and statusRDV='valide' order by date")
        List<Date> ListNotif(@Param("cin") String cin);

        @Query("select CONCAT(p.nom, ' ', p.prenom) from Personne p, RendezVous r where p.cin = r.medecin.cin and TYPE(p) = Medecin and r.patient.cin = :cin and r.statusRDV='valide' and r.date= :date")
        List<String> findMedecinByPatientCinAndStatus(@Param("cin") String cin, @Param("date") Date date);

        @Query("select count(*) from RendezVous r where r.patient.cin= :cin and statusRDV='valide'")
        int Count(@Param("cin") String cin);

    List<RendezVous> findByDate(LocalDateTime date);
    @Modifying

    @Query("Update RendezVous r set r.medecin.dep.secretaire.cin= :cinS where r.patient.cin= :cinP")
    void AjouterSecretaire(@Param("cinS") String cinS,@Param("cinP") String cinP);


    @Query("SELECT COUNT(rdv) FROM RendezVous rdv WHERE rdv.date IS NULL and rdv.compte.username= :username and (rdv.statusRDV='EnAttente' or rdv.statusRDV='reporte')")
    int countDemandes(@Param("username") String username);


    @Query("SELECT COUNT(rdv) FROM RendezVous rdv WHERE rdv.statusRDV='reporte' and rdv.compte.username= :sec")
      int countRDVReporte(@Param("sec") String sec);

    @Query("SELECT COUNT(p) FROM Personne p WHERE  TYPE(p) = Medecin and p.dep.secretaire.compte.username= :username")
    Integer countMed(@Param("username") String username);
    @Query("SELECT COUNT(rdv) FROM RendezVous rdv WHERE DATE(rdv.date) = CURRENT_DATE and rdv.compte.username= :username")
    int countRendezVousToday(@Param("username") String username);
    @Query("select R from RendezVous R where R.medecin.cin = :id AND DATE(R.date) = CURRENT_DATE() AND R.statusRDV = :s")
    public List<RendezVous> findRdvByMedecin(@Param("id")String id,StatusRDV s);
    RendezVous findRendezVousById(int id);
    @Query("select count(r) from RendezVous r where r.statusRDV='valide' and r.medecin.compte.username= :username")
    public Integer countRdvOfMedByDay(@Param("username") String username);



}




