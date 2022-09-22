package migrator

import org.apache.spark.sql.SparkSession
import scala.io.Source._

object Migrator:

  @main def run(migrationFilePath: String) =
    val spark = SparkSession.builder
      .appName("Migrator")
      .config("spark.master", "local[1]")
      .config("spark.sql.extensions","org.apache.iceberg.spark.extensions.IcebergSparkSessionExtensions")
      .config("spark.sql.catalog.spark_catalog","org.apache.iceberg.spark.SparkSessionCatalog")
      .config("spark.sql.catalog.spark_catalog.type","hive")
      .config("spark.sql.catalog.spark_catalog.uri", "thrift://localhost:9083")
      .config("spark.sql.catalog.spark_catalog.warehouse","s3a://ml-lake/")
      .config("fs.s3a.access.key","minio_access_key")
      .config("fs.s3a.secret.key","minio_secret_key")
      .config("fs.s3a.endpoint", "http://localhost:9000")
      .config("spark.sql.catalog.my_catalog.io-impl","org.apache.iceberg.aws.s3.S3FileIO" )
      .config("spark.sql.warehouse.dir", "s3a://ml-lake/warehouse")
      .config("iceberg.engine.hive.enabled", "true")
      .config("fs.s3a.path.style.access", "true")
      .enableHiveSupport()
      .getOrCreate()
    import spark.implicits._

    val migration_file = fromFile(migrationFilePath).mkString

    spark.sql(migration_file)

    println("Table created")

    var df = spark.read
      .option("inferSchema", "true")
      .csv("s3a://etc/data/iris.data")
    df = df.toDF("sepal_length", "sepal_width", "petal_length", "petal_width", "class")
    df.printSchema

    df.sortWithinPartitions("class")
      .writeTo("spark_catalog.default.iris")
      .append();

    spark.stop
