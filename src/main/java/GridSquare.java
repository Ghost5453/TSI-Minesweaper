public class GridSquare {

    private int contents;

    private boolean flagged;
    private boolean reveled;

    private String image;

    GridSquare(int myContents){

        this.contents = myContents;
        this.flagged = false;
        this.reveled = false;
        UpdateImage();
    }

    //region Inputs
    public void FlagSquare(){
        if (!reveled)
        {
            flagged = !flagged;
            UpdateImage();
        }
    }

    public void RevelSquare()
    {
        if (!flagged && !reveled)
        {
            reveled = true;
            UpdateImage();
        }
    }

    public void ForceRevel()
    {
        reveled = true;
        flagged = false;
        UpdateImage();
    }
    //endregion

    //region Outputs
    public String CheckSquare()
    {
        UpdateImage();
        return this.image;
    }

    //region Getters
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
    //endregion

    //endregion

    //region class methods
    private void UpdateImage()
    {
        if (reveled)
        {
            if (contents >= 0)
            {
                if(contents > 0)
                {
                    String myStr;

                    myStr = String.valueOf(contents);
                    image = "\033[0;33m" + myStr + "\033[0;37m";
                }else
                {
                    image = String.valueOf(contents);
                }
            } else
            {
                image = "\033[0;31m\u00D7\033[0;37m";
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
    //endregion
}
