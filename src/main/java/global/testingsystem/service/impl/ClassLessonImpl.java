package global.testingsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import global.testingsystem.entity.ClassLesson;
import global.testingsystem.repository.ClassLessonRepository;
import global.testingsystem.service.ClassLessonService;
@Service
@Transactional
public class ClassLessonImpl implements ClassLessonService {
	@Autowired
	ClassLessonRepository classLessonRepository;
	@Override
	public List<ClassLesson> findAllByOrderById() {
		return classLessonRepository.findAllByOrderById();
	}
	
}
