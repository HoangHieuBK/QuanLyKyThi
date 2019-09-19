package global.testingsystem.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.testingsystem.DTO.ExamDto;
import global.testingsystem.entity.Exam;
import global.testingsystem.entity.Exam_Setting;
import global.testingsystem.entity.Exam_User;
import global.testingsystem.entity.Group;
import global.testingsystem.entity.Question;
import global.testingsystem.entity.Subject;
import global.testingsystem.entity.Users;
import global.testingsystem.repository.ExamRepository;
import global.testingsystem.repository.ExamSettingRepository;
import global.testingsystem.repository.ExamUserRepository;
import global.testingsystem.repository.QuestionReposioty;
import global.testingsystem.service.ExamService;
import global.testingsystem.service.SubjectService;
import global.testingsystem.service.UsersService;

@Service
public class ExamServiceImpl implements ExamService {

	private ExamRepository examRepository;

	private SubjectService subjectService;

	private UsersService usersService;

	@Autowired
	private QuestionReposioty questionRepository;
	@Autowired
	private ExamSettingRepository examSettingRepository;
	@Autowired
	private ExamGroupServiceImpl examGroupService;
	@Autowired
	private ExamUserServiceImpl examUserService;
	@Autowired
	private GroupServiceImpl groupService;
	@Autowired
	private UsersServiceImpl userService;
	@Autowired
	private ExamUserRepository examUserRepository;

	@Autowired
	public ExamServiceImpl(ExamRepository examRepository, SubjectService subjectService, UsersService usersService) {
		this.examRepository = examRepository;
		this.subjectService = subjectService;
		this.usersService = usersService;
	}

	@Override
	public List<Object> list(String keySearch, String status) {
		try {
			return examRepository.list(keySearch, status);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(int id) {
		examRepository.deleteById(id);
		return true;
	}

	@Override
	public Exam insert(Exam exam) {
		try {
			Exam ex=examRepository.save(exam);
			return ex;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Exam insertGetId(Exam exam) {
		return examRepository.saveAndFlush(exam);
	
	}

	@Override
	public boolean update(Exam exam) {
		try {
			examRepository.save(exam);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Exam findById(int id) {
		return examRepository.findById(id).get();
	}

	@Override
	public Exam findLastId() {

		return examRepository.findFirstByOrderByIdDesc();
	}

	@Override
	public void deleteObjectInvite(String list, String type, int examId) {
		if ("group".equals(type)) {
			if ("".compareTo(list) != 0) {
				String[] groups = list.split(",");
				int number;
				for (String gr : groups) {
					try {
						number = Integer.parseInt(gr);
						examGroupService.deleteExamGroup(examId, number);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		} else {
			if ("".compareTo(list) != 0) {
				String[] users = list.split(",");
				int number;
				for (String u : users) {
					try {
						number = Integer.parseInt(u);
						examUserService.deleteExamUser(examId, number);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	@Override
	public boolean InsertObjectInvite(String list, String type, int examId) {
		Exam exam = examRepository.findById(examId).get();
		if ("group".equals(type)) {
			if ("".compareTo(list) != 0) {
				String[] groups = list.split(",");
				int number;
				for (String gr : groups) {
					try {
						number = Integer.parseInt(gr);
						Group group = groupService.findById(number);
						if (group != null)
							examGroupService.saveExamGroup(examId, number);
					} catch (Exception e) {
						return false;
					}

				}
				return true;
			}
			return true;
		} else {
			if ("".compareTo(list) != 0) {
				String[] users = list.split(",");
				int number;
				for (String u : users) {
					try {
						number = Integer.parseInt(u);
						Users user = userService.findById(number);
						if (user != null)
							examUserService.saveExamUser(exam, user);
					} catch (Exception e) {
						return false;
					}

				}
				return true;
			}
			return true;
		}
	}

// MR DUC
	@Override
	public List<Object> listPracticeHomepage() {
		return examRepository.listPracticeHomepage();
	}

	@Override
	public void readExcel(String excelFilePath) {
		final int COLUMN_INDEX_NAME = 0;
		final int COLUMN_INDEX_TITLE = 1;
		final int COLUMN_INDEX_CODE = 2;
		final int COLUMN_INDEX_DECRIPTION = 3;
		final int COLUMN_INDEX_MEDIA = 4;
		final int COLUMN_INDEX_START_DATE = 5;
		final int COLUMN_INDEX_END_DATE = 6;
		final int COLUMN_INDEX_TIME = 7;
		final int COLUMN_INDEX_CREATED_AT = 8;
		final int COLUMN_INDEX_UPDATED_AT = 9;
		final int COLUMN_INDEX_CREATE_TYPE = 10;
		final int COLUMN_INDEX_PERCENT_PASSING = 11;
		final int COLUMN_INDEX_MAX_ATTEMPT = 12;
		final int COLUMN_INDEX_STATUS = 13;
		final int COLUMN_INDEX_QUESTION_NUM = 14;
		final int COLUMN_INDEX_TYPE = 15;
		final int COLUMN_INDEX_CREATOR_ID = 16;
		final int COLUMN_INDEX_SUBJECT_ID = 17;
		try {

			InputStream inputStream = new FileInputStream(new File(excelFilePath));
//	 
			// Get workbook
			Workbook workbook = getWorkbook(inputStream, excelFilePath);
			// get sheet
			Sheet sheet = workbook.getSheetAt(0);
			// get all row
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row nextRow = (Row) iterator.next();
				if (nextRow.getRowNum() == 0) {
					continue;
				}
				// Gets all cell
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				// Read cells and set value for book object
				Exam exam = new Exam();
				while (cellIterator.hasNext()) {
					// Read cell
					Cell cell = (Cell) cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case COLUMN_INDEX_NAME:
						exam.setName((String) getCellValue(cell));
						break;
					case COLUMN_INDEX_TITLE:
						exam.setTitle((String) getCellValue(cell));
						;
						break;
					case COLUMN_INDEX_CODE:
						exam.setCode((String) getCellValue(cell));
						break;
					case COLUMN_INDEX_DECRIPTION:
						exam.setDecription((String) getCellValue(cell));
						break;
					case COLUMN_INDEX_MEDIA:
						exam.setMedia((String) getCellValue(cell));
						break;
					case COLUMN_INDEX_START_DATE:
						try {
							exam.setStart_date(formatter.parse((String) getCellValue(cell)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case COLUMN_INDEX_END_DATE:
						try {
							exam.setEnd_date(formatter.parse((String) getCellValue(cell)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case COLUMN_INDEX_TIME:
						exam.setTime(new BigDecimal((Double) cellValue).intValue());
						break;
					case COLUMN_INDEX_CREATED_AT:
						try {
							exam.setCreated_at(formatter.parse((String) getCellValue(cell)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case COLUMN_INDEX_UPDATED_AT:
						try {
							exam.setUpdated_at(formatter.parse((String) getCellValue(cell)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					case COLUMN_INDEX_CREATE_TYPE:
						exam.setCreate_type(Integer.parseInt((String) getCellValue(cell)));
						break;
					case COLUMN_INDEX_PERCENT_PASSING:
						exam.setPercent_passing(Integer.parseInt((String) getCellValue(cell)));
						break;
					case COLUMN_INDEX_MAX_ATTEMPT:
						exam.setMax_attempt(Integer.parseInt((String) getCellValue(cell)));
						break;
					case COLUMN_INDEX_STATUS:
						exam.setStatus(Integer.parseInt((String) getCellValue(cell)));
						break;
					case COLUMN_INDEX_QUESTION_NUM:
						exam.setQuestion_num(Integer.parseInt((String) getCellValue(cell)));
						break;
					case COLUMN_INDEX_TYPE:
						exam.setType(Integer.parseInt((String) getCellValue(cell)));
						break;
					case COLUMN_INDEX_CREATOR_ID:
						Users us = usersService.findById(Integer.parseInt((String) getCellValue(cell)));
						exam.setUsers(us);
						break;
					case COLUMN_INDEX_SUBJECT_ID:
						Subject sb = subjectService.findSubjectById(Integer.parseInt((String) getCellValue(cell)));
						exam.setSubject(sb);
						;
						break;
					default:
						break;
					}
				}
				examRepository.save(exam);
			}

		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Get cell value
	private static Object getCellValue(Cell cell) {
		CellType cellType = cell.getCellTypeEnum();
		Object cellValue = null;
		switch (cellType) {
		case BOOLEAN:
			cellValue = cell.getBooleanCellValue();
			break;
		case FORMULA:
			Workbook workbook = cell.getSheet().getWorkbook();
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			cellValue = evaluator.evaluate(cell).getNumberValue();
			break;
		case NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case _NONE:
		case BLANK:
		case ERROR:
			break;
		default:
			break;
		}

		return cellValue;
	}

	// Get Workbook
	private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	@Override
	public List<Object> listPractice(int user_id) {
//		return null;
		return examRepository.getListPractice(user_id);
	}

	public Exam getExamById(int id) {
		// TODO Auto-generated method stub
		return examRepository.findById(id).get();
	}

	@Override
	public List<Integer> getListQuestion(int id) {

		return examRepository.getListQuestion(id);
	}

	@Override
	public Exam getExamByIDS(int id) {
		return examRepository.getExamByIDS(id);
	}

	@Override
	public Object getExamByCode(String code) {
		return examRepository.getExamByCode(code);
	}

	@Override
	public List<Object> search(String key) {

		return examRepository.search(key);
	}

	@Override
	public List<Object> filterByType(String type) {

		return examRepository.filterByType(type);
	}
	// ngo minh anh
	@Override
	public List<Object> getExamBySubjectId(int subId) {

		return examRepository.getExamBySubjectId(subId);
	}
// trung 14/6

	@Override
	public List<Object> listExam(String keySearch, String type, String examMode) {
		// TODO Auto-generated method stub
		return examRepository.listExam(keySearch, type, examMode);
	}

	
	

	@Override
	public List<Object> ranDomOneExamBySubject(int subId) {
		return examRepository.ranDomOneExamBySubject(subId);
		}
	public Object getNumAndSubjectByExamId(int id) {
		return examRepository.getNumAndSubjectByExamId(id);
	}

	public List<Question> getListQuestionExam(int idExam) {
		Exam exam = examRepository.getOne(idExam);

		List<Question> result = new ArrayList<Question>();
		try {
			List<Exam_Setting> listExamSetting = examSettingRepository.getListExamSettingClass(idExam);
			if (listExamSetting != null && listExamSetting.size() > 0) {
				List<Integer> listIdQuestion = null;
				int subjectId = exam.getSubject().getId();
				String chapterId = null;
				String domainId = null;
				for (Exam_Setting setting : listExamSetting) {
					chapterId =setting.getChapter_id()==0?".*":("^"+setting.getChapter_id()+"$");
					domainId =setting.getDomain_id()==0?".*":("^"+setting.getDomain_id()+"$");
					listIdQuestion = questionRepository.getListQuestionRandom(subjectId,
							domainId, chapterId, setting.getPercentage());
					for (Integer num : listIdQuestion) {
						Question ques = questionRepository.findById(num.intValue()).get();
						result.add(ques);
					}
				}

			} else {
				List<Integer> listQuestion = this.getListQuestion(idExam);
				for (Integer s : listQuestion) {
					Question ques = questionRepository.findById(s.intValue()).get();
					if (ques != null)
						result.add(ques);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Question> getListQuestionExamDetail(int idExam, int examUserId) {
		List<Question> result=new ArrayList<Question>();
		Exam exam = examRepository.getOne(idExam);
		int createType = exam.getCreate_type();
		List<Integer> listQuestion=null;
		if(createType==1) {
			listQuestion = this.getListQuestion(idExam);
	    	for (Integer s : listQuestion) {
				Question ques = questionRepository.findById(s.intValue()).get();
				if (ques != null)
					result.add(ques);
			}
		}
		else {
			listQuestion = examRepository.getListQuestionResult(examUserId);
			for (Integer s : listQuestion) {
				Question ques = questionRepository.findById(s.intValue()).get();
				if (ques != null)
					result.add(ques);
			}
		}
		return result;

	}

	@Override
	public int importUserExam(int exam , String excelFilePath) {
		// Get workbook
		int count = 0;
		try {
			File file = new File(excelFilePath);
			InputStream inputStream = new FileInputStream(file);
			// Get workbook
			Workbook workbook = getWorkbook(inputStream, excelFilePath);
			// get sheet
			Sheet sheet = workbook.getSheetAt(0);
			// abc
			DataFormatter fmt = new DataFormatter();
			// get all row
			Iterator<Row> iterator = sheet.iterator();
			
			Row firstRow = iterator.next();
			Cell firstCell = firstRow.getCell(0);
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
			//	Users user = new Users();
			//	user = userService.findById(0);
				Exam_User eu = new Exam_User();
				String email = currentRow.getCell(1).getStringCellValue();
				eu.setEmail(email);
				String fullname = currentRow.getCell(2).getStringCellValue();
				eu.setFullName(fullname);
				String mobile = String.valueOf(currentRow.getCell(3).getNumericCellValue());
				eu.setMobile(mobile);
				//user = userService.findByEmail(email);

		        // chose a Character random from this String 
		        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		                                    + "0123456789"
		                                    + "abcdefghijklmnopqrstuvxyz"; 
		  
		        // create StringBuffer size of AlphaNumericString 
		        StringBuilder sb = new StringBuilder(6); 
		  
		        for (int i = 0; i < 6; i++) { 
		  
		            // generate a random number between 
		            // 0 to AlphaNumericString variable length 
		            int index 
		                = (int)(AlphaNumericString.length() 
		                        * Math.random()); 
		  
		            // add Character one by one in end of sb 
		            sb.append(AlphaNumericString 
		                          .charAt(index));   
		        } 
		        Exam exams = new Exam();
		        exams = examRepository.findById(exam).get();
		        eu.setExam(exams);
		        String code = sb.toString();
		        eu.setExamCode(code);
		       // eu.setUser(user);
		     examUserRepository.save(eu);
		       count++;
		    
			}
			file.delete();
			   return count;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return 0;
	}
	
	@Override
	public List<Exam> getExamByExamDemo() {
		
		return examRepository.getExamByExamDemo();
	}

	@Override
	public List<Exam> getExamByEntryTest() {
		
		return examRepository.getExamByEntryTest();
	}

	@Override
	public List<Exam> getExamByExamUser() {
		
		return examRepository.getExamByExamUser();
	}

	@Override
	public List<Exam> getExam() {
		
		return examRepository.getExam();
	}

	@Override

	public List<Exam> GetExamByExamMode(int exam_mode) {
		return examRepository.getExamByExamMode(exam_mode);
	}
	
	public List<Object> getExamUserByExamId(int id) {
		
		return examRepository.getExamUser(id);
	}

	@Override

	public List<Object> getExamCompletedByUser(int userId) {
		return examRepository.getExamCompletedByUser(userId);
	}

	public ExamDto findByExamIdAndUserId(Integer examId, Integer userId) {
		Exam exam = examRepository.findByIdAndUsers_Id(examId, userId);
		ExamDto examDto = new ExamDto();
		examDto.setId(exam.getId());
		examDto.setQuestionNum(exam.getQuestion_num());
		examDto.setStartDate(exam.getStart_date());
		examDto.setEndDate(exam.getEnd_date());
		examDto.setCreatedAt(exam.getCreated_at());
		examDto.setName(exam.getName());
		examDto.setTitle(exam.getTitle());
		return examDto;
	}


}