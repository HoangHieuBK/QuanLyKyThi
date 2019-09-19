package global.testingsystem.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import global.testingsystem.DTO.TraineeDTO;
import global.testingsystem.entity.Chapter;
import global.testingsystem.entity.ClassLesson;
import global.testingsystem.entity.TraineeAssignment;
import global.testingsystem.repository.ChapterRepository;
import global.testingsystem.repository.ClassLessonRepository;
import global.testingsystem.repository.TraineeAssignmentRepository;
import global.testingsystem.service.TraineeAssignmentService;
@Service
@Transactional
public class TraineeAssignmentServiceImpl implements TraineeAssignmentService {
	@Autowired
	TraineeAssignmentRepository traineeAssignmentRepository;
	@Autowired 
	ClassLessonRepository classLessonRepository;
	@Autowired
	ChapterRepository chapterReponsitory;
	@PersistenceContext
	EntityManager entityManager;
//	@Override
//	public List<TraineeDTO> getAllTraineeAssignmen() {
//		List<Object> objects = traineeAssignmentRepository.getAll();
//		List<TraineeDTO> traineeDTOs = new ArrayList<>();
//		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		for (Object object : objects) {
//			TraineeDTO traineeDTO = new TraineeDTO();
//			Object[] obj = (Object[]) object;
//			traineeDTO.setId(Integer.parseInt(obj[0].toString()));
//			traineeDTO.setFullName(obj[1].toString());
//			try {
//				traineeDTO.setTimeSubmit(formatter.parse(obj[2].toString()));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			traineeDTO.setTrainerId(obj[3].toString());
//			traineeDTO.setResult(Integer.parseInt(obj[4].toString()));
//			traineeDTO.setResultDetail(obj[5].toString());
//			try {
//				traineeDTO.setTimeEval(formatter.parse(obj[6].toString()));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			traineeDTO.setAssignment(obj[7].toString());
//			traineeDTOs.add(traineeDTO);
//		}
//		return traineeDTOs;
//	}
	// ngo minh anh
	@Override
	public List<TraineeDTO> getAllTraineeAssignment() {
		 List<ClassLesson> classLessons  = classLessonRepository.findAllByOrderById();
		 List<TraineeDTO> traineeDTOs = new ArrayList<>();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 for (ClassLesson classLesson : classLessons) {
			TraineeDTO traineeDTO = new TraineeDTO();
			traineeDTO.setId(classLesson.getId());
			traineeDTO.setFullName(classLesson.getTraineeAssignment().getUser1().getFullname());
			try {
				traineeDTO.setTimeSubmit(formatter.parse(classLesson.getTraineeAssignment().getTimeSubmit().toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			traineeDTO.setTrainerId(classLesson.getClass1().getTrainer());
			traineeDTO.setResult(Integer.parseInt(classLesson.getTraineeAssignment().getResult()));
			traineeDTO.setResultDetail(classLesson.getTraineeAssignment().getResultDetail().toString());
			try {
				traineeDTO.setTimeEval(formatter.parse(classLesson.getTraineeAssignment().getTimeEval().toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			traineeDTO.setAssignment(classLesson.getChapter().getAssignment());
			traineeDTOs.add(traineeDTO);
		}
		return traineeDTOs;
	}
	@Override
	public List<TraineeDTO> getOne(int id) {
	
		return null;
	}
	@Override
	public List<TraineeDTO> getId(int Id) {
		 List<ClassLesson> classLessons  = classLessonRepository.getId(Id);
		 List<TraineeDTO> traineeDTOs = new ArrayList<>();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 for (ClassLesson classLesson : classLessons) {
			TraineeDTO traineeDTO = new TraineeDTO();
			traineeDTO.setId(classLesson.getId());
			traineeDTO.setFullName(classLesson.getTraineeAssignment().getUser1().getFullname());
			try {
				traineeDTO.setTimeSubmit(formatter.parse(classLesson.getTraineeAssignment().getTimeSubmit().toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			traineeDTO.setTrainerId(classLesson.getClass1().getTrainer());
			traineeDTO.setResult(Integer.parseInt(classLesson.getTraineeAssignment().getResult()));
			traineeDTO.setResultDetail(classLesson.getTraineeAssignment().getResultDetail().toString());
			try {
				traineeDTO.setTimeEval(formatter.parse(classLesson.getTraineeAssignment().getTimeEval().toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			traineeDTO.setAssignment(classLesson.getChapter().getAssignment());
			traineeDTOs.add(traineeDTO);
		}
		return traineeDTOs;
	}
	
	
	@Override
	public List<TraineeDTO> getTraineeAssimentByClassId(int classId) {
		List<Object> objects = traineeAssignmentRepository.getTraineeAssimentByClassId(classId);
		List<TraineeDTO> traineeDTOs = new ArrayList<>();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (Object object : objects) {
			TraineeDTO traineeDTO = new TraineeDTO();
			Object[] obj = (Object[]) object;
			traineeDTO.setId(Integer.parseInt(obj[0].toString()));
			traineeDTO.setFullName(obj[2].toString());
			try {
				traineeDTO.setTimeSubmit(formatter.parse(obj[6].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// traineeDTO.setTrainerId(obj[3].toString());
			traineeDTO.setResult(Integer.parseInt(obj[3].toString()));
			traineeDTO.setResultDetail(obj[4].toString());
			try {
				traineeDTO.setTimeEval(formatter.parse(obj[5].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			traineeDTO.setAssignment(obj[7].toString());
			traineeDTO.setEvaluator(obj[9].toString());
			traineeDTO.setFile(obj[10].toString());
			traineeDTOs.add(traineeDTO);
		}
		return traineeDTOs;
	}
	@Override
	public List<TraineeDTO> getTraineeAssimentByClassIdAndTraineeAssignmentId(int classId, int traineeassignmentId) {
		List<Object> objects = traineeAssignmentRepository.getTraineeAssimentByClassIdAndTraineeAssignmentId(classId, traineeassignmentId);
		List<TraineeDTO> traineeDTOs = new ArrayList<>();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (Object object : objects) {
			TraineeDTO traineeDTO = new TraineeDTO();
			Object[] obj = (Object[]) object;
			traineeDTO.setId(Integer.parseInt(obj[0].toString()));
			traineeDTO.setFullName(obj[2].toString());
			try {
				traineeDTO.setTimeSubmit(formatter.parse(obj[6].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// traineeDTO.setTrainerId(obj[3].toString());
			traineeDTO.setResult(Integer.parseInt(obj[3].toString()));
			traineeDTO.setResultDetail(obj[4].toString());
			try {
				traineeDTO.setTimeEval(formatter.parse(obj[5].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			traineeDTO.setAssignment(obj[7].toString());
			traineeDTO.setEvaluator(obj[9].toString());
			traineeDTO.setFile(obj[10].toString());
			traineeDTOs.add(traineeDTO);
		}
		return traineeDTOs;
	}
	@Override
	public TraineeAssignment findById(int id) {
		
		return traineeAssignmentRepository.findById(id).get();
	}
	@Override
	public void save(TraineeAssignment ta) {
		traineeAssignmentRepository.save(ta);
		
	}
public List<Object> getListExamResult(int chapter_id) {
		
		return traineeAssignmentRepository.getListExamResult(chapter_id) ;
	}
	@Override
	public List<Chapter> getAllLesson() {
		try {
			return chapterReponsitory.findAll();
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<TraineeDTO> listSearch(int classId, String keySearch) {
		List<Object> objects = traineeAssignmentRepository.listSearch(classId, keySearch);
		List<TraineeDTO> traineeDTOs = new ArrayList<>();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (Object object : objects) {
			TraineeDTO traineeDTO = new TraineeDTO();
			Object[] obj = (Object[]) object;
			traineeDTO.setId(Integer.parseInt(obj[0].toString()));
			traineeDTO.setFullName(obj[2].toString());
			try {
				traineeDTO.setTimeSubmit(formatter.parse(obj[6].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			traineeDTO.setResult(Integer.parseInt(obj[3].toString()));
			traineeDTO.setResultDetail(obj[4].toString());
			try {
				traineeDTO.setTimeEval(formatter.parse(obj[5].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			traineeDTO.setAssignment(obj[7].toString());
			traineeDTO.setEvaluator(obj[9].toString());
			traineeDTO.setFile(obj[10].toString());
			traineeDTOs.add(traineeDTO);
		}
		try {
			return traineeDTOs;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
