package club.tilitili.schedule.entity;
import java.io.Serializable;
import club.tilitili.schedule.entity.dto.BaseDTO;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
public class TilitiliLog extends BaseDTO implements Serializable {
	public static final String TABLE_NAME = "tilitili_log";
	private Long id;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date runTime;
	private Boolean success;
	private String failReason;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	public Long getId() { return id; }
	public TilitiliLog setId(Long id) { this.id = id; return this; }
	public String getName() { return name; }
	public TilitiliLog setName(String name) { this.name = name; return this; }
	public Date getRunTime() { return runTime; }
	public TilitiliLog setRunTime(Date runTime) { this.runTime = runTime; return this; }
	public Boolean getSuccess() { return success; }
	public TilitiliLog setSuccess(Boolean success) { this.success = success; return this; }
	public String getFailReason() { return failReason; }
	public TilitiliLog setFailReason(String failReason) { this.failReason = failReason; return this; }
	public Date getCreateTime() { return createTime; }
	public TilitiliLog setCreateTime(Date createTime) { this.createTime = createTime; return this; }
	public Date getUpdateTime() { return updateTime; }
	public TilitiliLog setUpdateTime(Date updateTime) { this.updateTime = updateTime; return this; }
}