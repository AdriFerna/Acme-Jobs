
package acme.features.administrator.dashboardJob;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.DashboardJob;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardJobDisplayService implements AbstractShowService<Administrator, DashboardJob> {

	@Autowired
	AdministratorDashboardJobRepository repository;


	@Override
	public boolean authorise(final Request<DashboardJob> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<DashboardJob> request, final DashboardJob entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "averageNumberOfJobsPerEmployer", "averageNumberOfApplicationsPerEmployer", "averageNumberOfApplicationsPerWorker", "ratioOfJobsPerStatus", "ratioOfApplicationsPerStatus");

	}

	@Override
	public DashboardJob findOne(final Request<DashboardJob> request) {
		assert request != null;
		DashboardJob d = new DashboardJob();

		Double averageNumberOfJobsPerEmployer = this.repository.getAverageJobsPerEmployer();
		Double averageNumberOfApplicationsPerEmployer = this.repository.getAverageApplicationsPerEmployer();
		Double averageNumberOfApplicationsPerWorker = this.repository.getAverageApplicationsPerWorker();

		List<List<String>> ratioOfJobsPerStatus = this.repository.getRatioOfJobsPerStatus();
		List<List<String>> ratioOfApplicationsPerStatus = this.repository.getRatioOfJobsApplicationsPerStatus();

		d.setAverageNumberOfJobsPerEmployer(averageNumberOfJobsPerEmployer);
		d.setAverageNumberOfApplicationsPerEmployer(averageNumberOfApplicationsPerEmployer);
		d.setAverageNumberOfApplicationsPerWorker(averageNumberOfApplicationsPerWorker);

		d.setRatioOfApplicationsPerStatus(ratioOfApplicationsPerStatus);
		d.setRatioOfJobsPerStatus(ratioOfJobsPerStatus);

		return d;
	}

}
