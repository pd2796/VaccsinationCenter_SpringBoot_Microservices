package com.mocroervices.VaccinationCenter.model;

import java.util.List;

import com.mocroervices.VaccinationCenter.entity.VaccinationCenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequiredResponse {

	private VaccinationCenter center;
	private List<Citizen> citizens;
	public void setCitizens(List<Citizen> listCitizens) {
		// TODO Auto-generated method stub
		
	}
	public void setCenter(VaccinationCenter center2) {
		// TODO Auto-generated method stub
		
	}
	
}
