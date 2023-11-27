package org.workp.common.tag;

import java.lang.annotation.*;

/**
 * 聚合根唯一标识
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD})
@Inherited
public @interface EntityId {
}
