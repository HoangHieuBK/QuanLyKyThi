package global.testingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import global.testingsystem.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
	Permission findByControllerAndAction(String controller, String action);

	@Query(value = "select *from permission where name =:name", nativeQuery = true)
	Permission getPermissionByName(@Param("name") String namePermission);

	List<Permission> findByNameContainingOrControllerContainingOrActionContaining(String name, String controller,
			String action);
}
