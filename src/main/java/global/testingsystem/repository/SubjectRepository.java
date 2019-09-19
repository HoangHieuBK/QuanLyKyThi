/**
 * 
 */
package global.testingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import global.testingsystem.entity.Domain;
import global.testingsystem.entity.Subject;

/**
 * @author USER
 *
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>{
        
	@Query(value="select s.id, s.name, s.created_at, s.updated_at,s.status from subjects s order by s.id asc ", nativeQuery= true)
	List<Subject> list();
	
    Subject findSubjectByName(String name);    
    
    List<Subject> findAllByOrderByNameAsc();
    
    Subject findSubjectById(Integer id);
    
    @Query(value="select subject_id from exams where id = :id", nativeQuery = true)
    Integer getSubjectByExamId(@Param("id") int id );
    
}
