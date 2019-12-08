
package acme.features.employer.jobApplication;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobApplication.JobApplication;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobApplicationRepository extends AbstractRepository {

	@Query("select a from JobApplication a where a.id = ?1")
	JobApplication findOneById(int id);

	@Query("select a from JobApplication a where a.job.id IN(?1)")
	Collection<JobApplication> findManyMine(List<Integer> id);

	@Query("select a.id from Job a where a.employer.id = ?1")
	Integer[] findManyId(int idEmployer);

}
