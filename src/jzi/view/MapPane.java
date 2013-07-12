package jzi.view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JPanel;

import jzi.Resource;
import jzi.controller.state.FightState;
import jzi.controller.state.ZombieMode;
import jzi.controller.state.ZombieState;
import jzi.model.entities.IPlayer;
import jzi.model.entities.IZombie;
import jzi.model.entities.SuperZombie;
import jzi.model.entities.Zombie;
import jzi.model.game.IGame;
import jzi.model.map.Coordinates;
import jzi.model.map.Direction;
import jzi.model.map.Field;
import jzi.model.map.ICoordinates;
import jzi.model.map.IDirection;
import jzi.model.map.IField;
import jzi.model.map.ITile;
import jzi.model.map.Tile;

/**
 * Renders the game map.
 * 
 * @author Tobias Groth, Buddy Jonte
 * 
 */
public class MapPane extends JPanel {
	/**
	 * Starting scale of the map.
	 */
	private static final double START_SCALE = 0.5;
	/**
	 * Generated serialVersionUID.
	 */
	private static final long serialVersionUID = 4607860473640965464L;
	/**
	 * Affine transform used to translate and scale the map.
	 */
	private AffineTransform at;
	/**
	 * Game handle.
	 */
	private IGame game;
	/**
	 * Current x translation.
	 */
	private double translateX;
	/**
	 * Current y translation.
	 */
	private double translateY;
	/**
	 * Current scale.
	 */
	private double scale;
	/**
	 * Clicked point transformed to image coordinates.
	 */
	private Point2D transformedPoint;
	/**
	 * Last clicked point.
	 */
	private Point2D lastClicked;
	/**
	 * Field coordinates that the mouse is currently hovering over.
	 */
	private ICoordinates mouseCoords;

	private HashMap<IDirection, Double> rotateMap;

	/**
	 * Constructor with game instance, initializes transform attributes and
	 * loads images.
	 * 
	 * @param game
	 *            game instance
	 * @param zombieImage2
	 * @param ammoImage2
	 * @param lifeImage
	 */
	public MapPane(IGame game) {
		super();
		this.game = game;
		translateX = 0;
		translateY = 0;
		scale = START_SCALE;

		rotateMap = new HashMap<>();

		rotateMap.put(Direction.UP, Math.toRadians(0));
		rotateMap.put(Direction.RIGHT, Math.toRadians(90));
		rotateMap.put(Direction.DOWN, Math.toRadians(180));
		rotateMap.put(Direction.LEFT, Math.toRadians(-90));

		PanningHandler panning = new PanningHandler();

		addMouseListener(panning);
		addMouseMotionListener(panning);
		addMouseWheelListener(new ScaleHandler());
	}

	/**
	 * Handles painting of the map.
	 * 
	 * @param g
	 *            graphics object that handles the actual painting
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// get the graphics object and save the current transform
		Graphics2D graphics = (Graphics2D) g;
		AffineTransform transformBackup = graphics.getTransform();

		// paint the background
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, getWidth(), getHeight());

		// create a new affine transform
		at = new AffineTransform(transformBackup);

		// and apply our transformations
		at.translate(getWidth() / 2, getHeight() / 2);
		at.scale(scale, scale);
		at.translate(-getWidth() / 2, -getHeight() / 2);

		at.translate(translateX, translateY);

		// set our new transform
		graphics.setTransform(at);

		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// paint all tiles
		for (ITile tile : game.getMap().getTiles()) {
			paintTile(graphics, tile,
					Coordinates.pointFromField(tile.getCoordinates().toField()));
		}

		// paint empty spots
		if (game.getCurrentTile() != null) {
			paintEmptyTiles(graphics);
			paintCurrentTile(graphics);
		}

		// paint current zombie
		paintCurrentZombie(graphics);

		// paint all players
		paintPlayers(graphics);

		paintDim(graphics);

		// and restore the old transform
		graphics.setTransform(transformBackup);

		paintTileOverlay(graphics);
	}

	/**
	 * Focuses the map on a field.
	 * 
	 * @param coords
	 *            coordinates of field to focus on
	 */
	public void focus(ICoordinates coords) {
		ICoordinates tile = coords.toTile();
		ICoordinates field = coords.toRelativeField();

		translateX = -tile.getX() * Tile.TILE_SIZE - (field.getX() - 1)
				* Field.FIELD_SIZE + Field.FIELD_SIZE / 2;
		translateY = -tile.getY() * Tile.TILE_SIZE - (field.getY() - 1)
				* Field.FIELD_SIZE + Field.FIELD_SIZE / 2;
	}

	/**
	 * Paints one tile onto the map.
	 * 
	 * @param graphics
	 *            graphics object that handles the actual painting
	 * @param tile
	 *            tile to be painted
	 * @param coords
	 *            coordinates to paint tile at
	 */
	private void paintTile(Graphics2D graphics, ITile tile, Point point) {
		BufferedImage image = Resource.getImage(Resource.TILE_FOLDER
				+ tile.getTileType().getFileName());
		AffineTransform preTransform = graphics.getTransform();
		AffineTransform rotate = new AffineTransform(preTransform);
		int rotateX = ((int) point.getX()) + Tile.TILE_SIZE / 2;
		int rotateY = ((int) point.getY()) + Tile.TILE_SIZE / 2;

		rotate.translate(rotateX, rotateY);
		rotate.rotate(rotateMap.get(tile.getRotation()));
		rotate.translate(-rotateX, -rotateY);

		graphics.setTransform(rotate);

		// draw the tile image
		drawImage(graphics, image, point);

		graphics.setTransform(preTransform);

		// draw objects on each field
		for (int x = 0; x < Tile.WIDTH_FIELDS; x++) {
			for (int y = 0; y < Tile.HEIGHT_FIELDS; y++) {
				Point fieldPoint = new Point(point);

				fieldPoint
						.translate(x * Field.FIELD_SIZE, y * Field.FIELD_SIZE);

				paintField(graphics, tile.getField(y, x), fieldPoint);
			}
		}
	}

	private void paintField(Graphics2D graphics, IField field, Point point) {
		IZombie zombie = field.getZombie();

		// draw ammunition
		if (field.hasAmmo()) {
			drawImage(graphics,
					Resource.getImage(Resource.OBJ_FOLDER + "Ammo.png"), point);
		}

		// draw life points
		if (field.hasLife()) {
			drawImage(graphics,
					Resource.getImage(Resource.OBJ_FOLDER + "Life.png"), point);
		}

		// draw zombie if there is one
		if (zombie instanceof Zombie) {
			drawImage(graphics,
					Resource.getImage(Resource.OBJ_FOLDER + "Zombie.png"),
					point);
		}

		// draw super zombie if there is one
		if (zombie instanceof SuperZombie) {
			drawImage(graphics,
					Resource.getImage(Resource.OBJ_FOLDER + "SuperZombie.png"),
					point);
		}
	}

	/**
	 * Paints all empty spots where a tile may be placed.
	 * 
	 * @param graphics
	 *            Graphics object to paint onto
	 */
	private void paintEmptyTiles(Graphics2D graphics) {
		Composite comp = graphics.getComposite();
		ITile tile = game.getCurrentTile();

		graphics.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 0.2f));

		for (ICoordinates coords : game.getMap().getEmptyTiles()) {
			if (coords.equals(mouseCoords)) {
				continue;
			}

			if (game.getMap().checkTileRotations(coords, tile)) {
				graphics.setColor(Color.GREEN);
			} else {
				graphics.setColor(Color.RED);
			}

			fillSquare(graphics, Coordinates.pointFromField(coords.toField()),
					Tile.TILE_SIZE);
		}

		graphics.setComposite(comp);
	}

	private void paintCurrentTile(Graphics2D graphics) {
		ITile tile = game.getCurrentTile();
		Composite comp = graphics.getComposite();

		if (mouseCoords == null
				|| !game.getMap().getEmptyTiles().contains(mouseCoords)) {
			return;
		}

		if (game.getMap().checkTile(mouseCoords, tile)) {
			graphics.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.6f));
		} else {
			graphics.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.25f));
		}

		paintTile(graphics, tile,
				Coordinates.pointFromField(mouseCoords.toField()));

		graphics.setComposite(comp);
	}

	/**
	 * Paints all zombies on the map.
	 * 
	 * @param graphics
	 *            graphics object that handles the painting
	 */
	private void paintCurrentZombie(Graphics2D graphics) {
		// draw a border around the current zombie if there is one
		if (game.getCurrentZombie() != null) {
			ICoordinates coords = game.getCurrentZombie().getCoordinates();
			Stroke stroke = graphics.getStroke();

			// draw a red border around the current zombie
			graphics.setColor(Color.GREEN);
			graphics.setStroke(new BasicStroke(5));

			drawSquare(graphics, Coordinates.pointFromField(coords),
					Field.FIELD_SIZE);

			graphics.setStroke(stroke);
		}
	}

	/**
	 * Paints all the players onto the map.
	 * 
	 * @param graphics
	 *            graphics object that handles the actual painting
	 */
	private void paintPlayers(Graphics2D graphics) {
		HashMap<ICoordinates, LinkedList<Color>> colorMap = new HashMap<>();

		// iterate through players and add their color to their coordinates
		for (IPlayer player : game.getPlayers()) {
			ICoordinates coords = player.getCoordinates();

			if (colorMap.get(coords) == null) {
				colorMap.put(coords, new LinkedList<Color>());
			}

			colorMap.get(coords).add(player.getColor());
		}

		// for each point that has at least one player, paint their circles or
		// arcs
		for (Map.Entry<ICoordinates, LinkedList<Color>> entry : colorMap
				.entrySet()) {
			ICoordinates coords = entry.getKey();
			// defines the position of the player on the arc
			int i = 0;
			// defines the arc size for one player at these coordinates
			int size = 360 / entry.getValue().size();
			int x = coords.toTile().getX() * Tile.TILE_SIZE
					+ coords.toRelativeField().getX() * Field.FIELD_SIZE + 40;
			int y = coords.toTile().getY() * Tile.TILE_SIZE
					+ coords.toRelativeField().getY() * Field.FIELD_SIZE + 40;

			// draw the arc for each player (each color)
			for (Color color : entry.getValue()) {
				graphics.setColor(color);
				graphics.fillArc(x, y, Field.FIELD_SIZE - 80,
						Field.FIELD_SIZE - 80, i * size, size);
				i++;
			}
		}
	}

	private void paintDim(Graphics2D graphics) {
		Composite comp = graphics.getComposite();
		AlphaComposite dimAlpha = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 0.6f);

		for (ITile tile : game.getMap().getTiles()) {
			for (int i = 0; i < 9; i++) {
				IField field = tile.getField(i / 3, i % 3);
				IZombie zombie = field.getZombie();
				ICoordinates coords = field.getCoordinates();

				if (game.getCurrentState() instanceof FightState
						&& !coords.equals(game.getCurrentPlayer()
								.getCoordinates())) {
					graphics.setComposite(dimAlpha);
					graphics.setColor(Color.black);
					fillRect(graphics, Coordinates.pointFromField(coords),
							Field.FIELD_SIZE, Field.FIELD_SIZE);
					graphics.setComposite(comp);
				}

				// highlight movable zombies
				if (game.getCurrentState() instanceof ZombieState
						&& game.getCurrentPlayer().hasRolledZombie()) {
					ZombieMode mode = ((ZombieState) game.getCurrentState())
							.getZombieMode();
					IZombie current = game.getCurrentZombie();
					boolean neighbor = false;

					if (current != null) {
						for (IDirection dir : Direction.values()) {
							IField next = game.getMap().getField(
									coords.getDir(dir));

							if (field.hasDir(dir) && next != null
									&& zombie == null
									&& current.equals(next.getZombie())) {
								neighbor = true;
							}
						}
					}

					if (mode.equals(ZombieMode.Move)
							&& (!game.canZombieMove(zombie) || (current != null && !current
									.equals(zombie))) && !neighbor) {
						graphics.setComposite(dimAlpha);
						graphics.setColor(Color.black);

						fillSquare(graphics,
								Coordinates.pointFromField(coords),
								Field.FIELD_SIZE);

						graphics.setComposite(comp);
					}

					if (mode.equals(ZombieMode.Place)
							&& (zombie != null || !field.getType().equals(
									"building"))) {
						graphics.setComposite(dimAlpha);
						graphics.setColor(Color.black);

						fillSquare(graphics,
								Coordinates.pointFromField(coords),
								Field.FIELD_SIZE);

						graphics.setComposite(comp);
					}
				}
			}
		}
	}

	private void paintTileOverlay(Graphics2D graphics) {
		AffineTransform transform = graphics.getTransform();
		AffineTransform scaleTransform = new AffineTransform(transform);
		ITile tile = game.getCurrentTile();
		Point point = new Point();

		if (tile == null) {
			return;
		}

		scaleTransform.scale(150d / Tile.TILE_SIZE, 150d / Tile.TILE_SIZE);

		try {
			point.setLocation(scaleTransform.inverseTransform(
					new Point(10, 10), null));
		} catch (NoninvertibleTransformException ex) {
			System.out.println("Couldn't transform point");
		}

		graphics.setTransform(scaleTransform);

		paintTile(graphics, tile, point);

		graphics.setTransform(transform);
	}

	private void fillSquare(Graphics2D graphics, Point2D point, int size) {
		fillRect(graphics, point, size, size);
	}

	private void fillRect(Graphics2D graphics, Point2D point, int width,
			int height) {
		graphics.fillRect((int) point.getX(), (int) point.getY(), width, height);
	}

	private void drawSquare(Graphics2D graphics, Point2D point, int size) {
		drawRect(graphics, point, size, size);
	}

	private void drawRect(Graphics2D graphics, Point2D point, int width,
			int height) {
		graphics.drawRect((int) point.getX(), (int) point.getY(), width, height);
	}

	private void drawImage(Graphics2D graphics, Image image, Point2D point) {
		graphics.drawImage(image, (int) point.getX(), (int) point.getY(), null);
	}

	/**
	 * Defines this component's preferred size.
	 * 
	 * @return preferred size
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(2000, 2000);
	}

	/**
	 * Gets the last point clicked on the map.
	 * 
	 * @return last clicked point
	 */
	public Point2D getLastPoint() {
		return lastClicked;
	}

	/**
	 * Sets the last clicked point for testing purposes.
	 * 
	 * @param last
	 *            new point
	 */
	public void setLastPoint(Point2D last) {
		lastClicked = last;
	}

	/**
	 * Handles panning of the map view.
	 * 
	 * @author Buddy Jonte
	 * 
	 */
	private class PanningHandler implements MouseListener, MouseMotionListener {
		/**
		 * Reference x coordinate; defines where the user initiated the panning
		 * action.
		 */
		private double referenceX;
		/**
		 * Reference y coordinate; defines where the user initiated the panning
		 * action.
		 */
		private double referenceY;
		/**
		 * Transform that was valid at the beginning of the panning.
		 */
		private AffineTransform initialTransform;

		/**
		 * Invoked when a mouse button has been pressed on the map.
		 * 
		 * @param e
		 *            Mouse event for this action
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			// transform the clicked point into map coordinates
			try {
				transformedPoint = at.inverseTransform(e.getPoint(), null);
			} catch (NoninvertibleTransformException te) {
				return;
			}

			// save point as last clicked point
			lastClicked = transformedPoint;
			// save reference coordinates for panning
			referenceX = transformedPoint.getX();
			referenceY = transformedPoint.getY();
			// save transform for later use
			initialTransform = at;
		}

		/**
		 * Invoked when a mouse button is pressed on the component and the mouse
		 * is dragged.
		 * 
		 * @param e
		 *            Mouse event for this action
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			// transform new point into map coordinates
			try {
				transformedPoint = initialTransform.inverseTransform(
						e.getPoint(), null);
			} catch (NoninvertibleTransformException te) {
				return;
			}

			// calculate the dragged distance along each axis
			double deltaX = transformedPoint.getX() - referenceX;
			double deltaY = transformedPoint.getY() - referenceY;

			// update the reference point
			referenceX = transformedPoint.getX();
			referenceY = transformedPoint.getY();

			// translate according to movement
			translateX += deltaX;
			translateY += deltaY;

			// and repaint
			repaint();
		}

		/**
		 * Invoked after the mouse is released; unused in this class.
		 * 
		 * @param e
		 *            corresponding mouse event
		 */
		public void mouseClicked(MouseEvent e) {
		}

		/**
		 * Invoked when the mouse enters the component; unused in this class.
		 * 
		 * @param e
		 *            corresponding mouse event
		 */
		public void mouseEntered(MouseEvent e) {
		}

		/**
		 * Invoked when the mouse exits the component; unused in this class.
		 * 
		 * @param e
		 *            corresponding mouse event
		 */
		public void mouseExited(MouseEvent e) {
			mouseCoords = null;
			repaint();
		}

		/**
		 * Invoked when the mouse is moved inside the component; unused in this
		 * class.
		 * 
		 * @param e
		 *            corresponding mouse event
		 */
		public void mouseMoved(MouseEvent e) {
			if (at == null) {
				return;
			}

			// transform new point into map coordinates
			try {
				mouseCoords = Coordinates.tileFromPoint(at.inverseTransform(
						e.getPoint(), null));
			} catch (NoninvertibleTransformException te) {
				return;
			}

			repaint();
		}

		/**
		 * Invoked when the mouse is released; unused in this class.
		 * 
		 * @param e
		 *            corresponding mouse event
		 */
		public void mouseReleased(MouseEvent e) {
		}
	}

	/**
	 * Handles scaling of the map view.
	 * 
	 * @author Buddy Jonte
	 * 
	 */
	private class ScaleHandler implements MouseWheelListener {
		/**
		 * Maximum scale of the map.
		 */
		private static final double MAX_SCALE = 2.0;
		/**
		 * Minimum scale of the map.
		 */
		private static final double MIN_SCALE = 0.1;
		/**
		 * Factor by which the map is scaled after a mouse wheel movement.
		 */
		private static final double SCALE_FACTOR = 1.1;

		/**
		 * Invoked when the mouse wheel is moved over the component. Adjusts the
		 * map scale accordingly.
		 * 
		 * @param e
		 *            corresponding mouse event
		 */
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			double newScale;

			// check the mouse wheel direction
			if (e.getWheelRotation() > 0) {
				newScale = scale / SCALE_FACTOR;
			} else {
				newScale = scale * SCALE_FACTOR;
			}

			// set the new scale
			scale = newScale;
			// and make sure it is within bounds
			scale = Math.max(MIN_SCALE, scale);
			scale = Math.min(MAX_SCALE, scale);

			// then repaint the map
			repaint();
		}
	}
}
