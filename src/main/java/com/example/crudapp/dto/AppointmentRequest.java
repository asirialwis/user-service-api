package com.example.crudapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class AppointmentRequest {
    private String doctorName;
    private String Location;
    private String paymentStatus;
    private String category;
}
