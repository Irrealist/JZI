package jzi.model;

import static de.uni_lubeck.isp.rvtool.rvlib.syntax.ltl.LTL4Syntax.*;

import java.util.LinkedList;

import jzi.controller.state.FightState;
import jzi.controller.state.PlayerState;
import jzi.controller.state.ZombieState;
import jzi.model.map.ITileType;
import jzi.model.map.MapStub;
import jzi.model.map.TileTypeStub;
import jzi.view.GameMenu;
import jzi.view.IWindow;
import jzi.view.WindowStub;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.uni_lubeck.isp.rvtool.rvjunit.*;
import de.uni_lubeck.isp.rvtool.rvlib.syntax.java.Event;

@RunWith(RVRunner.class)
public class GameRVTest {
    private static IWindow window = new WindowStub();
    private static Game g;
    private static Event setPoints = called("jzi.model.Player", "setPoints");
    private static Event moveTo = called("jzi.model.Game", "movePlayer");
    private static Event showWinner = called("jzi.model.Game", "showWinner");
    private static Event roll = returned("jzi.model.Game", "roll");
    // private static Event moveZombie = called("jzi.model.Game", "moveZombie");
    private static Event stateChange = called("jzi.model.Game", "setState");
    private static Event playerDead = called("jzi.model.Player", "reset");
    private static Event setZombie = called("jzi.model.map.Field", "setZombie");
    private static Event drawTile = called("jzi.model.Game", "drawTile");
    private static Event nextPlayer = called("jzi.model.Game", "nextPlayer");

    private static Monitor noMoveUntilVictory = new FLTL4Monitor(
            Always(implies(and(p(setPoints), p(gt($arg(0), INT(19)))),
                    Until(not(moveTo), showWinner))));

    private static Monitor rollBounds = new FLTL4Monitor(Always(implies(
            p(roll), and(gt($return, INT(0)), lt($return, INT(7))))));

    /*
     * BROKEN private static Monitor zombieMove = new
     * FLTL4Monitor(Always(implies( moveZombie, eq(BOOL(((IField)
     * $arg(1)).getZombie() != null), // something // with // $field // BUT //
     * HOW?! BOOL(false)))));
     */

    private static Monitor fight = new FLTL4Monitor(Always(implies(
            and(stateChange, eq(BOOL($arg(0).equals(new FightState(window))), // this
                                                                              // should
                                                                              // be
                                                                              // easier,
                                                                              // right?
                    BOOL(true))),
            F(or(playerDead, and(setZombie, eq($arg(0), BOOL(false))))))));

    private static Monitor draw = new FLTL4Monitor(Always(implies(drawTile,
            F(p(stateChange)))));

    private static Monitor followPlayer = new FLTL4Monitor(Always(implies(
            and(stateChange,
                    eq(BOOL($arg(0).equals(new PlayerState(window))),
                            BOOL(true))),
            F(and(stateChange,
                    or(eq(BOOL($arg(0).equals(new ZombieState(window))),
                            BOOL(true)), showWinner))))));

    private static Monitor followZombie = new FLTL4Monitor(Always(implies(
            and(stateChange,
                    eq(BOOL($arg(0).equals(new ZombieState(window))),
                            BOOL(true))), F(p(nextPlayer)))));

    @BeforeClass
    public static void setUp() {
        LinkedList<ITileType> list = new LinkedList<>();

        list.add(new TileTypeStub());

        g = new Game(list);
        g.setWindow(window);
        g.setMap(new MapStub());
    }

    public void use(Monitor mon) {

    }

    // It would be way easier to just play the game
    // rather than simulate the whole thing in test cases
    @Test
    @Monitors({ "noMoveUntilVictory" })
    public void testVictory() {
        Player p = new Player();

        p.setPoints(20);

        g.addPlayer(p);
        g.checkWin();
        g.showWinner();

        use(noMoveUntilVictory);
        use(rollBounds);
        // use(zombieMove);
        use(fight);
        use(draw);
        use(followZombie);
        use(followPlayer);
    }

    @Test
    @Monitors({ "rollBounds" })
    public void testRoll() {
        g.roll();
        g.roll();
        g.roll();
        g.roll();
    }

    /*
     * @Test
     * 
     * @Monitors({ "zombieMove" }) public void testZombie() { IField to = new
     * Field();
     * 
     * // Should actually be: // Make a Tile // add it to map // add Fields and
     * set values // make player // set current player // make sure he has
     * zombies // ... // die of old age while writing this test case
     * 
     * g.moveZombie(to); }
     */

    @Test
    @Monitors({ "fight" })
    public void fightTest() {
        g.setState(new FightState(window));
    }

    @Test
    @Monitors({ "draw" })
    public void drawTest() {
        window.setMenu(new GameMenu(window, g));
        g.drawTile();
        g.setState(new PlayerState(window));
    }

    /*
     * @Test
     * 
     * @Monitors({"noMoveUntilVictory", "rollBounds", "zombieMove", "draw",
     * "fight", "followPlayer", "followZombie"}) public void humanTest() { //
     * really the only feasible way of doing this Main.main(null); }
     */
}
