package global.testingsystem.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import global.testingsystem.entity.Chapter;
import global.testingsystem.entity.Exam;
import global.testingsystem.entity.Subject;
import global.testingsystem.entity.Unit;
import global.testingsystem.service.ChapterService;
import global.testingsystem.service.DomainService;
import global.testingsystem.service.ExamService;
import global.testingsystem.service.SubjectService;
import global.testingsystem.service.UnitService;
import global.testingsystem.util.ConstantPage;

@CrossOrigin(origins = "*")
@RestController
public class UnitController {

	@Autowired
	private UnitService unitService;

	@Autowired
	SubjectService subjectService;

	@Autowired
	ChapterService chapterService;

	@Autowired
	DomainService domainService;

	@Autowired
	ExamService examService;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	global.testingsystem.service.UploadFileService uploadFileService;

	List<String> files = new ArrayList<>();

	private Logger log = Logger.getLogger(UnitController.class);

	@GetMapping(value = ConstantPage.REST_API_LIST_UNIT, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE, })
	public List<Unit> getAllUnit() {
		return unitService.getAllUnit();
	}
	

	@PostMapping(value = ConstantPage.REST_API_CREATE_UNIT, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE, })
	public ResponseEntity<Object> createUnit(@RequestParam("unit") String unit,
			@RequestParam(value = "fileDocument", required = false) MultipartFile fileDocument) throws ParseException {
		JSONObject jsonObject = new JSONObject(unit);
		String name = jsonObject.getString("name");
		String description = jsonObject.getString("description");
		String contentType = jsonObject.getString("contentType");
		int chapterId = jsonObject.getInt("chapterId");
		Chapter chapters = chapterService.findChapterById(chapterId);
		int unitOrder = unitService.countUnitByChapter(chapterId)+1;	
	
		Unit newUnit = new Unit();
		newUnit.setChapter(chapters);
		newUnit.setName(name);
		newUnit.setDescription(description);
		newUnit.setContentType(contentType);
		newUnit.setUnitOrder(unitOrder);
		Exam exam = new Exam();
		String mediaLink = null;
		if (jsonObject.isNull("mediaLink") != true) {
			mediaLink = jsonObject.getString("mediaLink");
			newUnit.setMediaLink(mediaLink);
		}

		if (jsonObject.isNull("exam") != true) {
			int exam_id = jsonObject.getInt("exam");
			exam = examService.getExamById(exam_id);
			newUnit.setExam(exam);
		}

	
		if (fileDocument != null) {
			String pathToSave = servletContext.getRealPath(ConstantPage.PATH_SAVE_DOCUMENT_UPLOAD);
			File documentFile = new File(pathToSave + "/" + fileDocument.getOriginalFilename());
			try {
				// transfer the received file to the given destination file
				fileDocument.transferTo(documentFile);
				newUnit.setDocument(fileDocument.getOriginalFilename());
			} catch (IllegalStateException e) {
				log.error("Co loi khi upload file");
			} catch (IOException e) {
				log.error("Sai duong dan file");
			}
		}

		boolean isSuccess = unitService.createUnit(newUnit);
		return new ResponseEntity<Object>(isSuccess, HttpStatus.OK);
	}

	@PostMapping(value = ConstantPage.REST_API_EDIT_UNIT, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE, })
	public ResponseEntity<Object> editUnit(@RequestParam("unit") String data,
			@RequestParam(value = "fileDocument", required = false) MultipartFile fileDocument) throws ParseException {
		JSONObject jsonObject = new JSONObject(data);
		int unitId = jsonObject.getInt("unitId");
		Unit unit = unitService.getUnitById(unitId);
		String name = jsonObject.getString("name");
		String description = jsonObject.getString("description");
		String contentType = jsonObject.getString("contentType");
	

		unit.setName(name);
		unit.setDescription(description);
		unit.setContentType(contentType);

		Exam exam = new Exam();
		String mediaLink = null;
		if (jsonObject.isNull("mediaLink") != true) {
			mediaLink = jsonObject.getString("mediaLink");
			unit.setMediaLink(mediaLink);
		}else {
			unit.setMediaLink(null);
		}

		if (jsonObject.isNull("exam") != true) {
			int exam_id = jsonObject.getInt("exam");
			exam = examService.getExamById(exam_id);
			unit.setExam(exam);
		}else {
			unit.setExam(null);
		}

	
		if (fileDocument != null) {
			String pathToSave = servletContext.getRealPath(ConstantPage.PATH_SAVE_DOCUMENT_UPLOAD);
			File documentFile = new File(pathToSave + "/" + fileDocument.getOriginalFilename());
			try {
				// transfer the received file to the given destination file
				fileDocument.transferTo(documentFile);
				unit.setDocument(fileDocument.getOriginalFilename());
			} catch (IllegalStateException e) {
				log.error("Co loi khi upload file");
			} catch (IOException e) {
				log.error("Sai duong dan file");
			}
		}else {
			unit.setDocument(null);
		}

		boolean isSuccess = unitService.createUnit(unit);
		return new ResponseEntity<Object>(isSuccess, HttpStatus.OK);
	}

	@DeleteMapping(value = ConstantPage.REST_API_DELETE_UNIT)
	public ResponseEntity<String> deleteUnitById(@PathVariable("id") int id) {
		Optional<Unit> unit = unitService.findById(id);
		if (unit.isPresent()) {
			unitService.deleteUnit(id);
			return new ResponseEntity<String>("Delete succses", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = ConstantPage.REST_API_SEARCH_UNIT, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE, })
	public List<Unit> searchUnit(@RequestParam("data") String data) {
		JSONObject jsonObject = new JSONObject(data);
		String key = jsonObject.getString("key");
		List<Unit> listUnit = unitService.searchUnit(key);
		return listUnit;
	}

	@GetMapping(value = ConstantPage.REST_API_GET_UNIT_BY_ID)
	public Unit getUnitById(@PathVariable int id) {
		Unit unit =  unitService.getUnitById(id);
		return unit;
	}

	@GetMapping(value = ConstantPage.REST_API_GET_LIST_UNIT_BY_CHAPTER_ORDER_ASC, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<List<Unit>> getUnitByChapterAndOrderASC(@PathVariable("chapterId") int chapterId) {
		return new ResponseEntity<List<Unit>>(unitService.getUnitByChapterAndOrderASC(chapterId), HttpStatus.OK);
	}

	@PutMapping(value = ConstantPage.REST_API_MOVE_UNIT_AND_CHAPTER, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<String> moveUnitAndChapter(@RequestParam("data") String data) {
		JSONObject jsonObject = new JSONObject(data);
		int id = jsonObject.getInt("id");
		int parentId = jsonObject.getInt("parentId");
		int currentPosition = jsonObject.getInt("currentPosition");
		int desiredPosition = jsonObject.getInt("desiredPosition");
		if (parentId > -1) {
			chapterService.updateDraggedChapter(currentPosition, id, parentId);
			if (desiredPosition > currentPosition) {
				chapterService.moveChapterDown(currentPosition, desiredPosition, id, parentId);
				chapterService.moveChapter(desiredPosition, id, parentId);
			} else {
				chapterService.moveChapterUp(currentPosition, desiredPosition, id, parentId);
				chapterService.moveChapter(desiredPosition, id, parentId);
			}
			return new ResponseEntity<String>("Update Success", HttpStatus.OK);
		} else {
			unitService.updateDraggedUnit(currentPosition, id);
			if (desiredPosition > currentPosition) {
				unitService.moveUnitDown(currentPosition, desiredPosition, id);
				unitService.moveUnit(desiredPosition, id);
			} else {
				unitService.moveUnitUp(currentPosition, desiredPosition, id);
				unitService.moveUnit(desiredPosition, id);
			}
			return new ResponseEntity<String>("Update Success", HttpStatus.OK);
		}
	}

}
