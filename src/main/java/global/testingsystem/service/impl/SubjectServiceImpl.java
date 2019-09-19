/**
 * 
 */
package global.testingsystem.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.testingsystem.entity.Domain;
import global.testingsystem.entity.Subject;
import global.testingsystem.repository.SubjectRepository;
import global.testingsystem.service.SubjectService;

/**
 * @author admin
 *
 */
@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepo;

	@Override
	public List<Subject> getListSubject() {
		// TODO Auto-generated method stub
		return subjectRepo.list();
	}

	@Override
	public boolean editSubject(Subject subject) {
		// TODO Auto-generated method stub
		subjectRepo.save(subject);
		return true;
	}

	@Override
	public boolean addSubject(Subject subject) {
		// TODO Auto-generated method stub
		subjectRepo.save(subject);
		return true;
	}

	@Override
	public boolean deleteSubject(int id) {
		// TODO Auto-generated method stub
		subjectRepo.deleteById(id);
		return true;
	}

	@Override
	public List<Subject> searchByName(String name) {
		// TODO Auto-generated method stub
		try {
			List<Subject> result = new LinkedList<Subject>();
			List<Subject> listAll = subjectRepo.findAll();
			if (name == null && "".equals(name)) {
				return listAll;
			} else {
				for (Subject subject : listAll) {
					if (subject.getName().toLowerCase().contains(name.toLowerCase())) {
						result.add(subject);
					}
				}
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return new LinkedList<Subject>();
		}
	}

	@Override
	public Subject findSubjectByName(String name) {
		// TODO Auto-generated method stub
		return subjectRepo.findSubjectByName(name);
	}

	@Override
	public Subject findSubjectById(int id) {
		try {
			return subjectRepo.findById(id).get();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Subject> sortSubjectByName() {
		// TODO Auto-generated method stub
//                List<Subject> list = subjectRepo.findAll();
//                try {
//                        if ("DESC".equals(name)) {
//                                list.sort(Comparator.comparing(Subject:: getName));
//                        }else {
//                                list.sort(Comparator.comparing(Subject:: getName).reversed());
//                        }
//                } catch (Exception e) {
//                        // TODO: handle exception
//                        return new LinkedList<Subject>();
//                }
//                return list;
		return subjectRepo.findAllByOrderByNameAsc();
	}

	// Tu ***************************************************
	@Override
	public Subject getSubjectById(int idSubject) {
		// TODO Auto-generated method stub
		return subjectRepo.findById(idSubject).get();
	}

	@Override
	public String saveSubject(Subject sb) {
		// TODO Auto-generated method stub
		try {
			subjectRepo.save(sb);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Subject> getAllSubject() {

		return subjectRepo.findAll();
	}
	// Tu ***************************************************

	@Override
	public void deleteSubject(Integer subjectId) {
		Subject subject = subjectRepo.getOne(subjectId);
		if (subject != null) {
			if (subject.getStatus() == 0) {
				subject.setStatus(1);

			} else {
				subject.setStatus(0);
			}
			subjectRepo.save(subject);
		}
	}

	@Override
	public void updateSubjectByDomain(Subject subject) {
		Subject subject2 = subjectRepo.getOne(subject.getId());
		if (subject2 != null) {
			subject2.setId(subject.getId());
			subject2.setStatus(subject.getStatus());
			subject2.setName(subject.getName());
			subject2.setCreated_at(subject.getCreated_at());
			subject2.setUpdated_at(new Date());
			subjectRepo.save(subject2);
		}

	}

	@Override
	public boolean checkNamSubjectDoNotExit(Integer id, String name) {
		Subject subject = subjectRepo.getOne(id);
		if (!subject.getName().equals(name)) {
			Subject subject2 = subjectRepo.findSubjectByName(name);
			if (subject2 != null) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	@Override
	public Integer getSubjectByExamId(int id) {
		return subjectRepo.getSubjectByExamId(id);
	}

}
