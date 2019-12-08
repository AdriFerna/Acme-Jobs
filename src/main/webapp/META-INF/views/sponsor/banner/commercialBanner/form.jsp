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
<acme:form readonly="true">
	<acme:form-textbox code="sponsor.commercialBanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.commercialBanner.form.label.imageurl" path="imageurl"/>
	<acme:form-url code="sponsor.commercialBanner.form.label.targeturl" path="targeturl"/>
	<acme:form-integer placeholder="5380724565863291" code="administrator.commercialBanner.form.label.cardNumber" path="creditCard.cardNumber"/>
	<acme:form-textbox code="sponsor.commercialBanner.form.label.holder" path="creditCard.holder"/>
	<acme:form-integer code="sponsor.commercialBanner.form.label.cvv" path="creditCard.cvv"/>
	<acme:form-textbox code="sponsor.commercialBanner.form.label.brand" path="creditCard.brand"/>
	<acme:form-integer code="sponsor.commercialBanner.form.label.expirationMonth" path="creditCard.expirationMonth"/>
	<acme:form-integer code="sponsor.commercialBanner.form.label.expirationYear" path="creditCard.expirationYear"/>

	<acme:form-return code="sponsor.commercialBanner.form.button.return"/>
</acme:form>
