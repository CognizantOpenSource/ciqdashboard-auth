#CIQDashboard-auth

#Command to Execute JAR file 
java -jar auth-api-3.1.0.jar --server.port=<serverPort> --spring.data.mongodb.uri=mongodb://<DBUsername>:${spring.data.mongodb.credents}@<servername>:<DBPort>/<DBName> --spring.data.mongodb.credents=ENC(JasyptEncryptedDBPassword) --jasypt.encryptor.password=<Base64EncodeKey> 
