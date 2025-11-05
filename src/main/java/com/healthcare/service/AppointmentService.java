package com.healthcare.service;

import java.util.List;

import com.healthcare.dto.AppointmentDTO;
import com.healthcare.dto.BookAppointmentDTO;

public interface AppointmentService {

	List<AppointmentDTO> getAllAppointments(Long pid);

	String bookAppointment(Long patientId, BookAppointmentDTO dto);

	boolean allAvailable(Long patientId, BookAppointmentDTO dto);
	
	

}
