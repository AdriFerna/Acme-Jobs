
package acme.features.authenticated.auditorrequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRequestRepository extends AbstractRepository {

	@Query("select count(ur) from AuditorRequest ur where ur.userAccountId = ?1")
	Integer findAuditorRequestByUserAccountId(int id);

}
