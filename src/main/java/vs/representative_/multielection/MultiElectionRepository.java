package vs.representative_.multielection;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MultiElectionRepository {

	private static final String FIND_ALL = "Select e FROM MultiElection e where deleted_date is null";
	private static final String FIND_BY_DISTRICT_ID = "SELECT e FROM MultiElection e WHERE district IS ";
	private static final String FIND_VOTES_FOR_PARTY_BY_PARTY_ID = "SELECT sum(m.votes) from MultiElection m LEFT JOIN m.party mp "
			+ "WHERE mp.id =:id AND m.published_date IS NOT NULL AND m.deleted_date is null AND mp.deletedTime is null";

	private static final String FIND_ALL_MULTI_ELECTION_PUBLISHED_VOTES_BY_CONSTITUENCY = "SELECT sum(m.votes) from MultiElection m LEFT JOIN m.party mp left join m.district md "
			+ "WHERE mp.deletedTime is null AND m.published_date IS NOT NULL AND m.deleted_date is null and md.constituencyId=:id";

	private static final String FIND_ALL_MULTI_ELECTION_PUBLISHED_VOTES = "SELECT sum(m.votes) from MultiElection m LEFT JOIN m.party mp left join m.district md "
			+ "WHERE mp.deletedTime is null AND m.published_date IS NOT NULL AND m.deleted_date is null";

	private static final String COUNT_PUBLISHED_DISTRICT_RESULTS = "SELECT count(distinct md) FROM MultiElection m "
			+ "LEFT JOIN m.district md " + "WHERE m.deleted_date IS NULL "
			+ "AND m.published_date IS NOT NULL and md.constituencyId=:id";

	private static final String GET_ALL_VOTES_BY_CONSTITUENCY_ID = "SELECT sum(m.votes) FROM MultiElection m "
			+ "LEFT JOIN m.district md " + "WHERE m.deleted_date IS NULL "
			+ "AND m.published_date IS NOT NULL and md.constituencyId=:id";

	private static final String GET_PARTY_VOTES_BY_CONSTITUENCY_ID = "SELECT sum(m.votes) FROM MultiElection m "
			+ "LEFT JOIN m.district md Left join m.party mp WHERE m.deleted_date IS NULL "
			+ "AND m.published_date IS NOT NULL and md.constituencyId=:consId and mp.deletedTime is null and mp.id=:partyId";

	private static final String GET_VOTES_IN_DISTRICT = "SELECT sum(m.votes) FROM MultiElection m "
			+ " LEFT JOIN m.district md WHERE m.deleted_date is null AND m.published_date IS NOT NULL AND md.id =:id AND md.deletedTime is null";

	private static final String GET_VOTES_IN_BY_DISTRICT_ID = "SELECT m.votes FROM MultiElection m "
			+ " LEFT JOIN m.district md LEFT JOIN m.party mp WHERE m.deleted_date is null AND m.published_date IS NOT NULL AND md.id=:disId AND mp.id=:partyId AND md.deletedTime is null";
	
	private static final String GET_UPDATED_DATE = "SELECT m.published_date FROM MultiElection m "
			+ " LEFT JOIN m.district md WHERE m.deleted_date is null AND m.published_date IS NOT NULL AND md.id=:disId AND md.deletedTime is null";

	private static final String GET_ALL_MULTI_VOTES = "SELECT sum(m.votes) FROM MultiElection m "
			+ "LEFT JOIN m.district md  WHERE "
			+ " m.deleted_date IS NULL AND m.published_date IS NOT NULL and md.deletedTime is null";

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<MultiElection> findAllElection() {
		return entityManager.createQuery(FIND_ALL).getResultList();
	}

	public Long getSumOfAllVotes() {
		if (entityManager.createQuery(GET_ALL_MULTI_VOTES).getResultList().isEmpty()) {
			return 0L;
		} else {

			return (Long) entityManager.createQuery(GET_ALL_MULTI_VOTES).getSingleResult();
		}
	}
	
	public Timestamp getPublishedDate(Integer disId) {
		if (entityManager.createQuery(GET_UPDATED_DATE).setParameter("disId", disId).getResultList().isEmpty()) {
			return null;
		} else {

			return  (Timestamp) entityManager.createQuery(GET_UPDATED_DATE).setParameter("disId", disId)
					.getSingleResult();
		}
	}


	public Long getAllPublishedVotes() {
		if (entityManager.createQuery(FIND_ALL_MULTI_ELECTION_PUBLISHED_VOTES).getResultList().isEmpty()) {
			return 0L;
		} else {

			return (Long) entityManager.createQuery(FIND_ALL_MULTI_ELECTION_PUBLISHED_VOTES).getSingleResult();
		}
	}

	public Long getVotesOfPartyByConsId(Integer consId, Integer partyId) {
		if (entityManager.createQuery(GET_PARTY_VOTES_BY_CONSTITUENCY_ID).setParameter("consId", consId)
				.setParameter("partyId", partyId).getResultList().isEmpty()) {
			return 0L;
		} else {

			return (Long) entityManager.createQuery(GET_PARTY_VOTES_BY_CONSTITUENCY_ID).setParameter("consId", consId)
					.setParameter("partyId", partyId).getSingleResult();
		}
	}

	public Long getVotesOfPartyByDistrictId(Integer disId, Integer partyId) {
		if (entityManager.createQuery(GET_VOTES_IN_BY_DISTRICT_ID).setParameter("disId", disId)
				.setParameter("partyId", partyId).getResultList().isEmpty()) {
			return 0L;
		} else {

			return (Long) entityManager.createQuery(GET_VOTES_IN_BY_DISTRICT_ID).setParameter("disId", disId)
					.setParameter("partyId", partyId).getSingleResult();
		}
	}

	public Long getCountDistricts(Integer consId) {
		if (entityManager.createQuery(COUNT_PUBLISHED_DISTRICT_RESULTS).setParameter("id", consId).getResultList()
				.isEmpty()) {
			return 0L;
		} else {

			return (Long) entityManager.createQuery(COUNT_PUBLISHED_DISTRICT_RESULTS).setParameter("id", consId)
					.getSingleResult();
		}
	}

	public Long getNumberOfVotesInDistrict(Integer disId) {
		if (entityManager.createQuery(GET_VOTES_IN_DISTRICT).setParameter("id", disId).getResultList().isEmpty()) {
			return 0L;
		} else {

			return (Long) entityManager.createQuery(GET_VOTES_IN_DISTRICT).setParameter("id", disId).getSingleResult();
		}
	}

	public Long getSumOfValidVotes(Integer consId) {
		if (entityManager.createQuery(GET_ALL_VOTES_BY_CONSTITUENCY_ID).setParameter("id", consId).getResultList()
				.isEmpty()) {
			return 0L;
		} else {

			return (Long) entityManager.createQuery(GET_ALL_VOTES_BY_CONSTITUENCY_ID).setParameter("id", consId)
					.getSingleResult();
		}
	}

	public Long getVotesOfCandidate(Integer id) {
		if (entityManager.createQuery(FIND_VOTES_FOR_PARTY_BY_PARTY_ID).setParameter("id", id).getResultList()
				.isEmpty()) {
			return 0L;
		} else {

			return (Long) entityManager.createQuery(FIND_VOTES_FOR_PARTY_BY_PARTY_ID).setParameter("id", id)
					.getSingleResult();
		}
	}

	public Long getAllPublishedVotes(Integer id) {
		if (entityManager.createQuery(FIND_ALL_MULTI_ELECTION_PUBLISHED_VOTES_BY_CONSTITUENCY).setParameter("id", id)
				.getResultList().isEmpty()) {
			return 0L;
		} else {

			return (Long) entityManager.createQuery(FIND_ALL_MULTI_ELECTION_PUBLISHED_VOTES_BY_CONSTITUENCY)
					.setParameter("id", id).getSingleResult();
		}
	}

	@Transactional
	public void saveOrUpdate(List<MultiElection> multiElections) {
		for (MultiElection multiElection : multiElections) {
			if (multiElection.getId() == null) {
				Date multiEnteredDate = new Date();
				multiElection.setEntered_date(multiEnteredDate);
				entityManager.persist(multiElection);
			} else {
				MultiElection merged = entityManager.merge(multiElection);
				entityManager.persist(merged);
			}
		}
	}

	public MultiElection findMultiElectionById(Integer id) {
		MultiElection multiElection = entityManager.find(MultiElection.class, id);
		if ((multiElection != null) && (multiElection.getDeleted_date() == null)) {
			return multiElection;
		}
		return null;
	}

	public List<MultiElection> findMultiElectionByDistrictId(Integer district_id) {
		@SuppressWarnings("unchecked")
		List<MultiElection> multiElectionsPublish = entityManager
				.createQuery(FIND_BY_DISTRICT_ID + district_id + "AND deleted_date is null").getResultList();
		return multiElectionsPublish;
	}

	@Transactional
	public void deleteMultiElection(Integer id) {
		MultiElection multiElection = entityManager.find(MultiElection.class, id);
		Date date = new Date();
		multiElection.setDeleted_date(date);
		entityManager.persist(multiElection);
	}

	@Transactional
	public void publishMultiElectionResultsByDistrictId(Integer district_id) {
		@SuppressWarnings("unchecked")
		List<MultiElection> multiElectionsPublish = entityManager.createQuery(FIND_BY_DISTRICT_ID + district_id)
				.getResultList();

		for (MultiElection multiElectionPublish : multiElectionsPublish) {
			Date date = new Date();
			multiElectionPublish.setPublished_date(date);
			entityManager.persist(multiElectionPublish);
		}
	}

	@Transactional
	public void deleteMultiElectionResultsByDistrictId(Integer districtId) {
		@SuppressWarnings("unchecked")
		List<MultiElection> multiElectionsDelete = entityManager.createQuery(FIND_BY_DISTRICT_ID + districtId)
				.getResultList();

		for (MultiElection multiElectionDelete : multiElectionsDelete) {
			Date date = new Date();
			multiElectionDelete.setDeleted_date(date);
			entityManager.persist(multiElectionDelete);
		}
	}
}
