package vs.admin_.candidate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin.AdminController;
import vs.admin_.constituency.Constituency;
import vs.admin_.district.District;
import vs.admin_.party.Party;

@Service
public class CandidateCreateService {

	@Autowired
	private CandidateRepository candidateRepository;

	private static final Logger log = Logger.getLogger(CandidateCreateService.class.getName());
	private List<String[]> candidatesData;
	private Integer candidatesConstituency;
	private Integer candidatesParty;

	public List<Candidate> saveConstituencyCandidates() {
		log.debug("CandidateCreateService - saveConstituencyCandidates started...");
		List<Candidate> candidates = new ArrayList<Candidate>();
		
		for (String[] cells : candidatesData) {
			candidates.add(createConstituencyCandidate(cells));
		}
		log.debug("CandidateCreateService - saveConstituencyCandidates finished!");
		return candidates;
	}

	public List<Candidate> savePartyCandidates() {
		log.debug("CandidateCreateService - savePartyCandidates started...");
		
		List<Candidate> candidates = new ArrayList<Candidate>();
		
		for (String[] cells : candidatesData) {
			candidates.add(createPartyCandidate(cells));
		}
		
		log.debug("CandidateCreateService - savePartyCandidates finished!");
		return candidates;
	}

	private Candidate createConstituencyCandidate(String[] cell) {
		log.debug("CandidateCreateService - createConstituencyCandidate started...");
		Candidate candidate = new Candidate();
		candidate.setCandidateName(cell[0]);
		candidate.setCandidateSurname(cell[1]);
		candidate.setCandidateDateOfBirth(cell[2]);
		candidate.setCandidatePersonalID(cell[3]);
		candidate.setCandidateDescription(cell[4]);
		Constituency idSetter = new Constituency();
		idSetter.setId(candidatesConstituency);
		candidate.setCandidateConstituency(idSetter);
		candidate.setCandidateParty(null);
		log.debug("CandidateCreateService - createConstituencyCandidate finished!");
		return candidate;
	}

	private Candidate createPartyCandidate(String[] cell) {
		log.debug("CandidateCreateService - createPartyCandidate started...");
		Candidate candidate = new Candidate();
		candidate.setCandidateName(cell[0]);
		candidate.setCandidateSurname(cell[1]);
		candidate.setCandidateDateOfBirth(cell[2]);
		candidate.setCandidatePersonalID(cell[3]);
		candidate.setCandidateDescription(cell[4]);
		candidate.setCandidateNumberInParty(Integer.valueOf(cell[5]));
		candidate.setCandidateConstituency(null);
		Party idSetter = new Party();
		idSetter.setId(candidatesParty);
		candidate.setCandidateParty(idSetter);
		log.debug("CandidateCreateService - createPartyCandidate finished!");
		return candidate;
	}

	public void setCandidatesData2(List<String[]> candidates) {
		this.candidatesData = candidates;
	}

	public void setCandidatesParty(Integer candidatesParty) {
		log.debug("setCandidatesParty result:" + candidatesParty);
		this.candidatesParty = candidatesParty;
	}

	public void setCandidatesConstituency(Integer candidatesDistrict) {
		log.debug("setCandidatesConstituency result: " + candidatesDistrict);
		this.candidatesConstituency = candidatesDistrict;
	}

	public List<Candidate> findAllCandidatesByDistrictId(Integer districtId) {
		log.debug("CandidateCreateService - findAllCandidatesByDistrictId started.. district id: " + districtId);
		// can be changed to a well written SQL or something

		List<Candidate> allCandidates = candidateRepository.findAllUndeletedCandidates();
		List<Candidate> candidatesInDistrict = new ArrayList<Candidate>();

		for (Candidate candidate : allCandidates) {
			List<District> candidateDistricts = new ArrayList<District>();
			if (candidate.getCandidateConstituency() != null
					&& candidate.getCandidateConstituency().getDistricts() != null) {
				candidateDistricts = candidate.getCandidateConstituency().getDistricts();
				for (District district : candidateDistricts) {
					if (district.getId() == districtId) {
						candidatesInDistrict.add(candidate);
					}
				}
			}
		}
		log.debug("CandidateCreateService - findAllCandidatesByDistrictId finished!");
		return candidatesInDistrict;
	}

}
