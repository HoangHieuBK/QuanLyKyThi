package global.testingsystem.response;

public class UnitDTO {

	private int unit_id;

	private String unit_name;

	private int unit_order;

	public UnitDTO() {
		super();
	}

	public int getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public int getUnit_order() {
		return unit_order;
	}

	public void setUnit_order(int unit_order) {
		this.unit_order = unit_order;
	}
}
