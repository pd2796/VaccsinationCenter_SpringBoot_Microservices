package com.mocroervices.VaccinationCenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class VaccinationCenter {
	
	
	private int id;
	
	private String centerName;
	
	private String centerAddress;
	

}
