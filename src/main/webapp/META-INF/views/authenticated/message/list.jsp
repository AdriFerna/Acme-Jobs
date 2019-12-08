<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="autheticated.message.list.label.title" path="title" width="10%"/>
	<acme:list-column code="autheticated.message.list.label.tags" path="tags" width="10%"/>
	<acme:list-column code="autheticated.message.list.label.username" path="user.userAccount.username" width="10%"/>
</acme:list>