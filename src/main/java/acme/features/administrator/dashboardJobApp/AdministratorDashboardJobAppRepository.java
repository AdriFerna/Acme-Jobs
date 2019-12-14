
package acme.features.administrator.dashboardJobApp;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobApplication.JobApplication;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardJobAppRepository extends AbstractRepository {

	@Query("select a from JobApplication a where a.updateMoment > ?1 and a.status ='accepted' ")
	List<JobApplication> findAccepted(Date ldt);

	@Query("select a from JobApplication a where a.updateMoment > ?1 and a.status ='rejected' ")
	List<JobApplication> findRejected(Date ldt);

	@Query("select a from JobApplication a")
	List<JobApplication> findPending(Date ldt);

}
