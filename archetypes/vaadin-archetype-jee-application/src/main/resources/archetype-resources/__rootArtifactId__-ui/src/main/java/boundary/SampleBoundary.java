#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.boundary;

import ${package}.entity.SampleEntity;

import javax.ejb.Local;

@Local
public interface SampleBoundary {

    SampleEntity createAndReturn(String foo);

}
