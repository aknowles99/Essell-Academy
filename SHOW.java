import javax.swing.JOptionPane;
import java.io.*; 
import java.util.Calendar;              // for general file handling
public class SHOW
{

    
    private TICKETORDER ticketorderlist[];// array of ticket orders 
    private int vnoOfTicketorder;
    private int vpurchasemethodS;
    private int vTotal;
    private int vyear;
    private int vpurchasemethodW;
    private String vpopmethod;

    // CLASSes to open, create, read/write, close files
    FILEREADCSV purchaseFile; 
    // CLASSes to open, create, read/write, close files
    FILEWRITECSV resultsFile;        

    public SHOW()  throws IOException
    {
        //declaring the variables
        purchaseFile = new FILEREADCSV();
        vyear = 0;
        resultsFile = new FILEWRITECSV();
        vTotal = 0;
        vpurchasemethodW = 0;
        vpurchasemethodS = 0;
        vnoOfTicketorder = 0;
        vpopmethod = " ";
    }

    public void processorder()  throws IOException
    {
        Ticketorder();
        countPURCHASE();
        calcMETHOD();
        saveNewMembers();
        getdate();
        Display();

    }
    private void Ticketorder() throws IOException
    {

        System.out.println("** Preparing to read data file.");
        String[] dataRows = purchaseFile.readCSVtable();
        vnoOfTicketorder = dataRows.length ;// number of orders calculated after reading file
        System.out.println("** " + vnoOfTicketorder + " rows read.\n\n");
      
        ticketorderlist = new TICKETORDER[vnoOfTicketorder];

        for  (int i = 0; i < vnoOfTicketorder; i++) {
            ticketorderlist[i] = new TICKETORDER();

            ticketorderlist[i].readTicketorderDetails(dataRows[i]);
        }
    }

    public void countPURCHASE() throws IOException
    {
        // *prepare a String to write data to disc
        String fileContent = "";

        // start the count
        int vcount = 0;

        // loop for each item : member
        for (int i = 0; i < vnoOfTicketorder; i++)
        {
            // decide if current item: member matches target: bmi
            if (ticketorderlist[i].getPURCHASE() == 'S' )
            {
                vpurchasemethodS = vpurchasemethodS +1;
            }
            else 
            {
                vpurchasemethodW = vpurchasemethodW +1;
            }

            if (ticketorderlist[i].gettID() == 'T' || ticketorderlist[i].gettID() == 'W')
            {

                vTotal = vTotal + 5;
                // *display the details for the member
            }
            else 
            {
                vTotal = vTotal + 10;

                //resultsFile.writeCSVtable(fileContent);
            }

            // display the final count: bmi
            //System.out.println("\n The total money rasied for charity is £" + Total);
            //System.out.println("the most popular method of sale is " + popmethod);
            // A blank line to separate this report from others.
            //System.out.println();

            // *send for writing to file as a string containing all data

        }

    }

    public static void main(String[] args)  throws IOException
    {
        SHOW myShow = new SHOW();
        myShow.processorder();
    }

    public void saveNewMembers() throws IOException
    {
        String fileContent = "";
        int vcount = 0;
        for (int i = 0; i < vnoOfTicketorder; i++) 
        {
            if(ticketorderlist[i].gettID() == 'F' )
            {
                vcount = vcount + 1;
                if (vcount>1) 
                {
                    fileContent = fileContent.concat("\n");
                }
                fileContent = fileContent.concat(ticketorderlist[i].writeDetails());
            }
        }

        // *send for writing to file as a string containing all data
        System.out.println("** Preparing to write new members file.");
        resultsFile.writeCSVtable(fileContent);
        System.out.println("** File written and closed.");
    }


    public void calcMETHOD()
    {
        if (vpurchasemethodS > vpurchasemethodW)
        {
            vpopmethod = "sold in school";
        }
        else 
        {
            vpopmethod = "sold online";
        }
    }

    public void getdate()
    {
        vyear = Calendar.getInstance().get(Calendar.YEAR);
    }

    public void Display()
    {
        System.out.println("\n Essell Academy Choral Shield " + vyear);
        System.out.println("\n The total money rasied for charity is £" + vTotal);
        System.out.println("the most popular method of sale is " + (vpopmethod));
        // A blank line to separate this report from others.
        System.out.println();
    }
    //show=pupil
}