
package acme.features.authenticated.auditorrequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customParams.Configuration;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRequestRepository extends AbstractRepository {

	@Query("select count(ur) from AuditorRequest ur where ur.user.id = ?1 and ur.status is null ")
	Integer findAuditorRequestByAuthenticatedId(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int id);

	@Query("select a from Configuration a")
	Configuration getConfigParams();

}
