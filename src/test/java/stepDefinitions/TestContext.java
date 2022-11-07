package stepDefinitions;

public class TestContext {

	// Lấy ra đối tượng duy nhất
	public DataContext dataContext;

	public TestContext() {
		dataContext = new DataContext();
	}

	public DataContext getDataContext() {
		return dataContext;
	}
}
