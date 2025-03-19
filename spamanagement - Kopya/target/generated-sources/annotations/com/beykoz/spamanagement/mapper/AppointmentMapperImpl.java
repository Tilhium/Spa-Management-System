package com.beykoz.spamanagement.mapper;

import com.beykoz.spamanagement.dto.AppointmentDto;
import com.beykoz.spamanagement.entity.Appointment;
import com.beykoz.spamanagement.entity.Customer;
import com.beykoz.spamanagement.entity.Employee;
import com.beykoz.spamanagement.entity.ExpertiseType;
import com.beykoz.spamanagement.entity.Spa;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-18T15:42:08+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentDto toDto(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDto appointmentDto = new AppointmentDto();

        appointmentDto.setSpaLocation( appointmentSpaLocation( appointment ) );
        appointmentDto.setEmployeeName( appointmentEmployeeName( appointment ) );
        ExpertiseType expertise = appointmentEmployeeExpertise( appointment );
        if ( expertise != null ) {
            appointmentDto.setEmployeeExpertise( expertise.name() );
        }
        appointmentDto.setCustomerName( appointmentCustomerName( appointment ) );
        appointmentDto.setId( appointment.getId() );
        appointmentDto.setAppointmentTime( appointment.getAppointmentTime() );

        return appointmentDto;
    }

    private String appointmentSpaLocation(Appointment appointment) {
        Spa spa = appointment.getSpa();
        if ( spa == null ) {
            return null;
        }
        return spa.getLocation();
    }

    private String appointmentEmployeeName(Appointment appointment) {
        Employee employee = appointment.getEmployee();
        if ( employee == null ) {
            return null;
        }
        return employee.getName();
    }

    private ExpertiseType appointmentEmployeeExpertise(Appointment appointment) {
        Employee employee = appointment.getEmployee();
        if ( employee == null ) {
            return null;
        }
        return employee.getExpertise();
    }

    private String appointmentCustomerName(Appointment appointment) {
        Customer customer = appointment.getCustomer();
        if ( customer == null ) {
            return null;
        }
        return customer.getName();
    }
}
