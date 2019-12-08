
package acme.features.administrator.dashboardJob;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.forms.DashboardJob;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/dashboard-job/")
public class AdministratorDashboardJobController extends AbstractController<Administrator, DashboardJob> {

	@Autowired
	private AdministratorDashboardJobDisplayService displayService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.DISPLAY, BasicCommand.SHOW, this.displayService);
	}
}
