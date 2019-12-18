
package acme.features.employer.jobs;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customParams.Configuration;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	private EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		int idJob = request.getModel().getInteger("id");
		Job j = this.repository.findOneById(idJob);
		int employerId = request.getPrincipal().getActiveRoleId();
		boolean res = j.getEmployer().getId() == employerId && j.getStatus().equals("draft");
		return res;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "status");

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "referenceNumber", "title", "deadline", "salary", "moreInfo", "description");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job j = this.repository.findOneById(request.getModel().getInteger("id"));
		return j;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date date = new Date();
		Calendar cl = Calendar.getInstance(TimeZone.getDefault());
		cl.add(Calendar.DAY_OF_MONTH, 7);
		date = cl.getTime();
		Configuration c = this.repository.getConfigParams();
		String refNumberWithoutUpdate = this.repository.SelectRefNumberById(entity.getId());

		if (!errors.hasErrors("deadline")) {
			boolean esAntes = entity.getDeadline().before(date);
			errors.state(request, !esAntes, "deadline", "employer.job.error.deadline");
		}

		if (!errors.hasErrors("salary")) {
			boolean moneyCurrencyMax = entity.getSalary().getCurrency().equals("EUROS") || entity.getSalary().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "salary", "employer.job.error.salaryCurrency");
			boolean noNegSalary = entity.getSalary().getAmount() < 0.0;
			errors.state(request, !noNegSalary, "salary", "employer.job.error.noNegSalary");
		}

		if (!refNumberWithoutUpdate.equals(entity.getReferenceNumber())) {
			boolean repetido = this.repository.getRefNumbers(entity.getReferenceNumber()) > 0;
			errors.state(request, !repetido, "referenceNumber", "employer.job.error.referenceNumber");
			boolean refSpam = SpamCheck.checkSpam(entity.getReferenceNumber(), c);
			errors.state(request, !refSpam, "referenceNumber", "employer.job.error.referenceNumber.spam");
		}

		if (!errors.hasErrors("title")) {
			boolean titleSpam = SpamCheck.checkSpam(entity.getTitle(), c);
			errors.state(request, !titleSpam, "title", "employer.job.error.title.spam");
		}
		if (!errors.hasErrors("description")) {
			boolean descSpam = SpamCheck.checkSpam(entity.getDescription(), c);
			errors.state(request, !descSpam, "description", "employer.job.error.description.spam");
		}
		if (!errors.hasErrors("moreInfo")) {
			boolean moreInfoSpam = SpamCheck.checkSpam(entity.getMoreInfo(), c);
			errors.state(request, !moreInfoSpam, "moreInfo", "employer.job.error.moreInfo.spam");
		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
