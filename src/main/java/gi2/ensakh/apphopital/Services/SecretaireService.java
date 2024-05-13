package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.RendezVous;
import gi2.ensakh.apphopital.Entities.Secretaire;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SecretaireService {
    public List<RendezVous> listRdvByMedecin(String cin);
    public Secretaire AjoutSecToRDV (Long num);
}
