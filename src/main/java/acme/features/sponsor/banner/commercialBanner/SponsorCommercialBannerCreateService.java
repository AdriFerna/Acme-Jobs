
package acme.features.sponsor.banner.commercialBanner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.banners.CreditCard;
import acme.entities.customParams.Configuration;
import acme.entities.roles.Sponsor;
import acme.features.authenticated.sponsor.AuthenticatedSponsorRepository;
import acme.features.sponsor.creditCard.SponsorCreditCardRepository;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

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

		request.bind(entity, errors);
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

		request.unbind(entity, model, "slogan", "imageurl", "targeturl");

		Collection<CreditCard> cards = this.repositoryCreditCard.findBySponsorId(sponsor.getId());
		model.setAttribute("creditCard", cards);

	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner result;
		Principal principal;
		int userAccountId;
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		Sponsor sponsor;
		sponsor = this.repositorySponsor.findOneSponsorByUserAccountId(userAccountId);
		CreditCard creditCard = new CreditCard();
		creditCard.setBrand("VISA");
		creditCard.setCardNumber("6011396602603793");
		creditCard.setCvv(999);
		creditCard.setExpirationMonth(12);
		creditCard.setExpirationYear(2020);
		creditCard.setHolder("John");
		creditCard.setSponsor(sponsor);

		result = new CommercialBanner();
		result.setSponsor(sponsor);
		result.setCreditCard(creditCard);

		Collection<CreditCard> cards = this.repositoryCreditCard.findBySponsorId(sponsor.getId());
		request.getModel().setAttribute("creditCard", cards);

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration c = this.repository.getConfigParams();
		if (!errors.hasErrors("imageurl")) {
			boolean imageSpam = SpamCheck.checkSpam(entity.getImageurl(), c);
			errors.state(request, !imageSpam, "imageurl", "sponsor.comercialbanner.error.imageurl.spam");
		}

		if (!errors.hasErrors("slogan")) {
			boolean sloganSpam = SpamCheck.checkSpam(entity.getSlogan(), c);
			errors.state(request, !sloganSpam, "slogan", "sponsor.comercialbanner.error.slogan.spam");
		}

		if (!errors.hasErrors("targeturl")) {
			boolean targetSpam = SpamCheck.checkSpam(entity.getTargeturl(), c);
			errors.state(request, !targetSpam, "targeturl", "sponsor.comercialbanner.error.targeturl.spam");
		}

		boolean countCreditCards = this.repository.countCreditCardsOfSponsor(request.getPrincipal().getActiveRoleId()) > 0;
		errors.state(request, countCreditCards, "creditCardId", "sponsor.commercialbanner.nocreditcard.error");
	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		CreditCard creditCard;

		creditCard = this.repositoryCreditCard.findOneById(request.getModel().getInteger("creditCardId"));

		entity.setCreditCard(creditCard);

		this.repository.save(entity);
	}

}
