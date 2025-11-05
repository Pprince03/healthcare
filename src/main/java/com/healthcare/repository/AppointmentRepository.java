package com.healthcare.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healthcare.dto.AppointmentDTO;
import com.healthcare.entities.Appointment;
import com.healthcare.entities.Status;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	@Query("""
			Select new com.healthcare.dto.AppointmentDTO(a.id,a.appointmentDateTime,a.myDoctor.userDetails.firstName,a.myDoctor.userDetails.lastName) from Appointment a where a.myPatient.id=:pid and a.status=:sts order by a.appointmentDateTime asc
			
			""")List<AppointmentDTO> getPAtientAppByPAtientID(@Param("pid") Long patientId,@Param("sts") Status status);
	
	@Query("""
			select a from Appointment a where a.myDoctor.id=:did and a.appointmentDateTime=:sts 
			""")Appointment checkDoctor(@Param("did") Long patientid,@Param("sts") LocalDateTime ts);
	
	Optional<Appointment> findByMyDoctorIdAndAppointmentDateTime(Long id,LocalDateTime ts);
}
