package ru.java.api.filter;

import org.springframework.data.jpa.domain.Specification;

@FunctionalInterface
public interface Filter<T> {
    Specification<T> toSpecification();
}
