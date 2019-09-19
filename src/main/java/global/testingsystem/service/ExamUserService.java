/**
 * 
 */
package global.testingsystem.service;

import java.util.List;

import global.testingsystem.DTO.ExamUserDto;
import global.testingsystem.entity.Exam;
import global.testingsystem.entity.Exam_User;
import global.testingsystem.entity.Users;

/**
 * @author USER
 *
 */
public interface ExamUserService {
	void saveExamUser(Exam exam, Users user);

	List<Users> getListById(int id);

	void deleteExamUser(int examId, int userId);

	Exam_User saveExamUser(Exam_User eu);

	Exam_User updateExam(Exam_User eu);

	List<Exam_User> findByUserAndExam(int userId, int examId);

	Exam_User findByCodeAndExam(String code, int examId);

	Exam_User findByExamUserId(int examUserId);

	void saveExamUserCode(Exam exam, Users user, String code);

	// minh anh
	void addExamUser(Exam_User ta);

	void addExamDemo(int exam_id, int user_id, String full_name, String email, String mobile, String school,
			Boolean pass);

	List<ExamUserDto> fillAllByExamId(Integer completed, Integer examId);

	List<ExamUserDto> fillAllByExamId(Integer examId);

}
