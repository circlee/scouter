/*
 *  Copyright 2016 the original author or authors. 
 *  @https://github.com/scouter-project/scouter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 */
package scouter.agent.batch.trace;

public class TraceSQL {
	public static final String CURRENT_TRACESQL_FIELD = "_current_trace_sql_";
	
	public Integer hashValue;
	public int count = 0;
	
	public int startTime = -1;
	public int endTime = 0;
	public int totalTime = 0;
	public int minTime = Integer.MAX_VALUE;
	public int maxTime = 0;
	public long processedRows = 0L;
	
	public long sqlStartTime;
	
	public void start(){
		sqlStartTime = System.currentTimeMillis();
		if(startTime == -1){
			startTime =  (int)(sqlStartTime - TraceContext.getInstance().startTime);
		}
		System.out.println("==>Start:" + sqlStartTime);
	}
	
	public void end(){
		int responseTime = (int)(System.currentTimeMillis() - sqlStartTime);
		count++;
		totalTime += responseTime;
		endTime = (int)(System.currentTimeMillis() - TraceContext.getInstance().startTime);
		
		if(minTime > responseTime){
			minTime = responseTime;
		}
		if(maxTime < responseTime){
			maxTime = responseTime;
		}
		System.out.println("==>End:" + count + " - " + totalTime + " - " + responseTime);
	}
	
	public void addRows(){
		processedRows++;
	}
	
	public void addRows(int rows){
		processedRows += rows;
	}
}