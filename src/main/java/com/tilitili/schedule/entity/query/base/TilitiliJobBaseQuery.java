package com.tilitili.schedule.entity.query.base;
import java.util.Date;
public class TilitiliJobBaseQuery<T> extends BaseQuery<T> {
	private String cron;
	private Date updateTime;
	private Date createTime;
	private String name;
	private Long id;
	private Integer status;
	public String getCron() { return cron; }
	public T setCron(String cron) { this.cron = cron; return (T) this; }
	public Date getUpdateTime() { return updateTime; }
	public T setUpdateTime(Date updateTime) { this.updateTime = updateTime; return (T) this; }
	public Date getCreateTime() { return createTime; }
	public T setCreateTime(Date createTime) { this.createTime = createTime; return (T) this; }
	public String getName() { return name; }
	public T setName(String name) { this.name = name; return (T) this; }
	public Long getId() { return id; }
	public T setId(Long id) { this.id = id; return (T) this; }
	public Integer getStatus() { return status; }
	public T setStatus(Integer status) { this.status = status; return (T) this; }
}