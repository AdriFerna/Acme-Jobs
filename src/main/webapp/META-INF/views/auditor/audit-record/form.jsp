<%--
- form.jsp
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

<acme:form>
	<acme:form-textbox code="auditor.auditRecord.form.label.title" path="title"/>
	
	<jstl:if test = "${command != 'create' }">
		<acme:form-moment code="auditor.auditRecord.form.label.moment" path="moment"  readonly="true"/>
	</jstl:if>
	<acme:form-textarea code="auditor.auditRecord.form.label.body" path="body"/>
	<jstl:if test = "${command != 'create' }">
	<acme:form-textbox code="auditor.auditRecord.form.label.status" path="status" readonly="true"/>
	<acme:form-textbox code="auditor.auditRecord.form.label.auditor.firm" path="auditor.firm" readonly="true"/>
	<acme:form-textbox code="auditor.auditRecord.form.label.job.refNumber" path="job.referenceNumber" readonly="true"/>
	<acme:form-textbox code="auditor.auditRecord.form.label.job.title" path="job.title" readonly="true"/>
	</jstl:if>
	
	<acme:form-hidden path="idJob"/>
	<acme:form-submit test="${command == 'create'}"
	code = "auditor.auditRecord.form.label.create"
	action="/auditor/audit-record/create"
	method = "post"/>
	
	<acme:form-submit test="${command == 'show' && status == 'draft' }"
	code = "auditor.auditRecord.form.label.publish"
	action="/auditor/audit-record/publish"
	method = "post"/>
	
	<acme:form-submit test="${command == 'publish' && status == 'draft' }"
		code = "auditor.auditRecord.form.label.publish"
	action="/auditor/audit-record/publish"
	method = "post"/>

	<acme:form-return code="auditor.auditRecord.form.button.return"/>
</acme:form>
