# Archetype for creating Vaadin widget add-ons

_To try this out:_

mvn archetype:generate -DarchetypeGroupId=com.vaadin -DarchetypeArtifactId=vaadin-archetype-widget -DarchetypeVersion=7.0-SNAPSHOT


_To compile the archetype:_

git clone https://github.com/vaadin/maven-integration.git

cd archetypes/vaadin-archetype-widget

mvn install

cd ..

mvn archetype:generate -DarchetypeGroupId=com.vaadin -DarchetypeArtifactId=vaadin-archetype-widget -DarchetypeVersion=7.0-SNAPSHOT
