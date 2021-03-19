package org.sunt.sqlanalysis.model;

import java.util.List;
import java.util.stream.Collectors;

public class WindowField extends FunctionField{

    private List<Expression> partitionByFields;

    private List<OrderByItem> orderByFields;

    private WindowFrame windowFrame;

    public WindowField(String expression) {
        super(expression);
    }

    public WindowField feed(List<Expression> partitionByFields, List<OrderByItem> orderByFields) {
        this.partitionByFields = partitionByFields;
        this.orderByFields = orderByFields;
        return this;
    }

    public WindowField feed(WindowFrame windowFrame) {
        this.windowFrame = windowFrame;
        return this;
    }

    public WindowField feed(FunctionField windowFunc) {
        this.functionName = windowFunc.functionName;
        this.params = windowFunc.params;
        return this;
    }

    public List<Expression> getPartitionByFields() {
        return partitionByFields;
    }

    public List<OrderByItem> getOrderByFields() {
        return orderByFields;
    }

    @Override
    public DataType getDataType() {
        //TODO
        return null;
    }

    @Override
    public WindowField clone() {
        return new WindowField(expression)
                .feed(super.clone())
                .feed(partitionByFields.stream().map(Expression::clone).collect(Collectors.toList()),
                        orderByFields.stream().map(OrderByItem::clone).collect(Collectors.toList()))
                .feed(windowFrame);
    }

    @Override
    public String toString() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(super.toString()).append(" OVER (");
        if (partitionByFields!=null && !partitionByFields.isEmpty()) {
            sqlBuilder.append(" PARTITION BY ");
            for (Expression partitionByField : partitionByFields) {
                sqlBuilder.append(partitionByField).append(", ");
            }
            sqlBuilder.setLength(sqlBuilder.length()-2);
        }
        if (orderByFields != null && !orderByFields.isEmpty()) {
            sqlBuilder.append(" ORDER BY ");
            for (OrderByItem orderByField : orderByFields) {
                sqlBuilder.append(orderByField).append(", ");
            }
            sqlBuilder.setLength(sqlBuilder.length()-2);
        }
        if (windowFrame != null) {
            sqlBuilder.append(windowFrame);
        }
        sqlBuilder.append(") ");
        return sqlBuilder.toString();
    }

    public enum Direction {
        PRECEDING,
        FOLLOWING
    }

    public enum WindowFrameUnit{
        ROWS,
        RANGE,
        GROUPS
    }

    public static class WindowFrame {

        private WindowFrameUnit frameUnit;

        private WindowBound from;

        private WindowBound to;

        public WindowFrame(WindowFrameUnit frameUnit, WindowBound from) {
            this.frameUnit = frameUnit;
            this.from = from;
        }

        public WindowFrame(WindowFrameUnit frameUnit, WindowBound from, WindowBound to) {
            this.frameUnit = frameUnit;
            this.from = from;
            this.to = to;
        }

        public static class WindowBound{

            public static final WindowBound UNBOUNDED_PRECEDING = new WindowBound(Direction.PRECEDING);
            public static final WindowBound UNBOUNDED_FOLLOWING = new WindowBound(Direction.FOLLOWING);
            public static final WindowBound CURRENT_ROW = new WindowBound();

            private boolean currentRow = false;
            private boolean unbounded = false;
            private Integer lines;
            private final Direction direction;

            private WindowBound(){
                this.currentRow = true;
                this.direction = null;
            }

            private WindowBound(Direction direction) {
                this.direction = direction;
                this.unbounded = true;
            }

            private WindowBound(Integer n, Direction direction){
                this.lines = n;
                this.direction = direction;
            }

            public static WindowBound preceding(Integer lines){
                return new WindowBound(lines, Direction.PRECEDING);
            }

            public static WindowBound following(Integer lines) {
                return new WindowBound(lines, Direction.FOLLOWING);
            }

            @Override
            public String toString(){
                if (currentRow){
                    return "CURRENT ROW";
                }
                StringBuilder builder = new StringBuilder();
                if (unbounded){
                    builder.append("UNBOUNDED");
                } else {
                    builder.append(lines);
                }
                switch (direction){
                    case FOLLOWING:
                        builder.append(" FOLLOWING");
                        break;
                    case PRECEDING:
                        builder.append(" PRECEDING");
                }
                return builder.toString();
            }
        }

        @Override
        public String toString() {
            if (frameUnit==null) {
                return "";
            }
            StringBuilder sqlBuilder = new StringBuilder();
            switch (frameUnit) {
                case ROWS:
                    sqlBuilder.append("ROWS");
                    break;
                case RANGE:
                    sqlBuilder.append("RANGE");
                    break;
                case GROUPS:
                    sqlBuilder.append("GROUPS");
                    break;
            }
            if (to != null) {
                sqlBuilder.append(" BETWEEN ");
            }
            sqlBuilder.append( from.toString() );
            if (to != null) {
                sqlBuilder.append(" AND ")
                        .append(to.toString());
            }
            return sqlBuilder.toString();
        }

    }

}
