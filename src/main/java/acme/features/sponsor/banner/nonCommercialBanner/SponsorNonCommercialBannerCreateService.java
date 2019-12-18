
package acme.features.sponsor.banner.nonCommercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.customParams.Configuration;
import acme.entities.roles.Sponsor;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorNonCommercialBannerCreateService implements AbstractCreateService<Sponsor, NonCommercialBanner> {

	@Autowired
	SponsorNonCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "slogan", "imageurl", "targeturl", "jingleurl");
	}

	@Override
	public NonCommercialBanner instantiate(final Request<NonCommercialBanner> request) {
		assert request != null;

		NonCommercialBanner result;
		Sponsor s = this.repository.findSponsorById(request.getPrincipal().getActiveRoleId());

		result = new NonCommercialBanner();
		result.setSponsor(s);

		return result;
	}

	@Override
	public void validate(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration c = this.repository.getConfigParams();
		if (!errors.hasErrors("imageurl")) {
			boolean imageSpam = SpamCheck.checkSpam(entity.getImageurl(), c);
			errors.state(request, !imageSpam, "imageurl", "sponsor.noncomercialbanner.error.imageurl.spam");
		}

		if (!errors.hasErrors("slogan")) {
			boolean sloganSpam = SpamCheck.checkSpam(entity.getSlogan(), c);
			errors.state(request, !sloganSpam, "slogan", "sponsor.noncomercialbanner.error.slogan.spam");
		}

		if (!errors.hasErrors("targeturl")) {
			boolean targetSpam = SpamCheck.checkSpam(entity.getTargeturl(), c);
			errors.state(request, !targetSpam, "targeturl", "sponsor.noncomercialbanner.error.targeturl.spam");
		}

		if (!errors.hasErrors("jingleurl")) {
			boolean jingleSpam = SpamCheck.checkSpam(entity.getJingleurl(), c);
			errors.state(request, !jingleSpam, "jingleurl", "sponsor.noncomercialbanner.error.jingleurl.spam");
		}

	}

	@Override
	public void create(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
