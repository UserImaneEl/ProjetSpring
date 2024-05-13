package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.RendezVous;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PatientService {
    public List<RendezVous> listeRdvByPatient(String cin);
}
