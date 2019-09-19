package global.testingsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.testingsystem.entity.Class;
import global.testingsystem.repository.ClassRepository;
import global.testingsystem.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassRepository classRepository;


	@Override
	public List<Class> getAllClass(int userid) {
		// TODO Auto-generated method stub
		return classRepository.getListClass(userid);
	}

	@Override
	public List<Class> getClassById(int id) {
		// TODO Auto-generated method stub
		List<Class> a = new ArrayList<Class>();
		try {
			a = classRepository.getClassById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public Class findById(int id) {
		// TODO Auto-generated method stub
		return classRepository.getOne(id);
	}

	@Override
	public boolean update(Class classes) {
		Class g = classRepository.save(classes);
		if (g != null)
			return true;
		else {
			return false;
		}

	}
	
	
//	trinh van tai
	@Override
	public List<Class> getAllClass() {
		// TODO Auto-generated method stub
		return classRepository.getAllClass();
	}

	@Override
	public List<Class> searchClassByName(String name) {
		// TODO Auto-generated method stub
		return classRepository.searchClassByName(name);
	}

	@Override
	public List<Class> sortClass(int index, String collumn, String key) {
		if (index % 2 == 0) {
			if (collumn.equalsIgnoreCase("id")) {
				return classRepository.sortAscClassById(key);
			} else if (collumn.equalsIgnoreCase("name")) {
				return classRepository.sortAscClassByName(key);
			} else if (collumn.equalsIgnoreCase("start_date")) {
				return classRepository.sortAscClassByStartDate(key);
			} else {
				return classRepository.sortAscClassEndDate(key);
			}
		} else {
			if (collumn.equalsIgnoreCase("id")) {
				return classRepository.sortDescClassById(key);
			} else if (collumn.equalsIgnoreCase("name")) {
				return classRepository.sortDescClassByName(key);
			} else if (collumn.equalsIgnoreCase("start_date")) {
				return classRepository.sortDescClassByStartDate(key);
			} else {
				return classRepository.sortDescClassEndDate(key);
			}
		}
	}

	@Override
	public List<Class> deleteClassById(int id) {
		// TODO Auto-generated method stub
		classRepository.deleteById(id);
		return classRepository.getAllClass();
	}

	@Override
	public Class getClassByClassId(int id) {
		// TODO Auto-generated method stub
		return classRepository.findOneByClassId(id);
	}
	
	public boolean create(Class classes) {
		Class g = classRepository.save(classes);
		if (g != null)
			return true;
		else {
			return false;
		}
	}

}
