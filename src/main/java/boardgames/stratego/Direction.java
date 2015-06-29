package boardgames.stratego;

public enum Direction {
    FORWARD {
        @Override
        public Position next(Position position) {
            return position.above();
        }

        @Override
        public Direction opposite() {
            return BACKWARD;
        }
    },
    BACKWARD {
        @Override
        public Position next(Position position) {
            return position.below();
        }

        @Override
        public Direction opposite() {
            return FORWARD;
        }
    },
    LEFT {
        @Override
        public Position next(Position position) {
            return position.left();
        }

        @Override
        public Direction opposite() {
            return RIGHT;
        }
    },
    RIGHT {
        @Override
        public Position next(Position position) {
            return position.right();
        }

        @Override
        public Direction opposite() {
            return LEFT;
        }
    };

    public abstract Position next(Position position);

    public abstract Direction opposite();

    public Position previous(Position position) {
        return opposite().next(position);
    }
}
