package jzi.model.map;

import jzi.model.map.IDirection;
import jzi.model.map.IFieldPosition;

/**
 * Direction.UP as an sample
 * @author MaiBot
 *
 */
public enum DirectionStub implements IDirection {
    
    UP {
        @Override
        public IDirection rotateLeft() {
            return Direction.LEFT;
        }
        @Override
        public IDirection rotateRight() {
            return Direction.RIGHT;
        }

        @Override
        public IDirection getOpposite() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public IFieldPosition getField() {
            // TODO Auto-generated method stub
            return null;
        }
    };

}
