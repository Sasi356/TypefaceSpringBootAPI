package com.example.demo;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.frepository.FolderDBRepository;
import com.example.demo.model.FileDB;
import com.example.demo.model1.FolderDB;
import com.example.demo.repository.FileDBRepository;
import com.example.demo.service.FileStorageService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TypefaceSpringBootApplicationTests {
	
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private MockMvc mockmvc;
	
	@Autowired
	private FileDBRepository filedbrep;
	
	@Autowired 
	private FolderDBRepository folderdbrep;
	
	
	@Autowired
	 private FileStorageService storageService;
	/*
	@Test
	public void getFileTest() throws Exception {
		
		String folderid = "aa1c0b26-49dd-4311-8d54-b09432d8eb86";
		String fileid = "b6db1e23-110e-4458-a634-ea53357d64b9";
		String url = "http://localhost:8080/"+folderid+"/"+fileid;
		
	    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).build();
	    System.out.println("Hello everyoneeeeee           "+builder.toString());
		HttpEntity<String> requestEntity = new HttpEntity<>(null, null);
		ResponseEntity<String> response = testRestTemplate.exchange(builder.toString(), HttpMethod.GET, requestEntity,
				String.class);
		System.out.println("Hello everyoneeeeee           "+response.getBody());

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	    
	}
	
    @Test
    public void getFileTest() {
    	String folderid = "aa1c0b26-49dd-4311-8d54-b09432d8eb86";
		String fileid = "b6db1e23-110e-4458-a634-ea53357d64b9";
		String url = "http://localhost:8080/"+folderid+"/"+fileid;
		
        ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        //Assertions.assertEquals("{\"name\":\"John\",\"age\":30}", response.getBody());
    }
    
    */
	//get single file 
	
	@Test
	public void createFolder() throws Exception{
		String url = "http://localhost:8080/folder_upload";
		mockmvc.perform(post(url)
			    .param("folder", "helloo"))
			    .andExpect(status().isOk());
	}

	
	@Test
	public void createFile() throws Exception{
		String folderid = "cc9155d3-db13-4c77-8310-06c280109e36";
		String url = "http://localhost:8080/"+folderid;
		MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello World".getBytes());
		mockmvc.perform(multipart(url).file(file))
		    .andExpect(status().isOk());
		
	}
	
	
	
	
	@Test
	public void getFileTest() throws Exception {
		String folderid = "aa1c0b26-49dd-4311-8d54-b09432d8eb86";
		String fileid = "b6db1e23-110e-4458-a634-ea53357d64b9";
		String url = "http://localhost:8080/"+folderid+"/"+fileid;
		mockmvc.perform(get(url)).andExpect(status().isOk());
	}
	
	//get all files in a folder
	@Test
	public void getFileByFid() throws Exception{
		String folderid = "aa1c0b26-49dd-4311-8d54-b09432d8eb86";
		String url = "http://localhost:8080/"+folderid;
		mockmvc.perform(get(url)).andExpect(status().isOk());
	}
	
	
	@Test
	public void updateFolderTest() throws Exception{
		String folderid = "aa1c0b26-49dd-4311-8d54-b09432d8eb86";
		String url = "http://localhost:8080/updateFolder/"+folderid+"/zoology";
		mockmvc.perform(put(url)).andExpect(status().isOk());
		
	}
	
	@Test
	public void updateFileTest() throws Exception{
		String folderid = "aa1c0b26-49dd-4311-8d54-b09432d8eb86";
		String fileid = "0b3127d3-af0b-4042-85d4-e42ee9090991";
		String url = "http://localhost:8080/updateFile/"+folderid+"/"+fileid;
		mockmvc.perform(put(url).param("updatefilename", "ecology")).andExpect(status().isOk());
		
	}
	
	
	
	@Test
	public void DeleteFileTest() throws Exception{
		String folderid = "aa1c0b26-49dd-4311-8d54-b09432d8eb86";
		String fileid = "0b3127d3-af0b-4042-85d4-e42ee9090991";
		String url = "http://localhost:8080/delete/"+folderid+"/"+fileid;
		mockmvc.perform(delete(url)).andExpect(status().isOk());	
	}

	@Test
	public void DeleteFolderTest() throws Exception{
		String folderid = "498abd01-4874-4c79-9af4-dbb6a87e28ea";
		String url = "http://localhost:8080/delete/"+folderid;
		mockmvc.perform(delete(url)).andExpect(status().isOk());
		
	}
	


	
	@Test
	public void setterFileTest() {
		String fid = "cc9155d3-db13-4c77-8310-06c280109e36";
		String id = "0346cdb1-c89e-417d-ae75-41e483d57157";
		System.out.println(id);

		FileDB filedb = storageService.getFile(id, fid);
		System.out.println(filedb.getName());
		filedb.setName("sasii");
		filedbrep.save(filedb);
		Assertions.assertEquals("sasii", filedb.getName());
		
	}
	
	@Test
	public void mainTest() {
		String[] arg= {"arg1","rg2"};
		TypefaceSpringBootApplication.main(arg);
	}
	
	
	
	
	

	
	
	}


