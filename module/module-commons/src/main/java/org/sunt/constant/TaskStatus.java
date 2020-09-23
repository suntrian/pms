package org.sunt.constant;

public enum TaskStatus {

    INITIAL(0, "未开始"),
    PROCESSING(1, "进行中"),

    FINISH(2, "已完成"),

    FAILURE(3, "失败"),

    STARTING(4, "开始中"),
    STOPPING(5, "停止中"),
    SUSPEND(6, "挂起"),
    BLOCKED(7, "阻塞");

    private final int key;
    private final String label;

    TaskStatus(int key, String label) {
        this.key = key;
        this.label = label;
    }

    public int getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }
}
