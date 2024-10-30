package com.example.springboot3.Dao;

import static org.junit.jupiter.api.Assertions.*;

import org.jooq.Record3;
import org.jooq.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryApiDaoTest {
    @Autowired
    private CategoryApiDao categoryApiDao;

    @Test
    public void testGetCategoryWithProductCount() {
        Result<Record3<Integer, String, Integer>> result = categoryApiDao.getCategoryWithProductCount();

        // Display the results in the console
        result.forEach(record -> System.out.println(
            "Category ID: " + record.get("id") +
                ", Name: " + record.get("name") +
                ", Product Count: " + record.get("count")
        ));

        // Add assertions to check that the query returns the expected values
        assertNotNull(result);
        assertTrue(result.size() > 0, "The result should contain records");
    }
}