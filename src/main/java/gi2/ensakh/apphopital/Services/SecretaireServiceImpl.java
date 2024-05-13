package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.RendezVous;
import gi2.ensakh.apphopital.Entities.Secretaire;
import gi2.ensakh.apphopital.Repositories.DepartementRepository;
import gi2.ensakh.apphopital.Repositories.MedecinRepository;
import gi2.ensakh.apphopital.Repositories.RendezVousRepository;
import gi2.ensakh.apphopital.Repositories.SecretaireRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class SecretaireServiceImpl implements SecretaireService {

    @Override
    public List<RendezVous> listRdvByMedecin(String cin) {
        return null;
    }

    @Override
    public Secretaire AjoutSecToRDV(Long idRDV) {
       /* String cinDr = medecinRepository.findDrCinByIdRDV(idRDV);
        int numDep=departementRepository.findIdDepByMedCin(cinDr);
        return secretaireRepository.findSecByDepartementId(numDep);*/
        return null;

    }
}
