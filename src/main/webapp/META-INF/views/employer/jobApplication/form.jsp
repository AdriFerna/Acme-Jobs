<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
<acme:form-integer code="employer.jobApplication.form.label.referenceNumber" path="referenceNumber" readonly="true"/>
<acme:form-moment code="employer.jobApplication.form.label.creationMoment" path="creationMoment" readonly="true"/>
<acme:form-textbox code="employer.jobApplication.form.label.status" path="status" readonly="true"/>
<acme:form-textarea code="employer.jobApplication.form.label.statement" path="statement" readonly="true"/>
<acme:form-textarea code="employer.jobApplication.form.label.skills" path="skills" readonly="true"/>
<acme:form-textarea code="employer.jobApplication.form.label.justification" path="justification"/>
<acme:form-textarea code="employer.jobApplication.form.label.qualifications" path="qualifications" readonly="true"/>
<jstl:if test="${status == 'pending' }">
<acme:form-submit test="${command == 'show' }"
	code="employer.jobApplication.form.button.accept"
	action="/employer/job-application/accept"/>
	<acme:form-submit test="${command == 'accept' }"
	code="employer.jobApplication.form.button.accept"
	action="/employer/job-application/accept"/>
	<acme:form-submit test="${command == 'show' }"
	code="employer.jobApplication.form.button.reject"
	action="/employer/job-application/reject"/>
	<acme:form-submit test="${command == 'reject' }"
	code="employer.jobApplication.form.button.reject"
	action="/employer/job-application/reject"/>
	
</jstl:if>
</acme:form>

<acme:form-submit test = "${command == 'show' }" action="/employer/job/show?id=${job.id}" code="employer.job.message.job" method="get"/>



<acme:form>
<acme:form-return code="autheticated.job.form.button.return"/>
</acme:form>