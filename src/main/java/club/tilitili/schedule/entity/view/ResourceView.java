package club.tilitili.schedule.entity.view;

public class ResourceView {
    private Object value;
    private Object text;

    public ResourceView() {
    }

    public ResourceView(Object value, Object text) {
        this.value = value;
        this.text = text;
    }

    public Object getValue() {
        return value;
    }

    public ResourceView setValue(Object value) {
        this.value = value;
        return this;
    }

    public Object getText() {
        return text;
    }

    public ResourceView setText(Object text) {
        this.text = text;
        return this;
    }
}
