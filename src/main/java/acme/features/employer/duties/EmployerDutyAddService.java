
package acme.features.employer.duties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customParams.Configuration;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyAddService implements AbstractCreateService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		int idJob = request.getModel().getInteger("idJob");
		Job j = this.repository.findOneById(idJob);
		int employerId = request.getPrincipal().getActiveRoleId();
		boolean res = j.getEmployer().getId() == employerId && j.getStatus().equals("draft");
		return res;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "description", "percentage");

		if (request.isMethod(HttpMethod.GET)) {
			int idJob = request.getModel().getInteger("idJob");
			model.setAttribute("idJob", idJob);
		}

	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		assert request != null;
		int idJob = request.getModel().getInteger("idJob");
		Job j = this.repository.findOneById(idJob);
		Duty d = new Duty();
		d.setJob(j);
		return d;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration c = this.repository.getConfigParams();
		int idJob = request.getModel().getInteger("idJob");
		Double sumaTotalPercentages = this.repository.getTotalPercentageOfDuties(idJob);

		if (!errors.hasErrors("title")) {
			boolean titleSpam = SpamCheck.checkSpam(entity.getTitle(), c);
			errors.state(request, !titleSpam, "title", "employer.duty.error.title.spam");
		}
		if (!errors.hasErrors("description")) {
			boolean descSpam = SpamCheck.checkSpam(entity.getDescription(), c);
			errors.state(request, !descSpam, "description", "employer.duty.error.description.spam");
		}
		if (!errors.hasErrors("percentage")) {
			boolean percentageSuperado = sumaTotalPercentages + entity.getPercentage() > 100.0;
			errors.state(request, !percentageSuperado, "percentage", "employer.duty.error.percentageSuperado");
		}

	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
