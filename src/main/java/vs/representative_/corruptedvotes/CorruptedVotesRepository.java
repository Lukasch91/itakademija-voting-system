package vs.representative_.corruptedvotes;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CorruptedVotesRepository {

	private static final String FIND_ALL = "Select e FROM CorruptedVotes e where deleted_date is null";

	private static final String FIND_BY_TYPE = "SELECT e FROM CorruptedVotes e WHERE deleted_date is null AND typeMulti IS ";
	private static final String FIND_BY_CONSTITUENCY_ID = "SELECT sum(c.votes) from CorruptedVotes c LEFT JOIN c.district cd WHERE cd.constituencyId = :id and c.typeMulti ='false'";
	private static final String FIND_FIND_BY_DISTRICT_ID = "SELECT c FROM CorruptedVotes c left join c.district cd WHERE cd.id =:id and c.typeMulti ='false'";
	private static final String FIND_FIND_BY_DISTRICT_ID_AND_TYPE = "SELECT c FROM CorruptedVotes c left join c.district cd WHERE cd.id =:id and c.typeMulti =:typeMulti";
	
	private static final String FIND_ALL_MULTI_INVALID_VOTES = "SELECT sum(c.votes) FROM CorruptedVotes c WHERE c.published_date is not null and c.deleted_date is null and c.typeMulti='true'";
	private static final String FIND_MULTI_ALL_BY_CONSTITUENCY_ID = "SELECT sum(c.votes) from CorruptedVotes c LEFT JOIN c.district cd WHERE cd.constituencyId = :id and c.typeMulti ='true'";
	private static final String FIND_MULT_INVALID_VOTES_IN_DISTRICTS ="SELECT c.votes from CorruptedVotes c LEFT JOIN c.district cd where cd.id = :id and c.typeMulti='true'";
	
	
	
	
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<CorruptedVotes> findAllCorruptedVotes() {
		return entityManager.createQuery(FIND_ALL).getResultList();
	}

	public Long getCorruptedVotesInConstituency(Integer id) {
		if (entityManager.createQuery(FIND_BY_CONSTITUENCY_ID).setParameter("id", id).getResultList().isEmpty()) {
			return 0L;
		} else
			return (Long) entityManager.createQuery(FIND_BY_CONSTITUENCY_ID).setParameter("id", id).getSingleResult();
	}
	
	public Long getMultiCorruptedVotesInDistrict(Integer id) {
		if (entityManager.createQuery(FIND_MULT_INVALID_VOTES_IN_DISTRICTS).setParameter("id", id).getResultList().isEmpty()) {
			return 0L;
		} else
			return (Long) entityManager.createQuery(FIND_MULT_INVALID_VOTES_IN_DISTRICTS).setParameter("id", id).getSingleResult();
	}

	public Long getMultiCorruptedVotesInConstituency(Integer id) {
		if (entityManager.createQuery(FIND_MULTI_ALL_BY_CONSTITUENCY_ID).setParameter("id", id).getResultList()
				.isEmpty()) {
			return 0L;
		} else
			return (Long) entityManager.createQuery(FIND_MULTI_ALL_BY_CONSTITUENCY_ID).setParameter("id", id)
					.getSingleResult();
	}

	public Long getCorruptedVotesByDistrict(Integer id) {
		if (entityManager.createQuery(FIND_FIND_BY_DISTRICT_ID).setParameter("id", id).getResultList().isEmpty()) {
			return 0L;
		} else {
			return (Long) entityManager.createQuery(FIND_FIND_BY_DISTRICT_ID).setParameter("id", id).getSingleResult();
		}
	}

	public Long getAllMultiElectionInvalidVotes() {
		if (entityManager.createQuery(FIND_ALL_MULTI_INVALID_VOTES).getResultList().isEmpty()) {
			return 0L;
		} else {
			return (Long) entityManager.createQuery(FIND_ALL_MULTI_INVALID_VOTES).getSingleResult();
		}
	}

	@Transactional
	public CorruptedVotes saveOrUpdate(CorruptedVotes corruptedVotes) {
		if (corruptedVotes.getId() == null) {
			Date corruptedEnteredDate = new Date();
			corruptedVotes.setEntered_date(corruptedEnteredDate);
			entityManager.persist(corruptedVotes);
			return corruptedVotes;
		} else {
			CorruptedVotes merged = entityManager.merge(corruptedVotes);
			entityManager.persist(merged);
			return merged;
		}
	}

	public CorruptedVotes findCorruptedVotesById(Integer id) {
		CorruptedVotes corruptedVotes = entityManager.find(CorruptedVotes.class, id);
		if ((corruptedVotes != null) && (corruptedVotes.getDeleted_date() == null)) {
			return corruptedVotes;
		}
		return null;
	}

	@Transactional
	public List<CorruptedVotes> GetCorruptedVotesByType(Boolean typeMulti) {
		@SuppressWarnings("unchecked")
		List<CorruptedVotes> corruptedVotessType = entityManager.createQuery(FIND_BY_TYPE + typeMulti).getResultList();
		return corruptedVotessType;

	}

	@Transactional
	public void deleteCorruptedVotes(Integer id) {
		CorruptedVotes corruptedVotes = entityManager.find(CorruptedVotes.class, id);
		Date date = new Date();
		corruptedVotes.setDeleted_date(date);
		entityManager.persist(corruptedVotes);
	}

	@Transactional
	public void publishCorruptedVotesByDistrictId(Integer districtId, Boolean typeMulti) {
		@SuppressWarnings("unchecked")
		List<CorruptedVotes> corruptedVotessPublish = entityManager.createQuery(FIND_FIND_BY_DISTRICT_ID_AND_TYPE)
				.setParameter("id", districtId).setParameter("typeMulti", typeMulti).getResultList();

		for (CorruptedVotes corruptedVotesPublish : corruptedVotessPublish) {
			Date date = new Date();
			corruptedVotesPublish.setPublished_date(date);
			corruptedVotesPublish.setTypeMulti(typeMulti);
			entityManager.persist(corruptedVotesPublish);
		}
	}

	@Transactional
	public void deleteCorruptedVotesByDistrictId(Integer districtId, Boolean typeMulti) {
		@SuppressWarnings("unchecked")
		List<CorruptedVotes> corruptedVotessDelete = entityManager.createQuery(FIND_FIND_BY_DISTRICT_ID_AND_TYPE)
				.setParameter("id", districtId).setParameter("typeMulti", typeMulti).getResultList();

		for (CorruptedVotes corruptedVotesDelete : corruptedVotessDelete) {
			Date date = new Date();
			corruptedVotesDelete.setDeleted_date(date);
			corruptedVotesDelete.setTypeMulti(typeMulti);
			entityManager.persist(corruptedVotesDelete);
		}
	}
}
