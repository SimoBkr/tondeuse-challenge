package com.tondeuse.tondeusekata.domaine;

public enum Orientation {
    N {
        @Override
        public Orientation rotateLeft() {
            return W;
        }

        @Override
        public Orientation rotateRight() {
            return E;
        }

        @Override
        public void moveForward(Tondeuse tondeuse) {
            tondeuse.setY(tondeuse.getY() + 1);
        }
    },
    E {
        @Override
        public Orientation rotateLeft() {
            return N;
        }

        @Override
        public Orientation rotateRight() {
            return S;
        }

        @Override
        public void moveForward(Tondeuse tondeuse) {
            tondeuse.setX(tondeuse.getX() + 1);
        }
    },
    S {
        @Override
        public Orientation rotateLeft() {
            return E;
        }

        @Override
        public Orientation rotateRight() {
            return W;
        }

        @Override
        public void moveForward(Tondeuse tondeuse) {
            tondeuse.setY(tondeuse.getY() - 1);
        }
    },
    W {
        @Override
        public Orientation rotateLeft() {
            return S;
        }

        @Override
        public Orientation rotateRight() {
            return N;
        }

        @Override
        public void moveForward(Tondeuse tondeuse) {
            tondeuse.setX(tondeuse.getX() - 1);
        }
    };

    public abstract Orientation rotateLeft();
    public abstract Orientation rotateRight();
    public abstract void moveForward(Tondeuse tondeuse);
}
