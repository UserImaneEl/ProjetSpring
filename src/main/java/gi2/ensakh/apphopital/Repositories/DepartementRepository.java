package gi2.ensakh.apphopital.Repositories;

import gi2.ensakh.apphopital.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartementRepository extends JpaRepository<Departement,Integer> {
    @Query("select dr.dep.num from Medecin dr where dr.cin = :cin")
    public int findIdDepByMedCin(@Param("cin") String cin);
}
