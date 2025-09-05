package chatbot.parser;

import chatbot.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseTodoCommand() throws Exception {
        String input = "todo Finish homework";
        var command = Parser.parse(input);
        assertEquals("AddCommand", command.getClass().getSimpleName());
    }

    @Test
    public void testParseTodoEmptyDescription() {
        Exception exception = assertThrows(Exception.class, () -> {
            Parser.parse("todo");
        });
        assertEquals("The description of a todo cannot be empty.", exception.getMessage());
    }

    @Test
    public void testParseMarkCommand() throws Exception {
        String input = "mark 1";
        var command = Parser.parse(input);
        assertEquals("MarkCommand", command.getClass().getSimpleName());
    }

    @Test
    public void testParseUnmarkCommand() throws Exception {
        String input = "unmark 2";
        var command = Parser.parse(input);
        assertEquals("MarkCommand", command.getClass().getSimpleName());
    }

    @Test
    public void testParseDeleteCommand() throws Exception {
        String input = "delete 3";
        var command = Parser.parse(input);
        assertEquals("DeleteCommand", command.getClass().getSimpleName());
    }

    @Test
    public void testParseInvalidCommand() {
        Exception exception = assertThrows(Exception.class, () -> {
            Parser.parse("foobar");
        });
        assertEquals("I'm sorry, but I don't know what that means.", exception.getMessage());
    }

    @Test
    public void testParseByeCommand() throws Exception {
        String input = "bye";
        var command = Parser.parse(input);
        assertEquals("ExitCommand", command.getClass().getSimpleName());
    }

    @Test
    public void testParseListCommand() throws Exception {
        String input = "list";
        var command = Parser.parse(input);
        assertEquals("ListCommand", command.getClass().getSimpleName());
    }

    @Test
    public void testParseSaveCommand() throws Exception {
        String input = "save";
        var command = Parser.parse(input);
        assertEquals("SaveCommand", command.getClass().getSimpleName());
    }
}
