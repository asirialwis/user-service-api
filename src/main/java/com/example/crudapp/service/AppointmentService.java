package com.example.crudapp.service;

import com.example.crudapp.dto.AppointmentRequest;
import com.example.crudapp.dto.AppointmentResponse;
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
                .appointmentStatus(appointmentRequest.getAppointmentStatus())
                .userid(appointmentRequest.getUserid())
                .build();

        appointmentRepository.save(appointment);
    }

    private AppointmentResponse mapToUserResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .userid(appointment.getUserid())
                .doctorName(appointment.getDoctorName())
                .location(appointment.getLocation())
                .paymentStatus(appointment.getPaymentStatus())
                .category(appointment.getCategory())
                .appointmentStatus(appointment.getAppointmentStatus())
                .build();

    }
}
