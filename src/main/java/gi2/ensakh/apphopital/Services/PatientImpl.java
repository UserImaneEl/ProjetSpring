package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.Patient;
import gi2.ensakh.apphopital.Entities.RendezVous;
import gi2.ensakh.apphopital.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import gi2.ensakh.apphopital.Dtos.patientDetailsDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientImpl implements PatientInf{
    @Autowired
    PatientRepository rep;
    @Override
    public List<patientDetailsDto> getPatientDetailsByCinMed(String cin){
        return rep.getPatientDetailsByCinMed(cin)
                .stream()
                .map(this::convertRDVToDto)
                .collect(Collectors.toList());
    }
    private patientDetailsDto convertRDVToDto(RendezVous rdv){
        patientDetailsDto dto=new patientDetailsDto();
        dto.setCin(rdv.getPatient().getCin());
        dto.setNom(rdv.getPatient().getNom());
        dto.setPrenom(rdv.getPatient().getPrenom());
        dto.setDate(rdv.getDate_RDV());
        //dto.setTime(rdv.getDate_demande());

        return dto;
    }
    @Override
    public void savePatient(Patient patient) {
        rep.save(patient);
}
}
