package cucumber.stepDefinitions;

import cucumber.customDataTableType.PersonData;
import io.cucumber.datatable.*;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DataTableExampleSteps {
    private WebDriver driver;
    private final DataTableTypeRegistry registry = new DataTableTypeRegistry(Locale.ENGLISH);

    // Defining custom datatype - DOES NOT WORK RIGHT NOW
    public DataTableExampleSteps() {
        this.driver = Hooks.driver;
        registry.defineDataTableType(new DataTableType(
                PersonData.class,
                new TableEntryTransformer<PersonData>() {
                    @Override
                    public PersonData transform(Map<String, String> map) throws Throwable {
                        return new PersonData(
                                map.get("name"),
                                map.get("genre"),
                                map.get("language")
                        );
                    }
                }
        ));
    }

    //---------------------------------------------------
    //------------One column examples--------------------
    //---------------------------------------------------
    @When("I receive one column table as DataTable")
    public void iReceiveDataTableAsDataTable(DataTable table) {
        System.out.println("Content of the DataTable----");
        System.out.println(table.toString());

        System.out.println("Value of second item, index starts from 0 (0,0 = first row, first column)----");
        System.out.println(table.cell(1,0));
        System.out.println(table.row(1));

        System.out.println("Prints the first column from table------");
        System.out.println(table.column(0));

        System.out.println("Converting DataTable to List of Strings");
        List<String> convertedTable = table.asList();
        System.out.println(convertedTable);
        System.out.println();
    }

    @When("I receive one column table as List")
    public void iReceiveAsList(List<String> table) {
        System.out.println("Contents of the table-----");
        System.out.println(table);
        System.out.println();
    }

    @When("I receive one column integer table as DataTable")
    public void iReceiveIntegerDataTable(DataTable table) {
        List<Integer> intList = table.asList(Integer.class);

        int sum = intList.stream().reduce(0, Integer::sum);
        System.out.println("Sum of all DataTable integers = " + sum);
        System.out.println();
    }

    @When("I receive one column integer table as List")
    public void iReceiveIntegerList(List<Integer> table) {
        int sum = table.stream().reduce(0, Integer::sum);
        System.out.println("Sum of all Integer List integers = " + sum);
        System.out.println();
    }

    //---------------------------------------------------
    //------------Two column examples--------------------
    //---------------------------------------------------
    @When("I receive two column table as DataTable")
    public void iReceiveTwoColumnsDataTable(DataTable table) {
        System.out.println("Value of second row, and separately the second column for that row----");
        System.out.println(table.row(1));
        System.out.println(table.cell(1,1));

        System.out.println("Print out converted DataTable into Map");
        Map<String, String> convertedTable = table.asMap();
        System.out.println(convertedTable);

        System.out.println("Print out language---");
        System.out.println(convertedTable.get("language"));
        System.out.println();
    }

    @When("I receive two column table as Map")
    public void iReceiveTwoColumnsMap(Map<String, String> table) {
        System.out.println("Print out Map received in step");
        System.out.println(table);
        System.out.println("Print out genre---");
        System.out.println(table.get("genre"));
        System.out.println();
    }

    @When("I receive two column table as List of strings")
    public void iReceiveTwoColumnsListOfLists(List<List<String>> table) {
        System.out.println("Print out List of Lists received in step");
        System.out.println(table);
        System.out.println();
    }

    @When("I receive two column table with List of numbers as DataTable")
    public void iReceiveTwoColumnsOfIntegersTable(DataTable table) {
        List<List<Integer>> convertedTable = table.asLists(Integer.class);
        System.out.println("List<List<Integer>> table-------");
        convertedTable.forEach(System.out::println);
        System.out.println("Second row of table----");
        System.out.println(convertedTable.get(1));
        System.out.println();
    }

    @When("I receive two column table with List of numbers as List of List of Integers")
    public void iReceiveTwoColumnsOfIntegersLists(List<List<Integer>> table) {
        System.out.println("Second row of table----");
        System.out.println(table.get(1));
        System.out.println();
    }

    //---------------------------------------------------
    //------------Three column examples------------------
    //---------------------------------------------------
    @When("I receive three column table as DataTable")
    public void iReceiveThreeColumnsIntsDataTable(DataTable table) {
        System.out.println("Print out third row");
        System.out.println(table.row(2));
        System.out.println("Print out third column");
        System.out.println(table.column(2));
        System.out.println("Print out the cell that is on second row in second column");
        System.out.println(table.cell(1,1));
        System.out.println();
    }

    @When("I receive three column table with first column as description in DataTable")
    public void iReceiveThreeColumnsWithFirstOneDescription(DataTable table) {
        Map<String, List<Integer>> listOfInts = table.asMap(String.class, (Type) List.class);
        System.out.println("Print out secondSet from converted DataTable");
        System.out.println(listOfInts.get("secondSet"));
        System.out.println("Print out fourthSet from converted DataTable");
        System.out.println(listOfInts.get("fourthSet"));
        System.out.println();
    }

    @When("I receive three column table with first column as description")
    public void iReceiveThreeColumnsWithDescription(Map<String, List<Integer>> table) {
        System.out.println("Print out secondSet after implicit conversion");
        System.out.println(table.get("secondSet"));
        System.out.println("Print out fourthSet after implicit conversion");
        System.out.println(table.get("fourthSet"));
        System.out.println();
    }

    @When("I receive four column table with header")
    public void iReceiveFourColumnsWithHeader(List<Map<String, String>> table) {
        System.out.println("Receive table with headers");
        System.out.println(table);
        System.out.println("Print out Anetes language---");
        System.out.println(table.get(1).get("language"));
        System.out.println();
    }

    @When("I receive four column table with header and am just messing with you at this point")
    public void iAmMessingWithMyStudentsNow(Map<String, Map<String, String>> table) {
        System.out.println("Receive table with headers");
        System.out.println(table);
        System.out.println("Print out map entry that has key of id1");
        System.out.println(table.get("id1"));
        System.out.println("Print out Anetes language---");
        System.out.println(table.get("id2").get("language"));
        System.out.println();
    }

    //---------------------------------------------------
    //----------------Advanced examples------------------
    //---------------------------------------------------
    @When("I receive three column person as a DataTable")
    public void iReceivePersonInDataTable(DataTable table) {
        List<String> column = table.column(1);
        PersonData person = new PersonData(
                column.get(0),
                column.get(1),
                column.get(2)
        );
        System.out.println(person);
    }

    //DOES NOT WORK RIGHT NOW
    @When("I receive three column person as row with headers")
    public void iReceivePersonInMap(List<PersonData> persons) {
        System.out.println(persons);
        System.out.println();
    }

    //DOES NOT WORK RIGHT NOW
    @When("I receive three column person as list")
    public void iReceivePersonInList(PersonData person) {
        System.out.println(person);
        System.out.println();
    }
}
