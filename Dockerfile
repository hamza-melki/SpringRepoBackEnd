# Utiliser une image de base, par exemple l'image officielle de Java
FROM openjdk:11

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR de l'application Spring dans le conteneur
COPY target/tpachatproject.jar .

# Exposer le port sur lequel l'application Spring sera en écoute
EXPOSE 5000

# Démarrer l'application Spring
CMD ["java", "-jar", "tpachatproject.jar"]
