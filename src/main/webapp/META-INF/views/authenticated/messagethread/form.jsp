<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="autheticated.messagethread.form.label.title" path="title" />
	<acme:form-moment 
		code="autheticated.messagethread.list.label.creationMoment" 
		path="creationMoment"/>
	</acme:form>	
	
	<acme:message code="authenticated.messagethread.message.users"></acme:message>
	<br>
	<jstl:forEach  items = "${usersData}" var = "user">
	<jstl:out value=" · (${user[0]}) | ${user[1]} ,${user[2]}   "></jstl:out>
	<br>
	</jstl:forEach>
	
	<a href = /acme-jobs/authenticated/message/list?idThread=<jstl:out value="${id}"></jstl:out>>
<acme:message code="authenticated.message.message.message"/>
</a>

		<acme:form>
	<acme:form-return code="autheticated.messagethread.form.button.return"/>
</acme:form>