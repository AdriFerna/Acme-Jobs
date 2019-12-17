/*
 * SponsorCreditCardCreateService.java
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
import acme.features.authenticated.sponsor.AuthenticatedSponsorRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCreditCardCreateService implements AbstractCreateService<Sponsor, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorCreditCardRepository		repository;

	@Autowired
	private AuthenticatedSponsorRepository	repositorySponsor;


	// AbstractCreateService<Sponsor, CreditCard> interface ---------------

	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		return true;
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
	public CreditCard instantiate(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		Principal principal;
		int userAccountId;
		Sponsor sponsor;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		sponsor = this.repositorySponsor.findOneSponsorByUserAccountId(userAccountId);

		result = new CreditCard();
		result.setSponsor(sponsor);

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
			errors.state(request, !cmp, "expirationYear", "sponsor.creditCard.error.expiration");
			errors.state(request, !cmp, "expirationMonth", "sponsor.creditCard.error.expiration");
		}
		if (!errors.hasErrors("cvv")) {
			boolean rangoCVV = String.valueOf(entity.getCvv()).length() == 3;
			errors.state(request, rangoCVV, "cvv", "sponsor.creditCard.error.cvv");
		}
	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
