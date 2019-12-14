
package acme.features.employer.duties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.jobs.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/duty/")
public class EmployerDutyController extends AbstractController<Employer, Duty> {

	@Autowired
	private EmployerDutyListService		listService;

	@Autowired
	private EmployerDutyAddService		addService;

	@Autowired
	private EmployerDutyUpdateService	updateService;

	@Autowired
	private EmployerDutyShowService		showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.ADD, BasicCommand.CREATE, this.addService);
	}

}
