package org.sunt.basicmodel;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author suntr
 * @version dmp1.6.0
 * 整个模型的变更信息包装类
 */
public class ModelDiffWrapper implements Serializable {

    private static final long serialVersionUID = -3169215895599226744L;
    @Getter
    private Map<Integer, LevelFunction<Serializable, Comparable>> functionMap;
    @Getter
    @Setter
    private List<ModelDiffElement<Serializable>> elementDiff;
    @Getter
    @Setter
    private List<ModelDiffEdge> edgeDiff;

    public boolean isEmpty() {
        return elementDiff == null || elementDiff.isEmpty();
    }

    public void setFunctionMap(Map<Integer, LevelFunction<Serializable, Comparable>> functionMap) {
        this.functionMap = functionMap;
    }

    public enum State implements Serializable {
        CREATED, UPDATED, DELETED, REMAIN
    }
}
