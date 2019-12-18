<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form >
<acme:form-integer placeholder="EEEE-JJJJ" code="employer.job.form.label.referenceNumber" path="referenceNumber"/>
<jstl:if test="${command != 'create' }">
 <acme:form-textbox readonly="true" code="employer.job.form.label.status" path="status"/>
</jstl:if>
<acme:form-textbox code="employer.job.form.label.title" path="title"/>
<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
<acme:form-double code="employer.job.form.label.salary" path="salary"/>
<acme:form-textbox code="employer.job.form.label.moreInfo" path="moreInfo"/>
<acme:form-textarea code="employer.job.form.label.description" path="description"/>


<acme:form-submit code="employer.duty.form.button.addDuty" 
     action="/employer/duty/add?idJob=${id}"
     test="${command != 'create'  && status == 'draft'}"
     method = "get"/>
     
<acme:form-submit code="employer.job.form.button.create" 
	action="/employer/job/create"
	test="${command == 'create'}"/>
	
	
	<acme:form-submit code="employer.job.form.button.update" 
	action="/employer/job/update"
	test="${command == 'update' && status == 'draft'}"/>
	
	<acme:form-submit code="employer.job.form.button.update" 
	action="/employer/job/update"
	test="${command == 'show' && status == 'draft'}"/>
	
	<acme:form-submit code="employer.job.form.button.delete" 
	action="/employer/job/delete"
	test="${command == 'delete'}"/>
	
	<acme:form-submit code="employer.job.form.button.delete" 
	action="/employer/job/delete"
	test="${command == 'show'}"/>
	
	<acme:form-submit code="employer.job.form.button.publish" 
	action="/employer/job/publish"
	test="${command == 'publish'  && status == 'draft'}"/>
	
	<acme:form-submit code="employer.job.form.button.publish" 
	action="/employer/job/publish"
	test="${command == 'show'  && status == 'draft'}"/>
	
	</acme:form>
	
	

<acme:form>
<acme:form-submit method="get" test="${command != 'create'}"  code="employer.job.message.duties" action="/employer/duty/list?idJob=${id}"/>

<acme:form-submit method="get" test="${command == 'show' && status == 'published' }"  code="employer.job.message.audit-record" action="/authenticated/audit-record/list?idJob=${id}"/>




<acme:form-return code="autheticated.job.form.button.return"/>
</acme:form>