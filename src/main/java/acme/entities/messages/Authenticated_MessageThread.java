
package acme.entities.messages;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authenticated_MessageThread extends DomainEntity {

	//Serialisation identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Relationship ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		user;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private MessageThread		thread;

	/*
	 * @NotNull
	 *
	 * @Valid
	 *
	 * @ManyToOne(optional = false)
	 * private MessageThread thread;
	 */

}
