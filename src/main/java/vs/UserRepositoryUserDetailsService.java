package vs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vs.admin.Admin;
import vs.admin.AdminRepository;
import vs.admin_.representative.Representative;
import vs.admin_.representative.RepresentativeRepository;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	private RepresentativeRepository representativeRepository;
	private AdminRepository adminRepository;
	
	@Autowired
    public UserRepositoryUserDetailsService(RepresentativeRepository representativeRepository, AdminRepository adminRepository) {
        this.representativeRepository = representativeRepository;
        this.adminRepository = adminRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {				
		try  {
			Representative representative = representativeRepository.findByLoginName(username);
			return new UserRepositoryUserDetails(representative);
		} catch (Exception e) {
			try {
				Admin admin = adminRepository.findByLoginName(username);
				return new AdminRepositoryUserDetails(admin);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
			System.out.println(e.getMessage());
		}
		throw new UsernameNotFoundException("Could not find user");			
	}
}
