<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
<acme:form-textbox code="authenticated.worker.form.label.qualifications" path="qualifications"/>
<acme:form-textbox code="authenticated.worker.form.label.skills" path="skills"/>

<acme:form-submit code="authenticated.worker.form.button.create"  test = "${ command == 'create'}" action="/authenticated/worker/create"/>
<acme:form-submit code="authenticated.worker.form.button.update"  test = "${ command == 'update'}" action="/authenticated/worker/update"/>

	
	<acme:form-return code="authenticated.worker.form.button.return"/>
</acme:form>
