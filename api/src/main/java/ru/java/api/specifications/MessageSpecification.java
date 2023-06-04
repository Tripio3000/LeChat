package ru.java.api.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.java.api.domain.MessageEntity;

import java.util.List;

import static ru.java.api.specifications.CommonSpecifications.*;

@UtilityClass
public class MessageSpecification {

    public static Specification<MessageEntity> hasUserId(List<Integer> userIds) {
        return in("userId", userIds);

    }

    public static Specification<MessageEntity> hasProgress(String progress) {
        return ((root, query, cb) -> getStringCriteria("progress", progress, cb, root));
    }

}
