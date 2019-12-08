
package acme.features.authenticated.message;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.MessageThread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneById(int id);

	@Query("select count(p) from Authenticated_MessageThread  p where p.user.id = ?1 and p.thread.id = ?2")
	Integer checkIfUserIsInTheThread(int userId, int threadId);

	@Query("select p.thread from Authenticated_MessageThread p where p.user.id = ?1")
	Collection<MessageThread> findThreadByUser(int userId);

	@Query("select  p.user.id from Authenticated_MessageThread p where p.thread.id = ?1")
	List<Integer> findUsersOnTheThread(int threadId);

	@Query("select a.username, a.identity.name , a.identity.surname from UserAccount a where a.id in (select b.userAccount.id from Authenticated b where b.id in ?1)")
	String[][] findDataFromUsers(List<Integer> idsAuthenticateds);
}
