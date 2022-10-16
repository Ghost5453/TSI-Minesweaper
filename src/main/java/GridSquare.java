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
        UpdateImage();
    }
    //endregion

    //region Outputs
    public String ShowSquareImage()
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
        String greenColour = "\033[0;32m";
        String redColour = "\033[0;31m";
        String yellowColour ="\033[0;33m";
        String whiteColour = "\033[0;37m";

        if (reveled)
        {
            if (contents >= 0)
            {
                if(contents > 0)
                {
                    String myStr;
                    myStr = String.valueOf(contents);
                    image = yellowColour + myStr + whiteColour;
                }else
                {
                    image = whiteColour + String.valueOf(contents) + whiteColour;
                }
            } else
            {
                if (flagged)
                {
                    image = greenColour + "\u00D7" + whiteColour;
                }else
                {
                    image = redColour + "\u00D7" + whiteColour;
                }
            }
        }else
        {
            if(flagged)
            {
                image = redColour + "\u22A0" + whiteColour;
            }else {

                image =whiteColour + "\u25A0" + whiteColour;
            }
        }
    }
    //endregion
}
