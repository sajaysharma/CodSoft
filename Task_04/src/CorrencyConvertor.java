import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CorrencyConvertor { // If possible, rename to CurrencyConverter for correctness

    // Method to get exchange rate from API
    public static double getExchangeRate(String base, String target) {
        try {
            String urlStr = "https://api.exchangerate.host/convert?from=" + base + "&to=" + target + "&amount=1";
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response from connection input stream
            Scanner sc = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (sc.hasNext()) {
                response.append(sc.nextLine());
            }
            sc.close();

            // Parse JSON and check for valid response
            JSONObject obj = new JSONObject(response.toString());
            if (obj.has("info") && obj.getJSONObject("info").has("rate")) {
                return obj.getJSONObject("info").getDouble("rate");
            } else {
                System.out.println("⚠️ Unexpected API response format.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error fetching exchange rate: " + e.getMessage());
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Step 1: Get base currency
        System.out.print("Enter base currency (e.g., USD, INR, EUR): ");
        String base = input.next().toUpperCase();

        // Step 2: Get target currency
        System.out.print("Enter target currency (e.g., INR, USD, GBP): ");
        String target = input.next().toUpperCase();

        // Step 3: Get amount
        double amount = 0;
        while (true) {
            System.out.print("Enter amount to convert: ");
            if (input.hasNextDouble()) {
                amount = input.nextDouble();
                if (amount > 0) break;
                else System.out.println("Amount must be positive.");
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); // clear invalid input
            }
        }

        // Step 4: Fetch rate
        double rate = getExchangeRate(base, target);

        if (rate != -1) {
            double converted = amount * rate;
            System.out.println("\n💰 " + amount + " " + base + " = " + converted + " " + target);
        } else {
            System.out.println("❌ Conversion failed!");
        }
        input.close();
    }
}
