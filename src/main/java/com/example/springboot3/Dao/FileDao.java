package com.example.springboot3.Dao;

import static com.example.springboot3.TableAlias.*;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FileDao {

	private final DSLContext dsl;

	public String findFilePath(int fileId) {
		return dsl.select(
					  FI.SAVE_FILE_NAME
				  )
				  .from(FI)
				  .where(FI.ID.eq(fileId))
				  .fetchOneInto(String.class);

	}
}
