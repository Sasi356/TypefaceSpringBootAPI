package com.example.demo.controller;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.example.demo.model.FileDB;
import com.example.demo.service.FileStorageService;



@RestController
public class FileController {
	
	
	  @Autowired
	  private FileStorageService storageService;
	  
	  
	  //upload file using folderid
	  @PostMapping("/{fid}")
	  public String uploadFile(@RequestParam("file") MultipartFile file,@PathVariable String fid) {
	    String message = "";
	    try {
	      storageService.store(file,fid);

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      System.out.println(message);
	      return message;
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      System.out.println("Error while storing");
	      return message;
	    }
	  }
	  //create folder 
	  @PostMapping("/folder_upload")
	  public String UploadFolder(@RequestParam("folder") String name) {
		  try {
			  storageService.storefolder(name);  
			  return "success";
		  }
		  catch(Exception e) {
			  System.out.println("error while storing folder");
			  return "hello not";
		  } 
	  }
	  
	  //get all files in folder given a folderid
	  @GetMapping("/{folderid}")
	  public ResponseEntity<List<FileDB>> getListFiles(@PathVariable String folderid) {
		  List<FileDB> filesList = storageService.getallbyfid(folderid);

	    return ResponseEntity.status(HttpStatus.OK).body(filesList);
	  }
	  
	  //get file given a fileid and folderid of corresponding file
	  @GetMapping("/{folderid}/{fileid}")
	  public ResponseEntity<FileDB> getFilee(@PathVariable String folderid,@PathVariable String fileid ) {
	    FileDB fileDB = storageService.getFile(fileid,folderid);
	    return ResponseEntity.status(HttpStatus.OK).body(fileDB);
	  }
	  //delete file given a folderid and fileid
	  @DeleteMapping("/delete/{folderid}/{fileid}")
	  public ResponseEntity<String> delete(@PathVariable String folderid,@PathVariable String fileid){
			storageService.delete(folderid,fileid);
			return ResponseEntity.ok("Deleted the file with fileid "+fileid+" in folder with folderid "+folderid);
	  }
	  //delete all files in folder given a folderid
	  @DeleteMapping("/delete/{folderid}")
	  public ResponseEntity<String> delete1(@PathVariable String folderid){
		  storageService.deleteall(folderid);
		  return ResponseEntity.ok("deleted all files with folderid "+folderid);
	  }
	  
	  @PutMapping("/updateFolder/{folderid}/{newName}")
	  public ResponseEntity<String> updatee(@PathVariable String folderid,@PathVariable String newName){
		  storageService.updatefolder(newName,folderid);
		  return ResponseEntity.ok("foldername updated!");  
	  }
	  
	  @PutMapping("/updateFile/{folderid}/{fileid}")
	  public ResponseEntity<String> updateFile(@RequestParam("updatefilename") String newName,@PathVariable String folderid,@PathVariable String fileid) throws IOException{
		  storageService.updatefilee(folderid,fileid,newName);
		return ResponseEntity.ok("Updation successful");
		  
	  }
	  
	  
	 
	  	
	  
	  
	  
	  
	  

	
	  
	  
	  
	  
	  

}
