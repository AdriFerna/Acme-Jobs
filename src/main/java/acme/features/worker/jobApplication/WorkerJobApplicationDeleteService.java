
package acme.features.worker.jobApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobApplication.JobApplication;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class WorkerJobApplicationDeleteService implements AbstractDeleteService<Worker, JobApplication> {

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
		return false;
	}

	@Override
	public void bind(final Request<JobApplication> request, final JobApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "status", "justification", "worker", "job", "updateMoment");

	}

	@Override
	public void unbind(final Request<JobApplication> request, final JobApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "statement", "skills", "qualifications");
	}

	@Override
	public JobApplication findOne(final Request<JobApplication> request) {
		assert request != null;

		JobApplication res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneById(id);
		return res;
	}

	@Override
	public void validate(final Request<JobApplication> request, final JobApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean pending = entity.getStatus() == "pending";
		errors.state(request, !pending, "status", "worker.jobApplication.error.status");
	}

	@Override
	public void delete(final Request<JobApplication> request, final JobApplication entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
