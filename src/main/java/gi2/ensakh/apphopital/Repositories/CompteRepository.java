package gi2.ensakh.apphopital.Repositories;

import gi2.ensakh.apphopital.Entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompteRepository extends JpaRepository<Compte, Integer> {
    Compte findCompteByUsername(String username);


}
