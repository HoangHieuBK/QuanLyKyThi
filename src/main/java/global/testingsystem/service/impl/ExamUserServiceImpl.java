/**
 * 
 */
package global.testingsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import global.testingsystem.DTO.ExamUserDto;
import global.testingsystem.entity.Exam;
import global.testingsystem.entity.Exam_User;
import global.testingsystem.entity.Users;
import global.testingsystem.repository.ExamUserRepository;
import global.testingsystem.repository.UsersRepository;
import global.testingsystem.service.ExamUserService;

@Service
public class ExamUserServiceImpl implements ExamUserService {

	@Autowired
	private ExamUserRepository examUserRepo;
	@Autowired
	private UsersRepository userRepository;
	@Override
	public void saveExamUser(Exam exam, Users user) {
		Exam_User eu=new Exam_User();
		eu.setExam(exam);
		eu.setUser(user);
		eu.setPass(false);
		examUserRepo.save(eu);
	}
	@Override
	public void saveExamUserCode(Exam exam, Users user, String code) {
		Exam_User eu = new Exam_User();
		eu.setExam(exam);
		eu.setUser(user);
		eu.setExamCode(code);
		examUserRepo.save(eu);
	}
	@Override
	public List<Users> getListById(int id) {
		return userRepository.findByExamId(id);
	}
	@Override
	@Transactional
	public void deleteExamUser(int examId, int userId) {
		
	examUserRepo.deleteExamUser(examId, userId);
	}
	@Override
	public Exam_User saveExamUser(Exam_User eu) {
		return examUserRepo.saveAndFlush(eu);
		
	}
	@Override
	public Exam_User updateExam(Exam_User eu) {
		return examUserRepo.saveAndFlush(eu);
	}
	@Override
	public List<Exam_User> findByUserAndExam(int userId, int examId) {
		// TODO Auto-generated method stub
		return examUserRepo.findByUserAndExam(examId, userId);
	}
	@Override
	public Exam_User findByCodeAndExam(String code, int examId) {
		// TODO Auto-generated method stub
		return examUserRepo.findByCodeAndExam(examId, code);
	}
	@Override
	public Exam_User findByExamUserId(int examUserId) {
		
		return examUserRepo.findById(examUserId).get();
	}
	// minh anh
	@Override
	public void addExamUser(Exam_User ta) {
		examUserRepo.save(ta);	
	}
	@Override
	public void addExamDemo(int exam_id, int user_id, String full_name, String email, String mobile, String school,
			Boolean pass) {
		 examUserRepo.addExamDemo(exam_id, user_id, full_name, email, mobile, school, pass);	
	}
	@Override
	public List<ExamUserDto> fillAllByExamId(Integer completed, Integer id) {
		List<ExamUserDto> examUserDtos = new ArrayList<ExamUserDto>();
		List<Exam_User> exam_Users = examUserRepo.findByCompletedAndExam_Id(completed, id);
		for (Exam_User exam_User : exam_Users) {
			ExamUserDto examUserDto = new ExamUserDto();
			examUserDto.setId(exam_User.getId());
			examUserDto.setCompleted(exam_User.getCompleted());
			examUserDto.setCorrect_num(exam_User.getCorrect_num());
			examUserDto.setCreated_at(exam_User.getCreated_at());
			examUserDto.setEmail(exam_User.getEmail());
			examUserDto.setFullName(exam_User.getFullName());
			examUserDto.setEnd_date(exam_User.getEnd_date());
			examUserDto.setStart_date(exam_User.getStart_date());
			examUserDto.setSchool(exam_User.getSchool());
			examUserDto.setMobile(exam_User.getMobile());
			examUserDto.setPass(exam_User.isPass());
			examUserDto.setTime(exam_User.getTime());
			examUserDtos.add(examUserDto);
		}
		return examUserDtos;

	}
	@Override
	public List<ExamUserDto> fillAllByExamId(Integer id) {
		List<ExamUserDto> examUserDtos = new ArrayList<ExamUserDto>();
		List<Exam_User> exam_Users = examUserRepo.getExamUserFinish(id);
		for (Exam_User exam_User : exam_Users) {
			ExamUserDto examUserDto = new ExamUserDto();
			examUserDto.setId(exam_User.getId());
			examUserDto.setCompleted(exam_User.getCompleted());
			examUserDto.setCorrect_num(exam_User.getCorrect_num());
			examUserDto.setCreated_at(exam_User.getCreated_at());
			examUserDto.setEmail(exam_User.getEmail());
			examUserDto.setFullName(exam_User.getFullName());
			examUserDto.setEnd_date(exam_User.getEnd_date());
			examUserDto.setStart_date(exam_User.getStart_date());
			examUserDto.setSchool(exam_User.getSchool());
			examUserDto.setMobile(exam_User.getMobile());
			examUserDto.setPass(exam_User.isPass());
			examUserDto.setTime(exam_User.getTime());
			examUserDtos.add(examUserDto);
		}
		return examUserDtos;

	}

}
