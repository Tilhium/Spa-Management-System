package com.beykoz.spamanagement.repository;

import com.beykoz.spamanagement.entity.Spa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaRepository extends JpaRepository<Spa, Long> {
}
