
package acme.features.administrator.dashboardJobApp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.forms.DashboardJobApp;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/dashboard-jobapp/")
public class AdministratorDashboardJobAppController extends AbstractController<Administrator, DashboardJobApp> {

	@Autowired
	private AdministratorDashboardJobAppDisplayService displayService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.DISPLAY, BasicCommand.SHOW, this.displayService);
	}
}
