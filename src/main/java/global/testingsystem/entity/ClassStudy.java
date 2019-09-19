package global.testingsystem.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ClassStudy implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_study_id")
	private int classStudyId;
	@Column(name = "study_time")
	private Date studyTime;
	@Column(name = "result_lesson")
	private int resultLesson;
	@Column(name = "result_note")
	private String resultNote;
	@Column(name = "is_present")
	private int isPresent;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class class1;
	@ManyToOne
	@JoinColumn(name = "class_unit_id")
	private ClassUnit classUnit;

	public int getClassStudyId() {
		return classStudyId;
	}

	public void setClassStudyId(int classStudyId) {
		this.classStudyId = classStudyId;
	}

	public Date getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(Date studyTime) {
		this.studyTime = studyTime;
	}

	public int getResultLesson() {
		return resultLesson;
	}

	public void setResultLesson(int resultLesson) {
		this.resultLesson = resultLesson;
	}

	public String getResultNote() {
		return resultNote;
	}

	public void setResultNote(String resultNote) {
		this.resultNote = resultNote;
	}

	public int getIsPresent() {
		return isPresent;
	}

	public void setIsPresent(int isPresent) {
		this.isPresent = isPresent;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Class getClass1() {
		return class1;
	}

	public void setClass1(Class class1) {
		this.class1 = class1;
	}

	public ClassUnit getClassUnit() {
		return classUnit;
	}

	public void setClassUnit(ClassUnit classUnit) {
		this.classUnit = classUnit;
	}

	public ClassStudy(int classStudyId, Date studyTime, int resultLesson, String resultNote, int isPresent, Users user,
			Class class1, ClassUnit classUnit) {
		super();
		this.classStudyId = classStudyId;
		this.studyTime = studyTime;
		this.resultLesson = resultLesson;
		this.resultNote = resultNote;
		this.isPresent = isPresent;
		this.user = user;
		this.class1 = class1;
		this.classUnit = classUnit;
	}

	public ClassStudy() {
		super();
	}

}
