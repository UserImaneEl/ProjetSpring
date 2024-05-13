package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.Medecin;
import gi2.ensakh.apphopital.Repositories.MedecinRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public class MedecinImpl implements MedecinInf{
    @Autowired
    MedecinRepository mr;
    @Override
    public Medecin getMedByName(String nom) {
        return mr.getMedByName(nom);
}
}
