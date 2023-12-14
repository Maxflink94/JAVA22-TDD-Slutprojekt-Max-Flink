package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Item;
import test.mockclass.MockConsumer;
import test.mockclass.MockHelperBuffer;
import test.mockclass.MockHelperItem;
import test.mockclass.MockProducer;

class BufferTest {
	
	private MockHelperBuffer buffer;
	
	@BeforeEach
	public void initBuffer() {
		int maxCapacity = 5;
		buffer = new MockHelperBuffer(maxCapacity);
	}
	
	@DisplayName("Testing adding null value to the buffer")
	@Test
	public void testAddNullValues() {
	    assertTrue(buffer.add(null));
	}
	
	@DisplayName("Testing to add to buffer with Producer")
	@Test
	public void testProducerAddToBuffer() {
		MockProducer mockProducer = new MockProducer(buffer);
		Item item = new Item("TestItem");
		
		assertTrue(mockProducer.addItem(item));
	}
	
	@DisplayName("Testing to remove from buffer with Consumer")
	@Test
	public void testConsumerRemoveFromBuffer() {
		MockConsumer mockConsumer = new MockConsumer(buffer);
		MockProducer mockProducer = new MockProducer(buffer);
		Item item = new Item("TestItem");
		
		mockProducer.addItem(item);
		Item removedItem = mockConsumer.removeItem();
		assertNotNull(removedItem);
		assertSame(item, removedItem);
	}
	
    @DisplayName("Testing if the add method throws a NullPointerException when it adds null to the buffer")
    @Test
    public void testAddNullItem() {
        assertThrows(NullPointerException.class, () -> buffer.add(null));
    }
	
	@DisplayName("Testing the add to buffer method")
	@Test
	public void testAddToBuffer() {
		Item item = new Item("TestItem");
		assertTrue(buffer.add(item));
		
	}
	
	@DisplayName("Testing the remove to buffer method")
	@Test
	public void testRemoveFromBuffer() {
		Item item = new Item("TestItem");
		buffer.add(item);
		Item removedItem = buffer.remove();
		assertNotNull(removedItem);
	}
	
	@DisplayName("Testing to add and remove multiple items to/from the buffer")
	@Test
	public void testAddAndRemoveMultipleItems() {
	    MockHelperItem item1 = new MockHelperItem("Item1");
	    MockHelperItem item2 = new MockHelperItem("Item2");

	    assertTrue(buffer.add(item1));
	    assertTrue(buffer.add(item2));

	    Item removedItem1 = buffer.remove();
	    assertNotNull(removedItem1);
	    assertSame(item1, removedItem1);

	    Item removedItem2 = buffer.remove();
	    assertNotNull(removedItem2);
	    assertSame(item2, removedItem2);
	}
	
    @DisplayName("Testing the buffer content")
    @Test
    public void testBufferContent() {
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");

        buffer.add(item1);
        buffer.add(item2);

        Queue<Item> bufferContent = buffer.getBuffer();

        assertEquals(2, bufferContent.size());
    }
	
    @DisplayName("Testing wait functionality")
    @Test
    public void testWaitingFunctionality() throws InterruptedException {

        Thread thread = new Thread(() -> {
            synchronized (buffer) {
                buffer.remove();
            }
        });

        thread.start();
        Thread.sleep(100);

        assertEquals(Thread.State.WAITING, thread.getState());

        thread.interrupt();

        thread.join();
    }
    
    @DisplayName("Testing to fill the buffer and add items to a full buffer")
    @Test
    public void testAddToFullBuffer() {
        int maxCapacity = 5;
        MockHelperBuffer buffer = new MockHelperBuffer(maxCapacity);

        for (int i = 0; i < maxCapacity; i++) {
            buffer.add(new Item("Item" + i));
        }

        assertEquals(maxCapacity, buffer.getBuffer().size());

        buffer.add(new Item("ExtraItem"));

        // Rött då där inte finns någon max storlek på baskods buffern.
        assertEquals(maxCapacity, buffer.getBuffer().size());
    }
    
    @DisplayName("Testing remove from a full buffer")
    @Test
    public void testRemoveFromFullBuffer() {
        int maxCapacity = 5;
        for (int i = 0; i < maxCapacity; i++) {
            assertTrue(buffer.add(new Item("Item" + i)));
        }
        assertNotNull(buffer.remove());
        assertTrue(buffer.add(new Item("NewItem")));
    }
	
}
