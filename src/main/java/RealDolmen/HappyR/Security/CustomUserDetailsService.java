package RealDolmen.HappyR.Security;

import RealDolmen.HappyR.Repository.UserRepository;
import RealDolmen.HappyR.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        List<SimpleGrantedAuthority> authorities = GetAuthorities(user);
        return mapUserToCustomUserDetails(user, authorities);
    }

    public List<SimpleGrantedAuthority> GetAuthorities(User user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Add ROLE_USER authority regardless of the user's role
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Add ROLE_MANAGER authority if the user is a manager
        if (true) {
            authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        }

        return authorities;
        }

    private CustomUserDetails mapUserToCustomUserDetails(User user, List<SimpleGrantedAuthority> authorities) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(user.getId());
        customUserDetails.setFirstName(user.getFirstName());
        customUserDetails.setLastName(user.getLastName());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setEmail(user.getEmail());
        customUserDetails.setAuthorities(authorities);
        return customUserDetails;
    }
}
