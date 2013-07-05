package jzi.view;

/**
 * Describes actions that are initiated by the player.
 * 
 * @author Buddy Jonte
 * 
 */
public enum Action {
    /**
     * Action for the "new game" button.
     */
    NewGame,
    /**
     * Action for the "co-op game" button.
     */
    Coop,
    /**
     * Action for the "exit" button and menu item.
     */
    Exit,
    /**
     * Action for the back button in the {@link AddPlayerMenu}.
     */
    AddBack,
    /**
     * Action for the "Add Player" button in the {@link AddPlayerMenu}.
     */
    AddPlayer,
    /**
     * Action for the "Play" button in the {@link AddPlayerMenu}.
     */
    Start,
    /**
     * Action which occurs when the name text field changes.
     */
    PlayerUpdate,
    /**
     * Action for the "roll die" button.
     */
    Roll,
    /**
     * Action for the "draw tile" button.
     */
    Draw,
    /**
     * Action for the "show rules" menu item.
     */
    Rules,
    /**
     * Action which occurs when the {@link MapPane} is clicked.
     */
    Map,
    /**
     * Action for the "move" radio button.
     */
    MoveZombie,
    /**
     * Action for the "place" radio button.
     */
    PlaceZombie,
    /**
     * Action for the "rotate left" button.
     */
    RotateLeft,
    /**
     * Action for the "rotate right" button.
     */
    RotateRight,
    /**
     * Action for the "continue" button.
     */
    Continue,
    /**
     * Action for the "quit current game" menu item.
     */
    Quit,
    /**
     * Action for the "surrender" menu item.
     */
    Surrender,
    /**
     * Action for the "load game..." menu item.
     */
    Load,
    /**
     * Action for the "save game..." menu item.
     */
    Save,
    
    /**
     * Action for the "useAmmo..." menu item.
     */
    
    useAmmo,
    
    /**
     * Action for the "use life..." menu item.
     */

    useLife,
    
    /**
     * Action for the "english language ..." menu item.
     */
    
    toEnglish,
    
    /**
     * Action for the "german language ..." menu item.
     */
    toGerman,
}
