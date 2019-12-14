
package acme.features.worker.jobApplication;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.jobApplication.JobApplication;
import acme.entities.roles.Worker;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/worker/job-application/")
public class WorkerJobApplicationController extends AbstractController<Worker, JobApplication> {

	@Autowired
	private WorkerJobApplicationListMineService	listMineService;

	@Autowired
	private WorkerJobApplicationShowService		showService;

	@Autowired
	private WorkerJobApplicationCreateService	createService;

	@Autowired
	private WorkerJobApplicationUpdateService	updateService;

	@Autowired
	private WorkerJobApplicationDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
