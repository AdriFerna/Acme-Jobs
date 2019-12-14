
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.entities.messages.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			int idThread = request.getModel().getInteger("idThread");
			model.setAttribute("idThread", idThread);
		}

		request.unbind(entity, model);

	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Message result;

		int ThreadId;
		MessageThread mt;
		Authenticated user;
		Date creationMoment = new Date();

		Principal principal;

		ThreadId = request.getModel().getInteger("idThread");
		mt = this.repository.findMessagesThread(ThreadId);

		principal = request.getPrincipal();
		int idUser = principal.getActiveRoleId();
		user = this.repository.findUser(idUser);

		result = new Message();
		result.setThread(mt);
		result.setUser(user);
		result.setCreationMoment(creationMoment);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Message> request, final Response<Message> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
