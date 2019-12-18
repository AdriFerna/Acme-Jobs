<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
<acme:form-integer code="worker.job.form.label.referenceNumber" path="referenceNumber"/>
<acme:form-textbox code="worker.job.form.label.status" path="status"/>
<acme:form-textbox code="worker.job.form.label.title" path="title"/>
<acme:form-moment code="worker.job.form.label.deadline" path="deadline"/>
<acme:form-double code="worker.job.form.label.salary" path="salary"/>
<acme:form-textbox code="worker.job.form.label.moreInfo" path="moreInfo"/>
<acme:form-textarea code="worker.job.form.label.description" path="description"/>
</acme:form>

<acme:form-submit code="authenticated.job.message.duties" action ="/authenticated/duty/list?idJob=${id}" method="get" />

<acme:form-submit code = "authenticated.job.message.audit-record" action="/authenticated/audit-record/list?idJob=${id}" method="get"/>

<acme:form>
<acme:form-submit test="${command == 'show' }"
	code="worker.jobApplication.form.button.create"
	action="/worker/job-application/create?idJob=${id}"
	method = "get"/>
<acme:form-return code="worker.job.form.button.return"/>
</acme:form>