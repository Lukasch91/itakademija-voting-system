package vs.admin_.candidate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Service
public class CandidateValidationService {

	@Autowired
	private CandidateCreateService candidateService;
	@Autowired
	private CandidateCSVParserService csvParser;
	@Autowired
	private CandidateRepository candidateRepository;
	private JSONParser parser = new JSONParser(0);

	@SuppressWarnings("rawtypes")
	public ResponseEntity validateSaveConstituencyData(CandidateDataPackage data) {
		List<String[]> parsedStructure = csvParser.csvReader(data.getText(), data.getDelimiter());
		List<Candidate> candidates = new ArrayList<Candidate>();
		boolean dataIsValid = true;
		boolean candidateIsValid = true;

		for (String[] row : parsedStructure) {
			if (row.length != 5) {
				dataIsValid = false;
			}
		}

		if (dataIsValid) {
			candidateService.setCandidatesConstituency(data.getId());
			candidateService.setCandidatesData2(parsedStructure);
			candidates = candidateService.saveConstituencyCandidates();

			for (Candidate can : candidates) {
				validateCandidate(can);
				if (validateCandidate(can).isEmpty() == false) {
					// System.out.println(candidateValidationService2.validateCandidate(can).get(0).toString());
					candidateIsValid = false;
				}
			}

		}

		if (dataIsValid && candidateIsValid) {
			for (Candidate can : candidates) {
				candidateRepository.createOrUpdateCandidate(can);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} else if (dataIsValid == false) {
			JSONObject json = null;
			try {
				json = (JSONObject) parser.parse("{\"error\": \"Bylos turinio struktura neatitinka reikalavimu\"}");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} else {
			JSONObject json = null;
			try {
				json = (JSONObject) parser.parse("{\"error\": \"Kandidato duomenys turi klaidu\"}");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity validateSavePartyData(CandidateDataPackage data) {
		List<String[]> parsedStructure = csvParser.csvReader(data.getText(), data.getDelimiter());
		List<Candidate> candidates = new ArrayList<Candidate>();
		boolean dataIsValid = true;
		boolean candidateIsValid = true;

		for (String[] row : parsedStructure) {
			if (row.length != 6) {
				dataIsValid = false;
			}
		}

		if (dataIsValid) {
			candidateService.setCandidatesParty(data.getId());
			candidateService.setCandidatesData2(parsedStructure);
			candidates = candidateService.savePartyCandidates();

			for (Candidate can : candidates) {
				validateCandidate(can);
				if (validateCandidate(can).isEmpty() == false) {
					// System.out.println(candidateValidationService2.validateCandidate(can).get(0).toString());
					candidateIsValid = false;
				}
			}
		}
		
		if (dataIsValid && candidateIsValid) {
			for (Candidate can : candidates) {
				candidateRepository.createOrUpdateCandidate(can);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} else if (dataIsValid == false) {
			JSONObject json = null;
			try {
				json = (JSONObject) parser.parse("{\"error\": \"Bylos turinio struktura neatitinka reikalavimu\"}");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} else {
			JSONObject json = null;
			try {
				json = (JSONObject) parser.parse("{\"error\": \"Kandidato duomenys turi klaidu\"}");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}
	}

	private List<String> validateCandidate(Candidate candidate) {

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		List<String> violationMessages = new ArrayList<String>();

		if (validator.validate(candidate).isEmpty() == false) {
			Set<ConstraintViolation<Candidate>> constraintViolationsCandidate = validator.validate(candidate);
			for (ConstraintViolation<Candidate> constraintViolation : constraintViolationsCandidate) {
				violationMessages.add(constraintViolation.getMessage());
			}
			constraintViolationsCandidate.clear();
		}

		return violationMessages;
	}

}
