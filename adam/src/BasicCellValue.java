public class BasicCellValue implements ICellValue {
	private Integer value;
	private boolean isOrig;
	
	public BasicCellValue(Integer v, boolean isOriginal) {
		this.set(v, isOriginal);
	}
	
	public BasicCellValue(Integer v) {
		this(v, false);
	}
	
	public BasicCellValue() {
		this(null);
	}
	
	public void set(Integer v, boolean isOriginal) {
		this.value = v;
		this.isOrig = isOriginal;
	}
	
	public void set(Integer v) {
		this.set(v, false);
	}
	
	public String getStringValue() {
		if (this.value == null) {
			return " ";
		}
		else {
			return this.value.toString();
		}
	}
	
	public int getIntValue() {
		if (this.value == null) {
			return 0;
		}
		else {
			return this.value;
		}
	}
	
	public boolean isOriginal() {
		return this.isOrig;
	}
}
