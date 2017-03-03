package vs.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vs.admin_.representative.Representative;

@Repository
public class AdminRepository {
	private static final String FIND_ALL = "SELECT x FROM Admin x";
	private static final String FIND_BY_ADMIN_LOGIN = "SELECT x FROM Admin x WHERE x.loginName = :loginName";
	
	@Autowired
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public List<Admin> findAdmin() {
		return em.createQuery(FIND_ALL).getResultList();
	}
	@Transactional
	public Admin findByLoginName(String loginName) {
		return (Admin) em.createQuery(FIND_BY_ADMIN_LOGIN).setParameter("loginName", loginName)
				.getSingleResult();
	}
}
