package com.healthcare.controller;
// first comment
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.healthcare.dto.ApiResponse;
import com.healthcare.dto.BookAppointmentDTO;
import com.healthcare.service.AppointmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor

public class PatientController {
	private final AppointmentService appService ;
	
	
	@GetMapping("/{patientID}/appointments/upcoming")
	public ResponseEntity<?> getAll(@PathVariable Long patientID){
		
		try {
			return ResponseEntity.ok(appService.getAllAppointments(patientID));
			
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),"failed"));
		}
		
	
	}
	
	@PostMapping("/{patientId}/appointments")
	public ResponseEntity<?> bookAppointments(@PathVariable Long patientId,@RequestBody BookAppointmentDTO dto){
		try {
			return  ResponseEntity.ok(appService.bookAppointment(patientId,dto));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Cant Booked"));
		}
		
	}

}
