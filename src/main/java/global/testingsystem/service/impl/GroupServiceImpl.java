package global.testingsystem.service.impl;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.testingsystem.entity.Group;
import global.testingsystem.repository.GroupRepository;
import global.testingsystem.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
        private static Logger log = Logger.getLogger(GroupServiceImpl.class);
        @Autowired
        private GroupRepository groupRepository;

        @Override
        public List<Group> list() {
                return groupRepository.getAll();
        }

        @Override
        public Boolean insert(Group group) {
                Group g = groupRepository.save(group);
                if (g != null)
                        return true;
                else {
                        log.error("Insert false ");
                        return false;
                }
        }

        @Override
        public Boolean update(Group group) {
                Group g = groupRepository.save(group);
                if (g != null)
                        return true;
                else {
                        log.error("Update false ");
                        return false;
                }
        }

        @Override
        public Boolean delete(int id) {

                try {
                        groupRepository.deleteById(id);
                        return true;
                } catch (Exception e) {
                        log.error("Delete false ");
                        return false;
                }

        }

        @Override
        public List<Object> searchGroupByName(String name) {
                // TODO Auto-generated method stub
           return groupRepository.searchGroupByName(name);
        }

        @Override
        public Group findGroupByName(String name) {
                return groupRepository.findGroupByName(name);
        }

        @Override
        public Group findById(int id) {
                // TODO Auto-generated method stub
                return groupRepository.getOne(id);
        }

        @Override
        public List<Group> sortGroupByName(String name) {
                // TODO Auto-generated method stub
                List<Group> listGroup = groupRepository.findAll();
                try {
                        if ("DESC".equals(name)) {
                                listGroup.sort(Comparator.comparing(Group:: getName));
                        }else {
                                listGroup.sort(Comparator.comparing(Group:: getName));
                        }
                } catch (Exception e) {
                        // TODO: handle exception
                }
                return listGroup;
        }

		@Override
		public List<Object> getlistGroup() {
			// TODO Auto-generated method stub
			return groupRepository.getListGroup();
		}

		@Override
		public List<Integer> getAllParentId() {
			// TODO Auto-generated method stub
			return groupRepository.getAllParentId();
		}

		@Override
		public List<Integer> getAllSubId(int parentId) {
			return groupRepository.getAllSubId(parentId);
		}

}