package vs.admin_.constituency;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConstituencyRepository {

	private static final Logger log = Logger.getLogger(ConstituencyRepository.class.getName());
	
	private static final String FIND_ALL = "SELECT DISTINCT c FROM Constituency c " + "LEFT JOIN FETCH c.districts cd "
			+ "LEFT JOIN cd.representatives r " + "WHERE c.deletedTime IS NULL " + "ORDER BY c.id";

	private static final String FIND_BY_ID = "SELECT DISTINCT c  FROM Constituency c " + "LEFT JOIN FETCH c.districts cd "
			+ "LEFT JOIN cd.representatives r " + "WHERE c.deletedTime IS NULL " + "AND c.id = :id " + "ORDER BY c.id";
	
	private static final String COUNT_ALL_CONSTITUENCIES = "SELECT COUNT(c) FROM Constituency c where c.deletedTime IS NULL";

	@Autowired
	EntityManager entityManager;

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Constituency> findAllConstituencies() {
		log.debug("ConstituencyRepository - findAllConstituencies was used");
		List<Constituency> constituenciesList = entityManager.createQuery(FIND_ALL).getResultList();
		return constituenciesList;
	}

	@Transactional
	public Constituency saveOrUpdate(Constituency constituency) {
		log.debug("ConstituencyRepository - saveOrUpdate was started...");
		if (constituency.getId() == null) {
			entityManager.persist(constituency);
			log.debug("ConstituencyRepository - saveOrUpdate finished! Constituency saved.");
			return constituency;
		} else {
			Constituency merged = entityManager.merge(constituency);
			entityManager.persist(merged);
			log.debug("ConstituencyRepository - saveOrUpdate finished! Constituency updated.");
			return merged;
		}
	}

	public Long countAllConstituencies() {
		log.debug("ConstituencyRepository - countAllConstituencies started...");
		if (entityManager.createQuery(COUNT_ALL_CONSTITUENCIES).getResultList().isEmpty()) {
			log.debug("ConstituencyRepository - countAllConstituenciesn (if stm) finished!");
			return 0L;
		} else
			log.debug("ConstituencyRepository - countAllConstituencies (else stm) finished!");
			return (Long) entityManager.createQuery(COUNT_ALL_CONSTITUENCIES).getSingleResult();
	}
	
	public Constituency findConstituencyById(Integer id) {
		log.debug("ConstituencyRepository - findConstituencyById was used! Id: " +id);
		return (Constituency) entityManager.createQuery(FIND_BY_ID).setParameter("id", id).getSingleResult();
	}

	@Transactional
	public void deleteConstituency(Integer id) {
		log.debug("ConstituencyRepository - deleteConstituency started! Id: " +id);
		Constituency constituency = entityManager.find(Constituency.class, id);
		Date date = new Date();
		constituency.setDeletedTime(date);
		entityManager.persist(constituency);
		log.debug("ConstituencyRepository - deleteConstituency finished!");
	}

}
