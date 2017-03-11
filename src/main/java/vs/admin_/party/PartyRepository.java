package vs.admin_.party;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vs.admin_.district.DistrictController;

@Repository
public class PartyRepository {

	private static final Logger log = Logger.getLogger(PartyRepository.class.getName());
	
	private static final String FIND_ALL = "Select p FROM Party p "
										 + "WHERE deletedTime IS NULL";

	private static final String FIND_PARTY_BY_ID = "Select p FROM Party p "
												 + "WHERE p.deletedTime IS NULL "
												 + "AND p.id =:id";
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Party> findAllParties() {
		return entityManager.createQuery(FIND_ALL).getResultList();
	}

	@Transactional
	public Party saveOrUpdate(Party party) {
		log.debug("|||||--> Started...");
		if (party.getId() == null) {
			entityManager.persist(party);
			log.debug("|||||--> Finshed! Party was saved.Party name: " + party.getTitle().toString());
			return party;
		} else {
			Party merged = entityManager.merge(party);
			entityManager.persist(merged);
			log.debug("|||||--> Finshed! Party was updated.Party name: " +  party.getTitle().toString());
			return merged;
		}
	}

	public Party findPartyById(Integer id) {
		log.debug("|||||--> Party id: " + id);
		return (Party) entityManager.createQuery(FIND_PARTY_BY_ID).setParameter("id", id);
	}

	@Transactional
	public void deleteParty(Integer id) {
		log.info("|||||--> Start... Party id: " + id);
		Party party = entityManager.find(Party.class, id);
		log.info("|||||--> Founded party by id to delete: " + party.getTitle().toString());
		Date date = new Date();
		party.setDeletedTime(date);
		entityManager.persist(party);
		log.info("|||||--> Finished! ");
	}
}
