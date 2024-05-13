package gi2.ensakh.apphopital.Repositories;

import gi2.ensakh.apphopital.Entities.Secretaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SecretaireRepository extends JpaRepository<Secretaire,Integer> {
    @Query("select sc from Secretaire sc join sc.listeDep dep where dep.num = :depNum")
    public Secretaire findSecByDepartementId(@Param("depNum") int depNum);
}
