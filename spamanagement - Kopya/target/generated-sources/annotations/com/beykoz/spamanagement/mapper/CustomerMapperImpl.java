package com.beykoz.spamanagement.mapper;

import com.beykoz.spamanagement.dto.AppointmentDto;
import com.beykoz.spamanagement.dto.CustomerDto;
import com.beykoz.spamanagement.entity.Appointment;
import com.beykoz.spamanagement.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-18T02:24:22+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public CustomerDto toDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId( customer.getId() );
        customerDto.setName( customer.getName() );
        customerDto.setAge( customer.getAge() );
        customerDto.setAppointments( appointmentListToAppointmentDtoList( customer.getAppointments() ) );

        return customerDto;
    }

    @Override
    public List<CustomerDto> toDtoList(List<Customer> customers) {
        if ( customers == null ) {
            return null;
        }

        List<CustomerDto> list = new ArrayList<CustomerDto>( customers.size() );
        for ( Customer customer : customers ) {
            list.add( toDto( customer ) );
        }

        return list;
    }

    protected List<AppointmentDto> appointmentListToAppointmentDtoList(List<Appointment> list) {
        if ( list == null ) {
            return null;
        }

        List<AppointmentDto> list1 = new ArrayList<AppointmentDto>( list.size() );
        for ( Appointment appointment : list ) {
            list1.add( appointmentMapper.toDto( appointment ) );
        }

        return list1;
    }
}
