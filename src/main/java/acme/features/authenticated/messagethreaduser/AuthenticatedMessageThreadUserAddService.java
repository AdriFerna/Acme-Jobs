
package acme.features.authenticated.messagethreaduser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.AuthenticatedMessageThread;
import acme.entities.messages.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageThreadUserAddService implements AbstractCreateService<Authenticated, AuthenticatedMessageThread> {

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
		List<Authenticated> userNotInTheThread = this.repository.findUserNotInTheThread(id);
		model.setAttribute("usersToAdd", userNotInTheThread);

		if (request.isMethod(HttpMethod.GET)) {
			int idThread = request.getModel().getInteger("idThread");
			model.setAttribute("idThread", idThread);
		}

	}

	@Override
	public AuthenticatedMessageThread instantiate(final Request<AuthenticatedMessageThread> request) {
		assert request != null;
		AuthenticatedMessageThread amt = new AuthenticatedMessageThread();
		int idThread = request.getModel().getInteger("idThread");
		MessageThread mt = this.repository.findMessageThreadById(idThread);
		amt.setThread(mt);

		//ESTE NO ES EL USUARIO FINAL QUE SE AGREGA A LA TABLA ES UN PLACEHOLDER
		Authenticated aprovisional = this.repository.findAuthenticatedById(request.getPrincipal().getActiveRoleId());
		amt.setUser(aprovisional);

		return amt;
	}

	@Override
	public void validate(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity) {
		assert request != null;
		assert entity != null;

		int idAuth = request.getModel().getInteger("userAdded");
		Authenticated a = this.repository.findAuthenticatedById(idAuth);
		entity.setUser(a);
		this.repository.save(entity);
	}

}
