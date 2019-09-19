package global.testingsystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="domains")
public class Domain implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int", nullable = false)
	private int id;
	@Column(name = "name", columnDefinition = "nvarchar(50)", nullable = false)
	private String name;
	@Column(name = "created_at", columnDefinition = "DateTime")
	private Date created_at;
	@Column(name = "updated_at", columnDefinition = "DateTime")
	private Date updated_at;
	private int status;
	@ManyToOne
	@JoinColumn(name = "subject_id", referencedColumnName = "id")
	@JsonIgnoreProperties("domains")
	private Subject subject;
	@OneToMany(targetEntity= Domain_Exam.class, mappedBy="domain_id", fetch=FetchType.EAGER)
	private Set<Domain_Exam> domain_Exams;
	@JsonIgnore
	@OneToMany(targetEntity = Question.class, mappedBy="domain")
	private Set<Question> questions;
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
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Set<Domain_Exam> getDomain_Exams() {
		return domain_Exams;
	}
	public void setDomain_Exams(Set<Domain_Exam> domain_Exams) {
		this.domain_Exams = domain_Exams;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
