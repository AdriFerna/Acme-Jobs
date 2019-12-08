
package acme.features.employer.duties;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select a from Job a where a.id = ?1")
	Job findOneById(int id);

	@Query("select a from Duty a where a.job.id = ?1")
	Collection<Duty> findMany(int id);

}
