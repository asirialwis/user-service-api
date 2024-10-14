package com.example.crudapp.service;

import com.example.crudapp.dto.AppointmentRequest;
import com.example.crudapp.model.Appointment;
import com.example.crudapp.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    //Create Appointment
    public void createAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = Appointment.builder()
                .doctorName(appointmentRequest.getDoctorName())
                .location(appointmentRequest.getLocation())
                .paymentStatus(appointmentRequest.getPaymentStatus())
                .category(appointmentRequest.getCategory())
                .build();

        appointmentRepository.save(appointment);
    }
}
