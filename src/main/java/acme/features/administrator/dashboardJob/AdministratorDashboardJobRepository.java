
package acme.features.administrator.dashboardJob;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardJobRepository extends AbstractRepository {

	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double getAverageJobsPerEmployer();

	@Query("select avg(select count(a) from JobApplication a where exists(select j from Job j where j.employer.id = e.id and a.job.id = j.id)) from Employer e")
	Double getAverageApplicationsPerEmployer();

	@Query("select avg( select count(j) from JobApplication j where j.worker.id = e.id) from Worker e")
	Double getAverageApplicationsPerWorker();

	@Query("select 100.0 * count(a)/(select count(b) from Job b),a.status from  Job a group by a.status")
	List<List<String>> getRatioOfJobsPerStatus();

	@Query("select 100.0 * count(a)/(select count(b) from JobApplication b),a.status from  JobApplication a group by a.status")
	List<List<String>> getRatioOfJobsApplicationsPerStatus();

}
