
package acme.features.authenticated.messagethreaduser;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.messages.Authenticated_MessageThread;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/message-thread-user")
public class AuthenticatedMessageThreadUserController extends AbstractController<Authenticated, Authenticated_MessageThread> {

	// Internal state

	@Autowired
	private AuthenticatedMessageThreadUserListService listService;

	//	@Autowired
	//	private AuthenticatedMessageThreadShowService	showService;

	//	@Autowired
	//	private AuthenticatedMessageThreadCreateService	createService;


	// Constructors -----------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		//		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		//		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
