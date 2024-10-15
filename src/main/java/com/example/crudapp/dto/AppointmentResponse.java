package com.example.crudapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private String id;
    private String doctorName;
    private String location;
    private String paymentStatus;
    private String category;
    private String appointmentStatus;
    private ObjectId userid;
}
