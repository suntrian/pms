package org.sunt.basicmodel;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @param <T>
 * @author suntr
 * @version dmp1.6.0
 * 用于保存节点的变更信息
 */
public class ModelDiffElement<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 2782896403641513494L;

    private ModelDiffWrapper.State state;
    private T original;
    private T current;
    //parent 和 children 和当前节点一般非同一类型
    private ModelDiffElement<Serializable> parent;
    private List<ModelDiffElement<? extends Serializable>> children = new LinkedList<>();

    public ModelDiffElement() {
    }

    public ModelDiffElement(ModelDiffWrapper.State state, T element) {
        this.state = state;
        if (ModelDiffWrapper.State.CREATED.equals(this.state)) {
            this.current = element;
        } else if (ModelDiffWrapper.State.DELETED.equals(this.state)) {
            this.original = element;
        } else if (ModelDiffWrapper.State.REMAIN.equals(this.state)) {
            this.original = element;
            this.current = element;
        } else {
            throw new IllegalStateException("current state not support one element");
        }
    }

    public ModelDiffElement(ModelDiffWrapper.State state, T original, T current) {
        this.state = state;
        this.original = original;
        this.current = current;
    }

    @SuppressWarnings("unchecked")
    public void addChild(ModelDiffElement<Serializable> child) {
        child.setParent((ModelDiffElement<Serializable>) this);
        this.children.add(child);
    }

    @SuppressWarnings("unchecked")
    public <C extends Serializable> void addChildren(Collection<ModelDiffElement<C>> children) {
        children.forEach(c -> c.setParent((ModelDiffElement<Serializable>) this));
        this.children.addAll(children);
    }

    public T getElement() {
        switch (state) {
            case CREATED:
            case UPDATED:
                return current;
            case DELETED:
            case REMAIN:
                return original;
            default:
                return null;
        }
    }

    public ModelDiffWrapper.State getState() {
        return state;
    }

    public void setState(ModelDiffWrapper.State state) {
        this.state = state;
    }

    public T getOriginal() {
        return original;
    }

    public void setOriginal(T original) {
        this.original = original;
    }

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public ModelDiffElement<Serializable> getParent() {
        return parent;
    }

    public void setParent(ModelDiffElement<Serializable> parent) {
        this.parent = parent;
    }

    public <C extends Serializable> List<ModelDiffElement<C>> getChildren() {
        return (List) children;
    }

    public <C extends Serializable> void setChildren(List<ModelDiffElement<C>> children) {
        this.children = (List) children;
    }
}
