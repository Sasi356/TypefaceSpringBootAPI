package com.example.demo.model1;

import java.util.ArrayList;

import org.hibernate.annotations.GenericGenerator;

import com.example.demo.model.FileDB;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="folders")
public class FolderDB {
	

	@Id
	@GeneratedValue(generator = "uuid")
	  @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String name;
	

	  public FolderDB() {
	  }

	  public FolderDB(String name) {
	    this.name = name;
	    //this.contents = contents;
	  }

	 
	
	  public void setName(String name) {
		    this.name = name;
		  }

	/*	  
	  public ArrayList<FileDB> getContents() {
	    return contents;
	  }
	  public void setContents(ArrayList<FileDB> contents) {
	    this.contents = contents;
	  }
	  */
}
