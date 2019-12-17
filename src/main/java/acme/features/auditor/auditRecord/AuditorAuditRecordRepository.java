
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecord.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.id = ?1")
	AuditRecord findOneById(int id);

	@Query("select a from AuditRecord a")
	AuditRecord findAll(int id);

	@Query("select a from Auditor a where a.userAccount = ?1")
	Auditor findAuditorByUserAcountId(int id);

	@Query("select a from AuditRecord a where a.status = 'published'")
	Collection<AuditRecord> findAuditPublished();

	@Query("select a.job from AuditRecord a  	 where a.id = ?1")
	Job findJobByAuditRecordId(int auditId);

}
