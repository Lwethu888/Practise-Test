package com.mycompany.productsalesapp.question2;

/**
 * This class "signs the contract" (implements IProductSales).
 * It stores the sales data and does all the math.
 */
public class ProductSales implements IProductSales {

    // A CONSTANT: a value that never changes.
    // "static final" = fixed forever. CAPITAL LETTERS = the naming rule for constants.
    // If the limit changes one day, we only change it here.
    private static final int SALES_LIMIT = 500;

    // The sales table (2D array). Think of it like an Excel sheet:
    //               Microphone  Speakers  Mixing Desk
    //   Year 1  →      300        150         700
    //   Year 2  →      250        200         600
    // "private" = only this class can touch it (this is called ENCAPSULATION).
    private final int[][] productSales = {
        {300, 150, 700},   // row 0 = Year 1
        {250, 200, 600}    // row 1 = Year 2
    };

    // @Override = "this method comes from the interface".
    // If you spell the name wrong, NetBeans will warn you.
    @Override
    public int[][] GetProductSales() {
        return productSales;   // just hand back the table
    }

    @Override
    public int GetTotalSales() {
        int total = 0;   // start the total at zero

        // OUTER loop: go through each ROW (each year)
        for (int year = 0; year < productSales.length; year++) {

            // INNER loop: go through each COLUMN (each product) in that row
            for (int product = 0; product < productSales[year].length; product++) {

                // add this cell's value to the total
                // (total += x  is short for  total = total + x)
                total += productSales[year][product];
            }
        }
        return total;   // 300+150+700+250+200+600 = 2200
    }

    @Override
    public int GetSalesOverLimit() {
        int count = 0;   // counter starts at zero

        // visit every cell in the table (same two loops as above)
        for (int year = 0; year < productSales.length; year++) {
            for (int product = 0; product < productSales[year].length; product++) {

                // if this sale reached the limit (500 or more)...
                if (productSales[year][product] >= SALES_LIMIT) {
                    count++;   // ...add 1 to the counter (count++ means count = count + 1)
                }
            }
        }
        // The paper doesn't say what happens at exactly 500,
        // so I chose: 500 or more = "over" (the product reached its target).
        return count;   // 700 and 600 → 2
    }

    @Override
    public int GetSalesUnderLimit() {
        int count = 0;

        for (int year = 0; year < productSales.length; year++) {
            for (int product = 0; product < productSales[year].length; product++) {

                // if this sale is smaller than 500, count it
                if (productSales[year][product] < SALES_LIMIT) {
                    count++;
                }
            }
        }
        return count;   // 300, 150, 250, 200 → 4
    }

    @Override
    public int GetProductsProcessed() {
        // .length on a 2D array = number of ROWS
        // each row is one year, so 2 rows = 2 years
        return productSales.length;
    }

    @Override
    public double GetAverageSales() {
        int count = 0;

        // count how many numbers are in the table
        // productSales[year].length = how many columns in that row (3)
        for (int year = 0; year < productSales.length; year++) {
            count += productSales[year].length;   // 3 + 3 = 6
        }

        // safety check: you can't divide by zero
        if (count == 0) {
            return 0;
        }

        // We REUSE GetTotalSales() instead of writing the loop again (no repeated code!)
        // (double) is VERY important: without it, 2200 / 6 = 366 (Java chops off decimals)
        // with it, 2200 / 6 = 366.666...
        return (double) GetTotalSales() / count;
    }
}