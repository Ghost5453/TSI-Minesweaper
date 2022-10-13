public class GridSquare {

    private int contents;
    private boolean flagged, reveled;
    private String image;

    GridSquare(int myContents){

        this.contents = myContents;
        this.flagged = false;
        this.reveled = false;
        UpdateImage();
    }

    public String CheckSquare()
    {
        UpdateImage();
        return this.image;
    }

    public void FlagSquare(){
        flagged = !flagged;
        UpdateImage();
    }

    public void ForceRevele()
    {
        reveled = true;
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
                if(contents > 0)
                {
                    image = "\033[0;33m" + String.valueOf(contents) + "\033[0;37m";
                }else
                {
                    image = String.valueOf(contents);
                }
            } else
            {
//                Main.EndGame(false);
                image = "\u00D7";
            }
        }else
        {
            if(flagged)
            {
                image = "\033[0;31m\u22A0\033[0;37m";
            }else {

                image = "\u25A0";

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
