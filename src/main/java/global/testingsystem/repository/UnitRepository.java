package global.testingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import global.testingsystem.entity.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
	@Query(value = "select * from unit as p where p.name like concat('%',:key,'%')", nativeQuery = true)
	List<Unit> getListUnitByName(@Param("key") String key);

	@Query(value = "select * from unit  where lesson_type = ?1  ", nativeQuery = true)
	List<Unit> getListUnitByLessonType(String lessonType);

	@Query(value = "SELECT * FROM Lesson l WHERE l.chapter_id = :id", nativeQuery = true)
	List<Unit> findAllUnitById(@Param("id") int id);

	@Query(value = "SELECT count(u.unit_id) FROM unit u WHERE u.chapter_id = :id", nativeQuery = true)
	int countUnitbyChapter(@Param("id") int chapterId);

	// Linh
	@Query(value = "select * from unit where chapter_id =:chapter_id order by unit_order ASC ", nativeQuery = true)
	List<Unit> getUnitByChapterAndOrderASC(@Param("chapter_id") int chapterId);

	@Modifying
	@Query(value = "UPDATE unit SET unit_order = 0 WHERE unit_order = :current_position AND chapter_id = :chapter_id", nativeQuery = true)
	void updateDraggedUnit(@Param("current_position") int currentPosition, @Param("chapter_id") int chapterId);

	@Modifying
	@Query(value = "UPDATE unit SET unit_order = (unit_order - 1) WHERE unit_order > :current_position AND unit_order <= :desired_position AND chapter_id =:chapter_id", nativeQuery = true)
	void moveUnitDown(@Param("current_position") int currentPosition, @Param("desired_position") int desiredPosition,
			@Param("chapter_id") int chapterId);

	@Modifying
	@Query(value = "UPDATE unit SET unit_order = (unit_order + 1) WHERE unit_order >= :desired_position AND unit_order < :current_position AND chapter_id =:chapter_id ", nativeQuery = true)
	void moveUnitUp(@Param("current_position") int currentPosition, @Param("desired_position") int desiredPosition,
			@Param("chapter_id") int chapterId);

	@Modifying
	@Query(value = "UPDATE unit SET unit_order = :desired_position WHERE unit_order = 0 AND chapter_id = :chapter_id", nativeQuery = true)
	void moveUnit(@Param("desired_position") int desiredPosition, @Param("chapter_id") int chapterId);

	@Query(value = "select count(*) from unit where chapter_id =:chapter_id", nativeQuery = true)
	int countUnitByChapter(@Param("chapter_id") int chapterId);

	@Query(value = "select * from unit where chapter_id =:chapter_id", nativeQuery = true)
	List<Unit> findUnitByChapter(@Param("chapter_id") int chapterId);
}
