SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

--
-- Table structure for table `comparison_operator_filters`
--

DROP TABLE IF EXISTS `comparison_operator_filters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comparison_operator_filters` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `comparison_operator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comparison_operator_filters`
--

LOCK TABLES `comparison_operator_filters` WRITE;
/*!40000 ALTER TABLE `comparison_operator_filters` DISABLE KEYS */;
INSERT INTO `comparison_operator_filters` VALUES (1,'string','contains'),(2,'string','does not contain'),(3,'string','begins with'),(4,'string','ends with'),(5,'string','is'),(7,'string','in'),(11,'datetime','after'),(12,'datetime','before'),(13,'string','equals'),(14,'string','not'),(15,'boolean','not'),(17,'datetime','between'),(18,'number','greater than'),(19,'number','greater than or equal to'),(20,'number','does not equal'),(21,'number','less than'),(22,'number','less than or equal to'),(23,'keyword','in'),(24,'keyword','equals'),(25,'number','equals');
/*!40000 ALTER TABLE `comparison_operator_filters` ENABLE KEYS */;
UNLOCK TABLES;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;