
package acme.entities.messages;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MessageThread extends DomainEntity {

	//Serialisation identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				creationMoment;

	// Relationships ----------------------------------------------------------

	/*
	 * @NotNull
	 * 
	 * @Valid
	 * 
	 * @ManyToMany(mappedBy = "messagethreads")
	 * private Collection<Authenticated> users;
	 * 
	 * @NotNull
	 * 
	 * @Valid
	 * 
	 * @OneToMany()
	 * private Message message;
	 */
}
