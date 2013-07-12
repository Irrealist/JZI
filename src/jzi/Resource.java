package jzi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Resource {
	public static String RESOURCE_FOLDER = "./data/";
	public static String IMAGE_FOLDER = RESOURCE_FOLDER + "img/";
	public static String TILE_FOLDER = IMAGE_FOLDER + "tiles/";
	public static String OBJ_FOLDER = IMAGE_FOLDER + "obj/";
	public static String DIE_FOLDER = IMAGE_FOLDER + "die/";

	private static HashMap<String, Object> Resources = new HashMap<>();

	public static BufferedImage getImage(String fileName) {
		Object resource = Resources.get(fileName);

		if (resource == null) {
			loadImage(fileName);

			resource = Resources.get(fileName);
		}

		return (BufferedImage) resource;
	}

	public static void loadImage(String fileName) {
		Object resource = null;

		try {
			resource = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			System.err.println("Could not read file " + fileName);
		}

		Resources.put(fileName, resource);
	}
}
