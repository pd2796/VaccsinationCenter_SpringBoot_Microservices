package com.mocroervices.VaccinationCenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mocroervices.VaccinationCenter.entity.VaccinationCenter;
import com.mocroervices.VaccinationCenter.model.Citizen;
import com.mocroervices.VaccinationCenter.model.RequiredResponse;
import com.mocroervices.VaccinationCenter.repository.CenterRepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

	@Autowired
	private CenterRepo centerRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping(path ="/add")
	public ResponseEntity<VaccinationCenter> addCitizen(@RequestBody VaccinationCenter vaccinationCenter) {
		
		VaccinationCenter vaccinationCenterAdded = centerRepo.save(vaccinationCenter);
		return new ResponseEntity<>(vaccinationCenterAdded, HttpStatus.OK);
	} 
	
	@GetMapping(path="id/{id}")
	@HystrixCommand(fallbackMethod = "handleCitizenDownTime")
	public ResponseEntity<RequiredResponse> getAllDataBasedOnCenterId(@PathVariable Integer id ){
		RequiredResponse requiredResponse = new RequiredResponse();
		VaccinationCenter center = centerRepo.findById(id).get();
		requiredResponse.setCenter(center); 
		
		List<Citizen> listCitizens = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/"+id, List.class);
		requiredResponse.setCitizens(listCitizens);
		return new ResponseEntity<RequiredResponse>(requiredResponse,HttpStatus.OK);
	}
	public ResponseEntity<RequiredResponse> handleCitizenDownTime(@PathVariable Integer id ){
		RequiredResponse requiredResponse = new RequiredResponse();
		VaccinationCenter center = centerRepo.findById(id).get();
		requiredResponse.setCenter(center); 
		return new ResponseEntity<RequiredResponse>(requiredResponse,HttpStatus.OK);
	}
}
