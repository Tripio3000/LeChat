package ru.java.api.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@UtilityClass
public class CommonSpecifications {

    public static <T, V> Specification<T> in(String attr, Collection<V> value) {
        return CollectionUtils.isEmpty(value) ? null : (root, query, cb) -> root.get(attr).in(value);
    }

    public static Predicate getStringCriteria(String field, String value, CriteriaBuilder cb, From root) {
        return (value == null) ? null : cb.equal(root.get(field), value);
    }

}
