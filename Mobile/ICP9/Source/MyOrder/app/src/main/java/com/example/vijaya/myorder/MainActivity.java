package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PIZZA_PRICE = 5;
    /*only meat affects price. Veggies and condiments are wrapped in pizza_price*/
    final int PEPPERONI = 1;
    final int SAUSAGE = 1;
    final double CHICKEN = 1.50;
    final int HAM = 2;
    final int BACON = 2;
    final double SARDINES = 2.50;
    int quantity = 1;//set so user is automatically ordering one pizza
    boolean order_complete = false;
    public static String orderSummaryMessage = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 2) {
            Intent ordercomplete = new Intent(this, order.class);
            //finish();
            startActivity(ordercomplete);//redirects to summary page

        }
        /**
         * This method is called when the order button is clicked.
         */
    }

    public void submitOrder(View view) {
        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        /*******************************************MEATS*************************************/
        // check if pepperoni is selected. used for order summary & price
        CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni);
        boolean has_pepperoni = pepperoni.isChecked();

        // check if sausage is selected. used for order summary
        CheckBox sausage = (CheckBox) findViewById(R.id.sausage);
        boolean has_sausage = sausage.isChecked();

        // check if chicken is selected. used for order summary
        CheckBox chicken = (CheckBox) findViewById(R.id.chicken);
        boolean has_chicken = chicken.isChecked();

        // check if ham is selected. used for order summary
        CheckBox ham = (CheckBox) findViewById(R.id.ham);
        boolean has_ham = ham.isChecked();

        // check if bacon is selected. used for order summary
        CheckBox bacon = (CheckBox) findViewById(R.id.bacon);
        boolean has_bacon = bacon.isChecked();

        // check if sardines is selected. used for order summary
        CheckBox sardines = (CheckBox) findViewById(R.id.sardines);
        boolean has_sardines = sardines.isChecked();

        /*****************************************VEGGIES*************************************/
        // check if bell pepper is selected. only used for order summary
        CheckBox b_pep = (CheckBox) findViewById(R.id.bellp);
        boolean has_bell = b_pep.isChecked();

        // check if banana pepper is selected. only used for order summary
        CheckBox banana_p = (CheckBox) findViewById(R.id.bananap);
        boolean has_banana = banana_p.isChecked();

        // check if mushroom is selected. only used for order summary
        CheckBox mushrooms = (CheckBox) findViewById(R.id.mushrooms);
        boolean has_mushrooms = mushrooms.isChecked();

        // check if onion is selected. only used for order summary
        CheckBox onions = (CheckBox) findViewById(R.id.onions);
        boolean has_onions = onions.isChecked();

        // check if olives is selected. only used for order summary
        CheckBox olives = (CheckBox) findViewById(R.id.olives);
        boolean has_olives = olives.isChecked();

        // check if spinach is selected. only used for order summary
        CheckBox spinach = (CheckBox) findViewById(R.id.spinach);
        boolean has_spinach = spinach.isChecked();

        // check if tomatoes are selected. only used for order summary
        CheckBox tomatoes = (CheckBox) findViewById(R.id.tomatoes);
        boolean has_tomatoes = tomatoes.isChecked();

        /**************************************CONDIMENTS*************************************/

        // check if ranch is selected. only used for order summary
        CheckBox ranch = (CheckBox) findViewById(R.id.ranch);
        boolean has_ranch = ranch.isChecked();

        // check if parmesan is selected. only used for order summary
        CheckBox parm = (CheckBox) findViewById(R.id.parmesean);
        boolean has_parm = parm.isChecked();

        // check if crushed red pepper is selected. only used for order summary
        CheckBox crp = (CheckBox) findViewById(R.id.crp);
        boolean has_crp = crp.isChecked();

        // check if salt/pepper is selected. only used for order summary
        CheckBox sp = (CheckBox) findViewById(R.id.saltpepper);
        boolean has_sp = sp.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(has_pepperoni, has_sausage, has_chicken, has_ham, has_bacon, has_sardines);

        /*****************************BUTTON/EMAIL FUNCTIONALITY*********************************/

        //create and store the order summary
        orderSummaryMessage = createOrderSummary(userInputName, has_pepperoni, has_sausage, has_chicken,
                has_ham, has_bacon, has_sardines, has_bell, has_banana, has_mushrooms, has_onions, has_olives,
                has_spinach, has_tomatoes, has_ranch, has_parm, has_crp, has_sp, totalPrice);

        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents
        sendEmail(orderSummaryMessage);//sends email

        //Intent ordercomplete = new Intent(this, order.class);
        //finish();
        //startActivity(ordercomplete);//redirects to summary page

    }

    public void sendEmail(String output) {
        // Write the relevant code for triggering email

        // Hint to accomplish the task
        // Write the relevant code for triggering email
        String[] TO = {"nefrr@mail.umkc.edu"};//user email to send to
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");//sets body formatting
        intent.putExtra(Intent.EXTRA_EMAIL, TO);//populates the receiver's email
        intent.putExtra(Intent.EXTRA_SUBJECT, "UMKC Pizza Order Summary");//populates the subject line
        intent.putExtra(Intent.EXTRA_TEXT, output);//populates the email body

        if (intent.resolveActivity(getPackageManager()) != null) {//checks for default email application
            //startActivity(intent);
            startActivityForResult(Intent.createChooser(intent, "Choose an Email client :"),2);//redirects when order is complete
            //finish();

        } else {//if none found, give an error message
            Toast.makeText(this, "No email client installed", Toast.LENGTH_LONG);
        }
        //Intent ordercomplete = new Intent(this, order.class);
        //finish();
        //startActivity(ordercomplete);//redirects to summary page
    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean has_pepperoni, boolean has_sausage, boolean has_chicken,
                                      boolean has_ham, boolean has_bacon, boolean has_sardines, boolean has_bell, boolean has_banana,
                                      boolean has_mushrooms, boolean has_onions, boolean has_olives, boolean has_spinach, boolean has_tomatoes,
                                      boolean has_ranch, boolean has_parm, boolean has_crp, boolean has_sp, float price) {
        //concatenates strings to save summary
        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                getString(R.string.order_summary_pepperoni, boolToString(has_pepperoni)) + "\n" +
                getString(R.string.order_summary_sausage, boolToString(has_sausage)) + "\n" +
                getString(R.string.order_summary_chicken, boolToString(has_chicken)) + "\n" +
                getString(R.string.order_summary_ham, boolToString(has_ham)) + "\n" +
                getString(R.string.order_summary_bacon, boolToString(has_bacon)) + "\n" +
                getString(R.string.order_summary_sardines, boolToString(has_sardines)) + "\n" +
                getString(R.string.order_summary_bell, boolToString(has_bell)) + "\n" +
                getString(R.string.order_summary_banana, boolToString(has_banana)) + "\n" +
                getString(R.string.order_summary_mushrooms, boolToString(has_mushrooms)) + "\n" +
                getString(R.string.order_summary_onions, boolToString(has_onions)) + "\n" +
                getString(R.string.order_summary_olives, boolToString(has_olives)) + "\n" +
                getString(R.string.order_summary_spinach, boolToString(has_spinach)) + "\n" +
                getString(R.string.order_summary_tomatoes, boolToString(has_tomatoes)) + "\n" +
                getString(R.string.order_summary_ranch, boolToString(has_ranch)) + "\n" +
                getString(R.string.order_summary_parm, boolToString(has_parm)) + "\n" +
                getString(R.string.order_summary_crp, boolToString(has_crp)) + "\n" +
                getString(R.string.order_summary_sp, boolToString(has_sp)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n";
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean has_pepperoni, boolean has_sasuage, boolean has_chicken,
                                 boolean has_ham, boolean has_bacon, boolean has_sardines) {
        int basePrice = PIZZA_PRICE;
        if (has_pepperoni) {//adds price of pepperoni
            basePrice += PEPPERONI;
        }
        if (has_sasuage) {//adds price of sausage
            basePrice += SAUSAGE;
        }
        if (has_chicken) {//adds price of chicken
            basePrice += CHICKEN;
        }
        if (has_ham) {//adds price of ham
            basePrice += HAM;
        }
        if (has_bacon) {//adds price of bacon
            basePrice += BACON;
        }
        if (has_sardines) {//adds price of sardines
            basePrice += SARDINES;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }

    public void summary(View view){
        //same concept as the ordersubmit button is applied here
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        /*******************************************MEATS*************************************/
        // check if pepperoni is selected. used for order summary & price
        CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni);
        boolean has_pepperoni = pepperoni.isChecked();

        // check if sausage is selected. used for order summary
        CheckBox sausage = (CheckBox) findViewById(R.id.sausage);
        boolean has_sausage = sausage.isChecked();

        // check if chicken is selected. used for order summary
        CheckBox chicken = (CheckBox) findViewById(R.id.chicken);
        boolean has_chicken = chicken.isChecked();

        // check if ham is selected. used for order summary
        CheckBox ham = (CheckBox) findViewById(R.id.ham);
        boolean has_ham = ham.isChecked();

        // check if bacon is selected. used for order summary
        CheckBox bacon = (CheckBox) findViewById(R.id.bacon);
        boolean has_bacon = bacon.isChecked();

        // check if sardines is selected. used for order summary
        CheckBox sardines = (CheckBox) findViewById(R.id.sardines);
        boolean has_sardines = sardines.isChecked();

        /*****************************************VEGGIES*************************************/
        // check if bell pepper is selected. only used for order summary
        CheckBox b_pep = (CheckBox) findViewById(R.id.bellp);
        boolean has_bell = b_pep.isChecked();

        // check if banana pepper is selected. only used for order summary
        CheckBox banana_p = (CheckBox) findViewById(R.id.bananap);
        boolean has_banana = banana_p.isChecked();

        // check if mushroom is selected. only used for order summary
        CheckBox mushrooms = (CheckBox) findViewById(R.id.mushrooms);
        boolean has_mushrooms = mushrooms.isChecked();

        // check if onion is selected. only used for order summary
        CheckBox onions = (CheckBox) findViewById(R.id.onions);
        boolean has_onions = onions.isChecked();

        // check if olives is selected. only used for order summary
        CheckBox olives = (CheckBox) findViewById(R.id.olives);
        boolean has_olives = olives.isChecked();

        // check if spinach is selected. only used for order summary
        CheckBox spinach = (CheckBox) findViewById(R.id.spinach);
        boolean has_spinach = spinach.isChecked();

        // check if tomatoes are selected. only used for order summary
        CheckBox tomatoes = (CheckBox) findViewById(R.id.tomatoes);
        boolean has_tomatoes = tomatoes.isChecked();

        /**************************************CONDIMENTS*************************************/

        // check if ranch is selected. only used for order summary
        CheckBox ranch = (CheckBox) findViewById(R.id.ranch);
        boolean has_ranch = ranch.isChecked();

        // check if parmesan is selected. only used for order summary
        CheckBox parm = (CheckBox) findViewById(R.id.parmesean);
        boolean has_parm = parm.isChecked();

        // check if crushed red pepper is selected. only used for order summary
        CheckBox crp = (CheckBox) findViewById(R.id.crp);
        boolean has_crp = crp.isChecked();

        // check if salt/pepper is selected. only used for order summary
        CheckBox sp = (CheckBox) findViewById(R.id.saltpepper);
        boolean has_sp = sp.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(has_pepperoni, has_sausage, has_chicken, has_ham, has_bacon, has_sardines);

        /*****************************BUTTON/EMAIL FUNCTIONALITY*********************************/

        //create and store the order summary
        orderSummaryMessage = createOrderSummary(userInputName, has_pepperoni, has_sausage, has_chicken,
                has_ham, has_bacon, has_sardines, has_bell, has_banana, has_mushrooms, has_onions, has_olives,
                has_spinach, has_tomatoes, has_ranch, has_parm, has_crp, has_sp, totalPrice);

        //Toast.makeText(this,orderSummaryMessage, Toast.LENGTH_LONG).show();
        Intent summary = new Intent(MainActivity.this, summary.class);
        startActivity(summary);//redirects to summary page
    }
}