
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecord.AuditRecord;
import acme.entities.customParams.Configuration;
import acme.entities.roles.Auditor;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditRecordPublishService implements AbstractUpdateService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		int idAuditRecord = request.getModel().getInteger("id");
		AuditRecord audit;
		audit = this.repository.findOneById(idAuditRecord);
		int auditorId = request.getPrincipal().getActiveRoleId();
		Boolean res = audit.getAuditor().getId() == auditorId && audit.getStatus().equals("draft");
		return res;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "moment", "auditor.firm", "job.title", "job.referenceNumber", "status");

	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;
		int idAuditRecord = request.getModel().getInteger("id");
		AuditRecord audit;
		audit = this.repository.findOneById(idAuditRecord);
		return audit;

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
	public void update(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;
		entity.setStatus("published");
		this.repository.save(entity);

	}

}
