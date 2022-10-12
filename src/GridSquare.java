public class GridSquare {

    private int contents;
    private boolean flagged, reveled;
    private char image;

    GridSquare(int myContents){

        this.contents = myContents;
        this.flagged = false;
        this.reveled = false;
        UpdateImage();
    }

    public char CheckSquare()
    {
        UpdateImage();
        return this.image;
    }

    public void FlagSquare(){
        flagged = !flagged;
        UpdateImage();
    }

    public void RevelSquare()
    {
        if (!flagged && !reveled)
        {
            reveled = true;
            UpdateImage();
        }
    }

    private void UpdateImage()
    {
        if (reveled)
        {
            if (contents >= 0 && contents <= 8)
            {
                image = Character.forDigit(contents, 10);

            } else
            {
//                Main.EndGame(false);
                image = '\u00D7';
            }
        }else
        {
            if(flagged)
            {
                image = '\u22A0';
            }else {

                image = '\u25A0';

            }
        }
    }

    public boolean GetFlag()
    {
        return flagged;
    }

    public boolean GetReveled()
    {
        return reveled;
    }

    public int GetContents()
    {
        return contents;
    }

}
