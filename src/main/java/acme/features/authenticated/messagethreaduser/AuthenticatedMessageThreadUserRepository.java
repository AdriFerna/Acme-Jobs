
package acme.features.authenticated.messagethreaduser;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.AuthenticatedMessageThread;
import acme.entities.messages.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadUserRepository extends AbstractRepository {

	@Query("select  p from AuthenticatedMessageThread p where p.thread.id = ?1")
	Collection<AuthenticatedMessageThread> findUsersOnTheThread(int threadId);

	@Query("select a.owner from MessageThread a where a.id = ?1")
	Authenticated findOwnerOfTheThread(int idThread);

	@Query("select a from MessageThread a where a.id = ?1")
	MessageThread findMessageThreadById(int idThread);

	@Query("select a from Authenticated a where a.id not in (select b.user.id from AuthenticatedMessageThread b where b.thread.id = ?1) ")
	List<Authenticated> findUserNotInTheThread(int idThread);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int idAuth);

	@Query("select a.user from AuthenticatedMessageThread a where a.thread.id = ?1 and a.user.id != ?2")
	List<Authenticated> findUsersInTheThread(int idThread, int idOwner);

	@Query("select a from AuthenticatedMessageThread a where a.user.id = ?1 and a.thread.id = ?2")
	AuthenticatedMessageThread findOneById(int idUser, int idThread);

}
