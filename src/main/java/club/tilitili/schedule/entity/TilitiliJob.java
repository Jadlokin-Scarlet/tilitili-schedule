package club.tilitili.schedule.entity;
import java.io.Serializable;
import club.tilitili.schedule.entity.dto.BaseDTO;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
public class TilitiliJob extends BaseDTO implements Serializable {
	public static final String TABLE_NAME = "tilitili_job";
	private Long id;
	private String title;
	private String name;
	private String cron;
	private Integer status;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	public Long getId() { return id; }
	public TilitiliJob setId(Long id) { this.id = id; return this; }
	public String getTitle() { return title; }
	public TilitiliJob setTitle(String title) { this.title = title; return this; }
	public String getName() { return name; }
	public TilitiliJob setName(String name) { this.name = name; return this; }
	public String getCron() { return cron; }
	public TilitiliJob setCron(String cron) { this.cron = cron; return this; }
	public Integer getStatus() { return status; }
	public TilitiliJob setStatus(Integer status) { this.status = status; return this; }
	public Date getCreateTime() { return createTime; }
	public TilitiliJob setCreateTime(Date createTime) { this.createTime = createTime; return this; }
	public Date getUpdateTime() { return updateTime; }
	public TilitiliJob setUpdateTime(Date updateTime) { this.updateTime = updateTime; return this; }
}