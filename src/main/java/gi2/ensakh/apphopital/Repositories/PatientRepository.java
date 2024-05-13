package gi2.ensakh.apphopital.Repositories;

import gi2.ensakh.apphopital.Entities.Patient;
import gi2.ensakh.apphopital.Entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    public Patient findPatientByCin(String cin);
    @Query("SELECT r FROM RendezVous r WHERE r.medecin.cin = :cinMed and r.statusRDV='valide'")
    List<RendezVous> getPatientDetailsByCinMed(@Param("cinMed") String cinMed);
    Patient getPatientByCin(String cin);

}
