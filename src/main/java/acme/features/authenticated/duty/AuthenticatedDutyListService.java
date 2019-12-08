
package acme.features.authenticated.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDutyListService implements AbstractListService<Authenticated, Duty> {

	@Autowired
	AuthenticatedDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		boolean result;
		Integer jobId;
		Principal principal;
		principal = request.getPrincipal();
		Job job;
		jobId = request.getModel().getInteger("idJob");
		job = this.repository.findOneById(jobId);

		if (principal.hasRole(Employer.class)) {
			Employer employer;
			employer = job.getEmployer();
			result = job.getStatus().equals("published") || !job.getStatus().equals("published") && employer.getUserAccount().getId() == principal.getAccountId();
		} else {
			result = job.getStatus().equals("published");
		}
		return result;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percentage", "job.title");

	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;
		int idJob = request.getModel().getInteger("idJob");

		Collection<Duty> res;
		res = this.repository.findMany(idJob);
		return res;
	}

}
