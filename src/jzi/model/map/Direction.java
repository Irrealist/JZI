/**
 * 
 */


package jzi.model.map;

/**
 * Models a direction, mainly for use in moving from Tiles or Fields.
 * 
 * @author Buddy Jonte
 * 
 */
public enum Direction implements IDirection {
    /**
     * Up direction.
     */
    UP {
        /**
         * Get the direction that results when rotating UP to the left.
         * 
         * @return Direction after rotating UP to the left
         */
        @Override
        public Direction rotateLeft() {
            return LEFT;
        }

        /**
         * Get the direction that results when rotating UP to the right.
         * 
         * @return Direction after rotating UP to the right
         */
        @Override
        public Direction rotateRight() {
            return RIGHT;
        }

        /**
         * Get the opposite direction of UP.
         * 
         * @return opposite direction
         */
        @Override
        public Direction getOpposite() {
            return DOWN;
        }

        /**
         * Get the FieldPosition associated with UP.
         * 
         * @return associated FieldPosition
         */
        @Override
        public FieldPosition getField() {
            return FieldPosition.TOP;
        }
    },

    /**
     * Right direction.
     */
    RIGHT {
        /**
         * Get the direction that results when rotating RIGHT to the left.
         * 
         * @return Direction after rotating RIGHT to the left
         */
        @Override
        public Direction rotateLeft() {
            return UP;
        }

        /**
         * Get the direction that results when rotating RIGHT to the right.
         * 
         * @return Direction after rotating RIGHT to the right
         */
        @Override
        public Direction rotateRight() {
            return DOWN;
        }

        /**
         * Get the opposite direction of RIGHT.
         * 
         * @return opposite direction
         */
        @Override
        public Direction getOpposite() {
            return LEFT;
        }

        /**
         * Get the FieldPosition associated with RIGHT.
         * 
         * @return associated FieldPosition
         */
        @Override
        public FieldPosition getField() {
            return FieldPosition.RIGHT;
        }
    },

    /**
     * Down direction.
     */
    DOWN {
        /**
         * Get the direction that results when rotating DOWN to the left.
         * 
         * @return Direction after rotating DOWN to the left
         */
        @Override
        public Direction rotateLeft() {
            return RIGHT;
        }

        /**
         * Get the direction that results when rotating DOWN to the right.
         * 
         * @return Direction after rotating DOWN to the right
         */
        @Override
        public Direction rotateRight() {
            return LEFT;
        }

        /**
         * Get the opposite direction of DOWN.
         * 
         * @return opposite direction
         */
        @Override
        public Direction getOpposite() {
            return UP;
        }

        /**
         * Get the FieldPosition associated with DOWN.
         * 
         * @return associated FieldPosition
         */
        @Override
        public FieldPosition getField() {
            return FieldPosition.BOTTOM;
        }
    },

    /**
     * Left direction.
     */
    LEFT {
        /**
         * Get the direction that results when rotating LEFT to the left.
         * 
         * @return Direction after rotating LEFT to the left
         */
        @Override
        public Direction rotateLeft() {
            return DOWN;
        }

        /**
         * Get the direction that results when rotating LEFT to the right.
         * 
         * @return Direction after rotating LEFT to the right
         */
        @Override
        public Direction rotateRight() {
            return UP;
        }

        /**
         * Get the opposite direction of LEFT.
         * 
         * @return opposite direction
         */
        @Override
        public Direction getOpposite() {
            return RIGHT;
        }

        /**
         * Get the FieldPosition associated with LEFT.
         * 
         * @return associated FieldPosition
         */
        @Override
        public FieldPosition getField() {
            return FieldPosition.LEFT;
        }
    };
}
