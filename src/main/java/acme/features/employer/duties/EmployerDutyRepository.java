
package acme.features.employer.duties;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customParams.Configuration;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select a from Job a where a.id = ?1")
	Job findOneById(int id);

	@Query("select a.job from Duty a  	 where a.id = ?1")
	Job findJobByDutyId(int dutyId);

	@Query("select a from Duty a where a.job.id = ?1")
	Collection<Duty> findMany(int id);

	@Query("select a from Duty a where a.id = ?1")
	Duty findOneDuty(int idDuty);

	@Query("select a from Configuration a")
	Configuration getConfigParams();

	@Query("select coalesce(sum(a.percentage),0) from Duty a where a.job.id = ?1")
	Double getTotalPercentageOfDuties(int idJob);

	@Query("select coalesce(sum(a.percentage),0) from Duty a where a.job.id = ?1 and a.id != ?2")
	Double getTotalPercentageOfDutiesUpdate(int idJob, int idDuty);

}
