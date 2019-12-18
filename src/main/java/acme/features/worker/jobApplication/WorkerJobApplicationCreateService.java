
package acme.features.worker.jobApplication;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobApplication.JobApplication;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerJobApplicationCreateService implements AbstractCreateService<Worker, JobApplication> {

	@Autowired
	WorkerJobApplicationRepository repository;


	@Override
	public boolean authorise(final Request<JobApplication> request) {
		assert request != null;

		int idJob = request.getModel().getInteger("idJob");
		Calendar c = Calendar.getInstance(TimeZone.getDefault());
		Job j = this.repository.getJob(idJob);
		boolean res = j.getDeadline().after(c.getTime()) && j.getStatus().equals("published");

		return res;
	}

	@Override
	public void bind(final Request<JobApplication> request, final JobApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "status", "justification", "job", "worker", "updateMoment");

	}

	@Override
	public void unbind(final Request<JobApplication> request, final JobApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "statement", "skills", "qualifications");

		if (request.isMethod(HttpMethod.GET)) {
			int idJob = request.getModel().getInteger("idJob");
			model.setAttribute("idJob", idJob);
		}

	}

	@Override
	public JobApplication instantiate(final Request<JobApplication> request) {
		JobApplication res;
		res = new JobApplication();
		Date t = new Date(System.currentTimeMillis() - 1);
		res.setCreationMoment(t);
		res.setJustification("");
		res.setStatus("pending");
		res.setUpdateMoment(null);
		Worker w = this.repository.getWorker(request.getPrincipal().getActiveRoleId());
		res.setQualifications(w.getQualifications());
		res.setSkills(w.getSkills());
		res.setWorker(w);
		int idJob = request.getModel().getInteger("idJob");
		res.setJob(this.repository.getJob(idJob));
		return res;
	}

	@Override
	public void validate(final Request<JobApplication> request, final JobApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("referenceNumber")) {
			boolean repetido = this.repository.getReferenceNumbers(entity.getReferenceNumber()) > 0;
			errors.state(request, !repetido, "referenceNumber", "worker.jobApplication.error.referenceNumber");
		}
		//		if (!errors.hasErrors("statement")) {
		//
		//		}
		//		if (!errors.hasErrors("skills")) {
		//
		//		}
		//		if (!errors.hasErrors("qualifications")) {
		//
		//		}

	}

	@Override
	public void create(final Request<JobApplication> request, final JobApplication entity) {
		Date creationMoment;

		creationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMoment);
		this.repository.save(entity);
	}

}
