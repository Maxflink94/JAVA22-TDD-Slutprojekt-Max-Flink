package test.mockclass;

import main.Item;
import main.Producer;

public class MockProducer implements Producer {
	
    MockHelperBuffer mockBuffer;
    
    public MockProducer(MockHelperBuffer mockBuffer) {
        this.mockBuffer = mockBuffer;
    }
    
    public boolean addItem(Item item) {
        return mockBuffer.add(item);
    }
	
	@Override
	public void run() {
		
	}

	@Override
	public void stopRunning() {
		
	}

}
