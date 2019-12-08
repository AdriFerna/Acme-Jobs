
package acme.features.auditor.job;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	// Devuelve los jobs para los que no se ha escrito una audit
	@Query("select a.job from AuditRecord a where a.auditor.id = ?1")
	List<Job> listJobsWritten(int id);

	@Query("select a from Job a where a.id = ?1")
	Job findOneById(int id);

	@Query("select a from Job a where a.deadline > ?1 and  a.status = 'published' ")
	Collection<Job> findManyActive(Date ln);

	@Query("select a from Job a where id not in (select b.job.id from AuditRecord b where b.auditor.id =?1) and  a.status = 'published'")
	Collection<Job> listJobNoAudited(int id);

}
