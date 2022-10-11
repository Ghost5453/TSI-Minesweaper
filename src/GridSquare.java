public class GridSquare {

    private int contents, xPosition, yPosition;
    private boolean flaged, reveled;
    private char image;

    GridSquare(int myX, int myY, int myContents){
        this.xPosition = myX;
        this.yPosition = myY;
        this.contents = myContents;
        this.flaged = false;
        UpdateImage();
    }

    public char CheckSquare()
    {
        UpdateImage();
        return this.image;
    }

    public void FlagSquare(){
        flaged = !flaged;
        UpdateImage();
    }

    public void ReveleSquare()
    {
        reveled = true;
        UpdateImage();
    }

    private void UpdateImage()
    {
        if (reveled)
        {
            if (contents == 0)
            {
                image = '\u25A0';

            } else if (contents > 0 && contents <= 8)
            {
                char[] myCharArr = Character.toChars(contents);
                image = myCharArr[0];

            } else
            {
                Main.EndGame(false);
            }
        }else
        {
            if(flaged)
            {
                image = '\u22A0';
            }else {

                image = '\u25A1';

            }
        }
    }

}
