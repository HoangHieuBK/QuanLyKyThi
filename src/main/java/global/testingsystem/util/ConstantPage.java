/**
 * 
 */
package global.testingsystem.util;

/**
 * @author NATUAN3
 * @created date Nov 21, 2018
 * @modified date Nov 21, 2018
 * @verson 1.0
 * @description
 */
public class ConstantPage {

	// BASE URL
	public static final String BASE_URL_CLIENT = "http://localhost:4200";
	// SERVER
//	public static final String BASE_URL_CLIENT = "http://192.168.10.133:81";
	// SERVER
//	public static final String BASE_URL_CLIENT = "http://45.122.253.18:83";
	public static final String REST_API_GET_ASSIGNMENT_OF_TRAINE = "/traineeAssignment/{id}/assigment";
//    CMC job
	public static final String REST_API_GET_ALL_JOB = "/job/list";
	public static final String REST_API_INSERT_JOB = "/job/insert";
	public static final String REST_API_DELETE_JOB_BY_ID = "/job/delete/{id}";
	public static final String REST_API_UPDATE_JOB = "/job/update";
	public static final String REST_API_FILTER_JOB_BY_NAME = "/job/filter/{name}";
	public static final String REST_API_UPLOAD_IMAGE_JOB = "/job/upload-image";
	public static final String REST_API_GET_ALL_JOB_ACTIVE = "/job/list/active";
	public static final String REST_API_SEARCH_JOB_BY_ID = "/job/search/{id}";

//    menu
	public static final String REST_API_GET_ALL_MENU = "/menu/list";
	public static final String REST_API_DELETE_MENU_BY_ID = "/menu/delete/{id}";
	public static final String REST_API_UPDATE_MENU = "/menu/update";
	public static final String REST_API_INSERT_MENU = "menu/insert";
	public static final String REST_API_LIST_MENU_TREE = "/menu/listMenuTree";
	public static final String REST_API_LIST_MENU_USER = "/menu/listMenuUser";
	public static final String REST_API_GET_PARENT_NAME = "/menu/parentname";
	public static final String REST_API_SEARCH_MENU_BY_NAME = "/menu/search";

	// news
	public static final String REST_API_GET_ALL_NEW = "/news/list";
	public static final String REST_API_DELETE_NEW_BY_ID = "/news/delete/{newId}";
	public static final String REST_API_UPDATE_NEW = "/news/update/{newsId}";
	public static final String REST_API_INSERT_NEW = "/news/insert";
	public static final String REST_API_SEARCH_NEW = "/news/search";
	public static final String REST_API_SEARCH_NEW_CMS = "/news/searchnewscms";
	public static final String REST_API_SORT_NEW = "/new/sort";
	public static final String REST_API_FIND_NEW_BY_ID = "/news/{newId}";
	public static final String REST_API_PIN_NEW = "/news/pin/{newsId}";
	public static final String REST_API_HOMEPAGE_NEW = "/news/newspage";

	public static final String REST_API_SEARCH_PRODUCT_NEW = "/new/search/{keyword}";

	public static final String REST_API_GET_ALL_PAGENEWS_NEWS = "/news/pagenewsnews";
	public static final String REST_API_GET_ALL_PINNED_NEWS = "/news/pinnednews";

	public static final String REST_API_UPDATE_NEWS_ACTIVE_STATUS = "/news/activestatus/{status}/{news_id}";
	// permission
	public static final String REST_API_GET_ALL_PERMISSION = "/permission/list";
	public static final String REST_API_GET_ALL_CONTROLLER_PERMISSION = "/permission/listController";
	public static final String REST_API_GET_ALL_ACTION_PERMISSION = "/permission/listAction";
	public static final String REST_API_DELETE_PERMISSION_BY_ID = "/permission/delete/{id}";
	public static final String REST_API_UPDATE_PERMISSION = "/permission/update";
	public static final String REST_API_INSERT_PERMISSION = "/permission/insert";
	public static final String REST_API_SEARCH_PERMISSION = "/permission/search";

	// Role
	public static final String REST_API_GET_ALL_ROLE = "/role/list";
	public static final String REST_API_DELETE_ROLE_BY_ID = "/role/delete/{id}";
	public static final String REST_API_UPDATE_ROLE = "/role/update";
	public static final String REST_API_INSERT_ROLE = "/role/insert";
	public static final String REST_API_SORT_ROLE = "/role/sort";

	public static final String REST_API_SEARCH_KEY_ROLE = "role/search";
	public static final String REST_API_SORT_ROLE_KEY = "/role/sort/{key}";
	public static final String REST_API_ROLE_PERMISSION = "/role/addRolePermission";
	//// Lâm
	public static final String REST_API_SEARCH_ROLE_PERMISSION = "/role/searchRolePermission";
	/////////

//  user
	public static final String REST_API_ACTIVE_USER = "/active";
	public static final String REST_API_ACTIVE_FORGET_PASSWORD = "/activeforgotpass";
	public static final String REST_API_GET_USER_BY_ID = "/users/{userId}";
	public static final String REST_API_PROFILE_USER = "/profileusers";
	public static final String REST_API_ROLE_USER = "/users/addUserRole";
	public static final String REST_API_ALL_ROLE_USER = "/users/getAllUserRole";
	public static final String REST_API_REMOVE_ROLE_USER = "/users/removeUserRole";
	public static final String REST_API_LIST_EXAM_OF_USER = "/users/listexamofuser";
	public static final String REST_API_DETAIL_USER = "/userdetail";
	public static final String REST_API_LIST_EXAM_COMPLETE = "/users/listExamUserCompleted/{userId}";
	public static final String REST_API_LIST_PRACTICE_COMPLETE = "/users/listPracticeUserCompleted/{userId}";
	public static final String REST_API_LIST_PRACTICE_BY_USER = "/users/listpracticeofuser";
	public static final String REST_API_ACTIVE_ACCOUNT = "/active_account";
	public static final String REST_API_LOGIN_USER = "/login";
	public static final String REST_API_LOGOUT_USER = "/user/logout";
	public static final String REST_API_COMPLETE_LIST_USER = "/users/listUserComplete";
	public static final String REST_API_INCOMPLETE_LIST_USER = "/users/listUserInComplete";
	public static final String REST_API_SEARCH_USER_ROLE = "/user/search";

	public static final String REST_API_GET_ALL_USERS = "/users/list";
	public static final String REST_API_DELETE_USERS_BY_ID = "/users/delete/{id}";
	public static final String REST_API_SEARCH_USERS = "/users/search";
	public static final String REST_API_SORT_USERS = "/users/sort";
	public static final String REST_API_INSERT_USERS = "/users/insert";
	public static final String REST_API_UPDATE_USERS = "/users/update";
	public static final String REST_API_UPDATE_USERS_ACTIVE = "/users/update/status";
	// slidebanner
	public static final String REST_API_SEARCH_KEY_SLIDEBANNER = "slidebanner/search";

	public static final String REST_API_GET_ALL_SLIDEBANNER = "/slidebanner/list";
	public static final String REST_API_DELETE_SLIDEBANNER_BY_ID = "/slidebanner/delete/{id}";
	public static final String REST_API_FILTER_SLIDEBANNER_BY_TITLE = "/slidebanner/list/filter/{title}";
	public static final String REST_API_INSERT_SLIDEBANNER = "/slidebanner/insert";
	public static final String REST_API_UPDATE_SLIDEBANNER = "/slidebanner/update";
	public static final String REST_API_GET_ALL_SLIDEBANNER_ACTIVE = "/slidebanner/list/active";
	public static final String REST_API_UPDATE_SLIDEBANNER_ACTIVE = "/slidebanner/update/status";

//	TAG
	public static final String REST_API_GET_ALL_TAG = "/tag/list";

	public static final String PATH_SAVE_EXAM_UPLOAD = "resources/images/exam/";
	public static final String PATH_SAVE_SLIDEBANNER_UPLOAD = "resources/images/slidebars/";
	public static final String PATH_SAVE_QUESTION_UPLOAD = "resources/images/question/";
	public static final String PATH_SAVE_USER_UPLOAD = "resources/images/user/";
	public static final String PATH_SAVE_THUMBNAIL_UPLOAD = "resources/images/thumbnail/";
	public static final String REST_API_REGISTER = "/registration";
	public static final String REST_API_FORGOT_PASSWORD = "/forgotpass";
	public static final String REST_API_CHANGE_PASSWORD = "/changepassword";
	public static final String REST_API_USER_PROFILE = "/user-profile/{id}";
	public static final String REST_API_CHANGE_PROFILE = "/user/changeprofile";
	public static final String REST_API_CHANGE_PROFILE_NO_IMAGE = "/user/changeprofile/noimage";

	public static final String MY_EMAIL = "testingcmcglobal@gmail.com";
	public static final String MY_PASSWORD = "cmcglobal123";

	// SUBJECT
	public static final String REST_API_GET_ALL_SUBJECT = "/subject/list";
	public static final String REST_API_INSERT_SUBJECT = "/subject/insert";
	public static final String REST_API_UPDATE_SUBJECT = "/subject/update";
	public static final String REST_API_DELETE_SUBJECT_BY_ID = "/subject/delete/{id}";
	public static final String REST_API_SEARCH_SUBJECT = "/subject/search";
	public static final String REST_API_SORT_SUBJECT_BY_NAME = "/subject/sort";

	// SUBJECT MR DUC
	public static final String REST_API_GET_SUBJECT_BY_ID = "/subject";

	// CHAPTER
	public static final String REST_API_GETCHAPTERBYSUBJECT = "/chapter/getChapterBySubject/{idSubject}";
	public static final String REST_API_GETCHAPTERBYSUBJECT_AND_PARENT = "/chapter/getChapterBySubjectAndParent/{idSubject}";
	public static final String REST_API_GET_ALL_CHAPTER = "/chapter/list";
	public static final String REST_API_INSERT_CHAPTER = "/chapter/insert";
	public static final String REST_API_UPDATE_CHAPTER = "/chapter/update";
	public static final String REST_API_DELETE_CHAPTER_BY_ID = "/chapter/delete/{id}";
	public static final String REST_API_SEARCH_CHAPTER = "/chapter/search";
	public static final String REST_API_SORT_CHAPTER = "/chapter/sort";
	public static final String REST_API_CHAPTER_BY_SUBJECT_ORDER_ASC = "/chapter/listChapterBySubjectAndOrder/{subjectId}";
	public static final String REST_API_CHAPTER_BY_SUBJECT_PARENT_ORDER_ASC = "/chapter/listChapterBySubjectAndParentAndOrder/{subjectId}/{parentId}";

	// create section từ chapter
	public static final String REST_API_CREATE_CHAPTER = "/chapter/create";
	public static final String REST_API_CREATE_BY_CHAPTER = "/chapter/create2";
	public static final String REST_API_GET_ALL_CHAPTER_PARENT = "/chapter/listchapter-parent";
	public static final String REST_API_GET_CHAPTER_DETAIL = "/chapter/getChapterById/{id}";
	public static final String REST_API_GET_ALL_CHAPTER2 = "/chapter/list2";
	public static final String REST_API_GETCHAPTER_BY_PARENT = "/chapter/getParent/{parentId}";
	public static final String REST_API_UPDATE2_CHAPTER = "/chapter/update2";
	public static final String REST_API_UPDATE3_CHAPTER = "/chapter/update3";
	public static final String REST_API_DELETE_CHAPTER_BY_PARENTID = "/chapter/delete2/{id}/{parentId}";

	// DOMAIN
	public static final String REST_API_GETDOMAINBYSUBJECT = "/domain/getDomainBySubject/{idSubject}";
	public static final String REST_API_GET_ALL_DOMAIN = "/domain/list";
	public static final String REST_API_INSERT_DOMAIN = "/domain/insert";
	public static final String REST_API_UPDATE_DOMAIN = "/domain/update";
	public static final String REST_API_DELETE_DOMAIN_BY_ID = "/domain/delete/{id}";
	public static final String REST_API_SEARCH_DOMAIN = "/domain/search";
	public static final String REST_API_SORT_DOMAIN = "/domain/sort";

	// GROUP
	public static final String REST_API_GET_ALL_GROUP = "/group/list";
	public static final String REST_API_GET_ALL2_GROUP = "/group/list2";
	public static final String REST_API_UPDATE_GROUP = "/group/update";
	public static final String REST_API_DELETE_GROUP_BY_ID = "/group/delete/{id}";
	public static final String REST_API_INSERT_GROUP = "/group/insert";
	public static final String REST_API_SEARCH_GROUP = "/group/search";
	public static final String REST_API_GET_GROUP_BY_ID = "/group/list/{id}";
	public static final String REST_API_SORT_GROUP = "/group/sort";

	// EXAM
	public static final String REST_API_GET_ALL_EXAM = "/exam/list";
	public static final String REST_API_GET_ALL_LIST_EXAM = "/exam/listExam";
	public static final String REST_API_UPDATE_STATUS_EXAM = "/exam/updateStatus";
	public static final String REST_API_UPDATE_DESCRIPTION_EXAM= "/exam/update/description";
	public static final String REST_API_UPDATE_EXAM = "/exam/update";
	public static final String REST_API_INSERT_EXAM = "/exam/insert";
	public static final String REST_API_UPDATE_FILE_EXAM = "/exam/updateFile";
	public static final String REST_API_GET_LIST_USER_BY_EXAM_ID = "/exam/listUserExam/{id}";
	public static final String REST_API_GET_LIST_GROUP_BY_EXAM_ID = "/exam/listGroupExam/{id}";
	public static final String REST_API_GET_LIST_QUESTION_BY_EXAM_ID = "/exam/listQuestion/{id}";
	public static final String REST_API_GET_EXAM_BY_EXAM_ID = "/exam/findByID/{id}";
	public static final String REST_API_SEARCH_EXAM = "/exam/search";
	public static final String REST_API_INSERT_PRACTISE = "/exam/insertPractise";
	public static final String REST_API_UPDATE_EXAM_SERVICE = "/exam/updateExamService";
	public static final String REST_API_ADD_EXAMRANDOM = "/exam/addExamRandom";
	public static final String REST_API_GET_LIST_EXAMSETTING = "/exam/getListExamSetting/{idExam}";
	public static final String REST_API_GET_LIST_EXAMRESULT = "/exam/getListExamResult/{idExam}";
	public static final String REST_API_GET_LIST_EXAM_BY_SUBJECT_ID = "/exam/getListExamBySubjectId/{subId}";

	public static final String REST_API_INSERT_USER_EXAM= "/exam/userExam";
	public static final String REST_API_GET_NUM_AND_SUBJECT_BY_EXAM_ID = "/exam/getNumAndSubject/{id}";


	public static final String REST_API_INSERT_EXAM_SETTING = "/exam/insertExamSetting";
	
	public static final String REST_API_IMPORT_EXAM_USER= "exam/importExamUser";

	public static final String REST_API_GET_ONE_EXAM_BY_SUBJECT = "/exam/exam/{subId}";


	//minh anh
	public static final String REST_API_ADD_USER_IN_EXAM_DEMO = "/exam/updatedemo";
	


	// QUESTION
	public static final String REST_API_GET_ALL_QUESTION = "/question/list";
	public static final String REST_API_GET_QUESTION_BY_ID = "/question/getQuestion/{id}";
	public static final String REST_API_INSERT_QUESTION = "/question/addQuestion";
	public static final String REST_API_UPDATE_QUESTION = "/question/updateQuestion";
	public static final String REST_API_DELETE_QUESTION = "/question/deleteQuestion/{id}";
	public static final String REST_API_GET_LIST_QUESTION_OF_SUBJECT = "/question/getListQuestionOfSubject";
	public static final String REST_API_GET_LIST_QUESTION_OF_CHAPTER = "/question/getListQuestionOfChapter/{idChapter}";
	public static final String REST_API_GET_LIST_QUESTION_OF_DOMAIN = "/question/getListQuestionOfDomain/{idDomain}";
	public static final String REST_API_GET_LIST_QUESTION_OF_EXAM = "/question/getQuestionByExamId/{examId}";
	public static final String REST_API_GET_LIST_REST_QUESTION = "/question/getListRestQuestion";
	public static final String REST_API_GET_LIST_REST_QUESTION_RANDOM = "/question/getListRestQuestionRandom";
	public static final String REST_API_GET_LIST_QUESTION_RANDOM_BY_CHAPTER_DOMIAN = "/question/getListQuestionRandomByChapTerAndDomain";
	public static final String REST_API_GET_NUMBER_QUESTION_OF_CHAPTER_DOMAIN = "/question/getNumberQuestionOfChapterAndDomain";
	public static final String REST_API_IMPORT_EXEL = "/question/importExcel";
	public static final String REST_API_DOWLOAD_EXEL = "/question/downloadFileExcel";
	public static final String REST_API_DOWLOAD_TXT = "/question/downloadFileTxt";
	public static final String REST_API_DOWNLOAD_FILE_EXCEL_USER_EXAM = "/user_exam/downloadFileExcel";
	public static final String REST_API_GET_NUMBER_QUESTION_OF_CHAPTER = "/question/getNumberQuestionOfChapter";
	public static final String REST_API_GET_NUMBER_QUESTION_OF_DOMAIN = "/question/getNumberQuestionOfDomain";
	public static final String REST_API_COUNT_SEARCH_QUESTION_BY_SUBJECT = "/question/countSearchQuestion";

	// EXAM MR DUC
	public static final String REST_API_GET_PRACTICE = "/practice";
	public static final String REST_API_GET_PRACTICE_HOMEPAGE = "/practice/pacticehomepage";
	public static final String REST_API_GET_EXAM_BY_IDS = "/exams";
	public static final String REST_API_GET_EXAM_ASC_BY_END_DATE = "/exams_asc";

	// CUSTOMER
	public static final String REST_API_GET_ALL_CUSTOMER = "/customer/list";
	public static final String REST_API_INSERT_CUSTOMER = "/customer/insert";
	public static final String REST_API_DELETE_CUSTOMER_BY_ID = "/customer/delete/{id}";
	public static final String REST_API_SEARCH_CUSTOMER = "/customer/search";
	public static final String REST_API_SORT_CUSTOMER_BY_NAME = "/customer/sort";

	// Registrantion
	public static final String REST_API_LIST_REGISTRANTION = "list-registrantion";
	public static final String REST_API_GET_REGISTRANTION = "get-registrantion/{registrantionId}";
	public static final String REST_API_UPDATE_REGISTRANTION_STATUS = "/update-status";
	public static final String REST_API_UPDATE_REGISTRANTION = "/update-registrantion/{registrantionId}";
	public static final String REST_API_ADD_REGISTRANTION = "/add-registrantion";

	// EXAM USER
	public static final String REST_API_START_EXAM = "/examUser/start";
	public static final String REST_API_UPDATE_EXAM_RESULT = "/examUser/update";
	public static final String REST_API_UPDATE_COMPLETE_RESULT = "/examUser/updateComplete";
	public static final String REST_API_UPDATE_TIME_RESULT = "/examUser/updateTime";
	public static final String REST_API_GET_FREE_TEST_RESULT = "/examUser/getFreeTestResult";
	// EXAM ANSWER
	public static final String REST_API_INSERT_EXAM_ANSWER = "examAnswer/insert";
	public static final String REST_API_DELETE_EXAM_ANSWER = "examAnswer/delete";
	public static final String REST_API_UPDATE_EXAM_ANSWER = "examAnswer/update";
	public static final String REST_API_LIST_QUESTION_EXAM_BY_RESULT_ID = "examAnswer/listQuestionResult/{id}";
	public static final String REST_API_GET_LIST_QUESTION_EXAM_DETAIL = "/exam/listQuestionExamDetail";
	// QUESTION
	public static final String REST_API_SEARCH_QUESTION = "question/search";

	// CONTENTTYPE
	public static final String REST_API_GET_ALL_CONTENT_TYPE = "contenttype/list";
	// PRODUCT
	public static final String REST_API_LIST_PRODUCT = "product/all";
	public static final String REST_API_CREATE_PRODUCT = "product/create";
	public static final String REST_API_EDIT_PRODUCT = "product/edit";
	public static final String REST_API_DELETE_PRODUCT = "product/delete/{id}";
	public static final String REST_API_SEARCH_PRODUCT = "product/search";
	public static final String REST_API_GET_PRODUCT_BY_ID = "/product/{id}";
	public static final String REST_API_GET_PRODUCT_PRACTICE_COMPLETED = "/product/listpracticecomplete/{userid}";
	public static final String REST_API_GET_LIST_PRODUCT_BY_CONTENTTYPE = "/product/catalog/{contentType}";

	// CLASS
	public static final String REST_API_GET_ALL_CLASS = "/class/list/{userid}";
//	public static final String REST_API_GET_ALL_CLASS = "/class/list";

	public static final String REST_API_GET_CLASS_DETAIL = "/class/detail/{id}";

	public static final String REST_API_UPDATE_CLASS = "class/update";

	public static final String REST_API_GET_LIST_CLASS = "/class/list";
	public static final String REST_API_SEARCH_CLASS = "/class/search";
	public static final String REST_API_SORT_CLASS = "/class/sort";
	public static final String REST_API_DELETE_CLASS = "/class/delete/{classid}";

	public static final String REST_API_UPDATE_CLASS_STATUS = "/class/update/status/{classid}";

	public static final String REST_API_CREAT_CLASS = "/class/create";

	public final static String REST_API_SEARCH_KEY_PRODUCT = "product/searchcontent";
	public final static String REST_API_SEARCH_KEY_PRODUCT_Content = "/search/{keyword}";

	/// thuy
	public static final String REST_API_GET_PRODUCT_BY_TYPE = "product/listCourse";
	// linh
	public static final String REST_API_LIST_PRACTICE_COMPLETED_BY_USER = "/users/listPracticeCompletedByUser/{userId}";
	public static final String REST_API_GET_PRODUCT_EXAM_COMPLETED = "/product/listexamcomplete/{userid}";
	public static final String REST_API_GET_PRODUCT_COMING_SOON = "/product/listProductComingSoon/{userid}";
	public static final String REST_API_GET_EXAM_RESULT_PRACTICE = "/examUser/listExamResultPractice";
	public static final String REST_API_COPY_CHAPTER_AND_UNIT = "/chapter/copyChapterAndUnit/{chapterId}/{unitId}";
	public static final String REST_API_GET_EXAM_COMPLETED_BY_USER = "/examresult/listexamcomplete/{userid}";

	// UNIT
	public static final String REST_API_LIST_UNIT = "unit/list";
	public static final String REST_API_CREATE_UNIT = "unit/create";
	public static final String REST_API_EDIT_UNIT = "unit/edit";
	public static final String REST_API_DELETE_UNIT = "unit/delete/{id}";
	public static final String REST_API_SEARCH_UNIT = "unit/search";
	public static final String REST_API_GET_UNIT_BY_ID = "/unit/{id}";
	public static final String REST_API_GET_LIST_UNIT_BY_CHAPTER_ORDER_ASC = "/unit/getListUnitByChapter/{chapterId}";
	public static final String REST_API_MOVE_UNIT_AND_CHAPTER = "/unit/moveUnitAndChapter";
	public static final String PATH_SAVE_DOCUMENT_UPLOAD = "resources/book/";

	// nmanh

	public static final String REST_API_GET_LIST_ALL_TRAINEE_ASSIGNMENT = "/traineeAssignment/list";
	public static final String DATE_FORMAT_NOW = "dd/MM/yyyy HH:mm:ss";
	// nmanh

	public static final String REST_API_GET_LIST_TRAINEE_ASSIGNMENT_BY_CLASS_ID = "/traineeAssignment/list/{classId}";
	public static final String REST_API_GET_TRAINEEASSIGNMENT_BY_ClassLesson_ID = "/traineeAssignment/getListTraineeAssimentByClassLessonId/{classId}/{traineeassignmentId}";
	public static final String REST_API_UPDATE_TRAINEEASSINMENT = "/traineeAssignment/update";
	public static final String REST_API_GET_LIST_TRAINEE_ASSIGNMENT_SEARCH = "/traineeAssignment/search";

//thuy
	public static final String REST_API_GET_EXAM_RESULT_BY_LESSON = "/chapter/lesson/{chapter_id}";
	public static final String REST_API_GET_ALL_CHAPTER_BY_PARENT_ID = "/chapter/listchapter";
	
//
	public static final String REST_API_GET_EXAM_DEMO = "/exam/exam_demo";
	public static final String REST_API_GET_ENTRY_TEST = "/exam/entry_test";
	public static final String REST_API_GET_EXAM_USER = "/exam/exam_user";
	public static final String REST_API_EXAM = "/exam/exam_often";
	

	public static final String REST_API_GET_EXAM_BY_EXAM_MODE="exam/listexam/{exam_mode}" ;

	public static final String REST_API_GET_LIST_EXAM_USER_BY_EXAM_ID = "/exam_user/{examId}";
	
}
