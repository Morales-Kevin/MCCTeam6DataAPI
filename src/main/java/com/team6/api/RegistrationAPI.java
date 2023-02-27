package com.team6.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.team6.domain.Registration;
import com.team6.repository.RegistrationRepository;

@RestController
@RequestMapping("/registrations")
public class RegistrationAPI {
//	ArrayList<Registration> registrationList = new ArrayList<Registration>();
//
//	@SuppressWarnings("deprecation")
//	public RegistrationAPI() {
//		Registration r1 = new Registration(1L, "1", "2", new Date(), "please email me the event details");
//		Registration r2 = new Registration(2L, "2", "2", new Date(), "send transportation and hotel booking");
//		Registration r3 = new Registration(3L, "3", "3", new Date(), "defer payments for a week");
//		
//		registrationList.add(r1);
//		registrationList.add(r2);
//		registrationList.add(r3);
//	}
//
//	@GetMapping
//	public Collection<Registration> getAll() {
//		return this.registrationList;
//	}
//
//	@GetMapping("/{registrationId}")
//	public Registration getCustomerById(@PathVariable("registrationId") long id) {
//		
//		Registration registration = null;
//		for (int i = 0; i < registrationList.size(); i++) {
//			if (registrationList.get(i).getId() == id) {
//				registration = registrationList.get(i);
//			}
//		}
//		return registration;
//	}
	
	@Autowired
	RegistrationRepository repo;
	
	@GetMapping
	public Iterable<Registration> getAll() {
		return repo.findAll();
	}
	
	@GetMapping("/{registrationId}")
	public Optional<Registration> getRegistrationById(@PathVariable("registrationId")
	long id) {
		return repo.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<?> addRegistration(@RequestBody Registration newRegistration,
	UriComponentsBuilder uri) {
		if (newRegistration.getId()!=0
		|| newRegistration.getEvent_id()==null
		|| newRegistration.getCustomer_id() == null
		|| newRegistration.getRegistration_date() == null
		|| newRegistration.getNotes() == null) { 
			return ResponseEntity.badRequest().build();
		}
		newRegistration=repo.save(newRegistration);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(newRegistration.getId()).toUri();
		ResponseEntity<?> response=ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{registrationId}")
	public ResponseEntity<?> putRegistration(@RequestBody Registration newRegistration,
	@PathVariable("registrationId") long id) {
		if (newRegistration.getId()!= id
		|| newRegistration.getEvent_id()==null
		|| newRegistration.getCustomer_id() == null
		|| newRegistration.getRegistration_date() == null
		|| newRegistration.getNotes() == null) {
			return ResponseEntity.badRequest().build();
		}
		newRegistration=repo.save(newRegistration);
		return ResponseEntity.ok().build();
	}

	
	@DeleteMapping("/{registrationId}")
	public void deleteRegistrationById(@PathVariable("registrationId") long id) {
		repo.deleteById(id);
	}	
}


