
package acme.features.authenticated.auditorrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequest.AuditorRequest;
import acme.entities.customParams.Configuration;
import acme.entities.roles.Auditor;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorRequestCreateService implements AbstractCreateService<Authenticated, AuditorRequest> {

	@Autowired
	private AuthenticatedAuditorRequestRepository repository;


	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;
		/*
		 * int id = request.getPrincipal().getAccountId();
		 * int yaRealizada = this.repository.findAuditorRequestByUserAccountId(id);
		 * boolean res = yaRealizada >= 1 ? false : true;
		 */

		return !request.getPrincipal().hasRole(Auditor.class);
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "status", "user");

	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int authenticatedId = request.getPrincipal().getActiveRoleId();
		int countPending = this.repository.findAuditorRequestByAuthenticatedId(authenticatedId);
		if (countPending > 0) {
			String alreadyRequested = "authenticated.auditor-request.requested.error";
			model.setAttribute("requestedError", alreadyRequested);
		}
		request.unbind(entity, model, "firm", "statement");

	}

	@Override
	public AuditorRequest instantiate(final Request<AuditorRequest> request) {
		assert request != null;

		Principal principal;

		AuditorRequest ar;
		Authenticated at;
		principal = request.getPrincipal();
		at = this.repository.findAuthenticatedById(principal.getActiveRoleId());
		ar = new AuditorRequest();
		ar.setUser(at);
		return ar;

	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration c = this.repository.getConfigParams();
		if (!errors.hasErrors("firm")) {
			boolean firmSpam = SpamCheck.checkSpam(entity.getFirm(), c);
			errors.state(request, !firmSpam, "firm", "authenticated.auditrequest.error.firm.spam");
		}

		if (!errors.hasErrors("statement")) {
			boolean statementSpam = SpamCheck.checkSpam(entity.getStatement(), c);
			errors.state(request, !statementSpam, "statement", "authenticated.auditrequest.error.statement.spam");
		}
	}

	@Override
	public void create(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
