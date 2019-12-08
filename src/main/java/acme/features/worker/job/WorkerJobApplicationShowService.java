
package acme.features.worker.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobApplication.JobApplication;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerJobApplicationShowService implements AbstractShowService<Worker, JobApplication> {

	@Autowired
	WorkerJobApplicationRepository repository;


	@Override
	public boolean authorise(final Request<JobApplication> request) {
		assert request != null;
		boolean result;
		int jobApplicationId;
		JobApplication jobApplication;
		Worker worker;
		Principal principal;

		jobApplicationId = request.getModel().getInteger("id");
		jobApplication = this.repository.findOneById(jobApplicationId);
		worker = jobApplication.getWorker();
		principal = request.getPrincipal();
		result = worker.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<JobApplication> request, final JobApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "referenceNumber", "creationMoment", "status", "statement", "skills", "qualifications", "job", "worker");

	}

	@Override
	public JobApplication findOne(final Request<JobApplication> request) {
		assert request != null;
		JobApplication result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
