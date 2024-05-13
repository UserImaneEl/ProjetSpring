package gi2.ensakh.apphopital.Services;



import java.time.LocalDateTime;
import java.util.Date;

import gi2.ensakh.apphopital.Entities.Consultation;
import gi2.ensakh.apphopital.Repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ConsultationServiceImp implements ConsultationService{



    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public void ajouterConsultation(String idM, String idP, LocalDateTime date, String description) {
        // TODO Auto-generated method stub
        Consultation c=new Consultation();
        c.setIdM(idM);
        c.setIdP(idP);
        c.setDateC(date);
        c.setDescription(description);
        consultationRepository.save(c);

    }



}