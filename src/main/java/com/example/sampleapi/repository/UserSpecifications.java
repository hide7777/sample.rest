package com.example.sampleapi.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.example.sampleapi.entity.Users;

public class UserSpecifications {
    /**
     * idを含むデータを検索する。
     */
    public static Specification<Users> idContains(Long id) {
        return StringUtils.isEmpty(id.toString()) ? null : (root, query, cb) -> {
                return cb.equal(root.get("id"), id);
        };
    }

    /**
     * ユーザー名を含むデータを検索する。
     */
    public static Specification<Users> nameContains(String name) {
        return StringUtils.isEmpty(name) ? null : (root, query, cb) -> {
            return cb.like(root.get("name"), "%" + name + "%");
        };
    }

    /**
     * emailを含むデータを検索する。
     */
    public static Specification<Users> emailContains(String email) {
        return StringUtils.isEmpty(email) ? null : (root, query, cb) -> {
            return cb.like(root.get("email"), "%" + email + "%");
        };
    }
}
