package com.web.backend.config.filter;

import com.web.backend.config.filter.interfaces.SoftDeletable;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationFilter {
    public static <T extends SoftDeletable> Specification<T> isNotDeleted() {
        return (root, query, builder) -> builder.equal(root.get("isDeleted"), false);
    }

    public static <T> Specification<T> fieldEquals(String fieldName, Object value) {
        return (root, query, builder) -> builder.equal(root.get(fieldName), value);
    }
}
