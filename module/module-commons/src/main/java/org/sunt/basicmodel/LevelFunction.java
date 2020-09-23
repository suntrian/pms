package org.sunt.basicmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.function.Function;

@SuppressWarnings("unchecked")
@Accessors(chain = true)
@NoArgsConstructor
public class LevelFunction<T extends Serializable, PK extends Comparable> implements Serializable {
  private static final long serialVersionUID = -2565599590654233367L;
  @Getter
  Function<T, PK> keyGetter;
  @Getter
  Function<T, PK> parentGetter;
  @Getter
  Function<T, ? extends Serializable>[] propertyGetter;

  public LevelFunction<T, PK> setKeyGetter(SerializableFunction<? extends T, PK> keyGetter) {
    this.keyGetter = (Function<T, PK>) keyGetter;
    return this;
  }

  public LevelFunction<T, PK> setParentGetter(SerializableFunction<? extends T, PK> parentGetter) {
    this.parentGetter = (Function<T, PK>) parentGetter;
    return this;
  }

  @SafeVarargs
  public final <S extends Serializable> LevelFunction<T, PK> setPropertyGetter(SerializableFunction<T, S>... propertyGetter) {
    this.propertyGetter = propertyGetter;
    return this;
  }

  public interface SerializableFunction<T, R> extends Function<T, R>, Serializable {
  }

}

