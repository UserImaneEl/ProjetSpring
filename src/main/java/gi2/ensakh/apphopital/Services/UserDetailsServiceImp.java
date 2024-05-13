package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.Compte;
import gi2.ensakh.apphopital.Repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
private CompteRepository compteRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Compte compte = compteRepository.findCompteByUsername(username);
        if (compte == null)throw new UsernameNotFoundException(String.format("User%s not found",username));
        UserDetails userDetails = User
                .withUsername(compte.getUsername())
                .password(compte.getMdp())
                .roles(String.valueOf(compte.getRole()))
                .build();
        return userDetails;
    }
}
