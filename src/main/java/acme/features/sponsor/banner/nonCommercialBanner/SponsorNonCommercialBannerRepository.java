
package acme.features.sponsor.banner.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.customParams.Configuration;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select s from NonCommercialBanner s where s.id = ?1")
	NonCommercialBanner findByid(int id);

	@Query("select s from NonCommercialBanner s where s.sponsor.id = ?1")
	Collection<NonCommercialBanner> findManyBySponsorId(int sponsorId);

	@Query("select s from NonCommercialBanner s")
	Collection<NonCommercialBanner> findMany();

	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor findSponsorById(int id);

	@Query("select a from Configuration a")
	Configuration getConfigParams();
}
