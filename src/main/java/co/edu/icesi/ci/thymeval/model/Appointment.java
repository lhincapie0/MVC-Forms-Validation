package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Data
public class Appointment {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@NotNull
	@FutureOrPresent(message="La fecha debe ser el día de hoy o fechas futuras.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@NotNull(message="Se requiere especificar una hora.")
	@DateTimeFormat(iso = ISO.TIME)
	private LocalTime time;
	
	@NotNull(message="Se requiere especificar el nombre del paciente.")
	@ManyToOne
	private User patient;
	
	@NotNull(message="Se requiere especificar el nombre del doctor.")
	@ManyToOne
	private User doctor;
}
