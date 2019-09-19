package global.testingsystem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Unit implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_id")
	private int unitId;
	private String name;
	private String description;
	@Column(name = "unit_order")
	private Integer unitOrder;
	@Column(name = "content_type")
	private String contentType;
	private String mediaLink;
	private String document;
	@ManyToOne
	@JoinColumn(name = "exam_id")
	private Exam exam;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "chapter_id")
	private Chapter chapter;

	public Unit(String name, String discription, String unitOrder, String lessonType, Exam exam, Chapter chapters) {
		super();
	}

	public Unit() {
		super();
	}

	public String getMediaLink() {
		return mediaLink;
	}

	public void setMediaLink(String mediaLink) {
		this.mediaLink = mediaLink;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String discription) {
		this.description = discription;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Integer getUnitOrder() {
		return unitOrder;
	}

	public void setUnitOrder(Integer unitOrder) {
		this.unitOrder = unitOrder;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

}
