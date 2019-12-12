
package acme.features.administrator.dashboardJobApp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobApplication.JobApplication;
import acme.forms.DashboardJobApp;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardJobAppDisplayService implements AbstractShowService<Administrator, DashboardJobApp> {

	@Autowired
	private AdministratorDashboardJobAppRepository repository;


	@Override
	public boolean authorise(final Request<DashboardJobApp> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<DashboardJobApp> request, final DashboardJobApp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "numberOfApplicationsPendingPerDayFromLastFourWeeks", "numberOfApplicationsAcceptedPerDayFromLastFourWeeks", "numberOfApplicationsRejectedPerDayFromLastFourWeeks");

	}

	@Override
	public DashboardJobApp findOne(final Request<DashboardJobApp> request) {
		DashboardJobApp dja = new DashboardJobApp();
		Calendar ldt1 = Calendar.getInstance(TimeZone.getDefault());
		ldt1.add(Calendar.DAY_OF_MONTH, -28);
		Date queryDate = ldt1.getTime();
		Calendar today = Calendar.getInstance(TimeZone.getDefault());

		//ACCEPTED
		List<JobApplication> listAccepted = this.repository.findAccepted(queryDate);
		String[][] datosAccepted = new String[28][4];
		Integer iA = 0;
		while (ldt1.before(today)) {
			Integer cont = (int) listAccepted.stream().filter(x -> x.getUpdateMoment().before(ldt1.getTime())).count();
			datosAccepted[iA][0] = cont.toString();
			String fecha = Integer.toString(ldt1.get(Calendar.YEAR)) + "/" + Integer.toString(ldt1.get(Calendar.MONTH) + 1) + "/" + Integer.toString(ldt1.get(Calendar.DAY_OF_MONTH));
			datosAccepted[iA][1] = fecha;
			ldt1.add(Calendar.DAY_OF_MONTH, 1);
			iA++;
		}
		dja.setNumberOfApplicationsAcceptedPerDayFromLastFourWeeks(datosAccepted);

		//REJECTED
		Calendar ldt2 = Calendar.getInstance(TimeZone.getDefault());
		ldt2.add(Calendar.DAY_OF_MONTH, -28);
		List<JobApplication> listRejected = this.repository.findRejected(queryDate);
		String[][] datosRejected = new String[28][4];
		Integer iR = 0;

		while (ldt2.before(today)) {
			Integer cont = (int) listRejected.stream().filter(x -> x.getUpdateMoment().before(ldt2.getTime())).count();
			datosRejected[iR][0] = cont.toString();
			String fecha = Integer.toString(ldt2.get(Calendar.YEAR)) + "/" + Integer.toString(ldt2.get(Calendar.MONTH) + 1) + "/" + Integer.toString(ldt2.get(Calendar.DAY_OF_MONTH));
			datosRejected[iR][1] = fecha;
			ldt2.add(Calendar.DAY_OF_MONTH, 1);
			iR++;
		}
		dja.setNumberOfApplicationsRejectedPerDayFromLastFourWeeks(datosRejected);

		//PENDING
		Calendar ldt3 = Calendar.getInstance(TimeZone.getDefault());
		ldt3.add(Calendar.DAY_OF_MONTH, -28);
		List<JobApplication> listPending = this.repository.findPending(queryDate);
		String[][] datosPending = new String[28][2];
		Integer iP = 0;

		while (ldt3.before(today)) {
			Integer cont = 0;
			for (JobApplication j : listPending) {
				if (j.getCreationMoment().before(ldt3.getTime())) {
					if (j.getUpdateMoment() == null || j.getUpdateMoment().after(ldt3.getTime())) {
						cont++;
					}
				}
			}
			datosPending[iP][0] = cont.toString();
			String fecha = Integer.toString(ldt3.get(Calendar.YEAR)) + "/" + Integer.toString(ldt3.get(Calendar.MONTH) + 1) + "/" + Integer.toString(ldt3.get(Calendar.DAY_OF_MONTH));
			datosPending[iP][1] = fecha;
			ldt3.add(Calendar.DAY_OF_MONTH, 1);
			iP++;
		}
		dja.setNumberOfApplicationsPendingPerDayFromLastFourWeeks(datosPending);

		return dja;
	}

}
