/**
 * 
 */
package global.testingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import global.testingsystem.entity.Users;

/**
 * @author admin
 *
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	List<Users> findByStatus(int status);

	Users findByEmail(@Param("email") String email);

	List<Users> findByEmailContainingOrFullnameContaining(String email, String fullname);

	List<Users> findAllByOrderByEmailAsc();

	List<Users> findAllByOrderByFullnameAsc();

	List<Users> findByEmailContainsOrFullnameContainsAllIgnoreCaseOrderByEmailAsc(String s, String s1);

	List<Users> findByEmailContainsOrFullnameContainsAllIgnoreCaseOrderByFullnameAsc(String s, String s1);

	// COONGGGGGGGGGGGGGGGGGGGGGG

	@Query(value = "select a.fullname,a.email from users as a \r\n"
			+ "			 where  a.fullname LIKE CONCAT('%',:fullname,'%');", nativeQuery = true)
	List<Object> searchUserByName(@Param("fullname") String fullname);

	@Query(value = "select ex.id, ex.name,ex.question_num, ex.end_date, ex.type from exams as ex\r\n"
			+ "inner join exam_user as eu on eu.exam_id = ex.id\r\n"
			+ "inner join users as u on u.id = eu.user_id where (u.id =:Id and ex.type = 0 and ex.status =1 )\r\n"
			+ "and ex.id not in (select exam_id from exam_result as er where er.user_id=:Id) order by ex.end_date ASC;", nativeQuery = true)
	List<Object> getListExamOfUserASCBYEndDate(@Param("Id") int Id);

	@Query(value = "select ex.id, ex.name, ex.title,ex.question_num, ex.time, ex.percent_passing,ex.max_attempt, ex.type, ex.end_date, ex.start_date "
			+ "\t\t\tfrom exams as ex\n" + "\t\t\tinner join exam_user as eu on eu.exam_id = ex.id\n"
			+ "\t\t\tinner join users as u on u.id = eu.user_id\n"
			+ "\t\t\twhere u.id=:Id and ex.type =0 and ex.status=1 and ex.start_date <= now()", nativeQuery = true)
	List<Object> getListExamOfUser(@Param("Id") int Id);

	// MR DUC thêm ngày 5.1.2019 ********* START *********

	@Query(value = "select ex.id, ex.name, ex.title,ex.question_num, ex.time, ex.percent_passing,ex.max_attempt, ex.type, ex.status,ex.start_date,ex.subject_id from exams  as ex inner join exam_user as eu on eu.exam_id = ex.id inner join users as u on u.id = eu.user_id where (u.id=:Id and ex.type =1 and ex.status =1);", nativeQuery = true)
	List<Object> getListPracticeOfUser(@Param("Id") int Id);

	// MR DUC thêm ngày 5.1.2019 ********* END *********

	@Query(value = "select c.name from "
			+ "(role c inner join users_role b on c.id=b.role_id) inner join users a on a.id=b.users_id"
			+ "   where a.email=:userName", nativeQuery = true)
	List<String> getListRoleOfUser(@Param("userName") String userName);

	@Query(value = "select *from users where fullname =:name", nativeQuery = true)
	Users getuserByName(@Param("name") String usersName);

	@Query(value = "select a.email as email,b.name as role_Name " + "from users a,role b " + " where a.id in ("
			+ " select users_Id from users_role" + " where users_id=a.id and role_Id=b.id) " + " and b.id in ( "
			+ "select role_Id from users_role " + " where users_id=a.id and role_Id=b.id)", nativeQuery = true)
	List<Object[]> getAllUserRole();

	@Query(value = "delete from users_role where users_Id=:usersId and role_Id=:roleId)", nativeQuery = true)
	void removeUserRole(@Param("usersId") int usersId, @Param("roleId") int roleId);

	@Query(value = "SELECT * \r\n" + "   FROM users us \r\n" + "   WHERE us.id IN (SELECT eu.user_id \r\n"
			+ "         FROM exam_user eu \r\n" + "         WHERE eu.exam_id = :exam_id) ;\r\n"
			+ "", nativeQuery = true)
	List<Users> findByExamId(@Param("exam_id") int exam_id);

	@Query(value = "select *from users a where (a.id NOT IN (:listUser) and a.id in (:listComplete) )", nativeQuery = true)
	public List<Users> getListUserInCompleted(@Param("listUser") List<Integer> listUser,
			@Param("listComplete") List<Integer> listComplete);

	@Query(value = "select *from exam_result where ( user_id IN (:listUser) and completed=1 and exam_id=:examId)", nativeQuery = true)
	public List<Object> getListUserCompleted(@Param("listUser") List<Integer> listUser, @Param("examId") int examId);

	// MR DUC thêm ngày 5.1.2019 ********* START *********

	@Query(value = "select * from exams where type=0 and id IN ( select b.exam_id from exam_user b where b.completed=1 and b.user_id =:userId) order by created_at ASC;", nativeQuery = true)
	List<Object> getListExamUserCompleted(@Param("userId") int userId);

	@Query(value = "select * from exams where type=1 and id IN ( select b.exam_id from exam_user b where b.completed=1 and b.user_id =:userId) order by created_at ASC;", nativeQuery = true)
	List<Object> getListPracticeUserCompleted(@Param("userId") int userId);

	// MR DUC thêm ngày 5.1.2019 ********* END *********

	// linh thêm ngày 19.04.2019
	@Query(value = "SELECT\r\n" + "	DISTINCT ex.id,\r\n" + "	ex.NAME,\r\n" + "	ex.title,\r\n" + "	ex.STATUS,\r\n"
			+ "	ex.max_attempt,\r\n"
			+ "	( SELECT count( * ) FROM exam_user WHERE exam_user.exam_id = ex.id AND exam_user.user_id =:userId  and exam_user.completed = 2) AS count \r\n"
			+ "FROM\r\n" + "	exams AS ex\r\n" + "	INNER JOIN exam_user AS eu ON eu.exam_id = ex.id\r\n"
			+ "	INNER JOIN users AS u ON u.id = eu.user_id \r\n" + "WHERE\r\n" + "	u.id =:userId \r\n"
			+ "	AND ex.type = 1;", nativeQuery = true)
	List<Object> getPracticeCompletedByUser(@Param("userId") int userId);

	@Query(value = "select exams.id,exams.name,exams.title,exam_user.exam_id,exam_user.id as examresult_id, exams.max_attempt, \r\n"
			+ "exam_user.created_at, exam_user.time, exam_user.completed, exam_user.total_score,exams.status,\r\n"
			+ "(select count(*) from exam_user where exam_user.exam_id =:examid and exam_user.user_id =:userId and exam_user.completed = 2) as count\r\n"
			+ "from exam_user inner join exams on exams.id = exam_user.exam_id\r\n"
			+ "where exam_user.user_id =:userId and exams.type =1  and exam_user.exam_id =:examid and exam_user.completed = 2 order by exam_user.id desc;", nativeQuery = true)
	List<Object> getExamResultPractice(@Param("userId") int userId, @Param("examid") int examid);

	// linh thêm ngày 19.04.2019
}
