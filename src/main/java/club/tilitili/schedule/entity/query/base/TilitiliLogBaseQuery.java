package club.tilitili.schedule.entity.query.base;
import java.util.Date;
public class TilitiliLogBaseQuery<T> extends BaseTableQuery<T> {
	private Long id;
	private String name;
	private Date runTimeStart;
	private Date runTimeEnd;
	private Boolean success;
	private String failReason;
	private Date createTimeStart;
	private Date createTimeEnd;
	private Date updateTimeStart;
	private Date updateTimeEnd;
	public Long getId() { return id; }
	public T setId(Long id) { this.id = id; return (T) this; }
	public String getName() { return name; }
	public T setName(String name) { this.name = name; return (T) this; }
	public Date getRunTimeStart() { return runTimeStart; }
	public T setRunTimeStart(Date runTimeStart) { this.runTimeStart = runTimeStart; return (T) this; }
	public Date getRunTimeEnd() { return runTimeEnd; }
	public T setRunTimeEnd(Date runTimeEnd) { this.runTimeEnd = runTimeEnd; return (T) this; }
	public Boolean getSuccess() { return success; }
	public T setSuccess(Boolean success) { this.success = success; return (T) this; }
	public String getFailReason() { return failReason; }
	public T setFailReason(String failReason) { this.failReason = failReason; return (T) this; }
	public Date getCreateTimeStart() { return createTimeStart; }
	public T setCreateTimeStart(Date createTimeStart) { this.createTimeStart = createTimeStart; return (T) this; }
	public Date getCreateTimeEnd() { return createTimeEnd; }
	public T setCreateTimeEnd(Date createTimeEnd) { this.createTimeEnd = createTimeEnd; return (T) this; }
	public Date getUpdateTimeStart() { return updateTimeStart; }
	public T setUpdateTimeStart(Date updateTimeStart) { this.updateTimeStart = updateTimeStart; return (T) this; }
	public Date getUpdateTimeEnd() { return updateTimeEnd; }
	public T setUpdateTimeEnd(Date updateTimeEnd) { this.updateTimeEnd = updateTimeEnd; return (T) this; }
}