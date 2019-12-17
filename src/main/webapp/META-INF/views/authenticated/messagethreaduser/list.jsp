<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="autheticated.messagethreaduser.list.label.user" path="user.userAccount.username" width="10%"/>
</acme:list>