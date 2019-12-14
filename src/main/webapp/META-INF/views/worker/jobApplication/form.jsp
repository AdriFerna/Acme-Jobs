<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
<acme:form-integer code="worker.jobApplication.form.label.referenceNumber" path="referenceNumber"/>
<jstl:if test="${command != 'create' }">
<acme:form-moment code="worker.jobApplication.form.label.creationMoment" path="creationMoment" readonly="true"/>
<acme:form-textbox code="worker.jobApplication.form.label.status" path="status" readonly ="true"/>
</jstl:if>
<acme:form-textarea code="worker.jobApplication.form.label.statement" path="statement"/>
<acme:form-textarea code="worker.jobApplication.form.label.skills" path="skills"/>
<acme:form-textarea code="worker.jobApplication.form.label.qualifications" path="qualifications"/>
<jstl:if test="${status != 'pending' }">
<acme:form-textarea code="worker.jobApplication.form.label.justification" path="justification"/>
</jstl:if>
<jstl:if test="${status == 'pending' }">
<acme:form-submit test="${command == 'show' }"
	code="worker.jobApplication.form.button.update"
	action="/worker/job-application/update"/>
	<acme:form-submit test="${command == 'show' }"
	code="worker.jobApplication.form.button.delete"
	action="/worker/job-application/delete"/>
	<acme:form-submit test="${command == 'update' }"
	code="worker.jobApplication.form.button.update"
	action="/worker/job-application/update"/>
	<acme:form-submit test="${command == 'delete' }"
	code="worker.jobApplication.form.button.delete"
	action="/worker/job-application/delete"/>
</jstl:if>
	<acme:form-submit test="${command == 'create' }"
	code="worker.jobApplication.form.button.create"
	action="/worker/job-application/create"/>
	<acme:form-hidden path="idJob"/>
	<acme:form-return code="worker.jobApplication.form.button.return"/>
</acme:form>

<jstl:if test="${command != 'create' }">
<a href = /acme-jobs/worker/job/show?id=<jstl:out value="${job.id}"></jstl:out>>
<acme:message code="worker.job.message.job"/>
</a>
</jstl:if>

<acme:form>

</acme:form>