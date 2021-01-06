package org.sunt.basicmodel;

import java.io.Serializable;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class LevelFunction<T extends Serializable, PK extends Comparable> implements Serializable {
  private static final long serialVersionUID = -2565599590654233367L;

  private Function<T, PK> keyGetter;

  private Function<T, PK> parentGetter;

  private Function<T, ? extends Serializable>[] propertyGetter;

  public Function<T, PK> getKeyGetter() {
    return this.keyGetter;
  }

  public LevelFunction<T, PK> setKeyGetter(Function<? extends T, PK> keyGetter) {
    this.keyGetter = (Function<T, PK>) keyGetter;
    return this;
  }

  public Function<T, PK> getParentGetter() {
    return parentGetter;
  }

  public LevelFunction<T, PK> setParentGetter(Function<? extends T, PK> parentGetter) {
    this.parentGetter = (Function<T, PK>) parentGetter;
    return this;
  }

  public Function<T, ? extends Serializable>[] getPropertyGetter() {
    return propertyGetter;
  }

  @SafeVarargs
  public final <S extends Serializable> LevelFunction<T, PK> setPropertyGetter(Function<T, S>... propertyGetter) {
    this.propertyGetter = propertyGetter;
    return this;
  }
}

