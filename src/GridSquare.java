public class GridSquare {

    private int contents, xPosition, yPosition;
    private boolean flaged, reveled;
    private char image;

    GridSquare(int myX, int myY, int myContents){
        this.xPosition = myX;
        this.yPosition = myY;
        this.contents = myContents;
        UpdateImage();
    }

    public char CheckSquar()
    {
        UpdateImage();
        return this.image;
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
