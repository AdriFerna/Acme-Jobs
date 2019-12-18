
package acme.features.authenticated.users;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedUsersRepository extends AbstractRepository {

	@Query("select  p.user from AuthenticatedMessageThread p where p.thread.id = ?1")
	Collection<Authenticated> findUsersAuthenticatedOnTheThread(int threadId);

	@Query("select count(p) from AuthenticatedMessageThread  p where p.user.id = ?1 and p.thread.id = ?2")
	Integer checkIfUserIsInTheThread(int userId, int threadId);

}
