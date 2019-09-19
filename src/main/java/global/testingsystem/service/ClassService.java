package global.testingsystem.service;

import java.util.List;

import global.testingsystem.entity.Class;

public interface ClassService {

	List<Class> getAllClass(int userid);
	List<Class> getClassById(int id);
	Class findById(int id);
	
	boolean update(Class classes);
	boolean create(Class classes);

//	trinh van tai
	
	Class getClassByClassId(int id);
	
	List<Class> getAllClass();

	List<Class> searchClassByName(String name);

	List<Class> sortClass(int index, String collumn,String key);
	
	List<Class> deleteClassById(int id);

}
