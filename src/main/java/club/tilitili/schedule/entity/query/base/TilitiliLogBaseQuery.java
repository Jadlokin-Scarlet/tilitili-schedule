package club.tilitili.schedule.entity.query.base;
import java.util.Date;
public class TilitiliLogBaseQuery<T> extends BaseQuery<T> {
	private Date updateTime;
	private Date runTime;
	private Date createTime;
	private Boolean success;
	private String name;
	private Long id;
	private String failReason;
	public Date getUpdateTime() { return updateTime; }
	public T setUpdateTime(Date updateTime) { this.updateTime = updateTime; return (T) this; }
	public Date getRunTime() { return runTime; }
	public T setRunTime(Date runTime) { this.runTime = runTime; return (T) this; }
	public Date getCreateTime() { return createTime; }
	public T setCreateTime(Date createTime) { this.createTime = createTime; return (T) this; }
	public Boolean getSuccess() { return success; }
	public T setSuccess(Boolean success) { this.success = success; return (T) this; }
	public String getName() { return name; }
	public T setName(String name) { this.name = name; return (T) this; }
	public Long getId() { return id; }
	public T setId(Long id) { this.id = id; return (T) this; }
	public String getFailReason() { return failReason; }
	public T setFailReason(String failReason) { this.failReason = failReason; return (T) this; }
}