package com.example.demo.frepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FileDB;
import com.example.demo.model1.FolderDB;

public interface FolderDBRepository extends JpaRepository<FolderDB,String>{

}
