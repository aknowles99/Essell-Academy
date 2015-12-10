import javax.swing.JOptionPane;
import java.io.*;               // for general file handling
public class SHOW
{
    // array of MEMBER objects

    // number of members calculated after reading file
    
    private TICKETORDER ticketorderlist[];
    private int noOfTicketorder;
    private String purchasemethod;
     private String poppurchasemethod;
    private int Total;

    // CLASSes to open, create, read/write, close files
    FILEREADCSV purchaseFile; 
    // CLASSes to open, create, read/write, close files
    FILEWRITECSV resultsFile;        

    public SHOW()  throws IOException
    {
        // create file handler objects
        purchaseFile = new FILEREADCSV();

        resultsFile = new FILEWRITECSV();
        Total = 0;
        purchasemethod = " ";
        noOfTicketorder = 49;
poppurchasemethod = " ";
    }

    public void processTicketorder()  throws IOException
    {
        setUpTicketorderList();
        //displayTicketorder();
        countPURCHASE();
    }

    private void setUpTicketorderList() throws IOException
    {
        // First user message
     
        System.out.println("** Preparing to read data file.");

        // read file, fetch data as String array containing the rows
        String[] dataRows = purchaseFile.readCSVtable();
        // calculate the number of member rows, skip headings
        noOfTicketorder = dataRows.length - 1;

        // update user with number of rows with membership details
        System.out.println("** " + noOfTicketorder + " rows read.\n\n");

        // prepare array for members
       ticketorderlist = new TICKETORDER[noOfTicketorder];
        // create member objects and copy data from source
        for  (int i = 0; i < noOfTicketorder; i++) {
          ticketorderlist[i] = new TICKETORDER();
            // adjust to skip headings
           ticketorderlist[i].readTicketorderDetails(dataRows[i+1]);
        }
    }

   // public void displayTicketorder() {
        // Heading for the display
        //ystem.out.println("A listing of all applicants for the next year\n");
        // results
        //for  (int i = 0; i < noOfTicketorder; i++) {
        //    ticketorderlist[i].displayDetails();
        //}
        // 2 blank line to separate this report from others.
       // System.out.print("\n\n\n");
   // }

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
            if (ticketorderlist[i].gettID() = T || W )
            {
               
               Total = Total + 5;
                // *display the details for the member
            }
              else if  ( ticketorderlist[i].gettID() = F  )
              {
                  Total = Total + 10;
                }
                // *use new line to separate rows in csv file, after 1st line
                
               // if (count>1) 
               // {
               //     fileContent = fileContent.concat("\n");
                //}
                // *join on next line of data for writing to file
               // fileContent = fileContent.concat(ticketorderlist[i].writeDetails());
            }
        }
        // display the final count: bmi
        System.out.println("\n The total money rasied for charity is £" + Total);
        System.out.println("the most popular method of sale is " + poppurchasemethod);
        // A blank line to separate this report from others.
        System.out.println();

        // *send for writing to file as a string containing all data
        System.out.println("** Preparing to write data file.");
        resultsFile.writeCSVtable(fileContent);
        System.out.println("** File written and closed.");
    }

    public static void main(String[] args)  throws IOException
    {
        SHOW myShow = new SHOW();
        myShow.processTicketorder();
    }
}
}
//show=pupil
