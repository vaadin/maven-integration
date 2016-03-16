#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.backend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Generic tests for the CrudService
 */
public class CrudServiceTest {

	private CrudService<TestBean> service;

	@Before
	public void setUp() {
		service = new CrudService<>();
	}

	@Test
	public void testSaveTenBeansToService() {
		for (int i = 0; i < 10; ++i) {
			Assert.assertEquals("Unexpected size of the service storage", service.findAll().size(), i);
			TestBean entity = new TestBean();
			service.save(entity);
			Assert.assertTrue("Storage did not contain new entity", service.findAll().contains(entity));
		}
	}

	@Test
	public void testRemoveBeanFromService() {
		int i = 0;
		while (i < 10) {
			TestBean entity = new TestBean();
			service.save(entity);
			++i;
		}

		Assert.assertEquals("Initial size did not match", service.findAll().size(), i);
		while (service.findAll().size() > 2) {
			TestBean toRemove = service.findAll().get(2);
			service.delete(toRemove);

			Assert.assertEquals("Size did not match after remove", service.findAll().size(), --i);
			Assert.assertFalse("Storage should not contain removed bean", service.findAll().contains(toRemove));
		}
	}

	@Test
	public void testAddEntityTwice() {
		TestBean entity = new TestBean();
		service.save(entity);
		Assert.assertEquals("Unexpected size after store", service.findAll().size(), 1);
		service.save(entity);
		Assert.assertEquals("Same bean should not be stored twice", service.findAll().size(), 1);
	}
}
