
package acme.entities.auditorRequest;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditorRequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@Valid
	private int					userAccountId;

	private Boolean				status;

	@NotBlank
	private String				firm;

	@NotBlank
	private String				statement;

}
