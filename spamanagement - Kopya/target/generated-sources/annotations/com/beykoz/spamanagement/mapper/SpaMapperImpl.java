package com.beykoz.spamanagement.mapper;

import com.beykoz.spamanagement.dto.SpaDto;
import com.beykoz.spamanagement.entity.Spa;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-18T14:50:01+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class SpaMapperImpl implements SpaMapper {

    @Override
    public SpaDto toDto(Spa spa) {
        if ( spa == null ) {
            return null;
        }

        SpaDto spaDto = new SpaDto();

        spaDto.setId( spa.getId() );
        spaDto.setLocation( spa.getLocation() );

        return spaDto;
    }

    @Override
    public List<SpaDto> toDtoList(List<Spa> spas) {
        if ( spas == null ) {
            return null;
        }

        List<SpaDto> list = new ArrayList<SpaDto>( spas.size() );
        for ( Spa spa : spas ) {
            list.add( toDto( spa ) );
        }

        return list;
    }
}
