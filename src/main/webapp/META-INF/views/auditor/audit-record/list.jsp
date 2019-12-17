<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="auditor.auditRecord.form.label.moment" path="moment" width="20%"/>
	<acme:list-column code="auditor.auditRecord.form.label.title" path="title" width="40%"/>
	<acme:list-column code="auditor.auditRecord.form.label.status" path="status" width="40%"/>
	<acme:list-column code="auditor.auditRecord.form.label.body" path="status" width="40%"/>
	<acme:list-column code="auditor.auditRecord.form.label.job" path="job" width="40%"/>
</acme:list>