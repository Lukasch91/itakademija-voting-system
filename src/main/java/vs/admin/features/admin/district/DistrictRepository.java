package vs.admin.features.admin.district;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DistrictRepository {

	private static final String FIND_ALL = "SELECT d FROM District d WHERE d.deletedTime IS NULL";

	private static final String GET_NUMBER_OF_DISTRICTS_BY_CONSTITUENCY = "SELECT COUNT(d) FROM District d WHERE d.constituencyId = :id AND d.deletedTime IS NULL";

	private static final String GET_NUMBER_OF_VOTERS_IN_DISTRICTS_BY_CONSTITUENCY = "SELECT SUM(d.numberOfVoters) FROM District d WHERE d.constituencyId = :id AND d.deletedTime IS NULL";
	
	private static final String GET_DISTRICTS_BY_CONSTITUENCY_ID = "SELECT d FROM District d WHERE d.deletedTime IS NULL ANd d.constituencyId=:id";
	
	private static final String GET_CONSTITUENCY_ID_BY_DISTRICT_ID = "SELECT d.constituencyId FROM District d where d.deletedTime IS NULL AND d.id=:id";

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<District> findAllDistricts() {
		return entityManager.createQuery(FIND_ALL).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<District> findAllDistrictsByConstituencyId(Integer id) {
		return entityManager.createQuery(GET_DISTRICTS_BY_CONSTITUENCY_ID).setParameter("id", id).getResultList();
	}
	
	public Integer getConstituencyIdByDistrictId (Integer id){
		return (Integer) entityManager.createQuery(GET_CONSTITUENCY_ID_BY_DISTRICT_ID).setParameter("id", id)
				.getSingleResult();
	}

	@Transactional
	public District saveOrUpdate(District district) {
		if (district.getId() == null) {
			entityManager.persist(district);
			return district;
		} else {
			District merged = entityManager.merge(district);
			entityManager.persist(merged);
			return merged;
		}
	}

	public District findDistrictById(Integer id) {
		District district = entityManager.find(District.class, id);
		if (district == null || district.getDeletedTime() != null) {
			return null;
		}
		return district;
	}

	@Transactional
	public void deleteDistrict(Integer id) {
		District district = entityManager.find(District.class, id);
		Date date = new Date();
		district.setDeletedTime(date);
		entityManager.persist(district);
	}

	public Long getNumberOfExistentDistricts(Integer id) {
		return (Long) entityManager.createQuery(GET_NUMBER_OF_DISTRICTS_BY_CONSTITUENCY).setParameter("id", id)
				.getSingleResult();
	}

	public Long getSumOfVoters(Integer id) {
		return (Long) entityManager.createQuery(GET_NUMBER_OF_VOTERS_IN_DISTRICTS_BY_CONSTITUENCY)
				.setParameter("id", id).getSingleResult();
	}

}
