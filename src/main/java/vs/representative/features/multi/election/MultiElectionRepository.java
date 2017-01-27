package vs.representative.features.multi.election;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vs.admin.features.party.model.Party;



@Repository
public class MultiElectionRepository {

	private static final String FIND_ALL = 
			"Select e FROM MultiElection e where deleted_date is null";
	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public  List<MultiElection> findAllElection() {
		return entityManager.createQuery(FIND_ALL).getResultList();
	}
	
	@Transactional
	public MultiElection saveOrUpdate(MultiElection multiElection) {
		if (multiElection.getId() == null) {
			entityManager.persist(multiElection);
			return multiElection;
		} else {
			MultiElection merged = entityManager.merge(multiElection);
			entityManager.persist(merged);
			return merged;
		}
	}
	
	public MultiElection findMultiElectionById(Integer id) {
		MultiElection multiElection = entityManager.find(MultiElection.class, id);
		if ((multiElection != null)&&(multiElection.getDeleted_date() == null)) {
			return multiElection;
		}
		return null;
	}
	
	@Transactional
	public void deleteMultiElection(Integer id) {
		MultiElection multiElection = entityManager.find(MultiElection.class, id);
		Date date = new Date();
		multiElection.setDeleted_date(date);
		entityManager.persist(multiElection);
	}
}
