package club.tilitili.schedule.entity.query.base;
import java.util.Date;
public class TilitiliJobBaseQuery<T> extends BaseTableQuery<T> {
	private Long id;
	private String title;
	private String name;
	private String cron;
	private Integer status;
	private Date createTimeStart;
	private Date createTimeEnd;
	private Date updateTimeStart;
	private Date updateTimeEnd;
	public Long getId() { return id; }
	public T setId(Long id) { this.id = id; return (T) this; }
	public String getTitle() { return title; }
	public T setTitle(String title) { this.title = title; return (T) this; }
	public String getName() { return name; }
	public T setName(String name) { this.name = name; return (T) this; }
	public String getCron() { return cron; }
	public T setCron(String cron) { this.cron = cron; return (T) this; }
	public Integer getStatus() { return status; }
	public T setStatus(Integer status) { this.status = status; return (T) this; }
	public Date getCreateTimeStart() { return createTimeStart; }
	public T setCreateTimeStart(Date createTimeStart) { this.createTimeStart = createTimeStart; return (T) this; }
	public Date getCreateTimeEnd() { return createTimeEnd; }
	public T setCreateTimeEnd(Date createTimeEnd) { this.createTimeEnd = createTimeEnd; return (T) this; }
	public Date getUpdateTimeStart() { return updateTimeStart; }
	public T setUpdateTimeStart(Date updateTimeStart) { this.updateTimeStart = updateTimeStart; return (T) this; }
	public Date getUpdateTimeEnd() { return updateTimeEnd; }
	public T setUpdateTimeEnd(Date updateTimeEnd) { this.updateTimeEnd = updateTimeEnd; return (T) this; }
}