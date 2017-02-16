package vs.admin.features.admin.constituency;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin.features.candidate.model.Candidate;
import vs.admin.features.candidate.model.CandidateRepository;

@Service
public class ConstituencyService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private ConstituencyRepository constituencyRepository;

	public List<ConstituencyExtension> findAllConstituenciesExtended() {
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
		return extendedConstituencies;
	}
}
