package gi2.ensakh.apphopital.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @AllArgsConstructor
@DiscriminatorValue("Admin")
public class Administrateur extends Personne {
//    @OneToOne
//    private Compte compte;


}
