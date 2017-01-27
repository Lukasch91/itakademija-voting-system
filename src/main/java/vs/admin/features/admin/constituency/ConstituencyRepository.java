package vs.admin.features.admin.constituency;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConstituencyRepository {

	// TODO : ASK PO ABOUT MULTIPLE SELECTS OF HIBERNATE (FETCH BAG)

	private static final String FIND_ALL = "SELECT c FROM Constituency c " + "LEFT JOIN FETCH c.districts cd "
			+ "LEFT JOIN cd.representatives r " + "WHERE c.deletedTime IS NULL " + "ORDER BY c.id";

	private static final String FIND_BY_ID = "SELECT c FROM Constituency c " + "LEFT JOIN FETCH c.districts cd "
			+ "LEFT JOIN cd.representatives r " + "WHERE c.deletedTime IS NULL " + "AND c.id = :id " + "ORDER BY c.id";

	@Autowired
	EntityManager entityManager;

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Constituency> findAllConstituencies() {
		List<Constituency> constituenciesList = entityManager.createQuery(FIND_ALL).getResultList();
		return constituenciesList;
	}

	@Transactional
	public Constituency saveOrUpdate(Constituency constituency) {
		if (constituency.getId() == null) {
			entityManager.persist(constituency);
			return constituency;
		} else {
			Constituency merged = entityManager.merge(constituency);
			entityManager.persist(merged);
			return merged;
		}
	}

	public Constituency findConstituencyById(Integer id) {
		return (Constituency) entityManager.createQuery(FIND_BY_ID).setParameter("id", id).getSingleResult();
	}

	@Transactional
	public void deleteConstituency(Integer id) {
		Constituency constituency = entityManager.find(Constituency.class, id);
		Date date = new Date();
		constituency.setDeletedTime(date);
		entityManager.persist(constituency);
	}

}
