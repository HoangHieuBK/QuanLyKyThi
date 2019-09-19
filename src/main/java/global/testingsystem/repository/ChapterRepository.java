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

import global.testingsystem.entity.Chapter;

/**
 * @author USER
 *
 */
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
	Chapter findChapterByName(String name);
	// trung ngày 23/5

	@Query(value = " select c.id as id1, c.name as chapter_name,\r\n" + "         c.created_at, c.updated_at, \r\n"
			+ "         c.content_type, c.assignment,\r\n" + "         s.id as id2, s.name as subject_name,\r\n"
			+ "    		d.id as id3, d.name as chapter_name2,\r\n"
			+ "    		e.id as id4, e.name as exam_name from chapters c \r\n"
			+ "    		inner join subjects s on c.subject_id = s.id\r\n"
			+ "         inner join exams e on c.exam_id = e.id\r\n"
			+ "    	    left join chapters d on c.parent_id = d.id\r\n"
			+ "    		ORDER BY c.id DESC", nativeQuery = true)
	List<Object> listChapter2();

	@Query(value = "select *from chapters where ( subject_id =:idSubject and parent_id=0 )", nativeQuery = true)
	public List<Chapter> getChapterBySubjectAndParent(@Param("idSubject") int idSubject);

	@Query(value = "select c.id as id1, c.name as chapter_name, c.created_at, c.updated_at,s.id as id2, s.name as subject_name, d.id as id3, d.name as chapter_name2 \r\n"
			+ "from chapters c inner join subjects s on c.subject_id = s.id left join chapters d on c.parent_id = d.id  where c.name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
	List<Object> searchChapterByName(@Param("name") String name);

	@Query(value = "select *from chapters where subject_id =:idSubject", nativeQuery = true)
	public List<Chapter> getChapterBySubject(@Param("idSubject") int idSubject);

	@Query(value = "select c.id as id1, c.name as chapter_name, c.created_at, c.updated_at,s.id as id2, s.name as subject_name, d.id as id3, d.name as chapter_name2 \r\n"
			+ "from chapters c inner join subjects s on c.subject_id = s.id left join chapters d on c.parent_id = d.id ORDER BY c.id DESC", nativeQuery = true)
	List<Object> list();

	// Linh
	@Query(value = "select * from chapters where subject_id =:subject_id and parent_id=0 order by chapter_order ASC", nativeQuery = true)
	List<Chapter> getChapterBySubjectAndChapterOrderAsc(@Param("subject_id") int subjectId);

	@Query(value = "select * from chapters where subject_id =:subject_id and parent_id =:parent_id order by chapter_order ASC", nativeQuery = true)
	List<Chapter> getChapterBySubjectAndParentAndChapterOrderAsc(@Param("subject_id") int subjectId,
			@Param("parent_id") int parentId);

	@Modifying
	@Query(value = "UPDATE chapters SET chapter_order = 0 WHERE chapter_order = :current_position AND subject_id = :subject_id AND parent_id = :parent_id", nativeQuery = true)
	void updateDraggedChapter(@Param("current_position") int currentPosition, @Param("subject_id") int subjectId,
			@Param("parent_id") int parentId);

	@Modifying
	@Query(value = "UPDATE chapters SET chapter_order = (chapter_order - 1) WHERE chapter_order > :current_position AND chapter_order <= :desired_position AND subject_id =:subject_id AND parent_id = :parent_id ", nativeQuery = true)
	void moveChapterDown(@Param("current_position") int currentPosition, @Param("desired_position") int desiredPosition,
			@Param("subject_id") int subjectId, @Param("parent_id") int parentId);

	@Modifying
	@Query(value = "UPDATE chapters SET chapter_order = (chapter_order + 1) WHERE chapter_order >= :desired_position AND chapter_order < :current_position AND subject_id =:subject_id AND parent_id = :parent_id ", nativeQuery = true)
	void moveChapterUp(@Param("current_position") int currentPosition, @Param("desired_position") int desiredPosition,
			@Param("subject_id") int subjectId, @Param("parent_id") int parentId);

	@Modifying
	@Query(value = "UPDATE chapters SET chapter_order = :desired_position WHERE chapter_order = 0 AND subject_id = :subject_id AND parent_id = :parent_id", nativeQuery = true)
	void moveChapter(@Param("desired_position") int desiredPosition, @Param("subject_id") int subjectId,
			@Param("parent_id") int parentId);

	@Query(value = "select * from chapters where chapter_id =:chapter_id and subject_id =:subject_id  and parent_id =:parent_id", nativeQuery = true)
	Chapter getChapterByIdAndSubjectAndParent(@Param("chapter_id") int chapterId, @Param("subject_id") int subjectId,
			@Param("parent_id") int parentId);

	@Query(value = "select * from chapters where subject_id =:subject_id  and parent_id =:parent_id", nativeQuery = true)
	List<Chapter> getChapterBySubjectAndParent(@Param("subject_id") int subjectId, @Param("parent_id") int parentId);

	@Query(value = "select count(*) from chapters where subject_id =:subject_id and parent_id =:parent_id", nativeQuery = true)
	int countChapterBySubjectAndParent(@Param("subject_id") int subjectId, @Param("parent_id") int parentId);

	@Query(value = "select *from chapters where name=:name", nativeQuery = true)
	List<Chapter> getListChapterByName(@Param("name") String name);

	@Query(value = "select id from chapters where parent_id=:parentId", nativeQuery = true)
	List<Integer> getAllSubId(@Param("parentId") int parentId);

	@Query(value = "select id from chapters where subject_id=:subjectId", nativeQuery = true)
	List<Integer> getListChapterIdBySubjectId(@Param("subjectId") int subjectId);

	List<Chapter> findAllByOrderByNameAsc();

	// thuy
	@Query(value = "select * from chapters where parent_id!=0 group by id", nativeQuery = true)
	List<Chapter> getAllChapter();

	// trung
	@Query(value = "select * from chapters where parent_id=:parentId", nativeQuery = true)
	List<Chapter> findByParent_Id(int parentId);

	@Query(value = "select * from chapters where parent_id = 0", nativeQuery = true)
	List<Chapter> getParentName();

	// trung end

	List<Chapter> findBySubject_Id(Integer id);

	@Query(value = "select c from Chapter c join c.subject s where c.parent_id =?1 and s.id=?2")
	List<Chapter> findByParent_idAndSubject_Id(int parentId, int id);

	
};
