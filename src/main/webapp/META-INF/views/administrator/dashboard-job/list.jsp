<%--
- list.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
<acme:form-double code="administrator.dashboard.legend.avgJobsEmployer" path="averageNumberOfJobsPerEmployer"/>
<acme:form-double code="administrator.dashboard.legend.avgAppsEmployer" path="averageNumberOfApplicationsPerEmployer"/>
<acme:form-double code="administrator.dashboard.legend.avgAppsWorker" path="averageNumberOfApplicationsPerWorker"/>
</acme:form>


<div style="height:25%; width:25%; display:inline-block">
<acme:message code="administrator.dashboard.chart.jobsPerStatus"/> 
<canvas id = "numberOfJobsPerStatus" > </canvas>
</div>


<div style="height:25%; width:25%;display:inline-block">
<acme:message code="administrator.dashboard.chart.appsPerStatus"/> 
<canvas id = "numberOfAppsPerStats" > </canvas>
</div> </br>


<script type = "text/javascript">
$(document).ready(function(){
var CanvasJobsPerStatus = document.getElementById("numberOfJobsPerStatus");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;

var DataJobsPerStatus = {
	    labels: [
				<jstl:forEach items = "${ratioOfJobsPerStatus}" var = "item">
				"<jstl:out value = "${item[1]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${ratioOfJobsPerStatus}" var = "item">
					"<jstl:out value = "${item[0]}" />" ,
					</jstl:forEach>
	            ], 
	            backgroundColor :["red", "green"]
	        }]
	};
var pieChartJobStatus = new Chart(CanvasJobsPerStatus, {
	  type: 'pie',
	  data: DataJobsPerStatus
	});
});
</script>



<script type = "text/javascript">
$(document).ready(function(){
var CanvasAppsPerStatus = document.getElementById("numberOfAppsPerStats");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;

var DataAppsPerStatus = {
	    labels: [
				<jstl:forEach items = "${ratioOfApplicationsPerStatus}" var = "item">
				"<jstl:out value = "${item[1]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${ratioOfApplicationsPerStatus}" var = "item">
					"<jstl:out value = "${item[0]}" />" ,
					</jstl:forEach>
	            ],
	            backgroundColor :["red", "green", "blue"]
	        }]
	};
var pieChartAppsPerStatus = new Chart(CanvasAppsPerStatus, {
	  type: 'pie',
	  data: DataAppsPerStatus
	});
});
</script>


	    	
	 
