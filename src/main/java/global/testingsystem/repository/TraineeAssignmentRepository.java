package global.testingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import global.testingsystem.entity.ClassLesson;
import global.testingsystem.entity.TraineeAssignment;

public interface TraineeAssignmentRepository extends JpaRepository<TraineeAssignment, Integer>{
	// ngo minh anh
	@Query(value = "SELECT ta.id, ta.user_id, u.fullname, ta.result, ta.result_detail, ta.time_eval, ta.time_submit,\r\n" + 
			"            ct.assignment, c.name as tenlop, ta.evaluator, ta.file\r\n" + 
			"            from testing_online.trainee_assignment as ta\r\n" + 
			"            inner join testing_online.class_lesson as cl on ta.class_lesson_id = cl.class_lesson_id\r\n" + 
			"            inner join testing_online.chapters as ct on cl.chapter_id = ct.id \r\n" + 
			"            inner join testing_online.users as u on u.id = ta.user_id\r\n" + 
			"            inner join testing_online.class as c on c.class_id = cl.class_id\r\n" + 
			"			where c.class_id = :classId and  ct.assignment like :keySearch ;", nativeQuery = true)
	List<Object> listSearch(@Param("classId") int classId, @Param("keySearch") String keySearch);
	@Query(value = " SELECT ta.id, ta.user_id, u.fullname, ta.result, ta.result_detail, ta.time_eval, ta.time_submit,\r\n" + 
			"            ct.assignment, c.name as tenlop, ta.evaluator, ta.file\r\n" + 
			"            from trainee_assignment as ta\r\n" + 
			"            inner join class_lesson as cl on ta.class_lesson_id = cl.class_lesson_id\r\n" + 
			"            inner join chapters as ct on cl.chapter_id = ct.id \r\n" + 
			"            inner join users as u on u.id = ta.user_id\r\n" + 
			"            inner join class as c on c.class_id = cl.class_id\r\n" + 
			"			where c.class_id = :classId", nativeQuery = true)
		List<Object> getTraineeAssimentByClassId(@Param("classId") int classId);
	
	@Query(value = " SELECT ta.id, ta.user_id, u.fullname, ta.result, ta.result_detail, ta.time_eval, ta.time_submit,\r\n" + 
			"            ct.assignment, c.name as tenlop, ta.evaluator, ta.file\r\n" + 
			"            from trainee_assignment as ta\r\n" + 
			"            inner join class_lesson as cl on ta.class_lesson_id = cl.class_lesson_id\r\n" + 
			"            inner join chapters as ct on cl.chapter_id = ct.id \r\n" + 
			"            inner join users as u on u.id = ta.user_id\r\n" + 
			"            inner join class as c on c.class_id = cl.class_id\r\n" + 
			"			where c.class_id = :classId and ta.id = :traineeassignmentId", nativeQuery = true)
		List<Object> getTraineeAssimentByClassIdAndTraineeAssignmentId(@Param("classId") int classId, @Param("traineeassignmentId")  int traineeassignmentId);
	//thuy
	@Query(value = "select exam_result.user_id,exam_result.exam_id,exam_result.total_score,exam_result.time,exams.name,exams.time as thoiluong,users.fullname,exam_result.by_chapter from exam_result \r\n" + 
			"			inner join users on exam_result.user_id=users.id\r\n" + 
			"            inner join exams on exam_result.exam_id=exams.id\r\n" + 
			"			where exam_result.by_chapter=:chapter_id and exam_result.by_chapter!=0 group by users.id", nativeQuery = true)
	List<Object> getListExamResult(@Param("chapter_id")int chapter_id);
	
}
