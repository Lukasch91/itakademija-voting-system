package vs.representative_.singleelection;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SingleElectionRepository {

	private static final String FIND_ALL = "SELECT x FROM SingleElection x WHERE single_deleted_date IS NULL";

	private static final String FIND_BY_DISTRICT_ID = "SELECT s FROM SingleElection s WHERE singleDistrict is ";

	private static final String FIND_VOTES_BY_CANDIDATE_ID = "SELECT sum(s.singleVotes) FROM SingleElection s LEFT Join s.singleCandidate ss "
			+ "WHERE ss.candidateID = :id AND s.singleDeletedDate is null AND ss.candidateDeletedDate is null "
			+ "AND s.singlePublishedDate IS NOT NULL";

	private static final String COUNT_PUBLISHED_DISTRICT_RESULTS = "SELECT distinct count(distinct s.singleDistrict) FROM SingleElection s "
			+ "LEFT JOIN s.singleDistrict d  " + "WHERE s.singleDeletedDate IS NULL "
			+ "AND s.singlePublishedDate IS NOT NULL and d.constituencyId=:id and d.deletedTime is null ";

	private static final String SUM_PUBLISHED_VOTES_IN_DISTRICTS_BY_CONSTITUENCY = "SELECT sum(s.singleVotes) FROM SingleElection s "
			+ "LEFT JOIN s.singleCandidate c left join c.candidateConstituency cc "
			+ "WHERE s.singleDeletedDate IS NULL " + "AND s.singlePublishedDate IS NOT NULL and cc.id = :id ";

	// private static final String SUM_PUBLISHED_VOTES_IN_DISTRICT = "SELECT
	// sum(s.singleVotes) FROM SingleElection s "
	// + "LEFT JOIN s.singleCandidate sc left join sc.singleDistrict cd " +
	// "WHERE s.singleDeletedDate IS NULL "
	// + "AND s.singlePublishedDate IS NOT NULL and cd.id = :id and
	// sc.singleConstituency is not null ";
	//
	//
	// private static final String GET_NUMBER_OF_VOTERS_WHO_VOTED = "SELECT
	// sum(s.singleVotes) FROM SingleElection s "
	// + "LEFT JOIN s.singleDistrict sd " + "LEFT JOIN s.singleCandidate sc "
	// + "WHERE s.singleDeletedDate IS NULL "
	// + "AND s.singlePublishedDate IS NOT NULL and sd.constituencyId=:id AND
	// sc.candidateName !='NOVOTES'";

	private static final String GET_PUBLISHED_VOTES_IN_DISTRICT = "SELECT sum(s.singleVotes) FROM SingleElection s "
			+ "LEFT JOIN s.singleCandidate c left join s.singleDistrict cd left join c.candidateConstituency cdc  "
			+ "WHERE s.singleDeletedDate IS NULL "
			+ "AND s.singlePublishedDate IS NOT NULL and cdc is not null and cd.id = :id";

	private static final String GET_RESULTS_BY_DISTRICT_ID = "SELECT s FROM SingleElection s "
			+ "RIGHT OUTER JOIN s.singleCandidate c left join s.singleDistrict cd left join c.candidateConstituency cdc  "
			+ "WHERE s.singleDeletedDate IS NULL "
			+ "AND s.singlePublishedDate IS NOT NULL and cdc is not null and cd.id = :id";

	private static final String GET_DISTRICTS_PUBLISHED_VOTES_IN_DISTRICT = "SELECT sum(s.singleVotes) FROM SingleElection s "
			+ "LEFT JOIN s.singleDistrict sd LEFT JOIN s.singleCandidate sc  WHERE sd.id = :id "
			+ "AND s.singleDeletedDate IS NULL AND s.singlePublishedDate IS NOT NULL and sc.candidateConstituency is not null";

	private static final String GET_SINGLE_VOTES_BY_CANDIDATE_ID = "Select s.singleVotes FROM SingleElection s LEFT JOIN s.singleDistrict sd LEFT JOIN s.singleCandidate sc WHERE s.singleDeletedDate IS NULL "
			+ "AND s.singlePublishedDate IS NOT NULL and sc.candidateID=:candidateId AND sd.id = :districtId";

	private static final String COUNT_ALL_VOTES = "SELECT sum(s.singleVotes) FROM SingleElection s "
			+ "LEFT JOIN s.singleDistrict sd  WHERE "
			+ " s.singleDeletedDate IS NULL AND s.singlePublishedDate IS NOT NULL and sd.deletedTime is null";

	private static final String GET_UPDATED_DATE = "SELECT m.published_date FROM MultiElection m "
			+ " LEFT JOIN m.district md WHERE m.deleted_date is null AND m.published_date IS NOT NULL AND md.id=:disId AND md.deletedTime is null";

	@Autowired
	private EntityManager em;

	public Timestamp getPublishedDate(Integer disId) {
		if (em.createQuery(GET_UPDATED_DATE).setParameter("disId", disId).getResultList().isEmpty()) {
			return null;
		} else {

			return (Timestamp) em.createQuery(GET_UPDATED_DATE).setParameter("disId", disId).getSingleResult();
		}
	}

	public Long getVotesOfCandidate(Integer candidateId, Integer districtId) {
		if (em.createQuery(GET_SINGLE_VOTES_BY_CANDIDATE_ID).setParameter("candidateId", candidateId)
				.setParameter("districtId", districtId).getResultList().get(0) == null) {
			return 0L;
		} else {
			return Long.parseLong((String) em.createQuery(GET_SINGLE_VOTES_BY_CANDIDATE_ID)
					.setParameter("candidateId", candidateId).setParameter("districtId", districtId).getSingleResult());
		}
	}

	public Long getAllVotes() {
		if (em.createQuery(COUNT_ALL_VOTES).getResultList().isEmpty()) {
			return 0L;
		} else {
			return (Long) Long.parseLong((String) em.createQuery(COUNT_ALL_VOTES).getSingleResult());
		}
	}

	@SuppressWarnings("unchecked")
	public List<SingleElection> findAllSingleElectionResults() {
		return em.createQuery(FIND_ALL).getResultList();
	}

	@Transactional
	public void saveSingleElection(List<SingleElection> singleElections) {

		for (SingleElection singleElection : singleElections) {
			if (singleElection.getSingleId() == null) {
				Date singleEnteredDate = new Date();
				singleElection.setSingleEnteredDate(singleEnteredDate);
				em.persist(singleElection);
			}
			// return null; //return type made to void
		}
		/* ============UpdateDisabled==== */
		// else {
		// SingleElection merged = em.merge(singleElection);
		// em.persist(merged);
		// return merged;
		// return null;
		// }
	}

	public SingleElection findSingleElectionById(Integer id) {
		SingleElection singleElection = em.find(SingleElection.class, id);
		if ((singleElection != null) && (singleElection.getSingleDeletedDate() == null)) {
			return singleElection;
		} else {
			return null;
		}
	}

	@Transactional
	public void deleteSingleElectionById(Integer id) {
		SingleElection singleElection = em.find(SingleElection.class, id);
		Date date = new Date();
		singleElection.setSingleDeletedDate(date);
		em.persist(singleElection);
	}

	@Transactional
	public void publishSingleElectionResultsByDistrictId(Integer districtId) {
		@SuppressWarnings("unchecked")
		List<SingleElection> singleElectionsPublish = em.createQuery(FIND_BY_DISTRICT_ID + districtId).getResultList();

		for (SingleElection singleElectionPublish : singleElectionsPublish) {
			Date date = new Date();
			singleElectionPublish.setSinglePublishedDate(date);
			em.persist(singleElectionPublish);
		}
	}

	@Transactional
	public void deleteSingleElectionResultsByDistrictId(Integer districtId) {
		@SuppressWarnings("unchecked")
		List<SingleElection> singleElectionsDelete = em.createQuery(FIND_BY_DISTRICT_ID + districtId).getResultList();

		for (SingleElection singleElectionDelete : singleElectionsDelete) {
			Date date = new Date();
			singleElectionDelete.setSingleDeletedDate(date);
			em.persist(singleElectionDelete);
		}
	}

	@Transactional
	public void deleteSingleElectionResultsByDistrictIdREAL(Integer districtId) {
		@SuppressWarnings("unchecked")
		List<SingleElection> singleElectionsDeleteREAL = em.createQuery(FIND_BY_DISTRICT_ID + districtId)
				.getResultList();

		for (SingleElection singleElectionDeleteREAL : singleElectionsDeleteREAL) {

			em.remove(singleElectionDeleteREAL);
		}
	}

	public Long getNumberOfPublishedResults(Integer id) {
		return (Long) em.createQuery(COUNT_PUBLISHED_DISTRICT_RESULTS).setParameter("id", id).getSingleResult();
	}

	public Long getSumOfPublishedVotes(Integer id) {

		if (em.createQuery(SUM_PUBLISHED_VOTES_IN_DISTRICTS_BY_CONSTITUENCY).setParameter("id", id).getResultList()
				.get(0) == null) {
			return 0L;
		} else {

			return Long.parseLong((String) em.createQuery(SUM_PUBLISHED_VOTES_IN_DISTRICTS_BY_CONSTITUENCY)
					.setParameter("id", id).getSingleResult());
		}
	}

	// public Long getCandidatePublishedVotes(Integer id) {
	// return (Long)
	// em.createQuery(CANDIDATE_PUBLISHED_VOTES_IN_DISTRICTS_BY_CONSTITUENCY).setParameter("id",
	// id)
	// .getSingleResult();
	// }

	public Long getNumberOfpublishedVotes(Integer id) {
		if (em.createQuery(GET_PUBLISHED_VOTES_IN_DISTRICT).setParameter("id", id).getResultList().get(0) == null) {
			return 0L;
		}
		return Long.parseLong(
				(String) em.createQuery(GET_PUBLISHED_VOTES_IN_DISTRICT).setParameter("id", id).getSingleResult());
	}

	public Long getNumberOfDistrictvalidVotes(Integer id) {

		if (em.createQuery(GET_DISTRICTS_PUBLISHED_VOTES_IN_DISTRICT).setParameter("id", id).getResultList()
				.get(0) == null) {
			return 0L;
		} else {
			return Long.parseLong((String) em.createQuery(GET_DISTRICTS_PUBLISHED_VOTES_IN_DISTRICT).setParameter("id", id).getSingleResult());
		}
	}

	public Long getVotesByCandidateId(Integer id) {
		if (em.createQuery(FIND_VOTES_BY_CANDIDATE_ID).setParameter("id", id).getResultList().get(0) == null) {
			return (Long) 0L;
		} else {
			return Long.parseLong(
					(String) em.createQuery(FIND_VOTES_BY_CANDIDATE_ID).setParameter("id", id).getSingleResult());
		}
	}
}
