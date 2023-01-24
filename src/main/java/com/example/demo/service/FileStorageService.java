package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.frepository.FolderDBRepository;
import com.example.demo.model.FileDB;
import com.example.demo.model1.FolderDB;
import com.example.demo.repository.FileDBRepository;


@Service
public class FileStorageService {
	
	 @Autowired
	  private FileDBRepository fileDBRepository;
	 @Autowired
	 private FolderDBRepository folderDBRepository;
	 
	 
	  public FileDB store(MultipartFile file,String fid) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	   
	    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(),fid);
	   
	    return fileDBRepository.save(FileDB);
	  }

	  public FileDB getFile(String fileid,String folderid) {
		  List<FileDB> FilesList = fileDBRepository.findAll();
		  for(FileDB itr: FilesList) {
			  if(itr.getFid().equals(folderid)) {
				  return itr;
			  }
			 
		  }
		  System.out.println("File Not found");
	    return null;
	  }
	  /*
	  public Stream<FileDB> getAllFiles() {
	    return fileDBRepository.findAll().stream();
	  }
*/
	public void delete(String folderid,String fileid) {
		FileDB file = fileDBRepository.findById(fileid).orElseThrow(null);
		if(file.getFid().equals(folderid)) {
			fileDBRepository.delete(file);
		}
	}
/*
	public void update(FileDB OldFile, FileDB NewFile) {
			OldFile.setName(NewFile.getName());
			OldFile.setType(NewFile.getType());
			OldFile.setData(NewFile.getData());
			fileDBRepository.save(OldFile);
	
	}*/

	public void deleteall(String folderid) {
		List<FileDB> FilesList = fileDBRepository.findAll();
		for(FileDB itr: FilesList) {
			if(itr.getFid().equals(folderid)) {
				fileDBRepository.delete(itr);
			}
		}
	}

	public void updatefilee(String folderid,String fileid,String newName) throws IOException {
		List<FileDB> FileList = fileDBRepository.findAll();
		 
		for(FileDB itr: FileList) {
			if(itr.getFid().equals(folderid)) {
				if(itr.getId().equals(fileid)) {
					itr.setName(newName);
				}
			}
		}
	}
	
	
	
	
public String storefolder(String name) throws IOException {
		
		FolderDB fdb = new FolderDB(name);
		
	    folderDBRepository.save(fdb);
	    return "Successful";
	    
	  }
public void updatefolder(String newName,String folderid) {
	FolderDB oldFolder = folderDBRepository.findById(folderid).orElseThrow(null);
	System.out.println(oldFolder);
	oldFolder.setName(newName);
	folderDBRepository.save(oldFolder);
}

public List<FileDB> getallbyfid(String folderid) {
	List<FileDB> list = new ArrayList<FileDB>();
	
	List<FileDB> allfiles = fileDBRepository.findAll();
	for(FileDB itr: allfiles) {
		if(itr.getFid().equals(folderid)) {
			list.add(itr);
		}
	}
	
	return list;
}




	
	  
	  
	  
	 
	
	
}
