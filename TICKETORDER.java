
import javax.swing.JOptionPane;    
public class TICKETORDER
{

    private String cID;
    private char tID;
    private int notickets;
    private String ticketID;
    private char purchase;
     private String purchasefull;
    public TICKETORDER()
    {
        // initialise instance variables

        cID= "";
        tID = ' ' ;
        purchase = ' ';
        ticketID ="";
        notickets = 0;
        purchasefull = " ";
    }

    // file handling store details from file
    public void readTicketorderDetails(String dataItems)
    {
        String[] rowItems = dataItems.split(",");
        // store each data item as instance property
        cID = rowItems[0];
        tID = (rowItems[1]).charAt(0);
        ticketID =rowItems[1];
        notickets = Integer.parseInt(rowItems[2]);
        purchase = rowItems[3].charAt(0);
        purchasefull = rowItems[3];

    }
    public String writeDetails()
    {
        // join up data into a string to output as a row
        // use "," to separate csv columns

        String memberData = "";
        memberData = memberData.concat(cID);
        memberData = memberData.concat(",");
        memberData = memberData.concat((ticketID));
        memberData = memberData.concat(",");
        memberData = memberData.concat(Float.toString(notickets));
        memberData = memberData.concat(",");
        memberData = memberData.concat(purchasefull);

        return memberData;
    }

    public char getPURCHASE() 
    {
        return purchase;
    }

    public char gettID() 
    {
        return tID;
    }
    //public void displayDetails()
    //{
    // output basic details
    //System.out.print("Member: " +  + " " + sName);
    //System.out.print(",  mark is " + mark);

    //System.out.println();
    //}

}
//TICKETORDER=toppupil
//MARK = PURCHASE
//TOPMARK=PURCHASEMETHOD