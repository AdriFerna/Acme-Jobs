
package acme.features.sponsor.creditCard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CreditCard;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCreditCardRepository extends AbstractRepository {

	@Query("select cc from CreditCard cc where cc.id = ?1")
	CreditCard findOneById(int id);

	@Query("select cc from CreditCard cc where cc.sponsor.id = ?1")
	Collection<CreditCard> findBySponsorId(int id);

	@Query("select cc from CreditCard cc")
	Collection<CreditCard> findMany();

}
