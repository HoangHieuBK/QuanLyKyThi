package global.testingsystem.service;

import java.util.List;

import global.testingsystem.entity.ClassLesson;

public interface ClassLessonService {

	  List<ClassLesson> findAllByOrderById();

}
