package org.sunt.sqlanalysis.model;

public interface Expression extends Cloneable{

    String getExpression();

    Expression clone();

    void setCharPos(int charStartIndex, int charStopIndex);

    void setTokenPos(int tokenStartIndex, int tokenStopIndex);

    int getCharStart();
    int getCharStop();

    int getTokenStart();
    int getTokenStop();

}
