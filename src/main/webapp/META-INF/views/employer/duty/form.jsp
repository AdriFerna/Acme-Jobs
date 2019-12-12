<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form> 
<acme:form-textbox code="employer.duty.form.label.title" path="title"/>
<acme:form-textarea code="employer.duty.form.label.description" path="description"/>
<acme:form-double code="employer.duty.form.label.percentage" path="percentage"/>
<acme:form-hidden path="idJob"/>
<acme:form-submit code="employer.duty.form.button.create" action="/employer/duty/add" test = "${command == 'add'}"/>
<acme:form-submit code="employer.duty.form.button.update" action="/employer/duty/update" test = "${command == 'show'}"/>
<acme:form-submit code="employer.duty.form.button.update" action="/employer/duty/update" test = "${command == 'update'}"/>
<acme:form-return code="employer.duty.form.button.return"/>
</acme:form>