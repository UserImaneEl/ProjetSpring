package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.*;
import gi2.ensakh.apphopital.Repositories.CompteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CompteServiceImpl implements CompteService {
    private CompteRepository compteRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public Compte addNewUser(String username, String password, Role role, String confirmPassword) {
        Compte compte=compteRepository.findCompteByUsername(username);
        if(compte != null) throw new RuntimeException("this user already exist");
        if(!password.equals(confirmPassword)) throw new RuntimeException("Passwords not match");
        Compte savecAccount= new Compte();
        savecAccount.setUsername(username);
        savecAccount.setMdp(passwordEncoder.encode(password));
        savecAccount.setRole(role);
        return compteRepository.save(savecAccount);
    }

    @Override
    public Medecin ajoutMedecin() {
        return null;
    }

    @Override
    public Secretaire ajoutSecretaire() {
        return null;
    }

    @Override
    public Patient ajoutPatient() {
        return null;
    }

}
