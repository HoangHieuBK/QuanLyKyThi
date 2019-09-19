package global.testingsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.testingsystem.entity.Exam_Result;
import global.testingsystem.entity.Exam_User;
import global.testingsystem.repository.ExamResultRepository;
import global.testingsystem.service.ExamResultService;

@Service
public class ExamResultServiceImpl implements ExamResultService {
	@Autowired
	private ExamResultRepository examResultRepo;

	@Override
	public List<Exam_User> listExam_User(int exam_id) {
		// TODO Auto-generated method stub
		return examResultRepo.listExamResult(exam_id);
	}

	@Override
	public boolean insert(Exam_User exam_User) {

		examResultRepo.save(exam_User);
		return true;
		// TODO Auto-generated method stub
	}

	@Override
	public Exam_User findById(int id) {

		return examResultRepo.findById(id).get();
	}

	@Override
	public Exam_User findFirstByOrderByIdDesc() {

		return examResultRepo.findFirstByOrderByIdDesc();
	}

	@Override
	public Exam_User getUserResultByUserExam(int userId, int examId) {
		// TODO Auto-generated method stub
		return examResultRepo.getExamReSultByUserExam(examId, userId);

	}
	@Override
	public void update(Exam_User exam_User) {
		examResultRepo.save(exam_User);
		
	}
	@Override
	public List<Exam_User> getListExamResultByUserIDExamID(int user_id, int exam_id) {
		return examResultRepo.getListExamResultByUserIDExamID(user_id, exam_id);
	}

	@Override
	public List<Exam_User> getListPracticeResultByUserIDPracticeID(int userID, int practiceID) {
			return examResultRepo.getListPracticeResultByUserIDPracticeID(userID, practiceID);
	}
}
