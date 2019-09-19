/**
 * 
 */
package global.testingsystem.service;

import java.util.List;

import global.testingsystem.entity.Domain;
import global.testingsystem.util.FilterRegistration;

/**
 * @author USER
 *
 */
public interface DomainService {
    List<Object> getListDomain();
    
    List<Domain> fillAll(FilterRegistration filter);
    
    boolean editDomain(Domain domain);
    
    boolean addDomain(Domain domain);
    
    boolean deleteDomain(int id);
    
    List<Object> searchDomainByName(String name);
    
    Domain findDomainByName(String name);
    
    Domain finDomainById(int id);
    
    List<Domain> sortDomainByName(String name);
    
    List<Domain> getListDomainBySubject(int idSubject);
    
    List<Domain> getListDomainByName(String name);
    
    List<Integer> getListDomainIdBySubjectId(int subjectId);

}
