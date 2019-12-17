
package acme.features.administrator.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequest.AuditorRequest;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorRequestAcceptService implements AbstractUpdateService<Administrator, AuditorRequest> {

	@Autowired
	private AdministratorAuditorRequestRepository repository;


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
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "firm", "statament", "status");
	}

	@Override
	public AuditorRequest findOne(final Request<AuditorRequest> request) {
		assert request != null;
		int arId = request.getModel().getInteger("id");
		AuditorRequest ar = this.repository.findOneById(arId);
		return ar;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;
		entity.setStatus(true);
		this.repository.save(entity);
		String firm = request.getModel().getString("firm");
		String statement = request.getModel().getString("statement");
		Auditor a = new Auditor();
		UserAccount c = entity.getUser().getUserAccount();
		a.setFirm(firm);
		a.setStatement(statement);
		a.setUserAccount(c);
		this.repository.save(a);

	}

}
