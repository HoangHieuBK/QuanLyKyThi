
package global.testingsystem.controller;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import global.testingsystem.entity.CustomError;
import global.testingsystem.entity.Exam;
import global.testingsystem.entity.Exam_Answer;
import global.testingsystem.entity.Exam_Result;
import global.testingsystem.entity.Exam_User;
import global.testingsystem.entity.Users;
import global.testingsystem.service.ExamResultService;
import global.testingsystem.service.ExamService;
import global.testingsystem.service.ExamUserService;
import global.testingsystem.service.UsersService;
import global.testingsystem.util.ConstantPage;

@CrossOrigin(origins = "*")
@RestController
public class ExamResultController {
	private ExamResultService examResultService;
	private ExamUserService examUserService;
	private ExamService examService;
	private UsersService usersService;

	@Autowired
	public ExamResultController(ExamResultService examResultService, ExamService examService, UsersService usersService,
			ExamUserService examUserService) {
		this.examResultService = examResultService;
		this.examService = examService;
		this.usersService = usersService;
		this.examUserService = examUserService;
	}
	

	@PostMapping(value = ConstantPage.REST_API_START_EXAM, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public Integer insert(@RequestParam("examUser") String examUser){
		JSONObject jsonObject = new JSONObject(examUser);
		String email = jsonObject.getString("email");
		int exam_id = jsonObject.getInt("exam_id");
		String code = jsonObject.getString("code");
		Users user = usersService.findByEmail(email);
		Exam exam = examService.findById(exam_id);
		int mode = exam.getExamMode();
		int maxAttemp = exam.getMax_attempt();
		Date date = new Date();
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		Exam_User exam_User =null;
		if(mode == 1) {
			exam_User=examUserService.findByCodeAndExam(code, exam_id);
			if(exam_User==null) throw new CustomError.GeneralError("Mã code không tồn tại !");
			if(exam_User.getCompleted()==1) throw new CustomError.GeneralError("Bài thi đã hoàn thành !");
			else {
			    exam_User.setStart_date(sqlDate);
				exam_User=examUserService.saveExamUser(exam_User);
			}
		}
		else {
				List<Exam_User> listExamUser = examUserService.findByUserAndExam(user.getId(), exam_id);
				int size = listExamUser.size();
				if(size<maxAttemp) {
					if(size==1)exam_User = listExamUser.get(0);
					if(exam_User.getCompleted()==0) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
					     exam_User.setStart_date(sqlDate);
						 examUserService.saveExamUser(exam_User);
					      }else {
					    	 exam_User = new Exam_User();
					    	 exam_User.setCompleted(0);
					    	 exam_User.setStart_date(sqlDate);
					    	 exam_User.setExam(exam);
					    	 exam_User.setUser(user);
					    	 exam_User = examUserService.saveExamUser(exam_User);
					      }
				}
				else {
					 throw new CustomError.GeneralError("Bạn đã thi quá số lần thi !");
				}
				
					     
		}
		return exam_User.getId();
	
	}

	@GetMapping(value = "/examUser/getExamResultByUserExam")
	public Exam_User getExamResultByUserExam(@RequestParam int idExamUser, @RequestParam int examId) {
		return examUserService.findByExamUserId(idExamUser);
	}

	@GetMapping(value = "/examUser/getListExamResultByUserIDExamID")
	public List<Exam_User> getListExamResultByUserIDExamID(@RequestParam int userId, @RequestParam int examId) {
		List<Exam_User> temp = examResultService.getListExamResultByUserIDExamID(userId, examId);
		return temp;
	}

	@GetMapping(value = "/examUser/getListPracticeResultByUserIDPracticeID")
	public List<Exam_User> getListPracticeResultByUserIDPracticeID(@RequestParam int userId,
			@RequestParam int practiceId) {
		List<Exam_User> temp = examResultService.getListPracticeResultByUserIDPracticeID(userId, practiceId);
		return temp;
	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE_COMPLETE_RESULT)
	public boolean updateComplete(@RequestParam("data") String data) {
		JSONObject jsonObject = new JSONObject(data);
		int examUserId = jsonObject.getInt("examUserId");
		Exam_User eu = examUserService.findByExamUserId(examUserId);
		if (eu.getTotal_score() * 100 >= eu.getExam().getPercent_passing())
			eu.setPass(true);
		else
			eu.setPass(false);
		eu.setCompleted(1);
		examUserService.saveExamUser(eu);
		return true;

	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE_TIME_RESULT)
	public boolean updateTime(@RequestParam("data") String data) {
		JSONObject jsonObject = new JSONObject(data);
		int examUsertID = jsonObject.getInt("examUsertID");
		String time = jsonObject.getString("time");
		int completed = jsonObject.getInt("complete");
		Exam_User eu = examUserService.findByExamUserId(examUsertID);
//		if(er.getTotal_score()*100>=er.getExam().getPercent_passing())er.setPass(true);
//		else er.setPass(false);
		eu.setTime(time);
		eu.setCompleted(completed);
		examUserService.saveExamUser(eu);
		return true;

	}
}
