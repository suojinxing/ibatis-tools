package org.workp.core.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.workp.common.tag.ValueObject;

@Getter
@AllArgsConstructor
@ToString
@ValueObject
public class ParsedLog {
    private String originLogStr;
    private String parsedResultStr;
    private String prettyParsedResultStr;


}
