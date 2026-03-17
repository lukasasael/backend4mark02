package com.angularmak02.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    @GetMapping
    public List<Map<String, Object>> getPatients() {
        return List.of(
            Map.of(
                "id", "1",
                "nome", "João",
                "idade", 30,
                "planoTratamento", "Plano A",
                "historico", "",
                "dataInicio", "2024-01-01"
            )
        );
    }
}
