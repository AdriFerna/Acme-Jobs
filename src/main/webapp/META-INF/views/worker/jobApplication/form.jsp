<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
<acme:form-integer code="worker.jobApplication.form.label.referenceNumber" path="referenceNumber"/>
<acme:form-moment code="worker.jobApplication.form.label.creationMoment" path="creationMoment"/>
<acme:form-textbox code="worker.jobApplication.form.label.status" path="status"/>
<acme:form-textarea code="worker.jobApplication.form.label.statement" path="statement"/>
<acme:form-textarea code="worker.jobApplication.form.label.skills" path="skills"/>
<acme:form-textarea code="worker.jobApplication.form.label.qualifications" path="qualifications"/>
</acme:form>

<a href = /acme-jobs/authenticated/job/show?id=<jstl:out value="${job.id}"></jstl:out>>
<acme:message code="worker.job.message.job"/>
</a>

<acme:form>
<acme:form-return code="autheticated.jobApplication.form.button.return"/>
</acme:form>