package com.agenda.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.exeption.ResourceNotFoundException;
import com.agenda.model.AgendaTelefonicaEntity;
import com.agenda.repository.AgendaTelefonicaRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class AgendaTelefonicaController {

	@Autowired
	AgendaTelefonicaRepository agendaTelefonicaRepository;

	/**
	 * Método para obtener el listado de agendas
	 * 
	 */
	@GetMapping("/agenda")
	public ResponseEntity<List<AgendaTelefonicaEntity>> getAllAgendasTelefonicas(
			@RequestParam(required = false) String title) {
		try {
			List<AgendaTelefonicaEntity> agendaTelefonicaData = new ArrayList<AgendaTelefonicaEntity>();
			agendaTelefonicaRepository.findAll().forEach(agendaTelefonicaData::add);

			if (agendaTelefonicaData.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(agendaTelefonicaData, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Método para obtener una agenda por id
	 * 
	 */
	@GetMapping("/agenda/{id}")
	public ResponseEntity<AgendaTelefonicaEntity> getAgendaTelefonicaById(@PathVariable("id") long id) {
		Optional<AgendaTelefonicaEntity> agendaTelefonicaData = agendaTelefonicaRepository.findById(id);

		if (agendaTelefonicaData.isPresent()) {
			return new ResponseEntity<>(agendaTelefonicaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Método para crear una agenda
	 * 
	 */
	@PostMapping("/agenda")
	public AgendaTelefonicaEntity createEmployee(@RequestBody AgendaTelefonicaEntity agendaTelefonicaEntity) {
		return agendaTelefonicaRepository.save(agendaTelefonicaEntity);
	}

	/**
	 * Método para actualizar una agenda
	 * 
	 */
	@PutMapping("/agenda/{id}")
	public ResponseEntity<AgendaTelefonicaEntity> updateAgendaTelefonica(@PathVariable Long id,
			@RequestBody AgendaTelefonicaEntity agendaTelefonicaEntityDetails) {
		AgendaTelefonicaEntity agendaTelefonicaEntity = agendaTelefonicaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No existe la agenda telefonica con el identificador :" + id));

		agendaTelefonicaEntity.setNombreContacto(agendaTelefonicaEntityDetails.getNombreContacto());
		agendaTelefonicaEntity.setTelefono(agendaTelefonicaEntityDetails.getTelefono());

		AgendaTelefonicaEntity updateAendaTelefonicaEntity = agendaTelefonicaRepository.save(agendaTelefonicaEntity);
		return ResponseEntity.ok(updateAendaTelefonicaEntity);
	}

	/**
	 * Método para eliminar una agenda
	 * 
	 */
	@DeleteMapping("/agenda/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAgendaTelefonica(@PathVariable Long id) {
		AgendaTelefonicaEntity agendaTelefonicaEntity = agendaTelefonicaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No existe la agenda telefonica con el identificador :" + id));

		agendaTelefonicaRepository.delete(agendaTelefonicaEntity);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
