package com.example.sampleapi.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sampleapi.entity.Users;
import com.example.sampleapi.repository.UserRepository;
import com.example.sampleapi.repository.UserSpecifications;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  //
  // ユーザー全件取得
  //
  @GetMapping
  public List<Users> getAll() {
    return this.userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
  }

  //
  // PKキーを指定しユーザーを取得
  //
  @GetMapping("{id}")
  public Users getById(@PathVariable("id") Long id) {
    return this.userRepository.findById(id).orElseThrow();
  }

  //
  // あいまい検索(氏名、email）
  //
  @GetMapping("/search")
  public  List<Users> queryAll(@RequestParam("name") String name,@RequestParam("email") String email) {
    return  this.userRepository.findAll(Specification
            .where(UserSpecifications.nameContains(name))
            .and(UserSpecifications.emailContains(email))
            , Sort.by(Sort.Direction.ASC, "id"));
  }
  
  //
  // ユーザーの登録・変更
  //
  @PostMapping
  public void save(@RequestBody Users user) {
    this.userRepository.save(user);
  }
  
  //
  // 指定したIDのユーザーを削除
  //
  @DeleteMapping("{id}")
  public void delete(@PathVariable("id") Long id) {
    this.userRepository.deleteById(id);
  }
}
