SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

--
-- Table structure for table `workflows`
--

DROP TABLE IF EXISTS `workflows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflows` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `workflow_type` varchar(100) DEFAULT NULL,
  `days_to_complete` int DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_update_by_user_id` bigint DEFAULT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `owner` bigint DEFAULT NULL,
  `is_default` bit(1) DEFAULT b'0',
  `external_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `workflows_external_id_uindex` (`external_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflows`
--

LOCK TABLES `workflows` WRITE;
/*!40000 ALTER TABLE `workflows` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflows` ENABLE KEYS */;
UNLOCK TABLES;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;