package com.ashok.reports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashok.reports.model.Employee;
import com.ashok.reports.service.EmployeeService;


@Controller
public class EmployeeController {

   @Autowired
   private EmployeeService bookService;
   
	@GetMapping("/")
	public String sayHello() {
		return "home";
	}

   /*---Add new book---*/
   @PostMapping("/employee")
   public ResponseEntity<?> save(@RequestBody Employee book) {
      long id = bookService.save(book);
      return ResponseEntity.ok().body("New Book has been saved with ID:" + id);
   }

   /*---Get a book by id---*/
   @GetMapping("/employee/{id}")
   public ResponseEntity<Employee> get(@PathVariable("id") long id) {
	  Employee book = bookService.get(id);
      return ResponseEntity.ok().body(book);
   }

   /*---get all books---*/
   @GetMapping("/employee")
   public ResponseEntity<List<Employee>> list() {
      List<Employee> books = bookService.list();
      return ResponseEntity.ok().body(books);
   }

   /*---Update a book by id---*/
   @PutMapping("/employee/{id}")
   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Employee book) {
      bookService.update(id, book);
      return ResponseEntity.ok().body("Book has been updated successfully.");
   }

   /*---Delete a book by id---*/
   @DeleteMapping("/employee/{id}")
   public ResponseEntity<?> delete(@PathVariable("id") long id) {
      bookService.delete(id);
      return ResponseEntity.ok().body("Book has been deleted successfully.");
   }
}