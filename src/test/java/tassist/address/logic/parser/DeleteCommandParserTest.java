package tassist.address.logic.parser;

import static tassist.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tassist.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tassist.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tassist.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import tassist.address.logic.commands.DeleteCommand;
import tassist.address.model.person.StudentId;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_validStudentId_returnsDeleteCommand() {
        assertParseSuccess(parser, "A1111111B", new DeleteCommand(new StudentId("A1111111B")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
