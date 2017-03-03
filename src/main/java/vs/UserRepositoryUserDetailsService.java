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
		Representative representative = representativeRepository.findByLoginName(username);		
		if (representative == null) {
			Admin admin = adminRepository.findByLoginName(username);
			if (admin == null) {
				throw new UsernameNotFoundException("Could not find user");
			} else {
				System.out.println("ADMIN USER - " + admin.getLoginName());
				return new AdminRepositoryUserDetails(admin);			
			}
		} else {
			System.out.println("REP USER - " + representative.getLoginName());
			return new UserRepositoryUserDetails(representative);
		}
	
	}
	
	
/*	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByLoginName(username);
		if (admin == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new AdminRepositoryUserDetails(admin);
	}*/
	
/*	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Representative representative = representativeRepository.findByLoginName(username);
		if (representative == null) {
				throw new UsernameNotFoundException("Could not find user");
		}
	    return new UserRepositoryUserDetails(representative);
	}*/

}
