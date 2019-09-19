/**
 * 
 */
package global.testingsystem.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import global.testingsystem.DTO.ChapterDto;
import global.testingsystem.entity.Chapter;
import global.testingsystem.entity.Domain;
import global.testingsystem.entity.Unit;
import global.testingsystem.repository.ChapterRepository;
import global.testingsystem.repository.SubjectRepository;
import global.testingsystem.repository.UnitRepository;
import global.testingsystem.response.ChapterDTO;
import global.testingsystem.response.SubjectResult;
import global.testingsystem.response.UnitDTO;
import global.testingsystem.service.ChapterService;
import global.testingsystem.service.UnitService;

/**
 * @author USER
 *
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

	@Autowired
	private ChapterRepository chapterRepo;

	@Autowired
	private UnitRepository unitRepository;

	@Autowired
	private UnitService unitService;
	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public List<Object> getListChapter() {
		// TODO Auto-generated method stub
		return chapterRepo.list();
	}

	@Override
	public List<Chapter> listChapter() {
		return chapterRepo.getParentName();
	}

	@Override
	public boolean editChapter(Chapter chapter) {
		// TODO Auto-generated method stub
		chapterRepo.save(chapter);
		return true;
	}

	@Override
	public boolean addChapter(Chapter chapter) {
		// TODO Auto-generated method stub
		chapterRepo.save(chapter);
		return true;
	}

	@Override
	public List<Object> searchChapterByName(String name) {
		// TODO Auto-generated method stub
		return chapterRepo.searchChapterByName(name);
	}

	@Override
	public Chapter findChapterByName(String name) {
		// TODO Auto-generated method stub
		return chapterRepo.findChapterByName(name);
	}

	@Override
	public boolean deleteChapter(int id) {
		// TODO Auto-generated method stub
		chapterRepo.deleteById(id);
		return true;
	}

	@Override
	public Chapter findChapterById(int id) {
		// TODO Auto-generated method stub
		try {
			return chapterRepo.findById(id).get();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Chapter> sortChapterByName(String name) {
		// TODO Auto-generated method stub
		List<Chapter> listChapter = chapterRepo.findAll();
		try {
			if ("DESC".equals(name)) {
				listChapter.sort(Comparator.comparing(Chapter::getName));
			} else {
				listChapter.sort(Comparator.comparing(Chapter::getName).reversed());
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new LinkedList<Chapter>();
		}
		return listChapter;
	}
//TU ********************************************************************

	@Override
	public String saveChapter(Chapter chapter) {
		// TODO Auto-generated method stub
		try {
			chapterRepo.save(chapter);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public Chapter getChapterById(int chapterId) {
		return chapterRepo.findById(chapterId).get();
	}
	// TU ********************************************************************

	@Override
	public List<Chapter> getListChapterBySubject(int idSubject) {
		// TODO Auto-generated method stub
		try {
			return chapterRepo.getChapterBySubject(idSubject);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Chapter> getListChapterBySubjectAndParent(int idSubject) {
		// TODO Auto-generated method stub
		return chapterRepo.getChapterBySubjectAndParent(idSubject);
	}

	@Override
	public List<Chapter> getListChapterByName(String name) {
		return chapterRepo.getListChapterByName(name);
	}

	@Override
	public List<Integer> getAllSubId(int parentId) {
		return chapterRepo.getAllSubId(parentId);
	}

	@Override
	public List<Integer> getListChapterIdBySubjectId(int subjectId) {
		return chapterRepo.getListChapterIdBySubjectId(subjectId);
	}

	// Linh
	@Override
	public List<SubjectResult> getChapterBySubjectAndChapterOrderAsc(int subjectId) {
		List<Chapter> chapters = chapterRepo.getChapterBySubjectAndChapterOrderAsc(subjectId);
		return chapters.stream().map(chapter -> {
			SubjectResult subjectResult = new SubjectResult();
			subjectResult.setId(chapter.getId());
			subjectResult.setName(chapter.getName());
			subjectResult.setChapterOrder(chapter.getChapterOrder());
			subjectResult.setParent_id(chapter.getParent_id());
			subjectResult.setCreated_at(chapter.getCreated_at());
			subjectResult.setChapters(listChapter(subjectId, chapter.getId()));
			return subjectResult;
		}).collect(Collectors.toList());
	}

	public List<ChapterDTO> listChapter(int subjectId, int parentId) {
		List<Chapter> chapters = chapterRepo.getChapterBySubjectAndParentAndChapterOrderAsc(subjectId, parentId);
		return chapters.stream().map(chapter -> {
			ChapterDTO chapterDTO = new ChapterDTO();
			chapterDTO.setId(chapter.getId());
			chapterDTO.setName(chapter.getName());
			chapterDTO.setChapter_order(chapter.getChapterOrder());
			chapterDTO.setParent_id(chapter.getParent_id());
			chapterDTO.setCreated_at(chapter.getCreated_at());
			chapterDTO.setUnits(listUnit(chapter.getId()));
			return chapterDTO;
		}).collect(Collectors.toList());
	}

	public List<UnitDTO> listUnit(int chapterId) {
		List<Unit> units = unitRepository.getUnitByChapterAndOrderASC(chapterId);
		return units.stream().map(unit -> {
			UnitDTO unitDTO = new UnitDTO();
			unitDTO.setUnit_id(unit.getUnitId());
			unitDTO.setUnit_name(unit.getName());
			unitDTO.setUnit_order(unit.getUnitOrder());
			return unitDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public void updateDraggedChapter(int currentPosition, int subjectId, int parentId) {
		// TODO Auto-generated method stub
		chapterRepo.updateDraggedChapter(currentPosition, subjectId, parentId);
	}

	@Override
	public void moveChapterDown(int currentPosition, int desiredPosition, int subjectId, int parentId) {
		// TODO Auto-generated method stub
		chapterRepo.moveChapterDown(currentPosition, desiredPosition, subjectId, parentId);
	}

	@Override
	public void moveChapterUp(int currentPosition, int desiredPosition, int subjectId, int parentId) {
		// TODO Auto-generated method stub
		chapterRepo.moveChapterUp(currentPosition, desiredPosition, subjectId, parentId);
	}

	@Override
	public void moveChapter(int desiredPosition, int subjectId, int parentId) {
		// TODO Auto-generated method stub
		chapterRepo.moveChapter(desiredPosition, subjectId, parentId);
	}

	@Override
	public Chapter getChapterByIdAndSubjectAndParent(int chapterId, int subjectId, int parentId) {
		// TODO Auto-generated method stub
		return chapterRepo.getChapterByIdAndSubjectAndParent(chapterId, subjectId, parentId);
	}

	@Override
	public List<Chapter> getChapterBySubjectAndParent(int subjectId, int parentId) {
		// TODO Auto-generated method stub
		return chapterRepo.getChapterBySubjectAndParent(subjectId, parentId);
	}

	@Override
	public int countChapterBySubjectAndParent(int subjectId, int parentId) {
		// TODO Auto-generated method stub
		return chapterRepo.countChapterBySubjectAndParent(subjectId, parentId);
	}

	@Override
	public List<Chapter> getAllChapter() {
		return chapterRepo.getAllChapter();
	}

	@Override
	public List<Object> getListChapter2() {
		// TODO Auto-generated method stub
		return chapterRepo.listChapter2();
	}

	@Override
	public Optional<Chapter> findChapterId(int chapterId) {
		// TODO Auto-generated method stub
		return chapterRepo.findById(chapterId);
	}

	@Override
	public List<Chapter> saveAll(List<Chapter> chapters) {
		// TODO Auto-generated method stub
		return chapterRepo.saveAll(chapters);
	}

	@Override
	public Chapter copyChapter(int chapterId) {
		// TODO Auto-generated method stub
		Optional<Chapter> chapter = chapterRepo.findById(chapterId);
		if (chapter.isPresent()) {
			Chapter ch = new Chapter();
			ch.setName(chapter.get().getName());
			ch.setContentType(chapter.get().getContentType());
			ch.setAssignment(chapter.get().getAssignment());
			ch.setChapterOrder(chapterRepo.countChapterBySubjectAndParent(chapter.get().getSubject().getId(),
					chapter.get().getParent_id()) + 1);
			ch.setStandard(chapter.get().getStandard());
			ch.setSubject(chapter.get().getSubject());
			ch.setExam(chapter.get().getExam());
			ch.setParent_id(chapter.get().getParent_id());
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			ch.setCreated_at(sqlDate);
			chapterRepo.save(ch);
			return ch;
		} else {
			return null;
		}
	}

	@Override
	public void copyListLesson(int subjectId, int parentId, int chapterId) {
		// TODO Auto-generated method stub
		List<Chapter> chapters = chapterRepo.getChapterBySubjectAndParent(subjectId, parentId);
		for (Chapter chap : chapters) {
			Chapter c = new Chapter();
			c.setName(chap.getName());
			c.setContentType(chap.getName());
			c.setAssignment(chap.getAssignment());
			c.setChapterOrder(chap.getChapterOrder());
			c.setStandard(chap.getStandard());
			c.setSubject(chap.getSubject());
			c.setExam(chap.getExam());
			c.setParent_id(chapterId);
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			c.setCreated_at(sqlDate);
			chapterRepo.save(c);
			System.out.println(c.getId());
			List<Unit> units = unitService.copyListUnit(chap.getId(), c.getId());
			if (!units.isEmpty()) {
				unitRepository.saveAll(units);
			}
		}
	}

	@Override
	public List<Chapter> findByParent_Id(int parentId) {
		// TODO Auto-generated method stub
		return chapterRepo.findByParent_Id(parentId);
	}

	public void deleteLessonByChapterId(int parentId) {
		Chapter chapter2 = chapterRepo.getOne(parentId);
		chapterRepo.deleteById(parentId);

	}

	@Override
	public void deleteChapter(Integer chapterId) {
		Chapter chapter = chapterRepo.getOne(chapterId);
		if (chapter != null) {
			if (chapter.getStatus() == 0) {
				chapter.setStatus(1);
			} else {
				chapter.setStatus(0);
			}
			chapterRepo.save(chapter);
		}
	}

	@Override
	public List<ChapterDto> findBySubject_Id(Integer id) {

		List<Chapter> chapters = chapterRepo.findBySubject_Id(id);
		List<ChapterDto> chapterDtos = new ArrayList<ChapterDto>();
		for (Chapter chapter : chapters) {
			ChapterDto chapterDto = new ChapterDto();
			chapterDto.setId(chapter.getId());
			chapterDto.setName(chapter.getName());
			chapterDto.setChapterOrder(chapter.getChapterOrder());
			chapterDto.setStatus(chapter.getStatus());
			chapterDtos.add(chapterDto);
		}
		return chapterDtos;

	}

	public List<ChapterDto> findByParentId() {
		List<Chapter> chapters = chapterRepo.getParentName();
		List<ChapterDto> chapterDtos = new ArrayList<ChapterDto>();
		for (Chapter chapter : chapters) {
			ChapterDto chapterDto = new ChapterDto();
			chapterDto.setId(chapter.getId());
			chapterDto.setName(chapter.getName());
			chapterDto.setChapterOrder(chapter.getChapterOrder());
			chapterDto.setParent_id(chapter.getParent_id());
			chapterDto.setStatus(chapter.getStatus());
			chapterDtos.add(chapterDto);
		}
		return chapterDtos;
	}

	@Override
	public void insertChapter(Chapter chapter) {
		chapter.setSubject(subjectRepository.getOne(chapter.getSubject().getId()));
		chapter.setStatus(0);
		chapterRepo.save(chapter);

	}

	@Override
	public List<ChapterDto> getChapterByIdAndSubjectId(Integer parentId, Integer subjectId) {
		List<ChapterDto> chapterDtos = new ArrayList<ChapterDto>();
		List<Chapter> chapters = chapterRepo.findByParent_idAndSubject_Id(parentId, subjectId);
		for (Chapter chapter : chapters) {
			ChapterDto chapterDto = new ChapterDto();
			chapterDto.setId(chapter.getId());
			chapterDto.setName(chapter.getName());
			chapterDto.setChapterOrder(chapter.getChapterOrder());
			chapterDto.setParent_id(chapter.getParent_id());
			chapterDto.setStatus(chapter.getStatus());
			chapterDtos.add(chapterDto);
			List<Chapter> chapterChilds = chapterRepo.findByParent_Id(chapter.getId());
			for (Chapter chapter2 : chapterChilds) {
				ChapterDto chapterDto2 = new ChapterDto();
				chapterDto2.setId(chapter2.getId());
				chapterDto2.setName(chapter2.getName());
				chapterDto2.setChapterOrder(chapter2.getChapterOrder());
				chapterDto2.setParent_id(chapter2.getParent_id());
				chapterDto2.setStatus(chapter2.getStatus());
				chapterDtos.add(chapterDto2);
			}

		}

		return chapterDtos;

	}

	@Override
	public ChapterDto findById(Integer id) {
		Chapter chapter = chapterRepo.getOne(id);
		ChapterDto chapterDto = new ChapterDto();
		chapterDto.setId(chapter.getId());
		chapterDto.setName(chapter.getName());
		chapterDto.setChapterOrder(chapter.getChapterOrder());
		chapterDto.setParent_id(chapter.getParent_id());
		chapterDto.setStatus(chapter.getStatus());
		return chapterDto;
	}

	@Override
	public void updateChapter(Chapter chapter) {
		Chapter chapter2 = chapterRepo.getOne(chapter.getId());
		if (chapter2 != null) {
			chapter2.setId(chapter.getId());
			chapter2.setName(chapter.getName());
			chapter2.setParent_id(chapter.getParent_id());
			chapter2.setChapterOrder(chapter.getChapterOrder());
			chapter2.setStatus(chapter.getStatus());
			chapter2.setSubject(subjectRepository.getOne(chapter.getSubject().getId()));
			chapterRepo.save(chapter);
		}

	}

	@Override
	public boolean checkNameChapterInsertDonotExit(String name) {
		Chapter chapter = chapterRepo.findChapterByName(name);
		if (chapter != null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean checkNameChapterUpdateDonotExit(Integer id, String name) {
		Chapter chapter = chapterRepo.getOne(id);
		if (!chapter.getName().equals(name)) {
			Chapter chapter2 = chapterRepo.findChapterByName(name);
			if (chapter2 != null) {
				return false;
			} else {
				return true;
			}

		} else {
			return true;
		}

	}
}
