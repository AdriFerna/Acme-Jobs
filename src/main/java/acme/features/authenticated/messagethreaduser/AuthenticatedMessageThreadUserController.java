
package acme.features.authenticated.messagethreaduser;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.messages.AuthenticatedMessageThread;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/authenticated-message-thread/")
public class AuthenticatedMessageThreadUserController extends AbstractController<Authenticated, AuthenticatedMessageThread> {

	@Autowired
	private AuthenticatedMessageThreadUserAddService	addService;
	@Autowired
	private AuthenticatedMessageThreadUserDeleteService	deleteService;


	// Constructors -----------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.ADD, BasicCommand.CREATE, this.addService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
