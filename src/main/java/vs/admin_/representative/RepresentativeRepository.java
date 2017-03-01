package vs.admin_.representative;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepresentativeRepository {
	private static final String FIND_ALL = "SELECT x FROM Representative x";
	private static final String FIND_BY_LOGIN = "SELECT x FROM Representative x WHERE x.loginName = :loginName";

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Representative> findAllRepresentatives() {
		return em.createQuery(FIND_ALL).getResultList();
	}

	@Transactional
	public Representative saveOrUpdateRepresentative(Representative representative) {
		if (representative.getId() == null) {
			em.persist(representative);
			return representative;
		} else {
			Representative merged = em.merge(representative);
			em.persist(merged);
			return merged;
		}
	}

	public Representative findRepresentativeById(Integer id) {
		Representative representative = em.find(Representative.class, id);
		if (representative != null) {
			return representative;
		} else {
			return null;
		}
	}

	@Transactional
	public void deleteRepresentative(Integer id) {
		Representative representative = em.find(Representative.class, id);
		em.remove(representative);
	}
	
	public Representative findByLoginName(String loginName) {
		return (Representative) em.createQuery(FIND_BY_LOGIN).setParameter("loginName", loginName)
				.getSingleResult();
	}
}