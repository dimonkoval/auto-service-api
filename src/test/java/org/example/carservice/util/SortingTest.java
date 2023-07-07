package org.example.carservice.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

class SortingTest {
    @Test
    void getSortFromRequestParam_Ok() {
        Sort actual = Sorting.getSortFromRequestParam("id:ASC");
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertEquals("ASC", Objects.requireNonNull(actual.getOrderFor("id")).getDirection().name());
    }
}