package global.testingsystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import global.testingsystem.entity.Class;
import global.testingsystem.entity.Users;
import global.testingsystem.service.ClassService;
import global.testingsystem.service.UsersService;
import global.testingsystem.util.ConstantPage;

@RestController
@CrossOrigin(origins = "*")
public class ClassController {

	@Autowired
	private ClassService classService;
	@Autowired
	private UsersService usersService;

	
	@GetMapping(value = ConstantPage.REST_API_GET_ALL_CLASS)
	public List<Class> getAll(@PathVariable("userid") int userid) {
		return classService.getAllClass(userid);
	}

	@GetMapping(value = ConstantPage.REST_API_GET_CLASS_DETAIL, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public List<Class> getClassById(@PathVariable int id) {
		return classService.getClassById(id);
	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE_CLASS, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updateClass(@RequestParam(required = false) String formData) {
		System.out.println(formData);
		JSONObject jsonobject = new JSONObject(formData);
		int id = jsonobject.getInt("id");
		String name = jsonobject.getString("name");
		// int subject_id = jsonobject.getInt("subject_id");
		int status = jsonobject.getInt("status");
		int manager = jsonobject.getInt("manager_id");
		// int managerid = manager.getInt("id");

		Users user = usersService.findById(manager);
		// String note = jsonobject.getString("note");
		String trainer = jsonobject.getString("trainner_id");
		Class updateclass = classService.findById(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			JSONObject date = jsonobject.getJSONObject("date");
			updateclass.setStartDate(sdf.parse(date.getString("start_date")));
			updateclass.setEndDate(sdf.parse(date.getString("end_date")));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		updateclass.setClassId(id);
		updateclass.setName(name);
		updateclass.setManager(user);
		updateclass.setTrainer(trainer);
		updateclass.setIsDefault(status);
		updateclass.setTrainer(trainer);
		boolean isSuccess = classService.update(updateclass);

		return new ResponseEntity<Object>(isSuccess, HttpStatus.OK);
	}

	@PostMapping(value = ConstantPage.REST_API_CREAT_CLASS, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> createClass(@RequestParam(required = false) String formData) {
		System.out.println(formData);
		JSONObject jsonobject = new JSONObject(formData);
		String name = jsonobject.getString("name");
		// int subject_id = jsonobject.getInt("subject_id");
		int status = jsonobject.getInt("status");
		int manager = jsonobject.getInt("manager_id");
		// int managerid = manager.getInt("id");
		Users user = usersService.findById(manager);
		// String note = jsonobject.getString("note");
		String trainer = jsonobject.getString("trainner_id");
		Class updateclass = new Class();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			JSONObject date = jsonobject.getJSONObject("date");
			updateclass.setStartDate(sdf.parse(date.getString("start_date")));
			updateclass.setEndDate(sdf.parse(date.getString("end_date")));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		updateclass.setName(name);
		updateclass.setManager(user);
		updateclass.setTrainer(trainer);
		updateclass.setIsDefault(status);
		updateclass.setTrainer(trainer);
		boolean isSuccess = classService.create(updateclass);

		return new ResponseEntity<Object>(isSuccess, HttpStatus.OK);
	}

	List<Class> getNameOfTrainers(List<Class> listClass) {
		for (Class c : listClass) {
			String nameTrainer = "";
			String trainer = c.getTrainer();
			String[] arr = trainer.split(",");
			for (String i : arr) {
				Integer id = Integer.parseInt(i);
				Users user = usersService.findById(id);
				nameTrainer = nameTrainer + user.getFullname() + ",";
			}

			nameTrainer = nameTrainer.substring(0, nameTrainer.length() - 1);
			c.setTrainer(nameTrainer);
		}
		return listClass;
	}

	@GetMapping(value = ConstantPage.REST_API_GET_LIST_CLASS)

	List<Class> getAllClass() {

		List<Class> listClass = classService.getAllClass();

		return getNameOfTrainers(listClass);
	}

	@GetMapping(value = ConstantPage.REST_API_SEARCH_CLASS)
	List<Class> searchClass(@RequestParam String name) {
		List<Class> listClass = classService.searchClassByName(name);
		return getNameOfTrainers(listClass);
	}

//	@GetMapping(value = ConstantPage.REST_API_SORT_CLASS)
//	List<Class> searchClass(@RequestParam int index, @RequestParam String collumn) {
//		List<Class> listClass = classService.sortClass(index, collumn);
//		return getNameOfTrainers(listClass);
//	}

	@PostMapping(value = ConstantPage.REST_API_UPDATE_CLASS_STATUS, produces = {
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public boolean changeClassStatus(@PathVariable int classid) {
		Class cl = classService.getClassByClassId(classid);
		cl.setIsDefault((cl.getIsDefault() + 1) % 2);
		return classService.update(cl);
	}

	@GetMapping(value = ConstantPage.REST_API_SORT_CLASS)
	List<Class> sortClass(@RequestParam int index, @RequestParam String collumn, @RequestParam String key) {
		List<Class> listClass = classService.sortClass(index, collumn,key);
		return getNameOfTrainers(listClass);
	}

	@DeleteMapping(value = ConstantPage.REST_API_DELETE_CLASS, produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public List<Class> deleteClassById(@PathVariable int classid) {
		List<Class> listClass = classService.deleteClassById(classid);
		return getNameOfTrainers(listClass);
	}

}
