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
	
	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<SingleElection> findAllSingleElectionResults() {
		return em.createQuery(FIND_ALL).getResultList();
	}

	@Transactional
	public SingleElection saveSingleElection(SingleElection singleElection) {
		if (singleElection.getSingleId() == null) {			
			Date singleEnteredDate = new Date();
			singleElection.setSingleEnteredDate(singleEnteredDate);
			em.persist(singleElection);
			return singleElection;
		} 		
		
		/*============UpdateDisabled====*/
		else {
//			SingleElection merged = em.merge(singleElection);
//			em.persist(merged);
//			return merged;
			return null;
		}
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
		List<SingleElection> singleElectionsPublish = em.createQuery(FIND_BY_DISTRICT_ID+districtId).getResultList();
		
		for(SingleElection singleElectionPublish : singleElectionsPublish) {
			Date date = new Date();
			singleElectionPublish.setSinglePublishedDate(date);
			em.persist(singleElectionPublish);	
		}
	}
	
	@Transactional
	public void deleteSingleElectionResultsByDistrictId(Integer districtId) {
		@SuppressWarnings("unchecked")
		List<SingleElection> singleElectionsDelete = em.createQuery(FIND_BY_DISTRICT_ID+districtId).getResultList();
		
		for(SingleElection singleElectionDelete : singleElectionsDelete) {
			Date date = new Date();
			singleElectionDelete.setSingleDeletedDate(date);
			em.persist(singleElectionDelete);	
		}
	}
	
	@Transactional
	public void deleteSingleElectionResultsByDistrictIdREAL(Integer districtId) {
		@SuppressWarnings("unchecked")
		List<SingleElection> singleElectionsDeleteREAL = em.createQuery(FIND_BY_DISTRICT_ID+districtId).getResultList();
		
		for(SingleElection singleElectionDeleteREAL : singleElectionsDeleteREAL) {

			em.remove(singleElectionDeleteREAL);
		}
	}
	
	
	
	
	
	
}



