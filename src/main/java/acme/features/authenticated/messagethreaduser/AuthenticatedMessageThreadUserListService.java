
package acme.features.authenticated.messagethreaduser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Authenticated_MessageThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageThreadUserListService implements AbstractListService<Authenticated, Authenticated_MessageThread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageThreadUserRepository repository;


	// AbstractListService<Authenticated, Authenticated_MessageThread> interface ----------------

	@Override
	public boolean authorise(final Request<Authenticated_MessageThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Authenticated_MessageThread> request, final Authenticated_MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "user.userAccount.username");
	}

	@Override
	public Collection<Authenticated_MessageThread> findMany(final Request<Authenticated_MessageThread> request) {
		assert request != null;

		Collection<Authenticated_MessageThread> result;

		int IdThread = request.getModel().getInteger("idThread");
		result = this.repository.findUsersOnTheThread(IdThread);

		return result;
	}

}
