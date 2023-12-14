package test.mockclass;

import main.Consumer;
import main.Item;

public class MockConsumer implements Consumer {

	MockHelperBuffer mockBuffer;
	
    public MockConsumer(MockHelperBuffer mockBuffer) {
        this.mockBuffer = mockBuffer;
    }
    
    public Item removeItem() {
        return mockBuffer.remove();
    }
	
	@Override
	public void run() {
		
	}

	@Override
	public void stopRunning() {
		
	}

}
