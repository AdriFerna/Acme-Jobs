
package acme.features.employer.jobApplication;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobApplication.JobApplication;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobApplicationAcceptService implements AbstractUpdateService<Employer, JobApplication> {

	@Autowired
	EmployerJobApplicationRepository repository;


	@Override
	public boolean authorise(final Request<JobApplication> request) {
		assert request != null;
		boolean result;
		int jobApplicationId;
		JobApplication jobApplication;
		Employer employer;
		Principal principal;

		jobApplicationId = request.getModel().getInteger("id");
		jobApplication = this.repository.findOneById(jobApplicationId);
		employer = jobApplication.getJob().getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<JobApplication> request, final JobApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "referenceNumber", "creationMoment", "statement", "skills", "qualifications", "job", "worker");

	}

	@Override
	public void unbind(final Request<JobApplication> request, final JobApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "updateMoment", "justification");

	}

	@Override
	public JobApplication findOne(final Request<JobApplication> request) {
		assert request != null;
		int JobApplicationId = request.getModel().getInteger("id");
		JobApplication jA = this.repository.findOneById(JobApplicationId);
		return jA;
	}

	@Override
	public void validate(final Request<JobApplication> request, final JobApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//		if(!errors.hasErrors("justification")) {
		//
		//		}

	}

	@Override
	public void update(final Request<JobApplication> request, final JobApplication entity) {
		assert request != null;
		assert entity != null;
		entity.setStatus("accepted");

		Date updateMoment;

		updateMoment = new Date(System.currentTimeMillis() - 1);
		entity.setUpdateMoment(updateMoment);
		this.repository.save(entity);
	}

}
