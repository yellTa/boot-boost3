package com.example.springboot3.Dao;

import static com.example.springboot3.TableAlias.U;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final DSLContext dsl;

    public int getUserid(String username) {

        return dsl.select(
            U.field("id").as("id"))
            .from(U)
            .where(U.field("username", String.class).eq(username))
            .fetchOne(0, int.class);
    }
}
