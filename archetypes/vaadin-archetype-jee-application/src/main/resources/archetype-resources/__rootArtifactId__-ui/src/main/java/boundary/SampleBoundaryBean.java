#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.boundary;

import ${package}.control.SampleRepository;
import ${package}.entity.SampleEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SampleBoundaryBean implements SampleBoundary {

    @Inject
    SampleRepository sampleRepository;

    @Override
    public SampleEntity createAndReturn(String foo) {
        SampleEntity entity = new SampleEntity();
        entity.setFoo(foo);
        return sampleRepository.save(entity);
    }
}
