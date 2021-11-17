package club.tilitili.schedule.entity.query.base;
public class BaseQuery<T> {
	private Integer pageNo;
	private Integer pageSize;
	private String sorter;
	private String sorted;
	public Integer getStart() { return pageNo != null && pageSize != null? (pageNo - 1) * pageSize: null; }

	public Integer getPageNo() { return pageNo; }
	public T setPageNo(Integer pageNo) { this.pageNo = pageNo;return (T) this; }
	public Integer getPageSize() { return pageSize; }
	public T setPageSize(Integer pageSize) { this.pageSize = pageSize;return (T) this; }
	public String getSorter() { return sorter; }
	public T setSorter(String sorter) { this.sorter = sorter;return (T) this; }
	public String getSorted() { return sorted; }
	public T setSorted(String sorted) { this.sorted = sorted;return (T) this; }
}
