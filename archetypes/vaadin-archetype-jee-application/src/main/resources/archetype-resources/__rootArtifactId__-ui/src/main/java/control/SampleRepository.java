#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.control;

import ${package}.entity.SampleEntity;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import javax.transaction.Transactional;

@Transactional(Transactional.TxType.MANDATORY)
@Repository(forEntity = SampleEntity.class)
public interface SampleRepository extends EntityRepository<SampleEntity, Long> {
}
