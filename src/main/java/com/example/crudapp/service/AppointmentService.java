package com.example.crudapp.service;

import com.example.crudapp.dto.AppointmentRequest;
import com.example.crudapp.dto.AppointmentResponse;
import com.example.crudapp.dto.UserResponse;
import com.example.crudapp.model.Appointment;
import com.example.crudapp.model.User;
import com.example.crudapp.repository.AppointmentRepository;
import com.example.crudapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    //Create Appointment
    public void createAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = Appointment.builder()
                .doctorName(appointmentRequest.getDoctorName())
                .location(appointmentRequest.getLocation())
                .paymentStatus(appointmentRequest.getPaymentStatus())
                .category(appointmentRequest.getCategory())
                .appointmentStatus(appointmentRequest.getAppointmentStatus())
                .userid(String.valueOf(appointmentRequest.getUserid()))
                .build();

        appointmentRepository.save(appointment);
    }

    //Get Single User Details
    public AppointmentResponse getAppointmentById(String id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(NOT_FOUND,"User Not Found"));

        // Ensure that the user id is not null before proceeding
        if (appointment.getUserid() == null) {
            throw new IllegalArgumentException("User ID in the appointment must not be null.");
        }
        return mapToAppointmentResponse(appointment);
    }


    private AppointmentResponse mapToAppointmentResponse(Appointment appointment) {
        Optional<User> userOpt = userRepository.findById(appointment.getUserid());

        // Create default AppointmentResponse
        AppointmentResponse.AppointmentResponseBuilder responseBuilder = AppointmentResponse.builder()
                .id(appointment.getId())
                .userid(appointment.getUserid())
                .doctorName(appointment.getDoctorName())
                .location(appointment.getLocation())
                .paymentStatus(appointment.getPaymentStatus())
                .category(appointment.getCategory())
                .appointmentStatus(appointment.getAppointmentStatus());

        // If user found, add user details to response
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserResponse userResponse = UserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .age(user.getAge())
                    .build();

            // Add the user details to the appointment response
            responseBuilder.userResponse(userResponse);
        } else {
            log.warn("User not found for id: {}", appointment.getUserid());
        }

        // Return the built response
        return responseBuilder.build();
    }

}
