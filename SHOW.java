import javax.swing.JOptionPane;
import java.io.*;               // for general file handling
public class SHOW
{
    // array of MEMBER objects

    // number of members calculated after reading file
    
    private TICKETORDER ticketorderlist[];
    int noOfTicketorder;
    int topmark;

    // CLASSes to open, create, read/write, close files
    FILEREADCSV markFile; 
    // CLASSes to open, create, read/write, close files
    FILEWRITECSV resultsFile;        

    public SHOW()  throws IOException
    {
        // create file handler objects
        markFile = new FILEREADCSV();

        resultsFile = new FILEWRITECSV();

        topmark = 0;
        noOfTicketorder = 49;

    }

    public void processTicketorder()  throws IOException
    {
        setUpTicketorderList();
        displayTicketorder();
        countMARK();
    }

    private void setUpTicketorderList() throws IOException
    {
        // First user message
        System.out.println("ScotFit Club: Membership BMI update/n");
        System.out.println("** Preparing to read data file.");

        // read file, fetch data as String array containing the rows
        String[] dataRows = markFile.readCSVtable();
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

    public void displayTicketorder() {
        // Heading for the display
        System.out.println("A listing of all applicants for the next year\n");
        // results
        for  (int i = 0; i < noOfTicketorder; i++) {
            ticketorderlist[i].displayDetails();
        }
        // 2 blank line to separate this report from others.
        System.out.print("\n\n\n");
    }

    public void countMARK() throws IOException
    {
        // *prepare a String to write data to disc
        String fileContent = "";

        System.out.println("A report of members within ideal BMI\n");
         int personnumber = 0;
        // start the count
        int count = 0;
        // loop for each item : member
        for (int i = 0; i < noOfTicketorder; i++)
        {
            // decide if current item: member matches target: bmi
            if (ticketorderlist[i].getMARK() > topmark)
            {
                // add 1 to count: for OK bmi
                topmark = ticketorderlist[i].getMARK() ;
                // *display the details for the member
                personnumber = i;

                // *use new line to separate rows in csv file, after 1st line
                if (count>1) 
                {
                    fileContent = fileContent.concat("\n");
                }
                // *join on next line of data for writing to file
                fileContent = fileContent.concat(ticketorderlist[i].writeDetails());
            }
        }
        // display the final count: bmi
        System.out.println("\n Top mark is  : " + topmark);
        System.out.println("which belongs to : " + personnumber);
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
//show=pupil
