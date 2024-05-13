package gi2.ensakh.apphopital.Dtos;

import java.time.LocalDateTime;
import java.util.Date;

public class patientDetailsDto {
    private String cin;
    private String nom;
    private String prenom;
    //private Date time;
    private LocalDateTime date;//date rendez vous

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    //public Date getTime() {
      //  return this.time;
    //}

    //public void setTime(Date time) {
      //  this.time = time;
    //}
}
