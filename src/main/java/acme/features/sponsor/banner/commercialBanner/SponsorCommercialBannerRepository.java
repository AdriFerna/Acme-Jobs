
package acme.features.sponsor.banner.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCommercialBannerRepository extends AbstractRepository {

	@Query("select s from CommercialBanner s where s.id = ?1")
	CommercialBanner findByid(int id);

	@Query("select s from CommercialBanner s where s.sponsor.id = ?1")
	Collection<CommercialBanner> findManyBySponsorId(int sponsorId);

	@Query("select s from CommercialBanner s")
	Collection<CommercialBanner> findMany();

}
