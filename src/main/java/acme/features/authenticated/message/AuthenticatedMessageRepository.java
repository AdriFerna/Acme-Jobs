
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customParams.Configuration;
import acme.entities.messages.Message;
import acme.entities.messages.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select count(p) from AuthenticatedMessageThread  p where p.user.id = ?1 and p.thread.id = ?2")
	Integer checkIfUserIsInTheThread(int userId, int threadId);

	@Query("select m from Message m where m.thread.id = ?1")
	Collection<Message> findMessagesFromThread(int idThread);

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findMessagesThread(int idThread);

	@Query("select u from Authenticated u where u.id = ?1")
	Authenticated findUser(int idUser);

	@Query("select a from Configuration a")
	Configuration getConfigParams();

}
