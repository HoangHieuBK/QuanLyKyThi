package global.testingsystem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "class_lesson")
public class ClassLesson implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "lesson_order")
	private Integer lessonOrder;

	@ManyToOne
	@JoinColumn(name = "chapter_id")
	private Chapter chapter;
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class class1;
	
	@ManyToOne
	@JoinColumn(name = "class_lesson_id")
	private TraineeAssignment traineeAssignment;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public Integer getLessonOrder() {
		return lessonOrder;
	}

	public void setLessonOrder(Integer lessonOrder) {
		this.lessonOrder = lessonOrder;
	}

	public Class getClass1() {
		return class1;
	}

	public void setClass1(Class class1) {
		this.class1 = class1;
	}

	public ClassLesson(Integer id, Integer lessonOrder) {
		super();
		this.id = id;
		this.lessonOrder = lessonOrder;
	}


	public TraineeAssignment getTraineeAssignment() {
		return traineeAssignment;
	}

	public void setTraineeAssignment(TraineeAssignment traineeAssignment) {
		this.traineeAssignment = traineeAssignment;
	}

	public ClassLesson(Integer id, Integer lessonOrder, Chapter chapter, Class class1,
			TraineeAssignment traineeAssignment) {
		super();
		this.id = id;
		this.lessonOrder = lessonOrder;
		this.chapter = chapter;
		this.class1 = class1;
		this.traineeAssignment = traineeAssignment;
	}

	public ClassLesson() {
		super();
	}

}
