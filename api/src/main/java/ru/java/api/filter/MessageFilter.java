package ru.java.api.filter;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.Specification;
import ru.java.api.domain.MessageEntity;
import ru.java.api.specifications.CommonSpecifications;
import ru.java.api.specifications.MessageSpecification;

import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class MessageFilter implements Filter<MessageEntity> {

    private List<Integer> userIds;
    private String progress;

    @Override
    public Specification<MessageEntity> toSpecification() {
        return Specification.where(MessageSpecification.hasUserId(getUserIds()))
                .and(MessageSpecification.hasProgress(getProgress()));
    }

}
