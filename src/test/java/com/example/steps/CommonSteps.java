package com.example.steps;

import com.example.context.Book;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import java.util.*;

import static com.example.context.Context.*;
import static com.example.utils.DataProcessing.processDataTable;

public class CommonSteps {

    @BeforeStep
    public void beforeEveryStep() {
        scenario.log("Current URL: " + getDriver().getCurrentUrl());
    }

    @Given("user enter login page")
    public void user_enter_login_page() {
        scenario.log("Entered login page");
    }

    @DataTableType
    public Book bookEntryTransformer(Map<String, String> row) {

        return new Book(
                row.get("title"),
                row.get("author"),
                Integer.parseInt(row.get("yearOfPublishing"))
        );
    }

    @Given("the following books")
    public void theFollowingBooks(List<Book> books) {

        for (Book book : books) {
            System.out.printf(
                    "'%s', published in %d, was written by %s\n",
                    book.title,
                    book.yearOfPublishing,
                    book.author
            );
        }
    }

    @Given("^the following table$")
    public void theFollowingTable(Map<String, String> dataTable) {
        dataTable = processDataTable(dataTable);
        scenario.log(dataTable.toString());
    }
}
