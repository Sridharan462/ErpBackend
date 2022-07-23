package com.example.ErpApp.Repository;

import com.example.ErpApp.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    public UserModel findByEmail(String email);
}