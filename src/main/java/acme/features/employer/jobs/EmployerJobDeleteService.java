
package acme.features.employer.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecord.AuditRecord;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		int idJob = request.getModel().getInteger("id");
		Job j = this.repository.findOneById(idJob);
		int employerId = request.getPrincipal().getActiveRoleId();
		boolean res = j.getEmployer().getId() == employerId;
		return res;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "referenceNumber", "title", "deadline", "salary", "moreInfo", "description");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job j = this.repository.findOneById(request.getModel().getInteger("id"));
		return j;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		int idJob = request.getModel().getInteger("id");
		Double numberOfJobApplications = this.repository.getJobApplicationsOfTheJob(idJob);
		boolean aplicado = numberOfJobApplications.equals(0.);
		errors.state(request, aplicado, "description", "employer.job.delete.error");

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		List<Duty> dutiesOfTheJob = this.repository.findDutiesOfTheJob(entity.getId());
		this.repository.deleteAll(dutiesOfTheJob);
		List<AuditRecord> auditrecordsOfTheJob = this.repository.findAuditRecordsOfTheJob(entity.getId());
		this.repository.deleteAll(auditrecordsOfTheJob);

		this.repository.delete(entity);

	}

}
