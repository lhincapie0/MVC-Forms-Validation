package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class UserApp {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@NotBlank(groups=User1.class)
	@Size(message="La contrase�a debe tener minimo ocho caracteres", min=8, groups = User1.class)
	private String password;
	
	@NotBlank(groups=User1.class)
	@Size(message="El username debe tener minimo tres caracteres", min=3, groups=User1.class)
	private String username;
	
	@NotBlank(groups=User2.class)
	@Size(message="El nombre debe tener minimo dos caracteres", min=2, groups=User2.class)
	private String name;
	
	@NotBlank(groups = User2.class)
	@Email(message= "Debes ingresar un correo valido", groups = User2.class)
	private String email;
	
	@NotNull(groups=User2.class)
	private UserType type;
	
	@NotNull(groups=User1.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message="La fecha debe ser anterior a hoy.", groups = User1.class)
	private LocalDate birthDate;
	
	@NotNull(groups=User2.class)
	private UserGender gender;
	
//	@OneToMany
//	private List<Appointment> appointments;
}
