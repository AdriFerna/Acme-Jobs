
package acme.features.worker.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobApplication.JobApplication;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerJobApplicationRepository extends AbstractRepository {

	@Query("select a from JobApplication a where a.id = ?1")
	JobApplication findOneById(int id);

	@Query("select a from JobApplication a where a.worker.id = ?1")
	Collection<JobApplication> findManyMine(int idWorker);

}
