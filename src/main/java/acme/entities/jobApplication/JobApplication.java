
package acme.entities.jobApplication;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "status")
})
public class JobApplication extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(min = 5, max = 15)
	@Column(unique = true)
	private String				referenceNumber;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				updateMoment;

	@NotBlank
	@Pattern(regexp = "^(pending|accepted|rejected)$")
	private String				status;

	@NotBlank
	@Column(length = 1024)
	private String				statement;

	@NotBlank
	@Column(length = 1024)
	private String				skills;

	@NotBlank
	@Column(length = 1024)
	private String				qualifications;

	@Column(length = 1024)
	private String				justification;

	@NotNull
	@ManyToOne(optional = false)
	private Job					job;

	@NotNull
	@ManyToOne(optional = false)
	private Worker				worker;

}
