package gi2.ensakh.apphopital.Dtos;

import java.time.LocalDateTime;

public class addPatientDto {
    String cin;
    String nom;
    String prenom;
    String mail;
    String ad;
    Long tel;
    LocalDateTime date;
    String nomPrMed;
    String prenomMed;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getNomPrMed() {
        return nomPrMed;
    }

    public void setNomPrMed(String nomPrMed) {
        this.nomPrMed = nomPrMed;
    }

    public String getPrenomMed() {
        return prenomMed;
    }

    public void setPrenomMed(String prenomMed) {
        this.prenomMed = prenomMed;
    }
}
