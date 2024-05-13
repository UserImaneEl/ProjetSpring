package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.Medecin;
import gi2.ensakh.apphopital.Entities.Patient;
import gi2.ensakh.apphopital.Entities.RendezVous;
import gi2.ensakh.apphopital.Entities.StatusRDV;
import gi2.ensakh.apphopital.Repositories.MedecinRepository;
import gi2.ensakh.apphopital.Repositories.PatientRepository;
import gi2.ensakh.apphopital.Repositories.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component

public class RendezVousImpl implements RendezVousInf{
    @Autowired
    RendezVousRepository rep;
    @Autowired
    PatientRepository repPat;
    @Autowired
    MedecinRepository repMed;
    @Autowired

    private JavaMailSender javaMailSender;
    @Transactional
    @Override
    public void ValiderRDV(Long idRdv,LocalDateTime date,String cinP) {
        Date dateDem = new Date();
        rep.ChangerStatus(idRdv,StatusRDV.valide);
        rep.ValiderRDV(idRdv, date,dateDem);

        //rep.AjouterSecretaire(cinS,cinP);
        String email=rep.getEmailByCin(cinP);
        String NomPrenom =rep.findMedecinByPatientCin(cinP);
        String message = "Votre rendez-vous avec docteur "+NomPrenom +"a été validé pour la date " + date + ".\n";
        message += "Merci de prendre note de votre rendez-vous.\n\n";
        sendEmail(email, "Bonjour",message);
    }

    public boolean sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
            return true; // Email envoyé avec succès
        } catch (MailException e) {
            // Gérer l'exception, par exemple, journaliser l'erreur
            System.out.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
            return false; // Échec de l'envoi de l'email
        }
    }

    @Override
    public List<String> ListNotif(String cin){
        List<String> list = new ArrayList<>();

        List<Date> listD=rep.ListNotif(cin);

        for (Date date : listD) {
            List<String> listNP=rep.findMedecinByPatientCinAndStatus(cin,date);
            for(String np : listNP){
                String message = "Votre rendez-vous avec docteur "+np +"a été validé pour la date " + date + ".\n";
                message += "Merci de prendre note de votre rendez-vous.\n\n";
                list.add(message);
            }


        }
        return list;

    }
    @Override
    public int Count(String cin)
    {
        return rep.Count(cin);
    }
    @Override
    public int countRDVreporte(String sec){
        return rep.countRDVReporte(sec);
    }

    @Override
    public void SaveRDV(String cinP, String cinM) {
        Date date=new Date();

        RendezVous rdv=new RendezVous();
        Patient p=new Patient();
      Medecin m=new Medecin();
        p.setCin(cinP);
        m.setCin(cinM);
        rdv.setDate_demande(date);
        rdv.setPatient(p);
        rdv.setMedecin(m);
        rdv.setStatusRDV(StatusRDV.EnAttente);



        rep.save(rdv);
    }

    @Scheduled(cron = "0 54 15 * * *") // Exécution à minuit tous les jours
    public void deleteExpiredRendezVous() {
        LocalDateTime currentDate = LocalDateTime.now();
        List<RendezVous> expiredRendezVous = rep.findByDate(currentDate);
        rep.deleteAll(expiredRendezVous);
    }
    @Override
    public List<RendezVous> getAllRendezVous() {
        return rep.findAll();
    }

    public int countDemandes(String username) {

        return rep.countDemandes(username);
   }
   @Override
    public Integer countMed(String username){
        return rep.countMed(username);
   }
    @Override
    public void saveRendezVous(RendezVous r) {
        rep.save(r);
        r.setStatusRDV(StatusRDV.valide);
   }
    /*@Override
    public void ajouterRDV(String cin , String nom , String prenom ,String mail, Long tel, String ad, String nomMed, LocalDateTime date) {
        Patient p = repPat.findPatientByCin(cin);
        if(p == null){
            p.setNom(nom);
            p.setPrenom(prenom);
            p.setCin(cin);
            p.setAdresse(ad);
            p.setEmail(mail);
            p.setTel(tel);
            repPat.save(p);
        }
        Medecin m = repMed.getMedByName(nomMed);
        if (m == null) {
            System.out.println("Médecin introuvable.");
        }
        RendezVous r = new RendezVous();
        r.setDate_RDV(date);
        r.setMedecin(m);
        r.setStatusRDV(StatusRDV.valide);
        rep.save(r);

}
*/
}
