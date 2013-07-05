package jzi.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jzi.model.map.Field;
import jzi.model.map.FieldPosition;
import jzi.model.map.IField;
import jzi.model.map.ITileType;
import jzi.model.map.Tile;
import jzi.model.map.TileType;
import jzi.view.TileGraphic;
import jzi.view.Warnings;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * XML-Parsing using SAX. Class parses the XML file,extracts and sets the
 * configuration for the Tile and Field classes.
 * 
 * @author MaiBot guest24
 * 
 */
public class ParseXMLConfiguration extends DefaultHandler implements
        IParseXMLConfiguration {
    /**
     * List of ITileType
     */
    private List<ITileType> tileList;

    /**
     * Matrix of fields.
     */
    private List<IField> fieldList;

    /**
     * File to be read out.
     */
    private String fileNameXML;

    /**
     * Temporary tile object.
     */
    private ITileType tmpTile;

    /**
     * Temporary field object.
     */
    private IField tmpField;

    /**
     * Constructor sets the variables and calls the method parseXMLDocument().
     * 
     * @param fileNameXML
     *            - file with XML configuration
     */
    public ParseXMLConfiguration(final String fileNameXML, boolean silent) {
        this.fileNameXML = fileNameXML;
        tileList = new ArrayList<ITileType>();
        fieldList = new ArrayList<IField>();

        parseXMLDocument(silent);
    }

    /**
     * Private method is called by the its constructor.The SAX parser is created
     * by the SAX parser factory.
     */
    private void parseXMLDocument(boolean silent) {
        // "parser factory" for creating SAX parser
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        // Now using the factory to create a SAX parser,which parses the XML
        // file
        try {
            SAXParser saxParser = saxFactory.newSAXParser();
            saxParser.parse(fileNameXML, this);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            System.out
                    .println("Something is wrong with the configuration of the parser!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not find file!");
        } catch (SAXException e) {
            e.printStackTrace();
            if (!silent) {
                Warnings.xmlError();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("InputStream cannot be written to or closed!");
        }
    }

    /**
     * Method called at start of a document element.Every time the parser
     * encounters the beginning of a new element,it calls this method,which
     * create a new temporary object.
     * 
     * @param uri
     *            - the Namespace URI, or the empty string if the element has no
     *            Namespace URI or if Namespace processing is not being
     *            performed
     * @param localName
     *            - the local name (without prefix), or the empty string if
     *            Namespace processing is not being performed
     * @param elementStartName
     *            - opening tag
     * @param attributes
     *            - attached to the element,if there are no attributes, it shall
     *            be an empty Attributes object,the value of this object after
     *            startElement returns is undefined
     * @throws SAXException
     *             there was an error parsing the document
     */
    @Override
    public final void startElement(final String uri, final String localName,
            final String elementStartName, final Attributes attributes)
            throws SAXException {
        // if opening tag 'maptile' is detected,set its attributes
        if (elementStartName.equalsIgnoreCase("maptile")) {
            startTile(attributes);
        }
        // if opening tag 'field' is detected,set its attributes
        if (elementStartName.equalsIgnoreCase("field")) {
            startField(attributes);
        }
    }

    /**
     * Private method is called when the start element name is 'maptile'.
     * 
     * @param attributes
     *            of start element 'maptile'
     */
    public final void startTile(final Attributes attributes) {
        tmpTile = new TileType();

        if (attributes.getValue("name") != null) {
            tmpTile.setName(attributes.getValue("name"));
        } else {
            tmpTile.setName("");
        }

        tmpTile.setCount(Integer.parseInt(attributes.getValue("count")));

        String img = attributes.getValue("img");
        // name is img value without '.png'
        final String tname = img.substring(0, img.lastIndexOf('.'));

        tmpTile.setFileName(tname);

        new Thread(new Runnable() {
            @Override
            public void run() {
                TileGraphic.loadImage(tname);
            }
        }).start();

        if (attributes.getValue("zombies") != null) {
            tmpTile.setZombieCount(Integer.parseInt(attributes
                    .getValue("zombies")));
        }
        if (attributes.getValue("life") != null) {
            tmpTile.setLifeCount(Integer.parseInt(attributes.getValue("life")));
        }
        if (attributes.getValue("ammo") != null) {
            tmpTile.setAmmoCount(Integer.parseInt(attributes.getValue("ammo")));
        }
    }

    /**
     * Private method is called when the start element name is 'field'.
     * 
     * @param attributes
     *            of start element 'field'
     */
    private void startField(final Attributes attributes) {
        String attPosition = attributes.getValue("pos").toUpperCase();

        tmpField = new Field();

        tmpField.setPosition(FieldPosition.valueOf(attPosition));

        tmpField.setType(attributes.getValue("type"));

        if (attributes.getValue("left") != null) {
            tmpField.setLeft();
        }

        if (attributes.getValue("right") != null) {
            tmpField.setRight();
        }

        if (attributes.getValue("up") != null) {
            tmpField.setUp();
        }

        if (attributes.getValue("down") != null) {
            tmpField.setDown();
        }
    }

    /**
     * Method called at end of a document element.
     * 
     * @param uri
     *            - the Namespace URI, or the empty string if the element has no
     *            Namespace URI or if Namespace processing is not being
     *            performed
     * @param localName
     *            - the Namespace URI, or the empty string if the element has no
     *            Namespace URI or if Namespace processing is not being
     *            performed
     * @param elementEndName
     *            - closing tag
     * @throws SAXException
     *             if parsing encounters an error
     */
    @Override
    public final void endElement(final String uri, final String localName,
            final String elementEndName) throws SAXException {

        // if closing tag "maptile" is detected get the 9 fields of each tile
        if (elementEndName.equalsIgnoreCase("maptile")) {
            for (int i = 0; i < fieldList.size(); i++) {
                tmpTile.setField(i / Tile.WIDTH_FIELDS, i % Tile.WIDTH_FIELDS,
                        fieldList.get(i));
            }

            fieldList.clear();

            // if count = 4 we need to copy this tile three times
            int tileCount = tmpTile.getCount();

            while (tileCount > 1) {
                ITileType tileCopy = new TileType(tmpTile);
                tileList.add(tileCopy);
                tileCount--;
            }

            // finally add tmpTile to tileList
            tileList.add(tmpTile);
        }

        // if closing tag "field" is detected add the 9 field in sequence to the
        // field list
        if (elementEndName.equalsIgnoreCase("field")) {
            fieldList.add(tmpField);
        }
    }

    /**
     * Getter for list with 30 sorted tiles,first tile will be 'Town-Center' and
     * last tile will be 'Helicopter'.
     * 
     * @return List with sorted tiles
     */
    public final List<ITileType> returnTileList() {
        ITileType tmpFirst = null;
        ITileType tmpLast = null;

        for (ITileType tile : tileList) {
            if (tile.getName() != null && tile.getName().equals("Town-Center")) {
                tmpFirst = tile;
            } else if (tile.getName() != null
                    && tile.getName().equals("Helicopter")) {
                tmpLast = tile;
            }
        }

        tileList.remove(tmpFirst);
        tileList.remove(tmpLast);

        Collections.shuffle(tileList);

        tileList.add(0, tmpFirst);
        tileList.add(tmpLast);

        return tileList;
    }
}
