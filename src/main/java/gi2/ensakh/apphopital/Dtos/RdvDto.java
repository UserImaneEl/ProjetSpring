package gi2.ensakh.apphopital.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import gi2.ensakh.apphopital.Entities.StatusRDV;
import lombok.Data;

import java.util.Date;

@Data
public class RdvDto {
    private StatusRDV statusRDV;
    @JsonProperty("Patientcin")
    private String patientCin;
    @JsonProperty("DRcin")
    private String DRcin;
    @JsonProperty("username")
    private String username;
    private Date date_demande;
    private String nom ;
    private String prenom ;
    private Long tel ;
    private String email ;
    private String adresse;

    public StatusRDV getStatusRDV() {
        return statusRDV;
    }

    public void setStatusRDV(StatusRDV statusRDV) {
        this.statusRDV = statusRDV;
    }

    public String getPatientCin() {
        return patientCin;
    }

    public void setPatientCin(String patientCin) {
        this.patientCin = patientCin;
    }

    public String getDRcin() {
        return DRcin;
    }

    public void setDRcin(String DRcin) {
        this.DRcin = DRcin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate_demande() {
        return date_demande;
    }

    public void setDate_demande(Date date_demande) {
        this.date_demande = date_demande;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}

