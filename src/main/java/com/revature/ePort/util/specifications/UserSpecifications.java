package com.revature.ePort.util.specifications;

import com.revature.ePort.user.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecifications {

    public static Specification<User> userByColumnNameAndValue(String columnName, String filter) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.like(root.<String>get(columnName), "%"+filter+"%");
            }
        };
    }
}
