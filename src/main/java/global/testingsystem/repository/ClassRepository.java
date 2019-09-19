package global.testingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import global.testingsystem.entity.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

	Class findOneByClassId(Integer ClassId);

	@Query(value = "SELECT * from class inner join product on class.product_id = product.product_id\r\n"
			+ "              inner join registration on registration.product_id = product.product_id\r\n"
			+ "where product.content_type='Khóa học' and registration.user_id = :userid", nativeQuery = true)
	List<Class> getListClass(@Param("userid") int userid);

//	@Query(value = "SELECT * from class inner join product on class.product_id = product.product_id\r\n" 
//           + "			     inner join registration on registration.product_id = product.product_id\r\n" 
//           + "where product.content_type='Khóa học'"
//           + " and registration.status = '1'"
//           + " and registration.user_id = '41'", nativeQuery = true)
//	List<Class> getListClass(@Param("userid") int userid);
	
	@Query(value = "Select * from class where class_id = :id ", nativeQuery = true)
	List<Class> getClassById(@Param("id") int id);
	
//	Trinh van tai
	@Query(value = "SELECT * from class inner join product on class.product_id = product.product_id\r\n"
			+ "where product.content_type='Khóa học' ", nativeQuery = true)
	List<Class> getAllClass();

	@Query(value = " select * from class a  where a.name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
	List<Class> searchClassByName(@Param("name") String name);

	@Query(value = " select * from class a where a.name LIKE CONCAT('%',:name,'%') order by a.class_id asc ", nativeQuery = true)
	List<Class> sortAscClassById(String name);

	@Query(value = " select * from class a where a.name LIKE CONCAT('%',:name,'%')  order by a.class_id desc ", nativeQuery = true)
	List<Class> sortDescClassById(String name);

	@Query(value = " select * from class a where a.name LIKE CONCAT('%',:name,'%')  order by a.name asc ", nativeQuery = true)
	List<Class> sortAscClassByName(String name);

	@Query(value = " select * from class a where a.name LIKE CONCAT('%',:name,'%')  order by a.name desc ", nativeQuery = true)
	List<Class> sortDescClassByName(String name);

	@Query(value = " select * from class a  order by a.start_date asc ", nativeQuery = true)
	List<Class> sortAscClassByStartDate(String name);

	@Query(value = " select * from class a where a.name LIKE CONCAT('%',:name,'%') order by a.start_date desc ", nativeQuery = true)
	List<Class> sortDescClassByStartDate(String name);

	@Query(value = " select * from class a where a.name LIKE CONCAT('%',:name,'%')  order by a.end_date asc ", nativeQuery = true)
	List<Class> sortAscClassEndDate(String name);

	@Query(value = " select * from class a where a.name LIKE CONCAT('%',:name,'%') order by a.end_date desc ", nativeQuery = true)
	List<Class> sortDescClassEndDate(String name);

}
