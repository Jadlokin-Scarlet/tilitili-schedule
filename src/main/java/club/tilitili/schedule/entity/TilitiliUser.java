package club.tilitili.schedule.entity;
import java.io.Serializable;
import club.tilitili.schedule.entity.dto.BaseDTO;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
public class TilitiliUser extends BaseDTO implements Serializable {
	public static final String TABLE_NAME = "tilitili_user";
	private Long id;
	private String userName;
	private String password;
	private Integer status;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	public Long getId() { return id; }
	public TilitiliUser setId(Long id) { this.id = id; return this; }
	public String getUserName() { return userName; }
	public TilitiliUser setUserName(String userName) { this.userName = userName; return this; }
	public String getPassword() { return password; }
	public TilitiliUser setPassword(String password) { this.password = password; return this; }
	public Integer getStatus() { return status; }
	public TilitiliUser setStatus(Integer status) { this.status = status; return this; }
	public Date getCreateTime() { return createTime; }
	public TilitiliUser setCreateTime(Date createTime) { this.createTime = createTime; return this; }
	public Date getUpdateTime() { return updateTime; }
	public TilitiliUser setUpdateTime(Date updateTime) { this.updateTime = updateTime; return this; }
}