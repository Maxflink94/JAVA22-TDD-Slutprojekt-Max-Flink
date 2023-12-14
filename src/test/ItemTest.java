package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import test.mockclass.MockHelperItem;

class ItemTest {
	
	private MockHelperItem item;
	
	@BeforeEach
	public void initItem() {
		item = new MockHelperItem("TestItem");
	}

	@DisplayName("Testing that the items gets the correct ID in lowercase")
	@Test
	public void testItemId() {
		assertEquals("testitem", item.getId());
	}
	
	@DisplayName("Testing to see if the setId-method changes the ID correctly")
	@Test
	public void testSetItemId() {
		item.setId("NewTestId");
		assertEquals("newtestid", item.getId());
	}
	
	@DisplayName("Testing the setId-method with special characters")
	@Test
	public void testSetItemIdWithSpecialCharacters() {
	    item.setId("NewTestId!@#123");
	    assertEquals("newtestid!@#123", item.getId());
	}
	
    @DisplayName("Testing special characters in ID")
    @Test
    public void testSpecialCharactersID() {
        String specialCharactersId = "!@#$%^&*()_+";
        MockHelperItem itemSpecial = new MockHelperItem(specialCharactersId);
        
        assertEquals(specialCharactersId.toLowerCase(), itemSpecial.getId());
    }
    
    @DisplayName("Testing conversion of special characters to lowercase in ID")
    @Test
    public void testSpecialCharactersToLowerCase() {
        String specialCharactersId = "Special-@#Characters";
        MockHelperItem itemSpecial = new MockHelperItem(specialCharactersId);
        
        assertEquals("special-@#characters", itemSpecial.getId());
    }
	
	@DisplayName("Testing the toString-method")
    @Test
    public void testToString() {
        assertEquals("testitem", item.toString());
    }
	
    @DisplayName("Testing uppercase to lowercase conversion in ID")
    @Test
    public void testUpperCaseToLowerCase() {
        String upperCaseId = "UpperCaseID";
        MockHelperItem itemUpperCase = new MockHelperItem(upperCaseId);
        
        assertEquals(upperCaseId.toLowerCase(), itemUpperCase.getId());
    }
	
	@DisplayName("Testing minimum value for ID")
	@Test
	public void testEmptyId() {
		String minId = "";
		MockHelperItem itemMin = new MockHelperItem(minId);
		
		assertEquals(minId.toLowerCase(), itemMin.getId());
	}
	
	@DisplayName("Testing maximum value for ID")
	@Test
	public void testMaximumId() {
		String maxId = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
		MockHelperItem itemMin = new MockHelperItem(maxId);
		
		assertEquals(maxId.toLowerCase(), itemMin.getId());
	}

	@DisplayName("Testing how item handles null as input in Constructor")
	@Test
	public void testNullId() {
	    assertThrows(NullPointerException.class, () -> new MockHelperItem(null));
	    
	    assertThrows(NullPointerException.class, () -> item.setId(null));
	}
	
	@DisplayName("Testing invalid datatype for ID (int)")
	@Test
	public void testInvalidDataTypeForSetId() {
	    int invalidId = 123;
	    assertThrows(ClassCastException.class, () -> item.setId(String.valueOf(invalidId)));
	}

}
