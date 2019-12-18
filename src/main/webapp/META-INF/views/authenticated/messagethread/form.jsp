<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
<jstl:if test="${command == 'create' }">
	<acme:form-textbox code="autheticated.messagethread.form.label.title" path="title" />
</jstl:if>	

	<jstl:if test="${command != 'create'}">
	<acme:form-textbox code="autheticated.messagethread.form.label.title" path="title" readonly="true" />
		<acme:form-moment  readonly="true" code="autheticated.messagethread.list.label.creationMoment" path="creationMoment"/>
		
		<acme:form-submit method="get" code="authenticated.message.message.message" action="/authenticated/message/list?idThread=${id}"/>
		
	<acme:form-submit code="authenticated.message.message.form.button.create" action="/authenticated/message/create?idThread=${id}"
	method="get"/>
	
	<acme:form-submit code="authenticated.message.message.form.button.seeUsers" action="/authenticated/users/list?idThread=${id}"
	method="get"/>

 <acme:form-submit
test = "${owner.id == authId}" 
  code="authenticated.message.message.user" action="/authenticated/authenticated-message-thread/add?idThread=${id}"
	method="get"/>
	
	 <acme:form-submit
test = "${owner.id == authId}" 
  code="authenticated.message.message.user.delete" action="/authenticated/authenticated-message-thread/delete?idThread=${id}"
	method="get"/>

 
		<br>
	</jstl:if>
	
	<acme:form-submit test = "${ command == 'create'}" code="authenticated.messagethread.form.button.create" action="/authenticated/message-thread/create"/>
	<acme:form-return code="autheticated.messagethread.form.button.return"/>
	
</acme:form>