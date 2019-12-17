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
	<acme:form-textbox code="sponsor.commercialBanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.commercialBanner.form.label.imageurl" path="imageurl"/>
	<acme:form-url code="sponsor.commercialBanner.form.label.targeturl" path="targeturl"/>
	<acme:form-select code="sponsor.commercialBanner.form.select.creditCard" path="creditCardId">
		<jstl:forEach items="${creditCard}" var="item">
	<acme:form-option   code="${item.cardNumber}" value="${item.id}"/>
	</jstl:forEach>
	</acme:form-select>

	<acme:form-submit test="${command == 'create' }" code="sponsor.commercialBanner.form.button.create" action="/sponsor/commercial-banner/create"/>
	<acme:form-submit test="${command == 'show' }" code="sponsor.commercialBanner.form.button.update" action="/sponsor/commercial-banner/update"/>
	<acme:form-submit test="${command == 'update' }" code="sponsor.commercialBanner.form.button.update" action="/sponsor/commercial-banner/update"/>
	<acme:form-submit test="${command == 'show' }" code="sponsor.commercialBanner.form.button.delete" action="/sponsor/commercial-banner/delete"/>
	<acme:form-submit test="${command == 'delete' }" code="sponsor.commercialBanner.form.button.delete" action="/sponsor/commercial-banner/delete"/>

	<acme:form-return code="sponsor.commercialBanner.form.button.return"/>
</acme:form>
