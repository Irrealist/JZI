package jzi.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.ImageIcon;

/**
 * create graphic Die.
 *
 * @author Tobias Groth
 *
 */
public class DieGraphic {
    /**
     * List of all combinations of eyes.
     */
    private static LinkedList<DieGraphic> dieList =
            new LinkedList<DieGraphic>();
    /**
     * Eyes of a Die.
     */
    private int value;
    /**
     * Image of a Value.
     */
    private BufferedImage scaleImage;

    /**
     * create Graphic.
     *
     * @param image
     * @param value
     */
    public DieGraphic(final BufferedImage image, final int value) {
        this.value = value;
        Image tempImage = image;
        tempImage = tempImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaleImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        scaleImage.getGraphics().drawImage(tempImage, 0, 0, null);

        dieList.add(this);

    }

    /**
     * get Icon of the Value.
     * @return ImageIcon
     */
    public final ImageIcon getIcon() {
        return new ImageIcon(scaleImage);
    }

    /**
     * get Value of a Graphic.
     * @return Value
     */
    public final int getValue() {
        return value;
    }

    /**
     * return icon of a value.
     * @param value
     * Value of the die
     * @return icon of value
     */
    public static ImageIcon getDiefromInt(int value) {
        for (int i = 0; i < dieList.size(); i++) {
            if (dieList.get(i).getValue() == value) {
                return dieList.get(i).getIcon();
            }
        }

        return null;
    }
}
