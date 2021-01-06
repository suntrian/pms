package org.sunt.basicmodel;

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

    private Map<Integer, LevelFunction<Serializable, Comparable>> functionMap;

    private List<ModelDiffElement<Serializable>> elementDiff;

    private List<ModelDiffEdge> edgeDiff;

    public boolean isEmpty() {
        return elementDiff == null || elementDiff.isEmpty();
    }

    public void setFunctionMap(Map<Integer, LevelFunction<Serializable, Comparable>> functionMap) {
        this.functionMap = functionMap;
    }

    public Map<Integer, LevelFunction<Serializable, Comparable>> getFunctionMap() {
        return functionMap;
    }

    public List<ModelDiffElement<Serializable>> getElementDiff() {
        return elementDiff;
    }

    public void setElementDiff(List<ModelDiffElement<Serializable>> elementDiff) {
        this.elementDiff = elementDiff;
    }

    public List<ModelDiffEdge> getEdgeDiff() {
        return edgeDiff;
    }

    public void setEdgeDiff(List<ModelDiffEdge> edgeDiff) {
        this.edgeDiff = edgeDiff;
    }

    public enum State implements Serializable {
        CREATED, UPDATED, DELETED, REMAIN
    }
}
