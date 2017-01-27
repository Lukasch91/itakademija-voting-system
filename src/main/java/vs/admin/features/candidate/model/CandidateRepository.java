package vs.admin.features.candidate.model;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CandidateRepository {
	private static final String FIND_ALL = "SELECT x FROM Candidate x WHERE candidate_Deleted_Date is NULL";

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Candidate> findAllUndeletedCandidates() {
		return em.createQuery(FIND_ALL).getResultList();
	}

	/* ===================================================== */

	@Transactional
	public Candidate createOrUpdateCandidate(Candidate candidate) {
		if (candidate.getCandidateID() == null) {
			
			boolean canPersIdNoMatch = true;
			@SuppressWarnings("unchecked")
			List<Candidate> candidates = em.createQuery(FIND_ALL).getResultList();
			
			for(Candidate x : candidates) {
				 if(x.equals(candidate)) {
				 canPersIdNoMatch = false;		
				 }
			}
			
			if(canPersIdNoMatch == false) {
				Candidate TEMP = new Candidate(
						candidate.getCandidateID(), 
						"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 
						candidate.getCandidateSurname(), 
						candidate.getCandidateDateOfBirth(), 
						candidate.getCandidatePersonalID(), 
						candidate.getCandidateDescription(), 
						candidate.getCandidateParty(), 
						candidate.getCandidateNumberInParty(), 
						candidate.getCandidateConstituency(), 
						candidate.getCandidateDeletedDate());
						
						
				
				em.persist(em.merge(TEMP));
				
				return TEMP;
				
			} else {

				
				
				em.persist(candidate);
				return candidate;

			}
			
			
			
		
		
			
			
			
			
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~update
		} else {

			Candidate merged = em.merge(candidate);
			em.persist(merged);
			return merged;

		}
	}

	/* ===================================================== */
	public Candidate findCandidateById(Integer id) {
		Candidate candidate = em.find(Candidate.class, id);
		if ((candidate != null) && (candidate.getCandidateDeletedDate() == null)) {
			return candidate;
		} else {
			return null;
		}
	}

	@Transactional
	public void deleteCandidateById(Integer id) {
		Candidate candidate = em.find(Candidate.class, id);
		Date date = new Date();
		candidate.setCandidateDeletedDate(date);
		em.persist(candidate);
	}
}

