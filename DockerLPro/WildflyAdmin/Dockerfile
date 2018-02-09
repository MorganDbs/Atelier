# Wildfly-admin: docker build -t lpro/wildfly-admin .
FROM jboss/wildfly:11.0.0.Final

ENV WILDFLY_HOME /opt/jboss/wildfly
ENV DEPLOYMENT_DIR ${WILDFLY_HOME}/standalone/deployments/

# Admin user
RUN ${WILDFLY_HOME}/bin/add-user.sh -u admin -p docker#admin --silent

# Mise en place de la BD
COPY param ${WILDFLY_HOME}/customization/

USER root

# lancement de la configuration de la BD
RUN chmod +x ${WILDFLY_HOME}/customization/execute.sh
RUN ${WILDFLY_HOME}/customization/execute.sh standalone 
RUN rm -rf ${WILDFLY_HOME}/standalone/configuration/standalone_xml_history
RUN chown -R jboss:jboss /opt/jboss/wildfly/

USER jboss

# Exposition 8080 et 9990 (admin)
EXPOSE 8080 9990

# Commande démarrage
CMD ${WILDFLY_HOME}/bin/standalone.sh -b `hostname -i` -bmanagement `hostname -i` 

