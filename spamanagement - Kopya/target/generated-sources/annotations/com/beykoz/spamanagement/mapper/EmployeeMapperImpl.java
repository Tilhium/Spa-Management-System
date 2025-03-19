package com.beykoz.spamanagement.mapper;

import com.beykoz.spamanagement.dto.AppointmentDto;
import com.beykoz.spamanagement.dto.EmployeeDto;
import com.beykoz.spamanagement.entity.Appointment;
import com.beykoz.spamanagement.entity.Employee;
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
public class EmployeeMapperImpl implements EmployeeMapper {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public EmployeeDto toDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        if ( employee.getExpertise() != null ) {
            employeeDto.setExpertise( employee.getExpertise().name() );
        }
        employeeDto.setId( employee.getId() );
        employeeDto.setName( employee.getName() );
        employeeDto.setAge( employee.getAge() );
        employeeDto.setSalary( employee.getSalary() );
        employeeDto.setAppointments( appointmentListToAppointmentDtoList( employee.getAppointments() ) );

        return employeeDto;
    }

    @Override
    public List<EmployeeDto> toDtoList(List<Employee> employees) {
        if ( employees == null ) {
            return null;
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>( employees.size() );
        for ( Employee employee : employees ) {
            list.add( toDto( employee ) );
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
