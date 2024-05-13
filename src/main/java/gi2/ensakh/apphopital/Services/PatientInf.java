package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.Patient;
import org.springframework.stereotype.Component;
import gi2.ensakh.apphopital.Dtos.patientDetailsDto;

import java.util.List;
@Component
public interface PatientInf {
    List<patientDetailsDto> getPatientDetailsByCinMed(String cin);
    void savePatient(Patient p);
}
