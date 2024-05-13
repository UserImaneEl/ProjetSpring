package gi2.ensakh.apphopital.Services;



import java.time.LocalDateTime;
import java.util.Date;

public interface ConsultationService {
    public void ajouterConsultation(String idM,String idP,LocalDateTime date,String description);

}