
package acme.features.worker.jobApplication;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobApplication.JobApplication;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerJobApplicationRepository extends AbstractRepository {

	@Query("select a from JobApplication a where a.id = ?1")
	JobApplication findOneById(int id);

	@Query("select a from JobApplication a where a.worker.id = ?1")
	Collection<JobApplication> findManyMine(int idWorker);

	@Query("select count(a) from JobApplication a where a.referenceNumber = ?1")
	Integer getReferenceNumbers(String a);

	@Query("select a from Worker a where a.id = ?1")
	Worker getWorker(Integer id);

	@Query("select a from Job a where a.id = ?1")
	Job getJob(int id);

}
