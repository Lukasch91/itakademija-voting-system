package vs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vs.admin.features.admin.representative.Representative;
import vs.admin.features.admin.representative.RepresentativeRepository;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	private final RepresentativeRepository representativeRepository;
	
	@Autowired
    public UserRepositoryUserDetailsService(RepresentativeRepository representativeRepository) {
        this.representativeRepository = representativeRepository;
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Representative representative = representativeRepository.findByLoginName(username);
		if (representative == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new UserRepositoryUserDetails(representative);
		
	}

}
