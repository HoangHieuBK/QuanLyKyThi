package global.testingsystem.response;

import java.util.Date;
import java.util.List;

public class ChapterDTO {

	private int id;

	private String name;

	private int parent_id;

	private int chapter_order;

	private Date created_at;

	private List<UnitDTO> units;

	public ChapterDTO() {
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

	public int getChapter_order() {
		return chapter_order;
	}

	public void setChapter_order(int chapter_order) {
		this.chapter_order = chapter_order;
	}

	public List<UnitDTO> getUnits() {
		return units;
	}

	public void setUnits(List<UnitDTO> units) {
		this.units = units;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
