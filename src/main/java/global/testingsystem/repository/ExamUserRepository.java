/**
 * 
 */
package global.testingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import global.testingsystem.entity.Exam_Result;
import global.testingsystem.entity.Exam_User;

/**
 * @author USER
 *
 */
@Repository
public interface ExamUserRepository extends JpaRepository<Exam_User, Integer> {
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from exam_user where (exam_id=:exam_id and user_id=:user_id)", nativeQuery = true)
	void deleteExamUser(@Param("exam_id") int exam_id, @Param("user_id") int user_id);

	/**
	 * @param exam_id
	 * @param user_id
	 * @return
	 */
	@Query(value = "select * from exam_user where (exam_id=:exam_id and user_id=:user_id)", nativeQuery = true)
	List<Exam_User> findByUserAndExam(@Param("exam_id") int exam_id, @Param("user_id") int user_id);

	/**
	 * @param exam_id
	 * @param user_id
	 * @return
	 */
	@Query(value = "select * from exam_user where (exam_id=:exam_id and exam_code=:code)", nativeQuery = true)
	Exam_User findByCodeAndExam(@Param("exam_id") int exam_id, @Param("code") String code);

	// minh anh
	@Query(value = "INSERT INTO testing_online.exam_user (exam_id,user_id,full_name,email,mobile,school,pass)\r\n"
			+ "VALUES (:exam_id,:user_id,:full_name,:email,:mobile,:school,:pass)", nativeQuery = true)
	void addExamDemo(@Param("exam_id") int exam_id, @Param("user_id") int user_id, @Param("full_name") String full_name,
			@Param("email") String email, @Param("mobile") String mobile, @Param("school") String school,
			@Param("pass") Boolean pass);

	List<Exam_User> findByCompletedAndExam_Id(Integer completed, Integer examId);

	@Query(value = "select e from Exam_User e join e.exam ex where e.completed=0 AND ex.id=?1")
	List<Exam_User> getExamUserFinish(Integer id);

	@Query(value = "select count(e.id) from Exam_User e join e.exam ex where e.completed=1 AND ex.id=?1")
	Integer countExamUserFinish(Integer id);

	@Query(value = "select count(e.id) from Exam_User e join e.exam ex where e.completed=0 AND ex.id=?1")
	Integer countExamUserUnfinished(Integer id);

}
