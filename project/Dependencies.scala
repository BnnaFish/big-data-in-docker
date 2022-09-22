import sbt._

object Dependencies {
  val spark_sql = ("org.apache.spark" %% "spark-sql" % "3.3.0").cross(CrossVersion.for3Use2_13)
  val spark_hive = ("org.apache.spark" %% "spark-hive" % "3.3.0").cross(CrossVersion.for3Use2_13)
  val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  val hadoop_cloud = ("org.apache.spark" %% "spark-hadoop-cloud" % "3.3.0").cross(CrossVersion.for3Use2_13)
  val iceberg_runtime = ("org.apache.iceberg" % "iceberg-spark-runtime-3.3" % "0.14.0").cross(CrossVersion.for3Use2_13)
  val iceberg_hive_runtime = "org.apache.iceberg" % "iceberg-hive-runtime" % "0.14.0"
}
