package jzi.model.map;

/**
 * Determines the position of a field on its tile.
 * 
 * @author Mai
 * 
 */
public enum FieldPosition implements IFieldPosition {
    /**
     * Position of the top left field.
     */
    TOPLEFT {
        /**
         * Gets the position of the top left field after being rotated to the
         * right.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateRightPosition() {
            return TOPRIGHT;
        }

        /**
         * Gets the position of the top left field after being rotated to the
         * left.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateLeftPosition() {
            return BOTTOMLEFT;

        }

        /**
         * Gets the relative field coordinates of the top left field.
         * 
         * @return relative field coordinates
         */
        @Override
        public ICoordinates getCoordinates() {
            return new Coordinates(0, 0);
        }
    },

    /**
     * Position of the top field.
     */
    TOP {
        /**
         * Gets the position of the top field after being rotated to the right.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateRightPosition() {
            return RIGHT;
        }

        /**
         * Gets the position of the top field after being rotated to the left.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateLeftPosition() {
            return LEFT;

        }

        /**
         * Gets the relative field coordinates of the top field.
         * 
         * @return relative field coordinates
         */
        @Override
        public ICoordinates getCoordinates() {
            return new Coordinates(1, 0);
        }
    },

    /**
     * Position of the top right field.
     */
    TOPRIGHT {
        /**
         * Gets the position of the top right field after being rotated to the
         * right.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateRightPosition() {
            return BOTTOMRIGHT;
        }

        /**
         * Gets the position of the top right field after being rotated to the
         * left.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateLeftPosition() {
            return TOPLEFT;

        }

        /**
         * Gets the relative field coordinates of the top right field.
         * 
         * @return relative field coordinates
         */
        @Override
        public ICoordinates getCoordinates() {
            return new Coordinates(2, 0);
        }
    },

    /**
     * Position of the left field.
     */
    LEFT {
        /**
         * Gets the position of the left field after being rotated to the right.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateRightPosition() {
            return TOP;
        }

        /**
         * Gets the position of the left field after being rotated to the left.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateLeftPosition() {
            return BOTTOM;

        }

        /**
         * Gets the relative field coordinates of the left field.
         * 
         * @return relative field coordinates
         */
        @Override
        public ICoordinates getCoordinates() {
            return new Coordinates(0, 1);
        }
    },

    /**
     * Position of the center field.
     */
    CENTER {
        /**
         * Gets the position of the center field after being rotated to the
         * right.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateRightPosition() {
            return CENTER;
        }

        /**
         * Gets the position of the center field after being rotated to the
         * left.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateLeftPosition() {
            return CENTER;

        }

        /**
         * Gets the relative field coordinates of the center field.
         * 
         * @return relative field coordinates
         */
        @Override
        public ICoordinates getCoordinates() {
            return new Coordinates(1, 1);
        }
    },

    /**
     * Position of the right field.
     */
    RIGHT {
        /**
         * Gets the position of the right field after being rotated to the
         * right.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateRightPosition() {
            return BOTTOM;
        }

        /**
         * Gets the position of the right field after being rotated to the left.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateLeftPosition() {
            return TOP;

        }

        /**
         * Gets the relative field coordinates of the right field.
         * 
         * @return relative field coordinates
         */
        @Override
        public ICoordinates getCoordinates() {
            return new Coordinates(2, 1);
        }
    },

    /**
     * Position of the bottom left field.
     */
    BOTTOMLEFT {
        /**
         * Gets the position of the bottom left field after being rotated to the
         * right.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateRightPosition() {
            return TOPLEFT;
        }

        /**
         * Gets the position of the bottom left field after being rotated to the
         * left.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateLeftPosition() {
            return BOTTOMRIGHT;

        }

        /**
         * Gets the relative field coordinates of the bottom left field.
         * 
         * @return relative field coordinates
         */
        @Override
        public ICoordinates getCoordinates() {
            return new Coordinates(0, 2);
        }
    },

    /**
     * Position of the bottom field.
     */
    BOTTOM {
        /**
         * Gets the position of the bottom field after being rotated to the
         * right.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateRightPosition() {
            return LEFT;
        }

        /**
         * Gets the position of the bottom field after being rotated to the
         * left.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateLeftPosition() {
            return RIGHT;

        }

        /**
         * Gets the relative field coordinates of the bottom field.
         * 
         * @return relative field coordinates
         */
        @Override
        public ICoordinates getCoordinates() {
            return new Coordinates(1, 2);
        }
    },

    /**
     * Position of the bottom right field.
     */
    BOTTOMRIGHT {
        /**
         * Gets the position of the bottom right field after being rotated to
         * the right.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateRightPosition() {
            return BOTTOMLEFT;
        }

        /**
         * Gets the position of the bottom right field after being rotated to
         * the left.
         * 
         * @return new field position
         */
        @Override
        public IFieldPosition rotateLeftPosition() {
            return TOPRIGHT;
        }

        /**
         * Gets the relative field coordinates of the bottom right field.
         * 
         * @return relative field coordinates
         */
        @Override
        public ICoordinates getCoordinates() {
            return new Coordinates(2, 2);
        }
    };

}
