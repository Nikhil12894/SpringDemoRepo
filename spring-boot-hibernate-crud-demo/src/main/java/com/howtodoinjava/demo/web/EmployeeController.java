package com.howtodoinjava.demo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;
import com.howtodoinjava.nk.EntityModelLoader;
import com.howtodoinjava.nk.ResourceLoader;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{
	@Autowired
	EmployeeService service;

	@SuppressWarnings("unchecked")
	@GetMapping
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
		List<EmployeeEntity> list = service.getAllEmployees();
		ResourceLoader resource = ResourceLoader.getResourceLoader();
		Map<String,Object> map= resource.redResourceAsJson("META-INF/config.json");
		ArrayList<String> list1=(ArrayList<String>) map.get("packageArray");
		Gson gson = new Gson();
		list1.forEach(packageName->{
			try {
				System.out.println(gson.toJson(EntityModelLoader.getClassesForPackage(packageName)));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
		return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
			throws RecordNotFoundException {
		EmployeeEntity entity = service.getEmployeeById(id);

		return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(EmployeeEntity employee)
			throws RecordNotFoundException {
		EmployeeEntity updated = service.createOrUpdateEmployee(employee);
		return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
			throws RecordNotFoundException {
		service.deleteEmployeeById(id);
		return HttpStatus.FORBIDDEN;
	}

}