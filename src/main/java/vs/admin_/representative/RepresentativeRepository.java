package vs.admin_.representative;

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
import vs.admin.Admin;
import vs.admin_.party.PartyRepository;
import vs.utils_.password.PasswordService;

@Repository
public class RepresentativeRepository {
	private static final Logger log = Logger.getLogger(RepresentativeRepository.class.getName());
	private static final String FIND_ALL = "SELECT x FROM Representative x";
	private static final String FIND_BY_LOGIN = "SELECT x FROM Representative x WHERE x.loginName = :loginName";

	@Autowired
	private EntityManager em;

	@Autowired
	private PasswordService passwordService;
	
	@SuppressWarnings("unchecked")
	public List<Representative> findAllRepresentatives() {
		log.info("||--> was used");
		return em.createQuery(FIND_ALL).getResultList();
	}

	@Transactional
	public Representative saveOrUpdateRepresentative(Representative representative) {
		log.info("||--> Started...");
		if (representative.getId() == null) {
			em.persist(representative);
			log.info("||--> Finished! Representative added: " + representative.getSurname());
			return representative;
		} else {
			Representative merged = em.merge(representative);
			em.persist(merged);
			log.info("||--> Finished! Representative updated: " + representative.getSurname());
			return merged;
		}
	}

	public Representative findRepresentativeById(Integer id) {
		log.info("||--> was used! Representative id: " + id);
		Representative representative = em.find(Representative.class, id);
		if (representative != null) {
			log.info("||--> was used! Representative surname: " + representative.getSurname());
			return representative;
		} else {
			return null;
		}
	}

	@Transactional
	public void deleteRepresentative(Integer id) {
		Representative representative = em.find(Representative.class, id);
		log.info("||--> was used! Deleted representative: " + representative.getSurname());
		em.remove(representative);
	}
	
	public Representative findByLoginName(String loginName) {
		log.info("||--> was used! Login name for search: " + loginName);
		return (Representative) em.createQuery(FIND_BY_LOGIN).setParameter("loginName", loginName)
				.getSingleResult();
	}
	
	@Transactional
	public boolean changePassword(@CurrentUser Representative representative, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		log.info("||--> was used! Representative surname: " + representative.getSurname());
		String regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[`~!@#$%^&*=-?.])(?=\\S+$).{12,60}$";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(password);
		if (matcher.find()){
			Representative repToChange = (Representative) em.createQuery(FIND_BY_LOGIN).setParameter("loginName", representative.getLoginName()).getSingleResult();
			repToChange.setPassword(passwordService.PassHashing(password));
			return true;
		}
		return false;

	}
	
}
