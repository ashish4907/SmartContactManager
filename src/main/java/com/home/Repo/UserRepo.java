package com.home.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.DTO.User;

public interface UserRepo extends JpaRepository< User,Integer>{

}
