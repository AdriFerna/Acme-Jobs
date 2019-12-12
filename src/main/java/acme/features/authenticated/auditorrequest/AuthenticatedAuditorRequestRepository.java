
package acme.features.authenticated.auditorrequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRequestRepository extends AbstractRepository {

	@Query("select count(ur) from AuditorRequest ur where ur.user.id = ?1 and ur.status is true ")
	Integer findAuditorRequestByUserAccountId(int id);

	@Query("select a from UserAccount a where a.id = ?1")
	UserAccount findUserAccountById(int id);

}
