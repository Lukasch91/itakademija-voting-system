package vs.admin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vs.CurrentUser;
import vs.utils_.password.PasswordService;

@Repository
public class AdminRepository {
	private static final String FIND_ALL = "SELECT x FROM Admin x";
	private static final String FIND_BY_ADMIN_LOGIN = "SELECT x FROM Admin x WHERE x.loginName = :loginName";
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private PasswordService passwordService;
	
	@SuppressWarnings("unchecked")
	public List<Admin> findAdmin() {
		return em.createQuery(FIND_ALL).getResultList();
	}
	
	@Transactional
	public Admin saveOrUpdateAdmin(Admin admin) {
		if (admin.getId() == null) {
			em.persist(admin);
			return admin;
		} else {
			Admin merged = em.merge(admin);
			em.persist(merged);
			return admin;
		}
	}
	
	@Transactional
	public Admin findByLoginName(String loginName) {
		return (Admin) em.createQuery(FIND_BY_ADMIN_LOGIN).setParameter("loginName", loginName)
				.getSingleResult();
	}
	
	@Transactional
	public void changePassword(@CurrentUser Admin admin, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Admin adminToChange = (Admin) em.createQuery(FIND_BY_ADMIN_LOGIN).setParameter("loginName", admin.getLoginName()).getSingleResult();
		adminToChange.setPassword(passwordService.PassHashing(password));
	}
	
}
