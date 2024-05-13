package gi2.ensakh.apphopital.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue("Patient")
public class Patient extends Personne {
    @JsonManagedReference

    @OneToMany(mappedBy = "patient")
    private List<RendezVous> RDV;
    @ManyToMany(mappedBy = "patients")
    private List<Medecin> medecins;
    @JsonManagedReference

    @OneToMany(mappedBy = "patient")
    private List<Reclamation> reclamations;

    public List<RendezVous> getRDV() {
        return RDV;
    }

    public void setRDV(List<RendezVous> RDV) {
        this.RDV = RDV;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }
}
