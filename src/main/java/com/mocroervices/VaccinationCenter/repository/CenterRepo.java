package com.mocroervices.VaccinationCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mocroervices.VaccinationCenter.entity.VaccinationCenter;

public interface CenterRepo extends JpaRepository<VaccinationCenter, Integer> {

}
