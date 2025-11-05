package com.healthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.dto.AppointmentDTO;
import com.healthcare.dto.BookAppointmentDTO;
import com.healthcare.entities.Appointment;
import com.healthcare.entities.Doctor;
import com.healthcare.entities.Patient;
import com.healthcare.entities.Status;
import com.healthcare.repository.AppointmentRepository;
import com.healthcare.repository.DoctorRepository;
import com.healthcare.repository.PatientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
	private AppointmentRepository appRepo;
	private PatientRepository patRepo;
	private DoctorRepository docRepo;
	@Override
	public List<AppointmentDTO> getAllAppointments(Long Pid) {
		// TODO Auto-generated method stub
		return appRepo.getPAtientAppByPAtientID(Pid, Status.SCHEDULED) ;
	}
	@Override
	public String bookAppointment(Long patientId, BookAppointmentDTO dto) {
		
		
			Patient p= patRepo.findById(patientId).orElseThrow(()->new ResourceNotFoundException("Not Exist"));
			System.out.println(" -------------------");
			Optional<Appointment> a= appRepo.findByMyDoctorIdAndAppointmentDateTime(dto.getId(), dto.getDate());
			System.out.println(" -------------------");
			Doctor d=docRepo.findById(dto.getId()).orElseThrow(()->new ResourceNotFoundException("Doctor id not valid"));
			if(a.isEmpty()) {
				appRepo.save(new Appointment(dto.getDate(),Status.SCHEDULED,d,p));
				return "booked";
			}
			else {
				return "Already Exist";
			}
				
				
	
		
		
	}
	@Override
	public boolean allAvailable(Long patientId, BookAppointmentDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

}
