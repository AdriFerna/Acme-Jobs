
package acme.features.authenticated.auditorrequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.auditorRequest.AuditorRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/auditor-request/")
public class AuthenticatedAuditorRequestController extends AbstractController<Authenticated, AuditorRequest> {

	@Autowired
	private AuthenticatedAuditorRequestCreateService createService;


	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
