
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Message;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select count(p) from Authenticated_MessageThread  p where p.user.id = ?1 and p.thread.id = ?2")
	Integer checkIfUserIsInTheThread(int userId, int threadId);

	@Query("select m from Message m where m.thread.id = ?1")
	Collection<Message> findMessagesFromThread(int idThread);

}
