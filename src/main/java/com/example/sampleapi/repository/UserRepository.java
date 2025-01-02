package com.example.sampleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.sampleapi.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {

	//Users findByName(String name);
 //   public List<Users> findByNameContainsOrderByIdAsc(Long id);

//    public List<Users> findByEmailAndName(String email, String name);

//    public List<Users> findByLankAndStartdateBetweenOrderByIdAsc(Integer lank,Date start, Date end);

//    @Query("select t from Users t")
//    Stream<Users> streamAll();
}
