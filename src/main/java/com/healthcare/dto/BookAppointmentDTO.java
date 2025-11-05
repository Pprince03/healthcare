package com.healthcare.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookAppointmentDTO {
	private Long id;
	private LocalDateTime date;

}
