package global.testingsystem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import global.testingsystem.util.CompositeExamSettingId;
@Entity
@Table(name="Exam_Setting")
//@IdClass(CompositeExamSettingId.class)
public class Exam_Setting implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int", nullable = false)
	private int id;
	@Column(name = "exam_id", columnDefinition = "int", nullable = false)
	private int exam_id;
	@Column(name = "chapter_id", columnDefinition = "int", nullable = false)
	private int chapter_id;
	@Column(name = "domain_id", columnDefinition = "int", nullable = false)
	private int domain_id;
	@Column(name = "question_num", columnDefinition = "int")
	private int percentage;
	@Column(name = "subject_id", columnDefinition = "int", nullable = false)
	private int subject_id;

	@Column(name = "keyword", nullable = false)
	private String keyword;

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getId() {
		return id;
	}

	public Exam_Setting(int exam_id, int chapter_id, int domain_id, int percentage, String keyword) {
		super();
		this.exam_id = exam_id;
		this.chapter_id = chapter_id;
		this.domain_id = domain_id;
		this.percentage = percentage;
		this.keyword = keyword;
	}
	public Exam_Setting(int exam_id, int chapter_id, int domain_id, int percentage) {
		super();
		this.exam_id = exam_id;
		this.chapter_id = chapter_id;
		this.domain_id = domain_id;
		this.percentage = percentage;
	
	}
	
	public Exam_Setting(int exam_id, int chapter_id, int domain_id, int percentage, int subject_id,String keyword) {
		super();
		this.exam_id = exam_id;
		this.chapter_id = chapter_id;
		this.domain_id = domain_id;
		this.percentage = percentage;
		this.subject_id = subject_id;
		this.keyword = keyword;
	}

	public Exam_Setting() {
		super();
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	public int getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(int chapter_id) {
		this.chapter_id = chapter_id;
	}
	public int getDomain_id() {
		return domain_id;
	}
	public void setDomain_id(int domain_id) {
		this.domain_id = domain_id;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}


}
