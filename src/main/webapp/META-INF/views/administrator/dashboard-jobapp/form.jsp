<%@page language="java"%>

			        
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
<acme:message code="administrator.dashboard.timeSeries.Pending"/> 
<canvas id = "timeSeriesPending" > </canvas>
</div>
<br>

<div>
<acme:message code="administrator.dashboard.timeSeries.Accepted"/> 
<canvas id = "timeSeriesAccepted" > </canvas>
</div>
<br>

<div>
<acme:message code="administrator.dashboard.timeSeries.Rejected"/> 
<canvas id = "timeSeriesRejected" > </canvas>
</div>

<!--  PENDING  -->
<script type = "text/javascript">
$(document).ready(function(){
	var CanvasAppsPending = document.getElementById("timeSeriesPending");
	Chart.defaults.global.defaultFontFamily = "Lato";
	Chart.defaults.global.defaultFontSize = 18;
	Chart.defaults.global.legend.display = false;

	var DataAppsPending = {
		    labels: [
					<jstl:forEach items = "${numberOfApplicationsPendingPerDayFromLastFourWeeks}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
		    ],
		    datasets: [
		        {fill: false,
		        	lineTension: 0,
		            data: [
		            	<jstl:forEach items = "${numberOfApplicationsPendingPerDayFromLastFourWeeks}" var = "item">
						"<jstl:out value = "${item[0]}" />" ,
						</jstl:forEach>
		            ],
		            backgroundColor : 'rgba(102,255,51,1)',
		            borderColor: 'rgba(255,0,0,1)'
		        }]
		};
	var pieChartAppsPending = new Chart(CanvasAppsPending, {
		  type: 'line',
		  data: DataAppsPending
		});
});

</script>


<!--  ACCEPTED  -->
<script type = "text/javascript">
$(document).ready(function(){
	var CanvasAppsAccepted = document.getElementById("timeSeriesAccepted");
	Chart.defaults.global.defaultFontFamily = "Lato";
	Chart.defaults.global.defaultFontSize = 18;
	Chart.defaults.global.legend.display = false;

	var DataAppsAccepted = {
		    labels: [
					<jstl:forEach items = "${numberOfApplicationsAcceptedPerDayFromLastFourWeeks}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
		    ],
		    datasets: [
		        {fill: false,
		        	lineTension: 0,
		            data: [
		            	<jstl:forEach items = "${numberOfApplicationsAcceptedPerDayFromLastFourWeeks}" var = "item">
						"<jstl:out value = "${item[0]}" />" ,
						</jstl:forEach>
		            ],
		            backgroundColor : 'rgba(102,255,51,1)',
		            borderColor: 'rgba(255,0,0,1)'
		        }]
		};
	var pieChartAppsAccepted = new Chart(CanvasAppsAccepted, {
		  type: 'line',
		  data: DataAppsAccepted
		});
});

</script>


<!--  REJECTED  -->
<script type = "text/javascript">
$(document).ready(function(){
	var CanvasAppsRejected = document.getElementById("timeSeriesRejected");
	Chart.defaults.global.defaultFontFamily = "Lato";
	Chart.defaults.global.defaultFontSize = 18;
	Chart.defaults.global.legend.display = false;

	var DataAppsRejected = {
		    labels: [
					<jstl:forEach items = "${numberOfApplicationsRejectedPerDayFromLastFourWeeks}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
		    ],
		    datasets: [
		        {fill: false,
		        	lineTension: 0,
		            data: [
		            	<jstl:forEach items = "${numberOfApplicationsRejectedPerDayFromLastFourWeeks}" var = "item">
						"<jstl:out value = "${item[0]}" />" ,
						</jstl:forEach>
		            ],
		            backgroundColor : 'rgba(102,255,51,1)',
		            borderColor: 'rgba(255,0,0,1)'
		        }]
		};
	var pieChartAppsRejected = new Chart(CanvasAppsRejected, {
		  type: 'line',
		  data: DataAppsRejected
		});
});

</script>