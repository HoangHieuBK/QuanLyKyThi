package global.testingsystem.response;

import java.util.Date;
import java.util.List;

public class SubjectResult {

	private int id;

	private String name;

	private int parent_id;

	private Integer chapterOrder;

	private Date created_at;

	private List<ChapterDTO> chapters;

	public SubjectResult() {
		super();
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

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getChapterOrder() {
		return chapterOrder;
	}

	public void setChapterOrder(Integer chapterOrder) {
		this.chapterOrder = chapterOrder;
	}

	public List<ChapterDTO> getChapters() {
		return chapters;
	}

	public void setChapters(List<ChapterDTO> chapters) {
		this.chapters = chapters;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
