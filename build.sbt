import sbt.Keys._
import com.typesafe.sbt.SbtNativePackager.autoImport.executableScriptName
import com.typesafe.sbt.packager.universal.UniversalPlugin.autoImport.stage
import Dependencies._

lazy val root =
  Project(id = "root", base = file("."))
    .settings(
      name := "root",
    )
    .withId("root")
    .settings(commonSettings)
    .aggregate(migrator)
    .enablePlugins(BuildInfoPlugin)

lazy val migrator = appModule(moduleID = "migrator")
  .settings(
    libraryDependencies ++= Seq(
      spark_sql,
      spark_hive,
      hadoop_cloud,
      iceberg_runtime,
      iceberg_hive_runtime
    )
  )

lazy val commonSettings = Seq(
  scalaVersion := "3.1.2",
  scalacOptions := Seq(
    "-unchecked",
    "-deprecation",
    "-encoding", "utf8"
  ),
  libraryDependencies ++= Seq(
    logback
  )
)

def appModule(moduleID: String): Project =
  Project(id = moduleID, base = file(moduleID))
    .settings(name := moduleID)
    .withId(moduleID)
    .settings(commonSettings)
