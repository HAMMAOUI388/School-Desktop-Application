package examen;



public class Main {

    @SuppressWarnings("unused")
	public static void main(String[] args) {
        CreateDatabaseAndTables createDatabaseAndTables = new CreateDatabaseAndTables();
        CrudOperations crudOperation = new CrudOperations();
        UserInterface userInterface = new UserInterface(crudOperation);
    }
}
