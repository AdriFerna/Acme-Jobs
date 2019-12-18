<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.creditCard.form.label.cardNumber" path="cardNumber"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.holder" path="holder"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.cvv" path="cvv"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.brand" path="brand"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.expirationMonth" path="expirationMonth"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.expirationYear" path="expirationYear"/>
	
	<acme:form-submit test="${command == 'create' }" code="sponsor.creditCard.form.button.create" action="/sponsor/credit-card/create"/>
	<acme:form-submit test="${command == 'show' }" code="sponsor.creditCard.form.button.update" action="/sponsor/credit-card/update"/>
	<acme:form-submit test="${command == 'update' }" code="sponsor.creditCard.form.button.update" action="/sponsor/credit-card/update"/>
	
	<acme:form-return code="sponsor.creditCard.form.button.return"/>
	
</acme:form>
