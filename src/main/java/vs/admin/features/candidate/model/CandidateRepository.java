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
	private static final String FIND_ALL_CONSTITUENCY = "SELECT x FROM Candidate x  "
			+ "WHERE candidate_Deleted_Date is NULL AND candidateConstituency=";
	private static final String FIND_ALL_PARTY = "SELECT x FROM Candidate x "
			+ "WHERE candidate_Deleted_Date is NULL AND candidateParty=";

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
		} else {
			return null; //if candidate id is not null
		}
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~update--disabled
		// } else {
		//
		// Candidate merged = em.merge(candidate);
		// em.persist(merged);
		// return merged;
		//
		// }
	}

	/* ===================================================== */
	
	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidatesByConstituencyId(Integer constituencyId) {
		return em.createQuery(FIND_ALL_CONSTITUENCY + constituencyId).getResultList();
	}
	
	@Transactional
	public void deleteCandidatesByConstituencyId(Integer constituencyId) {
		@SuppressWarnings("unchecked")
		List<Candidate> candidatesToDelete = em.createQuery(FIND_ALL_CONSTITUENCY + constituencyId).getResultList();

		for (Candidate candidate : candidatesToDelete) {
			if (candidate.getCandidateParty() != null) {
				candidate.setCandidateConstituency(null);
				em.persist(candidate);
			} else {
				Date date = new Date();
				candidate.setCandidateDeletedDate(date);
				em.persist(candidate);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidatesByPartyId(Integer partyId) {
		return em.createQuery(FIND_ALL_PARTY + partyId).getResultList();
	}
	
	@Transactional
	public void deleteCandidatesByPartyId(Integer partyId) {
		@SuppressWarnings("unchecked")
		List<Candidate> candidatesToDelete = em.createQuery(FIND_ALL_PARTY + partyId).getResultList();

		for (Candidate candidate : candidatesToDelete) {
			if (candidate.getCandidateConstituency() != null) {
				candidate.setCandidateParty(null);
				candidate.setCandidateNumberInParty(null);
				em.persist(candidate);
			} else {
				Date date = new Date();
				candidate.setCandidateDeletedDate(date);
				em.persist(candidate);
			}
		}
	}

}
