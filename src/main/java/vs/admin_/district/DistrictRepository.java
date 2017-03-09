package vs.admin_.district;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DistrictRepository {
	
	private static final Logger log = Logger.getLogger(DistrictRepository.class.getName());

	private static final String FIND_ALL = "SELECT d FROM District d WHERE d.deletedTime IS NULL";

	private static final String GET_NUMBER_OF_DISTRICTS_BY_CONSTITUENCY = "SELECT COUNT(d) FROM District d "
			+ "WHERE d.constituencyId = :id " + "AND d.deletedTime IS NULL";

	private static final String GET_NUMBER_OF_VOTERS_IN_DISTRICTS_BY_CONSTITUENCY = "SELECT SUM(d.numberOfVoters) FROM District d "
			+ "WHERE d.constituencyId = :id " + "AND d.deletedTime IS NULL";

	private static final String GET_DISTRICTS_BY_CONSTITUENCY_ID = "SELECT d FROM District d "
			+ "WHERE d.deletedTime IS NULL " + "AND d.constituencyId=:id";

	private static final String GET_CONSTITUENCY_ID_BY_DISTRICT_ID = "SELECT d.constituencyId "
			+ "FROM District d where d.deletedTime IS NULL " + "AND d.id=:id";

	private static final String GET_NUMBER_OF_ALL_DISTRICTS = "SELECT count(d) FROM District d where d.deletedTime IS NULL";

	private static final String SUM_OF_ALL_VOTERS = "SELECT SUM(d.numberOfVoters) FROM District d where d.deletedTime IS NULL";

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<District> findAllDistricts() {
		log.debug("DistrictRepository - findAllDistricts was used");
		return entityManager.createQuery(FIND_ALL).getResultList();
	}

	public Long countAllDistricts() {
		log.debug("DistrictRepository - countAllDistricts was used");
		if (entityManager.createQuery(GET_NUMBER_OF_ALL_DISTRICTS).getResultList().isEmpty()) {
			return 0L;
		} else
			return (Long) entityManager.createQuery(GET_NUMBER_OF_ALL_DISTRICTS).getSingleResult();
	}
	
	public Long countAllVoters() {
		log.debug("DistrictRepository - countAllVoters was used");
		if (entityManager.createQuery(SUM_OF_ALL_VOTERS).getResultList().isEmpty()) {
			return 0L;
		} else
			return (Long) entityManager.createQuery(SUM_OF_ALL_VOTERS).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<District> findAllDistrictsByConstituencyId(Integer id) {
		log.debug("DistrictRepository - findAllDistrictsByConstituencyId was used. id: " + id);
		return entityManager.createQuery(GET_DISTRICTS_BY_CONSTITUENCY_ID).setParameter("id", id).getResultList();
	}

	public Integer getConstituencyIdByDistrictId(Integer id) {
		log.debug("DistrictRepository - getConstituencyIdByDistrictId was used. id: " + id);
		return (Integer) entityManager.createQuery(GET_CONSTITUENCY_ID_BY_DISTRICT_ID).setParameter("id", id)
				.getSingleResult();
	}

	@Transactional
	public District saveOrUpdate(District district) {
		log.debug("DistrictRepository - saveOrUpdate started!");
		if (district.getId() == null) {
			entityManager.persist(district);
			log.debug("DistrictRepository - saveOrUpdate finished!District was saved.");
			return district;
		} else {
			District merged = entityManager.merge(district);
			entityManager.persist(merged);
			log.debug("DistrictRepository - saveOrUpdate finished!District was updated.");
			return merged;
		}
		
	}

	public District findDistrictById(Integer id) {
		log.debug("DistrictRepository - findDistrictById was used!District id: " + id);
		District district = entityManager.find(District.class, id);
		if (district == null || district.getDeletedTime() != null) {
			return null;
		}
		return district;
	}

	@Transactional
	public void deleteDistrict(Integer id) {
		log.debug("DistrictRepository - deleteDistrict was started...District id: " + id);
		District district = entityManager.find(District.class, id);
		Date date = new Date();
		district.setDeletedTime(date);
		entityManager.persist(district);
		log.debug("DistrictRepository - deleteDistrict was finished!");
	}

	public Long getNumberOfExistentDistricts(Integer id) {
		log.debug("DistrictRepository - getNumberOfExistentDistricts was used! id " + id );
		return (Long) entityManager.createQuery(GET_NUMBER_OF_DISTRICTS_BY_CONSTITUENCY).setParameter("id", id)
				.getSingleResult();
	}

	public Long getSumOfVoters(Integer id) {
		log.debug("DistrictRepository - getNumberOfExistentDistricts was used! id " + id );
		return (Long) entityManager.createQuery(GET_NUMBER_OF_VOTERS_IN_DISTRICTS_BY_CONSTITUENCY)
				.setParameter("id", id).getSingleResult();
	}

}
