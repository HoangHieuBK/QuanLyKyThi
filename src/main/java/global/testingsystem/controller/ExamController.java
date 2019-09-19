package global.testingsystem.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import global.testingsystem.DTO.ExamDto;
import global.testingsystem.DTO.ExamUserDto;
import global.testingsystem.entity.Chapter_Exam;
import global.testingsystem.entity.CustomError;
import global.testingsystem.entity.Domain_Exam;
import global.testingsystem.entity.Exam;
import global.testingsystem.entity.Exam_Question;
import global.testingsystem.entity.Exam_Result;
import global.testingsystem.entity.Exam_Setting;
import global.testingsystem.entity.Exam_User;
import global.testingsystem.entity.Group;
import global.testingsystem.entity.Question;
import global.testingsystem.entity.Subject;
import global.testingsystem.entity.Users;
import global.testingsystem.jsonview.ExamView;
import global.testingsystem.repository.ExamUserRepository;
import global.testingsystem.service.ChapterService;
import global.testingsystem.service.DomainService;
import global.testingsystem.service.ExamChapterService;
import global.testingsystem.service.ExamDomainService;
import global.testingsystem.service.ExamQuestionService;
import global.testingsystem.service.ExamResultService;
import global.testingsystem.service.ExamService;
import global.testingsystem.service.ExamSettingService;
import global.testingsystem.service.ExamUserService;
import global.testingsystem.service.GroupService;
import global.testingsystem.service.QuestionService;
import global.testingsystem.service.UploadFileService;
import global.testingsystem.service.UsersService;
import global.testingsystem.service.impl.ExamGroupServiceImpl;
import global.testingsystem.service.impl.ExamServiceImpl;
import global.testingsystem.service.impl.ExamUserServiceImpl;
import global.testingsystem.service.impl.GroupServiceImpl;
import global.testingsystem.service.impl.SubjectServiceImpl;
import global.testingsystem.service.impl.UsersServiceImpl;
import global.testingsystem.util.ConstantPage;
import global.testingsystem.util.ExamSettingDto;

@CrossOrigin(origins = "*")
@RestController
public class ExamController {
	private ExamService examService;
	private SubjectServiceImpl subjectService;
	private ChapterService chapterServcie;
	private DomainService domainService;
	private UsersService usersService;
	private UploadFileService uploadService;
	private GroupService groupService;
	private ExamQuestionService examQuestionService;
	private QuestionService questionService;
	ExamChapterService examChapterService;
	ExamDomainService examDomainService;
	private ExamResultService examResultService;
	@Autowired
	private ExamUserServiceImpl examUserService;
	@Autowired
	private ExamUserService examUserSer;
	@Autowired
	private ExamGroupServiceImpl examGroupService;
	@Autowired
	private ExamSettingService examSettingService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ExamUserRepository repository;
	@Autowired
	private ExamUserService userExamService;

	@Autowired
	public ExamController(ExamServiceImpl examService, SubjectServiceImpl subjectService, UsersServiceImpl usersService,
			UploadFileService uploadService, GroupServiceImpl groupService, ExamQuestionService examQuestionService,
			QuestionService questionService, ExamDomainService examDomainService, ExamResultService examResultService,
			ExamChapterService examChapterService) {
		this.examService = examService;
		this.subjectService = subjectService;
		this.usersService = usersService;
		this.uploadService = uploadService;
		this.groupService = groupService;
		this.examQuestionService = examQuestionService;
		this.questionService = questionService;
		this.examChapterService = examChapterService;
		this.examDomainService = examDomainService;
		this.examResultService = examResultService;
	}

	@PostMapping(value = "/uploadFileExcel")
	public String readExcel(@RequestParam("file") MultipartFile file) {
		String pathToSave = uploadService.saveFileVer(file, ConstantPage.PATH_SAVE_EXAM_UPLOAD);
		examService.readExcel(servletContext.getRealPath(ConstantPage.PATH_SAVE_EXAM_UPLOAD) + "/" + pathToSave);
		return pathToSave;
	}

	// MR DUC
	@GetMapping(value = ConstantPage.REST_API_GET_PRACTICE_HOMEPAGE)
	public List<Object> listPracticeHomepage() {
		List<Object> list = examService.listPracticeHomepage();
		return list;
	}

	// MR DUC GET EXAM BY ID
	@GetMapping(value = ConstantPage.REST_API_GET_EXAM_BY_IDS, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public Object getExamByIDS(@RequestParam int id) {
		return examService.getExamByIDS(id);
	}

	@GetMapping(value = "/exam/getExamById/{id}")
	public Exam getExamById(@PathVariable int id) {
		return examService.getExamById(id);
	}

	@GetMapping(value = ConstantPage.REST_API_GET_ALL_EXAM, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public List<Object> list(@RequestParam("searchKey") String searchKey, @RequestParam("type") String type) {
		List<Object> list = examService.list(searchKey, type);
		return list;
	}

	// trung 14/06

	@GetMapping(value = ConstantPage.REST_API_GET_ALL_LIST_EXAM, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public List<Object> listExam(@RequestParam("searchKey") String searchKey, @RequestParam("type") String type,
			@RequestParam("exam_mode") String exam_mode) {
		List<Object> listExam = examService.listExam(searchKey, type, exam_mode);
		return listExam;
	}

	// trung 14/06
	/*
	 * Linh Gia created by admin: type = 0 created by user: type = 1
	 *
	 */
	@GetMapping(value = ConstantPage.REST_API_GET_PRACTICE, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public List<Object> getListPractice(@RequestParam int user_id) {
		return examService.listPractice(user_id);
	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE_STATUS_EXAM, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public boolean updateStatus(@RequestParam String data) {
		JSONObject jsonObject = new JSONObject(data);
		int idExam = jsonObject.getInt("exam_id");
		int status = jsonObject.getInt("status");
		Exam ex = examService.findById(idExam);
		ex.setStatus(status);
		return examService.update(ex);
	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE_DESCRIPTION_EXAM, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public boolean updateDescription(@RequestParam("description") String data) {
		JSONObject jsonObject = new JSONObject(data);
		int idExam = jsonObject.getInt("examid");
		Exam ex = examService.findById(idExam);
		ex.setTitle(jsonObject.getString("title"));
		ex.setDecription(jsonObject.getString("description"));
		return examService.update(ex);
	}

	@PostMapping(value = ConstantPage.REST_API_INSERT_EXAM, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public Exam insert(@RequestParam("exam") String exam, @RequestParam(required = false) MultipartFile image)

			throws JSONException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject jsonObject = new JSONObject(exam);
		Subject sub = subjectService.findSubjectById(Integer.parseInt(jsonObject.getString("subject")));
		Exam ex = new Exam();
		ex.setName(jsonObject.getString("examName"));
		ex.setSubject(sub);
		ex.setEnd_date(sdf.parse(jsonObject.getString("endTime")));
		ex.setTime(jsonObject.getInt("duration"));
		ex.setExamMode(Integer.parseInt(jsonObject.getString("examType")));
		ex.setCreate_type(jsonObject.getInt("questionsConfig"));
		ex.setQuestion_num(jsonObject.getInt("numQuestions"));
		ex.setStart_date(sdf.parse(jsonObject.getString("startTime")));
		ex.setMax_attempt(jsonObject.getInt("numberTrial"));
		ex.setStatus(jsonObject.getInt("status"));
		ex.setPercent_passing(jsonObject.getInt("passRate"));
		ex.setCode("1");
		
		if (image != null) {
			String urlImg = uploadService.saveFileVer(image, ConstantPage.PATH_SAVE_THUMBNAIL_UPLOAD);
			String[] arrImg = urlImg.split("\\.");
			int lengh = arrImg.length;
			if (!arrImg[lengh - 1].toLowerCase().equals("jpg") && !arrImg[lengh - 1].toLowerCase().equals("jpeg")
					&& !arrImg[lengh - 1].toLowerCase().equals("png")
					&& !arrImg[lengh - 1].toLowerCase().equals("tiff")
					&& !arrImg[lengh - 1].toLowerCase().equals("gif")
					&& !arrImg[lengh - 1].toLowerCase().equals("bmp")) {
				throw new CustomError.GeneralError("Ảnh không đúng định dạng");
			} else {
				ex.setImage(uploadService.saveFileVer(image, ConstantPage.PATH_SAVE_THUMBNAIL_UPLOAD));
			}
		} else
			ex.setImage("");
//		String userEmail = jsonObject.getString("creator");
//		ex.setUsers(usersService.findByEmail(userEmail));
		return examService.insert(ex);
	}

	@PostMapping(value = ConstantPage.REST_API_INSERT_PRACTISE, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Object> insertPractise(@RequestParam("formdata") String practiseExam) {
		JSONObject jsonObject = new JSONObject(practiseExam);
		Exam ex = new Exam();
		ex.setCode(jsonObject.getString("code"));
		ex.setQuestion_num(jsonObject.getInt("numofquestion"));
		ex.setName(jsonObject.getString("nameofpractise").trim());
		String subject = jsonObject.getString("subject");
		List<Question> currentListQuestion = questionService.getListQuestionBySubjectId(Integer.parseInt(subject));
		if (currentListQuestion == null)
			throw new CustomError.GeneralError("Subject không đủ câu hỏi!");
		if (currentListQuestion.size() < jsonObject.getInt("numofquestion")) {
			throw new CustomError.GeneralError("Subject không đủ câu hỏi!");
		} else {
			int userId = jsonObject.getInt("creator_id");
			Users user = usersService.findById(userId);
			ex.setMax_attempt(5);
			ex.setTitle(jsonObject.getString("nameofpractise"));
			JSONArray selectDomain = jsonObject.getJSONArray("detailSelectDomain");
			JSONArray selectChapter = jsonObject.getJSONArray("detailSelectChapter");
			JSONObject notSelectChapter = selectChapter.getJSONObject(0);
			JSONObject notSelectDomain = selectDomain.getJSONObject(0);
			ex.setSubject(subjectService.findSubjectById(Integer.parseInt(subject)));
			ex.setUsers(user);
			ex.setType(1);
			ex.setStatus(1);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = new Date();
			try {
				ex.setStart_date(formatter.parse(formatter.format(startDate)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ex = examService.insertGetId(ex);
			examService.InsertObjectInvite(userId + "", "user", ex.getId());
			if (notSelectChapter.isNull("selectChapter")) {
				for (int i = 0; i < selectDomain.length(); i++) {
					JSONObject selectedDomain = selectDomain.getJSONObject(i);
					int domain = selectedDomain.getInt("selectDomain");
					int number = selectedDomain.getInt("numberQuestion");
					String key = jsonObject.getString("key");	
					Exam_Setting exam_Setting = new Exam_Setting(ex.getId(), 0, domain, number, key);
					examSettingService.saveExamSetting(exam_Setting);
				}
			}else
			if (notSelectDomain.isNull("selectDomain")) {
				for (int i = 0; i < selectChapter.length(); i++) {
					JSONObject selectedChapter = selectChapter.getJSONObject(i);
					int chapter = selectedChapter.getInt("selectChapter");
					int number = selectedChapter.getInt("numberQuestion");
					String key = jsonObject.getString("key");	
					Exam_Setting exam_Setting = new Exam_Setting(ex.getId(), chapter, 0, number, key);
					examSettingService.saveExamSetting(exam_Setting);
				}
			}else {
				int number = jsonObject.getInt("numofquestion");
				String key= jsonObject.getString("key");
				Exam_Setting exam_Setting = new Exam_Setting(ex.getId(), 0, 0, number, key);
				examSettingService.saveExamSetting(exam_Setting);
			}
			
			
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			Exam_User eu = new Exam_User();
			eu.setCompleted(0);
			eu.setStart_date(sqlDate);
			eu.setExam(ex);
			eu.setCreated_at(sqlDate);
			eu.setTime("00:00");
			eu.setUser(user);
			eu.setPass(false);
			eu = examUserService.saveExamUser(eu);
			return new ResponseEntity<Object>(eu, HttpStatus.OK);
		}

	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE_EXAM, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public boolean update(@RequestParam("exam") String exam, @RequestParam(required = false) MultipartFile image) throws JSONException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject jsonObject = new JSONObject(exam);
		Subject sub = subjectService.findSubjectById(jsonObject.getInt("subject"));
		Exam ex = examService.findById(jsonObject.getInt("id"));
		ex.setName(jsonObject.getString("examName"));
		ex.setSubject(sub);
		ex.setEnd_date(sdf.parse(jsonObject.getString("endTime")));
		ex.setTime(jsonObject.getInt("duration"));
		ex.setExamMode(jsonObject.getInt("examType"));
//		ex.setQuestion_num(Integer.parseInt(jsonObject.getString("questionsConfig")));
		ex.setStart_date(sdf.parse(jsonObject.getString("startTime")));
		ex.setMax_attempt(jsonObject.getInt("numberTrial"));
		ex.setStatus(jsonObject.getInt("status"));
		ex.setPercent_passing(jsonObject.getInt("passRate"));
		ex.setCode("1");
		ex.setCreate_type(jsonObject.getInt("questionsConfig"));
		ex.setQuestion_num(jsonObject.getInt("numQuestions"));
		
		if (image != null) {
			String urlImg = uploadService.saveFileVer(image, ConstantPage.PATH_SAVE_THUMBNAIL_UPLOAD);
			String[] arrImg = urlImg.split("\\.");
			int lengh = arrImg.length;
			if (!arrImg[lengh - 1].toLowerCase().equals("jpg") && !arrImg[lengh - 1].toLowerCase().equals("jpeg")
					&& !arrImg[lengh - 1].toLowerCase().equals("png")
					&& !arrImg[lengh - 1].toLowerCase().equals("tiff")
					&& !arrImg[lengh - 1].toLowerCase().equals("gif")
					&& !arrImg[lengh - 1].toLowerCase().equals("bmp")) {
				throw new CustomError.GeneralError("Ảnh không đúng định dạng");
			} else {
				ex.setImage(uploadService.saveFileVer(image, ConstantPage.PATH_SAVE_THUMBNAIL_UPLOAD));
			}
		} else
			ex.setImage("");
		
//		String userEmail = jsonObject.getString("creator");
//		ex.setUsers(usersService.findByEmail(userEmail));
		boolean isSuccess = examService.update(ex);
		return isSuccess;

	}

	@PostMapping(value = ConstantPage.REST_API_INSERT_USER_EXAM, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public void insertUserExam(@RequestParam("id") String examId, @RequestParam("invite") String invite) {
		try {
			JSONObject jsonInvite = new JSONObject(invite);
			int exam = Integer.parseInt(examId);
			Exam ex = examService.findById(exam);

			// boolean isSuccess = examService.update(ex);
			String deleteUserInvite = jsonInvite.getString("userDelete");
			String insertUserInvite = jsonInvite.getString("userInsert");

			examService.deleteObjectInvite(deleteUserInvite, "user", exam);
			examService.InsertObjectInvite(insertUserInvite, "user", exam);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return new ResponseEntity<Object>(isSuccess, HttpStatus.OK);

	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE_FILE_EXAM, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Object> updateFile(@RequestParam("exam") String exam,
			@RequestParam("file") MultipartFile file, @RequestParam("invite") String invite) {
		JSONObject jsonObject = new JSONObject(exam);
		JSONObject jsonInvite = new JSONObject(invite);
		Subject sub = subjectService.findSubjectByName(jsonObject.getString("subject"));
		int questionNum = jsonObject.getInt("question_num");
		List<Question> currentListQuestion = questionService.getListQuestionBySubjectId(sub.getId());
		if (currentListQuestion == null)
			throw new CustomError.GeneralError("Subject không đủ câu hỏi!");
		if (currentListQuestion.size() < questionNum) {
			throw new CustomError.GeneralError("Subject không đủ câu hỏi!");
		} else {
			Exam ex = examService.findById(jsonObject.getInt("id"));
			ex.setTitle(jsonObject.getString("title"));
			ex.setCode(jsonObject.getString("code"));
			ex.setDecription(jsonObject.getString("description"));
			ex.setTime(jsonObject.getInt("time"));
			ex.setName(jsonObject.getString("name"));
			ex.setSubject(sub);
			ex.setQuestion_num(questionNum);
			ex.setStatus(0);
			ex.setMax_attempt(jsonObject.getInt("max_attempt"));
			ex.setPercent_passing(jsonObject.getInt("percent_passing"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				JSONObject date = jsonObject.getJSONObject("date");
				ex.setStart_date(sdf.parse(date.getString("start_date")));
				ex.setEnd_date(sdf.parse(date.getString("end_date")));
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			ex.setUpdated_at(sqlDate);
			ex.setMedia(uploadService.saveFileVer(file, ConstantPage.PATH_SAVE_EXAM_UPLOAD));
			boolean isSuccess = examService.update(ex);
			String deleteUserInvite = jsonInvite.getString("userDelete");
			String deleteGroupInvite = jsonInvite.getString("groupDelete");
			String insertUserInvite = jsonInvite.getString("userInsert");
			String insertGroupInvite = jsonInvite.getString("groupInsert");
			examService.deleteObjectInvite(deleteUserInvite, "user", ex.getId());
			examService.deleteObjectInvite(deleteGroupInvite, "group", ex.getId());
			examService.InsertObjectInvite(insertUserInvite, "user", ex.getId());
			examService.InsertObjectInvite(insertGroupInvite, "group", ex.getId());
			return new ResponseEntity<Object>(isSuccess, HttpStatus.OK);
		}
	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE_EXAM_SERVICE, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public Map<String, String> updateExamService(@RequestParam int examId, @RequestParam List<Integer> listQuestion,
			@RequestParam int creatType, @RequestParam(required = false) String listRandom) {
		Map<String, String> map = new HashMap<>();
		try {
			Exam exam = examService.findById(examId);
			exam.setCreate_type(creatType);
			examService.update(exam);
			if (listRandom != null) {
				examSettingService.deleteExamSetting(examId);
				JSONObject jsonObject = new JSONObject(listRandom);
				JSONArray select = jsonObject.getJSONArray("detailSelect");
				for (int i = 0; i < select.length(); i++) {
					JSONObject object = select.getJSONObject(i);
					int chapter = object.getInt("chapter");
					int domain = object.getInt("domain");
					int number = object.getInt("number");
					Exam_Setting exam_Setting = new Exam_Setting(examId, chapter, domain, number);
					examSettingService.saveExamSetting(exam_Setting);
				}
			}
			List<Integer> temp = new ArrayList<Integer>();
			List<Integer> temp1 = new ArrayList<Integer>();
			Exam_Question exam_question = new Exam_Question();
			exam_question.setExam_id(examId);
			List<Integer> listIdQuestion = questionService.getListQuestionByExamId(examId);
			// các item sẽ update
			for (Integer id : listIdQuestion) {
				if (!listQuestion.contains(id)) {
					temp.add(id);
				}
			}
			// cac item cần update
			for (Integer id : listQuestion) {
				if (!listIdQuestion.contains(id)) {
					temp1.add(id);
				}
			}
			if (temp1.size() == 0 && temp.size() != 0) {
				for (int i = 0; i < temp.size(); i++) {
					examQuestionService.deleteExamQuestion(temp.get(i), examId);
				}
			} else if (temp1.size() != 0 && temp.size() == 0) {
				for (int i = 0; i < temp1.size(); i++) {
					Exam_Question exam_q = new Exam_Question();
					exam_q.setExam_id(examId);
					exam_q.setQuestion_id(temp1.get(i));
					examQuestionService.saveExamQuestion(exam_q);
				}
			} else {
				if (temp.size() < temp1.size()) {
					for (int i = 0; i < temp.size(); i++) {
						examQuestionService.updateExamQuestion(temp.get(i), temp1.get(i), examId);
					}
					for (int i = temp.size(); i < temp1.size(); i++) {
						Exam_Question exam_q = new Exam_Question();
						exam_q.setExam_id(examId);
						exam_q.setQuestion_id(temp1.get(i));
						examQuestionService.saveExamQuestion(exam_q);
						;
					}
				} else {
					for (int i = 0; i < temp1.size(); i++) {
						examQuestionService.updateExamQuestion(temp.get(i), temp1.get(i), examId);
					}
					for (int i = temp1.size(); i < temp.size(); i++) {
						Exam_Question exam_q = new Exam_Question();
						exam_q.setExam_id(examId);
						exam_q.setQuestion_id(temp.get(i));
						examQuestionService.saveExamQuestion(exam_q);
						;
					}
				}
			}
			map.put("response", "success");
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static String differentNumber(String first, String second) {
		if ("".equals(first))
			return "";
		else if ("".equals(second))
			return first;
		else {
			String result = "";
			String[] numbers = first.split("\\s");
			for (String tem : numbers)
				if (second.indexOf(tem) == -1)
					result += tem;
			return result;
		}

	}

	@PostMapping(value = ConstantPage.REST_API_ADD_EXAMRANDOM, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public void addExamRandom(@RequestParam int examId, @RequestParam int idDomain, @RequestParam int idChapter,
			@RequestParam int percentageChapter, @RequestParam int percentageDomain) {
		try {
			Chapter_Exam chapterExam = new Chapter_Exam();
			chapterExam.setChapter_id(idChapter);
			chapterExam.setExam_id(examId);
			chapterExam.setPercentage(percentageChapter);
			examChapterService.saveExamChapter(chapterExam);
			Domain_Exam domain_Exam = new Domain_Exam();
			domain_Exam.setDomain_id(idDomain);
			domain_Exam.setExam_id(examId);
			domain_Exam.setPercentage(percentageDomain);
			examDomainService.saveExamDomain(domain_Exam);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@GetMapping(value = ConstantPage.REST_API_GET_LIST_USER_BY_EXAM_ID)
	@ResponseBody
	public List<Users> listUserExam(@PathVariable int id) {
		return examUserService.getListById(id);
	}

	@GetMapping(value = ConstantPage.REST_API_GET_LIST_GROUP_BY_EXAM_ID)
	public List<Group> listGroupExam(@PathVariable int id) {
		return examGroupService.getListById(id);
	}

	@GetMapping(value = ConstantPage.REST_API_GET_LIST_QUESTION_BY_EXAM_ID)
	public List<Question> listQuestion(@PathVariable int id) {
		// List<Integer> listQuestion = examService.getListQuestion(id);
		List<Question> result = examService.getListQuestionExam(id);
//				for (Integer s : listQuestion) {
//					Question ques = questionService.getQuestionById(s.intValue());
//					if (ques != null)
//						result.add(ques);
//				}

		return result;
	}

	@JsonView(ExamView.Subject.class)
	@GetMapping(value = ConstantPage.REST_API_GET_EXAM_BY_EXAM_ID)
	public Exam getExam(@PathVariable int id) {
		return examService.findById(id);
	}

	public static int[] RandomizeArray(int[] array) {
		Random rgen = new Random(); // Random number generator

		for (int i = 0; i < array.length; i++) {
			int randomPosition = rgen.nextInt(array.length);
			int temp = array[i];
			array[i] = array[randomPosition];
			array[randomPosition] = temp;
		}

		return array;
	}

	@GetMapping(value = ConstantPage.REST_API_GET_LIST_EXAMSETTING)
	public List<Object> getListExamSetting(@PathVariable int idExam) {
		List<Object> response = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		List<Object> list = examSettingService.listExamSetting(idExam);
		for (Object obj : list) {
			ExamSettingDto data = new ExamSettingDto();
			JSONArray temp = new JSONArray(obj);
			for (int i = 0; i < temp.length(); i++) {
				if (i == 0)
					data.setChapter_id(Integer.parseInt(temp.get(i).toString()));
				else if (i == 1)
					data.setChapterName(temp.get(i).toString());
				else if (i == 2)
					data.setDomain_id(Integer.parseInt(temp.get(i).toString()));
				else if (i == 3)
					data.setDomainName(temp.get(i).toString());
				else
					data.setQuestionNum(Integer.parseInt(temp.get(i).toString()));
			}
			try {
				String json = mapper.writeValueAsString(data);
				response.add(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response;
	}

	@GetMapping(value = ConstantPage.REST_API_GET_LIST_EXAMRESULT)
	public List<Exam_User> getListExamResult(@PathVariable int idExam) {
		return examResultService.listExam_User(idExam);
	}

	@PostMapping(value = ConstantPage.REST_API_SEARCH_EXAM)
	public List<Object> search(@RequestParam("data") String data) {
		JSONObject jsonObject = new JSONObject(data);
		String key = jsonObject.getString("key");
		String type = jsonObject.getString("type");
		if ("search".equals(type))
			return examService.search(key);
		else
			return examService.filterByType(key);
	}

	@GetMapping(value = ConstantPage.REST_API_GET_LIST_EXAM_BY_SUBJECT_ID)
	public List<Object> getListExamBySubjectId(@PathVariable int subId) {
		return examService.getExamBySubjectId(subId);
	}

	@GetMapping(value = ConstantPage.REST_API_GET_LIST_QUESTION_EXAM_DETAIL)
	public List<Question> listQuestionExamDetail(@RequestParam("examId") int examId,
			@RequestParam("examUserId") int examUserId) {

		return examService.getListQuestionExamDetail(examId, examUserId);
	}

	@GetMapping(value = ConstantPage.REST_API_GET_ONE_EXAM_BY_SUBJECT)
	public List<Object> ranDomOneExamBySubject(@PathVariable int subId) {
		return examService.ranDomOneExamBySubject(subId);
	}

	@GetMapping(value = ConstantPage.REST_API_GET_NUM_AND_SUBJECT_BY_EXAM_ID)
	public Object getNumAndSubjecByExamId(@PathVariable int id) {
		return examService.getNumAndSubjectByExamId(id);
	}

	@PostMapping(value = ConstantPage.REST_API_INSERT_EXAM_SETTING, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE, })
	public ResponseEntity<Object> editUnit(@RequestParam("data") String data, @RequestParam("examId") int examId,
			@RequestParam("subjectId") int subjectId) {
		JSONObject jsonObject = new JSONObject(data);
		String keyword = jsonObject.getString("keyword");
		JSONArray domain = jsonObject.getJSONArray("detailSelectDomain");
		JSONArray chapter = jsonObject.getJSONArray("detailSelectChapter");
		Exam exam = examService.findById(examId);
		if (!keyword.isEmpty()) {
			Exam_Setting exam_Setting = new Exam_Setting(examId, 0, 0, exam.getQuestion_num(), subjectId, keyword);
			examSettingService.saveExamSetting(exam_Setting);
			return new ResponseEntity<Object>("ok", HttpStatus.OK);
		}
		if (chapter.length() > 0) {
			if (!chapter.getJSONObject(0).getString("selectChapter").isEmpty()) {
				for (int i = 0; i < chapter.length(); i++) {
					JSONObject object = chapter.getJSONObject(i);
					int selectChapter = object.getInt("selectDomain");
					int number = object.getInt("numberQuestion");
					Exam_Setting exam_Setting = new Exam_Setting(examId, selectChapter, 0, number, subjectId, "");
					examSettingService.saveExamSetting(exam_Setting);
				}
				return new ResponseEntity<Object>("ok", HttpStatus.OK);
			}
		}
		if (domain.length() > 0) {
			if (!domain.getJSONObject(0).getString("selectDomain").isEmpty()) {
				for (int i = 0; i < domain.length(); i++) {
					JSONObject object = domain.getJSONObject(i);
					int selectDomain = object.getInt("selectDomain");
					int number = object.getInt("numberQuestion");
					Exam_Setting exam_Setting = new Exam_Setting(examId, 0, selectDomain, number, subjectId, "");
					examSettingService.saveExamSetting(exam_Setting);
				}
				return new ResponseEntity<Object>("ok", HttpStatus.OK);
			}
		}
		return new ResponseEntity<Object>("not_ok", HttpStatus.OK);
	}

	@PostMapping(value = ConstantPage.REST_API_IMPORT_EXAM_USER)
	public int importExamUser(@RequestParam("exam") String exam, @RequestParam("file") MultipartFile file) {
		int id = Integer.parseInt(exam);
		Map<Integer, Object> map = new HashMap<>();
		String pathToSave = uploadService.saveFileVer(file, ConstantPage.PATH_SAVE_EXAM_UPLOAD);
		String[] formatFile = pathToSave.split("\\.");
		int len = formatFile.length - 1;
		if (formatFile[len].equals("xls") || formatFile[len].equals("xlsx")) {
			// List<Question> list = examService.getListQuestionExam(1);
			try {
				int questionNumber = examService.importUserExam(id,
						servletContext.getRealPath(ConstantPage.PATH_SAVE_EXAM_UPLOAD) + "/" + pathToSave);
				return questionNumber;
			} catch (Exception e) {
				throw new CustomError.GeneralError("Trường dữ liệu không đúng theo file mẫu!");
			}

		} else {
			throw new CustomError.GeneralError("File không đúng định dạng. Bạn phải chọn file excel!");
		}
	}

	@PostMapping(value = ConstantPage.REST_API_ADD_USER_IN_EXAM_DEMO, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Object> updateExamDemo(@RequestParam("examDemo") String examDemo) {
		JSONObject jsonObject = new JSONObject(examDemo);
		int exam_id = jsonObject.getInt("exam_id");
		String fullName = jsonObject.getString("fullName");
		String email = jsonObject.getString("email");
		String mobile = jsonObject.getString("mobile");
		String school = jsonObject.getString("school");
		Exam_User examUser = new Exam_User();
		Exam exam = new Exam();
		exam = examService.getExamById(exam_id);
		Users user = usersService.findById(42);
		// user.setId(42);
		examUser.setExam(exam);
		examUser.setUser(user);
		examUser.setFullName(fullName);
		examUser.setEmail(email);
		examUser.setMobile(mobile);
		examUser.setSchool(school);
		examUser.setPass(false);
		try {
			examUser = examUserSer.saveExamUser(examUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(examUser, HttpStatus.OK);
	}

	@GetMapping(value = ConstantPage.REST_API_GET_EXAM_DEMO)
	public List<Exam> getListExamByExamDemo() {
		return examService.getExamByExamDemo();
	}

	@GetMapping(value = ConstantPage.REST_API_GET_ENTRY_TEST)
	public List<Exam> getListExamByEntryTest() {
		return examService.getExamByEntryTest();
	}

	@GetMapping(value = ConstantPage.REST_API_GET_EXAM_USER)
	public List<Exam> getListExamByExamUser() {
		return examService.getExamByExamUser();
	}

	@GetMapping(value = ConstantPage.REST_API_EXAM)
	public List<Exam> getListExam() {
		return examService.getExam();
	}
	//
	@GetMapping(value=ConstantPage.REST_API_GET_EXAM_BY_EXAM_MODE)
	public List<Exam>getExamByExamMode(@PathVariable int exam_mode){
		return examService.GetExamByExamMode(exam_mode);
	}
	@GetMapping(value = ConstantPage.REST_API_GET_LIST_EXAM_USER_BY_EXAM_ID)
	public List<Object> getExamUserByExamId(@PathVariable("examId") int id) {
		return examService.getExamUserByExamId(id);
	}
	@GetMapping(value = ConstantPage.REST_API_DOWNLOAD_FILE_EXCEL_USER_EXAM)
	public ResponseEntity<InputStreamResource> downloadFile(HttpServletRequest request) throws IOException {
		HttpHeaders responseHeader = new HttpHeaders();
		try {
			String cwd = System.getProperty("user.dir");
			String classPath = cwd + "/" + "upload-dir/user.xlsx";
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
			return new ResponseEntity<InputStreamResource>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value = "/detail-exam/exam/{examId}/user/{userId}")
	public ExamDto getExamByExamIdAndUserId(@PathVariable("examId") Integer examId,
			@PathVariable("userId") Integer userId) {
		return examService.findByExamIdAndUserId(examId, userId);
	}


	@GetMapping(value =ConstantPage.REST_API_GET_EXAM_COMPLETED_BY_USER)
	public List<Object> getExamCompletedByUser(@PathVariable("userid") int id){
		return examService.getExamCompletedByUser(id);
	}
	
	@GetMapping(value = "/count-exam-user-finish/{id}")
	public Integer countExamUserFinish(@PathVariable("id") Integer id) {
		return repository.countExamUserFinish(id);
	}

	@GetMapping(value = "/count-exam-user-unfinish/{id}")
	public Integer countExamUserUnfinish(@PathVariable("id") Integer id) {
		return repository.countExamUserUnfinished(id);
	}
	@GetMapping(value = "/list-exam-user/{completed}/exam/{id}")
	public List<ExamUserDto> fillAll(@PathVariable("completed") Integer completed, @PathVariable("id") Integer id) {
		return userExamService.fillAllByExamId(completed, id);
	}
	@GetMapping(value = "/list-exam-user/exam/{id}")
	public List<ExamUserDto> fillAllByID(@PathVariable("id") Integer id) {
		return userExamService.fillAllByExamId(id);
	}
}