
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecord.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.features.auditor.job.AuditorJobRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository	repository;

	@Autowired
	AuditorJobRepository			repositoryJOB;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "moment", "auditor");

	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			int idJob = request.getModel().getInteger("idJob");
			model.setAttribute("idJob", idJob);
		}

		request.unbind(entity, model, "title", "status", "body", "job");

	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		AuditRecord res;
		res = new AuditRecord();
		Date t = new Date(System.currentTimeMillis() - 1);
		res.setMoment(t);

		int idJob = request.getModel().getInteger("idJob");
		Job j = this.repositoryJOB.findOneById(idJob);

		res.setJob(j);
		return res;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		Principal user;
		user = request.getPrincipal();

		Auditor auditor;
		auditor = this.repository.findAuditorByUserAcountId(user.getAccountId());
		entity.setAuditor(auditor);

		this.repository.save(entity);

	}

}
