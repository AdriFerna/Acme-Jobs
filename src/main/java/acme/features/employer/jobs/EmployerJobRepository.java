
package acme.features.employer.jobs;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select a from Job a where a.id = ?1")
	Job findOneById(int id);

	@Query("select a from Job a where a.employer.id = ?1")
	Collection<Job> findManyMine(int idEmployer);

}
