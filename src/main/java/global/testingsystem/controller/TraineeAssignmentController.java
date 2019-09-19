package global.testingsystem.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import global.testingsystem.DTO.TraineeDTO;
import global.testingsystem.entity.ClassLesson;
import global.testingsystem.entity.CustomError;
import global.testingsystem.entity.Exam;
import global.testingsystem.entity.Question;
import global.testingsystem.entity.Subject;
import global.testingsystem.entity.TraineeAssignment;
import global.testingsystem.service.ClassLessonService;
import global.testingsystem.service.TraineeAssignmentService;
import global.testingsystem.util.ConstantPage;

import org.json.JSONException;
import org.json.JSONObject;

@CrossOrigin(origins = "*")
@RestController
public class TraineeAssignmentController {
	// ngo minh anh
	@Autowired
	TraineeAssignmentService traineeAssignmentService;
	@Autowired
	ClassLessonService classLessonService;
	private Logger log = Logger.getLogger(TraineeAssignmentController.class);
	@GetMapping(value = ConstantPage.REST_API_GET_LIST_TRAINEE_ASSIGNMENT_BY_CLASS_ID)
	public List<TraineeDTO> getListByClassId(@PathVariable int classId) {
		return traineeAssignmentService.getTraineeAssimentByClassId(classId);
	}	
	@PostMapping(value = ConstantPage.REST_API_GET_LIST_TRAINEE_ASSIGNMENT_BY_CLASS_ID)
	public void update(@RequestParam("trainee") String trainee) {
	       JSONObject jsonObject = new JSONObject(trainee);
	       int id = jsonObject.getInt("id");
	       String name=jsonObject.getString("fullName");
	       int mark = jsonObject.getInt("result");
	       String comment = jsonObject.getString("resultDetail");
	       TraineeAssignment ta = traineeAssignmentService.findById(id);
	       ta.setResultDetail(comment);
	       ta.setResult(String.valueOf(mark));
	       try {
	       traineeAssignmentService.save(ta);
	       }catch(Exception e) {
	    	   e.printStackTrace();
	       }
	       return;
	}
	@PostMapping(value = ConstantPage.REST_API_UPDATE_TRAINEEASSINMENT, produces = {MediaType.APPLICATION_PROBLEM_JSON_VALUE})
	public ResponseEntity<Object> updateTraineeAssignment(@RequestParam("traineeAssignment") String traineeAssignment) {
		JSONObject jsonObject = new JSONObject(traineeAssignment);
		int id = jsonObject.getInt("id");
		String evaluator = jsonObject.getString("evaluator");
		int mark = jsonObject.getInt("result");
		String comment = jsonObject.getString("resultDetail");
		TraineeAssignment ta = traineeAssignmentService.findById(id);
		ta.setEvaluator(evaluator);
		ta.setResult(String.valueOf(mark));
		ta.setResultDetail(comment);
		ta.setTimeEval(new Date());
		try {
			traineeAssignmentService.save(ta);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	} 
	@GetMapping(value = ConstantPage.REST_API_GET_TRAINEEASSIGNMENT_BY_ClassLesson_ID)
	public List<TraineeDTO> getListByClassIdAndTraineeAssignmentId(@PathVariable int classId, @PathVariable int traineeassignmentId) {
		return traineeAssignmentService.getTraineeAssimentByClassIdAndTraineeAssignmentId(classId, traineeassignmentId);
	}
	@PostMapping(value = ConstantPage.REST_API_GET_LIST_TRAINEE_ASSIGNMENT_SEARCH, produces = {MediaType.APPLICATION_PROBLEM_JSON_VALUE})
	public List<TraineeDTO> listSeach(@RequestParam("searchKey") String searchKey){
		JSONObject jsonObject = new JSONObject(searchKey);
		String search = jsonObject.getString("searchKey");
		int id = jsonObject.getInt("id");
		List<TraineeDTO> list = traineeAssignmentService.listSearch(id, search);
				return list;
		}
	
	@GetMapping(value = ConstantPage.REST_API_GET_ASSIGNMENT_OF_TRAINE)
	public ResponseEntity<InputStreamResource> downloadTxt(HttpServletRequest request, @PathVariable int id)
			throws IOException {
		HttpHeaders responseHeader = new HttpHeaders();
		TraineeAssignment traineeAssigment = traineeAssignmentService.findById(id);
		
		String fileName = traineeAssigment.getFile();

		try {
			String cwd = System.getProperty("user.dir");
			String classPath = cwd + "/" + "upload-dir/assigment/"+fileName;
			File file = ResourceUtils.getFile(classPath);
			byte[] data = FileUtils.readFileToByteArray(file);
			// Set mimeType trả về
			responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			// Thiết lập thông tin trả về
			responseHeader.set("Content-disposition", "attachment; filename=" + file.getName());
			responseHeader.setContentLength(data.length);
			InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
			InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
			return new ResponseEntity<InputStreamResource>(inputStreamResource, responseHeader, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}

