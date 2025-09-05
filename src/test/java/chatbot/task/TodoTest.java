package chatbot.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoTest {
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Finish homework");
        // Newly created todos should not be marked as done
        assertFalse(todo.isDone());
        assertEquals("[T][ ] Finish homework", todo.toString());
    }

    @Test
    public void testMarkDone() {
        Todo todo = new Todo("Finish homework");
        todo.markDone();
        assertTrue(todo.isDone());
        assertEquals("[T][X] Finish homework", todo.toString());
    }

    @Test
    public void testUnmarkDone() {
        Todo todo = new Todo("Finish homework");
        todo.markDone();   // mark it first
        todo.unmarkDone(); // then unmark
        assertFalse(todo.isDone());
        assertEquals("[T][ ] Finish homework", todo.toString());
    }
}