
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
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, CommercialBanner> {

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
		// ESTO ESTÁ AQUÍ POR QUE SI NO, SI HAY ERRORES AL ACTUALIZAR, NO APARECEN
		//LAS TARJETAS DE CREDITO AL ESTAR EN EL COMANDO UPDATE
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
		result = this.repository.findByid(request.getModel().getInteger("id"));

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
	public void update(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		CreditCard creditCard;

		creditCard = this.repositoryCreditCard.findOneById(request.getModel().getInteger("creditCardId"));

		entity.setCreditCard(creditCard);

		this.repository.save(entity);
	}

}
