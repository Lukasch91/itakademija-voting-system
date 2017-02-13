package vs.representative.features.single.election;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SingleElectionRepository {

	private static final String FIND_ALL = "SELECT x FROM SingleElection x WHERE single_deleted_date IS NULL";

	private static final String FIND_BY_DISTRICT_ID = "SELECT x FROM SingleElection x WHERE singleDistrict IS ";

	private static final String COUNT_PUBLISHED_DISTRICT_RESULTS = "SELECT count(d) FROM SingleElection s "
			+ "LEFT JOIN s.singleDistrict d " + "WHERE s.singleDeletedDate IS NULL "
			+ "AND s.singlePublishedDate IS NOT NULL and d.constituencyId=:id";

	private static final String SUM_PUBLISHED_VOTES_IN_DISTRICTS_BY_CONSTITUENCY = "SELECT sum(s.singleVotes) FROM SingleElection s "
			+ "LEFT JOIN s.singleDistrict d " + "WHERE s.singleDeletedDate IS NULL "
			+ "AND s.singlePublishedDate IS NOT NULL and d.constituencyId=:id";

	private static final String GET_NUMBER_OF_INVALID_VOTES = "SELECT sum(s.singleVotes) FROM SingleElection s "
			+ "LEFT JOIN s.singleDistrict sd " + "LEFT JOIN s.singleCandidate sc "
			+ "WHERE s.singleDeletedDate IS NULL "
			+ "AND s.singlePublishedDate IS NOT NULL and sd.constituencyId=:id AND sc.candidateName ='NOVOTES'";

	private static final String GET_NUMBER_OF_VOTERS_WHO_VOTED = "SELECT sum(s.singleVotes) FROM SingleElection s "
			+ "LEFT JOIN s.singleDistrict sd " + "LEFT JOIN s.singleCandidate sc "
			+ "WHERE s.singleDeletedDate IS NULL "
			+ "AND s.singlePublishedDate IS NOT NULL and sd.constituencyId=:id AND sc.candidateName !='NOVOTES'";

	private static final String GET_PUBLISHED_VOTES_IN_DISTRICT = "SELECT sum(s.singleVotes) FROM SingleElection s "
			+ "LEFT JOIN s.singleDistrict sd " + "WHERE sd.id = :id "
			+ "AND s.singleDeletedDate IS NULL " + "AND s.singlePublishedDate IS NOT NULL";

	private static final String GET_DISTRICTS_PUBLISHED_VOTES_IN_DISTRICT = "SELECT sum(s.singleVotes) FROM SingleElection s "
			+ "LEFT JOIN s.singleDistrict sd LEFT JOIN s.singleCandidate sc  WHERE sd.id = :id " 
			+ "AND s.singleDeletedDate IS NULL AND s.singlePublishedDate IS NOT NULL  AND sc.candidateName !='NOVOTES'";
	
	@Autowired
	private EntityManager em;

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
		return (Long) em.createQuery(SUM_PUBLISHED_VOTES_IN_DISTRICTS_BY_CONSTITUENCY).setParameter("id", id)
				.getSingleResult();
	}

	public Long getSumOfInvalidVotes(Integer id) {
		return (Long) em.createQuery(GET_NUMBER_OF_INVALID_VOTES).setParameter("id", id).getSingleResult();
	}

	public Long getSumOfValidVotes(Integer id) {
		return (Long) em.createQuery(GET_NUMBER_OF_VOTERS_WHO_VOTED).setParameter("id", id).getSingleResult();
	}

	public Long getNumberOfpublishedVotes(Integer id) {
		return (Long) em.createQuery(GET_PUBLISHED_VOTES_IN_DISTRICT).setParameter("id", id)
				.getSingleResult();
	}
	
	public Long getNumberOfDistrictInvalidVotes(Integer id) {
		return (Long) em.createQuery(GET_DISTRICTS_PUBLISHED_VOTES_IN_DISTRICT).setParameter("id", id)
				.getSingleResult();
	}
}
