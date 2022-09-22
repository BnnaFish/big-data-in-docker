## Description

Minimal example to run Big Data tools in local docker.

## Architecture

Hive Metastore to store...metadata.

MariaDB as Hive backend.

Spark worker to run scripts.

Trino as a query engine to make requests via Pandas or IDE.

## Goal

The goal is to move some plane file from S3 to Data Lake with partitions.

Wellknown [iris](https://archive.ics.uci.edu/ml/datasets/iris) dataset is good enough for this purpose.

## HowTo

First fetch sample data

``` bash
wget -P ./data https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data
```

Then up minio. Files will be added via compose entrypoint command.

```bash
make minio_up
```


Then up Hive Metastore.

```bash
make hive_up
```

Then run Spark script to create new table in Iceberg and move data from file to this table.

```bash
make fill_tables
```

Then we need to up Trino to be used in pandas to make a query.

```bash
make trino_up
```

Install python requirements

```bash
poetry install
poetry shell
```

Then run pandas script

```bash
python pandas_app/fetch.py
```
