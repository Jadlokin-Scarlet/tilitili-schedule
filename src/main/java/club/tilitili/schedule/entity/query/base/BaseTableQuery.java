package club.tilitili.schedule.entity.query.base;
import club.tilitili.schedule.entity.dto.BaseDTO;
public class BaseTableQuery<T> extends BaseDTO {
	private Integer pageNo;
	private Integer pageSize;
	private String sorter;
	private String sorted;
	private String subSorter;
	private String subSorted;
	public Integer getStart() { return pageNo != null && pageSize != null? (pageNo - 1) * pageSize: null; }
	public Integer getCurrent() { return pageNo; }
	public T setCurrent(Integer current) { this.pageNo = current;return (T) this; }

	public Integer getPageNo() { return pageNo; }
	public T setPageNo(Integer pageNo) { this.pageNo = pageNo;return (T) this; }
	public Integer getPageSize() { return pageSize; }
	public T setPageSize(Integer pageSize) { this.pageSize = pageSize;return (T) this; }
	public String getSorter() { return sorter; }
	public T setSorter(String sorter) { this.sorter = sorter;return (T) this; }
	public String getSorted() { return sorted; }
	public T setSorted(String sorted) { this.sorted = sorted;return (T) this; }
	public String getSubSorter() { return subSorter; }
	public T setSubSorter(String subSorter) { this.subSorter = subSorter;return (T) this; }
	public String getSubSorted() { return subSorted; }
	public T setSubSorted(String subSorted) { this.subSorted = subSorted;return (T) this; }
}
