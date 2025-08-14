package PROJECT;

public class main {
	public static void main(String[] args) {
	
		if (DatabaseConnection.checkDatabaseExists()) {
		
			if (DatabaseConnection.testConnection()) {
				System.out.println("All database tests passed! Ready to proceed.");
			} else {
				System.err.println("Database connection test failed!");
			}
		} else {
			System.err.println("Database 'airline_db' does not exist!");
		}

		System.out.flush();

		
		new Entry();

		DatabaseConnection.closeConnection();
	}
}