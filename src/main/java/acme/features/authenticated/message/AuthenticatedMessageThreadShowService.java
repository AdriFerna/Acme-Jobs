
package acme.features.authenticated.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.MessageThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageThreadShowService implements AbstractShowService<Authenticated, MessageThread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	// AbstractShowService<Authenticated, MessageThread> interface ----------------

	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;
		boolean result;
		int messageThreadId;
		Principal principal = request.getPrincipal();
		int authId = principal.getActiveRoleId();
		messageThreadId = request.getModel().getInteger("id");
		Integer cuenta = this.repository.checkIfUserIsInTheThread(authId, messageThreadId);
		result = cuenta > 0 ? true : false;

		return result;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment");

		List<Integer> usersOnTheThread = this.repository.findUsersOnTheThread(request.getModel().getInteger("id"));
		String[][] datafromUsers = this.repository.findDataFromUsers(usersOnTheThread);
		model.setAttribute("usersData", datafromUsers);
	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
