/**
 * 
 */
package global.testingsystem.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import global.testingsystem.DTO.ChapterDto;
import global.testingsystem.entity.Chapter;
import global.testingsystem.entity.Exam;
import global.testingsystem.entity.Subject;
import global.testingsystem.entity.Unit;
import global.testingsystem.response.SubjectResult;
import global.testingsystem.service.ChapterService;
import global.testingsystem.service.ExamService;
import global.testingsystem.service.SubjectService;
import global.testingsystem.service.TraineeAssignmentService;
import global.testingsystem.service.UnitService;
import global.testingsystem.util.ConstantPage;

/**
 * @author USER
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class ChapterController {

	@Autowired
	private ChapterService chapterService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ExamService examService;
	@Autowired
	private TraineeAssignmentService traineeService;
	@Autowired
	private UnitService unitService;

	@GetMapping(value = ConstantPage.REST_API_GET_ALL_CHAPTER, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public List<Object> getListChapter() {
		return chapterService.getListChapter();
	}

	@GetMapping(value = ConstantPage.REST_API_GET_ALL_CHAPTER2, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public List<Object> getListChapter2() {
		return chapterService.getListChapter2();
	}

	@GetMapping(value = ConstantPage.REST_API_GET_ALL_CHAPTER_PARENT, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public List<Chapter> listChapter() {
		return chapterService.listChapter();
	}

	@DeleteMapping(value = ConstantPage.REST_API_DELETE_CHAPTER_BY_ID, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public boolean delete(@PathVariable int id) {
		List<Integer> chapters = chapterService.getAllSubId(id);
		if (chapters != null) {
			for (int i = 0; i < chapters.size(); i++) {
				chapterService.deleteChapter(chapters.get(i));
			}
		}
		return chapterService.deleteChapter(id);
	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE_CHAPTER, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Object> update(@RequestParam("chapter") String chapter) {
		JSONObject jsonObject = new JSONObject(chapter);
		int id = jsonObject.getInt("id");
		String name = jsonObject.getString("name");
		int parent_id = jsonObject.getInt("parent_name");
		int subject_id = jsonObject.getInt("subject_name");

		int exam_id = jsonObject.getInt("exam");
		Subject subject = subjectService.findSubjectById(subject_id);
		Exam exam = new Exam();
		if (exam_id != 0) {
			exam = examService.getExamById(exam_id);
		} else {
			exam = null;
		}

		List<Chapter> chapters = chapterService.getListChapterByName(name);
		if (chapters != null) {
			for (int i = 0; i < chapters.size(); i++) {
				if (chapters.get(i).getSubject().equals(subject) && chapters.get(i).getId() != id) {
					return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
				}
			}
		}
		Chapter chapterUpdate = chapterService.findChapterById(id);
		chapterUpdate.setSubject(subject);
		chapterUpdate.setName(name);
		chapterUpdate.setParent_id(parent_id);
		chapterUpdate.setCreated_at(chapterUpdate.getCreated_at());
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		chapterUpdate.setUpdated_at(sqlDate);
		boolean isSuccess = chapterService.editChapter(chapterUpdate);
		return new ResponseEntity<Object>(isSuccess, HttpStatus.OK);
	}

	@PostMapping(value = ConstantPage.REST_API_INSERT_CHAPTER, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Object> insert(@RequestParam("chapter") String chapter) {
		JSONObject jsonObject = new JSONObject(chapter);
		String name = jsonObject.getString("name");
		int parent_id = jsonObject.getInt("parent_name");
		int subject_id = jsonObject.getInt("subject_name");
		int exam_id = jsonObject.getInt("exam_name");
		Subject subject = subjectService.findSubjectById(subject_id);

		Exam exam = new Exam();
		if (exam_id != 0) {
			exam = examService.getExamById(exam_id);
		}
		List<Chapter> chapters = chapterService.getListChapterByName(name);
		if (chapters != null) {
			for (int i = 0; i < chapters.size(); i++) {
				if (chapters.get(i).getSubject().equals(subject)) {
					return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
				}
			}
		}

		Chapter chapterInsert = new Chapter();
		chapterInsert.setName(jsonObject.getString("name"));
		chapterInsert.setContentType(jsonObject.getString("contentType"));
		chapterInsert.setAssignment(jsonObject.getString("assignment"));
		chapterInsert.setChapterOrder(jsonObject.getInt("chapterOrder"));
		chapterInsert.setStandard(jsonObject.getInt("standard"));
		chapterInsert.setSubject(subject);
		chapterInsert.setExam(exam);
		chapterInsert.setParent_id(parent_id);
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		chapterInsert.setCreated_at(sqlDate);
		boolean isSuccsess = chapterService.addChapter(chapterInsert);
		return new ResponseEntity<Object>(isSuccsess, HttpStatus.OK);
	}

	@GetMapping(value = ConstantPage.REST_API_SEARCH_CHAPTER, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public List<Object> searchChapterbyName(@RequestParam String key) {
		List<Object> chapters = chapterService.searchChapterByName(key);
		return chapters;
	}

	@GetMapping(value = ConstantPage.REST_API_SORT_CHAPTER, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public List<Chapter> sortChapterByName(@RequestParam String name) {
		return chapterService.sortChapterByName(name);
	}

	@GetMapping(value = ConstantPage.REST_API_GETCHAPTERBYSUBJECT, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Chapter> getChapterBySubject(@PathVariable("idSubject") int idSubject) {
		return chapterService.getListChapterBySubject(idSubject);
	}

	@GetMapping(value = ConstantPage.REST_API_GETCHAPTERBYSUBJECT_AND_PARENT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public List<Chapter> getChapterBySubjectAndParent(@PathVariable("idSubject") int idSubject) {
		return chapterService.getListChapterBySubjectAndParent(idSubject);
	}

	// trung
	@GetMapping(value = ConstantPage.REST_API_GET_CHAPTER_DETAIL, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Chapter getChapterById(@PathVariable int id) {
		return chapterService.getChapterById(id);

	}

	@DeleteMapping(value = ConstantPage.REST_API_DELETE_CHAPTER_BY_PARENTID, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })

	public boolean deleteLessonByChapterId(@PathVariable int parentId) {

		return chapterService.deleteChapter(parentId);
	}

	@PostMapping(value = ConstantPage.REST_API_CREATE_CHAPTER, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Object> create(@RequestParam("chapter") String chapter) {
		JSONObject jsonObject = new JSONObject(chapter);
		String name = jsonObject.getString("name");
		int subject_id = jsonObject.getInt("subject_name");
		Subject subject = subjectService.findSubjectById(subject_id);

		List<Chapter> chapters = chapterService.getListChapterByName(name);
		if (chapters != null) {
			for (int i = 0; i < chapters.size(); i++) {
				if (chapters.get(i).getSubject().equals(subject)) {
					return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
				}
			}
		}

		Chapter createChapter = new Chapter();
		createChapter.setName(jsonObject.getString("name"));
		createChapter.setSubject(subject);
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		createChapter.setCreated_at(sqlDate);
		boolean isSuccsess = chapterService.addChapter(createChapter);
		return new ResponseEntity<Object>(isSuccsess, HttpStatus.OK);
	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE2_CHAPTER, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Object> update2(@RequestParam("chapter") String chapter) {
		JSONObject jsonObject = new JSONObject(chapter);
		int id = jsonObject.getInt("id");
		String name = jsonObject.getString("name");

		List<Chapter> chapters = chapterService.getListChapterByName(name);
		if (chapters != null) {
			for (int i = 0; i < chapters.size(); i++) {
				if (chapters.get(i).getName().equals(name) && chapters.get(i).getId() != id) {
					return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
				}
			}
		}
		Chapter chapterUpdate2 = chapterService.findChapterById(id);
		chapterUpdate2.setName(name);
		chapterUpdate2.setCreated_at(chapterUpdate2.getCreated_at());
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		chapterUpdate2.setUpdated_at(sqlDate);
		boolean isSuccess = chapterService.editChapter(chapterUpdate2);
		return new ResponseEntity<Object>(isSuccess, HttpStatus.OK);
	}

	@GetMapping(value = ConstantPage.REST_API_GETCHAPTER_BY_PARENT, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })

	public List<Chapter> findByParentId(@PathVariable int parentId) {
		return chapterService.findByParent_Id(parentId);
	}

	@PostMapping(value = ConstantPage.REST_API_CREATE_BY_CHAPTER, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Object> create2(@RequestParam("chapter") String chapter) {
		JSONObject jsonObject = new JSONObject(chapter);
		String name = jsonObject.getString("name");
		int parent_id = jsonObject.getInt("parent_name");
		int subject_id = jsonObject.getInt("subject_name");
		int exam_id = jsonObject.getInt("exam_name");
		Subject subject = subjectService.findSubjectById(subject_id);
		Exam exam = new Exam();
		try {
			if (exam_id != 0 && examService.getExamById(exam_id) != null) {
				exam = examService.getExamById(exam_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Chapter> chapter_exist = chapterService.getListChapterByName(name);
		if (chapter_exist != null) {
			for (int i = 0; i < chapter_exist.size(); i++) {
				if (chapter_exist.get(i).getName().equals(name)) {
					return ResponseEntity.ok("Chapter đã bị chùng vui lòng nhập lại");
				}
			}
		}
		Chapter chapterInsert = new Chapter();
		chapterInsert.setName(jsonObject.getString("name"));
		chapterInsert.setContentType(jsonObject.getString("contentType"));
		chapterInsert.setAssignment(jsonObject.getString("assignment"));
		chapterInsert.setSubject(subject);
		chapterInsert.setExam(exam);
		chapterInsert.setParent_id(parent_id);
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		chapterInsert.setCreated_at(sqlDate);
		boolean isSuccsess = chapterService.addChapter(chapterInsert);
		return new ResponseEntity<Object>(isSuccsess, HttpStatus.OK);
	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE3_CHAPTER, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Object> update3(@RequestParam("chapter") String chapter) {
		JSONObject jsonObject = new JSONObject(chapter);
		int id = jsonObject.getInt("id");
		String name = jsonObject.getString("name");
		String assignment = jsonObject.getString("assignment");
		String contentType = jsonObject.getString("contentType");
		int parent_id = jsonObject.getInt("parent_name");
		int subject_id = jsonObject.getInt("subject_name");
		int exam_id = jsonObject.getInt("exam_name");
		Subject subject = subjectService.findSubjectById(subject_id);
		Exam exam = new Exam();
		if (exam_id != 0) {
			exam = examService.getExamById(exam_id);
		}

		List<Chapter> chapters = chapterService.getListChapterByName(name);
		if (chapters != null) {
			for (int i = 0; i < chapters.size(); i++) {
				if (chapters.get(i).getName().equals(name) && chapters.get(i).getId() != id) {
					return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
				}
			}
		}
		Chapter chapterUpdate3 = chapterService.findChapterById(id);
		chapterUpdate3.setSubject(subject);
		chapterUpdate3.setExam(exam);
		chapterUpdate3.setName(name);
		chapterUpdate3.setParent_id(parent_id);
		chapterUpdate3.setAssignment(assignment);
		chapterUpdate3.setContentType(contentType);
		chapterUpdate3.setCreated_at(chapterUpdate3.getCreated_at());
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		chapterUpdate3.setUpdated_at(sqlDate);
		boolean isSuccess = chapterService.editChapter(chapterUpdate3);
		return new ResponseEntity<Object>(isSuccess, HttpStatus.OK);
	}

	// Linh
	@GetMapping(value = ConstantPage.REST_API_CHAPTER_BY_SUBJECT_ORDER_ASC, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public List<SubjectResult> getChapterBySubjectAndChapterOrderAsc(@PathVariable("subjectId") int subjectId) {
		System.out.println(chapterService.getChapterBySubjectAndChapterOrderAsc(subjectId).size() + " -------------");
		return chapterService.getChapterBySubjectAndChapterOrderAsc(subjectId);
	}

	// thuy
	@GetMapping(value = ConstantPage.REST_API_GET_EXAM_RESULT_BY_LESSON, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public List<Object> getExamResultByLesson(@PathVariable("chapter_id") int chapter_id) {
		return traineeService.getListExamResult(chapter_id);
	}

	@GetMapping(value = ConstantPage.REST_API_GET_ALL_CHAPTER_BY_PARENT_ID)
	public List<Chapter> getListChapterByParent() {
		return chapterService.getAllChapter();
	}

	@PostMapping(value = ConstantPage.REST_API_COPY_CHAPTER_AND_UNIT)
	public ResponseEntity<String> copyChapter(@PathVariable("chapterId") int chapterId,
			@PathVariable("unitId") int unitId) {
		if (chapterId != -1) {
			Optional<Chapter> chapter = chapterService.findChapterId(chapterId);
			if (!chapter.isPresent()) {
				return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
			} else {
				if (chapter.get().getParent_id() == 0) {
					Chapter ch = chapterService.copyChapter(chapter.get().getId());
					chapterService.copyListLesson(chapter.get().getSubject().getId(), chapter.get().getId(),
							ch.getId());
					return new ResponseEntity<String>("Copy Chapter Success", HttpStatus.OK);
				} else {
					Chapter ch = chapterService.copyChapter(chapter.get().getId());
					unitService.saveAll(unitService.copyListUnit(chapter.get().getId(), ch.getId()));
					return new ResponseEntity<String>("Copy Chapter Child Success", HttpStatus.OK);
				}
			}
		} else {
			if (unitId != -1) {
				Optional<Unit> unit = unitService.findById(unitId);
				if (unit.isPresent()) {
					unitService.copyUnit(unitId);
					return new ResponseEntity<String>("Copy Unit Success", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
			}

		}

	}

	@GetMapping(value = "/update-chapter/status/{id}")
	public ResponseEntity<String> updateStatus(@PathVariable("id") Integer chapterId) {
		chapterService.deleteChapter(chapterId);
		return new ResponseEntity<String>("Chuyển đổi trạng thái thành công", HttpStatus.OK);
	}

	@GetMapping(value = "/list-chapter/{id}")
	public List<ChapterDto> fillAllBySubjectId(@PathVariable("id") Integer subjectId) {
		return chapterService.findBySubject_Id(subjectId);
	}

	@GetMapping(value = "/list-chapter/parent")
	public List<ChapterDto> fillAllByParentId() {
		return chapterService.findByParentId();
	}

	@PostMapping(value = "/add-chapter")
	public ResponseEntity<String> addChapter(@RequestBody Chapter chapter) {
		chapterService.insertChapter(chapter);
		return new ResponseEntity<String>(" Thêm mới thành công", HttpStatus.OK);
	}

	@GetMapping(value = "/get-chapter/{parentId}/subject/{subjectId}")
	public List<ChapterDto> getChapterByParentIdAndSubjectId(@PathVariable("parentId") Integer parentId,
			@PathVariable("subjectId") Integer subjectId) {
		return chapterService.getChapterByIdAndSubjectId(parentId, subjectId);
	}

	@GetMapping(value = "/detail-chapter/{id}")
	public ChapterDto getChapterByIdAndSubjectId(@PathVariable("id") Integer id) {
		return chapterService.findById(id);
	}

	@PutMapping(value = "/update-chapter/{id}")
	public ResponseEntity<String> addChapter(@PathVariable("id") Integer id, @RequestBody Chapter chapter) {
		chapterService.updateChapter(chapter);
		return new ResponseEntity<String>(" Sửa thành công", HttpStatus.OK);
	}

	@PostMapping(value = "/check-chapter-name")
	public String checkNameInsert(@RequestBody String name) {
		if (chapterService.checkNameChapterInsertDonotExit(name)) {
			return "";
		} else {
			return "Tên đã tồn tại vui lòng chọn tên khác";
		}
	}

	@PostMapping(value = "/check-chapter-name/{id}")
	public String checkNameUpdate(@PathVariable("id") Integer id, @RequestBody String name) {
		if (chapterService.checkNameChapterUpdateDonotExit(id, name)) {
			return "";
		} else {
			return "Tên đã tồn tại vui lòng chọn tên khác";
		}
	}
}
