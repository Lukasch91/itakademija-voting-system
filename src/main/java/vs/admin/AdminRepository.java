package vs.admin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vs.CurrentUser;
import vs.utils_.password.PasswordService;

@Repository
public class AdminRepository {
	private static final String FIND_ALL = "SELECT x FROM Admin x";
	private static final String FIND_BY_ADMIN_LOGIN = "SELECT x FROM Admin x WHERE x.loginName = :loginName";
	private static final Logger log = Logger.getLogger(AdminRepository.class.getName());

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
		log.debug("Admin save or update started");
		if (admin.getId() == null) {
			em.persist(admin);
			log.debug("Admin was saved! Admin id = " + admin.getId());
			return admin;
		} else {
			Admin merged = em.merge(admin);
			em.persist(merged);
			log.debug("Admin was updated! Admin id = " + admin.getId());
			return admin;
		}
	}

	@Transactional
	public Admin findByLoginName(String loginName) {
		log.debug("was used. Login Name: " + loginName);
		return (Admin) em.createQuery(FIND_BY_ADMIN_LOGIN).setParameter("loginName", loginName).getSingleResult();
	}

	@Transactional
	public boolean changePassword(@CurrentUser Admin admin, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		log.debug("started...");
		String regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,60}$";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(password);

		if (matcher.find()) {
			Admin adminToChange = (Admin) em.createQuery(FIND_BY_ADMIN_LOGIN)
					.setParameter("loginName", admin.getLoginName()).getSingleResult();
			adminToChange.setPassword(passwordService.PassHashing(password));
			log.debug("finished, password is safe!");
			return true;
		}
		log.debug("finished, password not changed,  not safe!");
		return false;
	}

}
