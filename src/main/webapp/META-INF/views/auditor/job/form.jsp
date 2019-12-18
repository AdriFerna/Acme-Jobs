<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
<acme:form-integer code="auditor.job.form.label.referenceNumber" path="referenceNumber"/>
<acme:form-textbox code="auditor.job.form.label.status" path="status"/>
<acme:form-textbox code="auditor.job.form.label.title" path="title"/>
<acme:form-moment code="auditor.job.form.label.deadline" path="deadline"/>
<acme:form-double code="auditor.job.form.label.salary" path="salary"/>
<acme:form-textbox code="auditor.job.form.label.moreInfo" path="moreInfo"/>
<acme:form-textarea code="auditor.job.form.label.description" path="description"/>
</acme:form>


<acme:form-submit code="auditor.job.message.duties" method="get" action= "/auditor/duty/list?idJob=${id}"/>
<acme:form-submit code="auditor.job.message.audit-record" method="get" action= "/authenticated/audit-record/list?idJob=${id}"/>




<acme:form-submit  test ="${command != 'create'}"  
	code="auditor.job.form.button.create.auditRecord"
	method="get"
	action= "/auditor/audit-record/create?idJob=${id}"
/>


<acme:form>
<acme:form-return code="auditor.job.form.button.return"/>
</acme:form>