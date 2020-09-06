package org.sunt.basicmodel;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class ModelDecorator implements Serializable {
    public final static String split = "|";
    private static final long serialVersionUID = -8912707087146132642L;
    private int initialMaxLevel = 4;
    //统一保存模型各层级数据的属性方法
    //边的关键信息都保存在了modelEdge中，因此不再保存方法
    private Map<Integer, LevelFunction<Serializable, Comparable>> functionMap = new HashMap<>(initialMaxLevel);
    private List<ModelElement<Serializable>> elements;
    private Map<Integer, List<ModelEdge>> edges = new HashMap<>(initialMaxLevel);
    //游离节点，无法从根节点访问到的节点
    private Map<Integer, List<ModelElement>> deadElements = new HashMap<>(initialMaxLevel);
    private List<Integer> functionLevel0Based = new ArrayList<>(initialMaxLevel);
    private List<Integer> edgeLevel0Based = new ArrayList<>(initialMaxLevel);

    public static ModelDecoratorBuilder builder() {
        return new ModelDecoratorBuilder();
    }

    public Map<Integer, LevelFunction<Serializable, Comparable>> getFunctionMap() {
        return functionMap;
    }

    public void setFunctionMap(Map<Integer, LevelFunction<Serializable, Comparable>> functionMap) {
        this.functionMap = functionMap;
    }

    public List<ModelElement<Serializable>> getElements() {
        return elements;
    }

    protected void setElements(List<ModelElement<Serializable>> elements) {
        this.elements = elements;
    }

    public Map<Integer, List<ModelEdge>> getEdges() {
        return edges;
    }

    protected void setEdges(Map<Integer, List<ModelEdge>> edges) {
        this.edges = edges;
    }

    public Map<Integer, List<ModelElement>> getDeadElements() {
        return deadElements;
    }

    public boolean isEmpty() {
        return this.elements == null || this.elements.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public ModelDiffWrapper compare(ModelDecorator other) {
        if (other == null) {
            return null;
        }
        this.functionLevel0Based = checkLevel0Based(this.functionMap.keySet());
        //对比节点, 递归构建出modelDiff模型
        Diff<ModelElement<Serializable>> elementDiff = compareElementList(other.getElements(), this.getElements(), 0);
        List<ModelDiffElement<Serializable>> diffElementList = turnDiff2ModelDiffElement(elementDiff, 0);
        List<ModelDiffEdge> diffEdgesList = new LinkedList<>();
        //对比边
        if (this.edges.size() == 0 && other.edges.size() == 0) {
            // do nothing
        } else {
            Set<Integer> levelSet = new HashSet<>(this.edges.size() + other.edges.size());
            levelSet.addAll(this.edges.keySet());
            levelSet.addAll(other.edges.keySet());
            List<Integer> levelList = levelSet.stream().sorted().collect(Collectors.toList());
            for (Integer level : levelList) {
                Diff<ModelEdge> edgeDiff = compareEdge(this.getEdges().get(level), other.getEdges().get(level));
                ModelDiffEdge diffEdge = new ModelDiffEdge(level, edgeDiff.getCreated(), edgeDiff.getDeleted());
                diffEdgesList.add(diffEdge);
            }
        }
        ModelDiffWrapper wrapper = new ModelDiffWrapper();
        wrapper.setFunctionMap(functionMap);
        wrapper.setElementDiff(diffElementList);
        wrapper.setEdgeDiff(diffEdgesList);
        ModelDiffVisitHelper.removeRemainElement(wrapper);
        return wrapper;
    }

    private <T extends Serializable> Diff<ModelElement<T>> compareElementList(List<ModelElement<T>> s, List<ModelElement<T>> t, final int level) {
        Function<Serializable, ? extends Serializable>[] functionMapArray = this.getFunctionMap().get(this.functionLevel0Based.get(level)).getPropertyGetter();
        Function[] propFuncMap = new Function[functionMapArray.length];
        for (int i = 0; i < functionMapArray.length; i++) {
            Function<Serializable, ? extends Serializable> function = functionMapArray[i];
            propFuncMap[i] = (e) -> function.apply(((ModelElement) e).getElement());
        }

        return new Diff<ModelElement<T>>()
                .ignoreBlank()
                .keepRemain()
                .diff(s, t,
                        (ModelElement<T> e) -> this.getFunctionMap().get(this.functionLevel0Based.get(level)).getKeyGetter().apply(e.getElement()),
                        // 比对自身的属性及子节点的属性，version代码的是子节点的属性hash
                        // 这里变更的逻辑比较乱了，原本设想的是version代表的是子节点的变更，然后可以将子节点的版本保存下来，进行快速比对。但是这里因为采用递归的方式，子节点的变更仍然要再获取属性再比对一次
                        propFuncMap);
    }

    private <T extends Serializable, PK extends Comparable> int compareElement(ModelElement<T> s, ModelElement<T> t, int level) {
        Function<T, PK>[] propertyGetters = (Function<T, PK>[]) this.getFunctionMap().get(this.functionLevel0Based.get(level)).getPropertyGetter();
        if (propertyGetters.length == 0) {
            Function<T, PK> keyGetter = (Function<T, PK>) this.getFunctionMap().get(this.functionLevel0Based.get(level)).getKeyGetter();
            return keyGetter.apply(t.getElement()).compareTo(s.getElement());
        } else if (propertyGetters.length == 1) {
            PK tPK = propertyGetters[0].apply(t.getElement());
            PK sPK = propertyGetters[0].apply(s.getElement());
            return tPK == null ? (sPK == null ? 0 : -1) : sPK == null ? 1 : tPK.compareTo(sPK);
        } else {
            String meaninglessS = Stream.of(propertyGetters).map(f -> f.apply(s.getElement())).map(m -> m == null ? "" : m.toString()).collect(Collectors.joining(split));
            String meaninglessT = Stream.of(propertyGetters).map(f -> f.apply(t.getElement())).map(o -> o == null ? "" : o.toString()).collect(Collectors.joining(split));
            return meaninglessT.compareTo(meaninglessS);
        }
    }

    private Diff<ModelEdge> compareEdge(List<ModelEdge> s, List<ModelEdge> t) {
        return new Diff<ModelEdge>().diff(s, t, (ModelEdge e) -> String.join(split, e.getSource().toString(), e.getTarget().toString(), e.getExtra() == null ? "" : e.getExtra().toString()));
    }

    private <T extends Serializable> List<ModelDiffElement<T>> turnDiff2ModelDiffElement(Diff<ModelElement<T>> diff, int level) {
        List<ModelDiffElement<T>> result = new ArrayList<>(diff.getCreated().size() + diff.getUpdated().size() + diff.getDeleted().size());
        for (ModelElement<T> ele : diff.getCreated()) {
            ModelDiffElement<T> diffElement = turnCreatedAndDeletedModelElement2ModelDiffElementRecursive(ele, ModelDiffWrapper.State.CREATED);
            result.add(diffElement);
        }
        for (ModelElement<T> ele : diff.getDeleted()) {
            ModelDiffElement<T> diffElement = turnCreatedAndDeletedModelElement2ModelDiffElementRecursive(ele, ModelDiffWrapper.State.DELETED);
            result.add(diffElement);
        }
        for (ModelElement<T>[] eles : diff.getUpdated()) {
            ModelDiffElement<T> ele = turnUpdatedModelElement2ModelDiffElementRecursive(eles[0], eles[1], level, ModelDiffWrapper.State.UPDATED);
            result.add(ele);
        }
        for (ModelElement<T>[] eles : diff.getRemain()) {
            ModelDiffElement<T> ele = turnUpdatedModelElement2ModelDiffElementRecursive(eles[0], eles[1], level, ModelDiffWrapper.State.REMAIN);
            result.add(ele);
        }
        return result;
    }

    private <T extends Serializable> ModelDiffElement<T> turnCreatedAndDeletedModelElement2ModelDiffElementRecursive(ModelElement<T> element, ModelDiffWrapper.State state) {
        ModelDiffElement diff = new ModelDiffElement(state, element.getElement());
        if (element.getChildren() != null && element.getChildren().size() > 0) {
            for (ModelElement<Serializable> child : element.getChildren()) {
                diff.addChild(turnCreatedAndDeletedModelElement2ModelDiffElementRecursive(child, state));
            }
        }
        return diff;
    }

    private <T extends Serializable> ModelDiffElement<T> turnUpdatedModelElement2ModelDiffElementRecursive(ModelElement<T> original, ModelElement<T> current, int level, ModelDiffWrapper.State state) {
        ModelDiffElement<T> diff = new ModelDiffElement(state, original.getElement(), current.getElement());
        if ((original.getChildren() == null || original.getChildren().size() == 0)
                && (current.getChildren() == null || current.getChildren().size() == 0)) {
            return diff;
        } else if ((original.getChildren() == null || original.getChildren().size() == 0)) {
            for (ModelElement<Serializable> child : current.getChildren()) {
                diff.addChild(turnCreatedAndDeletedModelElement2ModelDiffElementRecursive(child, ModelDiffWrapper.State.CREATED));
            }
        } else if (current.getChildren() == null || current.getChildren().size() == 0) {
            for (ModelElement<Serializable> child : original.getChildren()) {
                diff.addChild(turnCreatedAndDeletedModelElement2ModelDiffElementRecursive(child, ModelDiffWrapper.State.DELETED));
            }
        } else {
            Diff<ModelElement<Serializable>> elementDiff = compareElementList(original.getChildren(), current.getChildren(), level + 1);
            List<ModelDiffElement<Serializable>> childrenDiff = turnDiff2ModelDiffElement(elementDiff, level + 1);
            diff.addChildren(childrenDiff);
        }
        return diff;
    }

    private List<Integer> checkLevel0Based(Collection<Integer> levels) {
        return levels.stream().sorted().collect(Collectors.toList());
    }

}

