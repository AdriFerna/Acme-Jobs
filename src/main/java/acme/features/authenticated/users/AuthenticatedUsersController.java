
package acme.features.authenticated.users;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@RequestMapping("/authenticated/users/")
@Controller
public class AuthenticatedUsersController extends AbstractController<Authenticated, Authenticated> {

	@Autowired
	private AuthenticatedUsersListService listService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}
}
