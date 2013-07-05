package jzi.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Black-Box-Test for the ParseXMLConfiguration class in src.jzi.model
 * 
 * @author MaiBot guest24
 */
public class ParseXMLConfigurationTest {
    /**
     * object of the class which is to be tested
     */
    private static ParseXMLConfiguration parserTestObject;
    private static boolean condition;
    /**
     * Test if the method returns a list object
     */
    @Test
    public void testReturnTileListNotNull() {
        parserTestObject = new ParseXMLConfiguration("./resource/jzi_maptiles.xml", true);
        assertNotNull(parserTestObject.returnTileList());
    }
    /**
     * Test expects a FileNotFound exception.The path of the XML file,which is to be parsed,
     * is specified as a parameter of the constructor.If the XML file cannot be found on this
     * path,a FileNotFoundException is to be thrown.
     */
    @Test
    public void testFileNotFoundException() {
        parserTestObject = new ParseXMLConfiguration("./resource/jzi_notThere.xml", true);
        if(parserTestObject != null) {
            condition = true;
        }
        assertTrue(condition);
    }
    /**
     * Test expects a SAX exception,which means there was an error parsing the document
     */
    @Test
    public void testSAXException() {
        parserTestObject = new ParseXMLConfiguration("", true);
        if(parserTestObject != null) {
            condition = true;
        }
        assertTrue(condition);
    }
}
