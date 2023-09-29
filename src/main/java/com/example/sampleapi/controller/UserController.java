package com.example.sampleapi.controller;

import com.example.sampleapi.entity.User;
import com.example.sampleapi.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  public List<User> getAll() {
    return this.userRepository.findAll();
  }

  @GetMapping("{id}")
  public User getById(@PathVariable("id") Long id) {
    return this.userRepository.findById(id).orElseThrow();
  }

  @PostMapping
  public void save(@RequestBody User user) {
    this.userRepository.save(user);
  }

  @PutMapping("{id}")
  public void update(@PathVariable("id") Long id, @RequestBody User user) {
    user.setId(id);
    this.userRepository.save(user);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable("id") Long id) {
    this.userRepository.deleteById(id);
  }
}
