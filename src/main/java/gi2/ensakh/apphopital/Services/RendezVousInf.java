package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.RendezVous;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Component

public interface
RendezVousInf {

    void ValiderRDV(Long idRdv,LocalDateTime date,String cinP);

    public List<String> ListNotif(String cin);

    int Count(String cin);

    int countRDVreporte(String sec);

    void SaveRDV(String cinP, String cinM);

    List<RendezVous> getAllRendezVous();

    int countDemandes(String username);

    Integer countMed(String username);
    //void ajouterRDV(String cin , String nom , String prenom ,String mail, Long tel, String ad, String nomMed, LocalDateTime date);
    void saveRendezVous(RendezVous r);
}
