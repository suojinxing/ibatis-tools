package org.workp.core.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.workp.common.tag.ValueObject;

@Getter
@AllArgsConstructor
@ValueObject
public class IpAddress {
    private String ip;
}
