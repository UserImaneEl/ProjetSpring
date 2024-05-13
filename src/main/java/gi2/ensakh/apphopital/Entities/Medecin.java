package gi2.ensakh.apphopital.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
@Entity
@AllArgsConstructor @Data @NoArgsConstructor
@DiscriminatorValue("Medecin")
public class Medecin extends Personne {
    private String specialite;
    @JsonBackReference
    @ManyToOne
    private Departement dep;
    @JsonBackReference
    @ManyToMany
   @JoinTable(name="MedPatient")
    private List<Patient> patients;
    @JsonManagedReference

    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> RDV;
    @JsonManagedReference

    @OneToMany(mappedBy ="medecin")
   private List<Reclamation> reclamations;
    private  Integer durationRDV;
    @Enumerated(EnumType.STRING)
    OnOff onOff;
    private Integer limitNumRDV;
    private LocalTime debutRDV;

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Departement getDep() {
        return dep;
    }

    public void setDep(Departement dep) {
        this.dep = dep;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<RendezVous> getRDV() {
        return RDV;
    }

    public void setRDV(List<RendezVous> RDV) {
        this.RDV = RDV;
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    public Integer getDurationRDV() {
        return durationRDV;
    }

    public void setDurationRDV(Integer durationRDV) {
        this.durationRDV = durationRDV;
    }

    public OnOff getOnOff() {
        return onOff;
    }

    public void setOnOff(OnOff onOff) {
        this.onOff = onOff;
    }

    public Integer getLimitNumRDV() {
        return limitNumRDV;
    }

    public void setLimitNumRDV(Integer limitNumRDV) {
        this.limitNumRDV = limitNumRDV;
    }

    public LocalTime getDebutRDV() {
        return debutRDV;
    }

    public void setDebutRDV(LocalTime debutRDV) {
        this.debutRDV = debutRDV;
    }

}
