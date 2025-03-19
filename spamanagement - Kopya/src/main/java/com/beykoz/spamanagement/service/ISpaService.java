package com.beykoz.spamanagement.service;

import com.beykoz.spamanagement.dto.SpaDto;
import com.beykoz.spamanagement.entity.Spa;

import java.util.List;

public interface ISpaService {
    List<SpaDto> getAllSpas();
    SpaDto findById(Long id);
}
