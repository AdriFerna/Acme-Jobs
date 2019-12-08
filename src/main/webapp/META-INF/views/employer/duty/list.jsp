<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list readonly="true">
<acme:list-column code="employer.duty.list.label.job.title" path="job.title" width="25%"/>
	<acme:list-column code="employer.duty.list.label.title" path="title" width="25%"/>
	<acme:list-column code="employer.duty.list.label.description" path="description" width="25%"/>
	<acme:list-column code="employer.duty.list.label.percentage" path="percentage" width="25%"/>
</acme:list>