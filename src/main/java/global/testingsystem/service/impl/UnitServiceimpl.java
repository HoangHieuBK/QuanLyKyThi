package global.testingsystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import global.testingsystem.entity.Chapter;
import global.testingsystem.entity.Unit;
import global.testingsystem.repository.UnitRepository;
import global.testingsystem.service.ChapterService;
import global.testingsystem.service.UnitService;

@Service
@Transactional
public class UnitServiceimpl implements UnitService {

	@Autowired
	private UnitRepository unitRepository;

	@Autowired
	private ChapterService chapterService;

	@Override
	public boolean createUnit(Unit unit) {
		// TODO Auto-generated method stub
		unitRepository.save(unit);
		return true;
	}

	@Override
	public boolean editUnit(Unit unit) {
		// TODO Auto-generated method stub
		unitRepository.save(unit);
		return true;
	}

	@Override
	public void deleteUnit(int id) {
		// TODO Auto-generated method stub
		unitRepository.deleteById(id);
	}

	@Override
	public Unit findUnitById(int id) {
		// TODO Auto-generated method stub
		return unitRepository.findById(id).get();
	}

	@Override
	public List<Unit> getAllUnit() {
		// TODO Auto-generated method stub
		try {
			return unitRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Unit> searchUnit(String key) {
		// TODO Auto-generated method stub
		return unitRepository.getListUnitByName(key);
	}

	@Override
	public Unit getUnitById(int id) {
		// TODO Auto-generated method stub

		return unitRepository.findById(id).get();
	}

	@Override
	public List<Unit> getListUnitByLessonType(String lessonType) {
		// TODO Auto-generated method stub
		return unitRepository.getListUnitByLessonType(lessonType);
	}

	@Override

	public int countUnitByChapter(int chapterId) {
		// TODO Auto-generated method stub
		return unitRepository.countUnitbyChapter(chapterId);
	}

	@Override
	public List<Unit> getUnitByChapterAndOrderASC(int chapterId) {
		// TODO Auto-generated method stub
		return unitRepository.getUnitByChapterAndOrderASC(chapterId);
	}

	@Override
	public void updateDraggedUnit(int currentPosition, int chapterId) {
		// TODO Auto-generated method stub
		unitRepository.updateDraggedUnit(currentPosition, chapterId);
	}

	@Override
	public void moveUnitDown(int currentPosition, int desiredPosition, int chapterId) {
		// TODO Auto-generated method stub
		unitRepository.moveUnitDown(currentPosition, desiredPosition, chapterId);
	}

	@Override
	public void moveUnitUp(int currentPosition, int desiredPosition, int chapterId) {
		// TODO Auto-generated method stub
		unitRepository.moveUnitUp(currentPosition, desiredPosition, chapterId);
	}

	@Override
	public void moveUnit(int desiredPosition, int chapterId) {
		// TODO Auto-generated method stub
		unitRepository.moveUnit(desiredPosition, chapterId);
	}

	@Override
	public Optional<Unit> findById(int unitId) {
		// TODO Auto-generated method stub
		return unitRepository.findById(unitId);

	}

	@Override
	public Unit copyUnit(int unitId) {
		// TODO Auto-generated method stub
		Optional<Unit> unit = unitRepository.findById(unitId);
		if (unit.isPresent()) {
			Unit u = new Unit();
			u.setName(unit.get().getName());
			u.setContentType(unit.get().getContentType());
			u.setDocument(unit.get().getDocument());
			u.setExam(unit.get().getExam());
			u.setMediaLink(unit.get().getMediaLink());
			u.setChapter(unit.get().getChapter());
			u.setUnitOrder(unitRepository.countUnitbyChapter(unit.get().getChapter().getId()) + 1);
			unitRepository.save(u);
			return u;
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public List<Unit> copyListUnit(int chapterId, int id) {
		// TODO Auto-generated method stub
		List<Unit> units = unitRepository.findUnitByChapter(chapterId);
		Chapter chapter = chapterService.findChapterById(id);
		return units.stream().map(u -> {
			Unit unit = new Unit();
			unit.setName(u.getName());
			unit.setContentType(u.getContentType());
			unit.setDocument(u.getDocument());
			unit.setExam(u.getExam());
			unit.setMediaLink(u.getMediaLink());
			unit.setChapter(chapter);
			unit.setUnitOrder(u.getUnitOrder());
			return unit;
		}).collect(Collectors.toList());
	}

	@Override
	public List<Unit> saveAll(List<Unit> units) {
		// TODO Auto-generated method stub
		return unitRepository.saveAll(units);
	}

}
