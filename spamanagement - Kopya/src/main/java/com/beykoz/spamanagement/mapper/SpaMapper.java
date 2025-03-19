package com.beykoz.spamanagement.mapper;

import com.beykoz.spamanagement.dto.SpaDto;
import com.beykoz.spamanagement.entity.Spa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpaMapper {
    SpaDto toDto(Spa spa);

    List<SpaDto> toDtoList(List<Spa> spas);
}
