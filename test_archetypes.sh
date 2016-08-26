#!/bin/bash
VAADIN_FRAMEWORK_VERSION=7.7.0
VAADIN_PLUGIN_VERSION=7.7.0
VAADIN_ARCHETYPE_VERSION=7.6-SNAPSHOT
TEMP_DIR=`pwd`/target/test_projects

function fail {
echo "Fail! Check $LOG_FILE"; exit 100
}

echo "Vaadin Framework version: $VAADIN_FRAMEWORK_VERSION"
echo "Vaadin Archetype version: $VAADIN_ARCHETYPE_VERSION"
echo "Clean up..."
rm -rf $TEMP_DIR
mkdir -p $TEMP_DIR
#vaadin-archetype-touchkit and vaadin-archetype-liferay-portlet-sharedlib are skipped

for arch in  \
   'vaadin-archetype-application'\
   'vaadin-archetype-application-multimodule'\
   'vaadin-archetype-application-example'\
   'vaadin-archetype-widget'\
   'vaadin-archetype-liferay-portlet'\
; do
  LOG_FILE=$TEMP_DIR/$arch.log
  echo "Testing archetype: $arch"
  cd archetypes/$arch > $LOG_FILE
  echo "...Uninstall previous archetype from local repo..."
  mvn dependency:purge-local-repository -DmanualInclude="com.vaadin:$arch"  >> $LOG_FILE || fail
  echo "...Install archetype to local repo..."
  mvn versions:set  -DnewVersion=$VAADIN_ARCHETYPE_VERSION >> $LOG_FILE
  mvn clean install -Dvaadin.version=$VAADIN_FRAMEWORK_VERSION -Dvaadin.plugin.version=$VAADIN_PLUGIN_VERSION >> $LOG_FILE || fail
  echo "...Generating trial project..."
  cd $TEMP_DIR
  mvn archetype:generate -DarchetypeGroupId=com.vaadin -DarchetypeArtifactId=$arch\
    -DarchetypeVersion=$VAADIN_ARCHETYPE_VERSION -DgroupId=com.vaadin.maven.test -DartifactId=$arch\
    -DinteractiveMode=false -DarchetypeRepository=local >> $LOG_FILE || fail
  echo "...Compiling trial project with prerelease profile..."
  cd $arch
  mvn clean install -Dliferay.version=6.2.3 -Dliferay.maven.plugin.version=6.2.10.13 -Pvaadin-prerelease >> $LOG_FILE || fail
  echo "...Compiling trial project without vaadin-prerelease profile..."
  mvn  clean install -Dliferay.version=6.2.3 -Dliferay.maven.plugin.version=6.2.10.13 >> $LOG_FILE && ( echo "The project could be built without 'vaadin-prerelease' profile"; exit 200 )
  cd ../../..
done
echo "OK!"
exit 1
