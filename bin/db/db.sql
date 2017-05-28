create database studenten;
use studenten;

CREATE USER 'student'@'localhost' IDENTIFIED BY PASSWORD '*1308E0FCD43112F8D948AB093F54892CB7B220AA';
GRANT ALL PRIVILEGES ON * . * TO 'student'@'localhost' IDENTIFIED BY PASSWORD '*1308E0FCD43112F8D948AB093F54892CB7B220AA' WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;
GRANT ALL PRIVILEGES ON `studenten` . * TO 'student'@'localhost';

CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(32) NOT NULL DEFAULT '''''',
  `adres` varchar(32) NOT NULL DEFAULT '''''',
  `studierichting` varchar(32) NOT NULL DEFAULT '''''',
  `leeftijd` int(11) NOT NULL DEFAULT '0',
  `cijfergemiddelde` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

INSERT INTO `student` (`id`, `naam`, `adres`, `studierichting`, `leeftijd`, `cijfergemiddelde`) VALUES
(1, 'Melvin', 'mope straat', 'Software Engineering', 20, 6),
(2, 'Gerbert', 'Jansteen straat', 'Software Engineering', 19, 9),
(3, 'Malik', 'Zinnia straat', 'Software Engineering', 22, 10),
(4, 'Sevan', 'serverstraat 4', 'Software Engineering', 19, 10),
(5, 'click for virus', 'virusstraat 5', 'Virus Engineering', 23, 3);
