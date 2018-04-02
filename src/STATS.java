public class STATS {
    static boolean
            menu = true,
            play = false,
            instructions = false,
            pause = false,
            end = false;

    static int
        level = 1,
        lives = 5;


    public static void increaseLevel(){
        level++;
    }

    public static void reset(){
        level = 1;
        lives = 5;
    }

    public static void decreaseLives(Board board){
        lives -=1;
        if(lives<0)
            gameOver(board);
        board.start();
    }

    public static void gameOver(Board board){
        board.bounds = null;
        play = false;
        end = true;
    }



    public static void startInstructions(){
        menu = false;
        instructions = true;
    }
    public static void startGame(){
        instructions = false;
        end = false;
        play = true;
    }

    public static void togglePause(){
        pause = !pause;
        play = !play;
    }

    public static boolean isMenu() {
        return menu;
    }
    public static boolean isPlay() {
        return play;
    }
    public static boolean isInstructions() {
        return instructions;
    }
    public static boolean isPause() {
        return pause;
    }
    public static boolean isEnd() {
        return end;
    }

    public static int getLevel() {
        return level;
    }
    public static int getLives() {
        return lives;
    }
}
