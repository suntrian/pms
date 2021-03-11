package org.sunt.sqlanalysis.model;

public class Alias {

    public static final Alias EMPTY = new Alias(null);

    private final boolean as;
    private final String alias;

    private int aliasStart = -1;
    private int aliasStop = -1;

    public Alias(String alias) {
        this.alias = alias;
        this.as = true;
    }

    public Alias(String alias, boolean as) {
        this.as = as;
        this.alias = alias;
    }

    public boolean isEmpty() {
        return this == EMPTY || this.alias == null || this.alias.isEmpty();
    }


    public String getAlias() {
        return alias;
    }

    public int getStart() {
        return aliasStart;
    }

    public int getStop() {
        return aliasStop;
    }

    public Alias setPos(int start, int stop){
        this.aliasStart = start;
        this.aliasStop = stop;
        return this;
    }

    @Override
    public String toString() {
        return alias==null?"" : alias;
    }
}
