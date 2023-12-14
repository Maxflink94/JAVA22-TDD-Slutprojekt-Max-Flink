package test.mockclass;

import java.util.Queue;

import main.Buffer;
import main.Item;

public class MockHelperBuffer extends Buffer {
	
	public MockHelperBuffer(int maxCapacity) {
        super();
    }
    
    public Queue<Item> getBuffer() {
        return super.buffer;
    }
	
}
