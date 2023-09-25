package utilz;

public class Constants {
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public static final int RUNNING = 0;
        public static final int IDLE = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK = 6;

        public static int GetSpriteAmount(int player_action){
            switch(player_action){
                case RUNNING:
                case IDLE:
                case HIT:
                    return 3;
                case GROUND:
                case ATTACK:
                    return 2;
                case JUMP:
                case FALLING:
                default:
                    return 1;

            }
        }
    }
}
