
package acme.features.employer.jobs;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecord.AuditRecord;
import acme.entities.customParams.Configuration;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select a from Job a where a.id = ?1")
	Job findOneById(int id);

	@Query("select a.referenceNumber from Job a where a.id = ?1")
	String SelectRefNumberById(int id);

	@Query("select a from Job a where a.employer.id = ?1")
	Collection<Job> findManyMine(int idEmployer);

	@Query("select a from Employer a where a.id = ?1")
	Employer findOneEmployerById(int id);

	@Query("select count(a) from Job a where a.referenceNumber = ?1")
	Integer getRefNumbers(String a);

	@Query("select a from Configuration a")
	Configuration getConfigParams();

	@Query("select count(a) from JobApplication a where a.job.id = ?1")
	Double getJobApplicationsOfTheJob(int jobId);

	@Query("select a from Duty a where a.job.id =?1 ")
	List<Duty> findDutiesOfTheJob(int idJob);

	@Query("select a from AuditRecord a where a.job.id = ?1")
	List<AuditRecord> findAuditRecordsOfTheJob(int idJob);

	@Query("select coalesce(sum(a.percentage),0) from Duty a where a.job.id = ?1")
	Double getTotalPercentageOfDuties(int idJob);

}
