/*
 * AuthenticatedProviderCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.messagethread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.AuthenticatedMessageThread;
import acme.entities.messages.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageThreadCreateService implements AbstractCreateService<Authenticated, MessageThread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedMessageThreadRepository repository;


	// AbstractCreateService<Authenticated, MessageThread> interface ---------------

	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");
	}

	@Override
	public MessageThread instantiate(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		Date creationMoment = new Date();
		Authenticated owner = this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId());
		result = new MessageThread();
		result.setCreationMoment(creationMoment);
		result.setOwner(owner);

		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<MessageThread> request, final MessageThread entity) {
		Date creationMoment;
		AuthenticatedMessageThread amt = new AuthenticatedMessageThread();
		amt.setThread(entity);
		amt.setUser(entity.getOwner());
		creationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMoment);
		this.repository.save(entity);
		this.repository.save(amt);
	}

}
