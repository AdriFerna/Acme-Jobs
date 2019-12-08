
package acme.features.authenticated.auditrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecord.AuditRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuditRecordShowService implements AbstractShowService<Authenticated, AuditRecord> {

	@Autowired
	AuthenticatedAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		boolean result;
		int auditId;
		auditId = request.getModel().getInteger("id");
		AuditRecord ar = this.repository.findOneById(auditId);
		result = ar.getStatus().equals("published");
		return result;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "moment", "body", "auditor.firm", "job.referenceNumber", "job.title");

	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;
		AuditRecord ar;
		int auditId;
		auditId = request.getModel().getInteger("id");
		ar = this.repository.findOneById(auditId);
		return ar;
	}
}
