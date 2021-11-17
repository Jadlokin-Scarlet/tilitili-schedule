package club.tilitili.schedule.entity;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
public class TilitiliLog implements Serializable {
	public final static String tableName = "tilitili_log";
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date runTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private Boolean success;
	private String name;
	private Long id;
	private String failReason;
	public Date getUpdateTime() { return updateTime; }
	public TilitiliLog setUpdateTime(Date updateTime) { this.updateTime = updateTime; return this; }
	public Date getRunTime() { return runTime; }
	public TilitiliLog setRunTime(Date runTime) { this.runTime = runTime; return this; }
	public Date getCreateTime() { return createTime; }
	public TilitiliLog setCreateTime(Date createTime) { this.createTime = createTime; return this; }
	public Boolean getSuccess() { return success; }
	public TilitiliLog setSuccess(Boolean success) { this.success = success; return this; }
	public String getName() { return name; }
	public TilitiliLog setName(String name) { this.name = name; return this; }
	public Long getId() { return id; }
	public TilitiliLog setId(Long id) { this.id = id; return this; }
	public String getFailReason() { return failReason; }
	public TilitiliLog setFailReason(String failReason) { this.failReason = failReason; return this; }
}