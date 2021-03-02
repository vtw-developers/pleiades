package com.vtw.pleiades.agent.test.metric;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AgentMetricCollector {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final String agentId = "3";
	private final String serverId = "2";
	private final String mountDir = "/";
	
	public AgentMetric collect() {
		AgentMetric metric = new AgentMetric(agentId, serverId);
		calculateCpuUsage(metric);
		calculateMemoryUsage(metric);
		calculateDiskUsage(metric, mountDir);

		return metric;
	}
	
	private void calculateCpuUsage(AgentMetric metric) {
		metric.setCpuUsage(getCpuUsage());
	}
	
	private void calculateMemoryUsage(AgentMetric metric) {
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

		long max = memoryMXBean.getHeapMemoryUsage().getMax();
		long used = memoryMXBean.getHeapMemoryUsage().getUsed();
		
		metric.setMemoryMaxSpace(max);
		metric.setMemoryUsedSpace(used);
	}
	
	private void calculateDiskUsage(AgentMetric metric, String mountDirPath) {
		File mountDir = new File(mountDirPath);

		long total = mountDir.getTotalSpace();
		long free = mountDir.getFreeSpace();
		long used = total - free;

		metric.setDiskTotalSpace(total);
		metric.setDiskUsedSpace(used);
	}

	private double getCpuUsage() {
		try {
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			if (mbs == null)
				return 0;
			ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
			if (name == null)
				return 0;
			double cpuUsageSize = (double) mbs.getAttribute(name, "ProcessCpuLoad");

			return cpuUsageSize;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return 0;
		}
	}
}
