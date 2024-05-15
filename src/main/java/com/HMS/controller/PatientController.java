package com.HMS.controller;

import com.HMS.entity.Patient;
import com.HMS.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;


    @PostMapping("/insert")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @GetMapping
    public List<Patient> getAllPatient()
    {
        return patientRepository.findAll();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePatient(@PathVariable Long id) throws AttributeNotFoundException {
        Patient patient= patientRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Patient Not Found"));
    patientRepository.deleteById(id);
    Map<String,Boolean> response= new HashMap<String,Boolean>();
    response.put("Deleted",Boolean.TRUE);
    return  ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,@RequestBody Patient patientd) throws AttributeNotFoundException {
        Patient patient= patientRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Patient Not Found"));
        patient.setAge(patientd.getAge());
        patient.setName(patientd.getName());
        patient.setBlood(patientd.getBlood());
        patient.setFees(patientd.getFees());
        patient.setDose(patientd.getDose());
        patient.setPrescription(patientd.getPrescription());
        patient.setUrgency(patientd.getUrgency());
        Patient saved=patientRepository.save(patient);
        return ResponseEntity.ok(saved);

    }

    //single patient
    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) throws AttributeNotFoundException {
       Patient patient= patientRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Patient Not Found"));
    return ResponseEntity.ok(patient);
    }

}
