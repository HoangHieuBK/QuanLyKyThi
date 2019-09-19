package global.testingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import global.testingsystem.entity.Exam_User;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ExamResultRepository extends JpaRepository<Exam_User, Integer> {
    @Query(value="select *from exam_user a where ( a.exam_id =:examId)",nativeQuery=true)
    public List<Exam_User> listExamResult(@Param("examId") int examId);
    Exam_User findFirstByOrderByIdDesc();
    @Query(value="select *from exam_user a where (a.exam_id=:examId and a.id=:id)",nativeQuery=true)
    Exam_User getExamReSultByUserExam(@Param("examId") int examId,@Param("id") int id);
    // MR DUC
	@Query(value =
			"select * from exam_user where (completed = 1 and user_id=:userID and exam_id=:examID) order by created_at ASC;", nativeQuery = true)
	List<Exam_User> getListExamResultByUserIDExamID(@Param("userID") int userID,@Param("examID") int examID);
	@Query(value =
			"select * from exam_user where (completed = 1 and user_id=:userID and exam_id=:practiceID) order by created_at ASC;", nativeQuery = true)
	List<Exam_User> getListPracticeResultByUserIDPracticeID(@Param("userID") int userID,@Param("practiceID") int practiceID);
} 



