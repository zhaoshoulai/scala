name := "myplay"

version := "1.0"

lazy val `myplay` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws  , anorm , filters,
    "mysql" % "mysql-connector-java" % "5.1.28"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

  