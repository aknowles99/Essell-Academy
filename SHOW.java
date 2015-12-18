import javax.swing.JOptionPane;
import java.io.*; 
import java.util.Calendar;              // for general file handling
public class SHOW
{
   

    
 
    private TICKETORDER ticketorderlist[];// array of ticket orders 
    private int noOfTicketorder;
    private int purchasemethodW;
    private int purchasemethodS;
    private String popmethod;
    private int Total;
    private int year;

    // CLASSes to open, create, read/write, close files
    FILEREADCSV purchaseFile; 
    // CLASSes to open, create, read/write, close files
    FILEWRITECSV resultsFile;        

    public SHOW()  throws IOException
    {
       //declaring the variables
        purchaseFile = new FILEREADCSV();
        year = 0;
        resultsFile = new FILEWRITECSV();
        Total = 0;
        purchasemethodW = 0;
        purchasemethodS = 0;
        noOfTicketorder = 0;
        popmethod = " ";
    }

    public void processTicketorder()  throws IOException
    {
        setUpTicketorderList();
        countPURCHASE();
        calcMETHOD();
        saveNewMembers();
        getdate();
        Display();
    

    }

    private void setUpTicketorderList() throws IOException
    {
       

        System.out.println("** Preparing to read data file.");

        String[] dataRows = purchaseFile.readCSVtable();
       
        noOfTicketorder = dataRows.length ;// number of orders calculated after reading file

        System.out.println("** " + noOfTicketorder + " rows read.\n\n");

      
        ticketorderlist = new TICKETORDER[noOfTicketorder];
     
        for  (int i = 0; i < noOfTicketorder; i++) {
            ticketorderlist[i] = new TICKETORDER();
            
            ticketorderlist[i].readTicketorderDetails(dataRows[i]);
        }
    }

    public void countPURCHASE() throws IOException
    {
        // *prepare a String to write data to disc
        String fileContent = "";

        // start the count
        int count = 0;

        // loop for each item : member
        for (int i = 0; i < noOfTicketorder; i++)
        {
            // decide if current item: member matches target: bmi
            if (ticketorderlist[i].getPURCHASE() == 'S' )
            {
                purchasemethodS = purchasemethodS +1;
            }
            else 
            {
                purchasemethodW = purchasemethodW +1;
            }

            if (ticketorderlist[i].gettID() == 'T' || ticketorderlist[i].gettID() == 'W')
            {

                Total = Total + 5;
                // *display the details for the member
            }
            else 
            {
                Total = Total + 10;
                
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
     public void saveNewMembers() throws IOException
    {
        String fileContent = "";
        int count = 0;
        for (int i = 0; i < noOfTicketorder; i++) 
        {
            if(ticketorderlist[i].gettID() == 'F' )
            {
                count = count + 1;
                if (count>1) 
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
    public void getdate()
    {
        year = Calendar.getInstance().get(Calendar.YEAR);
    }

    public static void main(String[] args)  throws IOException
    {
        SHOW myShow = new SHOW();
        myShow.processTicketorder();
    }

    public void calcMETHOD()
    {
        if (purchasemethodS > purchasemethodW)
        {
            popmethod = "sold in school";
        }
        else 
        {
            popmethod = "sold online";
        }
    }

    public void Display()
    {
        System.out.println("\n Essell Academy Choral Shield " + year);
        System.out.println("\n The total money rasied for charity is £" + Total);
        System.out.println("the most popular method of sale is " + (popmethod));
        // A blank line to separate this report from others.
        System.out.println();
    }
    //show=pupil
}