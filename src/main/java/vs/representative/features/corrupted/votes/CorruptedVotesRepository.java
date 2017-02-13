package vs.representative.features.corrupted.votes;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vs.admin.features.admin.district.District;
import vs.admin.features.party.model.Party;
import vs.representative.features.single.election.SingleElection;

@Repository
public class CorruptedVotesRepository {

	private static final String FIND_ALL = "Select e FROM CorruptedVotes e where deleted_date is null";
	private static final String FIND_BY_DISTRICT_ID = "SELECT e FROM CorruptedVotes e WHERE district IS ";
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<CorruptedVotes> findAllCorruptedVotes() {
		return entityManager.createQuery(FIND_ALL).getResultList();
	}

	@Transactional
	public CorruptedVotes saveOrUpdate(CorruptedVotes corruptedVotes) {
		if (corruptedVotes == null) {
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
	public void deleteCorruptedVotes(Integer id) {
		CorruptedVotes corruptedVotes = entityManager.find(CorruptedVotes.class, id);
		Date date = new Date();
		corruptedVotes.setDeleted_date(date);
		entityManager.persist(corruptedVotes);
	}

	@Transactional
	public void publishCorruptedVotesByDistrictId(Integer district_id) {
		@SuppressWarnings("unchecked")
		List<CorruptedVotes> corruptedVotessPublish = entityManager.createQuery(FIND_BY_DISTRICT_ID + district_id)
				.getResultList();

		for (CorruptedVotes corruptedVotesPublish : corruptedVotessPublish) {
			Date date = new Date();
			corruptedVotesPublish.setPublished_date(date);
			entityManager.persist(corruptedVotesPublish);
		}
	}

	@Transactional
	public void deleteCorruptedVotesByDistrictId(Integer districtId) {
		@SuppressWarnings("unchecked")
		List<CorruptedVotes> corruptedVotessDelete = entityManager.createQuery(FIND_BY_DISTRICT_ID + districtId)
				.getResultList();

		for (CorruptedVotes corruptedVotesDelete : corruptedVotessDelete) {
			Date date = new Date();
			corruptedVotesDelete.setDeleted_date(date);
			
			entityManager.persist(corruptedVotesDelete);
		}
	}
}
