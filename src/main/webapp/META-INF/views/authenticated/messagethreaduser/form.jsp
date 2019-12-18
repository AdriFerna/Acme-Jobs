<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>

<jstl:if test="${command == 'add' }">
<jstl:choose>
<jstl:when test="${empty usersToAdd}">
		<acme:message code="autheticated.messagethread.form.noMoreUsers"/>
	</jstl:when>
	
	<jstl:otherwise>
	<acme:form-select code="authenticated.messagethread.form.usersToAdd" path="userAdded">
	<jstl:forEach items="${usersToAdd}" var = "item">
	<acme:form-option code="${item.userAccount.username}" value="${item.id}"/>
	</jstl:forEach>
	
	</acme:form-select>
	<acme:form-hidden path="idThread"/>
	<acme:form-submit code="autheticated.messagethread.form.button.addUser" action="/authenticated/authenticated-message-thread/add"/>

	</jstl:otherwise>
	</jstl:choose>
	
	</jstl:if>
	
	<jstl:if test="${command == 'delete'}">
<jstl:choose>
<jstl:when test="${empty usersToRemove}">
		<acme:message code="autheticated.messagethread.form.noMoreUsersToRemove"/>
	</jstl:when>
	
	<jstl:otherwise>
	<acme:form-select code="authenticated.messagethread.form.usersToRemove" path="userRemoved">
	<jstl:forEach items="${usersToRemove}" var = "item">
	<acme:form-option code="${item.userAccount.username}" value="${item.id}"/>
	</jstl:forEach>
	
	</acme:form-select>
	<acme:form-hidden path="idThread"/>
	<acme:form-submit code="autheticated.messagethread.form.button.deleteUser" action="/authenticated/authenticated-message-thread/delete"/>

	</jstl:otherwise>
	</jstl:choose>
	
	</jstl:if>
	
	<acme:form-return code="autheticated.messagethread.form.button.return"/>
	
</acme:form>