/*
 * AuthenticatedCreditCardUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.sponsor.creditCard;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorCreditCardUpdateService implements AbstractUpdateService<Sponsor, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorCreditCardRepository repository;


	// AbstractUpdateService<Sponsor, CreditCard> interface ---------------

	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;
		boolean result;
		int creditCardId;
		CreditCard cc;
		Sponsor sponsor;
		Principal principal;

		creditCardId = request.getModel().getInteger("id");
		cc = this.repository.findOneById(creditCardId);
		sponsor = cc.getSponsor();
		principal = request.getPrincipal();
		result = sponsor.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "cardNumber", "holder", "cvv", "brand", "expirationMonth", "expirationYear");
	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("expirationYear") && !errors.hasErrors("expirationMonth")) {
			YearMonth ym = YearMonth.now();
			YearMonth introducido = YearMonth.of(entity.getExpirationYear(), entity.getExpirationMonth());
			boolean cmp = introducido.isBefore(ym);
			errors.state(request, !cmp, "expirationYear", "administrator.commercialBanner.error.expiration");
			errors.state(request, !cmp, "expirationMonth", "administrator.commercialBanner.error.expiration");
		}
		if (!errors.hasErrors("cvv")) {
			boolean rangoCVV = String.valueOf(entity.getCvv()).length() == 3;
			errors.state(request, rangoCVV, "cvv", "administrator.commercialBanner.error.cvv");
		}
	}

	@Override
	public void update(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<CreditCard> request, final Response<CreditCard> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
