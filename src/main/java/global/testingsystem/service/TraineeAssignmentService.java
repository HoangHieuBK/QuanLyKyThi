package global.testingsystem.service;

import java.util.List;
import java.util.Optional;

import global.testingsystem.DTO.TraineeDTO;
import global.testingsystem.entity.Chapter;
import global.testingsystem.entity.ClassLesson;
import global.testingsystem.entity.TraineeAssignment;

public interface TraineeAssignmentService {
		// nmanh start
	  List<TraineeDTO> getAllTraineeAssignment();
	  
	  List<TraineeDTO> getOne (int id);
	  
	  List<TraineeDTO> getId(int Id);
	  
	  List<TraineeDTO> getTraineeAssimentByClassId(int classId);
	  List<TraineeDTO> getTraineeAssimentByClassIdAndTraineeAssignmentId(int classId, int traineeassignmentId);
	  
	  TraineeAssignment findById(int id);
	  
	  void save(TraineeAssignment ta);
	  
	  List<TraineeDTO>listSearch(int classId, String searchKey);
	  // nmanh end
	  //thuy
	  List<Object> getListExamResult(int chapter_id);
		List<Chapter> getAllLesson();
}
