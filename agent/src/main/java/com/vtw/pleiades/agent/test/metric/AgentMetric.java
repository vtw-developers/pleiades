package com.vtw.pleiades.agent.test.metric;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgentMetric {

	@JsonProperty("@timestamp")
	private long timestamp;

	private String agentId;
	private String serverId;
	
	private double cpuUsage;

	private long memoryMaxSpace;
	private long memoryUsedSpace;

	private long diskTotalSpace;
	private long diskUsedSpace;

	public AgentMetric() {
	}
	
	public AgentMetric(String agentId, String serverId) {
		this.timestamp = System.currentTimeMillis();
		this.agentId = agentId;
		this.serverId = serverId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public long getMemoryMaxSpace() {
		return memoryMaxSpace;
	}

	public void setMemoryMaxSpace(long memoryMaxSpace) {
		this.memoryMaxSpace = memoryMaxSpace;
	}

	public long getMemoryUsedSpace() {
		return memoryUsedSpace;
	}

	public void setMemoryUsedSpace(long memoryUsedSpace) {
		this.memoryUsedSpace = memoryUsedSpace;
	}

	public long getDiskTotalSpace() {
		return diskTotalSpace;
	}

	public void setDiskTotalSpace(long diskTotalSpace) {
		this.diskTotalSpace = diskTotalSpace;
	}

	public long getDiskUsedSpace() {
		return diskUsedSpace;
	}

	public void setDiskUsedSpace(long diskUsedSpace) {
		this.diskUsedSpace = diskUsedSpace;
	}

	@Override
	public String toString() {
		return "AgentMetric [timestamp=" + timestamp + ", agentId=" + agentId + ", serverId=" + serverId + ", cpuUsage="
				+ cpuUsage + ", memoryMaxSpace=" + memoryMaxSpace + ", memoryUsedSpace=" + memoryUsedSpace
				+ ", diskTotalSpace=" + diskTotalSpace + ", diskUsedSpace=" + diskUsedSpace + "]";
	}

}
