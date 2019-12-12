<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<jstl:choose>
<jstl:when  test="${realizada == 'no realizada'}">
<acme:form>
	<acme:form-textbox code="authenticated.auditor-request.form.label.firm" path="firm"/>
	<acme:form-textarea code="authenticated.auditor-request.form.label.statement" path="statement"/>
	
	<acme:form-submit code="authenticated.auditor-request.form.label.create" test = "${command == 'create'}" action="/authenticated/auditor-request/create"/>
	<acme:form-return code="authenticated.auditor-request.form.label.return"/>
</acme:form>
</jstl:when>
<jstl:when  test="${realizada == 'realizada'}">
<acme:message code="authenticated.auditor-request.alreadyRequested"/>
 </jstl:when>
</jstl:choose>
