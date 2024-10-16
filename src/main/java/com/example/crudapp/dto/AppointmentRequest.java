package com.example.crudapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class AppointmentRequest {
    private String doctorName;
    private String location;
    private String paymentStatus;
    private String category;
    private String appointmentStatus;
    private ObjectId userid;

}
