package com.HMS.doclogin.controller;

import com.HMS.doclogin.entity.Appointment;
import com.HMS.doclogin.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v2")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/insert")
    public Appointment createEmployee(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

@DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteAppointments(
            @PathVariable Long id
    ) throws AttributeNotFoundException {
    Appointment appointment=    appointmentRepository.findById(id).orElseThrow(()->
                new AttributeNotFoundException("Appointment not found"));
    appointmentRepository.deleteById(id);
    Map<String,Boolean> response= new HashMap<String,Boolean>();
    response.put("Deleted",Boolean.TRUE);
    return ResponseEntity.ok(response);
    }
}
