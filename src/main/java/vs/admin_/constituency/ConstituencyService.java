package vs.admin_.constituency;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin_.candidate.Candidate;
import vs.admin_.candidate.CandidateRepository;

@Service
public class ConstituencyService {

	private static final Logger log = Logger.getLogger(ConstituencyRepository.class.getName());
	
	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private ConstituencyRepository constituencyRepository;

	public List<ConstituencyExtension> findAllConstituenciesExtended() {
log.debug("ConstituencyService - findAllConstituenciesExtended started...");
		List<Constituency> constituencies = constituencyRepository.findAllConstituencies();
		List<Candidate> candidates = candidateRepository.findAllUndeletedCandidates();
		List<ConstituencyExtension> extendedConstituencies = new ArrayList<>();

		for (Constituency constituency : constituencies) {
			ConstituencyExtension consExtend = new ConstituencyExtension();
			consExtend.setId(constituency.getId());
			consExtend.setTitle(constituency.getTitle());
			consExtend.setDeletedTime(null);
			consExtend.setDistricts(constituency.getDistricts());

			int numberOfCandidates = 0;
			for (Candidate candidate : candidates) {
				if (candidate.getCandidateConstituency() == constituency) {
					numberOfCandidates++;
				}
			}
			consExtend.setNumberOfCandidatesInConstituency(numberOfCandidates);
			extendedConstituencies.add(consExtend);
		}
		log.debug("ConstituencyService - findAllConstituenciesExtended finished!");
		return extendedConstituencies;
	}
}
