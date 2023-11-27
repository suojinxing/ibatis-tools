package org.workp.common.tag;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface ValueObject {
}
