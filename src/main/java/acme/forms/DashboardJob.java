
package acme.forms;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardJob implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Double						averageNumberOfJobsPerEmployer;
	Double						averageNumberOfApplicationsPerEmployer;
	Double						averageNumberOfApplicationsPerWorker;

	List<List<String>>			ratioOfJobsPerStatus;
	List<List<String>>			ratioOfApplicationsPerStatus;

}
