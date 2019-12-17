
package acme.features.administrator.auditorRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorRequest.AuditorRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuditorRequestRepository extends AbstractRepository {

	@Query("select a from AuditorRequest a where a.id = ?1")
	AuditorRequest findOneById(int id);

	@Query("select a from AuditorRequest a where a.status is null")
	Collection<AuditorRequest> findNotSeen();

	@Query("select a from UserAccount a where a.id = ?1")
	UserAccount findUserAccountById(int id);

}
