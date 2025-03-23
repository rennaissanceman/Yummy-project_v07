package pl.yummy.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * YummyUserDetailsService odpowiada za ładowanie danych użytkownika z bazy danych dla mechanizmu Spring Security.
 * Implementacja pobiera użytkownika na podstawie nazwy użytkownika, mapuje przypisane role na GrantedAuthority
 * oraz tworzy obiekt UserDetails, który służy do uwierzytelnienia i autoryzacji w aplikacji.
 */
@RequiredArgsConstructor
@Service
public class YummyUserDetailsService implements UserDetailsService {

    private final YummyUserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        YummyUserEntity user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika o nazwie: " + username);
        }
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }


    /*
     * Mapuje zbiór ról użytkownika na listę obiektów GrantedAuthority.
     *
     * @param userRoles zbiór ról przypisanych do użytkownika
     * @return lista GrantedAuthority
     */
    private List<GrantedAuthority> getUserAuthority(Set<RoleEntity> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .distinct()
                .collect(Collectors.toList());
    }

    /*
     * Buduje obiekt UserDetails na podstawie danych użytkownika i przypisanych uprawnień.
     *
     * @param user        obiekt YummyUserEntity pobrany z bazy
     * @param authorities lista uprawnień
     * @return obiekt UserDetails wykorzystywany przez Spring Security
     */
    private UserDetails buildUserForAuthentication(YummyUserEntity user, List<GrantedAuthority> authorities) {
        return new User(
                user.getUserName(),
                user.getPassword(),
                user.getActive(), // flaga aktywności użytkownika
                true,             // konto nie wygasło
                true,             // poświadczenia są aktualne
                true,             // konto nie jest zablokowane
                authorities
        );
    }
}
