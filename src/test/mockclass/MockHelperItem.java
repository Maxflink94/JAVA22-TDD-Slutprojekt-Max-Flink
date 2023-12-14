package test.mockclass;

import main.Item;

public class MockHelperItem extends Item{

	public MockHelperItem(String id) {
		super(id);
	}
	
	public String getId() {
		return super.id;
	}

}
