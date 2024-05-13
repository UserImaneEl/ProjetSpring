package gi2.ensakh.apphopital.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")


    public class Reclamation {
        @Id

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int Id;
        private String commentaire;
    @JsonBackReference
    @ManyToOne
    private Patient patient;
    @JsonBackReference
    @ManyToOne
    private Medecin medecin;
    }


