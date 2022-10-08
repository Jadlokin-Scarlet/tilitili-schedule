package club.tilitili.schedule.entity;

public class TilitiliJobDTO extends TilitiliJob {
	private String runStatus;

	public String getRunStatus() {
		return runStatus;
	}

	public TilitiliJobDTO setRunStatus(String runStatus) {
		this.runStatus = runStatus;
		return this;
	}
}
