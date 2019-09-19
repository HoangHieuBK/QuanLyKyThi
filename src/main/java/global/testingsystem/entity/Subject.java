package global.testingsystem.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import global.testingsystem.jsonview.ExamView;

@Entity
@Table(name = "Subjects")
public class Subject {
	@JsonView(ExamView.Subject.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int", nullable = false)
	private int id;
	@JsonView(ExamView.Subject.class)
	@Column(name = "name", columnDefinition = "nvarchar(50)", nullable = false)
	private String name;
	@Column(name = "created_at", columnDefinition = "DateTime")
	private Date created_at;
	@Column(name = "updated_at", columnDefinition = "DateTime")
	private Date updated_at;
	private int status;
	
	@JsonIgnore
	@OneToMany(targetEntity = Exam.class, mappedBy = "subject")
	private Set<Exam> exams;

	@OneToMany(targetEntity = Domain.class, mappedBy = "subject")
	@JsonIgnore
//	@JsonIgnoreProperties("subject")
	private Set<Domain> domains;
	
	@JsonIgnore
	@OneToMany(targetEntity = Question.class, mappedBy = "subject")
	private Set<Question> questions;
	
	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
//	@JsonIgnoreProperties("subject")
	private List<Chapter> chapters;

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Set<Domain> getDomains() {
		return domains;
	}

	public void setDomains(Set<Domain> domains) {
		this.domains = domains;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Exam> getExams() {
		return exams;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Subject(int id, String name, Date created_at, Date updated_at, Set<Exam> exams, Set<Domain> domains,
			Set<Question> questions) {
		super();
		this.id = id;
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.exams = exams;
		this.domains = domains;
		this.questions = questions;

	}

	public Subject() {
		super();
	}

}
