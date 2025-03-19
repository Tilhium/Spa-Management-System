package com.beykoz.spamanagement.service.impl;

import com.beykoz.spamanagement.dto.SpaDto;
import com.beykoz.spamanagement.entity.Spa;
import com.beykoz.spamanagement.mapper.SpaMapper;
import com.beykoz.spamanagement.repository.SpaRepository;
import com.beykoz.spamanagement.service.ISpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaService implements ISpaService {

    private final SpaRepository spaRepository;
    private final SpaMapper spaMapper;

    @Autowired
    public SpaService(SpaRepository spaRepository, SpaMapper spaMapper) {
        this.spaRepository = spaRepository;
        this.spaMapper = spaMapper;
    }

    @Override
    public List<SpaDto> getAllSpas() {
        List<Spa> spas = spaRepository.findAll();
        return spaMapper.toDtoList(spas);
    }

    @Override
    public SpaDto findById(Long id) {
        Spa spa = spaRepository.findById(id).orElseThrow(() -> new RuntimeException("Spa not found"));
        return spaMapper.toDto(spa);
    }
}
