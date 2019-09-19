package global.testingsystem.service;

import java.util.List;

import org.springframework.data.repository.query.Param;


import global.testingsystem.entity.Exam_User;
import global.testingsystem.entity.Users;

public interface ExamResultService {
    List<Exam_User> listExam_User(int exam_id);
    boolean insert(Exam_User exam_User);
    Exam_User findById(int id);
    
    Exam_User findFirstByOrderByIdDesc();
    
    Exam_User getUserResultByUserExam(int userId,int examId);
    
    void update(Exam_User exam_User);
    
    // MR DUC
    
    List<Exam_User> getListExamResultByUserIDExamID(int user_id, int exam_id);
    List<Exam_User> getListPracticeResultByUserIDPracticeID(int userID,int practiceID);
}
