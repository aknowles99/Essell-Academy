
import javax.swing.JOptionPane;    
public class TICKETORDER
{

    private String vcID;
    private char vtID;
    private int vnotickets;
    private String vticketID;
    private char vpurchase;
     private String vpurchasefull;
    public TICKETORDER()
    {
        // initialise instance variables

        vcID= "";
        vtID = ' ' ;
        vpurchase = ' ';
        vticketID ="";
        vnotickets = 0;
        vpurchasefull = " ";
    }

    // file handling store details from file
    public void readTicketorderDetails(String dataItems)
    {
        String[] rowItems = dataItems.split(",");
        // store each data item as instance property
        vcID = rowItems[0];
        vtID = (rowItems[1]).charAt(0);
        vticketID =rowItems[1];
        vnotickets = Integer.parseInt(rowItems[2]);
        vpurchase = rowItems[3].charAt(0);
        vpurchasefull = rowItems[3];

    }
    public String writeDetails()
    {
        // join up data into a string to output as a row
        // use "," to separate csv columns

        String vmemberData = "";
        vmemberData = vmemberData.concat(vcID);
        vmemberData = vmemberData.concat(",");
        vmemberData = vmemberData.concat((vticketID));
        vmemberData = vmemberData.concat(",");
        vmemberData = vmemberData.concat(Float.toString(vnotickets));
        vmemberData = vmemberData.concat(",");
        vmemberData = vmemberData.concat(vpurchasefull);

        return vmemberData;
    }

    public char getPURCHASE() 
    {
        return vpurchase;
    }

    public char gettID() 
    {
        return vtID;
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