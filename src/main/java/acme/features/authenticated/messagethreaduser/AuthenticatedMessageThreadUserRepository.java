
package acme.features.authenticated.messagethreaduser;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Authenticated_MessageThread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadUserRepository extends AbstractRepository {

	@Query("select  p from Authenticated_MessageThread p where p.thread.id = ?1")
	Collection<Authenticated_MessageThread> findUsersOnTheThread(int threadId);

}
