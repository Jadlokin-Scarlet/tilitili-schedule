package club.tilitili.schedule.entity;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
public class TilitiliUser implements Serializable {
	public final static String tableName = "tilitili_user";
	private String password;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String userName;
	private Long id;
	private Integer status;
	public String getPassword() { return password; }
	public TilitiliUser setPassword(String password) { this.password = password; return this; }
	public Date getUpdateTime() { return updateTime; }
	public TilitiliUser setUpdateTime(Date updateTime) { this.updateTime = updateTime; return this; }
	public Date getCreateTime() { return createTime; }
	public TilitiliUser setCreateTime(Date createTime) { this.createTime = createTime; return this; }
	public String getUserName() { return userName; }
	public TilitiliUser setUserName(String userName) { this.userName = userName; return this; }
	public Long getId() { return id; }
	public TilitiliUser setId(Long id) { this.id = id; return this; }
	public Integer getStatus() { return status; }
	public TilitiliUser setStatus(Integer status) { this.status = status; return this; }
}