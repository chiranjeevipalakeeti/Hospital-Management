package com.HMS.doclogin.controller;

import com.HMS.doclogin.entity.Medicines;
import com.HMS.doclogin.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v3")
public class MedicineController {
    @Autowired
    private MedicineRepository medicineRepository;

    @PostMapping("/insert")
    public Medicines createMedicine(@RequestBody Medicines medicines) {
        return medicineRepository.save(medicines);
    }

    @GetMapping
    public List<Medicines> getAllMedicines()
    {
        return medicineRepository.findAll();
    }

@GetMapping("/medicine/{id}")
    public ResponseEntity<Medicines> getMedicineById(@PathVariable Long id) throws AttributeNotFoundException {
Medicines medicines= medicineRepository.findById(id)
        .orElseThrow(()-> new AttributeNotFoundException("Medicine Not Found"));
return ResponseEntity.ok(medicines);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Medicines> updateMedicine(@PathVariable Long id,@RequestBody Medicines medicines) throws AttributeNotFoundException {
        Medicines medicinesd= medicineRepository.findById(id)
                .orElseThrow(()-> new AttributeNotFoundException("Medicine Not Found"));
        medicinesd.setDrugName(medicines.getDrugName());
        medicinesd.setStock(medicines.getStock());
        Medicines saved=medicineRepository.save(medicinesd);
        return ResponseEntity.ok(saved);
    }
@DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> delete(@PathVariable Long id) throws AttributeNotFoundException {
        Medicines medicines= medicineRepository.findById(id)
                .orElseThrow(()-> new AttributeNotFoundException("Medicine Not Found"));
        medicineRepository.deleteById(id);
        Map<String,Boolean> response= new HashMap<String,Boolean>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
