<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="autheticated.messagethread.form.label.title" path="title" />
	<acme:form-textbox code="autheticated.messagethread.form.label.ownerId" path="ownerId" />
	
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="autheticated.messagethread.list.label.creationMoment" path="creationMoment"/>
		
		<acme:message code="authenticated.messagethread.message.users"></acme:message>
		<br>
			<jstl:forEach  items = "${usersData}" var = "user">
				<jstl:out value=" · (${user[0]}) | ${user[1]} ,${user[2]}   "></jstl:out>
			<br>
		
			</jstl:forEach>
	
		<a href = /acme-jobs/authenticated/message/list?idThread=<jstl:out value="${id}"></jstl:out>>
			<acme:message code="authenticated.message.message.message"/>
		</a>
		
		<br>
	</jstl:if>
	
	<acme:form-submit test = "${ command == 'create'}" code="authenticated.messagethread.form.button.create" action="/authenticated/message-thread/create"/>
	<acme:form-return code="autheticated.messagethread.form.button.return"/>
	
</acme:form>