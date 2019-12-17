
package acme.features.auditor.auditRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditRecord.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/audit-record/")
public class AuditorAuditRecordController extends AbstractController<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordCreateService		createService;
	@Autowired
	private AuditorAuditRecordListService		listMineService;
	@Autowired
	private AuditorAuditRecordShowService		showService;
	@Autowired
	private AuditorAuditRecordPublishService	publishService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.PUBLISH, BasicCommand.UPDATE, this.publishService);
	}

}
