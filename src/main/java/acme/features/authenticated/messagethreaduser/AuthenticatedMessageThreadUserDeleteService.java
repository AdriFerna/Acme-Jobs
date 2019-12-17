
package acme.features.authenticated.messagethreaduser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.AuthenticatedMessageThread;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedMessageThreadUserDeleteService implements AbstractDeleteService<Authenticated, AuthenticatedMessageThread> {

	@Autowired
	private AuthenticatedMessageThreadUserRepository repository;


	@Override
	public boolean authorise(final Request<AuthenticatedMessageThread> request) {
		assert request != null;

		int idThread = request.getModel().getInteger("idThread");
		Authenticated owner = this.repository.findOwnerOfTheThread(idThread);
		int idAuthenticatedPrincipal = request.getPrincipal().getActiveRoleId();
		return owner.getId() == idAuthenticatedPrincipal;
	}

	@Override
	public void bind(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int id = entity.getThread().getId();
		request.unbind(entity, model, "user", "thread");
		List<Authenticated> usersInTheThread = this.repository.findUsersInTheThread(id, request.getPrincipal().getActiveRoleId());
		model.setAttribute("usersToRemove", usersInTheThread);

		if (request.isMethod(HttpMethod.GET)) {
			int idThread = request.getModel().getInteger("idThread");
			model.setAttribute("idThread", idThread);
		}

	}

	@Override
	public AuthenticatedMessageThread findOne(final Request<AuthenticatedMessageThread> request) {
		assert request != null;
		AuthenticatedMessageThread amt = new AuthenticatedMessageThread();
		int idThread = request.getModel().getInteger("idThread");
		amt = this.repository.findOneById(request.getPrincipal().getActiveRoleId(), idThread);
		return amt;
	}

	@Override
	public void validate(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity) {
		assert request != null;
		assert entity != null;

		AuthenticatedMessageThread amt;
		int userToRemove = request.getModel().getInteger("userRemoved");
		int idThread = request.getModel().getInteger("idThread");
		amt = this.repository.findOneById(userToRemove, idThread);
		this.repository.delete(amt);

	}
}
