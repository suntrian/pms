package org.sunt.dictionary;

public enum TaskStatus {

    READY(0, "就绪"),
    SUSPENDED(0, "挂起"),
    FINISHED(0, "完成"),
    RUNNING(0, "运行"),
    FAILED(0, "失败"),
    TIMEOUT(0, "超时"),
    BLOCKED(0, "阻塞"),
    TERMINATED(0, "中止");


    private final int status;

    TaskStatus(int status, String desc) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
