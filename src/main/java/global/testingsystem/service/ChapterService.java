/**
 * 
 */
package global.testingsystem.service;

import java.util.List;
import java.util.Optional;

import global.testingsystem.DTO.ChapterDto;
import global.testingsystem.entity.Chapter;
import global.testingsystem.response.SubjectResult;

/**
 * @author USER
 *
 */
public interface ChapterService {

	List<Object> getListChapter();

	List<Object> getListChapter2();

	List<Chapter> listChapter();

	boolean editChapter(Chapter chapter);

	boolean addChapter(Chapter chapter);

	boolean deleteChapter(int id);

	Chapter findChapterById(int id);

	List<Object> searchChapterByName(String name);

	Chapter findChapterByName(String name);

	List<Chapter> sortChapterByName(String name);

	// TU ***************************************************

	String saveChapter(Chapter chapter);

	Chapter getChapterById(int chapterId);

	List<Chapter> getListChapterBySubject(int idSubject);

	List<Chapter> getListChapterBySubjectAndParent(int idSubject);

	List<Chapter> getListChapterByName(String name);

	List<Integer> getAllSubId(int parentId);

	List<Integer> getListChapterIdBySubjectId(int subjectId);

	// Linh
	List<SubjectResult> getChapterBySubjectAndChapterOrderAsc(int subjectId);

	void updateDraggedChapter(int currentPosition, int subjectId, int parentId);

	void moveChapterDown(int currentPosition, int desiredPosition, int subjectId, int parentId);

	void moveChapterUp(int currentPosition, int desiredPosition, int subjectId, int parentId);

	void moveChapter(int desiredPosition, int subjectId, int parentId);

	// thuy
	List<Chapter> getAllChapter();

	// trung

	List<Chapter> findByParent_Id(int parentId);

	Chapter getChapterByIdAndSubjectAndParent(int chapterId, int subjectId, int parentId);

	List<Chapter> getChapterBySubjectAndParent(int subjectId, int parentId);

	int countChapterBySubjectAndParent(int subjectId, int parentId);

	Optional<Chapter> findChapterId(int chapterId);

	List<Chapter> saveAll(List<Chapter> chapters);

	Chapter copyChapter(int chapterId);

	void copyListLesson(int subjectId, int parentId, int chapterId);

	void deleteChapter(Integer chapterId);

	List<ChapterDto> findBySubject_Id(Integer id);

	List<ChapterDto> findByParentId();

	void insertChapter(Chapter chapter);

	List<ChapterDto> getChapterByIdAndSubjectId(Integer parentId, Integer subjectId);

	ChapterDto findById(Integer chapterId);

	void updateChapter(Chapter chapter);
	
	boolean checkNameChapterInsertDonotExit(String name);
	boolean checkNameChapterUpdateDonotExit(Integer id,String name);
}
