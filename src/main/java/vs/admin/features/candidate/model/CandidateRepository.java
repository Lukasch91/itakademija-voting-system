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
	private static final String FIND_ALL_CONSTITUENCY = "SELECT x FROM Candidate x "
			+ "WHERE candidate_Deleted_Date is NULL AND candidateConstituency=";

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Candidate> findAllUndeletedCandidates() {
		return em.createQuery(FIND_ALL).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidatesByConstituencyId(Integer id) {
		return em.createQuery(FIND_ALL_CONSTITUENCY+id).getResultList();
	}
	
	
	

	/* ===================================================== */

	@Transactional
	public Candidate createOrUpdateCandidate(Candidate candidate) {
		if (candidate.getCandidateID() == null) {

			// find a match

			boolean canPersIdNoMatch = true;
			Candidate match = null;
			@SuppressWarnings("unchecked")
			List<Candidate> candidates = em.createQuery(FIND_ALL).getResultList();

			for (Candidate matchingCandidate : candidates) {
				if (matchingCandidate.equals(candidate)) {
					canPersIdNoMatch = false;
					match = matchingCandidate;
				}
			}

			if (canPersIdNoMatch == false) {

				if (match.getCandidateParty() != null) {
					// add constituency id
					Candidate updatedCandidate = new Candidate();
					updatedCandidate.setCandidateID(match.getCandidateID());
					updatedCandidate.setCandidateName(match.getCandidateName());
					updatedCandidate.setCandidateSurname(match.getCandidateSurname());
					updatedCandidate.setCandidateDateOfBirth(match.getCandidateDateOfBirth());
					updatedCandidate.setCandidatePersonalID(match.getCandidatePersonalID());
					updatedCandidate.setCandidateDescription(match.getCandidateDescription());
					updatedCandidate.setCandidateDeletedDate(null);

					updatedCandidate.setCandidateParty(match.getCandidateParty());
					updatedCandidate.setCandidateNumberInParty(match.getCandidateNumberInParty());

					updatedCandidate.setCandidateConstituency(candidate.getCandidateConstituency());

					em.persist(em.merge(updatedCandidate));
					return updatedCandidate;

				} else if (match.getCandidateConstituency() != null) {
					// add party id & number in party
					Candidate updatedCandidate = new Candidate();
					updatedCandidate.setCandidateID(match.getCandidateID());
					updatedCandidate.setCandidateName(match.getCandidateName());
					updatedCandidate.setCandidateSurname(match.getCandidateSurname());
					updatedCandidate.setCandidateDateOfBirth(match.getCandidateDateOfBirth());
					updatedCandidate.setCandidatePersonalID(match.getCandidatePersonalID());
					updatedCandidate.setCandidateDescription(match.getCandidateDescription());
					updatedCandidate.setCandidateDeletedDate(null);

					updatedCandidate.setCandidateParty(candidate.getCandidateParty());
					updatedCandidate.setCandidateNumberInParty(candidate.getCandidateNumberInParty());

					updatedCandidate.setCandidateConstituency(match.getCandidateConstituency());

					em.persist(em.merge(updatedCandidate));
					return updatedCandidate;
				} else {
					System.err.println("candidate merge error");
					return null;
				}

			} else {
				em.persist(candidate);
				return candidate;
			}

			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~update
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
	
	@Transactional
	public void deleteCandidatesByConstituencyId(Integer constituencyId) {
		@SuppressWarnings("unchecked")
		List<Candidate> candidatesToDelete = em.createQuery(FIND_ALL_CONSTITUENCY + constituencyId).getResultList();

		for (Candidate candidate : candidatesToDelete) {
			Date date = new Date();
			candidate.setCandidateDeletedDate(date);
			em.persist(candidate);
		}
	}
	
	
}













