package jzi.view;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import jzi.model.map.Direction;
import jzi.model.map.IDirection;
import jzi.model.map.ITile;
import jzi.model.map.Tile;

/**
 * Loads all {@link ITile} images. Images are automatically rotated and scaled.
 * 
 * @author Tobias Groth, Buddy Jonte
 * 
 */
public class TileGraphic {
    /**
     * Map of rotated tile images.
     */
    private static HashMap<String, HashMap<IDirection, BufferedImage>> renderImage = new HashMap<>();
    /**
     * Map of scaled and rotated tile images.
     */
    private static HashMap<String, HashMap<IDirection, BufferedImage>> scaleImage = new HashMap<>();
    /**
     * Size of scaled images.
     */
    private static final int ICON_SIZE = 150;

    /**
     * Gets the scaled Icon of a Tile to be used in the GUI.
     * 
     * @param tile
     *            - {@link ITile} to get Icon for
     * @return {@link javax.swing.ImageIcon} for the specified {@link ITile}
     */
    public static ImageIcon getIcon(ITile tile) {
        return new ImageIcon(scaleImage.get(tile.getTileType().getFileName())
                .get(tile.getRotation()));
    }

    /**
     * Gets the Image of a Tile to be used in the MapPane.
     * 
     * @param tile
     *            - {@link ITile} to get Image for
     * @return {@link java.awt.image.BufferedImage} for the specified
     *         {@link ITile}
     */
    public static BufferedImage getRenderImage(ITile tile) {
        return renderImage.get(tile.getTileType().getFileName()).get(
                tile.getRotation());
    }

    /**
     * Loads a Tile image, rotates, and scales it.
     * 
     * @param fileName
     *            - File Name of image to be loaded
     */
    public static void loadImage(String fileName) {
        HashMap<IDirection, BufferedImage> renderMap;
        HashMap<IDirection, BufferedImage> scaleMap;
        BufferedImage image = null;

        // load image
        try {
            image = ImageIO.read(new File("./resource/maptiles/" + fileName
                    + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        AffineTransform guiTransform = new AffineTransform();
        AffineTransform rotateTransform = new AffineTransform();

        renderImage.put(fileName, new HashMap<IDirection, BufferedImage>());
        scaleImage.put(fileName, new HashMap<IDirection, BufferedImage>());

        renderMap = renderImage.get(fileName);
        scaleMap = scaleImage.get(fileName);

        guiTransform.scale((double) ICON_SIZE / Tile.TILE_SIZE,
                (double) ICON_SIZE / Tile.TILE_SIZE);
        rotateTransform.rotate(Math.toRadians(90), Tile.TILE_SIZE / 2,
                Tile.TILE_SIZE / 2);

        AffineTransformOp guiOp = new AffineTransformOp(guiTransform,
                AffineTransformOp.TYPE_BILINEAR);
        AffineTransformOp rotateOp = new AffineTransformOp(rotateTransform,
                AffineTransformOp.TYPE_BILINEAR);

        // rotate the image
        renderMap.put(Direction.UP, image);
        renderMap.put(Direction.RIGHT,
                rotateOp.filter(renderMap.get(Direction.UP), null));
        renderMap.put(Direction.DOWN,
                rotateOp.filter(renderMap.get(Direction.RIGHT), null));
        renderMap.put(Direction.LEFT,
                rotateOp.filter(renderMap.get(Direction.DOWN), null));

        // create scaled versions
        for (Direction dir : Direction.values()) {
            scaleMap.put(dir, guiOp.filter(renderMap.get(dir), null));
        }
    }
}
