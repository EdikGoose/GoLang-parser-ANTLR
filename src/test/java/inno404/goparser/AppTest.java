package inno404.goparser;

import org.antlr.v4.runtime.*;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class AppTest
{

    /* Testing lexer */

    @Test
    public void testLexer1() {
        String[] tokens = getTokensFromFile("src/test/resources/tests/go_src_1.go");

        assertArrayEquals(tokens, new String[]
        {
                "LINE_COMMENT",
                "LINE_COMMENT",
                "PACKAGE", "IDENTIFIER", "END",
                "IMPORT", "STRING_LIT", "END",
                "FUNC", "IDENTIFIER", "L_PAREN", "R_PAREN", "L_CURLY",
                "IDENTIFIER", "DOT", "IDENTIFIER", "L_PAREN", "RUNE_LIT", "R_PAREN", "END",
                "IDENTIFIER", "DOT", "IDENTIFIER", "L_PAREN", "STRING_LIT", "R_PAREN", "END",
                "R_CURLY", "END"
        });
    }

    @Test
    public void testLexer2() {
        String[] tokens = getTokensFromFile("src/test/resources/tests/go_src_2.go");

        assertArrayEquals(tokens, new String[]
        {
                "LINE_COMMENT",

                "PACKAGE", "IDENTIFIER", "END",

                "IMPORT", "L_PAREN",
                "STRING_LIT", "END",
                "STRING_LIT", "END",
                "R_PAREN", "END",

                "TYPE", "IDENTIFIER", "STRUCT", "L_CURLY", "LINE_COMMENT",
                "IDENTIFIER", "IDENTIFIER", "DOT", "IDENTIFIER", "STRING_LIT", "END",
                "IDENTIFIER", "IDENTIFIER", "STRING_LIT", "END",
                "IDENTIFIER", "IDENTIFIER", "STRING_LIT", "END",
                "IDENTIFIER", "L_BRACKET", "R_BRACKET", "IDENTIFIER", "STRING_LIT", "END",
                "R_CURLY", "END",

                "GENERAL_COMMENT",

                "FUNC", "L_PAREN", "IDENTIFIER", "IDENTIFIER", "R_PAREN", "IDENTIFIER", "L_PAREN", "R_PAREN", "IDENTIFIER", "L_CURLY",
                "RETURN", "IDENTIFIER", "DOT", "IDENTIFIER", "L_PAREN", "STRING_LIT", "COMMA",
                "IDENTIFIER", "DOT", "IDENTIFIER", "COMMA", "IDENTIFIER", "DOT", "IDENTIFIER", "COMMA", "IDENTIFIER", "DOT", "IDENTIFIER", "R_PAREN", "END",
                "R_CURLY", "END",

				"FUNC", "IDENTIFIER", "L_PAREN", "R_PAREN", "L_CURLY",
				"IDENTIFIER", "DECLARE_ASSIGN", "AMPERSAND", "IDENTIFIER", "L_CURLY", "IDENTIFIER", "COLON", "INT_LIT", "COMMA", "IDENTIFIER", "COLON", "STRING_LIT", "R_CURLY", "END",
				"IDENTIFIER", "DOT", "IDENTIFIER", "ASSIGN", "L_BRACKET", "R_BRACKET", "IDENTIFIER", "L_CURLY", "STRING_LIT", "COMMA", "STRING_LIT", "R_CURLY", "END",

                "IDENTIFIER", "COMMA", "IDENTIFIER", "DECLARE_ASSIGN", "IDENTIFIER", "DOT", "IDENTIFIER", "L_PAREN", "IDENTIFIER", "COMMA", "STRING_LIT", "COMMA", "STRING_LIT", "R_PAREN", "END",
				"IDENTIFIER", "DOT", "IDENTIFIER", "L_PAREN", "IDENTIFIER", "L_PAREN", "IDENTIFIER", "R_PAREN", "R_PAREN", "END",

                "IDENTIFIER", "DOT", "IDENTIFIER", "L_PAREN", "IDENTIFIER", "DOT", "IDENTIFIER", "PLUS", "IDENTIFIER", "L_PAREN", "IDENTIFIER", "R_PAREN", "R_PAREN", "END",

                "VAR", "IDENTIFIER", "IDENTIFIER", "END",
				"IF", "IDENTIFIER", "DECLARE_ASSIGN", "IDENTIFIER", "DOT", "IDENTIFIER", "L_PAREN", "IDENTIFIER", "COMMA", "AMPERSAND", "IDENTIFIER", "R_PAREN", "END", "IDENTIFIER", "NOT_EQUALS", "IDENTIFIER", "L_CURLY",
				"IDENTIFIER", "L_PAREN", "IDENTIFIER", "R_PAREN", "END",
                "R_CURLY", "END",

				"IDENTIFIER", "DOT", "IDENTIFIER", "L_PAREN", "IDENTIFIER", "R_PAREN", "END",

				"IDENTIFIER", "DECLARE_ASSIGN", "AMPERSAND", "IDENTIFIER", "L_CURLY", "IDENTIFIER", "COLON", "INT_LIT", "COMMA", "IDENTIFIER", "COLON", "STRING_LIT", "R_CURLY", "END",
				"IDENTIFIER", "DOT", "IDENTIFIER", "ASSIGN", "L_BRACKET", "R_BRACKET", "IDENTIFIER", "L_CURLY", "STRING_LIT", "COMMA", "STRING_LIT", "R_CURLY", "END",

                "TYPE", "IDENTIFIER", "STRUCT", "L_CURLY",
				"IDENTIFIER", "IDENTIFIER", "DOT", "IDENTIFIER", "STRING_LIT", "END",
				"IDENTIFIER", "L_BRACKET", "R_BRACKET", "ASTERISK", "IDENTIFIER", "STRING_LIT", "END",
				"R_CURLY", "END",

				"IDENTIFIER", "DECLARE_ASSIGN", "AMPERSAND", "IDENTIFIER", "L_CURLY", "R_CURLY", "END",
				"IDENTIFIER", "DOT", "IDENTIFIER", "ASSIGN", "L_BRACKET", "R_BRACKET", "ASTERISK", "IDENTIFIER", "L_CURLY", "IDENTIFIER", "COMMA", "IDENTIFIER", "R_CURLY", "END",

				"IDENTIFIER", "COMMA", "IDENTIFIER", "ASSIGN", "IDENTIFIER", "DOT", "IDENTIFIER", "L_PAREN", "IDENTIFIER", "COMMA", "STRING_LIT", "COMMA", "STRING_LIT", "R_PAREN", "END",
				"IDENTIFIER", "DOT", "IDENTIFIER", "L_PAREN", "IDENTIFIER", "L_PAREN", "IDENTIFIER", "R_PAREN", "R_PAREN", "END",
				"R_CURLY", "END"
        });
    }

    @Test
    public void testLexer3() {
        String[] tokens = getTokensFromFile("src/test/resources/tests/go_src_3.go");

        assertArrayEquals(tokens, new String[]
        {
                "PACKAGE", "IDENTIFIER", "END",
                "GENERAL_COMMENT", "FUNC", "IDENTIFIER", "L_PAREN", "R_PAREN", "L_CURLY",
                "R_CURLY", "LINE_COMMENT", "END"
        });
    }



    /* Testing parser */

    @Test
    public void testParser1() {
        runParser("src/test/resources/tests/go_src_1.go");
    }

    @Test
    public void testParser2() {
        runParser("src/test/resources/tests/go_src_2.go");
    }

    @Test
    public void testParser3() {
        runParser("src/test/resources/tests/go_src_3.go");
    }

    @Test (expected = InputMismatchException.class)
    public void testParser4() {
        runParser("src/test/resources/tests/go_src_4.go");
    }



    /* Private functions */

    private void runParser(String inputFilePath) throws InputMismatchException {
        try {
            CharStream cs = CharStreams.fromFileName(inputFilePath);
            GoLexer lexer = new GoLexer(cs);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            GoParser parser = new GoParser(tokenStream);

            parser.sourceFile();

            if (parser.getNumberOfSyntaxErrors() != 0)
                throw new InputMismatchException();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String[] getTokensFromFile(String inputFilePath) {
        try {
            CharStream cs = CharStreams.fromFileName(inputFilePath);

            GoLexer lexer = new GoLexer(cs);

            var tokenList = lexer.getAllTokens();

            ArrayList<String> tokens = new ArrayList<>(tokenList.size());

            tokenList.forEach(e -> tokens.add(lexer.getVocabulary().getSymbolicName(e.getType())));
            tokens.removeIf(e -> e.equals("WHITESPACE")); // remove whitespace-tokens from token

            return tokens.toArray(String[]::new);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
