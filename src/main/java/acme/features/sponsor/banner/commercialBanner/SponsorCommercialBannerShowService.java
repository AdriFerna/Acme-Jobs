
package acme.features.sponsor.banner.commercialBanner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.banners.CreditCard;
import acme.entities.roles.Sponsor;
import acme.features.authenticated.sponsor.AuthenticatedSponsorRepository;
import acme.features.sponsor.creditCard.SponsorCreditCardRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorCommercialBannerShowService implements AbstractShowService<Sponsor, CommercialBanner> {

	@Autowired
	private SponsorCommercialBannerRepository	repository;

	@Autowired
	SponsorCreditCardRepository					repositoryCreditCard;

	@Autowired
	AuthenticatedSponsorRepository				repositorySponsor;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;
		boolean result;
		int bannerId;
		CommercialBanner cm;
		Sponsor sponsor;
		Principal principal;

		bannerId = request.getModel().getInteger("id");
		cm = this.repository.findByid(bannerId);
		sponsor = cm.getSponsor();
		principal = request.getPrincipal();
		result = sponsor.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Principal principal;
		int userAccountId;
		Sponsor sponsor;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		sponsor = this.repositorySponsor.findOneSponsorByUserAccountId(userAccountId);

		Collection<CreditCard> cards = this.repositoryCreditCard.findBySponsorId(sponsor.getId());
		model.setAttribute("creditCard", cards);

		request.unbind(entity, model, "slogan", "imageurl", "targeturl", "creditCard.cardNumber");
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

}
