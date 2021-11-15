package club.tilitili.schedule.entity.query.base;
import java.util.Date;
public class TilitiliUserBaseQuery<T> extends BaseQuery<T> {
	private String password;
	private Date updateTime;
	private Date createTime;
	private String userName;
	private Long id;
	private Integer status;
	public String getPassword() { return password; }
	public T setPassword(String password) { this.password = password; return (T) this; }
	public Date getUpdateTime() { return updateTime; }
	public T setUpdateTime(Date updateTime) { this.updateTime = updateTime; return (T) this; }
	public Date getCreateTime() { return createTime; }
	public T setCreateTime(Date createTime) { this.createTime = createTime; return (T) this; }
	public String getUserName() { return userName; }
	public T setUserName(String userName) { this.userName = userName; return (T) this; }
	public Long getId() { return id; }
	public T setId(Long id) { this.id = id; return (T) this; }
	public Integer getStatus() { return status; }
	public T setStatus(Integer status) { this.status = status; return (T) this; }
}