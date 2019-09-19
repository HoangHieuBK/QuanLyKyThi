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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trainee_assignment")
public class TraineeAssignment implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "file")
	private String file;
	private String result;
	@Column(name = "result_detail")
	private String resultDetail;
	@Column(name = "time_submit")
	private Date timeSubmit;
	@Column(name = "time_Eval")
	private Date timeEval;
	@Column(name = "evaluator")
	private String evaluator;
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class class1;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user1;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String result) {
		this.file = file;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultDetail() {
		return resultDetail;
	}

	public void setResultDetail(String resultDetail) {
		this.resultDetail = resultDetail;
	}

	public Class getClass1() {
		return class1;
	}

	public void setClass1(Class class1) {
		this.class1 = class1;
	}

	public Date getTimeSubmit() {
		return timeSubmit;
	}

	public void setTimeSubmit(Date timeSubmit) {
		this.timeSubmit = timeSubmit;
	}

	public Date getTimeEval() {
		return timeEval;
	}

	public void setTimeEval(Date timeEval) {
		this.timeEval = timeEval;
	}

	public Users getUser1() {
		return user1;
	}

	public void setUser1(Users user1) {
		this.user1 = user1;
	}
	public String getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(String evaluator) {
		this.evaluator = evaluator;
	}
	public TraineeAssignment(Integer id, String file, String result, String resultDetail, Date timeSubmit,
			Date timeEval, String evaluator, Class class1, Users user1) {
		super();
		this.id = id;
		this.file = file;
		this.result = result;
		this.resultDetail = resultDetail;
		this.timeSubmit = timeSubmit;
		this.timeEval = timeEval;
		this.evaluator = evaluator;
		this.class1 = class1;
		this.user1 = user1;
	}
	public TraineeAssignment() {
		super();
	}

}
