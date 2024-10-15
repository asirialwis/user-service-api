package com.example.crudapp.controller;


import com.example.crudapp.dto.AppointmentRequest;
import com.example.crudapp.dto.AppointmentResponse;
import com.example.crudapp.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.createAppointment(appointmentRequest);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse getAppointmentById(@PathVariable String id){
        return appointmentService.getAppointmentById(id);
    }

}
