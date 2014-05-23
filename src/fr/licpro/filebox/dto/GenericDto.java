/*
 * Software property of MILLAU Julien. Copyright Copyright (c) 2014.
 */
package fr.licpro.filebox.dto;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Generic Dto
 *
 * @author julien
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public abstract class GenericDto implements Serializable {

    /**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -207819771276313724L;

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
