package jzi.view;

/**
 * Defines events that trigger updates in the view.
 * 
 * @author Buddy Jonte
 * 
 */
public enum Update {
    /**
     * Occurs when the die is rolled.
     */
    DieRolled,
    /**
     * Occurs when a tile is drawn.
     */
    TileDrawn,
    /**
     * Occurs when a tile is rotated.
     */
    TileRotated,
    /**
     * Occurs when a tile is placed.
     */
    TilePlaced,
    /**
     * Occurs when a player is added to the game.
     */
    PlayerAdded,
    /**
     * Occurs when the game state changes.
     */
    StateChanged,
    /**
     * Occurs when the current player changes.
     */
    PlayerChange,
    /**
     * Occurs when a player's attributes change.
     */
    PlayerAttributeUpdate,
    /**
     * Occurs when a player moves.
     */
    PlayerMoved,
    /**
     * Occurs when a zombie moves.
     */
    ZombieMoved,
    /**
     * Occurs when the map view needs to be updated.
     */
    Map,
    /**
     * Occurs when a Fight is Lost and a the FightPanel is needed.
     */
    FightLost,
    /**
     * Occurs when the player win a fight and hide the FightPanel.
     */
    FightWon,
    /**
     * Occurs when the player has enough Ammo to use it.
     */
    EnoughAmmo,
    /**
     * Occurs when the player choose to use a life and can roll the die.
     */
    DisableFightPanel,
    /**
     * Occurs when when the player has enough Lives to use it.
     */
    EnoughLife,
    /**
     * Occurs when the player lost the fight again.
     */
    FightPanelUpdate,
    /**
     * Occurs when a player change the language of a game.
     */
    ChangeLanguage,
}
