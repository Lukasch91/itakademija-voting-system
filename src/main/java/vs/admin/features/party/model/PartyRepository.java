package vs.admin.features.party.model;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class PartyRepository {

	private static final String FIND_ALL = "Select p FROM Party p where deletedTime is null";

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Party> findAllParties() {
		return entityManager.createQuery(FIND_ALL).getResultList();
	}

	@Transactional
	public Party saveOrUpdate(Party party) {
		if (party.getId() == null) {
			entityManager.persist(party);
			return party;
		} else {
			Party merged = entityManager.merge(party);
			entityManager.persist(merged);
			return merged;
		}
	}
	
	public Party findPartyById(Integer id) {
		Party party = entityManager.find(Party.class, id);
		if ((party.getDeletedTime() == null)&&(party != null)) {
			return party;
		}
		return null;
	}
	
	@Transactional
	public void deleteParty(Integer id) {
		Party party = entityManager.find(Party.class, id);
		Date date = new Date();
		party.setDeletedTime(date);
		entityManager.persist(party);
	}
}
