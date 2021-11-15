package club.tilitili.schedule.entity;

import java.util.List;

public class PageModel<T> {
    private Integer total;
    private Integer pageSize;
    private Integer current;
    private Boolean hasMore;
    private List<T> list;
    private Object data;

    public static <T> BaseModel<PageModel<T>> of(Integer total, Integer pageSize, Integer current, List<T> list) {
        boolean hasMore = pageSize * current < total;
        PageModel<T> pageModel = new PageModel<T>().setTotal(total).setPageSize(pageSize).setCurrent(current).setHasMore(hasMore).setList(list);
        return new BaseModel<>("查询成功", true, pageModel);
    }

    public static <T> BaseModel<PageModel<T>> of(Integer total, Integer pageSize, Integer current, List<T> list, Object data) {
        boolean hasMore = pageSize * current < total;
        PageModel<T> pageModel = new PageModel<T>().setTotal(total).setPageSize(pageSize).setCurrent(current).setHasMore(hasMore).setList(list).setData(data);
        return new BaseModel<>("查询成功", true, pageModel);
    }

    public Integer getTotal() {
        return total;
    }

    public PageModel<T> setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public PageModel<T> setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getCurrent() {
        return current;
    }

    public PageModel<T> setCurrent(Integer current) {
        this.current = current;
        return this;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public PageModel<T> setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
        return this;
    }

    public List<T> getList() {
        return list;
    }

    public PageModel<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public Object getData() {
        return data;
    }

    public PageModel<T> setData(Object data) {
        this.data = data;
        return this;
    }
}
