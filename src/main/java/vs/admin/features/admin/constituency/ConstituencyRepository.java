package vs.admin.features.admin.constituency;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConstituencyRepository {

	private static final String FIND_ALL = "SELECT c from Constituency c where deleted_date is null";

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
		Constituency constituency = entityManager.find(Constituency.class, id);
		if (constituency != null && constituency.getDeletedTime() == null) {
			return constituency;
		}
		return null;
	}

	@Transactional
	public void deleteConstituency(Integer id) {
		Constituency constituency = entityManager.find(Constituency.class, id);
		Date date = new Date();
		constituency.setDeletedTime(date);
		entityManager.persist(constituency);
	}

}
