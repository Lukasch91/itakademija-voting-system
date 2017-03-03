package vs;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import vs.admin.Admin;

public class AdminRepositoryUserDetails extends Admin implements UserDetails {

	protected AdminRepositoryUserDetails(Admin admin) {
        super(admin);
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
	}

	@Override
	public String getUsername() {
		return getLoginName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	private static final long serialVersionUID = 5639683223516504866L;

}
