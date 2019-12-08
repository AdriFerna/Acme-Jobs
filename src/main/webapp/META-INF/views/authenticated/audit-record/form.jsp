<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.audit-record.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.audit-record.form.label.moment" path="moment"/>
	<acme:form-textarea code="authenticated.audit-record.form.label.body" path="body"/>
	<acme:form-money code="authenticated.audit-record.form.label.auditor.firm" path="auditor.firm"/>
	<acme:form-textarea code="authenticated.audit-record.form.label.job.referenceNumber" path="job.referenceNumber"/>
	<acme:form-money code="authenticated.audit-record.form.label.job.title" path="job.title"/>

	<acme:form-return code="authenticated.audit-record.form.label.return"/>
</acme:form>
