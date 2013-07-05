package jzi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jzi.controller.state.IState;
import jzi.controller.state.TileState;
import jzi.controller.state.ZombieState;
import jzi.model.map.Coordinates;
import jzi.model.map.Direction;
import jzi.model.map.ICoordinates;
import jzi.model.map.IField;
import jzi.model.map.ITile;
import jzi.model.map.ITileType;
import jzi.view.LoseMenu;
import jzi.view.Update;

/**
 * Extends {@link Game} to provide functionality for a co-op game.
 * 
 * @author Buddy Jonte
 * 
 */
public class GameCoop extends Game {
    /**
     * Default starting ammunition.
     */
    private static final int DEFAULT_START_AMMUNITION = 3;
    /**
     * UID for serialization.
     */
    private static final long serialVersionUID = 3474529484974380671L;

    /**
     * Constructor calls {@link Game}'s constructor and initializes zombie
     * movement map and starting ammo.
     * 
     * @param window
     *            current game window
     * @param shuffledTileList
     *            list of Tiles for this game
     */
    public GameCoop(final List<ITileType> shuffledTileList) {
        super(shuffledTileList);
        setStartAmmo(DEFAULT_START_AMMUNITION);
    }

    /**
     * Calls super class setUp and sets starting ammunition.
     */
    @Override
    public final void setUp() {
        super.setUp();

        for (IPlayer player : getPlayers()) {
            player.setAmmo(getStartAmmo());
        }
    }

    /**
     * Calls super class setState and moves zombies if new state is
     * {@link ZombieState}. Advances the round to next player and sets game to
     * {@link TileState} afterwards.
     * 
     * @param state
     *            new game {@link IState}
     */
    @Override
    public final void setState(final IState state) {
        super.setState(state);

        if (state instanceof ZombieState) {
            rollDie();
            moveZombies();
            nextPlayer();
            setState(new TileState(getWindow()));
        }
    }

    /**
     * Executes zombie movement in {@link ZombieState}.
     */
    private void moveZombies() {
        // roll a random value
        int value = getDie();

        // Place a Zombie on top of a player if possible
        if (value >= ZOMBIE_PLACE_COST) {
            for (IPlayer player : getPlayers()) {
                IField field = getMap().getField(player.getCoordinates());

                // only place zombies in buildings
                if (field.getType().equals("building")
                        && field.getZombie() == null) {
                    addZombie(new Zombie(player.getCoordinates()));
                    value -= ZOMBIE_PLACE_COST;
                    break;
                }
            }
        }

        // no zombies to move
        if (getZombies().isEmpty()) {
            return;
        }

        // find all possible movements & execute as many as possible
        executeMovements(findMovements(), value);

        // update map
        setChanged();
        notifyObservers(Update.ZombieMoved);
    }

    /**
     * Finds possible zombie movements and saves them in the zombie movement
     * map.
     * 
     * @return hash map of found movements
     */
    private HashMap<IZombie, ICoordinates> findMovements() {
        HashMap<IZombie, ICoordinates> zombieMap = new HashMap<>();

        // check possible movements for every zombie
        for (IZombie zombie : getZombies()) {
            double minDist = Double.MAX_VALUE;
            IPlayer minPlayer = null;
            ICoordinates coords = zombie.getCoordinates();
            ICoordinates minCoords = null;

            // find closest player
            minPlayer = closestPlayer(zombie);
            minDist = Coordinates.distance(coords, minPlayer.getCoordinates());

            // Find movement that gets Zombie closer to player
            for (Direction dir : Direction.values()) {
                double dist = Coordinates.distance(coords.getDir(dir),
                        minPlayer.getCoordinates());
                IField from = getMap().getField(coords);
                IField to = getMap().getField(coords.getDir(dir));

                // Make sure that:
                // Field exists and has no Zombie
                // No other zombie is scheduled to move there
                // Current Field has an exit in that direction
                // And movement gives a benefit
                if (to != null && to.getZombie() == null
                        && !zombieMap.containsValue(coords.getDir(dir))
                        && from.hasDir(dir) && dist <= minDist) {
                    minDist = dist;
                    minCoords = coords.getDir(dir);
                }
            }

            // If such a movement exists, schedule it
            if (minCoords != null) {
                zombieMap.put(zombie, minCoords);
            }
        }

        return zombieMap;
    }

    /**
     * Finds the closest player to the given zombie.
     * 
     * @param zombie
     *            zombie to check
     * @return closest player to zombie
     */
    private IPlayer closestPlayer(final IZombie zombie) {
        double minDist = Double.MAX_VALUE;
        IPlayer minPlayer = null;

        for (IPlayer player : getPlayers()) {
            double dist = Coordinates.distance(player.getCoordinates(),
                    zombie.getCoordinates());
            if (dist < minDist) {
                minPlayer = player;
                minDist = dist;
            }
        }

        return minPlayer;
    }

    /**
     * Executes movements in the given hash map; performs at most
     * <code>value</code> movements. Assumes that the given movements are valid,
     * meaning the destination is free, the given coordinates are next to the
     * zombie's coordinates, and the fields permit movement in that direction.
     * 
     * @param value
     *            rolled die value
     * @param zombieMap
     *            Hash map of zombie movements to be executed
     */
    private void executeMovements(
            final HashMap<IZombie, ICoordinates> zombieMap, int value) {
        for (Map.Entry<IZombie, ICoordinates> entry : zombieMap.entrySet()) {
            // stop if no more movements are left
            if (value < 1) {
                break;
            }

            IZombie zombie = entry.getKey();
            IField from = getMap().getField(zombie.getCoordinates());
            IField to = getMap().getField(entry.getValue());

            to.setZombie(zombie);
            from.setZombie(null);

            zombie.setCoordinates(to.getCoordinates());

            value--;
        }
    }

    /**
     * Quits the game and shows the {@link LoseMenu}. All players must survive
     * in co-op mode.
     */
    @Override
    public final void revive() {
        getWindow().setMenu(new LoseMenu(getWindow()));
    }

    /**
     * Checks whether the game has been won. A co-op game is won if the sum of
     * points reaches 50 or all players are at the center of the helipad.
     * 
     * @return true if game has been won.
     */
    @Override
    public final boolean checkWin() {
        int sum = 0;

        for (IPlayer player : getPlayers()) {
            sum += player.getPoints();
        }

        if (sum >= getWinThreshold()) {
            return true;
        }

        for (IPlayer player : getPlayers()) {
            ICoordinates coord = player.getCoordinates();
            ITile tile = getMap().getTile(coord.toTile());
            IField field = getMap().getField(coord);

            // Game is Won if everyone is on Helipad...
            if (!tile.getTileType().getName().equals("Helicopter")) {
                return false;
            }

            if (field.getZombie() != null) {
                return false;
            }

            // ... and on center Field
            if (!coord.toRelativeField().equals(new Coordinates(1, 1))) {
                return false;
            }
        }

        return true;
    }
    
    @Override
    public boolean isCoop() {
        return true;
    }
}
