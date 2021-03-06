package com.team1389.base.webserver.chart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class lets you create a line chart that will show up on the web dashboard
 */
public class Chart{
	double xScale;
	double yScale;
	String xAxisLabel;
	String yAxisLabel;
	List<DataPoint> dataPoints;
	public Chart(double xScale, double yScale, String xAxisLabel, String yAxisLabel){
		this.xScale = xScale;
		this.yScale = yScale;
		this.xAxisLabel = xAxisLabel;
		this.yAxisLabel = yAxisLabel;
		dataPoints = Collections.synchronizedList(new ArrayList<DataPoint>());
	}
	public void addPoint(DataPoint point){
		dataPoints.add(point);
	}
	public List<DataPoint> getPoints(){
		return dataPoints;
	}
}
