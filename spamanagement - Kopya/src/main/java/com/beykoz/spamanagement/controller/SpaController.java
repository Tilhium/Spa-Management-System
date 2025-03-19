package com.beykoz.spamanagement.controller;

import com.beykoz.spamanagement.dto.SpaDto;
import com.beykoz.spamanagement.service.ISpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spa")
public class SpaController {

    private final ISpaService spaService;

    @Autowired
    public SpaController(ISpaService spaService) {
        this.spaService = spaService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<SpaDto>> getAllSpas() {
        List<SpaDto> spas = spaService.getAllSpas();
        return ResponseEntity.ok(spas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaDto> getSpaById(@PathVariable Long id) {
        SpaDto spa = spaService.findById(id);
        return ResponseEntity.ok(spa);
    }
}
