package com.mycompany.productsalesapp.question2;

// "import" = borrowing ready-made tools from Java's library
import java.awt.BorderLayout;     // layout: top / middle / bottom
import java.awt.GridLayout;       // layout: neat rows and columns
import java.io.BufferedWriter;    // writes text into a file
import java.io.FileWriter;        // opens/creates a file
import java.io.IOException;       // the error you get if saving a file fails
import javax.swing.*;             // all GUI parts: buttons, labels, windows, menus...

/**
 * "extends JFrame" means: our class IS a window.
 * (This is INHERITANCE: we get all of JFrame's features for free.)
 */
public class ProductSalesForm extends JFrame {

    // ===== The parts that sit on the form =====

    // Text area = a big box for many lines of text (6 rows high, 25 columns wide)
    private final JTextArea txtReport = new JTextArea(6, 25);

    // Label = plain text on the form. Users can't type in a label, so it's READ-ONLY.
    private final JLabel lblYearsProcessed = new JLabel("Years Processed: ");

    // Our ProductSales object: it holds the data and does the maths
    private final ProductSales productSales = new ProductSales();

    // ===== CONSTRUCTOR: runs once, when the window is created. It builds the form. =====
    public ProductSalesForm() {

        setTitle("Product Sales Application");    // text in the window's title bar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // clicking X stops the program

        // put the menu bar (File, Tools) at the top of the window
        setJMenuBar(createMenuBar());

        // --- Make the two buttons ---
        JButton btnLoad = new JButton("Load Product Data");
        JButton btnSave = new JButton("Save Product Data");

        // "When clicked, run this method"
        // e -> ...  is a short way to write a click handler (called a LAMBDA)
        btnLoad.addActionListener(e -> loadProductData());
        btnSave.addActionListener(e -> saveProductData());

        // A panel is like an invisible box that holds things.
        // GridLayout(2, 1, 5, 5) = 2 rows, 1 column, 5 pixels gap → buttons stack on top of each other
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnSave);

        // stop the user from typing in the text area (we only show results there)
        txtReport.setEditable(false);

        // --- Put everything on the window ---
        // BorderLayout has areas: NORTH (top), CENTER (middle), SOUTH (bottom)
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // space around the edges
        mainPanel.add(buttonPanel, BorderLayout.NORTH);                 // buttons at the top
        mainPanel.add(new JScrollPane(txtReport), BorderLayout.CENTER); // text area in the middle (with scroll bars)
        mainPanel.add(lblYearsProcessed, BorderLayout.SOUTH);          // label at the bottom
        add(mainPanel);   // put the panel inside the window

        pack();                       // make the window just big enough for everything
        setLocationRelativeTo(null);  // open the window in the middle of the screen
    }

    // ===== Builds the menu bar: File and Tools (Q2.3) =====
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();   // the bar across the top

        // --- File menu ---
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));   // System.exit(0) = close the program
        fileMenu.add(exitItem);

        // --- Tools menu ---
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem loadItem = new JMenuItem("Load Product Data");
        JMenuItem saveItem = new JMenuItem("Save Product Data");
        JMenuItem clearItem = new JMenuItem("Clear");

        // The menu items call the SAME methods as the buttons.
        // The paper asks for this, and it means no repeated code (= efficiency marks!)
        loadItem.addActionListener(e -> loadProductData());
        saveItem.addActionListener(e -> saveProductData());
        clearItem.addActionListener(e -> clearForm());

        toolsMenu.add(loadItem);
        toolsMenu.add(saveItem);
        toolsMenu.add(clearItem);

        // put both menus on the bar
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        return menuBar;
    }

    // ===== LOAD button / menu: calculate and show the report (Q2.2) =====
    private void loadProductData() {

        // Build the report as one String. "\n" means "go to a new line".
        // Math.round() rounds 366.67 to 367 (like the sample screenshot)
        String report = "Total Sales: " + productSales.GetTotalSales() + "\n"
                + "Average Sales: " + Math.round(productSales.GetAverageSales()) + "\n"
                + "Sales over limit: " + productSales.GetSalesOverLimit() + "\n"
                + "Sales under limit: " + productSales.GetSalesUnderLimit();

        txtReport.setText(report);   // show the report in the text area

        // show the number of years in the label at the bottom
        lblYearsProcessed.setText("Years Processed: " + productSales.GetProductsProcessed());
    }

    // ===== SAVE button / menu: write the report to data.txt (Q2.2) =====
    private void saveProductData() {

        // If the text area is empty, there's nothing to save, so tell the user and stop
        if (txtReport.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please load the product data first.");
            return;   // "return" here = leave this method now
        }

        // try = "attempt this; it might fail" (e.g. the file is locked)
        // Putting the writer inside try( ) closes the file AUTOMATICALLY when done.
        // FileWriter("data.txt") creates the file (or replaces the old one).
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {

            writer.write("DATA LOG");                          // heading
            writer.newLine();                                  // new line
            writer.write("******************************");    // stars line
            writer.newLine();
            writer.write(txtReport.getText());                 // the report from the text area
            writer.newLine();
            writer.write("******************************");

            // pop-up box to say it worked
            JOptionPane.showMessageDialog(this, "Data saved to data.txt");

        } catch (IOException ex) {
            // catch = "if it failed, do this instead of crashing"
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
        }
    }

    // ===== CLEAR menu: empty the text area and reset the label (Q2.3) =====
    private void clearForm() {
        txtReport.setText("");                          // "" = empty text
        lblYearsProcessed.setText("Years Processed: ");  // back to the starting text
    }

    // ===== MAIN: where the program starts =====
    public static void main(String[] args) {
        // Create the window and make it visible.
        // invokeLater = the safe, correct way to start a Swing GUI.
        SwingUtilities.invokeLater(() -> new ProductSalesForm().setVisible(true));
    }
}