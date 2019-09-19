package global.testingsystem.DTO;

import java.util.Date;

public class TraineeDTO {
	private int id;
	private String fullName;
	private Date timeSubmit;
	private String trainerId;
	private int result;
	private String resultDetail;
	private Date timeEval;
	private String assignment;
	private String evaluator;
	private String file;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getTimeSubmit() {
		return timeSubmit;
	}
	public void setTimeSubmit(Date timeSubmit) {
		this.timeSubmit = timeSubmit;
	}

	public String getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getResultDetail() {
		return resultDetail;
	}
	public void setResultDetail(String resultDetail) {
		this.resultDetail = resultDetail;
	}
	public Date getTimeEval() {
		return timeEval;
	}
	public void setTimeEval(Date timeEval) {
		this.timeEval = timeEval;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public String getEvaluator() {
		return evaluator;
	}
	public void setEvaluator(String evaluator) {
		this.evaluator = evaluator;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
}
