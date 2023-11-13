/**
 * Countries MySQL Tables 
 * Compiled by Atanas Bozhinov, 2015
 */

CREATE TABLE eucountries (
  country_id INT NOT NULL IDENTITY(1,1),
  code CHAR(2) NOT NULL,
  name VARCHAR(64) NOT NULL,
  full_name VARCHAR(128) NOT NULL,
  iso3 CHAR(3) NOT NULL,
  PRIMARY KEY (country_id),
  UNIQUE (code)
);
go
-- ----------------------------
-- Records of countries
-- ----------------------------
INSERT INTO eucountries VALUES ( 'AL', 'Albania', 'Republic of Albania', 'ALB');
INSERT INTO eucountries VALUES ( 'AN', 'Netherlands Antilles', 'Netherlands Antilles', 'ANT');
INSERT INTO eucountries VALUES ( 'AT', 'Austria', 'Republic of Austria', 'AUT');
INSERT INTO eucountries VALUES ( 'BA', 'Bosnia and Herzegovina', 'Bosnia and Herzegovina', 'BIH');
INSERT INTO eucountries VALUES ( 'BE', 'Belgium', 'Kingdom of Belgium', 'BEL');
INSERT INTO eucountries VALUES ( 'BG', 'Bulgaria', 'Republic of Bulgaria', 'BGR');
INSERT INTO eucountries VALUES ( 'CH', 'Switzerland', 'Swiss Confederation', 'CHE');
INSERT INTO eucountries VALUES ( 'CZ', 'Czech Republic', 'Czech Republic', 'CZE');
INSERT INTO eucountries VALUES ( 'DE', 'Germany', 'Federal Republic of Germany', 'DEU');
INSERT INTO eucountries VALUES ( 'DK', 'Denmark', 'Kingdom of Denmark', 'DNK');
INSERT INTO eucountries VALUES ( 'EE', 'Estonia', 'Republic of Estonia', 'EST');
INSERT INTO eucountries VALUES ( 'ES', 'Spain', 'Kingdom of Spain', 'ESP');
INSERT INTO eucountries VALUES ( 'FI', 'Finland', 'Republic of Finland', 'FIN');
INSERT INTO eucountries VALUES ( 'FR', 'France', 'French Republic', 'FRA');
INSERT INTO eucountries VALUES ( 'GB', 'United Kingdom', 'United Kingdom of Great Britain & Northern Ireland', 'GBR');
INSERT INTO eucountries VALUES ( 'GE', 'Georgia', 'Georgia', 'GEO');
INSERT INTO eucountries VALUES ( 'GL', 'Greenland', 'Greenland', 'GRL');
INSERT INTO eucountries VALUES ( 'GR', 'Greece', 'Hellenic Republic Greece', 'GRC');
INSERT INTO eucountries VALUES ( 'HR', 'Croatia', 'Republic of Croatia', 'HRV');
INSERT INTO eucountries VALUES ( 'HU', 'Hungary', 'Republic of Hungary', 'HUN');
INSERT INTO eucountries VALUES ( 'IE', 'Ireland', 'Ireland', 'IRL');
INSERT INTO eucountries VALUES ( 'IT', 'Italy', 'Italian Republic', 'ITA');
INSERT INTO eucountries VALUES ( 'LT', 'Lithuania', 'Republic of Lithuania', 'LTU');
INSERT INTO eucountries VALUES ( 'LU', 'Luxembourg', 'Grand Duchy of Luxembourg', 'LUX');
INSERT INTO eucountries VALUES ( 'LV', 'Latvia', 'Republic of Latvia', 'LVA');
INSERT INTO eucountries VALUES ( 'MA', 'Morocco', 'Kingdom of Morocco', 'MAR');
INSERT INTO eucountries VALUES ( 'MC', 'Monaco', 'Principality of Monaco', 'MCO');
INSERT INTO eucountries VALUES ( 'MD', 'Moldova', 'Republic of Moldova', 'MDA');
INSERT INTO eucountries VALUES ( 'ME', 'Montenegro', 'Republic of Montenegro', 'MNE');
INSERT INTO eucountries VALUES ( 'MK', 'Macedonia', 'Republic of Macedonia', 'MKD');
INSERT INTO eucountries VALUES ( 'MT', 'Malta', 'Republic of Malta', 'MLT');
INSERT INTO eucountries VALUES ( 'NL', 'Netherlands', 'Kingdom of the Netherlands', 'NLD');
INSERT INTO eucountries VALUES ( 'NO', 'Norway', 'Kingdom of Norway', 'NOR');
INSERT INTO eucountries VALUES ( 'PL', 'Poland', 'Republic of Poland', 'POL');
INSERT INTO eucountries VALUES ( 'PT', 'Portugal', 'Portuguese Republic', 'PRT');
INSERT INTO eucountries VALUES ( 'RO', 'Romania', 'Romania', 'ROU');
INSERT INTO eucountries VALUES ( 'RS', 'Serbia', 'Republic of Serbia', 'SRB');
INSERT INTO eucountries VALUES ( 'RU', 'Russian Federation', 'Russian Federation', 'RUS');
INSERT INTO eucountries VALUES ( 'SE', 'Sweden', 'Kingdom of Sweden', 'SWE');
INSERT INTO eucountries VALUES ( 'SI', 'Slovenia', 'Republic of Slovenia', 'SVN');
INSERT INTO eucountries VALUES ( 'SK', 'Slovakia', 'Slovakia (Slovak Republic)', 'SVK');
INSERT INTO eucountries VALUES ( 'SM', 'San Marino', 'Republic of San Marino', 'SMR');
INSERT INTO eucountries VALUES ( 'TR', 'Turkey', 'Republic of Turkey', 'TUR');
INSERT INTO eucountries VALUES ( 'UA', 'Ukraine', 'Ukraine', 'UKR');
INSERT INTO eucountries VALUES ( 'VA', 'Vatican City', 'Holy See (Vatican City State)', 'VAT');
