
package acme.features.worker.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobApplication.JobApplication;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class WorkerJobApplicationListMineService implements AbstractListService<Worker, JobApplication> {

	@Autowired
	WorkerJobApplicationRepository repository;


	@Override
	public boolean authorise(final Request<JobApplication> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<JobApplication> request, final JobApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "referenceNumber", "qualifications", "job.title");
	}

	@Override
	public Collection<JobApplication> findMany(final Request<JobApplication> request) {
		assert request != null;
		Collection<JobApplication> res;
		Principal principal;
		principal = request.getPrincipal();
		res = this.repository.findManyMine(principal.getActiveRoleId());
		return res;
	}

}
