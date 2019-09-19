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
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class Class implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_id")
	private int classId;
	@Column(name = "is_default")
	private int isDefault;
	@Column(name = "name")
	private String name;
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "trainer_id")
	private String trainer;
	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Users manager;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public Users getManager() {
		return manager;
	}

	public void setManager(Users manager) {
		this.manager = manager;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Class(int classId, int isDefault, String name, String trainer, Users manager) {
		super();
		this.classId = classId;
		this.isDefault = isDefault;
		this.name = name;

		this.trainer = trainer;
		this.manager = manager;
	}

	public Class(int classId, int isDefault, String name, Date startDate, Date endDate, String trainer, Users manager) {
		super();
		this.classId = classId;
		this.isDefault = isDefault;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.trainer = trainer;
		this.manager = manager;
	}

	public Class() {
		super();
	}

}
