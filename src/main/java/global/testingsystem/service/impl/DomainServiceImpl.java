/**
 * 
 */
package global.testingsystem.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.testingsystem.entity.Domain;
import global.testingsystem.repository.DomainRepository;
import global.testingsystem.repository.SubjectRepository;
import global.testingsystem.service.DomainService;
import global.testingsystem.util.DomainConvert;
import global.testingsystem.util.FilterRegistration;

/**
 * @author USER
 *
 */
@Service
public class DomainServiceImpl implements DomainService {

	@Autowired
	private DomainRepository domainRepo;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private SubjectRepository repository;

	@Override
	public List<Object> getListDomain() {
		// TODO Auto-generated method stub
		return domainRepo.findAllDomain();
	}

	@Override
	public boolean editDomain(Domain domain) {
		// TODO Auto-generated method stub
		domainRepo.save(domain);
		return true;
	}

	@Override
	public boolean addDomain(Domain domain) {
		// TODO Auto-generated method stub
		domainRepo.save(domain);
		return true;
	}

	@Override
	public boolean deleteDomain(int id) {
		// TODO Auto-generated method stub
		domainRepo.deleteById(id);
		return true;
	}

	@Override
	public List<Object> searchDomainByName(String name) {
		// TODO Auto-generated method stub
		return domainRepo.searchDomainByName(name);
	}

	@Override
	public Domain findDomainByName(String name) {
		// TODO Auto-generated method stub
		return domainRepo.findDomainByName(name);
	}

	@Override
	public Domain finDomainById(int id) {
		// TODO Auto-generated method stub
		try {
			return domainRepo.findById(id).get();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Domain> sortDomainByName(String name) {
		// TODO Auto-generated method stub
		List<Domain> listDomain = domainRepo.findAll();
		try {
			if ("DESC".equals(name)) {
				listDomain.sort(Comparator.comparing(Domain::getName));
			} else {
				listDomain.sort(Comparator.comparing(Domain::getName).reversed());
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new LinkedList<Domain>();
		}
		return listDomain;
	}

	@Override
	public List<Domain> getListDomainBySubject(int idSubject) {
		// TODO Auto-generated method stub
		try {
			return domainRepo.getDomainBySubject(idSubject);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Domain> getListDomainByName(String name) {
		return domainRepo.getListDomainByName(name);
	}

	@Override
	public List<Integer> getListDomainIdBySubjectId(int subjectId) {
		return domainRepo.getListDomainIdBySubjectId(subjectId);
	}

	@Override
	public List<Domain> fillAll(FilterRegistration filter) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Domain> query = cb.createQuery(Domain.class);
		Root<Domain> root = query.from(Domain.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!StringUtils.isNotBlank(filter.getSortName())) {
			query.orderBy(cb.asc(root.get("id")));
		} else {
			query.orderBy(filter.getSort() ? (cb.asc(root.get(filter.getSortName())))
					: (cb.desc(root.get(filter.getSortName()))));
		}
		if (StringUtils.isNotBlank(filter.getKeyword())) {
			predicates.add(cb.like(root.get("name"), "%" + filter.getKeyword() + "%"));
		}

		query.where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Domain> typedQuery = entityManager.createQuery(query.select(root));

		return typedQuery.getResultList();
	}

	public void addDomainBySubjectId(Domain domain) {
		domain.setSubject(repository.getOne(domain.getSubject().getId()));
		domainRepo.save(domain);
	}

	public void deleteDomainBySubjectId(int id) {
		Domain domain = domainRepo.getOne(id);
		if (domain != null) {
			if (domain.getStatus() == 1) {
				domain.setStatus(0);
			} else {
				domain.setStatus(1);
			}
		}
		domainRepo.save(domain);
	}

	public void updateDomainBySubjectId(Domain domain) {
		Domain domain2 = domainRepo.getOne(domain.getId());
		if (domain2 != null) {
			domain2.setId(domain.getId());
			domain2.setName(domain.getName());
			domain2.setStatus(domain.getStatus());
			domain2.setSubject(repository.getOne(domain.getSubject().getId()));
			domainRepo.save(domain2);
		}
	}

	public boolean checkNameDomain(String oldName) {
		Domain domain = domainRepo.findByName(oldName);
		if (domain != null) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkNameDomainUpdate(Integer id, String oldName) {
		Domain domain = domainRepo.getOne(id);
		if (!domain.getName().equals(oldName)) {
			Domain domain2 = domainRepo.findByName(oldName);
			if (domain2 != null) {
				return false;
			} else {
				return true;
			}

		} else {
			return true;
		}

	}

	public List<DomainConvert> findBySubjectId(Integer id) {
		List<DomainConvert> domainConverts = new ArrayList<DomainConvert>();
		List<Domain> domains = domainRepo.findBySubject_Id(id);
		for (Domain domain : domains) {
			DomainConvert convert = new DomainConvert();
			convert.setId(domain.getId());
			convert.setName(domain.getName());
			convert.setStatus(domain.getStatus());
			convert.setSubjectId(domain.getSubject().getId());
			domainConverts.add(convert);
		}
		return domainConverts;
	}

}
