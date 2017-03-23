package livestreaming.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class Data {
	
	@JsonProperty public String xValue;
	@JsonProperty public Double[] columnA;
	@JsonProperty public Double[] columnB;
	
	
	@Override
	public String toString() {
		
	       return MoreObjects.toStringHelper(this)
	    		   	.add("xValue", xValue)
	                .add("columnA", columnA)
	                .add("columnB", columnB)
	                .toString();
	}

	public Data(){
		
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
