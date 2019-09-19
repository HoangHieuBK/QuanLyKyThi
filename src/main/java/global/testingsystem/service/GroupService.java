/**
 * 
 */
package global.testingsystem.service;

import java.util.List;

import global.testingsystem.entity.Group;

/**
 * @author USER
 *
 */
public interface GroupService {
    List<Group> list();
    
    List<Object> getlistGroup();
    
    Boolean insert(Group group);
    
    Boolean update(Group group);
    
    Boolean delete(int id);
    
    List<Object> searchGroupByName(String name);
    
    Group findGroupByName(String name);
    
    Group findById(int id);
    
    List<Group> sortGroupByName(String name);
    
    List<Integer> getAllParentId();
    
    List<Integer> getAllSubId(int parentId);
}
