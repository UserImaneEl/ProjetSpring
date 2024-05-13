package gi2.ensakh.apphopital.Dtos;

import gi2.ensakh.apphopital.Entities.Medecin;
import gi2.ensakh.apphopital.Entities.StatusRDV;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PatientRdvDto {
    private int idRdv;
    private String cin;
    private String nom;
    private String prenom;
    private Long tel;
    private String email;
    private Date dateDemande;
    private StatusRDV statusRDV;
    private String cin_med ;
    private String nom_med;
    private String prenom_med;
    private String cin_sec;
    public String getCin() {
        return cin;
    }

    public String getCin_med() {
        return cin_med;
    }

    public void setCin_med(String cin_med) {
        this.cin_med = cin_med;
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

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return dateDemande;
    }

    public void setDate(Date date) {
        this.dateDemande = date;
    }

    public StatusRDV getStatusRDV() {
        return statusRDV;
    }

    public void setStatusRDV(StatusRDV statusRDV) {
        this.statusRDV = statusRDV;
    }

    public String getNom_med() {
        return nom_med;
    }

    public void setNom_med(String nom_med) {
        this.nom_med = nom_med;
    }

    public String getPrenom_med() {
        return prenom_med;
    }

    public void setPrenom_med(String prenom_med) {
        this.prenom_med = prenom_med;
    }

    public String getCin_sec() {
        return cin_sec;
    }

    public void setCin_sec(String cin_sec) {
        this.cin_sec = cin_sec;
    }

    public int getIdRdv() {
        return idRdv;
    }

    public void setIdRdv(int idRdv) {
        this.idRdv = idRdv;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }
}