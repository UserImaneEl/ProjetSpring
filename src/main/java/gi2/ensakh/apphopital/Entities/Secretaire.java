package gi2.ensakh.apphopital.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue("Secretaire")
public class Secretaire extends Personne {
    @JsonManagedReference
    @OneToMany(mappedBy = "secretaire")
    private List<Departement> listeDep;
//    @OneToOne
//    private Compte compte;


}
