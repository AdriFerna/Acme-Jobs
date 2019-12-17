
package acme.features.sponsor.banner.commercialBanner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.banners.CreditCard;
import acme.entities.roles.Sponsor;
import acme.features.authenticated.sponsor.AuthenticatedSponsorRepository;
import acme.features.sponsor.creditCard.SponsorCreditCardRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class SponsorCommercialBannerDeleteService implements AbstractDeleteService<Sponsor, CommercialBanner> {

	@Autowired
	SponsorCommercialBannerRepository	repository;

	@Autowired
	SponsorCreditCardRepository			repositoryCreditCard;

	@Autowired
	AuthenticatedSponsorRepository		repositorySponsor;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Principal principal;
		int userAccountId;
		Sponsor sponsor;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		sponsor = this.repositorySponsor.findOneSponsorByUserAccountId(userAccountId);

		Collection<CreditCard> cards = this.repositoryCreditCard.findBySponsorId(sponsor.getId());
		request.getModel().setAttribute("creditCard", cards);

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "slogan", "imageurl", "targeturl", "creditCard.id");
	}

	@Override
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findByid(id);

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
