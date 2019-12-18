
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecord.AuditRecord;
import acme.entities.customParams.Configuration;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository repository;


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
		request.bind(entity, errors, "moment");

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

		request.unbind(entity, model, "title", "body");

	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		AuditRecord res;
		res = new AuditRecord();
		Date t = new Date(System.currentTimeMillis() - 1);
		res.setMoment(t);

		int idJob = request.getModel().getInteger("idJob");
		Job j = this.repository.findJobById(idJob);
		res.setJob(j);
		Auditor a = this.repository.findAuditorById(request.getPrincipal().getActiveRoleId());
		res.setAuditor(a);

		res.setStatus("draft");

		return res;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		Configuration c = this.repository.getConfigParams();
		if (!errors.hasErrors("title")) {
			boolean titleSpam = SpamCheck.checkSpam(entity.getTitle(), c);
			errors.state(request, !titleSpam, "title", "auditor.auditrecord.error.title.spam");
		}

		if (!errors.hasErrors("body")) {
			boolean bodySpam = SpamCheck.checkSpam(entity.getBody(), c);
			errors.state(request, !bodySpam, "body", "auditor.auditrecord.error.body.spam");
		}

	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
