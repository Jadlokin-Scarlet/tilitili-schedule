package club.tilitili.schedule.entity;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
public class TilitiliJob implements Serializable {
	public final static String tableName = "tilitili_job";
	private String cron;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String name;
	private Long id;
	private String title;
	private Integer status;
	public String getCron() { return cron; }
	public TilitiliJob setCron(String cron) { this.cron = cron; return this; }
	public Date getUpdateTime() { return updateTime; }
	public TilitiliJob setUpdateTime(Date updateTime) { this.updateTime = updateTime; return this; }
	public Date getCreateTime() { return createTime; }
	public TilitiliJob setCreateTime(Date createTime) { this.createTime = createTime; return this; }
	public String getName() { return name; }
	public TilitiliJob setName(String name) { this.name = name; return this; }
	public Long getId() { return id; }
	public TilitiliJob setId(Long id) { this.id = id; return this; }
	public String getTitle() { return title; }
	public TilitiliJob setTitle(String title) { this.title = title; return this; }
	public Integer getStatus() { return status; }
	public TilitiliJob setStatus(Integer status) { this.status = status; return this; }
}