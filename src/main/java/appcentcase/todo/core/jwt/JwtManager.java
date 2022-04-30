package appcentcase.todo.core.jwt;

import appcentcase.todo.entities.User;
import appcentcase.todo.models.dto.AuthResponse;
import appcentcase.todo.models.dto.UserLoginDto;
import appcentcase.todo.services.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */

@Service
public class JwtManager implements UserDetailsService {
    @Autowired
    private Jwt jwt;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse createToken(UserLoginDto userForLoginDto) throws Exception {
        String userName = userForLoginDto.getUserName();
        String userPassword = userForLoginDto.getPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = loadUserByUsername(userName);
        String token = jwt.generateToken(userDetails);

        User user = userService.getByUserName(userName).getData();
        return new AuthResponse(user, token);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getByUserName(userName).getData();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                    getAuthority(user));
        } else {
            throw new UsernameNotFoundException("Kullanıcı adına sahip kullanıcı bulunamadı: " + userName);
        }
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
