package com.backend.employee.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class FilterDtoTest {

    @Test
    public void testGetSkills() {
        FilterDto dto = new FilterDto();
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Python");
        dto.setSkills(skills);

        assertEquals(skills, dto.getSkills());
    }

    @Test
    public void testGetChecked() {
        FilterDto dto = new FilterDto();
        boolean checked = true;
        dto.setChecked(checked);

        assertEquals(checked, dto.getChecked());
    }
    
    @Test
    public void testHashCode() {
        // Create two FilterDto objects with the same values
        FilterDto filter1 = new FilterDto();
        filter1.setChecked(true);
        filter1.setSkills(List.of("Java", "Python"));

        FilterDto filter2 = new FilterDto();
        filter2.setChecked(true);
        filter2.setSkills(List.of("Java", "Python"));

        // Check that their hash codes are equal
        assertEquals(filter1.hashCode(), filter2.hashCode());
    }

    @Test
    public void testEquals() {
        // Create two FilterDto objects with the same values
        FilterDto filter1 = new FilterDto();
        filter1.setChecked(true);
        filter1.setSkills(List.of("Java", "Python"));

        FilterDto filter2 = new FilterDto();
        filter2.setChecked(true);
        filter2.setSkills(List.of("Java", "Python"));

        // Check that they are equal
        assertEquals(filter1, filter2);

        // Create another FilterDto object with different values
        FilterDto filter3 = new FilterDto();
        filter3.setChecked(false);
        filter3.setSkills(List.of("Java", "C++"));

        // Check that filter1 and filter3 are not equal
        assertNotEquals(filter1, filter3);
    }

    @Test
    public void testToString() {
        // Create a FilterDto object
        FilterDto filter = new FilterDto();
        filter.setChecked(true);
        filter.setSkills(List.of("Java", "Python"));

        // Check the toString representation
        assertEquals("FilterDto [skills=[Java, Python], checked=true]", filter.toString());
    }
}

