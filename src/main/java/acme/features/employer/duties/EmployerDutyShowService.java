
package acme.features.employer.duties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerDutyShowService implements AbstractShowService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		int idDuty = request.getModel().getInteger("id");
		Job j = this.repository.findJobByDutyId(idDuty);
		int employerId = request.getPrincipal().getActiveRoleId();
		boolean res = j.getEmployer().getId() == employerId;
		return res;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "description", "percentage", "job");

	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;
		int idDuty = request.getModel().getInteger("id");
		Duty d = this.repository.findOneDuty(idDuty);
		return d;
	}

}
