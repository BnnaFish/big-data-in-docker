minio_up:
	docker-compose up minio-mc

hive_up:
	docker-compose up -d hive-metastore

trino_up:
	docker-compose up -d trino

fill_tables:
	sbt "migrator/run ./migrations/raw/iris/create_table.sql"
