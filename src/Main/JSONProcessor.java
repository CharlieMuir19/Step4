import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONProcessor {

    Batch batch = new Batch();
    JSONObject batchDetails = new JSONObject();
    JSONObject gradeDetails = new JSONObject();
    JSONObject batchObject = new JSONObject();
    JSONObject pricingObject = new JSONObject();
    private InputHandler inputHandler;

    public JSONProcessor(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    //save the batch details to a json file in a specified filepath
    public void saveJsonBatchfile(String batchNumber, String date, String fruitCode, int batchWeight) {
        batchDetails.put("Batch Number: ", batchNumber);
        batchDetails.put("Recieved Date: ", date);
        batchDetails.put("Fruit: ", fruitCode);
        batchDetails.put("Batch Weight: ", batchWeight);
        batchObject.put("Batch Details: ", batchDetails);

        //Write to the JSON file
        Path filepath = Paths.get("C:\\Users\\GA\\Documents\\Year1\\CS112 Programming 1- T3 Project\\step1Output\\" + batchNumber + ".json");
        File outputFile = filepath.toFile();
        if (!outputFile.exists()) {
            try (FileWriter file = new FileWriter(String.valueOf(filepath))) {
                file.write(batchObject.toJSONString());
                file.flush();
            } catch (IOException e) {
                System.out.println("Oops, there has been an error saving your batch.");
            }
        } else {
            System.out.println("This batch" + batchNumber + " already exists");
        }
    }

    //this method is called when the user wants to view the details of a single batch.
    //the file is passed as a parameter and used to find the file location.
    public boolean viewSingleBatch(String file) {
        JSONParser jsonParser = new JSONParser();

        //If we cannot find the file, error message is displayed to user stating batch not found
        //if file is found however, we create a parsing object that reads through the file and gets each element in the file
        //it calls another method to carry this out
        try (FileReader reader = new FileReader("C:\\Users\\GA\\Documents\\Year1\\CS112 Programming 1- T3 Project\\step1Output\\" + file + ".json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray batchList = new JSONArray();
            batchList.add(obj);
            batchList.forEach(btc -> parsebatchAndGradeObject((JSONObject) btc));
            System.out.println("\n");
            return true;
        } catch (ParseException | IOException e) {
            System.out.println("Sorry, batch not found");
            return false;
        }
    }

    private void parsebatchObject(JSONObject btc) {
        //Get batch object within list

        JSONObject batchObject = (JSONObject) btc.get("Batch Details: ");
        JSONObject gradeObject = (JSONObject) btc.get("Grade Details: ");

        //Get batch number, recieved date, fruit and batch weight in each batchObject
        String batchNumber = (String) batchObject.get("Batch Number: ");
        System.out.print("Batch Number: " + batchNumber + ", ");
        String date = (String) batchObject.get("Recieved Date: ");
        System.out.println("Recieved Date: " + date + ", ");
        String fruit = (String) batchObject.get("Fruit: ");
        System.out.println("Fruit: " + fruit + ", ");
        long weight = (long) batchObject.get("Batch Weight: ");
        System.out.println("Batch Weight: " + weight + " kgs\n ");
    }

    //This method reads the details from a graded batch, and calculates the individual weight of each grade
    //if the batch has not been graded, it throws an error message stating batch must be graded before viewing
    private void parsebatchAndGradeObject(JSONObject btc) {
        //Get batch object within list
        try {
            //two JSONObjects for each object in the file: one for batch and one for grades
            JSONObject batchObject = (JSONObject) btc.get("Batch Details: ");
            JSONObject gradeObject = (JSONObject) btc.get("Grade Details: ");

            //Get batch number, recieved date, fruit and batch weight in each batchObject
            String batchNumber = (String) batchObject.get("Batch Number: ");
            System.out.print("Batch Number: " + batchNumber + ", ");
            String date = (String) batchObject.get("Recieved Date: ");
            System.out.println("Recieved Date: " + date + ", ");
            String fruit = (String) batchObject.get("Fruit: ");
            System.out.println("Fruit: " + fruit + ", ");
            long weight = (long) batchObject.get("Batch Weight: ");
            System.out.println("Batch Weight: " + weight + " kgs\n ");

            //calculate the weight of each individual grade for the fruit
            long gradeAPercentage = (long) gradeObject.get("Grade A: ");
            float gradeWeightA = (((float) weight / 100) * gradeAPercentage);
            System.out.println("Grade A: " + gradeAPercentage + "% = ");
            System.out.printf("%.2f", gradeWeightA);
            System.out.print("kgs");

            long gradeBPercentage = (long) gradeObject.get("Grade B: ");
            float gradeWeightB = (((float) weight / 100) * gradeBPercentage);
            System.out.println("\nGrade B: " + gradeBPercentage + "% = ");
            System.out.printf("%.2f", gradeWeightB);
            System.out.print("kgs");

            long gradeCPercentage = (long) gradeObject.get("Grade C: ");
            float gradeWeightC = (((float) weight / 100) * gradeCPercentage);
            System.out.println("\nGrade C: " + gradeCPercentage + "% = ");
            System.out.printf("%.2f", gradeWeightC);
            System.out.print("kgs");

            long rejectedPercentage = (long) gradeObject.get("Rejected: ");
            float rejectedWeight = (((float) weight / 100) * rejectedPercentage);
            System.out.println("\nRejects: " + rejectedPercentage + "% = ");
            System.out.printf("%.2f", rejectedWeight);
            System.out.print("kgs");
        } catch (Exception e) {
            System.out.println("Please grade this batch before viewing grade details.");
        }
    }

    //We recieve the percentages of each grade from the user and check if the total meets 100%
    //by calling the method calculatePercentage.
    //if it totals to 100%, it returns true, and allows the program to decide whether to proceed or repeat the process
    public boolean getGradeBatches(String file) {
        System.out.println("Enter percentage of GRADE A fruit in batch: ");
        int gradeA = inputHandler.getGrade();
        System.out.println("Enter percentage of GRADE B fruit in batch: ");
        int gradeB = inputHandler.getGrade();
        System.out.println("Enter percentage of GRADE C fruit in batch: ");
        int gradeC = inputHandler.getGrade();
        System.out.println("Enter percentage of REJECTED fruit in batch: ");
        int rejected = inputHandler.getGrade();
        boolean withinLimits = calculatePercentage(file, gradeA, gradeB, gradeC, rejected);
        return withinLimits;
    }

    //This method calculates whether the percentages given by the user totals to 100%.
    //if too high or low, it repeats, but if 100%,
    private boolean calculatePercentage(String file, int gradeA, int gradeB, int gradeC, int rejected) {
        boolean limit = false;
        if (gradeA + gradeB + gradeC + rejected > 100) {
            System.out.println("Your grading does not reach 100%: TOO HIGH. Please try again.");
            limit = false;
        } else if (gradeA + gradeB + gradeC + rejected < 0) {
            System.out.println("Your grading does not reach 100%. Please try again.");
            limit = false;
        } else if (gradeA + gradeB + gradeC + rejected == 100) {
            System.out.println("You have " + gradeA + "% of GRADE A fruit.");
            System.out.println("You have " + gradeB + "% of GRADE B fruit.");
            System.out.println("You have " + gradeC + "% of GRADE C fruit.");
            System.out.println("You have " + rejected + "% of REJECTED fruit.");
            System.out.println("Is this correct?");
            System.out.println("1. Yes \n 2. No");
            int choice = inputHandler.decide(1, 2);
            //if user says yes, set the values and the calls the saveGradeDetails method.
            //if saved successfully, returns true, if not then false
            //if user says no, then returns false
            if (choice == 1) {
                batch.setFile(file);
                batch.setGradeA(gradeA);
                batch.setGradeB(gradeB);
                batch.setGradeC(gradeC);
                batch.setRejected(rejected);
                limit = saveGradeDetails(batch.getFile());
            } else if (choice == 2) {
                limit = false;
            }
        }
        return limit;
    }

    //this method reads the details from the file using a jsonParser,
    //and attempts to write the details back into the file alongside the grade details in the method
    //writeBatchAndGradeObject
    private boolean saveGradeDetails(String file) {

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\GA\\Documents\\Year1\\CS112 Programming 1- T3 Project\\step1Output\\" + file + ".json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray batchList = new JSONArray();
            batchList.add(obj);
            batchList.forEach(btc -> writeBatchAndGradeObject((JSONObject) btc));
            return true;
        } catch (IOException | ParseException e) {
            System.out.println("Sorry, batch not found");
            return false;
        }
    }

    //using the details we recieved inside the larger method, we write these details back into the batchObject,
    //alongside the new grade details.
    //if successfully written to the file, then we state so, if not then error message is displayed
    private void writeBatchAndGradeObject(JSONObject btc) {
        JSONObject batchObjects = (JSONObject) btc.get("Batch Details: ");

        //Get batch number, recieved date, fruit and batch weight in each batchObject
        String batchNumber = (String) batchObjects.get("Batch Number: ");
        System.out.print("Batch Number: " + batchNumber + ", ");
        String date = (String) batchObjects.get("Recieved Date: ");
        System.out.println("Recieved Date: " + date + ", ");
        String fruit = (String) batchObjects.get("Fruit: ");
        System.out.println("Fruit: " + fruit + ", ");
        long weight = (long) batchObjects.get("Batch Weight: ");
        System.out.println("Batch Weight: " + weight + " kgs\n ");

        batchDetails.put("Batch Number: ", batchNumber);
        batchDetails.put("Recieved Date: ", date);
        batchDetails.put("Fruit: ", fruit);
        batchDetails.put("Batch Weight: ", weight);

        gradeDetails.put("Grade A: ", batch.getGradeA());
        gradeDetails.put("Grade B: ", batch.getGradeB());
        gradeDetails.put("Grade C: ", batch.getGradeC());
        gradeDetails.put("Rejected: ", batch.getRejected());

        batchObject.put("Batch Details: ", batchDetails);
        batchObject.put("Grade Details: ", gradeDetails);

        try (FileWriter files = new FileWriter("C:\\Users\\GA\\Documents\\Year1\\CS112 Programming 1- T3 Project\\step1Output\\" + batch.getFile() + ".json")) {
            files.write(batchObject.toJSONString());
            files.flush();
            System.out.println("Updated batch details are saved to " + batch.getFile());
        } catch (IOException e) {
            System.out.println("Oops, there are no grades to save.");
        }
    }

    //JSON parser object to parse read file
    //first retrieve the names of all files,
    // and using a loop get each file, list batch details then get next file
    public void listAllBatches() {
        //sources to credit: https://howtodoinjava.com/library/json-simple-read-write-json-examples/#maven
        //https://stackoverflow.com/questions/36240647/reading-multiple-files-in-a-folder-using-java

        File[] listOfBatches;
        String[] myDocs;

        File batchOutputFolder = new File("C:\\Users\\GA\\Documents\\Year1\\CS112 Programming 1- T3 Project\\step1Output\\");
        listOfBatches = batchOutputFolder.listFiles();
        assert listOfBatches != null;
        myDocs = new String[listOfBatches.length];



        for (int i = 0; i < listOfBatches.length; i++) {
            myDocs[i] = listOfBatches[i].getName();
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader("C:\\Users\\GA\\Documents\\Year1\\CS112 Programming 1- T3 Project\\step1Output\\" + myDocs[i])) {
                //Read JSON file
                Object obj = jsonParser.parse(reader);
                JSONArray batchList = new JSONArray();
                batchList.add(obj);
                System.out.println("File: " + listOfBatches[i].getName());
                batchList.forEach(btc -> parsebatchObject((JSONObject) btc));
            } catch (ParseException | IOException e) {
                System.out.println("Oops, the file " + myDocs[i] + " has invalid data and the batch details cannot be recieved. Please try again later");
            }
        }
    }

    //we use a FileReader to determine whether we find a file for prices already in our filepath
    //we return true if we have set values already, and we return false if the file is not found
    public boolean checkJSONPrice(String date) {
        try (FileReader reader = new FileReader("C:\\Users\\GA\\Documents\\Year1\\CS112 Programming 1- T3 Project\\Pricing\\" + date + "PRICING.json")) {
            System.out.println("\n\nPrices for " + date + " have been set.\n");
            return true;
        } catch (IOException e) {
            System.out.println("\n\nPrices for " + date + " have not been set. \nPlease enter fruit prices before proceeding.");
            return false;
        }
    }

    //All the values have been passed as parameters to be stored inside the JSON file.
    public void writeJSONPriceFile(String date, double stGradeAPrice, double stGradeBPrice, double stGradeCPrice, double stRejectedPrice,
                                   double raGradeAPrice, double raGradeBPrice, double raGradeCPrice, double raRejectedPrice,
                                   double blGradeAPrice, double blGradeBPrice, double blGradeCPrice, double blRejectedPrice,
                                   double goGradeAPrice, double goGradeBPrice, double goGradeCPrice, double goRejectedPrice) {
        JSONObject pricingDetails = new JSONObject();
        pricingDetails.put("ST Grade A Price ", stGradeAPrice);
        pricingDetails.put("ST Grade B Price ", stGradeBPrice);
        pricingDetails.put("ST Grade C Price ", stGradeCPrice);
        pricingDetails.put("ST Rejected Price ", stRejectedPrice);

        pricingDetails.put("RA Grade A Price ", raGradeAPrice);
        pricingDetails.put("RA Grade B Price ", raGradeBPrice);
        pricingDetails.put("RA Grade C Price ", raGradeCPrice);
        pricingDetails.put("RA Rejected Price ", raRejectedPrice);

        pricingDetails.put("BL Grade A Price ", blGradeAPrice);
        pricingDetails.put("BL Grade B Price ", blGradeBPrice);
        pricingDetails.put("BL Grade C Price ", blGradeCPrice);
        pricingDetails.put("BL Rejected Price ", blRejectedPrice);

        pricingDetails.put("GO Grade A Price ", goGradeAPrice);
        pricingDetails.put("GO Grade B Price ", goGradeBPrice);
        pricingDetails.put("GO Grade C Price ", goGradeCPrice);
        pricingDetails.put("GO Rejected Price ", goRejectedPrice);

        pricingObject.put("Pricing Details: ", pricingDetails);

        Path filepath = Paths.get("C:\\Users\\GA\\Documents\\Year1\\CS112 Programming 1- T3 Project\\Pricing\\" + date + "PRICING.json");
        //Write to the JSON file
        try (FileWriter file = new FileWriter(String.valueOf(filepath))) {
            file.write(pricingObject.toJSONString());
            file.flush();
            System.out.println("Prices saved to " + date + "PRICING.json");
        } catch (IOException e) {
            System.out.println("Could not save prices at this time.");
        }
    }

    //If prices are set, we access the file and then read the values out from the file
    public void readJSONPrices(String date){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\GA\\Documents\\Year1\\CS112 Programming 1- T3 Project\\Pricing\\" + date + "PRICING.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray batchList = new JSONArray();
            batchList.add(obj);
            batchList.forEach(btc -> readPricingObject((JSONObject) btc));
        } catch (IOException | ParseException e) {
            System.out.println("Sorry, prices not found");
        }
    }

    //This method reads what is inside the file and displays all the prices to the user.
    private void readPricingObject(JSONObject btc) {
        JSONObject pricingObjects = (JSONObject) btc.get("Pricing Details: ");
        System.out.println("[Farmers prices]\nStrawberries: ");

        double stGradeA = (double) pricingObjects.get("ST Grade A Price ");
        System.out.print("Grade A: £" + stGradeA + ", ");
        double stGradeB = (double) pricingObjects.get("ST Grade B Price ");
        System.out.print("Grade B: £" + stGradeB + ", ");
        double stGradeC = (double) pricingObjects.get("ST Grade C Price ");
        System.out.print("Grade C: £" + stGradeC + ", ");
        double stRejected = (double) pricingObjects.get("ST Rejected Price ");
        System.out.print("Rejects: £" + stRejected + ", \n");

        System.out.println("Raspberries: ");
        double raGradeA = (double) pricingObjects.get("RA Grade A Price ");
        System.out.print("Grade A: £" + raGradeA + ", ");
        double raGradeB = (double) pricingObjects.get("RA Grade B Price ");
        System.out.print("Grade B: £" + raGradeB + ", ");
        double raGradeC = (double) pricingObjects.get("RA Grade C Price ");
        System.out.print("Grade C: £" + raGradeC + ", ");
        double raRejected = (double) pricingObjects.get("RA Rejected Price ");
        System.out.print("Rejects: £" + raRejected + ", \n");

        System.out.println("Blackberries: ");
        double blGradeA = (double) pricingObjects.get("BL Grade A Price ");
        System.out.print("Grade A: £" + blGradeA + ", ");
        double blGradeB = (double) pricingObjects.get("BL Grade B Price ");
        System.out.print("Grade B: £" + blGradeB + ", ");
        double blGradeC = (double) pricingObjects.get("BL Grade C Price ");
        System.out.print("Grade C: £" + blGradeC + ", ");
        double blRejected = (double) pricingObjects.get("BL Rejected Price ");
        System.out.print("Rejects: £" + blRejected + ", \n");

        System.out.println("Gooseberries: ");
        double goGradeA = (double) pricingObjects.get("GO Grade A Price ");
        System.out.print("Grade A: £" + goGradeA + ", ");
        double goGradeB = (double) pricingObjects.get("GO Grade B Price ");
        System.out.print("Grade B: £" + goGradeB + ", ");
        double goGradeC = (double) pricingObjects.get("GO Grade C Price ");
        System.out.print("Grade C: £" + goGradeC + ", ");
        double goRejected = (double) pricingObjects.get("GO Rejected Price ");
        System.out.print("Rejects: £" + goRejected + ", \n");
    }

}
