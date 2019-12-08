<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
<acme:form-integer code="employer.jobApplication.form.label.referenceNumber" path="referenceNumber"/>
<acme:form-moment code="employer.jobApplication.form.label.creationMoment" path="creationMoment"/>
<acme:form-textbox code="employer.jobApplication.form.label.status" path="status"/>
<acme:form-textarea code="employer.jobApplication.form.label.statement" path="statement"/>
<acme:form-textarea code="employer.jobApplication.form.label.skills" path="skills"/>
<acme:form-textarea code="employer.jobApplication.form.label.qualifications" path="qualifications"/>
</acme:form>


<a href = /acme-jobs/employer/job/show?id=<jstl:out value="${job.id}"></jstl:out>>
<acme:message code="employer.job.message.job"/>
</a>

<acme:form>
<acme:form-return code="autheticated.job.form.button.return"/>
</acme:form>