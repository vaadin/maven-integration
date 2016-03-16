#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend;

public class TestBean {

	private static int counter = 0;

	private final int id;

	public TestBean() {
		id = ++counter;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "{" + getClass().getSimpleName() + ":id=" + getId() + "}";
	}
}
