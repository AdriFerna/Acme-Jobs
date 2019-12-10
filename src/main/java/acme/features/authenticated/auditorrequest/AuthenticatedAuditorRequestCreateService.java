
package acme.features.authenticated.auditorrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequest.AuditorRequest;
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
		return true;
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "status");

	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "firm", "statement");

		int id = request.getPrincipal().getAccountId();
		Integer yaRealizada = this.repository.findAuditorRequestByUserAccountId(id);

		String realizada = yaRealizada > 0 ? "realizada" : "no realizada";
		model.setAttribute("realizada", realizada);

	}

	@Override
	public AuditorRequest instantiate(final Request<AuditorRequest> request) {
		assert request != null;

		Principal principal;
		int userAccountId;
		AuditorRequest ar;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		ar = new AuditorRequest();
		ar.setUserAccountId(userAccountId);
		return ar;

	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
