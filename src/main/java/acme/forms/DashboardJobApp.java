
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardJobApp implements Serializable {

	private static final long	serialVersionUID	= 1L;

	String[][]					numberOfApplicationsPendingPerDayFromLastFourWeeks;
	String[][]					numberOfApplicationsAcceptedPerDayFromLastFourWeeks;
	String[][]					numberOfApplicationsRejectedPerDayFromLastFourWeeks;

}
