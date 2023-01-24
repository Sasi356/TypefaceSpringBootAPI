package com.example.demo.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="files")
public class FileDB {
	
	@Id
	@GeneratedValue(generator = "uuid")
	  @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String name;

	  private String type;

	  @Lob
	  private byte[] data;
	  
	  private String fid;

	  public FileDB() {
	  }

	  public FileDB(String name, String type, byte[] data,String fid) {
	    this.name = name;
	    this.type = type;
	    this.data = data;
	    this.fid = fid;
	  }

	  public String getId() {
	    return id;
	  }

	  public String getName() {
	    return name;
	  }
	  
	  
	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getType() {
	    return type;
	  }

	

	  public byte[] getData() {
	    return data;
	  }

	
	  
	  public String getFid() {
		  return fid;
	  }
	  
	 
	}
	


