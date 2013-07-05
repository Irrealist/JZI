package jzi.model.map;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TileTypeTest {
    private static ITileType type;

    /**
     * Initializes the tile type.
     */
    @BeforeClass
    public static void setup() {
        type = new TileType();

        for (int x = 0; x < Tile.WIDTH_FIELDS; x++) {
            for (int y = 0; y < Tile.HEIGHT_FIELDS; y++) {
                type.setField(y, x, new FieldStub());
            }
        }
    }

    /**
     * Tests the TileType.createTile() method, makes sure the tile type is
     * inherited.
     */
    @Test
    public void testCreate() {
        ITile tile = type.createTile();

        assertTrue(tile.getTileType().equals(type));
    }
}
