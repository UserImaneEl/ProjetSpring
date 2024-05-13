package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.*;

public interface CompteService {
   Compte addNewUser(String username, String password, Role role, String confirmPassword);
   Medecin ajoutMedecin();
   Secretaire ajoutSecretaire();
   Patient ajoutPatient();


}
