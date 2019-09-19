package global.testingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import global.testingsystem.entity.ClassLesson;
public interface ClassLessonRepository extends JpaRepository<ClassLesson, Integer>{
		
		
		List<ClassLesson> findAllByOrderById();
		
		List<ClassLesson> getOne (int id);
		
		  @Query(value= "select * from class_lesson as e where e.id =:Id", nativeQuery = true)
			List<ClassLesson> getId(@Param ("Id") int Id);
	
		
}
