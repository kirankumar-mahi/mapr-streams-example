package livestreaming.data;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
	
	@JsonProperty public String xValue;
	@JsonProperty public Double[] columnA;
	@JsonProperty public Double[] columnB;
	
	public Data(){
		
	}
	
	@Override
	public String toString() {
		return "Data [xValue=" + xValue + ", columnA=" + Arrays.toString(columnA) + ", columnB="
				+ Arrays.toString(columnB) + "]";
	}

	public Data(String xValue,Double[] columnA,Double[] columnB){
		this.xValue = xValue;
		this.columnA = columnA;
		this.columnB = columnB;
	}

	public String getxValue() {
		return xValue;
	}

	public void setxValue(String xValue) {
		this.xValue = xValue;
	}

	public Double[] getColumnA() {
		return columnA;
	}

	public void setColumnA(Double[] columnA) {
		this.columnA = columnA;
	}

	public Double[] getColumnB() {
		return columnB;
	}

	public void setColumnB(Double[] columnB) {
		this.columnB = columnB;
	}
	
}
