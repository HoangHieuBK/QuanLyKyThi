package global.testingsystem.service;

import java.util.List;
import java.util.Optional;

import global.testingsystem.entity.Unit;

public interface UnitService {

	boolean createUnit(Unit unit);

	boolean editUnit(Unit unit);

	void deleteUnit(int unitId);

	Unit findUnitById(int id);

	List<Unit> getAllUnit();

	List<Unit> searchUnit(String key);

	Unit getUnitById(int id);

	List<Unit> getListUnitByLessonType(String lessonType);

	int countUnitByChapter(int chapterId);

	// Linh
	List<Unit> getUnitByChapterAndOrderASC(int chapterId);

	void updateDraggedUnit(int currentPosition, int chapterId);

	void moveUnitDown(int currentPosition, int desiredPosition, int chapterId);

	void moveUnitUp(int currentPosition, int desiredPosition, int chapterId);

	void moveUnit(int desiredPosition, int chapterId);

	Optional<Unit> findById(int unitId);

	Unit copyUnit(int unitId);

	List<Unit> copyListUnit(int chapterId, int Id);

	List<Unit> saveAll(List<Unit> units);

}
